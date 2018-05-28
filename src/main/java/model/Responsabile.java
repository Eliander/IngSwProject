package model;

/**
 *
 * @author Eliander
 */
public class Responsabile extends Utente{

    public Responsabile() {
    }

    public Responsabile(String nome, String cognome, String username) {
        super(nome, cognome, username, 1);
    }

}
