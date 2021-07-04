package repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import entities.Knjiga;
import entities.Pozajmica;
import entities.PrimerakKnjige;
import entities.Rezervacija;
import enumerations.StatusRezervacije;
import interfaces.Menadzer;
import userEntities.Clan;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MenadzerRezervacija {
    private List<Rezervacija> rezervacije = new ArrayList<>();
    private MenadzerClanova menadzerClanova;
    private MenadzerKnjiga menadzerKnjiga;

    public MenadzerRezervacija(MenadzerClanova menadzerClanova, MenadzerKnjiga menadzerKnjiga) {
        this.menadzerClanova = menadzerClanova;
        this.menadzerKnjiga = menadzerKnjiga;
    }

    public void dodajRezervaciju(Rezervacija rezervacija) throws IOException {
        rezervacije.add(rezervacija);
    }

    public Rezervacija pronadjiRezervacijuPoId(int id) {
        for (Rezervacija r : this.rezervacije) {
            if (r.getId() == id) {
                return r;
            }
        }
        return null;
    }

    public Clan dobaviClanaRezervacije(Rezervacija rezervacija) {
        for (Clan clan : this.menadzerClanova.getClanovi()) {
            for (Rezervacija rezervacijaClana : clan.getRezervacije()) {
                if (rezervacija.getId() == rezervacijaClana.getId())
                    return clan;
            }
        }
        return null;
    }

    public void ucitajPodatke() {
        for (Clan c : menadzerClanova.getClanovi()) {
            for (Rezervacija r : c.getRezervacije()) {
                for (PrimerakKnjige pk : menadzerKnjiga.getPrimerci()) {
                    if (r.getRezervisanPrimerak().getId() == pk.getId()) {
                        r.setRezervisanPrimerak(pk);
                        rezervacije.add(r);
                    }
                }
            }
        }
    }

    public List<Rezervacija> dobaviRezervacijeZaOdobravanje() {
        List<Rezervacija> rezervacijeZaOdobravanje = new ArrayList<>();
        for (Rezervacija rezervacija : this.rezervacije) {
            if (rezervacija.getStatus() == StatusRezervacije.zahtevPoslat)
                rezervacijeZaOdobravanje.add(rezervacija);
        }
        return rezervacijeZaOdobravanje;
    }

    public int dobaviSlobodanIdRezervacije() {
        if (this.rezervacije.isEmpty())
            return 0;
        Rezervacija poslednjaRezervacija = this.rezervacije.get(this.rezervacije.size() - 1);
        int idPoslednjeRezervacije = poslednjaRezervacija.getId();
        return idPoslednjeRezervacije + 1;
    }

    public List<Rezervacija> getRezervacije() {
        return rezervacije;
    }
}
