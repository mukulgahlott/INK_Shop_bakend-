package com.tattoo.inkshop.repository;

import com.tattoo.inkshop.model.Appointment;
import com.tattoo.inkshop.model.AppointmentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findByClientId(Long clientId);
    List<Appointment> findByShopId(Long shopId);
    List<Appointment> findByShopIdAndStatus(Long shopId, AppointmentStatus status);
    List<Appointment> findByShopIdAndDateTimeBetween(Long shopId, LocalDateTime start, LocalDateTime end);
} 