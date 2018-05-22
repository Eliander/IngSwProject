package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author Eliander
 */
public class ArticoloMagazzino extends Articolo{

    private Posizione posizione;
    private String codice;
    private Date data;
    private final static Logger log = LogManager.getLogger(ArticoloMagazzino.class);

    public ArticoloMagazzino(Posizione posizione, String codice, Date data, Tipo tipo, String nome, String descrizione, Sport sport, ArrayList<String> materiali, double prezzo) {
        super(tipo, nome, descrizione, sport, materiali, prezzo);
        this.posizione = posizione;
        this.codice = codice;
        this.data = data;
    }

    public Posizione getPosizione() {
        return posizione;
    }

    public void setPosizione(Posizione posizione) {
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
