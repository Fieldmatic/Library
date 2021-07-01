package entities;

public class Recenzija {
    private int ocena;
    private String komentar;

    public Recenzija() {}

    public Recenzija(int ocena, String komentar) {
        this.ocena = ocena;
        this.komentar = komentar;
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
}
