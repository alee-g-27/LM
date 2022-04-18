package LM;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.lang.String;

public class Login extends JSONObject{

    protected String NomeUtente;
    protected String PSW;
    protected String tipoUtente;

    //Costruttore
    public Login(String NomeUtente, String PSW, String tipoUtente) throws IOException, ParseException {

        this.NomeUtente = NomeUtente;
        this.PSW = PSW;
        this.tipoUtente = tipoUtente;
        VerificaCredenziali(this.NomeUtente, this.PSW, this.tipoUtente);

    }

    //Metodi
    public String getNomeUtente() {

        return NomeUtente;

    }


    public String getPSW() {

        return PSW;

    }

    public String getTipoUtente() {

        return tipoUtente;

    }

    //Metodo per leggere il file json DA METTERE ANCHE PER I LIBRI
    public boolean userReader(String NomeUtente, String PSW, String tipoUtente) throws IOException, ParseException {

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

            System.out.println("PSW: " + psw);

            //Get user password
            String nomeUtente = (String) user.get("Nome Utente");

            System.out.println("Nome Utente: " + nomeUtente);
            //Get user type
            String type = (String) user.get("Tipoutente");

            System.out.println("type: " + type);

            if (NomeUtente.equals(nomeUtente) && PSW.equals(psw) && tipoUtente.equals(type)) {
                res = true;
            }

        }

        return res;

    }

    public void VerificaCredenziali(String NomeUtente, String PSW, String tipoUtente) throws IOException, ParseException {

        boolean res = false;

        res = userReader(NomeUtente, PSW, tipoUtente);

        System.out.println("Verifica delle credenziali in corso...");

        if (res) {

            System.out.println("Accesso riuscito!");

        }else{

            System.out.println("Accesso non riuscito!");

        }

    }

}
