package com.Inventory.service;

import com.Inventory.entity.OrdersEntity;
import java.util.List;
import java.util.Optional;

public interface OrderService {
    List<OrdersEntity> getAllOrders();
    Optional<OrdersEntity> getOrderById(Integer id);
    OrdersEntity placeOrder(OrdersEntity order);
    OrdersEntity updateOrder(Integer id, OrdersEntity order);
    void deleteOrder(Integer id);
}
