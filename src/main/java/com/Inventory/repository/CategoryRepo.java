package com.Inventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Inventory.entity.CategoryEntity;
@Repository
public interface CategoryRepo  extends JpaRepository<CategoryEntity, Long> {

}
