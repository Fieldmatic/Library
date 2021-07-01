package repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import interfaces.menadzer;
import userEntities.Bibliotekar;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MenadzerBibliotekara implements menadzer {
    public List<Bibliotekar> bibliotekari = new ArrayList<Bibliotekar>();
    private static final String putanjaDoFajla = "fajlovi/Bibliotekari.json";

    public MenadzerBibliotekara(){

    }

    public void dodajBibliotekara(Bibliotekar b) throws IOException {
        ObjectMapper obj = new ObjectMapper();
        bibliotekari.add(b);
        FileWriter file = new FileWriter(putanjaDoFajla);
        try{
            String jsonStr = obj.writerWithDefaultPrettyPrinter().writeValueAsString(bibliotekari);
            file.write(jsonStr);
            file.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }


    public void azurirajFajl() {

    }

    public void ucitajPodatke() throws IOException {
        ObjectMapper obj = new ObjectMapper();
        File file = new File(putanjaDoFajla);
        bibliotekari = Arrays.asList(obj.readValue(Paths.get(putanjaDoFajla).toFile(), Bibliotekar[].class));
    }
}
