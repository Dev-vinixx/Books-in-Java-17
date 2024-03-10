package library;

import books.Book;
import users.User;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;
import java.util.Random;

public class Books {
    private final ArrayList<Book> book = new ArrayList<>();
    private final ArrayList<User> user = new ArrayList<>();
    public User createNewUser(String name, String address) {
        if (Objects.equals(name, "") || Objects.equals(address, "")) {
            throw new RuntimeException("System: " + "No value can be null");
        } else {
            User newUser = new User(verifyName(name),address,generateNumber(),this);
            user.add(newUser);
            return newUser;
        }
    }
    public int generateNumber() {
        Random rand = new Random();
        int randomNumber = rand.nextInt(900000) + 100000;
        for (User user : user) {
            if (user.getNumber() != randomNumber) {
                return randomNumber;
            } else {
                return generateNumber();
            }
        }
        return randomNumber;
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
    public boolean borrowSuccessful(String name, String title, int uniqueCode) {
        if (Objects.equals(name, "") || Objects.equals(title, "")) {
            throw new RuntimeException("System: " + "No value can be null");
        } else {
            int check = 1;
            for (User user : user) {
                if (user.getName().equals(name)) {
                    check = 2;
                    if (user.getNumber() == uniqueCode) {
                        check = 3;
                        if (user.getBorrow() >= 3) {
                            throw new RuntimeException("System: You've reached the maximum number of books you've borrowed, return one before you pick up another");
                        }
                        Iterator<Book> iterator = this.book.iterator();
                        while (iterator.hasNext()) {
                            Book book = iterator.next();
                            if (book.getTitle().equals(title)) {
                                user.borrowBooks(title, book.getAuthor(), book.getGender(), book.getForeword(), book.getNumber());
                                iterator.remove();
                                return true;
                            }
                        }
                    }
                }
            }
            if (check == 1) {
                throw new RuntimeException("System: " + "Unregistered user");
            } else if (check == 2) {
                throw new RuntimeException("System: " + "The unique code does not correspond to that account.");
            } else if (check == 3) {
                throw new RuntimeException("System: " + title + " with the title not found");
            }
        }
        return false;
    }
    public ArrayList<String> getBooks() {
        ArrayList<String> titles = new ArrayList<>();
        for (Book book : book ) {
            titles.add(book.getTitle());
        }
        return titles;
    }
    public Book getAllBook(String title) {
        if (Objects.equals(title, "")) {
            throw new RuntimeException("System: " + "No value can be null");
        } else {
            for (Book book : book) {
                if(book.getTitle().equals(title)) {
                    return book;
                }
            }
            throw new RuntimeException("System: " + "The book with the title " + title + " was not found");
        }
    }
}