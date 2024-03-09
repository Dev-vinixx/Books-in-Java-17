package library;

import books.Book;
import users.User;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;
import java.util.Random;

public class Library {
    private final ArrayList<Book> book = new ArrayList<>();
    private final ArrayList<User> user = new ArrayList<>();
    public void createNewUser(String name, String address) {
        if (Objects.equals(name, "") || Objects.equals(address, "")) {
            throw new RuntimeException("System: " + "No value can be null");
        } else {
            user.add(new User(verifyName(name), address, generateNumber(), this));
        }
    }
    public int generateNumber() {
        Random rand = new Random();
        return rand.nextInt(900000) + 100000;
    }
    public String verifyName(String value) {

        for (User user : user) {
            if (user.getName().equals(value)) {
                throw new RuntimeException("System: Another user has already been registered with this same name.");
            }
        }
        return value;

    }
    public void donateBook(String name, String title, String author, String gender, String foreword) {
        if (Objects.equals(name, "") || Objects.equals(title, "") || Objects.equals(author, "") || Objects.equals(gender, "") || Objects.equals(foreword, "")) {
            throw new RuntimeException("System: " + "No value can be null");
        } else {
            boolean userFound = false;
            for (User user : user) {
                if (user.getName().equals(name)) {
                    userFound = true;
                    if (user.getDonates() < 3) {
                        user.setDonates(user.getDonates() + 1);
                        book.add(new Book(title, author, gender, foreword, book.size() + 1));
                        System.out.println("System: " + user.getName() + ", the book was successfully donated and " + (3 - user.getDonates()) + " more book has been added to your donation balance.");
                    } else if (user.getDonates() == 3) {
                        user.setDonates(0);
                        user.setBorrowLimit(user.getBorrowLimit() + 1);
                        book.add(new Book(title, author, gender, foreword, book.size() + 1));
                        System.out.println("System: " + user.getName() + ", the book was successfully donated and 1 more has been added to your balance of books that you can borrow from the library." + "Your balance is: " + user.getBorrowLimit());
                    }
                }
            }
            if (!userFound) {
                book.add(new Book(title, author, gender, foreword, book.size() + 1));
                System.out.println("Book successfully donated but as there was no user with this name registered in the system, the book is just donated.");
            }
        }
    }
    public void returnBook(String name, String title) {
        if (Objects.equals(name, "") || Objects.equals(title, "")) {
            throw new RuntimeException("System: " + "No value can be null");
        } else {
            boolean pass = false;
            for (User user : user) {
                if (user.getName().equals(name)) {
                    pass = true;
                    user.returnBook(title);
                }
            }
            if (!pass) {
                throw new RuntimeException("System: " + "Unregistered user");
            }
        }
    }
    public void bookComeBack( String title, String author, String gender, String foreword, int number) {
        book.add(new Book(title, author, gender, foreword, number));
    }
    public void borrow(String name, String title) {
        if (Objects.equals(name, "") || Objects.equals(title, "")) {
            throw new RuntimeException("System: " + "No value can be null");
        } else {
            boolean pass = false;
            int check = 1;
            for (User user : user) {
                if (user.getName().equals(name)) {
                    check = 2;
                    if (user.getBorrow() >= 3) {
                        throw new RuntimeException("System: You've reached the maximum number of books you've borrowed, return one before you pick up another");
                    }
                    Iterator<Book> iterator = this.book.iterator();
                    while (iterator.hasNext()) {
                        Book book = iterator.next();
                        if (book.getTitle().equals(title)) {
                            pass = true;
                            user.borrowBooks(title, book.getAuthor(), book.getGender(), book.getForeword(), book.getNumber());
                            iterator.remove();
                            System.out.println("System: " + name + ", you successfully borrowed the book: " + title);
                            break;
                        }
                    }
                }
            }
            if (!pass && check == 1) {
                throw new RuntimeException("System: " + "Unregistered user");
            } else if (!pass && check == 2) {
                throw new RuntimeException("System: " + title + " with the title not found");
            }
        }
    }
}