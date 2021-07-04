package gui.pregledKnjiga;

import entities.Knjiga;
import gui.Prijavljivanje;
import net.miginfocom.swing.MigLayout;
import repository.Fabrika;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PregledKnjigaDialog extends JDialog  {
    protected Fabrika repo;
    protected List<Knjiga> data;
    protected JTable tabela;
    protected JTextField tfPretraga;
    protected TableRowSorter<AbstractTableModel> tabelaSorter = new TableRowSorter<>();
    protected JLabel lblInfo;

    public PregledKnjigaDialog(Fabrika repo, List<Knjiga> data) {
        this.repo = repo;
        this.data = data;
        this.tabela = new JTable(new PregledKnjigaModel(this.data));
        initDialog();
        initActions();
    }

    private void initDialog() {
        this.setTitle("Pregled knjiga");
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
        PregledKnjigaModel m = (PregledKnjigaModel) this.tabela.getModel();
        m.fireTableDataChanged();
    }

    private Map<Integer, Integer> sortOrder = new HashMap<Integer, Integer>() {{
        put(0, 1);
        put(1, 1);
        put(2, 1);
        put(3, 1);
        put(4, 1);
        put(5, 1);
    }};

    protected void sort(int index) {
        // index of table column

        this.data.sort(new Comparator<Knjiga>() {
            int retVal = 0;

            public int compare(Knjiga c1, Knjiga c2) {
                switch (index) {
                    case 0:
                        retVal = c1.getNaziv().compareTo(c2.getNaziv());
                        break;
                    case 1:
                        retVal = c1.getDatumIzdanja().compareTo(c2.getDatumIzdanja());
                    case 2:
                        retVal = c1.getSadrzaj().getNaziv().compareTo(c2.getSadrzaj().getNaziv());
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

    private JPanel pretragaPanel() {
        JPanel p = new JPanel();
        p.setBackground(Color.YELLOW);
        JLabel lblPretraga = new JLabel("Pretraga:");
        lblPretraga.setFont(new Font("Yu Gothic", Font.BOLD, 12));
//        lblPretraga.setIcon(new ImageIcon(BibliotekarPozajmice.class.getResource("/slike/pretraga.png")));

        lblInfo = new JLabel("Za informacije o autorima izaberite knjigu.");
        lblInfo.setBounds(500, 100, 65, 58);
        lblInfo.setIcon(new ImageIcon(Prijavljivanje.class.getResource("/slike/notification.png")));
        p.add(lblPretraga);
        p.add(tfPretraga);
        p.add(lblInfo);

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

    protected void initActions() {
        tabela.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    int row = tabela.getSelectedRow();
                    if (row == -1)
                        JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greška", JOptionPane.WARNING_MESSAGE);
                    else {
                        Knjiga k = repo.getMenadzerKnjiga().pronadjiKnjiguPoId((int) tabela.getValueAt(row, 0));
                        new PregledAutoraKnjigeDialog(k);
                    }
                }
            }
        });
    }
}
