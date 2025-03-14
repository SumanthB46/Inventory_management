	package com.Inventory.Controller;
	
	import com.Inventory.entity.ProductEntity;
	import com.Inventory.entity.CategoryEntity;
	import com.Inventory.service.ProductService;
	import com.Inventory.service.CategoryService;
	import org.springframework.stereotype.Controller;
	import org.springframework.ui.Model;
	import org.springframework.web.bind.annotation.*;
	
	import java.util.List;
	
	@Controller
	@RequestMapping("/products")
	public class ProductController {
	
	    private final ProductService productService;
	    private final CategoryService categoryService;
	
	    public ProductController(ProductService productService, CategoryService categoryService) {
	        this.productService = productService;
	        this.categoryService = categoryService;
	    }
	
	    // ✅ Show Product List
	    @GetMapping("")
	    public String listProducts(Model model) {
	        List<ProductEntity> products = productService.getAllProducts();
	        List<CategoryEntity> categories = categoryService.getAllCategories();
	
	        model.addAttribute("products", products);
	        model.addAttribute("product", new ProductEntity()); // ✅ Fix binding issues
	        model.addAttribute("categories", categories);
	        
	        return "Product-list"; 
	    }
	
	    // ✅ Show Product Form
	    @GetMapping("/product-form")
	    public String showProductForm(Model model) {
	        model.addAttribute("product", new ProductEntity());
	        model.addAttribute("categories", categoryService.getAllCategories());
	        return "product-form";
	    }
	
	    @PostMapping("/save")
	    public String saveProduct(@ModelAttribute ProductEntity product,
	                              @RequestParam(value = "categoryId", required = false) Long categoryId,
	                              Model model) {
	        if (categoryId == null) {
	            model.addAttribute("errorMessage", "Please select a category.");
	            model.addAttribute("product", product);
	            model.addAttribute("categories", categoryService.getAllCategories());
	            model.addAttribute("products", productService.getAllProducts());
	            return "Product-list"; // Stay on same page if error
	        }

	        CategoryEntity category = categoryService.getCategoryById(categoryId)
	            .orElseThrow(() -> new RuntimeException("Category not found with ID: " + categoryId));

	        product.setCategory(category);
	        productService.createProduct(product);

	        // ✅ Show Success Message
	        model.addAttribute("successMessage", "Product saved successfully!");
	        model.addAttribute("product", new ProductEntity()); // Clear form
	        model.addAttribute("categories", categoryService.getAllCategories());
	        model.addAttribute("products", productService.getAllProducts());

	        return "Product-list"; // ✅ Stay on same page
	    }


	
	    // ✅ Edit Product
	    @GetMapping("/edit/{id}")
	    public String editProduct(@PathVariable Long id, Model model) {
	        ProductEntity product = productService.getProductById(id);
	        List<CategoryEntity> categories = categoryService.getAllCategories();
	
	        model.addAttribute("product", product);
	        model.addAttribute("categories", categories);
	        return "product-form";
	    }
	
	    // ✅ Delete Product (With Confirmation)
	    @GetMapping("/delete/{id}")
	    public String deleteProduct(@PathVariable Long id) {
	        productService.deleteProduct(id);
	        return "redirect:/products";
	    }
	}
