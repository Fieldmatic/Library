package entities;

import enumerations.TipClanarine;
import userEntities.Clan;

import java.time.LocalDate;

public class Clanarina {
    private TipClanarine tip;
    private LocalDate datumPocetka;
    private LocalDate datumKraja;
    private Clan clan;

    public Clanarina() {
    }

    public Clanarina(TipClanarine tip, LocalDate datumPocetka, LocalDate datumKraja, Clan clan) {
        this.tip = tip;
        this.datumPocetka = datumPocetka;
        this.datumKraja = datumKraja;
        this.clan = clan;
    }

    public TipClanarine getTip() {
        return tip;
    }

    public void setTip(TipClanarine tip) {
        this.tip = tip;
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

    public Clan getClan() {
        return clan;
    }

    public void setClan(Clan clan) {
        this.clan = clan;
    }

    @Override
    public String toString() {
        return "Clanarina{" +
                "tip=" + tip +
                ", datumPocetka=" + datumPocetka +
                ", datumKraja=" + datumKraja +
                ", datumKraja=" + datumKraja +
                ", clan=" + clan +
                '}';
    }

}