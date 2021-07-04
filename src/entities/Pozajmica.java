package entities;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import enumerations.StatusPozajmice;
import jsonSerializerDeserializer.LocalDateDeserializer;
import jsonSerializerDeserializer.LocalDateSerializer;

import java.time.LocalDate;

public class Pozajmica {
    private int id;
    private PrimerakKnjige pozajmljenPrimerak;
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate datumPocetka;
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate datumKraja;
    private StatusPozajmice status;

    public Pozajmica() {}

    public Pozajmica(int id, PrimerakKnjige pozajmljenPrimerak, LocalDate datumPocetka, LocalDate datumKraja, StatusPozajmice status) {
        this.id = id;
        this.pozajmljenPrimerak = pozajmljenPrimerak;
        this.datumPocetka = datumPocetka;
        this.datumKraja = datumKraja;
        this.status = status;
    }

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

    @Override
    public String toString() {
        return "Pozajmica{" +
                "id=" + id +
                ", pozajmljenPrimerak=" + pozajmljenPrimerak +
                ", datumPocetka=" + datumPocetka +
                ", datumKraja=" + datumKraja +
                ", status=" + status +
                '}';
    }
}
