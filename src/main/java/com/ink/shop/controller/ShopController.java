package com.ink.shop.controller;

import com.ink.shop.model.TattooShop;
import com.ink.shop.model.VisitStats;
import com.ink.shop.service.ShopService;
import com.ink.shop.security.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.Optional;

@RestController
@RequestMapping("/shop")
public class ShopController {
    @Autowired
    private ShopService shopService;
    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/create")
    public ResponseEntity<?> createShop(HttpServletRequest request, @RequestBody TattooShop shop) {
        Long ownerId = (long) request.getAttribute("id");
        shop.getOwner().setId(ownerId);
        TattooShop created = shopService.createShop(shop);
        return ResponseEntity.ok(created);
    }

    @GetMapping("/my")
    public ResponseEntity<?> getMyShop(HttpServletRequest request) {
        Long ownerId = (long) request.getAttribute("id");
        Optional<TattooShop> shopOpt = shopService.getShopByOwnerId(ownerId);
        return shopOpt.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateShop(@PathVariable Long id, @RequestBody TattooShop shop) {
        shop.setId(id);
        TattooShop updated = shopService.updateShop(shop);
        return ResponseEntity.ok(updated);
    }

    @PostMapping("/uploadImage")
    public ResponseEntity<?> uploadImage(@RequestParam Long shopId, @RequestParam MultipartFile image) {
        // For simplicity, just use the original filename as the image path
        TattooShop updated = shopService.uploadImage(shopId, image.getOriginalFilename());
        return ResponseEntity.ok(updated);
    }

    @GetMapping("/stats/{id}")
    public ResponseEntity<?> getShopStats(@PathVariable Long id) {
        Optional<VisitStats> statsOpt = shopService.getShopStats(id);
        return statsOpt.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
} 