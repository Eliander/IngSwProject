package model;

import java.util.Objects;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author Eliander
 */
public class Tipo {

    private final static Logger log = LogManager.getLogger(Tipo.class);
    private String tipo;

    public Tipo(String tipo) {
        this.tipo = tipo;
    }

    public Tipo() {
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + Objects.hashCode(this.tipo);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Tipo) {
            Tipo other = (Tipo) obj;
            if (this.tipo.equals(other.tipo)) {
                return true;
            }
        }
        return false;
    }
}
