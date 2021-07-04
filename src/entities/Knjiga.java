package entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import localDateJson.LocalDateDeserializer;
import localDateJson.LocalDateSerializer;

import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Knjiga {
    private int id;
    private String naziv;
    private Dimension format;
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate datumIzdanja;
    private boolean iznosiSe;
    private String izdavac;
    private SadrzajKnjige sadrzaj;
    private List<Recenzija> recenzije = new ArrayList<Recenzija>();
    private List<String> tagovi = new ArrayList<String>();
    @JsonManagedReference
    private List<PrimerakKnjige> primerci = new ArrayList<PrimerakKnjige>();
    private List<Autorstvo> autori = new ArrayList<Autorstvo>();

    public Knjiga() {}

    public Knjiga(int id, String naziv, Dimension format, LocalDate datumIzdanja, boolean iznosiSe, String izdavac, SadrzajKnjige sadrzaj, List<String> tagovi, List<Autorstvo> autori) {
        this.id = id;
        this.naziv = naziv;
        this.format = format;
        this.datumIzdanja = datumIzdanja;
        this.iznosiSe = iznosiSe;
        this.izdavac = izdavac;
        this.sadrzaj = sadrzaj;
        this.tagovi = tagovi;
        this.autori = autori;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public LocalDate getDatumIzdanja() {
        return datumIzdanja;
    }

    public void setDatumIzdanja(LocalDate datumIzdanja) {
        this.datumIzdanja = datumIzdanja;
    }

    public boolean isIznosiSe() {
        return iznosiSe;
    }

    public void setIznosiSe(boolean iznosiSe) {
        this.iznosiSe = iznosiSe;
    }

    public List<String> getTagovi() {
        return tagovi;
    }

    public void setTagovi(List<String> tagovi) {
        this.tagovi = tagovi;
    }

    public String getIzdavac() {
        return izdavac;
    }

    public void setIzdavac(String izdavac) {
        this.izdavac = izdavac;
    }

    public List<PrimerakKnjige> getPrimerci() {
        return primerci;
    }

    public void setPrimerci(List<PrimerakKnjige> primerci) {
        this.primerci = primerci;
    }

    public SadrzajKnjige getSadrzaj() {
        return sadrzaj;
    }

    public void setSadrzaj(SadrzajKnjige sadrzaj) {
        this.sadrzaj = sadrzaj;
    }

    public List<Autorstvo> getAutori() {
        return autori;
    }

    public void setAutori(List<Autorstvo> autori) {
        this.autori = autori;
    }

    public List<Recenzija> getRecenzije() {
        return recenzije;
    }

    public void setRecenzije(List<Recenzija> recenzije) {
        this.recenzije = recenzije;
    }

    public Dimension getFormat() {
        return format;
    }

    public void setFormat(Dimension format) {
        this.format = format;
    }

    public int getProsecnaOcena() {
        int avgOcena = 0;
            for (Recenzija r : this.getRecenzije())
                avgOcena += r.getOcena();
        return avgOcena / this.getRecenzije().size();
    }

    @Override
    public String toString() {
        return "Knjiga{" +
                "id=" + id +
                ", naziv='" + naziv + '\'' +
                ", format=" + format +
                ", datumIzdanja=" + datumIzdanja +
                ", iznosiSe=" + iznosiSe +
                ", izdavac='" + izdavac + '\'' +
                ", sadrzaj=" + sadrzaj +
                ", recenzije=" + recenzije +
                ", tagovi=" + tagovi +
                ", autori=" + autori +
                '}';
    }

}
