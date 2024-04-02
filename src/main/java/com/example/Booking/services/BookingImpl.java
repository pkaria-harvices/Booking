package com.example.Booking.services;

import com.example.Booking.Dao.Bookings;
import com.example.Booking.entity.Booking;
import com.example.Booking.model.BookingInfoEntity;
import com.example.Booking.model.GetTransactionEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Random;

@Service
public class BookingImpl {

    @Autowired
    private Bookings booking;



    public Booking confirmBooking(GetTransactionEntity getTransactionEntity){
        if(booking.findById(getTransactionEntity.getBookingId())==null){
            throw new RuntimeException("Please First Complete Booking Process");
        }
        RestTemplate restTemplate=new RestTemplate();
        int tid=restTemplate.getForObject("http://localhost:8091/payment/"+getTransactionEntity.getBookingId(),Integer.class);
        if(tid > 0){
            Optional<Booking> booking1= Optional.of(new Booking());
            booking1=booking.findById(getTransactionEntity.getBookingId());
            booking1.get().setTransactionId(tid);
           return updateBooking(booking1.get());
        }
        else {
            throw new RuntimeException("Not found Transaction Id");
        }
    }

    public Booking getBooking(int id){
        Optional<Booking> booking1=booking.findById(id);
        if(booking1.isPresent()){
            return booking1.get();
        }else {
            throw new RuntimeException();
        }
    }

    public Booking bookRooms(BookingInfoEntity bookingInfoEntity){
        if(bookingInfoEntity.getFromDate()==null || bookingInfoEntity.getToDate()==null){
            throw new RuntimeException("Please enter From date and To date");
        }
        int days = 0;
        Booking booking1=new Booking();
            LocalDate from=bookingInfoEntity.getFromDate();
            LocalDate to=bookingInfoEntity.getToDate();
            while(!from.isEqual(to)){
                days++;
                to=to.minusDays(1);
            }
            if(from==to){
                days = 1;
            }
            booking1.setAadharNumber(bookingInfoEntity.getAadharNumber());
            booking1.setNumOfRooms(bookingInfoEntity.getNumOfRooms());
            booking1.setRoomNumbers(getRandomNumbers(bookingInfoEntity.getNumOfRooms()));
            booking1.setBookedOn(LocalDateTime.now());
            booking1.setFromDate(bookingInfoEntity.getFromDate().atTime(00,00,00));
            booking1.setToDate(bookingInfoEntity.getToDate().atTime(00,00,00));
            booking1.setRoomPrice(1000*bookingInfoEntity.getNumOfRooms()*days);
            booking.save(booking1);
            return booking1;

    }
    public static ArrayList<String> getRandomNumbers(int count){
        Random rand = new Random();
        int upperBound = 100;
        ArrayList<String>numberList = new ArrayList<String>();

        for (int i=0; i<count; i++){
            numberList.add(String.valueOf(rand.nextInt(upperBound)));
        }

        return numberList;
    }

    public Booking updateBooking(Booking bookings) {
       return booking.save(bookings);
    }
}
