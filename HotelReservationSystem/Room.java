package HotelReservationSystem;

public class Room {
  private int roomNo;
  private RoomType roomType;
  private double price;
  private boolean available;

  public Room(int roomNo,RoomType roomType,double price){
    this.roomNo = roomNo;
    this.roomType = roomType;
    this.price = price;
    this.available = true;
  }

  public Room(int roomNo,RoomType roomType,double price,boolean available){
    this.roomNo = roomNo;
    this.roomType = roomType;
    this.price = price;
    this.available = available;
  }

  public int getRoomNo(){
    return roomNo;
  }

  public double getPrice(){
    return price;
  }

  public boolean isAvailable(){
    return available;
  }

  public RoomType getRoomType(){
    return roomType;
  }

  public void setAvailable(boolean available){
    this.available = available;
  }

  public String toString(){
    return "Room No: " + roomNo + "\n" + 
    "Room Type: " + roomType + "\n" + 
    "Price: " + price + "\n" + 
    "Available: " + available + "\n" ;
  }
}
