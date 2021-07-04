package testovi;

import enumerations.Zanr;
import org.junit.jupiter.api.Assertions;
import repository.MenadzerKnjiga;

import java.io.IOException;

class MenadzerKnjigaTest {

    private MenadzerKnjiga menadzerKnjiga;

    @org.junit.jupiter.api.BeforeEach
    void setUp() throws IOException {
        menadzerKnjiga = new MenadzerKnjiga();
        menadzerKnjiga.ucitajPodatke();
    }

    @org.junit.jupiter.api.Test
    void pronadjiKnjiguPoId() {
        Assertions.assertEquals("Senke", menadzerKnjiga.pronadjiKnjiguPoId(4).getNaziv());
    }

    @org.junit.jupiter.api.Test
    void nadjiKnjigePoNazivu() {
        Assertions.assertFalse(menadzerKnjiga.nadjiKnjigePoNazivu("Senke").isEmpty());
        Assertions.assertTrue(menadzerKnjiga.nadjiKnjigePoNazivu("Senke").stream()
        .anyMatch(knjiga -> knjiga.getNaziv().equals("Senke") &&
                knjiga.getIzdavac().equals("Laguna") &&
                knjiga.getTagovi().contains("ubistvo")));
    }

    @org.junit.jupiter.api.Test
    void nadjiKnjigePoNazivuSadrzaja() {
        Assertions.assertFalse(menadzerKnjiga.nadjiKnjigePoNazivuSadrzaja("beletristika").isEmpty());
    }

    @org.junit.jupiter.api.Test
    void nadjiKnjigePoZanru() {
        Assertions.assertFalse(menadzerKnjiga.nadjiKnjigePoZanru(Zanr.istorijski).isEmpty());
    }

    @org.junit.jupiter.api.Test
    void nadjiKnjigePoImenuAutora() {
        Assertions.assertFalse(menadzerKnjiga.nadjiKnjigePoImenuAutora("Ivo").isEmpty());
        Assertions.assertFalse(menadzerKnjiga.nadjiKnjigePoImenuAutora("ivo").isEmpty());
        Assertions.assertFalse(menadzerKnjiga.nadjiKnjigePoImenuAutora("iVo").isEmpty());
    }

    @org.junit.jupiter.api.Test
    void nadjiKnjigePoPrezimenuAutora() {
        Assertions.assertFalse(menadzerKnjiga.nadjiKnjigePoPrezimenuAutora("anDric").isEmpty());
        Assertions.assertFalse(menadzerKnjiga.nadjiKnjigePoPrezimenuAutora("AnDric").isEmpty());
        Assertions.assertFalse(menadzerKnjiga.nadjiKnjigePoPrezimenuAutora("Andric").isEmpty());
    }

    @org.junit.jupiter.api.Test
    void nadjiKnjigePoTagovima() {
        Assertions.assertFalse(menadzerKnjiga.nadjiKnjigePoTagovima("potreba za slobodom").isEmpty());
    }
}