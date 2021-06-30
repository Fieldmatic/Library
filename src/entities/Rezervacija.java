package entities;

import enumerations.StatusRezervacije;

import java.time.LocalDate;

public class Rezervacija {
    private int id;
    private PrimerakKnjige rezervisanPrimerak;
    private LocalDate datum;
    private StatusRezervacije status;

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
