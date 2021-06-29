package entities;

import java.time.LocalDate;
import java.util.List;

public class Knjiga {
    private int id;
    private String naziv;
    private float format;
    private LocalDate datumIzdanja;
    private boolean iznosiSe;
    private int ocena;
    private int brojOcena;
    private String izdavac;
    private int brojPrimeraka;
    private SadrzajKnjige sadrzaj;
    private List<String> tagovi;
    private List<String> komentari;
    private List<PrimerakKnjige> primerci;
    private List<Autorstvo> autori;

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

    public float getFormat() {
        return format;
    }

    public void setFormat(float format) {
        this.format = format;
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

    public int getOcena() {
        return ocena;
    }

    public void setOcena(int ocena) {
        this.ocena = ocena;
    }

    public int getBrojOcena() {
        return brojOcena;
    }

    public void setBrojOcena(int brojOcena) {
        this.brojOcena = brojOcena;
    }

    public List<String> getTagovi() {
        return tagovi;
    }

    public void setTagovi(List<String> tagovi) {
        this.tagovi = tagovi;
    }

    public List<String> getKomentari() {
        return komentari;
    }

    public void setKomentari(List<String> komentari) {
        this.komentari = komentari;
    }

    public String getIzdavac() {
        return izdavac;
    }

    public void setIzdavac(String izdavac) {
        this.izdavac = izdavac;
    }

    public int getBrojPrimeraka() {
        return brojPrimeraka;
    }

    public void setBrojPrimeraka(int brojPrimeraka) {
        this.brojPrimeraka = brojPrimeraka;
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
}
