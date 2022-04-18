package LM;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Admin implements UtenteGenerico{

    private String nomeUtente;
    private String password;
    private String type;

    public Admin(String nomeUtente, String password) {

        this.nomeUtente = nomeUtente;
        this.password = password;
        this.type = "admin";

    }

    @Override
    public void setNomeUtente(String nomeUtente) {

        this.nomeUtente = nomeUtente;

    }

    @Override
    public void setPassword(String password) {

        this.password = password;

    }

    @Override
    public String getNome() {

        return nomeUtente;

    }

    @Override
    public String getPassword() {

        return password;

    }

    //Write a new user in the JSON file
    public void creaUtente(String nomeUtente, String password, String type) {

        JSONObject userDetails = new JSONObject();
        userDetails.put("Nome Utente", nomeUtente);
        userDetails.put("PSW", password);
        userDetails.put("Type", type);

        JSONObject userObject = new JSONObject();
        userObject.put("user", userDetails);


        //Add employees to list
        JSONArray userList = new JSONArray();
        userList.add(userObject);

        //Write JSON file
        try (FileWriter file = new FileWriter(".settings/users.json", true)) {

            file.write(userList.toJSONString());
            file.flush();

        } catch (IOException e) {

            e.printStackTrace();

        }

    }

    //Delete a user from the JSON file
    public void eliminaUtente(String nomeUtente) throws IOException, ParseException {
        // Read the settings JSON file and load the array in memory to work with it.
        JSONParser jsonParser = new JSONParser();
        FileReader reader = new FileReader(".settings/users.json");
        JSONArray users = (JSONArray)jsonParser.parse(reader);

        for (int i = 0; i < users.size(); i++) {
            if (((JSONObject)users.get(i)).get("Nome Utente").equals(nomeUtente)) {
                users.remove(i);
            }
        }

    }

    @Override
    public String toString() {

        return "Admin \n" + "nomeUtente = " + nomeUtente + "\npassword=" + password;

    }

}
