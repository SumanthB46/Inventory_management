package com.Inventory.serviceImplementation;

import com.Inventory.entity.CategoryEntity;
import com.Inventory.repository.CategoryRepo;
import com.Inventory.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepo categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepo categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
    
    public void saveCategory(CategoryEntity category) {
        categoryRepository.save(category);
    }

//
//    @Override
//    public CategoryEntity saveCategory(CategoryEntity category) {
//        return categoryRepository.save(category); // ✅ Saves new or updated category
//    }

    @Override
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id); // ✅ Deletes category by ID
    }

    @Override
    public Optional<CategoryEntity> getCategoryById(Long id) {
        return categoryRepository.findById(id); // ✅ Returns Optional to prevent errors
    }

    @Override
    public List<CategoryEntity> getAllCategories() {
        return categoryRepository.findAll(); // ✅ Fetches all categories
    }
}
