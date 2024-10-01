package com.tiger.order.command.api.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {
    private String productId;
    private String userId;
    private String addressId;
    private Integer quantity;
}
