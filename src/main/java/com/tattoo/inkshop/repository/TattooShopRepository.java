package com.tattoo.inkshop.repository;

import com.tattoo.inkshop.model.TattooShop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TattooShopRepository extends JpaRepository<TattooShop, Long> {
    Optional<TattooShop> findByOwnerId(Long ownerId);
    List<TattooShop> findByLocationContainingIgnoreCase(String location);
    
    @Query("SELECT t FROM TattooShop t ORDER BY t.averageRating DESC")
    List<TattooShop> findTopRatedShops();
    
    @Query("SELECT t FROM TattooShop t JOIN t.visitStats v ORDER BY v.totalVisits DESC")
    List<TattooShop> findMostVisitedShops();
} 