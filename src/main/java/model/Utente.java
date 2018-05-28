package model;

/**
 *
 * @author Eliander
 */
import java.security.MessageDigest;
import java.util.Objects;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class Utente {

    private String nome;
    private String cognome;
    private String username;
    private String password;
    private int ruolo;
    private final static Logger log = LogManager.getLogger(Utente.class);

    public Utente() {
    }
    
    public Utente(String nome, String cognome, String username, int ruolo){
        this.nome = nome;
        this.cognome = cognome;
        this.username = username;
        this.ruolo = ruolo;
    }
    
    public Utente(String nome, String cognome, String username, int ruolo, String password) {
        this.nome = nome;
        this.cognome = cognome;
        this.username = username;
        this.ruolo = ruolo;
        //creo un MessageDigest per poter cifrare la password
        MessageDigest messageDigest;
        try {
            //se non trova l'algoritmo md5 va in catch
            messageDigest = MessageDigest.getInstance("MD5");
            byte[] digest = messageDigest.digest(password.getBytes());
            //setto la nuova password sull'utente
            this.password = new String(digest);
        } catch (Exception ex) {
            log.error(ex);
        }
    }

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
