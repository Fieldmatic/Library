package userEntities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import enumerations.Pol;
import localDateJson.LocalDateDeserializer;
import localDateJson.LocalDateSerializer;

import java.time.LocalDate;

public abstract class Korisnik {
    private String jmbg;
    private String ime;
    private String prezime;
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate datumRodjenja;
    private Pol pol;
    @JsonManagedReference
    private KorisnickiNalog nalog;

    public Korisnik() {}

    public Korisnik(String ime, String prezime, LocalDate datumRodjenja, String jmbg, Pol pol) {
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

    public String getJmbg() {
        return jmbg;
    }

    public void setJmbg(String jmbg) {
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
