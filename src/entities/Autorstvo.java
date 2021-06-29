package entities;

import enumerations.UlogaAutora;

public class Autorstvo {
    private Autor autor;
    private UlogaAutora uloga;

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public UlogaAutora getUloga() {
        return uloga;
    }

    public void setUloga(UlogaAutora uloga) {
        this.uloga = uloga;
    }
}
