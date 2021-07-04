package repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import entities.CenaClanarine;
import entities.Rezervacija;
import enumerations.TipClanarine;
import enumerations.TrajanjeClanarine;
import interfaces.Menadzer;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MenadzerCena implements Menadzer {
    private List<CenaClanarine> cene = new ArrayList<>();
    private static final String putanjaDoFajla = "fajlovi/Cenovnik.json";

    public MenadzerCena() {}

    public void dodajCenu(CenaClanarine cena) throws IOException {
        cene.add(cena);
        azurirajFajl();
    }

    public void ucitajPodatke() throws IOException {
        ObjectMapper obj = new ObjectMapper();
        cene = new ArrayList(Arrays.asList(obj.readValue(Paths.get(putanjaDoFajla).toFile(), CenaClanarine[].class)));
    }

    public void azurirajFajl() throws IOException {
        ObjectMapper obj = new ObjectMapper();
        FileWriter file = new FileWriter(putanjaDoFajla);
        try {
            String jsonStr = obj.writerWithDefaultPrettyPrinter().writeValueAsString(cene);
            file.write(jsonStr);
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public CenaClanarine pronadjiPoTipuiTrajanju(TipClanarine tip, TrajanjeClanarine trajanje){
        for (CenaClanarine c : cene){
            if (c.getTip() == tip && c.getTrajanje() == trajanje) return c;
        }
        return null;
    }
}
