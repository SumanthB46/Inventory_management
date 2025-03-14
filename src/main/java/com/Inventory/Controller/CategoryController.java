package com.Inventory.Controller;

import com.Inventory.entity.CategoryEntity;
import com.Inventory.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    // ✅ Display all categories
    @GetMapping
    public String listCategories(Model model) {
        List<CategoryEntity> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);
        model.addAttribute("categoryForm", new CategoryEntity()); // Empty form model
        return "category"; // Ensure category.html exists in /templates/
    }

    @PostMapping("/save")
    public String saveCategory(@RequestParam(value = "categoryId", required = false) Long categoryId, 
                               @ModelAttribute("categoryForm") CategoryEntity category) {
        if (categoryId != null) {
            category.setId(categoryId); // ✅ Set ID if updating an existing category
        }
        categoryService.saveCategory(category);
        return "redirect:/categories";
    }


    // ✅ Edit Category
    @GetMapping("/edit/{id}")
    public String editCategory(@PathVariable Long id, Model model) {
        Optional<CategoryEntity> categoryOptional = categoryService.getCategoryById(id);
        if (categoryOptional.isPresent()) {
            model.addAttribute("categoryForm", categoryOptional.get());
            return "category"; // ✅ Ensure category.html contains form with hidden ID
        }
        return "redirect:/categories"; // Redirect if category not found
    }

    // ✅ Delete Category
    @GetMapping("/delete/{id}")
    public String deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return "redirect:/categories";
    }
}
