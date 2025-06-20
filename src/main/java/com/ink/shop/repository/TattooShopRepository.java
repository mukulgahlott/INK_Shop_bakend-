package com.ink.shop.repository;

import com.ink.shop.model.TattooShop;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TattooShopRepository extends JpaRepository<TattooShop, Long> {
    List<TattooShop> findByLocationContainingIgnoreCase(String location);
    List<TattooShop> findByOwnerId(Long ownerId);
} 