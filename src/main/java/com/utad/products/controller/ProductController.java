package com.utad.products.controller;

import com.utad.products.model.Product;
import com.utad.products.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/products")

public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAll() {
        List<Product> list = productService.getProducts();
        return ResponseEntity.ok(list);
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product createdProduct = productService.save(product);
        return ResponseEntity.ok(createdProduct);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product) {
        Product updatedProduct = productService.updateProduct(id, product);
        return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/sold-out")
    public ResponseEntity<List<Product>> getSoldOutProducts() {
        List<Product> soldOutProducts = productService.findSoldOutProducts();
        return new ResponseEntity<>(soldOutProducts, HttpStatus.OK);
    }

    @GetMapping("/{name}")
    public ResponseEntity<List<Product>> searchProductsByName(@PathVariable String name) {
        List<Product> products = productService.findProductsByName(name);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
}
