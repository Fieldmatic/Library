package repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import interfaces.Menadzer;
import userEntities.Bibliotekar;
import userEntities.KorisnickiNalog;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MenadzerBibliotekara implements Menadzer {
    private List<Bibliotekar> bibliotekari = new ArrayList<>();
    private static final String putanjaDoFajla = "fajlovi/Bibliotekari.json";

    public MenadzerBibliotekara(){}

    public void dodajBibliotekara(Bibliotekar b) throws IOException {
        bibliotekari.add(b);
        azurirajFajl();
    }


    public void azurirajFajl() throws IOException {
        ObjectMapper obj = new ObjectMapper();
        FileWriter file = new FileWriter(putanjaDoFajla);
        try{
            String jsonStr = obj.writerWithDefaultPrettyPrinter().writeValueAsString(bibliotekari);
            file.write(jsonStr);
            file.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public void ucitajPodatke() throws IOException {
        ObjectMapper obj = new ObjectMapper();
        bibliotekari = new ArrayList(Arrays.asList(obj.readValue(Paths.get(putanjaDoFajla).toFile(), Bibliotekar[].class)));
    }

    public List<Bibliotekar> getBibliotekari() {
        return bibliotekari;
    }

    public Bibliotekar pronadjiBibliotekaraPoNalogu(KorisnickiNalog nalog) {
        for (Bibliotekar b : this.bibliotekari) {
            if (b.getNalog().getKorisnickoIme().equals(nalog.getKorisnickoIme())) {
                return b;
            }
        }
        return null;
    }

}
