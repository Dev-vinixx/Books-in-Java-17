import library.Books;
import users.User;

public class Main {
    Books library = new Books();
    private int userSize = 0;

    public static void main(String[] args) {
        Main mainInstance = new Main();
        try {
            mainInstance.library.donateBook("A Marca de Atena", "Rick Riordan", "Fantasia, mitologia greco-romana, juvenil", "A história é contada através do ponto de vista de Annabeth Chase, uma filha semideusa de Atena, a deusa grega da sabedoria, que parte pela busca da Atena Partenos, estátua que traria paz entre os romanos e gregos, além pelo de Percy Jackson, filho de Poseidon, de Piper McLean, filha de Afrodite e de Leo Valdez, filho de Hefesto, que juntamente com os outros semideuses da nova grande profecia (Jason Grace, Hazel Levesque e Frank Zhang), tentam encontrar Nico Di Angelo, que foi capturado pelos gigantes Oto e Efialtes ");
            User Vinicius = mainInstance.library.createUser("Vinicius", 16, "João de Freitas 981", mainInstance.userSize += 1);
            System.out.println(mainInstance.library.getTitles());

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}