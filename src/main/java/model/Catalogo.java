package model;

import java.util.ArrayList;

/**
 *
 * @author Bosky
 */
public class Catalogo {
    private ArrayList<Articolo> articoli;
    
    public Catalogo() {
        this.articoli = new ArrayList<>();
    }
    
    public Catalogo(ArrayList<Articolo> articoli) {
        this.articoli = articoli;
    }
    
    public ArrayList<Articolo> getArticoli() {
        return this.articoli;
    }
    
    public void setArticoli(ArrayList<Articolo> articoli) {
        this.articoli = articoli;
    }
    
    public boolean addArticolo(Articolo articolo) {
        return this.articoli.add(articolo);
    }
    
    public boolean removeArticolo(Articolo articolo) {
        return this.articoli.remove(articolo);
    }
}
