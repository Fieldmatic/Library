package repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import entities.Pozajmica;
import entities.PrimerakKnjige;
import interfaces.Menadzer;
import userEntities.Clan;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MenadzerPozajmica {
    private List<Pozajmica> pozajmice = new ArrayList<>();
    private static final String putanjaDoFajla = "fajlovi/Pozajmice.json";
    private MenadzerClanova menadzerClanova;
    private MenadzerKnjiga menadzerKnjiga;

    public MenadzerPozajmica(MenadzerClanova menadzerClanova, MenadzerKnjiga menadzerKnjiga){
        this.menadzerClanova = menadzerClanova;
        this.menadzerKnjiga = menadzerKnjiga;
    }

    public void dodajPozajmicu(Pozajmica pozajmica) throws IOException {
        pozajmice.add(pozajmica);
    }

    public void ucitajPodatke() {
        for (Clan c : menadzerClanova.getClanovi()){
            for (Pozajmica p : c.getPozajmice()){
                for (PrimerakKnjige pk : menadzerKnjiga.getPrimerci()){
                    if (p.getPozajmljenPrimerak().getId() == pk.getId()) {
                        p.setPozajmljenPrimerak(pk);
                        pozajmice.add(p);
                    }
                }
            }
        }
    }

    public List<Pozajmica> getPozajmice() {
        return pozajmice;
    }
}
