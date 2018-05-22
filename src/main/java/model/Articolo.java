package model;

import java.util.ArrayList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author Eliander
 */
public class Articolo {
    
    private Tipo tipo;
    private String nome;
    private String descrizione;
    //che sia il caso di inserire una classe sport?
    private Sport sport;
    private ArrayList<String> materiali;
    private double prezzo;
    private final static Logger log = LogManager.getLogger(Articolo.class);

    public Articolo() {
        this.tipo = new Tipo();
        this.materiali = new ArrayList();
    }
    
    public Articolo(Tipo tipo, String nome, String descrizione, Sport sport, ArrayList<String> materiali, double prezzo) {
        this.tipo = tipo;
        this.nome = nome;
        this.descrizione = descrizione;
        this.sport = sport;
        this.materiali = materiali;
        this.prezzo = prezzo;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
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

    public Sport getSport() {
        return sport;
    }

    public void setSport(Sport sport) {
        this.sport = sport;
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
        return tipo.hashCode() ^ nome.hashCode() ^ descrizione.hashCode() ^ hashMateriali;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Articolo){
            Articolo other = (Articolo)obj;
            if(this.nome.equals(other.nome)){
                if(this.tipo.equals(other.tipo)){
                    if(this.descrizione.equals(other.descrizione)){
                        if(this.materiali.equals(other.materiali)){
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
    
}
