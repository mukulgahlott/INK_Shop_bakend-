package com.tattoo.inkshop.repository;

import com.tattoo.inkshop.model.VisitStats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VisitStatsRepository extends JpaRepository<VisitStats, Long> {
    Optional<VisitStats> findByShopId(Long shopId);
    
    @Query("SELECT v FROM VisitStats v ORDER BY v.totalLikes DESC")
    List<VisitStats> findMostLikedShops();
    
    @Query("SELECT v FROM VisitStats v ORDER BY v.totalVisits DESC")
    List<VisitStats> findMostVisitedShops();
} 