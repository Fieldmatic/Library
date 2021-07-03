package repository;

import entities.Clanarina;
import userEntities.Clan;
import java.util.ArrayList;
import java.util.List;

public class MenadzerClanarina {
    private List<Clanarina> clanarine = new ArrayList<>();
    private MenadzerClanova menadzerClanova;

    public MenadzerClanarina(MenadzerClanova menadzerClanova){
        this.menadzerClanova = menadzerClanova;
    }

    public void dodajClanarinu(Clanarina c) {
        clanarine.add(c);
    }

    public void ucitajPodatke() {
        for (Clan c : menadzerClanova.getClanovi()){
            clanarine.add(c.getClanarina());
        }
    }

    public List<Clanarina> getClanarine() {
        return clanarine;
    }
}
