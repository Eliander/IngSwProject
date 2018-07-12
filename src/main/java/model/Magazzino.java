package model;

import java.util.ArrayList;
import java.util.Calendar;

/**
 *
 * @author Bosky
 */
public class Magazzino {
    private ArrayList<ArticoloMagazzino> articoli;
    private ArrayList<Ingresso> ingressi;
    private ArrayList<Uscita> uscite;
    private ArrayList<Statistica> statistiche;
    private Catalogo catalogo;

    public Magazzino() {
        this.articoli = new ArrayList<>();
        this.ingressi = new ArrayList<>();
        this.uscite = new ArrayList<>();
        this.statistiche = new ArrayList<>();
        this.catalogo = new Catalogo();
    }

    public Magazzino(ArrayList<ArticoloMagazzino> articoli, ArrayList<Ingresso> ingressi, ArrayList<Uscita> uscite, ArrayList<Statistica> statistiche, Catalogo catalogo) {
        this.articoli = articoli;
        this.ingressi = ingressi;
        this.uscite = uscite;
        this.statistiche = statistiche;
        this.catalogo = catalogo;
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
    
    // aggiung una uscita se gli articoli dell'uscita sono presenti nel magazzino
    public boolean addUscita(Uscita uscita) {
        //se l'uscita rispetta il rispettivo ordine
        if(uscita.checkUscita()){
            //controllo gli articoli dell'uscita siano presenti nel magazzino
            boolean correct = true;
            for(ArticoloMagazzino art : uscita.getArticoli()) {
                if(!(this.articoli.contains(art))){
                    correct = false;
                }
            }
            if(correct){
                //rimuovo gli articoli dal magazzino
                for(ArticoloMagazzino art : uscita.getArticoli()) {
                    this.articoli.remove(art);
                }
                //aggiungo l'uscita
                return this.uscite.add(uscita);
            }
            else{
                return false;
            }
        }
        return false;
    }
    
    private boolean addStatistica(Statistica sta) {
        return this.statistiche.add(sta);
    }
    
    //metodo che aggiorna le statistiche del magazzino
    public void updateStatistiche(){
        //ricavo l'ultima statistica creata
        Statistica last_sta = this.statistiche.get(0);
        for(Statistica sta : this.statistiche){
            if((sta.getAnno() > last_sta.getAnno()) || (sta.getAnno() == last_sta.getAnno() && sta.getMese() > last_sta.getMese())){
                last_sta = sta;
            }
        }
        //ricavo la data odierna
        Calendar today = Calendar.getInstance();
        int today_anno = today.get(Calendar.YEAR);
        int today_mese = today.get(Calendar.MONTH);
        //creo le statistiche per i mesi mancanti (per ogni articolo del catologo)
        for(int anno = last_sta.getAnno(); anno <= today_anno; anno++){
            for(int mese = last_sta.getMese(); mese < today_mese; mese++){
                //mese mancante
                for(Articolo art : this.catalogo.getArticoli()){
                    int quantitaIn = 0;
                    int quantitaOut = 0;
                    //per ogni articolo controllo gli ingressi
                    for(Ingresso ing : this.ingressi){
                        for(ArticoloMagazzino artmag : ing.getArticoli()){
                            if(((Articolo)artmag).equals(art)){
                                quantitaIn++;
                            }
                        }
                    }
                    //per ogni articolo controllo le uscite
                    for(Uscita usc : this.uscite){
                        for(ArticoloMagazzino artmag : usc.getArticoli()){
                            if(((Articolo)artmag).equals(art)){
                                quantitaOut++;
                            }
                        }
                    }
                    //creo la statistica per l'articolo e l'aggiungo
                    Statistica new_sta = new Statistica(art, anno, mese, quantitaIn, quantitaOut);
                    this.addStatistica(new_sta);
                }
            }
        }
    }
    
    public int getQuantitaArticolo(Articolo articolo) {
        int qty = 0;
        for(ArticoloMagazzino artmag : this.articoli){
            if(((Articolo)artmag).equals(articolo)){
                qty++;
            }
        }
        return qty;
    }
}
