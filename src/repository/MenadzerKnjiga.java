package repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import entities.Knjiga;
import interfaces.menadzer;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MenadzerKnjiga implements menadzer {
    private List<Knjiga> knjige = new ArrayList<Knjiga>();
    private static final String putanjaDoFajla = "fajlovi/Knjige.json";

    public MenadzerKnjiga() {}

    public void dodajKnjigu(Knjiga knjiga) throws IOException {
        ObjectMapper obj = new ObjectMapper();
        FileWriter file = new FileWriter(putanjaDoFajla);
        try {
            knjige.add(knjiga);
            String jsonStr = obj.writerWithDefaultPrettyPrinter().writeValueAsString(knjige);
            file.write(jsonStr);
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void ucitajPodatke() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        File file = new File(putanjaDoFajla);

        Knjiga knjiga = objectMapper.readValue(file, Knjiga.class);
    }

    public void azurirajFajl() {

    }
}
