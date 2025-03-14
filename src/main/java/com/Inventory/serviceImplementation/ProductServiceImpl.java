package com.Inventory.serviceImplementation;
import org.springframework.stereotype.Service;
import com.Inventory.entity.ProductEntity;
import com.Inventory.repository.ProductRepo;
import com.Inventory.service.ProductService;	

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepo productRepository;

    public ProductServiceImpl(ProductRepo productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ProductEntity createProduct(ProductEntity product) {
        return productRepository.save(product);
    }

    @Override
    public List<ProductEntity> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public ProductEntity getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }

    @Override
    public ProductEntity updateProduct(Long id, ProductEntity updatedProduct) {
        ProductEntity existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        existingProduct.setName(updatedProduct.getName());
        existingProduct.setPrice(updatedProduct.getPrice());
        existingProduct.setQuantity(updatedProduct.getQuantity());
//      existingProduct.setImage(updatedProduct.getImage());
        existingProduct.setCategory(updatedProduct.getCategory());

        return productRepository.save(existingProduct);
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
