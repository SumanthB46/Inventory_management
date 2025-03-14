package com.Inventory.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Inventory.entity.ProductEntity;
@Repository
public interface ProductRepo extends JpaRepository<ProductEntity, Long> {

	
	void deleteById(Long id);

	Optional<ProductEntity> findById(Long id);

}
