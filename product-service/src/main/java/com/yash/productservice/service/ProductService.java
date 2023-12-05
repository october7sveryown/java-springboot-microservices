package com.yash.productservice.service;

import com.yash.productservice.dto.ProductRequestDTO;
import com.yash.productservice.dto.ProductResponse;
import com.yash.productservice.model.Product;
import com.yash.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;

        public void createProduct(ProductRequestDTO requestDTO){
        Product product = Product.builder().
                name(requestDTO.getName()).description(requestDTO.getDescription()).price(requestDTO.getPrice()).build();
        productRepository.save(product);
        log.info("product {} saved", product.getId());
    }

    public List<ProductResponse> getAllProducts() {
            List<Product> myProducts = productRepository.findAll();
        return myProducts.stream().map(product -> mapToResponse(product)).toList();
    }

    private ProductResponse mapToResponse(Product product) {
        return ProductResponse.builder().id(product.getId()).name(product.getName()).description(product.getDescription())
                    .price(product.getPrice()).build();
    }
}
