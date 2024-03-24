package users;
 public class User {
     private String name;
     private int age;
     private String address;
     private int id;
     private int borrowLimit = 3;
     public User(String name, int age, String address, int id) {
         this.name = name;
         this.age = age;
         this.address = address;
         this.id = id;
     }

     public String getName() {
         return name;
     }

     public int getAge() {
         return age;
     }

     public String getAddress() {
         return address;
     }

     public int getBorrowLimit() {
         return borrowLimit;
     }

     public void setBorrowLimit(int borrow) {
         this.borrowLimit += borrow;
     }
 }