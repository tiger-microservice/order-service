package com.tiger.order.command.api.saga;

import java.util.UUID;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.modelling.saga.EndSaga;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.queryhandling.QueryGateway;
import org.axonframework.spring.stereotype.Saga;
import org.springframework.beans.factory.annotation.Autowired;

import com.tiger.order.command.api.dtos.events.OrderCreatedEvent;

import lombok.extern.slf4j.Slf4j;
import vn.tiger.sagacommon.commands.*;
import vn.tiger.sagacommon.constants.enums.OrderStatus;
import vn.tiger.sagacommon.events.*;
import vn.tiger.sagacommon.model.User;
import vn.tiger.sagacommon.queries.GetUserPaymentDetailsQuery;

@Saga
@Slf4j
public class OrderProcessingSaga {

    // transient need to use in @Saga for inject bean
    @Autowired
    private transient CommandGateway commandGateway;

    @Autowired
    private transient QueryGateway queryGateway;

    @StartSaga
    @SagaEventHandler(associationProperty = "orderId")
    private void handle(OrderCreatedEvent event) {
        log.info("OrderCreatedEvent in Saga for Order Id: {}", event.getOrderId());
        GetUserPaymentDetailsQuery getUserPaymentDetailsQuery = new GetUserPaymentDetailsQuery(event.getUserId());

        try {
            User user = queryGateway
                    .query(getUserPaymentDetailsQuery, ResponseTypes.instanceOf(User.class))
                    .join();

            ValidatePaymentCommand validatePaymentCommand = ValidatePaymentCommand.builder()
                    .cardDetails(user.getCardDetails())
                    .orderId(event.getOrderId())
                    .paymentId(UUID.randomUUID().toString())
                    .build();

            commandGateway.sendAndWait(validatePaymentCommand);
        } catch (Exception e) {
            log.error("Get user info error {}", e.getMessage(), e);
            // Start the Compensating transaction
            cancelOrderCommand(event.getOrderId());
        }
    }

    private void cancelOrderCommand(String orderId) {
        CancelOrderCommand cancelOrderCommand = new CancelOrderCommand(orderId);
        commandGateway.send(cancelOrderCommand);
    }

    @SagaEventHandler(associationProperty = "orderId")
    private void handle(PaymentProcessedEvent event) {
        log.info("PaymentProcessedEvent in saga for orderId {}", event.getOrderId());
        try {
            ShipOrderCommand shipOrderCommand = ShipOrderCommand.builder()
                    .shipmentId(UUID.randomUUID().toString())
                    .orderId(event.getOrderId())
                    .build();
            commandGateway.send(shipOrderCommand);
        } catch (Exception e) {
            log.error("ShipOrderCommand error {}", e.getMessage(), e);
            // Start the compensating transaction
            cancelPaymentCommand(event);
        }
    }

    private void cancelPaymentCommand(PaymentProcessedEvent event) {
        CancelPaymentCommand cancelPaymentCommand = new CancelPaymentCommand(event.getPaymentId(), event.getOrderId());
        commandGateway.send(cancelPaymentCommand);
    }

    @SagaEventHandler(associationProperty = "orderId")
    public void handler(OrderShippedEvent event) {
        log.info("OrderShippedEvent in saga for orderId {}", event.getOrderId());
        CompleteOrderCommand completeOrderCommand = CompleteOrderCommand.builder()
                .orderId(event.getOrderId())
                .orderStatus(OrderStatus.APPROVED.name())
                .build();

        commandGateway.send(completeOrderCommand);
    }

    @SagaEventHandler(associationProperty = "orderId")
    @EndSaga
    public void handle(OrderCompletedEvent event) {
        log.info("OrderCompletedEvent in Saga for Order Id : {}", event.getOrderId());
    }

    @SagaEventHandler(associationProperty = "orderId")
    @EndSaga
    public void handle(OrderCancelledEvent event) {
        log.info("OrderCancelledEvent in Saga for Order Id : {}", event.getOrderId());
    }

    @SagaEventHandler(associationProperty = "orderId")
    @EndSaga
    public void handle(PaymentCancelledEvent event) {
        log.info("PaymentCancelledEvent in Saga for Order Id : {}", event.getOrderId());
        cancelOrderCommand(event.getOrderId());
    }
}
