package library;

import books.Book;
import users.User;

import java.util.ArrayList;
import java.util.Objects;

public class Books {
    private ArrayList<Book> book = new ArrayList<>();

    public void donateBook(String title, String author, String gender, String prologue) throws NullPointerException {
        if (title.isBlank() || author.isBlank() || gender.isBlank() || prologue.isBlank()) {
            throw new NullPointerException("no value can be null");
        } else {
            boolean exist = false;
            for (Book book : book) {
                if(book.getTitle().equalsIgnoreCase(title)) {
                    exist = true;
                    break;
                }
            }
            if(!exist) {
                this.book.add(new Book(title, author, gender, this.book.size(), Book.enumStatus.available, prologue));
            } else {
                throw new RuntimeException("this book has already been registered");
            }
        }
    }
    public String getTitles() {
        if (book.isEmpty()) {
            throw new NullPointerException("There are no books in the library");
        } else {
            String title = "";
            for (Book book : book) {
                title += "Title: " + book.getTitle() + " Status: " + book.getStatus() + "\n";
            }
            return title;
        }
    };
    public Book readBook(String title) throws NullPointerException, RuntimeException {
        if (title.isBlank()) {
            throw new NullPointerException("no value can be null");
        }
        for (Book book : book) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                return book;
            }
        }
        throw new RuntimeException("there is no record of a book with that title");
    };
    public User createUser(String name, int age, String address, int id) {
        return new User(name, age, address, id);
    }
    public String borrowBook(User user, String title) throws NullPointerException, RuntimeException {
        if (title.isBlank()) {
            throw new NullPointerException("no value can be null");
        }
        for (Book book : book) {
            if (book.getTitle().equalsIgnoreCase(title) && user.getBorrowLimit() > 0 ){
                if (book.getStatus() == Book.enumStatus.loaned) {
                    throw new RuntimeException("this book has already been borrowed by the user " + book.getUsedBy() + " wait for it to be returned");
                }
                book.setUsedBy(user.getName());
                user.setBorrowLimit(-1);
                book.setState(Book.enumStatus.loaned);
                return "the book has been successfully borrowed";
            }
        }
        if (user.getBorrowLimit() <= 0) {
            throw new RuntimeException("the user has reached the limit of borrowed books.");
        }
        throw new RuntimeException("there is no record of a book with that title");
    }
    public String returnBook(User user, String title) throws NullPointerException, RuntimeException {
        if (title.isBlank()) {
            throw new NullPointerException("no value can be null");
        }
        for (Book book : book) {
            if (book.getTitle().equalsIgnoreCase(title) && book.getUsedBy() != null && book.getUsedBy().equalsIgnoreCase(user.getName())){
                book.setUsedBy("");
                book.setState(Book.enumStatus.available);
                return "the book has been successfully returned";
            } else if (book.getTitle().equalsIgnoreCase(title)) {
                if (book.getStatus() == Book.enumStatus.available) {
                    throw new RuntimeException("this book has not yet been borrowed by anyone");
                } else if (book.getUsedBy() == null || !book.getUsedBy().equalsIgnoreCase(user.getName())) {
                    throw new RuntimeException("This book was borrowed by user " + book.getUsedBy() + " and not by user: " + user.getName());
                }
            }
        }
        throw new RuntimeException("there is no record of a book with that title");
    }


}