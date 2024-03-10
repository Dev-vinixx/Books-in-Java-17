import books.Book;
import library.Books;
import users.User;

import java.util.ArrayList;


public class Main {
    Books library = new Books();
    public static void main(String[] args) {

        try {
            Main mainInstance = new Main();
            mainInstance.createUser("Vinicius", "Rua João de Freitas");
            mainInstance.createUser("Victor", "Rua João de Freitas");
            mainInstance.donateBook("Vinicius", "1984", "George Orwell", "Ficção distópica", " Em um futuro sombrio, a sociedade é governada por um regime totalitário que controla todos os aspectos da vida humana, até mesmo seus pensamentos. O protagonista, Winston Smith, luta para manter sua sanidade e moralidade contra a opressão onipresente do Partido.");
            mainInstance.donateBook("Victor", "Orgulho e Preconceito", "Jane Austen", "Romance", "Ambientado na Inglaterra rural do século XIX, o livro conta a história de Elizabeth Bennet enquanto ela navega questões de maneiras, educação, moralidade e casamento.");
            mainInstance.donateBook("Vinicius", "O Sol é para todos", "Harper Lee", "Ficção / Drama social", "A história é contada através dos olhos de Scout, uma menina jovem em uma cidade do sul dos EUA, que lida com a injustiça racial quando seu pai advogado defende um homem negro acusado injustamente.");
            mainInstance.donateBook("Victor", "O Senhor dos Anéis", "J.R.R. Tolkien", "Fantasia ", "A saga épica de Frodo Bolseiro e sua jornada para destruir o Anel de Poder e derrotar o Senhor das Trevas, Sauron. Ambientado na Terra-média, o livro explora temas de amizade, coragem e a eterna luta entre o bem e o mal.");
            mainInstance.donateBook("lasanha man", "Cem Anos de Solidão", "Gabriel García Márquez", "Realismo mágico", "A história da família Buendía na cidade fictícia de Macondo. O livro é uma metáfora da história da América Latina, misturando o mágico e o real de uma maneira que é característica do realismo mágico.");
            mainInstance.donateBook("lasanha gyr", "Percy Jacson A Maldição do Titã", "Rick Riordan", "Fantasia / Aventura", "Um chamado do amigo Grover deixa Percy a postos para mais uma missão: dois novos meios-sangues foram encontrados, e sua ascendência ainda é desconhecida. Como sempre, Percy sabe que precisará contar com o poder de seus aliados heróis, com sua leal espada Contracorrente… e com uma caroninha da mãe. O que eles ainda não sabem é que os jovens descobertos não são os únicos em perigo: Cronos, o Senhor dos Titãs, arquitetou um de seus planos mais traiçoeiros, e nossos heróis serão presas fáceis. Um monstro ancestral foi despertado – um ser com poder suficiente para destruir o Olimpo –, e Ártemis, a única deusa capaz de encontrá-lo, desapareceu. Percy e seus amigos têm apenas uma semana para resgatar a deusa sequestrada e solucionar o mistério que ronda o monstro que ela caçava.");
            mainInstance.booksInTheLibrary();
        } catch (RuntimeException exception) {
            System.out.println(exception.getMessage());
        }
    }
    public void createUser(String name, String address) {
        User user = library.createNewUser(name, address);
        System.out.println("System: The user has been successfully registered.");
        System.out.println("System: Your username is " + user.getName() + " and your unique code is " + user.getNumber() + ". Save this information to be able to borrow books later.");
    }
    public void donateBook(String name,String title, String author, String gender, String foreword) {
        library.donateBook(name, title, author,gender,foreword);
    }
    public void borrow(String name, String title, int uniqueCode) {
        boolean success = library.borrowSuccessful(name, title, uniqueCode);
        if (success) {
            System.out.println("System: " + name + ", you successfully borrowed the book: " + title);
        }
    }
    public void returnBooks(String name, String title) {
        library.returnBook(name, title);
        System.out.println("System: " + name + ", your book has been successfully returned.");
    }
    public void booksInTheLibrary() {
        ArrayList<String> bookTitle = library.getBooks();
        if (!bookTitle.isEmpty()) {
            System.out.println("System: " + "The books registered in the bookstore are: ");
            for (String title : bookTitle) {
                System.out.println(title);
            }
        } else {
            throw new RuntimeException("Não a livros na biblioteca");
        }
    }
    public void getAllOfBook(String title) {
        if (title != "") {
            Book book = library.getAllBook(title);
            System.out.println("System: The information about the book is: ");
            System.out.println("Title: " + book.getTitle());
            System.out.println("Author: " + book.getAuthor());
            System.out.println("Gender: " + book.getGender());
            System.out.println("Foreword: " + book.getForeword());
        }
    }
}