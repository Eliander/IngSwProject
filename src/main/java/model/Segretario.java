package model;

/**
 *
 * @author Eliander
 */
public class Segretario extends Utente{
    
    public Segretario() {
    }

    public Segretario(String nome, String cognome, String username) {
        //il ruolo e cablato
        super(nome, cognome, username, 0);
    }
    
}
