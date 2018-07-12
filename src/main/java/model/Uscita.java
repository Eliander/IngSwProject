package model;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Bosky
 */
public class Uscita {
    private String numBolla;
    private Date data;
    private ArrayList<ArticoloMagazzino> articoli;
    private Ordine ordine;
    private Spedizioniere spedizioniere;
    
    public Uscita(String numBolla, Date data, ArrayList<ArticoloMagazzino> articoli, Ordine ordine, Spedizioniere spedizioniere) {
        this.numBolla = numBolla;
        this.data = data;
        this.articoli = articoli;
        this.ordine = ordine;
        this.spedizioniere = spedizioniere;
    }
    
    public String getNumBolla() {
        return this.numBolla;
    }
    
    public void setNumBolla(String numBolla) {
        this.numBolla = numBolla;
    }
    
    public Date getData() {
        return this.data;
    }
    
    public void setData(Date data) {
        this.data = data;
    }
    
    public ArrayList<ArticoloMagazzino> getArticoli() {
        return this.articoli;
    }
    
    public void setArticoli(ArrayList<ArticoloMagazzino> articoli) {
        this.articoli = articoli;
    }
    
    public Ordine getOrdine() {
        return this.ordine;
    }
    
    public void setOrdine(Ordine ordine) {
        this.ordine = ordine;
    }
    
    public Spedizioniere getSpedizioniere() {
        return this.spedizioniere;
    }
    
    public void setSpedizioniere(Spedizioniere spedizioniere) {
        this.spedizioniere = spedizioniere;
    }
}
