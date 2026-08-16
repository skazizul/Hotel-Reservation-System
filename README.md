# 🏨 Hotel Reservation System
A Java console-based **Hotel Reservation System** developed using Core Java and Object-Oriented Programming concepts.
This project simulates a basic hotel reservation workflow including room management, customer management, room booking, cancellation, check-in, check-out, billing, and file-based data persistence.
---
## 🎥 Project Demo
[![Hotel Reservation System Demo]([https://img.youtube.com/vi/YOUR_VIDEO_ID/0.jpg)](https://www.youtube.com/watch?v=YOUR_VIDEO_ID](https://youtu.be/5XuVM1uTKu8))
## 🚀 Features

- Add Hotel Rooms
- Add Customers
- Search Rooms
- Display All Rooms
- Display Available Rooms
- Display All Customers
- Book a Room
- Search Reservation
- Cancel Reservation
- Customer Check-In
- Customer Check-Out
- Calculate Reservation Bill
- Save Data to Files
- Load Data from Files
- Automatic Data Saving Before Exit

---
## 🛠️ Technologies Used

- Java
- Object-Oriented Programming (OOP)
- Encapsulation
- Association
- Composition
- Enums
- Collections Framework
- HashMap
- Exception Handling
- Custom Exceptions
- File Handling
- BufferedReader
- BufferedWriter
- FileReader
- FileWriter
- LocalDate
- ChronoUnit

---

## 🧠 OOP Concepts Demonstrated

### 1. Encapsulation

Classes use private fields with public methods to control access to object data.

Example:

```java
private int roomNo;

public int getRoomNo() {
    return roomNo;
}
```

---

### 2. Association

A `Reservation` is associated with both a `Customer` and a `Room`.

```java
private Customer customer;
private Room room;
```

---

### 3. Composition

The `Reservation` object contains references to the related `Customer` and `Room` objects.

---

### 4. Enum

Room types and reservation statuses are represented using enums.

```java
RoomType
ReservationStatus
```

---

### 5. Polymorphism

Objects are handled through their appropriate class/interface types where applicable.

---

## 📂 Project Structure

```text
   └── HotelReservationSystem/
       │
       ├── Main.java
       ├── Hotel.java
       ├── Room.java
       ├── Customer.java
       ├── Reservation.java
       │
       ├── RoomType.java
       ├── ReservationStatus.java
       │
       ├── RoomNotFoundException.java
       ├── CustomerNotFoundException.java
       └── ReservationNotFoundException.java
       ├── rooms.txt
       ├── customers.txt
       ├── reservations.txt
       ├── .gitignore
       └── README.md
```

---

## 🏗️ Class Responsibilities

### `Main.java`

Responsible for:

- Console menu
- User input
- Calling Hotel methods
- Running the application

---

### `Hotel.java`

Acts as the main management class.

Responsible for:

- Room management
- Customer management
- Reservation management
- Booking
- Cancellation
- Check-in
- Check-out
- Billing
- File saving/loading

---

### `Room.java`

Represents a hotel room.

Contains:

- Room number
- Room type
- Room price
- Availability status

---

### `Customer.java`

Represents a hotel customer.

Contains:

- Customer ID
- Name
- Phone number
- Email

---

### `Reservation.java`

Represents a hotel reservation.

Contains:

- Reservation ID
- Customer
- Room
- Check-in date
- Check-out date
- Reservation status

Also handles:

- Number of nights calculation
- Bill calculation

---

### `RoomType.java`

Defines available room types.

Example:

```text
SINGLE
DOUBLE
TWIN
QUEEN
DELUXE
```

---

### `ReservationStatus.java`

Defines reservation states.

```text
BOOKED
CHECKED_IN
CHECKED_OUT
CANCELLED
```

---

## 🔄 Reservation Lifecycle

```text
BOOKED
   │
   │ Check-In
   ▼
CHECKED_IN
   │
   │ Check-Out
   ▼
CHECKED_OUT
```

A booking can also be cancelled:

```text
BOOKED
   │
   │ Cancel
   ▼
CANCELLED
```

---

## 🗃️ Data Persistence

The application stores data in text files.

### `rooms.txt`

Stores room information.

```text
roomNo,roomType,price,available
```

Example:

```text
101,STANDARD,2000.0,true
102,DELUXE,3000.0,false
```

---

### `customers.txt`

Stores customer information.

```text
customerId,name,phoneNumber,email
```

---

### `reservations.txt`

Stores reservation information.

```text
reservationId,customerId,roomNo,checkIn,checkOut,status
```

---

## ⚡ How the System Works

### Application Startup

```text
Start Application
       ↓
Load Customers
       ↓
Load Rooms
       ↓
Load Reservations
       ↓
Display Menu
```

---

### Room Booking

```text
Customer ID
      ↓
Find Customer
      ↓
Find Room
      ↓
Check Availability
      ↓
Validate Dates
      ↓
Create Reservation
      ↓
Save Reservation
      ↓
Set Room Unavailable
```

---

### Cancellation

```text
Find Reservation
      ↓
Get Room
      ↓
Make Room Available
      ↓
Remove Reservation
```

---

### Check-In

```text
Find Reservation
      ↓
Validate Reservation Status
      ↓
BOOKED → CHECKED_IN
```

---

### Check-Out

```text
Find Reservation
      ↓
Validate Status
      ↓
Calculate Bill
      ↓
Make Room Available
      ↓
CHECKED_IN → CHECKED_OUT
```

---

## 💻 Sample Menu

```text
========== HOTEL RESERVATION SYSTEM ==========
1. Add Room
2. Add Customer
3. Display All Rooms
4. Display Available Rooms
5. Display All Customers
6. Book Room
7. Display All Reservations
8. Search Reservation
9. Cancel Reservation
10. Check-In
11. Check-Out
12. Calculate Reservation Bill
13. Save Data
0. Exit
==============================================
```

---

## ▶️ How to Run

### Prerequisites

Install:

- Java JDK 8 or higher
- Any Java IDE or terminal

Check Java installation:

```bash
java -version
```

Check compiler:

```bash
javac -version
```

---

## ▶️ Run Using IDE

Open the project in:

- IntelliJ IDEA
- Eclipse
- VS Code
- NetBeans

Run:

```text
Main.java
```

---

## ▶️ Run Using Terminal

Navigate to the source directory:

```bash
cd src
```

Compile:

```bash
javac HotelReservationSystem/*.java
```

Run:

```bash
java HotelReservationSystem.Main
```

---

## 📌 Example Workflow

```text
1. Add Room
2. Add Customer
3. Book Room
4. Check-In
5. Calculate Bill
6. Check-Out
7. Save Data
0. Exit
```

Example:

```text
Room Number: 101
Room Type: DELUXE
Room Price: ₹3000

Customer ID: 1
Name: Aziz

Reservation ID: 5001
Check-In: 2026-08-20
Check-Out: 2026-08-23
```

Total:

```text
3 Nights × ₹3000
= ₹9000
```

---

## 🎯 Learning Objectives

This project was built to practice:

- Java Class Design
- Object-Oriented Programming
- Object Relationships
- Encapsulation
- Collections
- HashMap
- Exception Handling
- Custom Exceptions
- Enum
- Date and Time API
- File Handling
- CRUD Operations
- Business Logic
- Console Application Design

---

## 🔮 Future Improvements

Possible future features:

- Admin Login
- Multiple Hotels
- Room Amenities
- Different Pricing Based on Room Type
- Seasonal Pricing
- Payment System
- Invoice Generation
- Database Integration
- MySQL
- JDBC
- Spring Boot REST API
- Authentication and Authorization
- Web-based Frontend
- REST API
- Unit Testing with JUnit

---

## 👨‍💻 Author

**Sk Azizul Hosen**

B.Tech Computer Science & Engineering

---

## ⭐ If You Like This Project

If this project helped you understand Java OOP and project design, consider giving the repository a ⭐ star.

