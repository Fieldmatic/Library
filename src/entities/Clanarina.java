package entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import enumerations.TipClanarine;
import localDateJson.LocalDateDeserializer;
import localDateJson.LocalDateSerializer;
import userEntities.Clan;

import java.time.LocalDate;

public class Clanarina {
    private TipClanarine tip;
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate datumPocetka;
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate datumKraja;
    @JsonBackReference
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
                '}';
    }

}
