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
        this.nome = nome;
        this.cognome = cognome;
        this.username = username;
        this.ruolo = 2;
    }
    
}
