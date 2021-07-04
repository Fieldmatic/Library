package gui.pretragaKnjiga;

import entities.Knjiga;
import entities.PrimerakKnjige;
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

public class PregledPrimerakaKnjigeDialog extends JDialog {
    private Fabrika repo;
    private Knjiga knjiga;
    private JTable tabela;
    private JTextField tfPretraga;
    private JPanel pretragaPanel;
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
        initActions();
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

    private JPanel pretragaPanel() {
        pretragaPanel = new JPanel();
        pretragaPanel.setBackground(Color.YELLOW);
        JLabel lblPretraga = new JLabel("Pretraga:");
        lblPretraga.setFont(new Font("Yu Gothic", Font.BOLD, 12));
//        lblPretraga.setIcon(new ImageIcon(BibliotekarPozajmice.class.getResource("/slike/pretraga.png")));
        pretragaPanel.add(lblPretraga);

        pretragaPanel.add(tfPretraga);

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

        return pretragaPanel;
    }

    private void initActions() {
        tabela.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    int row = tabela.getSelectedRow();
                    if (row == -1)
                        JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greška", JOptionPane.WARNING_MESSAGE);
                    else {
                        PrimerakKnjige p = repo.getMenadzerKnjiga().pronadjiPrimerakPoId((Integer) tabela.getValueAt(row, 0));
                        if (p.isPozajmljen() || p.isPopravljaSe())
                            JOptionPane.showMessageDialog(null, "Izabran primerak nije dostupan za pozajmljenje.", "Greška", JOptionPane.WARNING_MESSAGE);
                        else {
                            /*try {
                                Clan c = repo.getMenadzerClanova().pronadjiClanaPoKorImenu(JOptionPane.showInputDialog("Korisnicko ime clana: "));
                            } catch (NullPointerException e) {
                                JOptionPane.showMessageDialog(null, "Clan nije pronadjen.", "Greška", JOptionPane.WARNING_MESSAGE);
                            }
                            repo.getMenadzerPozajmica().kreirajPozajmicu(p, repo.getMenadzerClanova());*/
                        }
                    }
                }
            }
        });
    }

}
