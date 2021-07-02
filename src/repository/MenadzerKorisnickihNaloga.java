package repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import interfaces.Menadzer;
import userEntities.KorisnickiNalog;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MenadzerKorisnickihNaloga implements Menadzer {
    private List<KorisnickiNalog> nalozi = new ArrayList<>();
    private static final String putanjaDoFajla = "fajlovi/Nalozi.json";

    public MenadzerKorisnickihNaloga(){}

    public void dodajNalog(KorisnickiNalog kn) throws IOException {
        nalozi.add(kn);
        azurirajFajl();
    }

    public void azurirajFajl() throws IOException {
        ObjectMapper obj = new ObjectMapper();
        FileWriter file = new FileWriter(putanjaDoFajla);
        try{
            String jsonStr = obj.writerWithDefaultPrettyPrinter().writeValueAsString(nalozi);
            file.write(jsonStr);
            file.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public void ucitajPodatke() throws IOException {
        ObjectMapper obj = new ObjectMapper();
        nalozi = new ArrayList(Arrays.asList(obj.readValue(Paths.get(putanjaDoFajla).toFile(), KorisnickiNalog[].class)));
    }

    public KorisnickiNalog pronadjiNalogSaUsername(String korisnickoIme){
        for(KorisnickiNalog nalog: this.nalozi) {
            if (nalog.getKorisnickoIme().equals(korisnickoIme)){
                return nalog;
            }
        }
        return null;
    }

    public boolean validnostPrijave(String korisnickoIme, String lozinka) {
        KorisnickiNalog nalog = pronadjiNalogSaUsername(korisnickoIme);
        if ((!nalog.equals(null)) && (nalog.getLozinka().equals(lozinka))) {
            return true;
        }
        return false;
    }
}
