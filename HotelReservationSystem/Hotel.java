package HotelReservationSystem;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;

public class Hotel {
  private HashMap<Integer, Room> rooms;
  private HashMap<Integer, Customer> customers;
  private HashMap<Integer, Reservation> reservationMap;

  public Hotel() {
    rooms = new HashMap<>();
    customers = new HashMap<>();
    reservationMap = new HashMap<>();
  }

  public void addRoom(Room room) {
    rooms.put(room.getRoomNo(), room);
    System.out.println("Room added successfully");
  }

  public Room searchRoom(int id) throws RoomNotFoundException {
    Room room = rooms.get(id);
    if (room == null) {
      throw new RoomNotFoundException("Room not found");
    }
    return room;
  }

  public void removeRoom(int id) throws RoomNotFoundException {
    Room room = rooms.get(id);
    if (room == null) {
      throw new RoomNotFoundException("Room not found");
    }
    rooms.remove(id);
  }

  public void displayAllRooms() {
    for (Room room : rooms.values()) {
      System.out.println(room);
    }
  }

  public void displayAvailableRooms() {
    for (Room room : rooms.values()) {
      if (room.isAvailable()) {
        System.out.println(room);
      }
    }
  }

  public void addCustomer(Customer customer) {
    customers.put(customer.getCustomerId(), customer);
    System.out.println("Customer add successfully");
  }

  public Customer searchCustomer(int id) throws CustomerNotFoundException {
    Customer customer = customers.get(id);
    if (customer == null) {
      throw new CustomerNotFoundException("Customer Not Found");
    }
    return customer;
  }

  public void removeCustomer(int id) throws CustomerNotFoundException {
    Customer customer = customers.get(id);
    if (customer == null) {
      throw new CustomerNotFoundException("Customer Not Found");
    }
    customers.remove(id);
  }

  public void displayAllCustomer() {
    for (Customer customer : customers.values()) {
      System.out.println(customer);
    }
  }

  public void bookRoom(int reservationId, int customerId, int roomNo, LocalDate checkIn, LocalDate checkOut)
      throws RoomNotFoundException, CustomerNotFoundException {
    Customer customer = customers.get(customerId);
    Room room = rooms.get(roomNo);
    if (!room.isAvailable()) {
      System.out.println("room " + roomNo + " is not available");
      return;
    }

    if (!checkOut.isAfter(checkIn)) {
      System.out.println("invalid check-in/check-out date");
      return;
    }

    Reservation reservation = new Reservation(reservationId, customer, room, checkIn, checkOut);
    reservationMap.put(reservationId, reservation);
    room.setAvailable(false);
    System.out.println("Room Booked Successfully");
    System.out.println("Reservation ID: " + reservationId);
  }

  public Reservation searchReservation(int reservationId) throws ReservationNotFound {
    Reservation reservation = reservationMap.get(reservationId);
    if (reservation == null) {
      throw new ReservationNotFound("Reservation not found");
    }

    return reservation;
  }

  public double calculateReservationBill(int reservationId) throws ReservationNotFound{
    Reservation reservation = searchReservation(reservationId);
    double bill = reservation.calculateBill();
    return bill;
  }

  public void calcleReservation(int reservationId) throws ReservationNotFound {
    Reservation reservation = reservationMap.get(reservationId);
    if (reservation == null) {
      throw new ReservationNotFound("Reservation not found");
    }

    reservationMap.remove(reservationId);
    System.out.println("Reservation Cancelled Successfully");
  }

  public void displayAllReservation() {
    for (Reservation reservation : reservationMap.values()) {
      System.out.println(reservation);
    }
  }

  public void checkIn(int reservationId) throws ReservationNotFound {
    Reservation reservation = searchReservation(reservationId);
    if (reservation.getStatus() == ReservationStatus.CANCLED) {
      System.out.println("Reservation Cancled");
      return;
    }

    if (reservation.getStatus() == ReservationStatus.CHECK_IN) {
      System.out.println("Customer already check in");
      return;
    }

    if (reservation.getStatus() == ReservationStatus.CHECK_OUT) {
      System.out.println("Customer already checked in");
      return;
    }

    reservation.setStatus(ReservationStatus.CHECK_IN);
    System.out.println("Checked in successfull");
    System.out.println("Reservation ID: " + reservationId);
  }

  public void checkOut(int reservationId) throws ReservationNotFound {
    Reservation reservation = searchReservation(reservationId);
    if (reservation.getStatus() == ReservationStatus.CANCLED) {
      System.out.println("Reservation cancled");
      return;
    }

    if (reservation.getStatus() == ReservationStatus.BOOKED) {
      System.out.println("Customer has not checked");
      return;
    }

    if (reservation.getStatus() == ReservationStatus.CHECK_OUT) {
      System.out.println("Customer already checked-out");
      return;
    }
    Room room = reservation.getRoom();
    room.setAvailable(true);
    double bill = reservation.calculateBill();
    reservation.setStatus(ReservationStatus.CHECK_OUT);
    System.out.println("Customer check out successfull");
    System.out.println("Total Bill: " + bill);
  }

  public void roomSaveToFile() {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter("HotelReservationSystem/Room.txt"))) {
      for (Room room : rooms.values()) {
        writer.write(room.getRoomNo() + "," + room.getRoomType() + "," + room.getPrice() + "," + room.isAvailable());
        writer.newLine();
      }
      System.out.println("Room saved successfully");
    } catch (IOException e) {
      System.out.println(e.getMessage());
    }
  }

  public void roomLoadFromFile() {
    try {
      rooms.clear();
      BufferedReader reader = new BufferedReader(new FileReader("HotelReservationSystem/Room.txt"));
      String line;
      while ((line = reader.readLine()) != null) {
        String[] data = line.split(",");
        int roomNo = Integer.parseInt(data[0]);
        RoomType roomtype = RoomType.valueOf(data[1]);
        double price = Double.parseDouble(data[2]);
        boolean available = Boolean.parseBoolean(data[3]);

        Room room = new Room(roomNo, roomtype, price, available);
        rooms.put(room.getRoomNo(), room);
      }
      reader.close();
      System.out.println("Rooms loaded successfully");
    } catch (IOException e) {
      System.out.println(e.getMessage());
    }
  }

  public void saveCustomerToFile() {
    try {
      BufferedWriter writer = new BufferedWriter(new FileWriter("HotelReservationSystem/Customer.txt"));
      for (Customer customer : customers.values()) {
        writer.write(customer.getCustomerId() + "," + customer.getName() + "," + customer.getPhoneNumber() + ","
            + customer.getEmail());
        writer.newLine();
      }
      writer.close();
      System.out.println("File Save Successfully");
    } catch (IOException e) {
      System.out.println(e.getMessage());
    }
  }

  public void loadCustomersFromFile() {

    try {
      customers.clear();
      BufferedReader reader = new BufferedReader(new FileReader("HotelReservationSystem/Customer.txt"));
      String line;
      while ((line = reader.readLine()) != null) {
        String[] data = line.split(",");
        int id = Integer.parseInt(data[0]);
        String name = data[1];
        String phoneNumber = data[2];
        String email = data[3];
        Customer customer = new Customer(
            id,
            name,
            phoneNumber,
            email);

        customers.put(id, customer);
      }
      reader.close();
      System.out.println("Customers loaded successfully.");
    } catch (IOException e) {
      System.out.println("Error loading customers: " + e.getMessage());
    }
  }

  public void saveReservationsToFile() {
    try {
      BufferedWriter writer = new BufferedWriter(new FileWriter("HotelReservationSystem/Reservation.txt"));
      for (Reservation reservation : reservationMap.values()) {
        writer.write(
            reservation.getReservationId() + "," +
                reservation.getCustomer().getCustomerId() + "," +
                reservation.getRoom().getRoomNo() + "," +
                reservation.getCheckIn() + "," +
                reservation.getCheOut() + "," +
                reservation.getStatus());

        writer.newLine();
      }
      writer.close();
      System.out.println("Reservations saved successfully.");

    } catch (IOException e) {

      System.out.println(
          "Error saving reservations: "
              + e.getMessage());
    }
  }

  public void loadReservationsFromFile() {
    try{
      reservationMap.clear();
      BufferedReader reader = new BufferedReader(new FileReader("HotelReservationSystem/Reservation.txt"));
      String line;
      while ((line = reader.readLine()) != null) {
        String[] data = line.split(",");
        int reservationId = Integer.parseInt(data[0]);
        int customerId = Integer.parseInt(data[1]);
        int roomNo = Integer.parseInt(data[2]);
        LocalDate checkIn = LocalDate.parse(data[3]);
        LocalDate checkOut = LocalDate.parse(data[4]);
        ReservationStatus status = ReservationStatus.valueOf(data[5]);
        Customer customer = searchCustomer(customerId);
        Room room = searchRoom(roomNo);
        Reservation reservation = new Reservation(
            reservationId,
            customer,
            room,
            checkIn,
            checkOut);
        reservation.setStatus(status);
        reservationMap.put(
            reservationId,
            reservation);
      }
      reader.close();
      System.out.println("Reservations loaded successfully.");

    } catch (IOException | CustomerNotFoundException | RoomNotFoundException e) {

      System.out.println(
          "Error loading reservations: "
              + e.getMessage());
    }
  }
}
