package com.example.Booking.Dao;

import com.example.Booking.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Bookings extends JpaRepository<Booking,Integer> {
}
