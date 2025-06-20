package com.ink.shop.service;

import com.ink.shop.model.TattooShop;
import com.ink.shop.model.VisitStats;
import com.ink.shop.repository.TattooShopRepository;
import com.ink.shop.repository.VisitStatsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ShopService {
    @Autowired
    private TattooShopRepository tattooShopRepository;
    @Autowired
    private VisitStatsRepository visitStatsRepository;

    public TattooShop createShop(TattooShop shop) {
        return tattooShopRepository.save(shop);
    }

    public Optional<TattooShop> getShopByOwnerId(Long ownerId) {
        List<TattooShop> shops = tattooShopRepository.findByOwnerId(ownerId);
        return shops.isEmpty() ? Optional.empty() : Optional.of(shops.get(0));
    }

    public TattooShop updateShop(TattooShop shop) {
        return tattooShopRepository.save(shop);
    }

    public TattooShop uploadImage(Long shopId, String imagePath) {
        Optional<TattooShop> shopOpt = tattooShopRepository.findById(shopId);
        if (shopOpt.isPresent()) {
            TattooShop shop = shopOpt.get();
            shop.getImages().add(imagePath);
            return tattooShopRepository.save(shop);
        }
        return null;
    }

    public Optional<VisitStats> getShopStats(Long shopId) {
        return visitStatsRepository.findById(shopId);
    }
} 