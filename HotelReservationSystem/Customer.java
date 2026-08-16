package HotelReservationSystem;

public class Customer {
  private int CustomerId;
  private String name;
  private String phoneNumber;
  private String email;

  public Customer(int CustomerId,String name,String phoneNumber,String email){
    this.CustomerId = CustomerId;
    this.name = name;
    this.phoneNumber = phoneNumber;
    this.email = email;
  }


  public int getCustomerId(){
    return CustomerId;
  }

  public String getName(){
    return name;
  }

  public String getPhoneNumber(){
    return phoneNumber;
  }

  public String getEmail(){
    return email;
  }

  public String toString(){
    return String.format("Customer Id: %d\nName: %s\nPhone Number: %s\nEmail: %s\n",CustomerId,name,phoneNumber,email);
  }
}
