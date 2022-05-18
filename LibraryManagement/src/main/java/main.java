import LM.*;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

import static LM.JSONModifier.*;

public class main {

    public static void main(String[] args) throws IOException, ParseException {

        Scanner input = new Scanner(System.in);

        boolean exit = false;

        boolean res = false;

        do{

            int scelta = 0;
            boolean errore = true;

            while (errore) {

                try {

                    errore = false;

                    System.out.println("-----------------------------------------------------");
                    System.out.println("                                                     ");
                    System.out.println("                Gestione Biblioteca");
                    System.out.println("                                                     ");
                    System.out.println("-----------------------------------------------------");
                    System.out.println("\n1. Accesso Admin\n" +
                            "2. Accesso Utente Registrato\n" +
                            "3. Crea Account\n" +
                            "4. Visualizza Libri\n" +
                            "5. Uscita\n");
                    System.out.println("-----------------------------------------------------\n");
                    System.out.println("Selezionare un numero: ");
                    scelta = input.nextInt();

                } catch (InputMismatchException ime) {

                    System.out.println("Errore! \nInserire un numero da 1 a 5");
                    input.nextLine();
                    errore = true;

                }

            }

            switch (scelta) {

                case 1:

                    //REGISTRATIZIONE UTENTE ADMIN
                    System.out.println("-----------------------------------------------------");
                    System.out.println("                                                     ");
                    System.out.println("                  Accesso Admin");
                    System.out.println("                                                     ");
                    System.out.println("-----------------------------------------------------");

                    System.out.println("Inserire username: ");
                    String username = input.next();
                    System.out.println("Inserire password: ");
                    String password = input.next();

                    VerificaCredenziali(username, password, "admin");

                    if (userReader(username, password, "admin")) {

                        System.out.print("\033[H\033[2J");
                        System.out.flush();

                        Admin admin = new Admin(username, password);

                        System.out.println("\nBenvenuto " + admin.getNome() + "!\n");

                        System.out.println("1. Elimina account\n" +
                                "2. Modifica catalogo\n" +
                                "3. Visualizza libri\n" +
                                "4. Gestisci notifiche\n");

                        System.out.println("Selezionare un'opzione: ");

                        int sceltaAdmin = input.nextInt();

                        switch (sceltaAdmin) {

                            case 1:

                                //ELIMINA ACCOUNT
                                System.out.println("\nEliminazione account\n");
                                System.out.println("Inserire il nome utente da eliminare: ");
                                String usernamedaEliminare = input.next();

                                eliminaUtente(usernamedaEliminare);

                            break;

                            case 2:

                                //MODIFICA CATALOGO
                                System.out.println("\nModifica catalogo\n");
                                System.out.println("1. Aggiungi libro\n" +
                                        "2. Elimina libro\n" +
                                        "3. Modifica libro\n");
                                System.out.println("Selezionare un'opzione: ");
                                int sceltaModifica = input.nextInt();

                                switch (sceltaModifica) {

                                    case 1:

                                        //INSERIMENTO LIBRO
                                        System.out.println("\nInserisci libro\n");

                                        System.out.println("Inserire il titolo del libro: ");
                                        String titolo = input.next();

                                        System.out.println("Inserire l'autore del libro: ");
                                        String autore = input.next();

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

                                        Libro libro = new Libro(titolo, autore, numeroPagine, casaEditrice, annoUscita, lingua, numeroCopie, isbn, codiceDewey);

                                        creaLibro(libro);

                                        break;

                                    case 2:

                                        //ELIMINAZIONE LIBRO
                                        System.out.println("\nEliminazione libro\n");
                                        visualizzaLibri();
                                        System.out.println("Inserire l'ID del libro da eliminare: ");
                                        int idLibroEliminare = input.nextInt();

                                        eliminaLibro(idLibroEliminare);

                                        break;

                                    case 3:
                                        //MODIFICA LIBRO
                                        System.out.println("\nModifica Libro\n");
                                        visualizzaLibri();
                                        System.out.println("Inserire l'ID del libro da modificare: ");
                                        int idLibroModificare = input.nextInt();

                                        break;

                                    case 4:
                                        //GESTIONE NOTIFICHE
                                        System.out.println("\nGestione notifiche\n");
                                        System.out.println("1. Visualizza notifiche" +
                                                "\n2. Invia notifica" +
                                                "\n3. Elimina notifica" +
                                                "\n4. Torna indietro");
                                        int sceltaNotifiche = input.nextInt();

                                        switch(sceltaNotifiche) {
                                            case 1:
                                                //VISUALIZZAZIONE NOTIFICHE
                                                /*System.out.println("\nVisualizzazione notifiche\n");
                                                visualizzaNotifiche();
                                                break;*/

                                            case 2:
                                                //INVIO NOTIFICA
                                                /*System.out.println("Scrivi il messaggio da inserire come notifica: ");
                                                String messaggio = input.next();
                                                inviaNotifica(messaggio);
                                                break;*/

                                            case 3:
                                                //ELIMINAZIONE NOTIFICA
                                                /*System.out.println("\nEliminazione notifica\n");
                                                visualizzaNotifiche();
                                                System.out.println("Inserire l'indice della notifica da eliminare: ");
                                                int idNotificaEliminare = input.nextInt();

                                                eliminaNotifica(idNotificaEliminare);*/

                                                break;
                                        }
                                }

                            break;

                            case 3:

                                //VISUALIZZA LIBRI
                                System.out.println("-----------------------------------------------------");
                                System.out.println("                                                     ");
                                System.out.println("\n               Visualizza Libri                    ");
                                System.out.println("                                                     ");
                                System.out.println("-----------------------------------------------------");

                                visualizzaLibri();

                                System.out.println("Vuoi visualizzare un libro specifico? (1) si (2) no");
                                int sceltaVisualizzaLibro = input.nextInt();

                                if(sceltaVisualizzaLibro == 1){

                                    System.out.println("Inserire il titolo del libro da visualizzare: ");
                                    int idLibroVisualizzare = input.nextInt();

                                    visualizzaLibro(idLibroVisualizzare-1);

                                }else if(sceltaVisualizzaLibro == 2){

                                    System.out.println("\n\n");

                                }

                            break;

                            default:

                                System.out.println("Opzione non valida!");

                            break;
                        }

                    }

                break;

                case 2:

                    //REGISTRAZIONE UTENTE REGISTRATO
                    System.out.println("Registrazione Utente Registrato\n");

                    System.out.println("Inserire username: ");
                    String usernameUtenteReg = input.next();

                    System.out.println("Inserire password: ");
                    String passwordUtenteReg = input.next();

                    VerificaCredenziali(usernameUtenteReg, passwordUtenteReg, "utente");

                    if (userReader(usernameUtenteReg, passwordUtenteReg, "utente")) {

                        System.out.print("\033[H\033[2J");
                        System.out.flush();

                        UtenteRegistrato utenteRegistrato = new UtenteRegistrato(usernameUtenteReg, passwordUtenteReg, "utente");

                        System.out.println("Benvenuto " + utenteRegistrato.getNome() + "!");

                        System.out.println("1. Gestione Notifiche" +
                                "2. Visualizza libri\n");
                                //METTERE VISUALIZZA LIBRI
                        System.out.println("Selezionare un'opzione: ");
                        int sceltaUtenteReg = input.nextInt();

                        switch (sceltaUtenteReg) {

                            case 1:

                                //GESTIONE NOTIFICHE
                                System.out.println("\nGestione notifiche\n");
                                System.out.println("1. Visualizza notifiche" +
                                        "\n2. Invia notifica" +
                                        "\n3. Elimina notifica" +
                                        "\n4. Torna indietro");
                                int sceltaNotifiche = input.nextInt();

                                switch(sceltaNotifiche) {
                                    case 1:
                                        //VISUALIZZAZIONE NOTIFICHE
                                                /*System.out.println("\nVisualizzazione notifiche\n");
                                                visualizzaNotifiche();
                                                break;*/

                                    case 2:
                                        //INVIO NOTIFICA
                                                /*System.out.println("Scrivi il messaggio da inserire come notifica: ");
                                                String messaggio = input.next();
                                                inviaNotifica(messaggio);
                                                break;*/

                                    case 3:
                                        //ELIMINAZIONE NOTIFICA
                                                /*System.out.println("\nEliminazione notifica\n");
                                                visualizzaNotifiche();
                                                System.out.println("Inserire l'indice della notifica da eliminare: ");
                                                int idNotificaEliminare = input.nextInt();

                                                eliminaNotifica(idNotificaEliminare);

                                    break;*/
                                }
                                break;
                            case 2:

                                System.out.println("\nVisualizza Libri\n");

                            break;

                        }

                    }

                break;

                case 3:

                    //CREAZIONE DEL NUOVO ACCOUNT
                    System.out.println("Crea il tuo account!\n");

                    System.out.println("Inserire username: ");
                    String username2 = input.next();

                    System.out.println("Inserire password: ");
                    String password2 = input.next();

                    creaUtente(username2, password2, "utente");

                    break;

                case 4:

                    //VISUALIZZA LIBRI
                    System.out.println("-----------------------------------------------------");
                    System.out.println("                                                     ");
                    System.out.println("                 Visualizza Libri                    ");
                    System.out.println("                                                     ");
                    System.out.println("-----------------------------------------------------");
                    visualizzaLibri();

                    System.out.println("Vuoi visualizzare un libro? (1) si (2) no");
                    int sceltaVisualizzaLibro = input.nextInt();

                    if(sceltaVisualizzaLibro == 1){

                        System.out.println("Inserire il titolo del libro da visualizzare: ");
                        int idLibroVisualizzare = input.nextInt();
                        visualizzaLibro(idLibroVisualizzare-1);

                    }else if(sceltaVisualizzaLibro == 2){

                        System.out.println("\n\n");

                    }
                    break;

                case 5:

                    //USCITA DAL PROGRAMMA
                    exit = true;
                    System.out.println("Uscita...");

                break;

                default:

                    if (scelta >= 6){

                        System.out.println("\nScelta non valida\n");

                    }

                break;

            }

        }while(!exit);

    }

}
