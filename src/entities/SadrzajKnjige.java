package entities;

import enumerations.Zanr;

import java.util.List;

public class SadrzajKnjige {
    private String naziv;
    private List<Zanr> zanrovi;

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public List<Zanr> getZanrovi() {
        return zanrovi;
    }

    public void setZanrovi(List<Zanr> zanrovi) {
        this.zanrovi = zanrovi;
    }

    @Override
    public String toString() {
        return "SadrzajKnjige{" +
                "naziv='" + naziv + '\'' +
                ", zanrovi=" + zanrovi +
                '}';
    }
}