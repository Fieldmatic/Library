package entities;


import com.fasterxml.jackson.annotation.JsonBackReference;

public class PrimerakKnjige {
    private int id;
    @JsonBackReference
    private Knjiga knjiga;
    private boolean popravljaSe = false;
    private boolean pozajmljen = false;
    private boolean ostecen = false;

    public PrimerakKnjige() {}

    public PrimerakKnjige(int id, Knjiga knjiga, boolean popravljaSe, boolean pozajmljen, boolean ostecen) {
        this.id = id;
        this.knjiga = knjiga;
        this.popravljaSe = popravljaSe;
        this.pozajmljen = pozajmljen;
        this.ostecen = ostecen;
    }

    public PrimerakKnjige(int id, Knjiga knjiga) {
        this.id = id;
        this.knjiga = knjiga;
    }

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
