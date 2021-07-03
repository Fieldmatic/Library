package gui.pregledKnjiga;

import entities.Knjiga;
import gui.bibliotekar.pozajmice.DataModel;

import java.util.List;

public class PregledKnjigaModel extends DataModel<Knjiga>  {
    public PregledKnjigaModel(List<Knjiga> data) {
        super(data);
        imeKolona = new String[]{"Id", "Naziv", "Datum izdanja", "Sadrzaj", "Zanrovi", "Dozvoljeno iznosenje"};
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Knjiga c = data.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return c.getId();
            case 1:
                return c.getNaziv();
            case 2:
                return c.getDatumIzdanja();
            case 3:
                return c.getSadrzaj().getNaziv();
            case 4:
                return c.getSadrzaj().zanroviToString();
            case 5:
                return c.isIznosiSe();
            default:
                return null;
        }
    }
}
