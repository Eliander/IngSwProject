package model;

import java.util.Objects;

/**
 *
 * @author Eliander
 */
public class Sport {
    
    private String nome;
    private String descrizione;

    public Sport(String nome, String descrizione) {
        this.nome = nome;
        this.descrizione = descrizione;
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

    @Override
    public int hashCode() {
        return nome.hashCode() ^ descrizione.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Sport){
            Sport other = (Sport)obj;
            if(other.nome.equals(this.nome)){
                if(other.descrizione.equals(this.descrizione)){
                    return true;
                }
            }
        }
        return false;
    }
    
    
    
}
