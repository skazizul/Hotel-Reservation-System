package HotelReservationSystem;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Reservation {
  private int reservationId;
  private Customer customer;
  private Room room;
  private LocalDate checkIn;
  private LocalDate checkOut;
  private ReservationStatus status;

  public Reservation(int reservationId,Customer customer,Room room,LocalDate checkIn,LocalDate checkOut){
    this.reservationId = reservationId;
    this.customer = customer;
    this.room = room;
    this.checkIn = checkIn;
    this.checkOut = checkOut;
    this.status = ReservationStatus.BOOKED;
  }

  public int getReservationId(){
    return reservationId;
  }

  public Customer getCustomer(){
    return customer;
  }
  
  public ReservationStatus getStatus(){
    return status;
  }

  public void setStatus(ReservationStatus status){
    this.status = status;
  }
  public Room getRoom(){
    return room;
  }

  public LocalDate getCheckIn(){
    return checkIn;
  }

  public LocalDate getCheOut(){
    return checkOut;
  }

  public int calculateDays(){
    int numberOfNights = (int) ChronoUnit.DAYS.between(checkIn,checkOut);
    return numberOfNights;
  }

  public double calculateBill(){
    int numberOfNights = Math.max(1, calculateDays());
    return numberOfNights * room.getPrice();
  }

  public String toString(){
    return String.format("Reservation Id: %d\nCustomer: %s\nRoom: %s\nCheckInDate: %s\nCheckOutDate: %s\n",reservationId,customer,room,checkIn,checkOut);
  }
}
