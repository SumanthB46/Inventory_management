package com.Inventory.service;

import java.util.List;

import com.Inventory.entity.ProductEntity;

public interface ProductService {
    ProductEntity createProduct(ProductEntity product);
    List<ProductEntity> getAllProducts();
    ProductEntity getProductById(Long id);
    ProductEntity updateProduct(Long id, ProductEntity updatedProduct);
    void deleteProduct(Long id);
}
