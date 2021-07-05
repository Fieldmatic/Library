package gui.bibliotekar.pozajmice.rezervacije;

import entities.Pozajmica;
import entities.PrimerakKnjige;
import entities.Rezervacija;
import enumerations.StatusPozajmice;
import gui.pretragaKnjiga.PozadinskiPanel;
import net.miginfocom.swing.MigLayout;
import repository.Fabrika;
import userEntities.Clan;

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
import java.io.IOException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PregledRezervacijaDialog extends JDialog {

    private Fabrika repo;
    private Clan clan;
    private JTable tabela;
    private JTextField tfPretraga;
    private JPanel pretragaPanel;
    private TableRowSorter<AbstractTableModel> tabelaSorter = new TableRowSorter<>();
    protected List<Rezervacija> data;


    private Map<Integer, Integer> sortOrder = new HashMap<Integer, Integer>() {{
        put(0, 1);
        put(1, 1);
        put(2, 1);
        put(3, 1);
    }};

    public PregledRezervacijaDialog(Fabrika repo, Clan c) {
        this.repo = repo;
        this.clan = c;
        this.tabela = new JTable(new PregledRezervacijaModel(repo, repo.getMenadzerClanova().dobaviRezervacijeClanaSpremneZaPreuzimanje(c)));
        initDialog();
    }



    private void initDialog() {
        this.setTitle("Pregled rezervacija clana: " + clan.getNalog().getKorisnickoIme());
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
        PozadinskiPanel p = new PozadinskiPanel("src/slike/backgroundTabela.jpg");
        p.add(sc);
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
        PregledRezervacijaModel m = (PregledRezervacijaModel) this.tabela.getModel();
        m.fireTableDataChanged();
    }

    protected void sort(int index) {
        // index of table column

        this.data.sort(new Comparator<Rezervacija>() {
            int retVal = 0;

            public int compare(Rezervacija r1, Rezervacija r2) {
                switch (index) {
                    case 0:
                        retVal = Integer.compare(r1.getId(), r2.getId());
                        break;
                    case 1:
                        retVal = r1.getDatum().compareTo(r2.getDatum());
                        break;
                    case 2:
                        retVal = Integer.compare(r1.getRezervisanPrimerak().getId(), r2.getRezervisanPrimerak().getId());
                        break;
                    case 3:
                        retVal = repo.getMenadzerPozajmica().getPozajmicaPoIdPrimerka(r1.getRezervisanPrimerak().getId()).getStatus()
                                .compareTo(repo.getMenadzerPozajmica().getPozajmicaPoIdPrimerka(r2.getRezervisanPrimerak().getId()).getStatus());
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
                        Rezervacija r = repo.getMenadzerRezervacija().pronadjiRezervacijuPoId((Integer) tabela.getValueAt(row, 0));
                        if (!repo.getMenadzerPozajmica().getPozajmicaPoIdPrimerka(r.getRezervisanPrimerak().getId()).getStatus().equals(StatusPozajmice.vracena))
                            JOptionPane.showMessageDialog(null, "Primerak nije vracen.", "Greška", JOptionPane.WARNING_MESSAGE);
                        else {
                            PrimerakKnjige rezervisanPrimerak = r.getRezervisanPrimerak();
                            Pozajmica p = repo.getMenadzerPozajmica().kreirajPozajmicu(rezervisanPrimerak, clan);
                            if (!clan.uslovPozajmice())
                                JOptionPane.showMessageDialog(null, "Clan: " + clan.getNalog().getKorisnickoIme() + " je posudio maksimalan broj knjiga.", "Greška", JOptionPane.WARNING_MESSAGE);
                            else {
                                rezervisanPrimerak.setPozajmljen(true);
                                clan.dodajPozajmicu(p);
                                try {
                                    repo.getMenadzerPozajmica().dodajPozajmicu(p);
                                } catch (IOException ex) {
                                    ex.printStackTrace();
                                }
                                try {
                                    repo.getMenadzerClanova().azurirajFajl();
                                    repo.getMenadzerKnjiga().azurirajFajl();
                                } catch (IOException ex) {
                                    ex.printStackTrace();
                                }
                                JOptionPane.showMessageDialog(null, "Pozajmica uspesno dodata.", "Pozajmica", JOptionPane.INFORMATION_MESSAGE);
                                PregledRezervacijaDialog.this.dispose();
                            }
                        }
                    }
                }
            }
        });
    }
}
