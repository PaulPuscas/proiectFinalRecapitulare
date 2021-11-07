package com.sda.proiectFinalRecapitulare.service;

import com.sda.proiectFinalRecapitulare.dto.ProductDto;
import com.sda.proiectFinalRecapitulare.model.Category;
import com.sda.proiectFinalRecapitulare.model.Product;
import com.sda.proiectFinalRecapitulare.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public void add(ProductDto productDto) {
        Product product = new Product();
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setCategory(Category.valueOf(productDto.getCategory()));
        product.setStartingPrice(Integer.valueOf(productDto.getStartBiddingPrice()));
        product.setEndDateTime(LocalDateTime.parse(productDto.getEndDateTime()));

        productRepository.save(product);
    }

    public List<ProductDto> getAllProductDtos() {

        List<Product> products = productRepository.findAll();
        List<ProductDto> result = new ArrayList<>();
        for (Product product : products) {
            ProductDto productDto = new ProductDto();
            productDto.setName(product.getName());
            productDto.setDescription(product.getDescription());
            productDto.setCategory(product.getCategory().name());
            productDto.setStartBiddingPrice(product.getStartingPrice().toString());
            productDto.setEndDateTime(product.getEndDateTime().toString());
            result.add(productDto);
        }
        return result;
    }
}
