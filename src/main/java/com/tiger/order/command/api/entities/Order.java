package com.tiger.order.command.api.entities;

import com.tiger.cores.entities.SoftDelEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import java.util.UUID;

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
