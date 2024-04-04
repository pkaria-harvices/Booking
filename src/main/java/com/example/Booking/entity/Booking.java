package com.example.Booking.entity;


import javax.annotation.Nonnull;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bookingId;
    private LocalDateTime fromDate;
    private LocalDateTime toDate;
    private String aadharNumber;
    private int numOfRooms;
    private String roomNumbers;
    @Column(nullable = false)
    private int roomPrice;
    @Column(columnDefinition = "Decimal(30) default '0'")
    private int transactionId;
    private LocalDateTime bookedOn;

    public Booking() {
    }

    @Override
    public String toString() {
        return "Booking{" +
                "bookingId=" + bookingId +
                ", fromDate=" + fromDate +
                ", toDate=" + toDate +
                ", aadharNumber='" + aadharNumber + '\'' +
                ", numOfRooms=" + numOfRooms +
                ", roomNumbers=" + roomNumbers +
                ", roomPrice=" + roomPrice +
                ", transactionId=" + transactionId +
                ", bookedOn=" + bookedOn +
                '}';
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public LocalDateTime getFromDate() {
        return fromDate;
    }

    public void setFromDate(LocalDateTime fromDate) {
        this.fromDate = fromDate;
    }

    public LocalDateTime getToDate() {
        return toDate;
    }

    public void setToDate(LocalDateTime toDate) {
        this.toDate = toDate;
    }

    public String getAadharNumber() {
        return aadharNumber;
    }

    public void setAadharNumber(String aadharNumber) {
        this.aadharNumber = aadharNumber;
    }

    public int getNumOfRooms() {
        return numOfRooms;
    }

    public void setNumOfRooms(int numOfRooms) {
        this.numOfRooms = numOfRooms;
    }

    public List<String> getRoomNumbers() {
        List<String> roomNumber=new ArrayList<>();
        String[] rooms = roomNumbers.split(",");
        for(String room:rooms){
            roomNumber.add(room);
        }

          return roomNumber;
    }

    public void setRoomNumbers(List<String> roomNumbers) {
        String roomNumber="";
            for(String room:roomNumbers){
                roomNumber += room+",";
            }
            this.roomNumbers = roomNumber.substring(0,roomNumber.length()-2);
    }

    public int getRoomPrice() {
        return roomPrice;
    }

    public void setRoomPrice(int roomPrice) {
        this.roomPrice = roomPrice;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public LocalDateTime getBookedOn() {
        return bookedOn;
    }

    public void setBookedOn(LocalDateTime bookedOn) {
        this.bookedOn = bookedOn;
    }

    public Booking(int bookingId, LocalDateTime fromDate, LocalDateTime toDate, String aadharNumber, int numOfRooms, List<String> roomNumbers, int roomPrice, int transactionId, LocalDateTime bookedOn) {
        this.bookingId = bookingId;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.aadharNumber = aadharNumber;
        this.numOfRooms = numOfRooms;
        String roomNumber="";
        for(String room:roomNumbers){
            roomNumber += room+",";
        }
        this.roomNumbers = roomNumber.substring(0,roomNumber.length()-2);
        this.roomPrice = roomPrice;
        this.transactionId = transactionId;
        this.bookedOn = bookedOn;
    }
}
