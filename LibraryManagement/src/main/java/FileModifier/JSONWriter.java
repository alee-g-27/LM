package FileModifier;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JSONWriter
{
    @SuppressWarnings("unchecked")
    public static void main( String[] args ) throws IOException, ParseException {

        // Read the settings JSON file and load the array in memory to work with it.
        JSONParser jsonParser = new JSONParser();
        FileReader reader = new FileReader("./src/main/resources/users.json");
        JSONArray users = (JSONArray)jsonParser.parse(reader);

        // Create a new user to add to the array.
        JSONObject user = new JSONObject();
        user.put("Nome Utente", "Nome Utente");
        user.put("PSW", "qwerty");
        user.put("Tipoutente", "admin");

        users.add(user);
        // Add the new user to the array.

        /*for (int i = 0; i < users.size(); i++) {
            if (((JSONObject)users.get(i)).get("Nome Utente") == "mammamia") {
                System.out.println("utente presente");
            }
        }*/

        //Write JSON file
        try (FileWriter file = new FileWriter("src/main/resources/users.json", false)){

            file.write(users.toJSONString());
            file.flush();

        } catch (IOException e) {

            e.printStackTrace();

        }

    }

}

