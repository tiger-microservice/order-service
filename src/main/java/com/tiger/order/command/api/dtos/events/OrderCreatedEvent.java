package com.tiger.order.command.api.dtos.events;

import lombok.Data;

@Data
public class OrderCreatedEvent {
    private String orderId;
    private String productId;
    private String userId;
    private String addressId;
    private Integer quantity;
    private String orderStatus;

    public OrderCreatedEvent() {}

    public OrderCreatedEvent(
            String orderId, String productId, String userId, String addressId, Integer quantity, String orderStatus) {
        this.orderId = orderId;
        this.productId = productId;
        this.userId = userId;
        this.addressId = addressId;
        this.quantity = quantity;
        this.orderStatus = orderStatus;
    }
}
