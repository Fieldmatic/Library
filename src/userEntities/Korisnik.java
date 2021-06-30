package userEntities;

import enumerations.Pol;

import java.time.LocalDate;

public abstract class Korisnik {
    private int jmbg;
    private String ime;
    private String prezime;
    private LocalDate datumRodjenja;
    private Pol pol;
    private KorisnickiNalog nalog;

    public Korisnik() {

    }

    public Korisnik(String ime, String prezime, LocalDate datumRodjenja, int jmbg, Pol pol) {
        this.jmbg = jmbg;
        this.ime = ime;
        this.prezime = prezime;
        this.datumRodjenja = datumRodjenja;
        this.pol = pol;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public LocalDate getDatumRodjenja() {
        return datumRodjenja;
    }

    public void setDatumRodjenja(LocalDate datumRodjenja) {
        this.datumRodjenja = datumRodjenja;
    }

    public int getJmbg() {
        return jmbg;
    }

    public void setJmbg(int jmbg) {
        this.jmbg = jmbg;
    }

    public Pol getPol() {
        return pol;
    }

    public void setPol(Pol pol) {
        this.pol = pol;
    }

    public KorisnickiNalog getNalog() {
        return nalog;
    }

    public void setNalog(KorisnickiNalog nalog) {
        this.nalog = nalog;
    }

    @Override
    public String toString() {
        return "Korisnik{" +
                "ime='" + ime + '\'' +
                ", prezime='" + prezime + '\'' +
                ", datumRodjenja=" + datumRodjenja +
                ", jmbg=" + jmbg +
                ", pol=" + pol +
                '}';
    }
}
