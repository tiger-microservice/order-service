package com.tiger.order.command.api.entities;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import org.hibernate.annotations.SQLDelete;

import com.tiger.cores.entities.SoftDelEntity;

import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "orders")
@SQLDelete(sql = "UPDATE orders set is_deleted = true where id = ?")
public class Order extends SoftDelEntity {
    @Id
    //    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "order_id")
    UUID orderId;

    String productId;
    String userId;
    String addressId;
    Integer quantity;
    String orderStatus;
}
