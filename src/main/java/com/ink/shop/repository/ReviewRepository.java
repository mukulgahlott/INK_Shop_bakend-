package com.ink.shop.repository;

import com.ink.shop.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByShopId(Long shopId);
    List<Review> findByClientId(Long clientId);
} 