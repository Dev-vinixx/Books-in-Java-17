package books;

public class Book {
    private String title;
    private String author;
    private String gender;
    private int id;
    private String prologue;
    private enumStatus status;
    public enum enumStatus {
        available,
        loaned,
    }
    private String usedBy;

    public Book(String title, String author, String gender, int id, enumStatus status, String prologue) {
        this.title = title;
        this.author = author;
        this.gender = gender;
        this.id = id;
        this.status = status;
        this.prologue = prologue;
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
    public int getId() {
        return id;
    }
    public enumStatus getStatus() {
        return status;
    }
    public String getPrologue() {
        return prologue;
    }
    public void setState(enumStatus value) {
        this.status = value;
    }

    public String getUsedBy() {
        return usedBy;
    }

    public void setUsedBy(String usedBy) {
        this.usedBy = usedBy;
    }
}