package com.tiger.order.command.api.repositories;

import com.tiger.order.command.api.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, UUID> {
}
