package userEntities;

import enumerations.Pol;

import java.time.LocalDate;

public class Bibliotekar extends Korisnik {

    public Bibliotekar(){

    }

    public Bibliotekar(String ime, String prezime, LocalDate datumRodjenja, int jmbg, Pol pol) {
        super(ime, prezime, datumRodjenja, jmbg, pol);
    }

}
