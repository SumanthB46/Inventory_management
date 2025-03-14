package com.Inventory.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Inventory.entity.SupplierEntity;

public interface SupplierRepo extends JpaRepository<SupplierEntity, Integer>{

	void deleteById(Long id);

	Optional<SupplierEntity> findById(Long id);

}
