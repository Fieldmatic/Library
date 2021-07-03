package repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import entities.Knjiga;
import entities.PrimerakKnjige;
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
