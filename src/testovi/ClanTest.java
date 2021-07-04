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
    void dodajPrevisePozajmica() throws IOException {
        Pozajmica p1 = fabrika.getMenadzerPozajmica().kreirajPozajmicu(fabrika.getMenadzerKnjiga().nadjiSlobodanPrimerak(fabrika.getMenadzerKnjiga().nadjiKnjigePoNazivu("senke").get(0)),
                fabrika.getMenadzerClanova().pronadjiClanaPoKorImenu("tamara"));
        fabrika.getMenadzerClanova().pronadjiClanaPoKorImenu("tamara").dodajPozajmicu(p1);
        fabrika.getMenadzerPozajmica().dodajPozajmicu(p1);

        Pozajmica p2 = fabrika.getMenadzerPozajmica().kreirajPozajmicu(fabrika.getMenadzerKnjiga().nadjiSlobodanPrimerak(fabrika.getMenadzerKnjiga().nadjiKnjigePoNazivu("oko otoka").get(0)),
                fabrika.getMenadzerClanova().pronadjiClanaPoKorImenu("tamara"));
        fabrika.getMenadzerClanova().pronadjiClanaPoKorImenu("tamara").dodajPozajmicu(p2);
        fabrika.getMenadzerPozajmica().dodajPozajmicu(p2);

         Assertions.assertFalse(fabrika.getMenadzerClanova().pronadjiClanaPoKorImenu("tamara").uslovPozajmice());

    }

    @Test
    void dodajRezervaciju() {
    }
}