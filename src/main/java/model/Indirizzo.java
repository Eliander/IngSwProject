package model;

/**
 *
 * @author Bosky
 */
public class Indirizzo {
    
    private String citta;
    private String via;
    private int civico;

    public Indirizzo(String citta, String via, int civico) {
        this.citta = citta;
        this.via = via;
        this.civico = civico;
    }

    public String getCitta() {
        return citta;
    }

    public void setCitta(String citta) {
        this.citta = citta;
    }

    public String getVia() {
        return via;
    }

    public void setVia(String via) {
        this.via = via;
    }

    public int getCivico() {
        return civico;
    }

    public void setCivico(int civico) {
        this.civico = civico;
    }
    
    
}
