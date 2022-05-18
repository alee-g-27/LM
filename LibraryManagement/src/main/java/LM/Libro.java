package LM;

import java.lang.String;

public class Libro {

    private String titolo;
    private String autore;
    private int nPagine;
    private String CasaEditrice;
    private int AnnoUscita;
    private String lingua;
    private int nCopie;
    private String ISBN;
    private int dewey;
    private int ID; //ID del libro nel database


    public Libro (String titolo, String autore, int nPagine, String CasaEditrice, int AnnoUscita, String lingua, int nCopie, String ISBN, int dewey){

        this.titolo = titolo;
        this.autore = autore;
        this.nPagine = nPagine;
        this.CasaEditrice = CasaEditrice;
        this.AnnoUscita = AnnoUscita;
        this.lingua = lingua;
        this.nCopie = nCopie;
        this.ISBN = ISBN;
        this.dewey = dewey;

    }

    //Costruttore per copia
    public Libro (Libro libro){

        this.titolo = libro.getTitolo();
        this.autore = libro.getAutore();
        this.nPagine = libro.getnPagine();
        this.CasaEditrice = libro.getCasaEditrice();
        this.AnnoUscita = libro.getAnnoUscita();
        this.lingua = libro.getLingua();
        this.nCopie = libro.getnCopie();
        this.ISBN = libro.getISBN();
        this.dewey = libro.getDewey();
        this.ID = libro.getID();

    }

    public String getAutore() {

        return autore;

    }

    private String setAutore() {

        return autore;

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

}