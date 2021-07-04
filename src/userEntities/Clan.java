package userEntities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import entities.Clanarina;
import entities.Pozajmica;
import entities.Rezervacija;
import enumerations.Pol;
import enumerations.TipClanarine;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Clan extends Korisnik {
    @JsonManagedReference
    private Clanarina clanarina;
    private List<Pozajmica> pozajmice = new ArrayList<>();
    private List<Rezervacija> rezervacije = new ArrayList<>();


    public Clan(){}

    public Clan(String ime, String prezime, LocalDate datumRodjenja, String jmbg, Pol pol) {
        super(ime, prezime, datumRodjenja, jmbg, pol);
    }

    public Clanarina getClanarina() {
        return clanarina;
    }

    public void setClanarina(Clanarina clanarina) {
        this.clanarina = clanarina;
    }

    public List<Pozajmica> getPozajmice() {
        return pozajmice;
    }

    public void setPozajmice(List<Pozajmica> pozajmice) {
        this.pozajmice = pozajmice;
    }

    public List<Rezervacija> getRezervacije() {
        return rezervacije;
    }

    public void setRezervacije(List<Rezervacija> rezervacije) {
        this.rezervacije = rezervacije;
    }

    public int getBrojDanaPozajmljenja() {
        switch (clanarina.getTip()) {
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

    @Override
    public String toString() {
        return super.toString() + "Clan{" +
                "clanarina=" + clanarina +
                '}';
    }
}
