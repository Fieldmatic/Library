package userEntities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import enumerations.VrstaNaloga;

public class KorisnickiNalog {
    private String korisnickoIme;
    private String lozinka;
    private VrstaNaloga vrstaNaloga;
    @JsonBackReference
    private Korisnik korisnik;

    public KorisnickiNalog(){

    }

    public KorisnickiNalog(String korisnickoIme, String lozinka, VrstaNaloga vrstaNaloga, Korisnik korisnik) {
        this.korisnickoIme = korisnickoIme;
        this.lozinka = lozinka;
        this.vrstaNaloga = vrstaNaloga;
        this.korisnik = korisnik;
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    public String getLozinka() {
        return lozinka;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }

    public VrstaNaloga getVrstaNaloga() {
        return vrstaNaloga;
    }

    public void setVrstaNaloga(VrstaNaloga vrstaNaloga) {
        this.vrstaNaloga = vrstaNaloga;
    }

    public Korisnik getKorisnik() {
        return korisnik;
    }

    public void setKorisnik(Korisnik korisnik) {
        this.korisnik = korisnik;
    }

    @Override
    public String toString() {
        return "KorisnickiNalog{" +
                "korisnickoIme='" + korisnickoIme + '\'' +
                ", lozinka='" + lozinka + '\'' +
                ", vrstaNaloga=" + vrstaNaloga +
                ", korisnik=" + korisnik +'}';
    }

}
