package model;

public class Spedizioniere {
    
    private String nome;
    private Indirizzo indirizzo;
    private String numeroTelefono;
    
    public Spedizioniere(String nome, Indirizzo indirizzo, String numeroTelefono) {
        this.nome = nome;
        this.indirizzo = indirizzo;
        this.numeroTelefono = numeroTelefono;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Indirizzo getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(Indirizzo indirizzo) {
        this.indirizzo = indirizzo;
    }

    public String getNumeroTelefono() {
        return numeroTelefono;
    }

    public void setNumeroTelefono(String numeroTelefono) {
        this.numeroTelefono = numeroTelefono;
    }
    
    @Override
    public String toString(){
        return this.nome + ", " + this.numeroTelefono;
    }
}
