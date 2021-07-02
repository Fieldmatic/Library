package gui.bibliotekar.pozajmice;

import net.miginfocom.swing.MigLayout;
import repository.Fabrika;
import userEntities.Clan;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PregledClanovaDialog extends JDialog {

    private Fabrika repo;
    private List<Clan> data;
    private JTable tabela;
    private JTextField tfPretraga;
    protected TableRowSorter<AbstractTableModel> tabelaSorter = new TableRowSorter<>();

    public PregledClanovaDialog(Fabrika repo) {
        this.repo = repo;
        this.data = repo.getMenadzerClanova().clanovi;
        this.tabela = new JTable(new PregledClanovaModel(this.data));
        initDialog();
    }

    private void initDialog() {
        this.setTitle("Pregled clanova");
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        initGUI();
        this.pack();
        this.setVisible(true);
    }

    private void initGUI() {
        MigLayout layout = new MigLayout();
        setLayout(layout);

        tabela.setFont(new Font("Yu Gothic", Font.BOLD, 12));
        tabela.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabela.getTableHeader().setReorderingAllowed(false);
        tabelaSorter.setModel((AbstractTableModel) tabela.getModel());
        tabela.setRowSorter(tabelaSorter);
        JScrollPane sc = new JScrollPane(tabela);
        add(sc, "pushx, growx");

        tabela.getTableHeader().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int index = tabela.getTableHeader().columnAtPoint(e.getPoint());
                sort(index);
            }
        });

        tfPretraga =  new JTextField(20);
        add(pretragaPanel(), BorderLayout.SOUTH);
    }

    public void refresh() {
        PregledClanovaModel m = (PregledClanovaModel) this.tabela.getModel();
        m.fireTableDataChanged();
    }

    private Map<Integer, Integer> sortOrder = new HashMap<Integer, Integer>() {{
        put(0, 1);
        put(1, 1);
        put(2, 1);
        put(3, 1);
        put(4, 1);
    }};

    protected void sort(int index) {
        // index of table column

        this.data.sort(new Comparator<Clan>() {
            int retVal = 0;

            public int compare(Clan c1, Clan c2) {
                switch (index) {
                    case 0:
                        retVal = c1.getIme().compareTo(c2.getIme());
                        break;
                    case 1:
                        retVal = c1.getPrezime().compareTo(c2.getPrezime());
                    case 2:
                        retVal = c1.getDatumRodjenja().compareTo(c2.getDatumRodjenja());
                        break;
                    case 3:
                        retVal = c1.getClanarina().getTip().compareTo(c2.getClanarina().getTip());
                        break;
                    case 4:
                        retVal = c1.getClanarina().getDatumKraja().compareTo(c2.getClanarina().getDatumKraja());
                        break;
                    default:
                        System.out.println("Prosirena tabela");
                        System.exit(1);
                        break;
                }
                return retVal * sortOrder.get(index);
            }
        });

        System.out.println("column " + index + " row " + sortOrder.get(index));
        sortOrder.put(index, sortOrder.get(index) * -1);
        refresh();
    }

    private JPanel pretragaPanel() {
        JPanel p = new JPanel();
        p.setBackground(Color.YELLOW);
        JLabel lblPretraga = new JLabel("Pretraga:");
        lblPretraga.setFont(new Font("Yu Gothic", Font.BOLD, 12));
//        lblPretraga.setIcon(new ImageIcon(BibliotekarPozajmice.class.getResource("/slike/pretraga.png")));
        p.add(lblPretraga);

        p.add(tfPretraga);

        tfPretraga.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                changedUpdate(e);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                changedUpdate(e);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                if (tfPretraga.getText().trim().length() == 0) {
                    tabelaSorter.setRowFilter(null);
                } else {
                    tabelaSorter.setRowFilter(RowFilter.regexFilter("(?i)" + tfPretraga.getText().trim()));
                }
            }
        });

        return p;
    }


}
