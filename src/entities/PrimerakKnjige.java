package entities;

public class PrimerakKnjige {
    private int id;
    private Knjiga knjiga;
    private boolean popravljaSe;
    private boolean pozajmljen;
    private boolean ostecen;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Knjiga getKnjiga() {
        return knjiga;
    }

    public void setKnjiga(Knjiga knjiga) {
        this.knjiga = knjiga;
    }

    public boolean isPopravljaSe() {
        return popravljaSe;
    }

    public void setPopravljaSe(boolean popravljaSe) {
        this.popravljaSe = popravljaSe;
    }

    public boolean isPozajmljen() {
        return pozajmljen;
    }

    public void setPozajmljen(boolean pozajmljen) {
        this.pozajmljen = pozajmljen;
    }

    public boolean isOstecen() {
        return ostecen;
    }

    public void setOstecen(boolean ostecen) {
        this.ostecen = ostecen;
    }

    @Override
    public String toString() {
        return "PrimerakKnjige{" +
                "id=" + id +
                ", knjiga=" + knjiga +
                ", popravljaSe=" + popravljaSe +
                ", pozajmljen=" + pozajmljen +
                ", ostecen=" + ostecen +
                '}';
    }
}
