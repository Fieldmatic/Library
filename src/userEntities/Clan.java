package userEntities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import entities.Clanarina;
import enumerations.Pol;

import java.time.LocalDate;

public class Clan extends Korisnik {
    @JsonManagedReference
    private Clanarina clanarina;

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

    @Override
    public String toString() {
        return super.toString() + "Clan{" +
                "clanarina=" + clanarina +
                '}';
    }
}
