package LM;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

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
                    book.put("ID", libro.getID());

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

    //Elimina libro
    public static void eliminaLibro(int IDdaTrovare) throws IOException, ParseException {
        // Read the settings JSON file and load the array in memory to work with it.
        JSONParser jsonParser = new JSONParser();
        FileReader reader = new FileReader(".settings/books.json");
        JSONArray books = (JSONArray)jsonParser.parse(reader);

        for (int i = 0; i < books.size(); i++) {

            /*if (((JSONObject)books.get(i)).get("ID").equals(IDdaTrovare)) {

                System.out.println(i);

                books.remove(i);

            }*/
            System.out.println("ID: " + ((JSONObject)books.get(i)).get("ID"));
            System.out.println("ID da trovare: " + IDdaTrovare);
            //if the ID is found, remove it from the array
            if (!((JSONObject)books.get(i)).get("ID").equals(IDdaTrovare)) {

                books.remove(i);

            }else{

                System.out.println("Libro non trovato!");

            }

        }

    }

    //Metodo per visualizzare il titolo dei libri dal file JSON
    public void parseBooksObject(JSONObject book){

        String titolo = (String) book.get("titolo");
        System.out.println(titolo);

    }

    public void showBooks(){

        //JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader("./.settings/books.json")){

            //Read JSON file
            Object obj = jsonParser.parse(reader);

            JSONArray booksList = (JSONArray) obj;

            booksList.forEach( user -> parseBooksObject( (JSONObject) user ) );



        } catch (FileNotFoundException fnfe) {

            fnfe.printStackTrace();

        } catch (IOException ioe) {

            ioe.printStackTrace();

        } catch (ParseException pe) {

            pe.printStackTrace();

        }

    }

    //----------------------------------------------------
    //OPERAZIONI SULL'ACCOUNT
    //----------------------------------------------------

    //Metodo per creare l'utente
    public static void creaUtente(String NomeUtente, String PSW, String tipoUtente){

        //Verificare se l'utente esiste già
        try {

            if (verificaUtenteEsistente(NomeUtente) == false) {
                System.out.println("Registrazione in corso...");
                //JSON parser object to parse read file
                JSONParser jsonParser = new JSONParser();
                FileReader reader = new FileReader(".settings/users.json");
                //Read JSON file
                JSONArray usersObject = (JSONArray)jsonParser.parse(reader);
                //JSONArray  = (JSONArray) obj;

                //Create JSON Object
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

            }else {

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

        //JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();
        FileReader reader = new FileReader(".settings/users.json");
        //Read JSON file
        JSONArray usersObject = (JSONArray)jsonParser.parse(reader);
        //JSONArray  = (JSONArray) obj;

        for (int i = 0; i < usersObject.size(); i++) {

            JSONObject user = (JSONObject) usersObject.get(i);

            //Get user name
            String psw = (String) user.get("PSW");

            //Get user password
            String nomeUtente = (String) user.get("Nome Utente");

            //Get user type
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

        System.out.println("Verifica delle credenziali in corso...");

        if (res) {

            System.out.println("Accesso riuscito!");

        }else{

            System.out.println("Accesso non riuscito!");

        }

    }

    //Metodo per verificare se l'utente esiste già
    public static boolean verificaUtenteEsistente(String NomeUtente) throws IOException, ParseException {

        boolean res = false;

        //JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();
        FileReader reader = new FileReader(".settings/users.json");
        //Read JSON file
        JSONArray usersObject = (JSONArray)jsonParser.parse(reader);
        //JSONArray  = (JSONArray) obj;

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

    //Delete a user from the JSON file
    public static void eliminaUtente(String nomeUtente) throws IOException, ParseException {

        JSONParser parser = new JSONParser();
        FileReader reader = new FileReader(".settings/users.json");
        JSONArray users = (JSONArray) parser.parse(reader);

        if (verificaUtenteEsistente(nomeUtente)) {

            for (int i = 0; i < users.size(); i++) {

                JSONObject user = (JSONObject) users.get(i);
                String userName = (String) user.get("Nome Utente");

                if (userName.equals(nomeUtente)) {

                    users.remove(i);
                    System.out.println("Utente eliminato");

                }

            }

            FileWriter file = new FileWriter(".settings/users.json");
            file.write(users.toJSONString());
            file.flush();

        }else {

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
                //JSON parser object to parse read file
                JSONParser jsonParser = new JSONParser();
                FileReader reader = new FileReader(".settings/request.json");
                //Read JSON file
                JSONArray richiestaObject = (JSONArray)jsonParser.parse(reader);
                //JSONArray  = (JSONArray) obj;

                //Create JSON Object
                JSONObject richiesta = new JSONObject();
                richiesta.put("Nome Utente", NomeUtente);
                richiesta.put("Richiesta", Richiesta);

                //Add JSON Object to JSON Array
                richiestaObject.add(richiesta);

                //Write JSON file
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
