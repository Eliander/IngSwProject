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

    private String codice;
    private Date data;
    private Posizione posizione;
    private final static Logger log = LogManager.getLogger(ArticoloMagazzino.class);

    public ArticoloMagazzino(String nome, String descrizione, String sport, String categoria, ArrayList<String> materiali, double prezzo, String codice, Date data, Posizione posizione) {
        super(nome, descrizione, sport, categoria, materiali, prezzo);
        this.codice = codice;
        this.data = data;
        this.posizione = posizione;
    }
    
    public ArticoloMagazzino(Articolo articolo, String codice, Date data, Posizione posizione) {
        super(articolo.getNome(), articolo.getDescrizione(), articolo.getSport(), articolo.getCategoria(), articolo.getMateriali(), articolo.getPrezzo());
        this.codice = codice;
        this.data = data;
        this.posizione = posizione;
    }

    public String getCodice() {
        return codice;
    }

    public void setCodice(String codice) {
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
        return codice.hashCode() ^ data.hashCode() ^ posizione.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ArticoloMagazzino){
            ArticoloMagazzino other = (ArticoloMagazzino)obj;
            if(other.codice.equals(this.codice)){
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
