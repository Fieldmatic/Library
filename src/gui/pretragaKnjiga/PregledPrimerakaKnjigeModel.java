package gui.pretragaKnjiga;

import entities.PrimerakKnjige;
import gui.bibliotekar.pozajmice.DataModel;

import java.util.List;

public class PregledPrimerakaKnjigeModel  extends DataModel<PrimerakKnjige> {
    public PregledPrimerakaKnjigeModel(List<PrimerakKnjige> data) {
        super(data);
        imeKolona = new String[]{"Id primerka", "Pozajmljen", "Ostecen", "Na popravci"};
    }
    
     @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        PrimerakKnjige pK = data.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return pK.getId();
            case 1:
                return pK.isPozajmljen();
            case 2:
                return pK.isOstecen();
            case 3:
                return pK.isPopravljaSe();
            default:
                return null;
        }
    }
}
