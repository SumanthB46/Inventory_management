package com.Inventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Inventory.entity.OrdersEntity;

@Repository
public interface OrdersRepo extends JpaRepository<OrdersEntity, Integer>{

}
