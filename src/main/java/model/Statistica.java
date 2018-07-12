package model;

/**
 *
 * @author Bosky
 */
public class Statistica {
    
    private Articolo articolo;
    private int anno;
    private int mese;
    private int quantitaIn;
    private int quantitaOut;

    public Statistica(Articolo articolo, int anno, int mese, int quantitaIn, int quantitaOut) {
        this.articolo = articolo;
        this.anno = anno;
        this.mese = mese;
        this.quantitaIn = quantitaIn;
        this.quantitaOut = quantitaOut;
    }

    public Articolo getArticolo() {
        return articolo;
    }

    public void setArticolo(Articolo articolo) {
        this.articolo = articolo;
    }

    public int getAnno() {
        return anno;
    }

    public void setAnno(int anno) {
        this.anno = anno;
    }

    public int getMese() {
        return mese;
    }

    public void setMese(int mese) {
        this.mese = mese;
    }

    public int getQuantitaIn() {
        return quantitaIn;
    }

    public void setQuantitaIn(int quantitaIn) {
        this.quantitaIn = quantitaIn;
    }

    public int getQuantitaOut() {
        return quantitaOut;
    }

    public void setQuantitaOut(int quantitaOut) {
        this.quantitaOut = quantitaOut;
    }
    
}
