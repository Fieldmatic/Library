package repository;

import entities.Pozajmica;
import entities.PrimerakKnjige;
import enumerations.StatusPozajmice;
import userEntities.Clan;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
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

    public Pozajmica getPozajmicaPoIdPrimerka(int idPrimerka) {
        for (Pozajmica p : getPozajmice())
            if (p.getPozajmljenPrimerak().getId() == idPrimerka)
                return p;
        return null;
    }

    public List<Pozajmica> getPozajmice() {
        return pozajmice;
    }

    public Pozajmica kreirajPozajmicu(PrimerakKnjige primerak, Clan c) {
        int id = 0;
        for (Pozajmica p : getPozajmice())
            if (p.getId() > id) id = p.getId();
        return new Pozajmica((id == 0) ? 0 : id+1, primerak, LocalDate.now(), LocalDate.now().plusDays(getBrojDanaPozajmljenja(c)), StatusPozajmice.aktivna);
    }

     public int getBrojDanaPozajmljenja(Clan c) {
        switch (c.getClanarina().getTip()) {
            case djak:
            case student:
                return 15;
            case penzioner:
                return 21;
            case pocasniClan:
                return 30;
            default:
                return 10;
        }
    }
}
