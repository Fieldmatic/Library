package gui.pregledKnjiga;

import entities.Autorstvo;
import gui.bibliotekar.pozajmice.DataModel;

import java.util.List;

public class PregledAutoraKnjigeModel extends DataModel<Autorstvo> {
    public PregledAutoraKnjigeModel(List<Autorstvo> data) {
        super(data);
        imeKolona = new String[]{"Ime", "Prezime", "Uloga"};
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Autorstvo c = data.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return c.getAutor().getIme();
            case 1:
                return c.getAutor().getPrezime();
            case 2:
                return c.getUloga();
            default:
                return null;
        }
    }
}
