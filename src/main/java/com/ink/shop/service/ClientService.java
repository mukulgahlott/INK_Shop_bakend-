package com.ink.shop.service;

import com.ink.shop.model.TattooShop;
import com.ink.shop.model.Appointment;
import com.ink.shop.model.Review;
import com.ink.shop.repository.TattooShopRepository;
import com.ink.shop.repository.AppointmentRepository;
import com.ink.shop.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ClientService {
    @Autowired
    private TattooShopRepository tattooShopRepository;
    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private ReviewRepository reviewRepository;

    public List<TattooShop> getAllShops() {
        return tattooShopRepository.findAll();
    }

    public List<TattooShop> searchShopsByLocation(String location) {
        return tattooShopRepository.findByLocationContainingIgnoreCase(location);
    }

    public Optional<TattooShop> getShopById(Long id) {
        return tattooShopRepository.findById(id);
    }

    public Appointment bookAppointment(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    public List<Appointment> getAppointmentsByClientId(Long clientId) {
        return appointmentRepository.findByClientId(clientId);
    }

    public Review addReview(Review review) {
        return reviewRepository.save(review);
    }

    public List<Review> getReviewsByShopId(Long shopId) {
        return reviewRepository.findByShopId(shopId);
    }
} 