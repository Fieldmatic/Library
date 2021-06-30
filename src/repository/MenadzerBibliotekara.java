package repository;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import interfaces.menadzer;
import userEntities.Bibliotekar;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class MenadzerBibliotekara implements menadzer {
    private List<Bibliotekar> biblitekari;
    private static final String putanja_do_fajla = "fajlovi/Bibliotekari.json";

    public MenadzerBibliotekara(){

    }

    public void dodajBibliotekara(Bibliotekar b) throws IOException {
        ObjectMapper obj = new ObjectMapper();
        FileWriter file = new FileWriter(putanja_do_fajla, true);
        try{
            String jsonStr = obj.writerWithDefaultPrettyPrinter().writeValueAsString(b);
            file.write(jsonStr+"\n");
            file.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }


    public void azurirajFajl() {

    }

    public void ucitajPodatke() {

    }
}
