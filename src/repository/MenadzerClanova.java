package repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import entities.Rezervacija;
import enumerations.StatusRezervacije;
import interfaces.Menadzer;
import userEntities.Clan;
import userEntities.KorisnickiNalog;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MenadzerClanova implements Menadzer {
    private List<Clan> clanovi = new ArrayList<>();
    private static final String putanjaDoFajla = "fajlovi/Clanovi.json";

    public MenadzerClanova(){}

    public void dodajClana(Clan c) throws IOException {
        clanovi.add(c);
        azurirajFajl();
    }


    public void azurirajFajl() throws IOException {
        ObjectMapper obj = new ObjectMapper();
        FileWriter file = new FileWriter(putanjaDoFajla);
        try{
            String jsonStr = obj.writerWithDefaultPrettyPrinter().writeValueAsString(clanovi);
            file.write(jsonStr);
            file.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public void ucitajPodatke() throws IOException {
        ObjectMapper obj = new ObjectMapper();
        clanovi = new ArrayList(Arrays.asList(obj.readValue(Paths.get(putanjaDoFajla).toFile(), Clan[].class)));
    }

    public Clan pronadjiClanaPoNalogu(KorisnickiNalog nalog) {
        for (Clan c : this.clanovi) {
            if (c.getNalog().getKorisnickoIme().equals(nalog.getKorisnickoIme())) {
                return c;
            }
        }
        return null;
    }

    public Clan pronadjiClanaPoKorImenu(String korIme) {
        for (Clan c : this.clanovi) {
            if (c.getNalog().getKorisnickoIme().equals(korIme)) {
                return c;
            }
        }
        return null;
    }


    public List<Clan> getClanovi() {
        return clanovi;
    }

    public List<Clan> dobaviClanoveSaRezervacijomZaPreuzimanje() {
        List<Clan> ret = new ArrayList<>();
        for (Clan c: getClanovi())
            for (Rezervacija rezervacija : c.getRezervacije()) {
                if (rezervacija.getStatus() == StatusRezervacije.spremnaZaPreuzimanje)
                    ret.add(c);
            }
        return ret;
    }

    public List<Rezervacija> dobaviRezervacijeClanaSpremneZaPreuzimanje(Clan clan) {
        List<Rezervacija> rezervacijeSpremneZaPreuzimanje = new ArrayList<>();
        for (Rezervacija rezervacija : clan.getRezervacije()) {
            if (rezervacija.getStatus() == StatusRezervacije.spremnaZaPreuzimanje) {
                rezervacijeSpremneZaPreuzimanje.add(rezervacija);
            }
        }
        return rezervacijeSpremneZaPreuzimanje;
    }
}
