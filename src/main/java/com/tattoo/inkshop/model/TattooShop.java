package com.tattoo.inkshop.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "tattoo_shops")
public class TattooShop {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String shopName;

    @Column(length = 1000)
    private String bio;

    @NotBlank
    @Column(nullable = false)
    private String location;

    private String contactInfo;
    
    @ElementCollection
    @CollectionTable(name = "shop_images", joinColumns = @JoinColumn(name = "shop_id"))
    @Column(name = "image_url")
    private List<String> images = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "owner_id", nullable = false)
    private User owner;

    @OneToMany(mappedBy = "shop")
    private List<Appointment> appointments;

    @OneToMany(mappedBy = "shop")
    private List<Review> reviews;

    @OneToOne(mappedBy = "shop", cascade = CascadeType.ALL)
    private VisitStats visitStats;

    private double averageRating;
    private int totalReviews;
} 