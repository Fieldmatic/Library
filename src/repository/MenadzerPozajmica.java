package repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import entities.Pozajmica;
import interfaces.Menadzer;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MenadzerPozajmica implements Menadzer {
    private List<Pozajmica> pozajmice = new ArrayList<>();
    private static final String putanjaDoFajla = "fajlovi/Pozajmice.json";

    public MenadzerPozajmica() {}

    public void dodajPozajmicu(Pozajmica pozajmica) throws IOException {
        pozajmice.add(pozajmica);
        azurirajFajl();
    }

    public void ucitajPodatke() throws IOException {
        ObjectMapper obj = new ObjectMapper();
        pozajmice = new ArrayList(Arrays.asList(obj.readValue(Paths.get(putanjaDoFajla).toFile(), Pozajmica[].class)));
    }

    public void azurirajFajl() throws IOException {
        ObjectMapper obj = new ObjectMapper();
        FileWriter file = new FileWriter(putanjaDoFajla);
        try {
            String jsonStr = obj.writerWithDefaultPrettyPrinter().writeValueAsString(pozajmice);
            file.write(jsonStr);
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
