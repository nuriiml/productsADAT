package com.utad.products.service;

import com.utad.products.model.Product;
import com.utad.products.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Product not found with id: " + id));
    }

    public Product save(Product product){
        return productRepository.save(product);
    }

    public Product createCocktail(String name, String description, Double price, String category, Integer stock) {
        Product newProduct = new Product();
        newProduct.setName(name);
        newProduct.setDescription(description);
        newProduct.setPrice(price);
        newProduct.setCategory(category);
        newProduct.setStock(stock);
        return productRepository.save(newProduct);
    }
    public Product updateProduct(Long id, Product newProductData) {
        Product existingProduct = getProductById(id);
        existingProduct.setName(newProductData.getName());
        existingProduct.setDescription(newProductData.getDescription());
        existingProduct.setPrice(newProductData.getPrice());
        existingProduct.setCategory(newProductData.getCategory());
        existingProduct.setStock(newProductData.getStock());
        return productRepository.save(existingProduct);
    }

    public void deleteProduct(Long id) {
        Product product = getProductById(id);
        productRepository.delete(product);
    }

    public List<Product> findSoldOutProducts() {
        return productRepository.findByStock(0);
    }
    public List<Product> findProductsByName(String name) {
        return productRepository.findByNameContainingIgnoreCase(name);
    }

}
