package repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import interfaces.menadzer;
import userEntities.Clan;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MenadzerClanova implements menadzer {
    private List<Clan> clanovi = new ArrayList<>();
    private static final String putanjaDoFajla = "fajlovi/Clanovi.json";

    public MenadzerClanova(){}

    public void dodajClana(Clan c) throws IOException {
        clanovi.add(c);
        azurirajFajl();
    }


    public void azurirajFajl() throws IOException {
        ObjectMapper obj = new ObjectMapper();
        FileWriter file = new FileWriter(putanjaDoFajla);
        try{
            String jsonStr = obj.writerWithDefaultPrettyPrinter().writeValueAsString(clanovi);
            file.write(jsonStr);
            file.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public void ucitajPodatke() throws IOException {
        ObjectMapper obj = new ObjectMapper();
        clanovi = new ArrayList(Arrays.asList(obj.readValue(Paths.get(putanjaDoFajla).toFile(), Clan[].class)));
    }
}
