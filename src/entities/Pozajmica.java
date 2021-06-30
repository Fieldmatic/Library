package entities;

import enumerations.StatusPozajmice;

import java.time.LocalDate;

public class Pozajmica {
    private int id;
    private PrimerakKnjige pozajmljenPrimerak;
    private LocalDate datumPocetka;
    private LocalDate datumKraja;
    private StatusPozajmice status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public PrimerakKnjige getPozajmljenPrimerak() {
        return pozajmljenPrimerak;
    }

    public void setPozajmljenPrimerak(PrimerakKnjige pozajmljenPrimerak) {
        this.pozajmljenPrimerak = pozajmljenPrimerak;
    }

    public LocalDate getDatumPocetka() {
        return datumPocetka;
    }

    public void setDatumPocetka(LocalDate datumPocetka) {
        this.datumPocetka = datumPocetka;
    }

    public LocalDate getDatumKraja() {
        return datumKraja;
    }

    public void setDatumKraja(LocalDate datumKraja) {
        this.datumKraja = datumKraja;
    }

    public StatusPozajmice getStatus() {
        return status;
    }

    public void setStatus(StatusPozajmice status) {
        this.status = status;
    }
}
