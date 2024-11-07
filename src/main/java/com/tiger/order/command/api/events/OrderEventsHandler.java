package com.tiger.order.command.api.events;

import java.util.UUID;

import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.tiger.order.command.api.dtos.events.OrderCreatedEvent;
import com.tiger.order.command.api.entities.Order;
import com.tiger.order.command.api.repositories.OrderRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import vn.tiger.sagacommon.events.OrderCompletedEvent;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderEventsHandler {

    private final OrderRepository orderRepository;

    @EventHandler
    public void on(OrderCreatedEvent event) {
        log.info("[OrderCreatedEvent] orderId {}", event.getOrderId());
        Order order = Order.builder().build();
        BeanUtils.copyProperties(event, order);
        order.setOrderId(UUID.fromString(event.getOrderId()));
        orderRepository.save(order);
    }

    @EventHandler
    public void on(OrderCompletedEvent event) {
        Order order =
                orderRepository.findById(UUID.fromString(event.getOrderId())).get();
        order.setOrderStatus(event.getOrderStatus());
        orderRepository.save(order);
    }
}
