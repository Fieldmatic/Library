package repository;

import auxiliary.Provere;
import entities.Pozajmica;
import enumerations.StatusPozajmice;
import userEntities.Clan;

import java.io.IOException;
import java.time.LocalDate;

public class Fabrika {
    private MenadzerKnjiga menadzerKnjiga;
    private MenadzerClanova menadzerClanova;
    private MenadzerClanarina menadzerClanarina;
    private MenadzerBibliotekara menadzerBibliotekara;
    private MenadzerPozajmica menadzerPozajmica;
    private MenadzerRezervacija menadzerRezervacija;
    private MenadzerKorisnickihNaloga menadzerKorisnickihNaloga;
    private Provere provere;

    public Fabrika() throws IOException {
        this.menadzerKnjiga = new MenadzerKnjiga();
        this.menadzerClanova = new MenadzerClanova();
        this.menadzerClanarina = new MenadzerClanarina(menadzerClanova);
        this.menadzerBibliotekara = new MenadzerBibliotekara();
        this.menadzerPozajmica = new MenadzerPozajmica(menadzerClanova, menadzerKnjiga);
        this.menadzerRezervacija = new MenadzerRezervacija();
        this.menadzerKorisnickihNaloga = new MenadzerKorisnickihNaloga(menadzerBibliotekara, menadzerClanova);
        this.provere = new Provere(menadzerKorisnickihNaloga);

        this.menadzerKnjiga.ucitajPodatke();
        this.menadzerClanova.ucitajPodatke();
        this.menadzerClanarina.ucitajPodatke();
        this.menadzerBibliotekara.ucitajPodatke();
        this.menadzerPozajmica.ucitajPodatke();
        //this.menadzerRezervacija.ucitajPodatke();
        this.menadzerKorisnickihNaloga.ucitajPodatke();
        //mozda treba neki apdejt negde

    }

    public MenadzerKnjiga getMenadzerKnjiga() {
        return menadzerKnjiga;
    }

    public MenadzerClanova getMenadzerClanova() {
        return menadzerClanova;
    }

    public MenadzerClanarina getMenadzerClanarina() {
        return menadzerClanarina;
    }

    public MenadzerBibliotekara getMenadzerBibliotekara() {
        return menadzerBibliotekara;
    }

    public MenadzerPozajmica getMenadzerPozajmica() {
        return menadzerPozajmica;
    }

    public MenadzerRezervacija getMenadzerRezervacija() {
        return menadzerRezervacija;
    }

    public MenadzerKorisnickihNaloga getMenadzerKorisnickihNaloga() {
        return menadzerKorisnickihNaloga;
    }
    public Provere getProvere() {
        return provere;
    }

}
