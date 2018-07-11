package model;

import java.util.ArrayList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author Eliander
 */
public class Articolo {
    
    private String nome;
    private String descrizione;
    private String sport;
    private String categoria;
    private ArrayList<String> materiali;
    private double prezzo;
    private final static Logger log = LogManager.getLogger(Articolo.class);
    
    public Articolo(String nome, String descrizione, String sport, String categoria, ArrayList<String> materiali, double prezzo) {
        this.nome = nome;
        this.descrizione = descrizione;
        this.sport = sport;
        this.categoria = categoria;
        this.materiali = materiali;
        this.prezzo = prezzo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public String getSport() {
        return sport;
    }

    public void setSport(String sport) {
        this.sport = sport;
    }
    
    public String getCategoria() {
        return categoria;
    }
    
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public ArrayList<String> getMateriali() {
        return materiali;
    }

    public void setMateriali(ArrayList<String> materiali) {
        this.materiali = materiali;
    }

    public double getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(double prezzo) {
        this.prezzo = prezzo;
    }

    @Override
    public int hashCode() {
        int hashMateriali = 0;
        for(String materiale : materiali){
            hashMateriali += materiale.hashCode();
        }
        return categoria.hashCode() ^ nome.hashCode() ^ descrizione.hashCode() ^ hashMateriali;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Articolo){
            Articolo other = (Articolo)obj;
            if(this.nome.equals(other.getNome())){
                if(this.categoria.equals(other.getCategoria())){
                    if(this.descrizione.equals(other.getDescrizione())){
                        if(this.materiali.equals(other.getMateriali())){
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
    
}
