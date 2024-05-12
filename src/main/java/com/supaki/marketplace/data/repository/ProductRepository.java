package com.supaki.marketplace.data.repository;

import com.supaki.marketplace.data.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT p FROM Product p WHERE p.quantity > 0")
    Page<Product> findAllProducts(Pageable pageRequest);

    Optional<Product> findByUserIdAndName(long userId, String name);

}
