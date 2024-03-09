import library.Library;

public class Main {
    Library library = new Library();
    public static void main(String[] args) {

        try {
            Main mainInstance = new Main();
            mainInstance.createUser("Vinicius", "Rua João de Freitas");
            mainInstance.createUser("Victor", "Rua João de Freitas");
        } catch (RuntimeException exception) {
            System.out.println(exception.getMessage());
        }
    }
    public void createUser(String name, String address) {
        library.createNewUser(name, address);
        System.out.println("System: The user has been successfully registered.");
    }
    public void donateBook(String name,String title, String author, String gender, String foreword) {
        library.donateBook(name, title, author,gender,foreword);
    }
    public void borrow(String name, String title) {
        library.borrow(name, title);
    }
    public void returnBooks(String name, String title) {
        library.returnBook(name, title);
        System.out.println("System: " + name + ", your book has been successfully returned.");
    }
}
