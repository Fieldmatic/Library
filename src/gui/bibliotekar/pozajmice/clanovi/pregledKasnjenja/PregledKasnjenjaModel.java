package gui.bibliotekar.pozajmice.clanovi.pregledKasnjenja;

import entities.Pozajmica;
import gui.bibliotekar.pozajmice.DataModel;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.List;

public class PregledKasnjenjaModel extends DataModel<Pozajmica> {
    public PregledKasnjenjaModel(List<Pozajmica> data) {
        super(data);
        imeKolona = new String[]{"Id knjige", "Naziv knjige", "Id pozajmice", "Br. dana kasnjenja"};
    }

     @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Pozajmica p = data.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return p.getPozajmljenPrimerak().getKnjiga().getId();
            case 1:
                return p.getPozajmljenPrimerak().getKnjiga().getNaziv();
            case 2:
                return p.getId();
            case 3:
                return Period.between(LocalDate.from(LocalDateTime.now()), p.getDatumKraja()).getDays();
            default:
                return null;
        }
    }
}

