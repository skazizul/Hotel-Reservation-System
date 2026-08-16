package HotelReservationSystem;

import java.time.LocalDate;
import java.time.DateTimeException;
import java.util.Scanner;

public class Main {
  private static LocalDate parseDateInput(String input) {
    String[] parts = input.trim().split("-");
    if (parts.length != 3) {
      throw new IllegalArgumentException("Invalid date format. Use YYYY-MM-DD or YYYY-M-D");
    }

    try {
      int year = Integer.parseInt(parts[0]);
      int month = Integer.parseInt(parts[1]);
      int day = Integer.parseInt(parts[2]);
      return LocalDate.of(year, month, day);
    } catch (NumberFormatException | DateTimeException e) {
      throw new IllegalArgumentException("Invalid date value. Use a real calendar date");
    }
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    Hotel hotel = new Hotel();
    // Load saved data when program starts
    hotel.loadCustomersFromFile();
    hotel.roomLoadFromFile();
    hotel.loadReservationsFromFile();
    while (true) {
      System.out.println("\n========== HOTEL RESERVATION SYSTEM ==========");
      System.out.println("1. Add Room");
      System.out.println("2. Add Customer");
      System.out.println("3. Display All Rooms");
      System.out.println("4. Display Available Rooms");
      System.out.println("5. Display All Customers");
      System.out.println("6. Book Room");
      System.out.println("7. Display All Reservations");
      System.out.println("8. Search Reservation");
      System.out.println("9. Cancel Reservation");
      System.out.println("10. Check-In");
      System.out.println("11. Check-Out");
      System.out.println("12. Calculate Reservation Bill");
      System.out.println("13. Save Data");
      System.out.println("0. Exit");
      System.out.println("==============================================");
      System.out.print("Enter Choice: ");
      int choice = sc.nextInt();
      try {
        switch (choice) {
          // =====================================
          // 1. ADD ROOM
          // =====================================
          case 1:
            System.out.print("Enter Room Number: ");
            int roomNo = sc.nextInt();
            System.out.println("Room Types:");
            System.out.println("1. SINGLE");
            System.out.println("2. DOUBLE");
            System.out.println("3. TWIN");
            System.out.println("4. QUEEN");
            System.out.println("5. DELUXE");
            System.out.print("Enter Room Type: ");
            int roomChoice = sc.nextInt();
            RoomType roomType;
            if (roomChoice == 1) {
              roomType = RoomType.SINGLE;
            } else if (roomChoice == 2) {
              roomType = RoomType.DOUBLE;
            } else if (roomChoice == 3) {
              roomType = RoomType.TWIN;
            } else if(roomChoice == 4){
              roomType = RoomType.QUEEN;
            }else if(roomChoice == 5){
              roomType = RoomType.DELUXE;
            }else{
              System.out.println("Invalid choice");
              break;
            }
            System.out.print("Enter Room Price: ");
            double price = sc.nextDouble();
            Room room = new Room(
                roomNo,
                roomType,
                price,
                true);
            hotel.addRoom(room);
            break;
          // =====================================
          // 2. ADD CUSTOMER
          // =====================================
          case 2:
            System.out.print("Enter Customer ID: ");
            int customerId = sc.nextInt();
            sc.nextLine();
            System.out.print("Enter Customer Name: ");
            String name = sc.nextLine();

            System.out.print("Enter Phone Number: ");
            String phoneNumber = sc.nextLine();

            System.out.print("Enter Email: ");
            String email = sc.nextLine();
            Customer customer = new Customer(
                customerId,
                name,
                phoneNumber,
                email);
            hotel.addCustomer(customer);
            break;
          // =====================================
          // 3. DISPLAY ALL ROOMS
          // =====================================
          case 3:
            hotel.displayAllRooms();
            break;
          // =====================================
          // 4. DISPLAY AVAILABLE ROOMS
          // =====================================
          case 4:
            hotel.displayAvailableRooms();
            break;
          // =====================================
          // 5. DISPLAY ALL CUSTOMERS
          // =====================================
          case 5:
            hotel.displayAllCustomer();;
            break;
          // =====================================
          // 6. BOOK ROOM
          // =====================================
          case 6:
            System.out.print("Enter Reservation ID: ");
            int reservationId = sc.nextInt();
            System.out.print("Enter Customer ID: ");
            int bookCustomerId = sc.nextInt();
            System.out.print("Enter Room Number: ");
            int bookRoomNo = sc.nextInt();
            System.out.print(
                "Enter Check-In Date (YYYY-MM-DD): ");
            String checkInInput = sc.next();
            LocalDate checkIn = parseDateInput(checkInInput);
            System.out.print(
                "Enter Check-Out Date (YYYY-MM-DD): ");
            String checkOutInput = sc.next();
            LocalDate checkOut = parseDateInput(checkOutInput);
            hotel.bookRoom(
                reservationId,
                bookCustomerId,
                bookRoomNo,
                checkIn,
                checkOut);
            break;
          // =====================================
          // 7. DISPLAY RESERVATIONS
          // =====================================
          case 7:
            hotel.displayAllReservation();;
            break;
          // =====================================
          // 8. SEARCH RESERVATION
          // =====================================
          case 8:
            System.out.print(
                "Enter Reservation ID: ");
            int searchReservationId = sc.nextInt();
            Reservation reservation = hotel.searchReservation(
                searchReservationId);
            System.out.println(reservation);
            break;
          // =====================================
          // 9. CANCEL RESERVATION
          // =====================================
          case 9:
            System.out.print(
                "Enter Reservation ID: ");
            int cancelReservationId = sc.nextInt();
            hotel.calcleReservation(cancelReservationId);
            break;
          // =====================================
          // 10. CHECK-IN
          // =====================================
          case 10:
            System.out.print(
                "Enter Reservation ID: ");
            int checkInReservationId = sc.nextInt();
            hotel.checkIn(
                checkInReservationId);
            break;
          // =====================================
          // 11. CHECK-OUT
          // =====================================
          case 11:
            System.out.print(
                "Enter Reservation ID: ");
            int checkOutReservationId = sc.nextInt();
            hotel.checkOut(
                checkOutReservationId);
            break;
          // =====================================
          // 12. CALCULATE BILL
          // =====================================
          case 12:
            System.out.print(
                "Enter Reservation ID: ");
            int billReservationId = sc.nextInt();
            double bill = hotel.calculateReservationBill(
                billReservationId);
            System.out.println(
                "Total Bill: ₹" + bill);
            break;
          // =====================================
          // 13. SAVE DATA
          // =====================================
          case 13:

            hotel.roomSaveToFile();
            hotel.saveCustomerToFile();
            hotel.saveReservationsToFile();
            System.out.println(
                "All data saved successfully.");
            break;
          // =====================================
          // 0. EXIT
          // =====================================
          case 0:
            // Save before exiting
            hotel.roomSaveToFile();
            hotel.saveCustomerToFile();
            hotel.saveReservationsToFile();
            System.out.println(
                "Data saved successfully.");
            System.out.println(
                "Thank you for using Hotel Reservation System.");
            sc.close();
            return;
          default:
            System.out.println(
                "Invalid choice. Please try again.");
        }
      } catch (Exception e) {
        System.out.println(
            "Error: " + e.getMessage());
      }
    }
  }
}