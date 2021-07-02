package repository;

import java.io.IOException;

public class Fabrika {
    private MenadzerKnjiga menadzerKnjiga;
    private MenadzerClanova menadzerClanova;
    private MenadzerClanarina menadzerClanarina;
    private MenadzerBibliotekara menadzerBibliotekara;
    private MenadzerPozajmica menadzerPozajmica;
    private MenadzerRezervacija menadzerRezervacija;
    private MenadzerKorisnickihNaloga menadzerKorisnickihNaloga;

    public Fabrika() throws IOException {
        this.menadzerKnjiga = new MenadzerKnjiga();
        this.menadzerClanova = new MenadzerClanova();
        this.menadzerClanarina = new MenadzerClanarina();
        this.menadzerBibliotekara = new MenadzerBibliotekara();
        this.menadzerPozajmica = new MenadzerPozajmica();
        this.menadzerRezervacija = new MenadzerRezervacija();
        this.menadzerKorisnickihNaloga = new MenadzerKorisnickihNaloga();

        this.menadzerKnjiga.ucitajPodatke();
        this.menadzerClanova.ucitajPodatke();
        this.menadzerClanarina.ucitajPodatke();
        this.menadzerBibliotekara.ucitajPodatke();
        //this.menadzerPozajmica.ucitajPodatke();
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
}
