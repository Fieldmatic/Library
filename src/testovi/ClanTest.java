package testovi;

import entities.Pozajmica;
import enumerations.StatusPozajmice;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.Fabrika;

import java.io.IOException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ClanTest {

    Fabrika fabrika;

    @BeforeEach
    void setUp() throws IOException {
        fabrika = new Fabrika();
    }

    @Test
    void dodajPozajmicu() throws IOException {
        Pozajmica p = fabrika.getMenadzerPozajmica().kreirajPozajmicu(fabrika.getMenadzerKnjiga().nadjiSlobodanPrimerak(fabrika.getMenadzerKnjiga().nadjiKnjigePoNazivu("Na drini cuprija").get(0)),
                fabrika.getMenadzerClanova().pronadjiClanaPoKorImenu("tamara"));
        fabrika.getMenadzerClanova().pronadjiClanaPoKorImenu("tamara").dodajPozajmicu(p);
        fabrika.getMenadzerPozajmica().dodajPozajmicu(p);
        Assertions.assertTrue(fabrika.getMenadzerPozajmica().getPozajmice().stream()
                .anyMatch(pozajmica -> pozajmica.getDatumPocetka().equals(LocalDate.now()) &&
                        pozajmica.getStatus().equals(StatusPozajmice.aktivna) &&
                        pozajmica.getPozajmljenPrimerak().getKnjiga().getNaziv().contains("Drini")));
    }

    @Test
    void dodajRezervaciju() {
    }
}