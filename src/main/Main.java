package main;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import enumerations.Pol;
import enumerations.VrstaNaloga;
import repository.MenadzerBibliotekara;
import userEntities.Bibliotekar;
import userEntities.KorisnickiNalog;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) throws IOException {
        MenadzerBibliotekara mb = new MenadzerBibliotekara();
        Bibliotekar b = new Bibliotekar("Mico", "Milic", LocalDate.now(), 812741, Pol.muski);
        KorisnickiNalog k = new KorisnickiNalog("mico", "milic", VrstaNaloga.bibliotekar, b);
        b.setNalog(k);
        mb.dodajBibliotekara(b);
        Bibliotekar g = new Bibliotekar("Mico", "Milic", LocalDate.now(), 29812741, Pol.muski);
        mb.dodajBibliotekara(g);
        mb.ucitajPodatke();
        System.out.println(mb.bibliotekari);

    }
}
