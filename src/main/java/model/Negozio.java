package model;

/**
 *
 * @author Bosky
 */
public class Negozio {
    
    private String codiceFiscale;
    private String nome;
    private Indirizzo indirizzo;
    private Responsabile responsabile;

    public Negozio(String codiceFiscale, String nome, Indirizzo indirizzo, Responsabile responsabile) {
        this.codiceFiscale = codiceFiscale;
        this.nome = nome;
        this.indirizzo = indirizzo;
        this.responsabile = responsabile;
    }

    public String getCodiceFiscale() {
        return codiceFiscale;
    }

    public void setCodiceFiscale(String codiceFiscale) {
        this.codiceFiscale = codiceFiscale;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Indirizzo getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(Indirizzo indirizzo) {
        this.indirizzo = indirizzo;
    }

    public Responsabile getResponsabile() {
        return responsabile;
    }

    public void setResponsabile(Responsabile responsabile) {
        this.responsabile = responsabile;
    }
    
    
}
