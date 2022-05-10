package LM;

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

    @Override
    public String toString() {

        return "Admin \n" + "nomeUtente = " + nomeUtente + "\npassword=" + password;

    }

}
