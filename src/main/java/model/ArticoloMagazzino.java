package model;

import java.util.ArrayList;
import java.util.Date;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author Eliander
 */
public class ArticoloMagazzino extends Articolo{

    private int codice;
    private Date data;
    private Posizione posizione;
    private final static Logger log = LogManager.getLogger(ArticoloMagazzino.class);

    public ArticoloMagazzino(String nome, String descrizione, String sport, String categoria, ArrayList<String> materiali, double prezzo, int codice, Date data, Posizione posizione) {
        super(nome, descrizione, sport, categoria, materiali, prezzo);
        this.codice = codice;
        this.data = data;
        this.posizione = posizione;
    }
    
    public ArticoloMagazzino(Articolo articolo, int codice, Date data, Posizione posizione) {
        super(articolo.getNome(), articolo.getDescrizione(), articolo.getSport(), articolo.getCategoria(), articolo.getMateriali(), articolo.getPrezzo());
        this.codice = codice;
        this.data = data;
        this.posizione = posizione;
    }
    
    public ArticoloMagazzino(Articolo articolo, int codice, Date data, int x, int y) {
        super(articolo.getNome(), articolo.getDescrizione(), articolo.getSport(), articolo.getCategoria(), articolo.getMateriali(), articolo.getPrezzo());
        this.codice = codice;
        this.data = data;
        this.posizione = new Posizione(x, y);
    }

    public int getCodice() {
        return codice;
    }

    public void setCodice(int codice) {
        this.codice = codice;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }
    
    public Posizione getPosizione() {
        return posizione;
    }

    public void setPosizione(Posizione posizione) {
        this.posizione = posizione;
    }    

    @Override
    public int hashCode() {
        return codice ^ data.hashCode() ^ posizione.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ArticoloMagazzino){
            ArticoloMagazzino other = (ArticoloMagazzino)obj;
            if(other.codice == (this.codice)){
                if(other.posizione.equals(this.posizione)){
                    if(other.data.equals(this.data)){
                        return true && super.equals(obj);
                    }
                }
            }
        }
        return false;
    }
    
    
    
}
