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


    public Clan() {
    }

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

    @Override
    public String toString() {
        return super.toString() + "Clan{" +
                "clanarina=" + clanarina +
                '}';
    }

    public void dodajPozajmicu(Pozajmica p) {
        pozajmice.add(p);
    }

    public boolean uslovPozajmice() {
        if (getClanarina().getTip().equals(TipClanarine.djak) || getClanarina().getTip().equals(TipClanarine.student)
                || getClanarina().getTip().equals(TipClanarine.dete))
            return getPozajmice().size() < 3;
        else if (getClanarina().getTip().equals(TipClanarine.penzioner))
            return getPozajmice().size() < 3;
        else if (getClanarina().getTip().equals(TipClanarine.pocasniClan))
            return getPozajmice().size() < 10;
        return false;
    }
}
