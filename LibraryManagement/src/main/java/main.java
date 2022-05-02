import LM.*;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.Scanner;

public class main {

    public static void main(String[] args) throws IOException, ParseException {

        Scanner input = new Scanner(System.in);

        boolean exit = false;
        int scelta = 0;
        boolean res = false;

        while(exit == false){
            System.out.println("_____________________________________________________");
            System.out.println("Gestione Biblioteca\n");
            System.out.println("_____________________________________________________");
            System.out.println("1. Admin\n" +
                    "2. Utente Generico\n" +
                    "3. Crea Account\n" +
                    "4. Uscita\n");

            System.out.println("Selezionare il tipo di utente: ");
            System.out.println("_____________________________________________________");

            //INPUT

            scelta = input.nextInt();

            switch (scelta) {
                case 1:

                    //ADMIN LOGIN + MENU
                    System.out.println("Inserire username: ");
                    String username = input.next();
                    System.out.println("Inserire password: ");
                    String password = input.next();
                    Login login = new Login(username, password, "admin");

                    if(login.userReader(username, password, "admin")){

                        System.out.print("\033[H\033[2J");
                        System.out.flush();

                        Admin admin = new Admin(username, password);

                        System.out.println("Benvenuto " + admin.getNome() + "!");

                        System.out.println("1. Elimina account\n" +
                                "2. Inserisci libro\n" +
                                "3. Elimina libro\n" +
                                "4. Modifica libro\n" +
                                "5. Visualizza libri\n" );

                        System.out.println("Selezionare un'opzione: ");
                        int sceltaAdmin = input.nextInt();

                        switch (sceltaAdmin) {
                            case 1:
                                System.out.println("\nEliminazione account\n");
                                System.out.println("Inserire il nome utente da eliminare: ");
                                String usernamedaEliminare = input.next();

                                admin.eliminaUtente(usernamedaEliminare);

                                break;
                            case 2:
                                System.out.println("\nInserisci libro\n");

                                System.out.println("Inserire il titolo del libro: ");
                                String titolo = input.next();

                                System.out.println("Inseire il numero delle pagine del libro: ");
                                int numeroPagine = input.nextInt();

                                System.out.println("Inserire la casa editrice del libro: ");
                                String casaEditrice = input.next();

                                System.out.println("Inserire l'anno di uscita del libro: ");
                                int annoUscita = input.nextInt();

                                System.out.println("Inserire la lingua del libro: ");
                                String lingua = input.next();

                                System.out.println("Inserire il numero di copie del libro: ");
                                int numeroCopie = input.nextInt();

                                System.out.println("Inserire l'ISBN del libro: ");
                                String isbn = input.next();

                                System.out.println("Inserire il codice Dewey del libro: ");
                                int codiceDewey = input.nextInt();

                                System.out.println("Inserire l'ID del libro: ");
                                int id = input.nextInt();

                                Libro libro = new Libro(titolo, numeroPagine, casaEditrice, annoUscita, lingua, numeroCopie, isbn, codiceDewey, id);

                                libro.creaLibro(libro);

                                break;

                            default:
                                System.out.println("Opzione non valida!");
                                break;
                        }

                    }

                    break;
                case 2:

                    //utente registrato
                    System.out.println("Inserire username: ");
                    String username1 = input.next();
                    System.out.println("Inserire password: ");
                    String password1 = input.next();
                    Login login1 = new Login(username1, password1, "utente");

                case 3:

                    //CREAZIONE DEL NUOVO ACCOUNT
                    System.out.println("Crea il tuo account!\n");
                    System.out.println("Inserire username: ");
                    String username2 = input.next();
                    System.out.println("Inserire password: ");
                    String password2 = input.next();

                    Registrazione registrazione = new Registrazione(username2, password2, "utente");

                    scelta = 2;

                    break;
                case 4:

                    //USCITA
                    exit = true;
                    System.out.println("Uscita...");
                    break;

                default:

                    //OPZIONE NON VALIDA
                    System.out.println("Opzione non valida");
                    break;

            }

        }
    }
}
