package gui.bibliotekar.pozajmice.rezervacije;

import entities.Rezervacija;
import gui.bibliotekar.pozajmice.DataModel;
import repository.Fabrika;

import java.util.List;

public class PregledRezervacijaModel extends DataModel<Rezervacija> {

    private final Fabrika repo;

    public PregledRezervacijaModel(Fabrika repo, List<Rezervacija> data) {
        super(data);
        this.repo = repo;
        imeKolona = new String[]{"Id rezervacije", "Datum rezervacije", "Id primerka knjige", "Naziv knjige"};
    }

     @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Rezervacija r = data.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return r.getId();
            case 1:
                return r.getDatum();
            case 2:
                return r.getRezervisanPrimerak().getId();
            case 3:
                return r.getRezervisanPrimerak().getKnjiga().getNaziv();
            default:
                return null;
        }
    }


}
