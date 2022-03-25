package com.example.demoapelsin.service;

import com.example.demoapelsin.entity.Categorys;
import com.example.demoapelsin.entity.Product;
import com.example.demoapelsin.payload.ApiResponse;
import com.example.demoapelsin.payload.ProductDto;
import com.example.demoapelsin.repository.CategoryRepository;
import com.example.demoapelsin.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    final ProductRepository productRepository;
    final CategoryRepository categoryRepository;

    public ApiResponse getAll() {
        List<Product> productList = productRepository.findAllByActiveTrue();
        return productList != null ? new ApiResponse(true, "success", productList)
                : new ApiResponse(false, "failed");
    }

    public ApiResponse getById(Integer id) {
        Optional<Product> productOptional = productRepository.findById(id);
        return productOptional.map(product -> new ApiResponse(true, "Success", product))
                .orElseGet(() -> new ApiResponse(false, "Not Found"));
    }

    public ApiResponse add(ProductDto productDto) {
        try {
            Product product = new Product();
            if (productDto.getId() != null) {
                product = productRepository.getById(productDto.getId());
            }
            Optional<Categorys> categorysOptional = categoryRepository.findById(productDto.getCategoryId());
            if (!categorysOptional.isPresent()) {
                return new ApiResponse(false, "Category not Found!");
            }
            product.setName(productDto.getName());
            product.setCategory(categorysOptional.get());
            product.setPrice(productDto.getPrice());
            product.setDescription(productDto.getDescription());
            productRepository.save(product);
            return new ApiResponse(true, productDto.getId() != null ? "Updated" : "Saved");
        } catch (Exception e) {
            return new ApiResponse(false, "There is the category with the same name");
        }
    }

    public ApiResponse update(Integer id, ProductDto productDto) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        Product product = optionalProduct.get();

        Optional<Categorys> optionalCategorys = categoryRepository.findById(id);
        Categorys categorys = optionalCategorys.get();

        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setDescription(product.getDescription());
        product.setCategory(categorys);

        productRepository.save(product);
        return new ApiResponse(true, "Updated");
    }

    public ApiResponse delete(Integer id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isEmpty()) return new ApiResponse(false, "Not Found");
        Product product = optionalProduct.get();
        product.setActive(false);
        productRepository.save(product);
        return new ApiResponse(true, "Deleted");
    }
}
