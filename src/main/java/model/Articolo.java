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
    private String sport;
    private ArrayList<String> materiali;
    private double prezzo;
    private final static Logger log = LogManager.getLogger(Articolo.class);

    public Articolo() {
        this.tipo = new Tipo();
        this.materiali = new ArrayList();
    }
    
    public Articolo(Tipo tipo, String nome, String descrizione, String sport, ArrayList<String> materiali, double prezzo) {
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

    public String getSport() {
        return sport;
    }

    public void setSport(String sport) {
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
    
    
}
