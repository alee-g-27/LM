package LM;

import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import java.lang.String;

import static LM.JSONModifier.VerificaCredenziali;

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

}
