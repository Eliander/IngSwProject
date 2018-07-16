package model;

import java.util.ArrayList;
import java.util.Date;

public class Ingresso {
    
    private int codice;
    private Date data;
    private ArrayList<ArticoloMagazzino> articoli;
    
    public Ingresso(int codice, Date data, ArrayList<ArticoloMagazzino> articoli) {
        this.codice = codice;
        this.data = data;
        this.articoli = articoli;
    }
    
    public int getCodice() {
        return this.codice;
    }
    
    public void setCodice(int codice) {
        this.codice = codice;
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
    
    public boolean addArticolo(ArticoloMagazzino art){
        return this.articoli.add(art);
    }
}
