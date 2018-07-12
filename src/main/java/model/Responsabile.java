package model;

/**
 *
 * @author Eliander
 */
public class Responsabile extends Utente{

    public Responsabile() {
    }

    public Responsabile(String nome, String cognome, String username) {
        this.nome = nome;
        this.cognome = cognome;
        this.username = username;
        this.ruolo = 1;
    }

}
