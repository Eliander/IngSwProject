package model;

/**
 *
 * @author Eliander
 */
public class Magazziniere extends Utente{

    public Magazziniere() {
    }

    public Magazziniere(String nome, String cognome, String username) {
        //il ruolo e cablato
        super(nome, cognome, username, 2);
    }
    
}
