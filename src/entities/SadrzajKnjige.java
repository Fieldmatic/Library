package entities;

import enumerations.Zanr;

import java.util.ArrayList;
import java.util.List;

public class SadrzajKnjige {
    private String naziv;
    private List<Zanr> zanrovi  = new ArrayList<Zanr>();

    public SadrzajKnjige() {}

    public SadrzajKnjige(String naziv, List<Zanr> zanrovi) {
        this.naziv = naziv;
        this.zanrovi = zanrovi;
    }

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

    public String zanroviToString() {
        String zanroviString = "";
        for (Zanr zanr : this.zanrovi) {
            zanroviString += zanr + ",";
        }
        return zanroviString.substring(0, zanroviString.length() - 1);
    }

    @Override
    public String toString() {
        return "SadrzajKnjige{" +
                "naziv='" + naziv + '\'' +
                ", zanrovi=" + zanrovi +
                '}';
    }
}
