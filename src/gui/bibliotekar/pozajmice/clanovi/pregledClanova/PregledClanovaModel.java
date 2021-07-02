package gui.bibliotekar.pozajmice.clanovi.pregledClanova;

import gui.bibliotekar.pozajmice.DataModel;
import userEntities.Clan;

import java.util.List;

public class PregledClanovaModel extends DataModel<Clan> {

    public PregledClanovaModel(List<Clan> data) {
        super(data);
        imeKolona = new String[]{"Korisnicko ime", "Ime", "Prezime", "Dat. rodj.", "Tip Clana", "Dat. istek."};
    }

 @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Clan c = data.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return c.getNalog().getKorisnickoIme();
            case 1:
                return c.getIme();
            case 2:
                return c.getPrezime();
            case 3:
                return c.getDatumRodjenja();
            case 4:
                return c.getClanarina().getTip();
            case 5:
                return c.getClanarina().getDatumKraja();
            case 6:
                return c.getJmbg();
            default:
                return null;
        }
    }
}
