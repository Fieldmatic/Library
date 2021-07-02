package repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import entities.Clanarina;
import interfaces.Menadzer;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MenadzerClanarina implements Menadzer {
    private List<Clanarina> clanarine = new ArrayList<>();
    private static final String putanjaDoFajla = "fajlovi/Clanarine.json";

    public MenadzerClanarina(){}

    public void dodajClanarinu(Clanarina c) throws IOException {
        clanarine.add(c);
        azurirajFajl();
    }


    public void azurirajFajl() throws IOException {
        ObjectMapper obj = new ObjectMapper();
        FileWriter file = new FileWriter(putanjaDoFajla);
        try{
            String jsonStr = obj.writerWithDefaultPrettyPrinter().writeValueAsString(clanarine);
            file.write(jsonStr);
            file.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public void ucitajPodatke() throws IOException {
        ObjectMapper obj = new ObjectMapper();
        clanarine = new ArrayList(Arrays.asList(obj.readValue(Paths.get(putanjaDoFajla).toFile(), Clanarina[].class)));
    }
}
