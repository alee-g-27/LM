package LM;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;

public class UtenteRegistrato implements UtenteGenerico {

    private String nomeUtente;
    private String password;
    private String ID;
    private int type;

    public UtenteRegistrato(String nomeUtente, String password, String ID) {

        this.nomeUtente = nomeUtente;
        this.password = password;
        this.ID = ID;
        this.type = 1; // 1 = utente ospite

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

    public void InviaRichiesta(String Richiesta) {

        JSONArray RichiesteUtenti = new JSONArray();

        JSONObject Richieste = new JSONObject();
        Richieste.put("Nome Utente", "Ciccio");
        Richieste.put("Richiesta", Richiesta);

        JSONObject userObject = new JSONObject();
        userObject.put("richiesta", Richiesta);

        RichiesteUtenti.add(userObject);

        //Write JSON file
        try (FileWriter file = new FileWriter(".settings/request.json")) {
            file.write(RichiesteUtenti.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Richiesta inviata!");

    }

    public void NuovoUtente(String nomeUtente, String password, String ID) {

        JSONArray Utenti = new JSONArray();

        JSONObject Utente = new JSONObject();
        Utente.put("Nome Utente", nomeUtente);
        Utente.put("Password", password);
        Utente.put("ID", ID);

        JSONObject userObject = new JSONObject();
        userObject.put("utente", Utente);

        Utenti.add(userObject);

        //Write JSON file
        try (FileWriter file = new FileWriter(".settings/utenti.json")) {
            file.write(Utenti.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Utente registrato con successo!");

    }

    @Override
    public String toString() {

        return "Admin \n" + "nomeUtente = " + nomeUtente + "\npassword=" + password + "\n ID=" + ID;

    }


}
