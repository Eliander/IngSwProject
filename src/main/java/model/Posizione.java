package model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author Eliander
 */
public class Posizione {

    //bisogna trovare un nome migliore, saffale, piano, posizione?
    private int x;
    private int y;
    private int z;
    private final static Logger log = LogManager.getLogger(Posizione.class);

    public Posizione(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Posizione() {
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getZ() {
        return z;
    }

    public void setZ(int z) {
        this.z = z;
    }

    @Override
    public int hashCode() {
        return x ^ y ^ z;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Posizione) {
            Posizione other = (Posizione) obj;
            if (this.x == other.x) {
                if (this.y == other.y) {
                    if (this.z == other.z) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

}
