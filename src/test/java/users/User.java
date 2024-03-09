package users;

import books.Book;
import library.Library;

import java.util.ArrayList;

public class User {
    private String name;
    private String address;
    private int number;
    private int donates = 0;
    private int borrow = 0;
    private int borrowLimit = 3;
    private Library library;
    private ArrayList<Book> bookBorrow = new ArrayList<>();
    public void borrowBooks(String title, String author, String gender, String foreword, int number) {
        bookBorrow.add(new Book(title, author, gender, foreword, number));
    }
    public User(String name, String address, int number, Library library) {
        this.name = name;
        this.address = address;
        this.number = number;
        this.library = library;
    }
    public String getName() {
        return name;
    }
    public int getDonates() {
        return donates;
    }
    public void setDonates(int donates) {
        this.donates = donates;
    }
    public int getBorrowLimit() {
        return borrowLimit;
    }

    public int getBorrow() {
        return borrow;
    }

    public void setBorrow(int borrow) {
        this.borrow = borrow;
    }

    public void setBorrowLimit(int borrowLimit) {
        this.borrowLimit = borrowLimit;
    }
    public void returnBook(String title) {
        boolean pass = false;
        for (Book book : bookBorrow) {
            if (book.getTitle().equals(title)) {
                pass = true;
                library.bookComeBack(book.getTitle(), book.getAuthor(), book.getGender(), book.getForeword(), book.getNumber());
            }
        }
        if (!pass) {
            throw new RuntimeException("System: " + title + ", this book is not with the user: " + name);
        }
    }

}