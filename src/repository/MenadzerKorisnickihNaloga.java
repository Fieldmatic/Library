package repository;
import userEntities.Bibliotekar;
import userEntities.Clan;
import userEntities.KorisnickiNalog;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MenadzerKorisnickihNaloga {
    private List<KorisnickiNalog> nalozi = new ArrayList<>();
    private static final String putanjaDoFajla = "fajlovi/Nalozi.json";
    private MenadzerBibliotekara menadzerBibliotekara;
    private MenadzerClanova menadzerClanova;

    public MenadzerKorisnickihNaloga(MenadzerBibliotekara menadzerBibliotekara, MenadzerClanova menadzerClanova){
        this.menadzerBibliotekara = menadzerBibliotekara;
        this.menadzerClanova = menadzerClanova;
    }

    public void dodajNalog(KorisnickiNalog kn) throws IOException {
        nalozi.add(kn);
    }

    public void ucitajPodatke() {
        for (Bibliotekar b: menadzerBibliotekara.getBibliotekari()){
            nalozi.add(b.getNalog());
        }
        for (Clan c : menadzerClanova.getClanovi()){
            nalozi.add(c.getNalog());
        }
    }

    public KorisnickiNalog pronadjiNalogSaUsername(String korisnickoIme){
        for(KorisnickiNalog nalog: this.nalozi) {
            if (nalog.getKorisnickoIme().equals(korisnickoIme)){
                return nalog;
            }
        }
        return null;
    }

    public boolean validnostPrijave(String korisnickoIme, String lozinka) {
        KorisnickiNalog nalog = pronadjiNalogSaUsername(korisnickoIme);
        if ((!nalog.equals(null)) && (nalog.getLozinka().equals(lozinka))) {
            return true;
        }
        return false;
    }

    public List<KorisnickiNalog> getNalozi() {
        return nalozi;
    }
}
