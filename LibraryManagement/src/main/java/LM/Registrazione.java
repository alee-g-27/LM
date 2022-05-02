package LM;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.lang.String;


public class Registrazione {

    protected String NomeUtente;
    protected String PSW;
    protected String tipoUtente;

    public Registrazione(String NomeUtente, String PSW, String tipoUtente){
        this.NomeUtente = NomeUtente;
        this.PSW = PSW;
        this.tipoUtente = tipoUtente;

        creaUtente(NomeUtente, PSW, tipoUtente);

    }

    //metodi
    public String getNomeUtente(){
        return this.NomeUtente;
    }

    public String getPSW(){
        return this.PSW;
    }

    //Metodo per creare l'utente
    public void creaUtente(String NomeUtente, String PSW, String tipoUtente){

        //Verificare se l'utente esiste già
        try {

            if (!verificaUtente(NomeUtente)) {
                System.out.println("Registrazione in corso...");
                //JSON parser object to parse read file
                JSONParser jsonParser = new JSONParser();
                FileReader reader = new FileReader("../LM/.settings/users.json");
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
                FileWriter file = new FileWriter("../LM/.settings/users.json");
                file.write(usersObject.toJSONString());
                file.flush();
                System.out.println("Registrazione completata!");

            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    //Metodo per verificare se l'utente esiste già
    public boolean verificaUtente(String NomeUtente) throws IOException, ParseException {

        boolean res = false;

        //JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();
        FileReader reader = new FileReader("../LM/.settings/users.json");
        //Read JSON file
        JSONArray usersObject = (JSONArray)jsonParser.parse(reader);
        //JSONArray  = (JSONArray) obj;

        for (int i = 0; i < usersObject.size(); i++) {

            JSONObject user = (JSONObject) usersObject.get(i);

            String nomeUtente = (String) user.get("Nome Utente");
            System.out.println("Nome Utente: " + nomeUtente);

            if (NomeUtente.equals(nomeUtente)) {
                res = true;
                System.out.println("Utente già esistente!");
            }

        }

        return res;

    }

}
