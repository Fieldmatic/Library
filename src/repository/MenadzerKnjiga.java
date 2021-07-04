package repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import entities.Autorstvo;
import entities.Knjiga;
import entities.PrimerakKnjige;
import enumerations.UlogaAutora;
import enumerations.Zanr;
import interfaces.Menadzer;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class MenadzerKnjiga implements Menadzer {
    private List<Knjiga> knjige = new ArrayList<>();
    private List<PrimerakKnjige> primerci = new ArrayList<>();
    private static final String putanjaDoFajla = "fajlovi/Knjige.json";

    public MenadzerKnjiga() {}

    public void dodajKnjigu(Knjiga knjiga) throws IOException {
        knjige.add(knjiga);
        azurirajFajl();
    }

    public void dodajPrimerakKnjige(PrimerakKnjige primerak) throws IOException {
        primerci.add(primerak);
    }

    public Knjiga pronadjiKnjiguPoId(int id) {
        for (Knjiga k : this.knjige) {
            if (k.getId() == id) {
                return k;
            }
        }
        return null;
    }
    
    public boolean knjigaImaZauzetePrimerke(Knjiga knjiga) {
        for (PrimerakKnjige primerakKnjige: knjiga.getPrimerci()) {
            if (primerakKnjige.isPozajmljen()) 
                return true;
        }
        return false;
    }

    public int dobaviSlobodanIdKnjige() {
        Knjiga poslednjaKnjiga = this.knjige.get(this.knjige.size() - 1);
        int idPoslednjeKnjige = poslednjaKnjiga.getId();
        return idPoslednjeKnjige + 1;
    }

    public int dobaviSlobodanIdPrimeraka() {
        Knjiga poslednjaKnjiga = this.knjige.get(this.knjige.size() - 1);
        List<PrimerakKnjige> primerciKnjige = poslednjaKnjiga.getPrimerci();
        PrimerakKnjige poslednjiPrimerak;
        if (primerciKnjige.isEmpty()) {
            Knjiga pretposlednjaKnjiga = this.knjige.get(this.knjige.size() - 2);
            poslednjiPrimerak = pretposlednjaKnjiga.getPrimerci().get(pretposlednjaKnjiga.getPrimerci().size() - 1);
        } else {
            poslednjiPrimerak = primerciKnjige.get(primerciKnjige.size() - 1);
        }
        int idPoslednjegPrimerka = poslednjiPrimerak.getId();
        return idPoslednjegPrimerka + 1;
    }

    public boolean knjigaImaPisca(Knjiga knjiga) {
        for (Autorstvo autor : knjiga.getAutori()) {
            if (autor.getUloga() == UlogaAutora.pisac)
                return true;
        }
        return false;
    }

    public PrimerakKnjige pronadjiPrimerakPoId(int id) {
        for (PrimerakKnjige p : this.primerci) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }



    public void ucitajPodatke() throws IOException {
        ObjectMapper obj = new ObjectMapper();
        knjige = new ArrayList(Arrays.asList(obj.readValue(Paths.get(putanjaDoFajla).toFile(), Knjiga[].class)));
        ucitajPrimerke();
    }

    private void ucitajPrimerke() {
        for (Knjiga k : knjige) {
            for (PrimerakKnjige p : k.getPrimerci()){
                p.setKnjiga(k);
                primerci.add(p);
            }
        }
    }

    public void azurirajFajl() throws IOException {
        ObjectMapper obj = new ObjectMapper();
        FileWriter fajlKnjiga = new FileWriter(putanjaDoFajla);
        try {
            String jsonKnjige = obj.writerWithDefaultPrettyPrinter().writeValueAsString(knjige);
            fajlKnjiga.write(jsonKnjige);
            fajlKnjiga.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Knjiga> getKnjige() {
        return knjige;
    }

    public List<PrimerakKnjige> getPrimerci() {
        return primerci;
    }

    public List<Knjiga> nadjiKnjigePoNazivu(String naziv) {
        List<Knjiga> ret = new ArrayList<>();
        for (Knjiga k : getKnjige())
            if (k.getNaziv().toLowerCase().equals(naziv.toLowerCase()))
                ret.add(k);
        return ret;
    }

    public List<Knjiga> nadjiKnjigePoNazivuSadrzaja(String nazivSadrzaja) {
        List<Knjiga> ret = new ArrayList<>();
        for (Knjiga k : getKnjige())
            if (k.getSadrzaj().getNaziv().toLowerCase().equals(nazivSadrzaja.toLowerCase()))
                ret.add(k);
        return ret;
    }

    public List<Knjiga> nadjiKnjigePoZanru(Zanr zanr) {
        List<Knjiga> ret = new ArrayList<>();
        for (Knjiga k : getKnjige())
            for (Zanr z : k.getSadrzaj().getZanrovi())
                if (z.equals(zanr)) {
                    ret.add(k);
                    break;
                }
        return ret;
    }

    public List<Knjiga> nadjiKnjigePoImenuAutora(String imeAutora) {
        List<Knjiga> ret = new ArrayList<>();
        for (Knjiga k : getKnjige())
            for (Autorstvo a: k.getAutori())
                if (a.getAutor().getIme().toLowerCase().equals(imeAutora.toLowerCase())) {
                    ret.add(k);
                    break;
                }
        return ret;
    }

    public List<Knjiga> nadjiKnjigePoPrezimenuAutora(String prezimeAutora) {
        List<Knjiga> ret = new ArrayList<>();
        for (Knjiga k : getKnjige())
            for (Autorstvo a: k.getAutori())
                if (a.getAutor().getPrezime().toLowerCase().equals(prezimeAutora.toLowerCase())) {
                    ret.add(k);
                    break;
                }
        return ret;
    }

    public List<Knjiga> nadjiKnjigePoTagovima(String tag) {
        List<Knjiga> ret = new ArrayList<>();
        for (Knjiga k : getKnjige())
            for (String s: k.getTagovi())
                if (s.toLowerCase().equals(tag.toLowerCase())) {
                    ret.add(k);
                    break;
                }
        return ret;
    }

    public List<Knjiga> nadjiKnjigePoOceni(int ocena) {
        List<Knjiga> ret = new ArrayList<>();
        for (Knjiga k : getKnjige()) {
            if (k.getProsecnaOcena() == ocena)
                ret.add(k);
        }
        return ret;
    }

    public List<Knjiga> nadjiKnjigePoDatIzdavanja(Date datum) {
        List<Knjiga> ret = new ArrayList<>();
        for (Knjiga k : getKnjige()) {
            if (k.getDatumIzdanja().equals(datum))
                ret.add(k);
        }
        return ret;
    }

    public List<Knjiga> nadjiKnjigePoIzdavacu(String izdavac) {
        List<Knjiga> ret = new ArrayList<>();
        for (Knjiga k : getKnjige()) {
            if (k.getIzdavac().toLowerCase().equals(izdavac.toLowerCase()))
                ret.add(k);
        }
        return ret;
    }

    public boolean imaSlobodanPrimerak(Knjiga k) {
        return nadjiSlobodanPrimerak(k) != null;
    }

    public PrimerakKnjige nadjiSlobodanPrimerak(Knjiga k) {
        for (PrimerakKnjige p : k.getPrimerci())
            if (!p.isPozajmljen() || !p.isPopravljaSe()) return p;
        return null;
    }
}
