package com.tattoo.inkshop.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "visit_stats")
public class VisitStats {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "shop_id", nullable = false)
    private TattooShop shop;

    @Column(nullable = false)
    private Long totalVisits = 0L;

    @Column(nullable = false)
    private Long totalLikes = 0L;

    public void incrementVisits() {
        this.totalVisits++;
    }

    public void incrementLikes() {
        this.totalLikes++;
    }

    public void decrementLikes() {
        if (this.totalLikes > 0) {
            this.totalLikes--;
        }
    }
} 