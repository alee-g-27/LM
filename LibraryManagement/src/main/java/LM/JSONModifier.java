package LM;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.Arrays;

public class JSONModifier {

    public static void creaLibro (Libro libro) {

            try {

                String titolodaTrovare = libro.getTitolo();

                if (verificaLibro(titolodaTrovare)==false) {

                    System.out.println("Creazione libro in corso...");
                    JSONParser jsonParser = new JSONParser();
                    FileReader reader = new FileReader(".settings/books.json");
                    //Read JSON file
                    JSONArray bookObject = (JSONArray)jsonParser.parse(reader);

                    //Create JSON Object
                    JSONObject book = new JSONObject();
                    book.put("titolo", libro.getTitolo());
                    book.put("autore", libro.getAutore());
                    book.put("nPagine", libro.getnPagine());
                    book.put("CasaEditrice", libro.getCasaEditrice());
                    book.put("AnnoUscita", libro.getAnnoUscita());
                    book.put("lingua", libro.getLingua());
                    book.put("nCopie", libro.getnCopie());
                    book.put("ISBN", libro.getISBN());
                    book.put("dewey", libro.getDewey());
                    book.put("ID", bookObject.size()+1);

                    //Add JSON Object to JSON Array
                    bookObject.add(book);

                    //Write JSON file
                    FileWriter file = new FileWriter(".settings/books.json");
                    file.write(bookObject.toJSONString());
                    file.flush();
                    System.out.println("Libro inserito con successo!");

                }

            } catch (IOException e) {

                e.printStackTrace();

            } catch (ParseException e) {

                e.printStackTrace();

            }

    }

    //Verifica libro
    public static boolean verificaLibro(String titolodaTrovare) throws IOException, ParseException {

        boolean res = false;

        JSONParser jsonParser = new JSONParser();
        FileReader reader = new FileReader(".settings/books.json");
        JSONArray books = (JSONArray)jsonParser.parse(reader);

        for (int i = 0; i < books.size(); i++) {

            JSONObject book = (JSONObject) books.get(i);

            String titolo = (String) book.get("titolo");

            System.out.println("titolo: " + titolo);

            if (titolodaTrovare.equals(titolo)) {

                res = true;
                System.out.println("Libro già esistente!");

            }

        }

        return res;

    }

    public static void eliminaLibro(int ID) throws IOException, ParseException{

        JSONParser parser = new JSONParser();
        FileReader reader = new FileReader(".settings/books.json");
        JSONArray books = (JSONArray) parser.parse(reader);

        if (verificaLibroEsistente(ID)) {

            for (int i = 0; i < books.size(); i++) {

                JSONObject book = (JSONObject) books.get(i);
                long id = (long) book.get("ID");

                if (ID==id) {

                    books.remove(i);
                    System.out.println("Libro eliminato!");

                }

            }

            FileWriter file = new FileWriter(".settings/books.json");
            file.write(books.toJSONString());
            file.flush();

        }else{

            System.out.println("Libro non esistente!");

        }


    }

    public static boolean verificaLibroEsistente(int ID) throws IOException, ParseException {

        boolean res = false;

        JSONParser jsonParser = new JSONParser();
        FileReader reader = new FileReader(".settings/books.json");
        JSONArray booksObject = (JSONArray)jsonParser.parse(reader);

        for (int i = 0; i < booksObject.size(); i++) {

            JSONObject user = (JSONObject) booksObject.get(i);

            long id = (long) user.get("ID");

            if (ID==id) {

                res = true;

            }

        }
        System.out.println(res);
        return res;

    }

    //Metodo per visualizzare il titolo dei libri dal file JSON
    public void parseBooksObject(JSONObject book){

        String titolo = (String) book.get("titolo");
        System.out.println(titolo);

    }

    //----------------------------------------------------
    //OPERAZIONI SULL'ACCOUNT
    //----------------------------------------------------

    //Metodo per creare l'utente
    public static void creaUtente(String NomeUtente, String PSW, String tipoUtente){

        try {

            if (verificaUtenteEsistente(NomeUtente) == false) {

                System.out.println("Registrazione in corso...");

                JSONParser jsonParser = new JSONParser();
                FileReader reader = new FileReader(".settings/users.json");
                JSONArray usersObject = (JSONArray)jsonParser.parse(reader);
                JSONObject user = new JSONObject();

                user.put("Nome Utente", NomeUtente);
                user.put("PSW", PSW);
                user.put("Tipoutente", tipoUtente);

                //Add JSON Object to JSON Array
                usersObject.add(user);

                //Write JSON file
                FileWriter file = new FileWriter(".settings/users.json");
                file.write(usersObject.toJSONString());
                file.flush();

                System.out.println("Registrazione completata!");

            }else{

                System.out.println("Utente già esistente!");

            }

        } catch (IOException e) {

            e.printStackTrace();

        } catch (ParseException e) {

            e.printStackTrace();

        }

    }


    //Metodo per leggere il file json DA METTERE ANCHE PER I LIBRI
    public static boolean userReader(String NomeUtente, String PSW, String tipoUtente) throws IOException, ParseException {

        boolean res = false;

        JSONParser jsonParser = new JSONParser();
        FileReader reader = new FileReader(".settings/users.json");
        JSONArray usersObject = (JSONArray)jsonParser.parse(reader);

        for (int i = 0; i < usersObject.size(); i++) {

            JSONObject user = (JSONObject) usersObject.get(i);

            String nomeUtente = (String) user.get("Nome Utente");

            String psw = (String) user.get("PSW");

            String type = (String) user.get("Tipoutente");

            if (NomeUtente.equals(nomeUtente) && PSW.equals(psw) && tipoUtente.equals(type)) {

                res = true;

            }

        }

        return res;

    }

    public static void VerificaCredenziali(String NomeUtente, String PSW, String tipoUtente) throws IOException, ParseException {

        boolean res = false;

        res = userReader(NomeUtente, PSW, tipoUtente);

        System.out.println("\nVerifica delle credenziali in corso...");


        if (res) {

            System.out.println("Accesso riuscito!\n");

        }else{

            System.out.println("Accesso non riuscito!\n");

        }

    }

    //Metodo per verificare se l'utente esiste già
    public static boolean verificaUtenteEsistente(String NomeUtente) throws IOException, ParseException {

        boolean res = false;

        JSONParser jsonParser = new JSONParser();
        FileReader reader = new FileReader(".settings/users.json");
        JSONArray usersObject = (JSONArray)jsonParser.parse(reader);

        for (int i = 0; i < usersObject.size(); i++) {

            JSONObject user = (JSONObject) usersObject.get(i);

            String nomeUtente = (String) user.get("Nome Utente");
            System.out.println("Nome Utente: " + nomeUtente);

            if (NomeUtente.equals(nomeUtente)) {

                res = true;

            }

        }

        return res;

    }

    //Metodo per visualizzare i libri in base al titolo
    public static void visualizzaLibri() throws IOException, ParseException {

        JSONParser jsonParser = new JSONParser();
        FileReader reader = new FileReader(".settings/books.json");
        JSONArray booksObject = (JSONArray)jsonParser.parse(reader);

        String[] titoli = new String[booksObject.size()];
        //long[] ID = new long[booksObject.size()];

        long[] indici = new long[booksObject.size()];
        long[] ID = new long[booksObject.size()];

        titoli = prendiTitoli();
        ordinaLibri(titoli);
        //setIndiceLibri(titoli, indici);
        ID=getIndiceLibri();
        /*
        for(int i = 0; i < booksObject.size(); i++) {
            JSONObject books = (JSONObject) booksObject.get(i);
            String id = (String) books.get("ID");

            if (ID[i].equals(id)) {

            }
        }*/

        System.out.println("\nTitoli dei libri disponibili:\n");

        for(int i = 0;i < booksObject.size();i++) {

            System.out.println((i+1) + " - " +  titoli[i] + "ID: " + ID[i] + "\n");

        }

    }

    public static void visualizzaLibro(int indice) throws IOException, ParseException {

        JSONParser jsonParser = new JSONParser();
        FileReader reader = new FileReader(".settings/books.json");
        JSONArray booksObject = (JSONArray)jsonParser.parse(reader);
        JSONObject books;

        books = (JSONObject) booksObject.get(indice);
        System.out.println("\nTitolo: " + books.get("titolo") + "\n");
        System.out.println("Autore: " + books.get("autore") + "\n");
        System.out.println("Lingua: " + books.get("lingua") + "\n");
        System.out.println("Numero pagine: " + books.get("nPagine") + "\n");
        System.out.println("Anno di pubblicazione: " + books.get("AnnoUscita") + "\n");
        System.out.println("ISBN: " + books.get("ISBN") + "\n");
        System.out.println("Casa Editrice: " + books.get("CasaEditrice") + "\n");
        System.out.println("Dewey: " + books.get("dewey") + "\n");
        System.out.println("Numero copie disponibili: " + books.get("nCopie") + "\n");
        System.out.println("ID: " + books.get("ID") + "\n");

    }

    private static String[] prendiTitoli() throws IOException, ParseException {

        JSONParser jsonParser = new JSONParser();
        FileReader reader = new FileReader(".settings/books.json");
        JSONArray booksObject = (JSONArray)jsonParser.parse(reader);

        String[] titoli = new String[booksObject.size()];

        for(int i = 0; i < booksObject.size(); i++) {

            JSONObject books = (JSONObject) booksObject.get(i);
            String titolo = (String) books.get("titolo");
            titoli[i] = titolo;

        }

        return titoli;

    }


    //Metodo per ordinare i libri in base al titolo
    private static String[] ordinaLibri (String[] Arr) {

        Arrays.sort(Arr);

        return Arr;

    }

    //DA SISTEMARE
    private static long[] getIndiceLibri() throws IOException, ParseException {

        JSONParser jsonParser = new JSONParser();
        FileReader reader = new FileReader(".settings/books.json");
        JSONArray booksObject = (JSONArray)jsonParser.parse(reader);
        //Create JSON Object
        JSONObject book = new JSONObject();
        long arrIndici[] = new long[booksObject.size()];

        for(int i = 0; i < booksObject.size(); i++) {
            book = (JSONObject) booksObject.get(i);

            arrIndici[i] = (long) book.get("ID");
            System.out.println(arrIndici[i]);

        }

        return arrIndici;

    }

    /*public static void changeBooksID() throws IOException, ParseException {

        JSONParser jsonParser = new JSONParser();
        FileReader reader = new FileReader(".settings/books.json");
        JSONArray booksObject = (JSONArray)jsonParser.parse(reader);
        //Create JSON Object
        JSONObject book = new JSONObject();

        int arrIndici[] = new int[booksObject.size()];

        for(int i = 0; i < booksObject.size(); i++) {

            //arrIndici[i] = i+1;
            book.remove("ID");

        }

        booksObject.remove(book);

        //Write JSON file
        FileWriter file = new FileWriter(".settings/books.json");
        file.write(booksObject.toJSONString());
        file.flush();

    }*/


    public static void eliminaUtente(String nomeUtente) throws IOException, ParseException{

        JSONParser parser = new JSONParser();
        FileReader reader = new FileReader(".settings/users.json");
        JSONArray users = (JSONArray) parser.parse(reader);

        if (verificaUtenteEsistente(nomeUtente)) {

            for (int i = 0; i < users.size(); i++) {

                JSONObject user = (JSONObject) users.get(i);
                String userName = (String) user.get("Nome Utente");

                if (userName.equals(nomeUtente)) {

                    users.remove(i);
                    System.out.println("Utente eliminato!");

                }

            }

            FileWriter file = new FileWriter(".settings/users.json");
            file.write(users.toJSONString());
            file.flush();

        }else{

            System.out.println("Utente non esistente!");

        }

    }

    //----------------------------------------------------
    //INVIO DELLE RICHIESTE
    //----------------------------------------------------

    public static void InviaRichiesta(String Richiesta, String NomeUtente) throws IOException, ParseException {

        //Verificare se l'utente esiste già
        try {
            System.out.println("Invio richiesta in corso...");

            JSONParser jsonParser = new JSONParser();
            FileReader reader = new FileReader(".settings/request.json");
            JSONArray richiestaObject = (JSONArray)jsonParser.parse(reader);

            JSONObject richiesta = new JSONObject();
            richiesta.put("Nome Utente", NomeUtente);
            richiesta.put("Richiesta", Richiesta);

            richiestaObject.add(richiesta);

            FileWriter file = new FileWriter(".settings/request.json");
            file.write(richiestaObject.toJSONString());
            file.flush();
            System.out.println("Richiesta inviata!");

        } catch (IOException e) {

            e.printStackTrace();

        } catch (ParseException e) {

            e.printStackTrace();

        }

    }

}
