package model;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Bosky
 */
public class Ordine {
    
    private int codice;
    private Date data;
    private ArrayList<ArticoloOrdinato> articoli;
    private Negozio negozio;

    public Ordine(int codice, Date data, ArrayList<ArticoloOrdinato> articoli, Negozio negozio) {
        this.codice = codice;
        this.data = data;
        this.articoli = articoli;
        this.negozio = negozio;
    }

    public int getCodice() {
        return codice;
    }

    public void setCodice(int codice) {
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
    
    public boolean addArticolo(ArticoloOrdinato art){
        return this.articoli.add(art);
    }
    
    public boolean removeArticolo(ArticoloOrdinato art){
        return this.articoli.remove(art);
    }
    
    public double getPrezzoTot() {
        double tot = 0;
        for(ArticoloOrdinato art : this.articoli){
            tot += (art.getPrezzo()*art.getQuantita());
        }
        return tot;
    }
    
    @Override
    public String toString(){
        return "" + this.codice + " - " + this.data.toString();
    }
}
