package model;

import java.util.ArrayList;

/**
 *
 * @author Bosky
 */
public class ArticoloOrdinato extends Articolo{
    
    private int quantita;
    
    public ArticoloOrdinato(String nome, String descrizione, String sport, String categoria, ArrayList<String> materiali, double prezzo, int quantita) {
        super(nome, descrizione, sport, categoria, materiali, prezzo);
        this.quantita = quantita;
    }
    
    public ArticoloOrdinato(Articolo articolo, int quantita) {
        super(articolo.getNome(), articolo.getDescrizione(), articolo.getSport(), articolo.getCategoria(), articolo.getMateriali(), articolo.getPrezzo());
        this.quantita = quantita;
    }
    
    public int getQuantita() {
        return quantita;
    }
    
    public void setQuantita(int quantita) {
        this.quantita = quantita;
    }
    
    @Override
    public String toString(){
        return nome + ", " + descrizione + ", " + sport + ", " + categoria + ", " + prezzo + " euro, " + quantita + " pezzi";
    }
}
