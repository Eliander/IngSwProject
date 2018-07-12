package model;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Bosky
 */
public class Ordine {
    
    private String codice;
    private Date data;
    private ArrayList<ArticoloOrdinato> articoli;
    private Negozio negozio;

    public Ordine(String codice, Date data, ArrayList<ArticoloOrdinato> articoli, Negozio negozio) {
        this.codice = codice;
        this.data = data;
        this.articoli = articoli;
        this.negozio = negozio;
    }

    public String getCodice() {
        return codice;
    }

    public void setCodice(String codice) {
        this.codice = codice;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public ArrayList<ArticoloOrdinato> getArticoli() {
        return articoli;
    }

    public void setArticoli(ArrayList<ArticoloOrdinato> articoli) {
        this.articoli = articoli;
    }

    public Negozio getNegozio() {
        return negozio;
    }

    public void setNegozio(Negozio negozio) {
        this.negozio = negozio;
    }
    
    public double getPrezzoTot() {
        double tot = 0;
        for(ArticoloOrdinato art : this.articoli){
            tot += (art.getPrezzo()*art.getQuantita());
        }
        return tot;
    }
}
