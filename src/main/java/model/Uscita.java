package model;

import java.util.ArrayList;
import java.util.Date;

public class Uscita {
    private int numBolla;
    private Date data;
    private ArrayList<ArticoloMagazzino> articoli;
    private Ordine ordine;
    private Spedizioniere spedizioniere;
    
    public Uscita(int numBolla, Date data, ArrayList<ArticoloMagazzino> articoli, Ordine ordine, Spedizioniere spedizioniere) {
        this.numBolla = numBolla;
        this.data = data;
        this.articoli = articoli;
        this.ordine = ordine;
        this.spedizioniere = spedizioniere;
    }
    
    public Uscita(Date data, ArrayList<ArticoloMagazzino> articoli, Ordine ordine, Spedizioniere spedizioniere) {
        this.numBolla = 0;
        this.data = data;
        this.articoli = articoli;
        this.ordine = ordine;
        this.spedizioniere = spedizioniere;
    }
    
    public int getNumBolla() {
        return this.numBolla;
    }
    
    public void setNumBolla(int numBolla) {
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
    
    public boolean addArticolo(ArticoloMagazzino art){
        return this.articoli.add(art);
    }
    
    //controlla che gli articoli in uscita rispecchiano gli articoli dell'ordine e le quantita
    public boolean checkUscita() {
        for(ArticoloOrdinato artord : this.ordine.getArticoli()){
            int qty = artord.getQuantita();
            Articolo art1 = new Articolo(artord);
            for(ArticoloMagazzino artmag : this.articoli){
                Articolo art2 = new Articolo(artmag);
                if(art2.equals(art1)){
                    qty--;
                }
            }
            if(qty != 0){
                return false;
            }
        }
        return true;
    }
    
    @Override
    public String toString(){
        return this.numBolla + " - " + this.data.toString() + " - Ordine " + this.ordine.getCodice() + " - " + this.ordine.getNegozio().getNome();
    }
}
