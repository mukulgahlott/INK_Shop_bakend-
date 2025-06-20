package com.ink.shop.service;

import com.ink.shop.model.VisitStats;
import com.ink.shop.model.TattooShop;
import com.ink.shop.repository.VisitStatsRepository;
import com.ink.shop.repository.TattooShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StatsService {
    @Autowired
    private VisitStatsRepository visitStatsRepository;
    @Autowired
    private TattooShopRepository tattooShopRepository;

    public List<TattooShop> getTopShopsByLikesOrVisits(int limit) {
        List<VisitStats> stats = visitStatsRepository.findAll();
        List<Long> topShopIds = stats.stream()
                .sorted((a, b) -> Integer.compare((b.getTotalLikes() + b.getTotalVisits()), (a.getTotalLikes() + a.getTotalVisits())))
                .limit(limit)
                .map(VisitStats::getShopId)
                .collect(Collectors.toList());
        return tattooShopRepository.findAllById(topShopIds);
    }
} 