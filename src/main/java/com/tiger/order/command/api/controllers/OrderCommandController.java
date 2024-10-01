package com.tiger.order.command.api.controllers;

import com.tiger.order.command.api.commands.CreateOrderCommand;
import com.tiger.order.command.api.dtos.request.OrderRequest;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.tiger.sagacommon.constants.enums.OrderStatus;

import java.util.UUID;

@RestController
@RequestMapping("/orders")
public class OrderCommandController {

    @Autowired
    private CommandGateway commandGateway;

    @PostMapping
    public String createOrder(@RequestBody OrderRequest request) {
        String orderId = UUID.randomUUID().toString();

        CreateOrderCommand createOrderCommand = CreateOrderCommand.builder()
                .orderId(orderId)
                .userId(request.getUserId())
                .addressId(request.getAddressId())
                .productId(request.getProductId())
                .quantity(request.getQuantity())
                .orderStatus(OrderStatus.CREATED.name())
                .build();

        commandGateway.sendAndWait(createOrderCommand);

        return "Order created " + orderId;
    }
}
