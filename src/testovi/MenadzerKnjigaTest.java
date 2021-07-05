package testovi;

import entities.Knjiga;
import enumerations.Zanr;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.MenadzerKnjiga;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

class MenadzerKnjigaTest {

    private MenadzerKnjiga menadzerKnjiga;
    private List<Knjiga> rezultatPretrage;
    private boolean rezultatPostavljen;


    @BeforeEach
    void setUp() throws IOException {
        menadzerKnjiga = new MenadzerKnjiga();
        menadzerKnjiga.ucitajPodatke();
        rezultatPretrage = new ArrayList<>();
        rezultatPostavljen = false;
    }

    @Test
    void pronadjiKnjiguPoId() {
        Assertions.assertEquals("Senke", menadzerKnjiga.pronadjiKnjiguPoId(4).getNaziv());
    }

    @Test
    void nadjiKnjigePoNazivu() {
        Assertions.assertFalse(menadzerKnjiga.nadjiKnjigePoNazivu("Senke").isEmpty());
        Assertions.assertTrue(menadzerKnjiga.nadjiKnjigePoNazivu("Senke").stream()
                .anyMatch(knjiga -> knjiga.getNaziv().equals("Senke") &&
                        knjiga.getIzdavac().equals("Laguna") &&
                        knjiga.getTagovi().contains("ubistvo")));
    }

    @Test
    void nadjiKnjigePoNazivuSadrzaja() {
        Assertions.assertFalse(menadzerKnjiga.nadjiKnjigePoNazivuSadrzaja("beletristika").isEmpty());
    }

    @Test
    void nadjiKnjigePoZanru() {
        Assertions.assertFalse(menadzerKnjiga.nadjiKnjigePoZanru(Zanr.istorijski).isEmpty());
    }

    @Test
    void nadjiKnjigePoImenuAutora() {
        Assertions.assertFalse(menadzerKnjiga.nadjiKnjigePoImenuAutora("Ivo").isEmpty());
        Assertions.assertFalse(menadzerKnjiga.nadjiKnjigePoImenuAutora("ivo").isEmpty());
        Assertions.assertFalse(menadzerKnjiga.nadjiKnjigePoImenuAutora("iVo").isEmpty());
    }

    @Test
    void nadjiKnjigePoPrezimenuAutora() {
        Assertions.assertFalse(menadzerKnjiga.nadjiKnjigePoPrezimenuAutora("anDric").isEmpty());
        Assertions.assertFalse(menadzerKnjiga.nadjiKnjigePoPrezimenuAutora("AnDric").isEmpty());
        Assertions.assertFalse(menadzerKnjiga.nadjiKnjigePoPrezimenuAutora("Andric").isEmpty());
    }

    @Test
    void nadjiKnjigePoTagovima() {
        Assertions.assertFalse(menadzerKnjiga.nadjiKnjigePoTagovima("potreba za slobodom").isEmpty());
    }

    @Test
    void presekPretrage1() {
        napraviPresjek(menadzerKnjiga.nadjiKnjigePoNazivu("Na drini cuprija"));
        napraviPresjek(menadzerKnjiga.nadjiKnjigePoNazivuSadrzaja("beletristika"));
        napraviPresjek(menadzerKnjiga.nadjiKnjigePoZanru(Zanr.istorijski));
        napraviPresjek(menadzerKnjiga.nadjiKnjigePoImenuAutora("Ivo"));
        napraviPresjek(menadzerKnjiga.nadjiKnjigePoPrezimenuAutora("Andric"));
        napraviPresjek(menadzerKnjiga.nadjiKnjigePoTagovima("roman"));
        Assertions.assertFalse(rezultatPretrage.isEmpty());
    }

    @Test
    void presekPretrage2() {
        rezultatPretrage = new ArrayList<>();
        napraviPresjek(menadzerKnjiga.nadjiKnjigePoNazivu("na savi cuprija")); // pogresan parametar
        napraviPresjek(menadzerKnjiga.nadjiKnjigePoNazivuSadrzaja("beletristika"));
        napraviPresjek(menadzerKnjiga.nadjiKnjigePoZanru(Zanr.istorijski));
        napraviPresjek(menadzerKnjiga.nadjiKnjigePoImenuAutora("Ivo"));
        napraviPresjek(menadzerKnjiga.nadjiKnjigePoPrezimenuAutora("Andric"));
        napraviPresjek(menadzerKnjiga.nadjiKnjigePoTagovima("roman"));
        Assertions.assertTrue(rezultatPretrage.isEmpty());
    }

    private void napraviPresjek(List<Knjiga> knjige) {
        if (rezultatPretrage.isEmpty() || !rezultatPostavljen) {
            rezultatPretrage.addAll(knjige);
            rezultatPostavljen = true;
        }
        else
            rezultatPretrage = new ArrayList<>(presjeci(knjige));
    }

    private Set<Knjiga> presjeci(List<Knjiga> knjige) {
        return rezultatPretrage.stream()
                .distinct()
                .filter(knjige::contains)
                .collect(Collectors.toSet());
    }
}