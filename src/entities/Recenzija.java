package entities;

public class Recenzija {
    private float ocena;
    private String komentar;

    public Recenzija() {}

    public Recenzija(float ocena, String komentar) {
        this.ocena = ocena;
        this.komentar = komentar;
    }

    public float getOcena() {
        return ocena;
    }

    public void setOcena(float ocena) {
        this.ocena = ocena;
    }

    public String getKomentar() {
        return komentar;
    }

    public void setKomentar(String komentar) {
        this.komentar = komentar;
    }
}
