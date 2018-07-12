package model;

import java.util.ArrayList;

/**
 *
 * @author Bosky
 */
public class Magazzino {
    private ArrayList<ArticoloMagazzino> articoli;
    private ArrayList<Ingresso> ingressi;
    private ArrayList<Uscita> uscite;
    private ArrayList<Statistica> statistiche;

    public Magazzino() {
        this.articoli = new ArrayList<>();
        this.ingressi = new ArrayList<>();
        this.uscite = new ArrayList<>();
        this.statistiche = new ArrayList<>();
    }

    public Magazzino(ArrayList<ArticoloMagazzino> articoli, ArrayList<Ingresso> ingressi, ArrayList<Uscita> uscite, ArrayList<Statistica> statistiche) {
        this.articoli = articoli;
        this.ingressi = ingressi;
        this.uscite = uscite;
        this.statistiche = statistiche;
    }

    public ArrayList<ArticoloMagazzino> getArticoli() {
        return articoli;
    }

    public void setArticoli(ArrayList<ArticoloMagazzino> articoli) {
        this.articoli = articoli;
    }

    public ArrayList<Ingresso> getIngressi() {
        return ingressi;
    }

    public void setIngressi(ArrayList<Ingresso> ingressi) {
        this.ingressi = ingressi;
    }

    public ArrayList<Uscita> getUscite() {
        return uscite;
    }

    public void setUscite(ArrayList<Uscita> uscite) {
        this.uscite = uscite;
    }

    public ArrayList<Statistica> getStatistiche() {
        return statistiche;
    }

    public void setStatistiche(ArrayList<Statistica> statistiche) {
        this.statistiche = statistiche;
    }
    
    public boolean addArticolo(ArticoloMagazzino articolo) {
        return this.articoli.add(articolo);
    }
    
    public boolean removeArticolo(ArticoloMagazzino articolo) {
        return this.articoli.remove(articolo);
    }
    
    public boolean addIngresso(Ingresso ingresso) {
        for(ArticoloMagazzino art : ingresso.getArticoli()) {
            boolean success = this.addArticolo(art);
            if(!success){
                return false;
            }
        }
        return this.ingressi.add(ingresso);
    }
    
    // aggiungere addUscita con controllo se le quantita degli articoli ordinati siano presenti nel magazzino
}
