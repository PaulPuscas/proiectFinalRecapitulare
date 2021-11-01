package com.sda.proiectFinalRecapitulare.repository;

import com.sda.proiectFinalRecapitulare.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
