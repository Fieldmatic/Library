package entities;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import enumerations.StatusRezervacije;
import JsonSerializersDeserializers.LocalDateDeserializer;
import JsonSerializersDeserializers.LocalDateSerializer;

import java.time.LocalDate;

public class Rezervacija {
    private int id;
    private PrimerakKnjige rezervisanPrimerak;
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate datum;
    private StatusRezervacije status;

    public Rezervacija(int id, PrimerakKnjige rezervisanPrimerak, LocalDate datum, StatusRezervacije status) {
        this.id = id;
        this.rezervisanPrimerak = rezervisanPrimerak;
        this.datum = datum;
        this.status = status;
    }

    public Rezervacija() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public PrimerakKnjige getRezervisanPrimerak() {
        return rezervisanPrimerak;
    }

    public void setRezervisanPrimerak(PrimerakKnjige rezervisanPrimerak) {
        this.rezervisanPrimerak = rezervisanPrimerak;
    }

    public LocalDate getDatum() {
        return datum;
    }

    public void setDatum(LocalDate datum) {
        this.datum = datum;
    }

    public StatusRezervacije getStatus() {
        return status;
    }

    public void setStatus(StatusRezervacije status) {
        this.status = status;
    }
}
