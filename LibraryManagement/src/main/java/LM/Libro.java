package LM;

import java.io.*;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.lang.String;

public class Libro {

    private String titolo;
    private int nPagine;
    private String CasaEditrice;
    private int AnnoUscita;
    private String lingua;
    private int nCopie;
    private String ISBN;
    private int dewey;
    private int ID; //ID del libro nel database


    public Libro (String titolo, int nPagine, String CasaEditrice, int AnnoUscita, String lingua, int nCopie, String ISBN, int dewey, int ID){

        this.titolo = titolo;
        this.nPagine = nPagine;
        this.CasaEditrice = CasaEditrice;
        this.AnnoUscita = AnnoUscita;
        this.lingua = lingua;
        this.nCopie = nCopie;
        this.ISBN = ISBN;
        this.dewey = dewey;
        this.ID = ID;

    }

    //Costruttore per copia
    public Libro (Libro libro){

        this.titolo = libro.getTitolo();
        this.nPagine = libro.getnPagine();
        this.CasaEditrice = libro.getCasaEditrice();
        this.AnnoUscita = libro.getAnnoUscita();
        this.lingua = libro.getLingua();
        this.nCopie = libro.getnCopie();
        this.ISBN = libro.getISBN();
        this.dewey = libro.getDewey();
        this.ID = libro.getID();

    }

    public String getTitolo() {

        return titolo;

    }

    public void setTitolo(String titolo) {

        this.titolo = titolo;

    }

    public int getnPagine() {

        return nPagine;

    }

    public void setnPagine(int nPagine) {

        this.nPagine = nPagine;

    }

    public String getCasaEditrice() {

        return CasaEditrice;

    }

    public void setCasaEditrice(String casaEditrice) {

        CasaEditrice = casaEditrice;

    }

    public int getAnnoUscita() {

        return AnnoUscita;

    }

    public void setAnnoUscita(int annoUscita) {

        AnnoUscita = annoUscita;

    }

    public String getLingua() {

        return lingua;

    }

    public void setLingua(String lingua) {

        this.lingua = lingua;

    }

    public int getnCopie() {

        return nCopie;

    }

    public void setnCopie(int nCopie) {

        this.nCopie = nCopie;

    }

    public String getISBN() {

        return ISBN;

    }

    public void setISBN(String ISBN) {

        this.ISBN = ISBN;

    }

    public int getDewey() {

        return dewey;

    }

    public void setDewey(int dewey) {

        this.dewey = dewey;

    }

    public int getID() {

    	return ID;

    }

    public void setID(int ID) {

    	this.ID = ID;

    }

    //Metodo per inserire un libro nel file
    public void creaLibro(Libro libro){

        //Verificare se l'utente esiste già
        try {
            String titolodaTrovare = libro.getTitolo();
            if (verificaLibro(titolodaTrovare)) {
                System.out.println("Creazione libro in corso...");
                JSONParser jsonParser = new JSONParser();
                FileReader reader = new FileReader(".settings/books.json");
                //Read JSON file
                JSONArray bookObject = (JSONArray)jsonParser.parse(reader);

                //Create JSON Object
                JSONObject book = new JSONObject();
                book.put("titolo", libro.getTitolo());
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
    public boolean verificaLibro(String titolodaTrovare) throws IOException, ParseException {

        boolean res = false;

        JSONParser jsonParser = new JSONParser();
        FileReader reader = new FileReader(".settings/users.json");
        JSONArray books = (JSONArray)jsonParser.parse(reader);

        for (int i = 0; i < books.size(); i++) {

            JSONObject book = (JSONObject) books.get(i);

            String titolo = (String) book.get("titolo");
            System.out.println("titolo: " + titolo);

            if (titolodaTrovare.equals(titolo)) {
                res = true;
            }else{
                System.out.println("Libro già esistente!");
            }

        }

        return res;

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
    
    //Metodo per visualizzare il titolo dei libri dal file JSON
    public void parseBooksObject(JSONObject book){

        String titolo = (String) book.get("titolo");
        System.out.println(titolo);

    }

    //Method that write the books in a JSON file


    @Override
    public String toString() {

        return "Libro:" +
                "\ntitolo : " + titolo +
                "\nnPagine : " + nPagine +
                "\nCasaEditrice : " + CasaEditrice +
                "\nAnnoUscita : " + AnnoUscita +
                "\nlingua : " + lingua +
                "\nnCopie : " + nCopie +
                "\nISBN : " + ISBN +
                "\ndewey : " + dewey;

    }



}