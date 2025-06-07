package com.tattoo.inkshop.repository;

import com.tattoo.inkshop.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByShopId(Long shopId);
    List<Review> findByClientId(Long clientId);
    
    @Query("SELECT AVG(r.rating) FROM Review r WHERE r.shop.id = ?1")
    Double calculateAverageRating(Long shopId);
    
    @Query("SELECT COUNT(r) FROM Review r WHERE r.shop.id = ?1")
    Long countReviewsByShopId(Long shopId);
} 