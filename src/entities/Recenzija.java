package entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import userEntities.Clan;

public class Recenzija {
    private int ocena;
    private String komentar;
    private Clan clan;
    @JsonBackReference
    private Knjiga knjiga;

    public Recenzija() {}

    public Recenzija(int ocena, String komentar, Clan clan, Knjiga knjiga) {
        this.ocena = ocena;
        this.komentar = komentar;
        this.clan = clan;
        this.knjiga = knjiga;
    }

    public int getOcena() {
        return ocena;
    }

    public void setOcena(int ocena) {
        this.ocena = ocena;
    }

    public String getKomentar() {
        return komentar;
    }

    public void setKomentar(String komentar) {
        this.komentar = komentar;
    }

    @Override
    public String toString() {
        return "Recenzija{" +
                "ocena=" + ocena +
                ", komentar='" + komentar + '\'' +
                '}';
    }

    public Clan getClan() {
        return clan;
    }

    public void setClan(Clan clan) {
        this.clan = clan;
    }

    public Knjiga getKnjiga() {
        return knjiga;
    }

    public void setKnjiga(Knjiga knjiga) {
        this.knjiga = knjiga;
    }
}
