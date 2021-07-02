package gui.bibliotekar.pozajmice;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public abstract class DataModel<T> extends AbstractTableModel {

    protected List<T> data;
    protected String[] imeKolona;

    public DataModel(List<T> data) {
        this.data = data;
    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        return imeKolona.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return null;
    }

    @Override
    public String getColumnName(int column) {
        return this.imeKolona[column];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return getValueAt(0, columnIndex).getClass();
    }
}
