package repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import entities.Rezervacija;
import interfaces.menadzer;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MenadzerRezervacija implements menadzer {
    private List<Rezervacija> rezervacije = new ArrayList<>();
    private static final String putanjaDoFajla = "fajlovi/Rezervacije.json";

    public MenadzerRezervacija() {}

    public void dodajRezervaciju(Rezervacija rezervacija) throws IOException {
        rezervacije.add(rezervacija);
        azurirajFajl();
    }

    public void ucitajPodatke() throws IOException {
        ObjectMapper obj = new ObjectMapper();
        rezervacije = new ArrayList(Arrays.asList(obj.readValue(Paths.get(putanjaDoFajla).toFile(), Rezervacija[].class)));
    }

    public void azurirajFajl() throws IOException {
        ObjectMapper obj = new ObjectMapper();
        FileWriter file = new FileWriter(putanjaDoFajla);
        try {
            String jsonStr = obj.writerWithDefaultPrettyPrinter().writeValueAsString(rezervacije);
            file.write(jsonStr);
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
