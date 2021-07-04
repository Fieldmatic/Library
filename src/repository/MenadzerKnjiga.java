package repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import entities.Autor;
import entities.Autorstvo;
import entities.Knjiga;
import entities.PrimerakKnjige;
import enumerations.UlogaAutora;
import interfaces.Menadzer;

import java.io.*;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
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
}
