package FileModifier;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JSONReader
{
    @SuppressWarnings("unchecked")
    public static void main(String[] args)
    {

    }

    private static void parseUserObject(JSONObject user)
    {
        //Get employee object within list
        JSONObject employeeObject = (JSONObject) user.get("users");

        //Get employee first name
        String nomeUtente = (String) employeeObject.get("NomeUtente");
        System.out.println(nomeUtente);

        //Get employee last name
        String psw = (String) employeeObject.get("PSW");
        System.out.println(psw);

    }
}
