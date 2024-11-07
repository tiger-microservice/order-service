package com.tiger.order.command.api.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tiger.order.command.api.entities.Order;

public interface OrderRepository extends JpaRepository<Order, UUID> {}
