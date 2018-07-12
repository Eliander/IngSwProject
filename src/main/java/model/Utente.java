package model;

/**
 *
 * @author Eliander
 */
import java.util.Objects;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class Utente {

    protected String nome;
    protected String cognome;
    protected String username;
    protected int ruolo;
    protected final static Logger log = LogManager.getLogger(Utente.class);

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getRuolo() {
        return ruolo;
    }

    public void setRuolo(int ruolo) {
        this.ruolo = ruolo;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Utente){
            Utente other = (Utente)obj;
            if(this.nome.equals(other.nome)){
                if(this.cognome.equals(other.cognome)){
                    if(this.username.equals(other.username)){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    //hashcode generato, da sistemare
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + Objects.hashCode(this.username);
        return hash;
    }

    
}
