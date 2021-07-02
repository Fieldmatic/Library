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
    private static final String putanjaDoFajlaKnjiga = "fajlovi/Knjige.json";
    private static final String putanjaDoFajlaPrimeraka = "fajlovi/PrimerciKnjiga.json";

    public MenadzerKnjiga() {}

    public void dodajKnjigu(Knjiga knjiga) throws IOException {
        knjige.add(knjiga);
        azurirajFajl();
    }

    public void dodajPrimerakKnjige(PrimerakKnjige primerak) throws IOException {
        primerci.add(primerak);
        azurirajFajl();
    }

    public void ucitajPodatke() throws IOException {
        ObjectMapper obj = new ObjectMapper();
        knjige = new ArrayList(Arrays.asList(obj.readValue(Paths.get(putanjaDoFajlaKnjiga).toFile(), Knjiga[].class)));
        primerci = new ArrayList(Arrays.asList(obj.readValue(Paths.get(putanjaDoFajlaPrimeraka).toFile(), PrimerakKnjige[].class)));
    }

    public void azurirajFajl() throws IOException {
        ObjectMapper obj = new ObjectMapper();
        FileWriter fajlKnjiga = new FileWriter(putanjaDoFajlaKnjiga);
        FileWriter fajlPrimeraka = new FileWriter(putanjaDoFajlaPrimeraka);
        try {
            String jsonKnjige = obj.writerWithDefaultPrettyPrinter().writeValueAsString(knjige);
            fajlKnjiga.write(jsonKnjige);
            fajlKnjiga.close();

            String jsonPrimerci = obj.writerWithDefaultPrettyPrinter().writeValueAsString(primerci);
            fajlPrimeraka.write(jsonPrimerci);
            fajlPrimeraka.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
