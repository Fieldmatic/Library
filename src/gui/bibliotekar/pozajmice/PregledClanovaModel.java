package gui.bibliotekar.pozajmice;

import userEntities.Clan;

import java.util.List;

public class PregledClanovaModel extends DataModel<Clan> {

    public PregledClanovaModel(List<Clan> data) {
        super(data);
        imeKolona = new String[]{"Ime", "Prezime", "Dat. rodj.", "Tip Clana", "Dat. istek."};
    }

 @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Clan c = data.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return c.getIme();
            case 1:
                return c.getPrezime();
            case 2:
                return c.getDatumRodjenja();
            case 3:
                return c.getClanarina().getTip();
            case 4:
                return c.getClanarina().getDatumKraja();
            default:
                return null;
        }
    }
}
