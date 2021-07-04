package gui.bibliotekar.pozajmice;

import entities.Rezervacija;
import repository.Fabrika;

import java.util.List;

public class PregledRezervacijaModel extends DataModel<Rezervacija> {
    private Fabrika fabrika;
    public PregledRezervacijaModel(List<Rezervacija> data, Fabrika fabrika) {
        super(data);
        this.fabrika = fabrika;
        imeKolona = new String[]{"Id", "Korisnicko ime", "Rezervisana knjiga", "Datum rezervacije"};
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Rezervacija r = data.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return r.getId();
            case 1:
                return fabrika.getMenadzerRezervacija().dobaviClanaRezervacije(r).getNalog().getKorisnickoIme();
            case 2:
                return r.getRezervisanPrimerak().getKnjiga().getNaziv();
            case 3:
                return r.getDatum();
            default:
                return null;
        }
    }
}

