package gui.pretragaKnjiga;

import entities.Knjiga;
import entities.PrimerakKnjige;
import net.miginfocom.swing.MigLayout;
import repository.Fabrika;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;
import java.util.List;

public class PregledPrimerakaKnjigeDialog extends JDialog {
    private Fabrika repo;
    private Knjiga knjiga;
    private JTable tabela;
    private TableRowSorter<AbstractTableModel> tabelaSorter = new TableRowSorter<>();
    protected List<PrimerakKnjige> data;


    private Map<Integer, Integer> sortOrder = new HashMap<Integer, Integer>() {{
        put(0, 1);
        put(1, 1);
        put(2, 1);
        put(3, 1);
    }};

    public PregledPrimerakaKnjigeDialog(Fabrika repo, Knjiga k) {
        this.repo = repo;
        this.knjiga = k;
        this.tabela = new JTable(new PregledPrimerakaKnjigeModel(k.getPrimerci()));
        initDialog();
    }



    private void initDialog() {
        this.setTitle("Pregled primeraka knjige: " + knjiga.getNaziv());
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

        /*tfPretraga =  new JTextField(20);
        add(pretragaPanel(), BorderLayout.SOUTH);*/
    }

    public void refresh() {
        PregledPrimerakaKnjigeModel m = (PregledPrimerakaKnjigeModel) this.tabela.getModel();
        m.fireTableDataChanged();
    }

    protected void sort(int index) {
        // index of table column

        this.data.sort(new Comparator<PrimerakKnjige>() {
            int retVal = 0;

            public int compare(PrimerakKnjige p1, PrimerakKnjige p2) {
                switch (index) {
                    case 0:
                        retVal = Integer.compare(p1.getId(), p2.getId());
                        break;
                    case 1:
                        retVal = Boolean.compare(p1.isPozajmljen(), p2.isPozajmljen());
                        break;
                    case 2:
                        retVal = Boolean.compare(p1.isOstecen(), p2.isOstecen());
                        break;
                    case 3:
                        retVal = Boolean.compare(p1.isPopravljaSe(), p2.isPopravljaSe());
                        break;
                    default:
                        System.out.println("Prosirena tabela");
                        System.exit(1);
                        break;
                }
                return retVal * sortOrder.get(index);
            }
        });
        sortOrder.put(index, sortOrder.get(index) * -1);
        refresh();
    }

}
