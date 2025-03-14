package com.Inventory.service;

import com.Inventory.entity.CategoryEntity;
import java.util.List;
import java.util.Optional;

public interface CategoryService {
   
    void deleteCategory(Long id);
    Optional<CategoryEntity> getCategoryById(Long id); // ✅ Return Optional for safety
    List<CategoryEntity> getAllCategories();
    void saveCategory(CategoryEntity category);
}



//
//import java.util.List;
//import java.util.Optional;
//
//import com.Inventory.entity.CategoryEntity;
//
//public interface CategoryService 
//	{
//	// Retrieve all categories
//    List<CategoryEntity> getAllCategories();
//    
//    // Retrieve a category by ID
//    Optional<CategoryEntity> getCategoryById(Long id);
//    
//    // Add a new category
//    CategoryEntity addCategory(CategoryEntity category);
//    
//    // Update an existing category
//    CategoryEntity updateCategory(Long id, CategoryEntity category);
//    
//    // Delete a category
//    boolean deleteCategory(Long id);
//}