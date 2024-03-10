package books;

public class Book {
    private String title;
    private String author;
    private String gender;
    private int number;
    private String foreword;

    public Book(String title, String author, String gender, String foreword, int number) {
        this.title = title;
        this.author = author;
        this.gender = gender;
        this.foreword = foreword;
        this.number = number;
    }
    public String getTitle() {
        return title;
    }
    public String getAuthor() {
        return author;
    }
    public String getGender() {
        return gender;
    }
    public int getNumber() {
        return number;
    }
    public String getForeword() {
        return foreword;
    }
}