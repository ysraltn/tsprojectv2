package tech.ysraltn.tsprojectV2.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import tech.ysraltn.tsprojectV2.dto.ProductDto;
import tech.ysraltn.tsprojectV2.dto.ProductRequestDto;
import tech.ysraltn.tsprojectV2.model.Product;
import tech.ysraltn.tsprojectV2.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductDto>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }


    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable Long id) {
        return productService.getProductById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/serial/{serial}")
    public ResponseEntity<ProductDto> getProductBySerial(@PathVariable String serial) {
        return productService.getProductBySerial(serial)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/by-institution/{institutionId}")
    public ResponseEntity<List<ProductDto>> getProductsByInstitution(@PathVariable Long institutionId) {
        return ResponseEntity.ok(productService.getProductsByInstitution(institutionId));
    }


    @PostMapping
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductRequestDto request) {
        ProductDto created = productService.createProduct(request);
        return ResponseEntity.ok(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable Long id, @RequestBody ProductRequestDto request) {
        ProductDto updated = productService.updateProduct(id, request);
        return ResponseEntity.ok(updated);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
