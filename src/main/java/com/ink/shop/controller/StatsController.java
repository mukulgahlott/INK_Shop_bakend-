package com.ink.shop.controller;

import com.ink.shop.model.TattooShop;
import com.ink.shop.service.StatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/stats")
public class StatsController {
    @Autowired
    private StatsService statsService;

    @GetMapping("/shops/top")
    public ResponseEntity<List<TattooShop>> getTopShops(@RequestParam(defaultValue = "5") int limit) {
        return ResponseEntity.ok(statsService.getTopShopsByLikesOrVisits(limit));
    }
} 