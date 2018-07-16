package model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Posizione {

    //Per semplicita, saffale, piano e assumiamo che un piano puo contenere un oggetto
    private int scaffale;
    private int ripiano;
    private final static Logger log = LogManager.getLogger(Posizione.class);

    public Posizione(int x, int y) {
        this.scaffale = x;
        this.ripiano = y;
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

    @Override
    public int hashCode() {
        return scaffale ^ ripiano;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Posizione) {
            Posizione other = (Posizione) obj;
            if (this.scaffale == other.scaffale) {
                if (this.ripiano == other.ripiano) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public String toString(){
        return "scaffale n° " + scaffale + ", ripiano n° " + ripiano;
    }
}
