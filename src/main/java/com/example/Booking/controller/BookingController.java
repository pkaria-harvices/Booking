package com.example.Booking.controller;

import com.example.Booking.entity.Booking;
import com.example.Booking.model.BookingInfoEntity;
import com.example.Booking.model.GetTransactionEntity;
import com.example.Booking.services.BookingImpl;
import org.apache.http.protocol.HTTP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/hotel/booking/")
public class BookingController {

    @Autowired
    private BookingImpl booking;
    @PostMapping
    public ResponseEntity<Booking> bookRooms(@RequestBody BookingInfoEntity bookingInfoEntity){

        try{
            return new ResponseEntity(booking.bookRooms(bookingInfoEntity), HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity(e,HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping(value = "{bookingId}/transaction",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Booking> getTransaction(@PathVariable("bookingId") int bookingId,@RequestBody GetTransactionEntity getTransactionEntity){
    if(getTransactionEntity.getPaymentMode().toUpperCase().equals("UPI") || getTransactionEntity.getPaymentMode().toUpperCase().equals("CARD")){
        return new ResponseEntity(booking.confirmBooking(getTransactionEntity),HttpStatus.CREATED);
    }else{
        return new ResponseEntity("Invalid type of Payment",HttpStatus.BAD_REQUEST);
    }
    }
    @GetMapping(value ="{bookingId}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Booking> getBooking(@PathVariable("bookingId") int bookingId){
        Booking booking1=booking.getBooking(bookingId);
        if(booking1!=null){
            return new ResponseEntity(booking1,HttpStatus.OK);
        }else{
            return new ResponseEntity("Invalid Booking Id",HttpStatus.BAD_REQUEST);
        }
    }
    @PutMapping(value="update",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Booking> confirmPayment(@RequestBody Booking bookings){
        if(bookings!=null){
            return new ResponseEntity(booking.updateBooking(bookings),HttpStatus.ACCEPTED);
        }
        else{
            return new ResponseEntity("No value found",HttpStatus.BAD_REQUEST);
        }
    }
}
