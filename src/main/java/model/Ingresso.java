package model;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Bosky
 */
public class Ingresso {
    
    private String codice;
    private Date data;
    private ArrayList<ArticoloMagazzino> articoli;
    
    public Ingresso(String codice, Date data, ArrayList<ArticoloMagazzino> articoli) {
        this.codice = codice;
        this.data = data;
        this.articoli = articoli;
    }
    
    public String getCodice() {
        return this.codice;
    }
    
    public void setCodice(String codice) {
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
}
