package entities;

import enumerations.TipClanarine;
import enumerations.TrajanjeClanarine;

public class CenaClanarine {
    private TipClanarine tip;
    private TrajanjeClanarine trajanje;
    private double cena;

    public CenaClanarine() { }

    public CenaClanarine(TipClanarine tip, TrajanjeClanarine trajanje, double cena) {
        this.tip = tip;
        this.trajanje = trajanje;
        this.cena = cena;
    }

    public TipClanarine getTip() {
        return tip;
    }

    public void setTip(TipClanarine tip) {
        this.tip = tip;
    }

    public TrajanjeClanarine getTrajanje() {
        return trajanje;
    }

    public void setTrajanje(TrajanjeClanarine trajanje) {
        this.trajanje = trajanje;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    @Override
    public String toString() {
        return "CenaClanarine{" +
                "tip=" + tip +
                ", trajanje=" + trajanje +
                ", cena=" + cena +
                '}';
    }
}
