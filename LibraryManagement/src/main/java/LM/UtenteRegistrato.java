package LM;

public class UtenteRegistrato implements UtenteGenerico {

    private String nomeUtente;
    private String password;
    private String type;

    public UtenteRegistrato(String nomeUtente, String password, String type) {

        this.nomeUtente = nomeUtente;
        this.password = password;
        this.type = "utente";

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


}
