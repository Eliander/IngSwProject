package model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author Eliander
 */
public class Posizione {

    //bisogna trovare un nome migliore, saffale, piano, posizione?
    private int scaffale;
    private int ripiano;
    private int posizione;
    private final static Logger log = LogManager.getLogger(Posizione.class);

    public Posizione(int x, int y, int z) {
        this.scaffale = x;
        this.ripiano = y;
        this.posizione = z;
    }

    public Posizione() {
    }

    public int getScaffale() {
        return scaffale;
    }

    public void setScaffale(int scaffale) {
        this.scaffale = scaffale;
    }

    public int getRipiano() {
        return ripiano;
    }

    public void setRipiano(int ripiano) {
        this.ripiano = ripiano;
    }

    public int getPosizione() {
        return posizione;
    }

    public void setPosizione(int posizione) {
        this.posizione = posizione;
    }

    @Override
    public int hashCode() {
        return scaffale ^ ripiano ^ posizione;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Posizione) {
            Posizione other = (Posizione) obj;
            if (this.scaffale == other.scaffale) {
                if (this.ripiano == other.ripiano) {
                    if (this.posizione == other.posizione) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

}
