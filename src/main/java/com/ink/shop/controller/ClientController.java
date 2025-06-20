package com.ink.shop.controller;

import com.ink.shop.model.TattooShop;
import com.ink.shop.model.Appointment;
import com.ink.shop.model.Review;
import com.ink.shop.service.ClientService;
import com.ink.shop.security.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/client")
public class ClientController {
    @Autowired
    private ClientService clientService;
    @Autowired
    private JwtUtil jwtUtil;

    @GetMapping("/shop/all")
    public ResponseEntity<List<TattooShop>> getAllShops() {
        return ResponseEntity.ok(clientService.getAllShops());
    }

    @GetMapping("/shop/search")
    public ResponseEntity<List<TattooShop>> searchShops(@RequestParam String location) {
        return ResponseEntity.ok(clientService.searchShopsByLocation(location));
    }

    @GetMapping("/shop/{id}")
    public ResponseEntity<?> getShopDetail(@PathVariable Long id) {
        Optional<TattooShop> shopOpt = clientService.getShopById(id);
        return shopOpt.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/appointment/book")
    public ResponseEntity<Appointment> bookAppointment(HttpServletRequest request, @RequestBody Appointment appointment) {
        Long clientId = (long) request.getAttribute("id");
        appointment.getClient().setId(clientId);
        return ResponseEntity.ok(clientService.bookAppointment(appointment));
    }

    @GetMapping("/appointment/my")
    public ResponseEntity<List<Appointment>> getMyAppointments(HttpServletRequest request) {
        Long clientId = (long) request.getAttribute("id");
        return ResponseEntity.ok(clientService.getAppointmentsByClientId(clientId));
    }

    @PostMapping("/review/add")
    public ResponseEntity<Review> addReview(HttpServletRequest request, @RequestBody Review review) {
        Long clientId = (long) request.getAttribute("id");
        review.getClient().setId(clientId);
        return ResponseEntity.ok(clientService.addReview(review));
    }
} 