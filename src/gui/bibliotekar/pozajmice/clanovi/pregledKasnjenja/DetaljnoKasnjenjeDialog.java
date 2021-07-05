package gui.bibliotekar.pozajmice.clanovi.pregledKasnjenja;

import entities.Pozajmica;
import enumerations.StatusPozajmice;
import gui.bibliotekar.pozajmice.clanovi.pregledClanova.PregledClanovaModel;
import gui.pretragaKnjiga.PozadinskiPanel;
import net.miginfocom.swing.MigLayout;
import userEntities.Clan;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.List;
import java.util.*;

public class DetaljnoKasnjenjeDialog extends JDialog {
    protected List<Pozajmica> data;
    protected JTable tabela;
    protected JTextField tfPretraga;
    protected TableRowSorter<AbstractTableModel> tabelaSorter = new TableRowSorter<>();

    public DetaljnoKasnjenjeDialog(Clan c) {
        this.data = getKasnjenja(c);
        this.tabela = new JTable(new PregledKasnjenjaModel(this.data));
        initDialog();
    }

    private List<Pozajmica> getKasnjenja(Clan c) {
        List<Pozajmica> ret = new ArrayList<>();
        for (Pozajmica p : c.getPozajmice()) {
            if (p.getStatus().equals(StatusPozajmice.istekla))
                ret.add(p);
        }
        return ret;
    }

    private void initDialog() {
            this.setTitle("Pregled clanova");
            this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            this.setLocationRelativeTo(null);
            initGUI();
            this.setVisible(true);
    }

    private void initGUI() {
        MigLayout layout = new MigLayout();
        setLayout(layout);

        justifyContent(SwingConstants.LEFT);
        tabela.setFont(new Font("Yu Gothic", Font.BOLD, 12));
        tabela.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabela.getTableHeader().setReorderingAllowed(false);
        tabelaSorter.setModel((AbstractTableModel) tabela.getModel());
        tabela.setRowSorter(tabelaSorter);
        tabela.setOpaque(false);

        JScrollPane sc = new JScrollPane(tabela);
        sc.setViewportView(tabela);
        sc.getViewport().setOpaque(false);
        sc.setOpaque(false);

        tabela.setGridColor(Color.BLACK);
        tabela.setBorder(null);
        tabela.getTableHeader().setOpaque(false);
        tabela.getTableHeader().setBackground(Color.GRAY);
        tabela.setForeground(Color.BLACK);
        tabela.setSurrendersFocusOnKeystroke(true);
        tabela.setSelectionBackground(Color.GRAY);
        tabela.setSelectionForeground(Color.WHITE);
        tabela.setFillsViewportHeight(true);
        ((DefaultTableCellRenderer) tabela.getDefaultRenderer(Object.class)).setOpaque(false);
        tabela.setFont(new Font("Yu Gothic", Font.BOLD, 13));
        tabela.setBackground(Color.WHITE);
        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
        cellRenderer.setHorizontalAlignment(JLabel.CENTER);
        cellRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        cellRenderer.setOpaque(false);
        cellRenderer.setBackground(Color.WHITE);
        tabela.getColumnModel().getColumn(0).setCellRenderer(cellRenderer);
        tabela.getColumnModel().getColumn(1).setCellRenderer(cellRenderer);
        tabela.getColumnModel().getColumn(2).setCellRenderer(cellRenderer);
        tabela.getColumnModel().getColumn(3).setCellRenderer(cellRenderer);

        PozadinskiPanel panel = new PozadinskiPanel("src/slike/backgroundTabela.jpg");
        panel.setLayout(layout);
        panel.add(sc, "push, grow");
        add(panel, "push, grow");
        this.setSize(new Dimension(1000, 600));


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

    private void justifyContent(int justify) {
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setHorizontalAlignment(justify);
        for (int i = 0; i < tabela.getColumnCount(); i++) {
            tabela.getColumnModel().getColumn(i).setCellRenderer(renderer);
        }

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
    }};

    protected void sort(int index) {
        // index of table column

        this.data.sort(new Comparator<Pozajmica>() {
            int retVal = 0;

            public int compare(Pozajmica p1, Pozajmica p2) {
                switch (index) {
                    case 0:
                        retVal = Integer.compare(p1.getPozajmljenPrimerak().getKnjiga().getId(), p2.getPozajmljenPrimerak().getKnjiga().getId());
                        break;
                    case 1:
                        retVal = p1.getPozajmljenPrimerak().getKnjiga().getNaziv().compareTo(p2.getPozajmljenPrimerak().getKnjiga().getNaziv());
                        break;
                    case 2:
                        retVal =  Integer.compare(p1.getPozajmljenPrimerak().getId(), p2.getPozajmljenPrimerak().getId());
                    case 3:
                        retVal = Integer.compare(Period.between(LocalDate.from(LocalDateTime.now()), p1.getDatumKraja()).getDays(), Period.between(LocalDate.from(LocalDateTime.now()), p1.getDatumKraja()).getDays());
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
