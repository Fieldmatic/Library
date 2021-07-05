package gui.bibliotekar.pozajmice.clanovi.pregledClanova;

import gui.pretragaKnjiga.PozadinskiPanel;
import net.miginfocom.swing.MigLayout;
import repository.Fabrika;
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
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PregledClanovaDialog extends JDialog {

    protected Fabrika repo;
    protected List<Clan> data;
    protected JTable tabela;
    protected JPanel pretragaPanel;
    protected JTextField tfPretraga;
    protected JLabel lblInfo;
    protected TableRowSorter<AbstractTableModel> tabelaSorter = new TableRowSorter<>();

    public PregledClanovaDialog(Fabrika repo, List<Clan> data) {
        this.repo = repo;
        this.data = data;
        this.tabela = new JTable(new PregledClanovaModel(this.data));
        initDialog();
    }

    private void initDialog() {
        this.setTitle("Pregled clanova");
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        initGUI();
        this.setVisible(true);
    }

    protected void initGUI() {
        MigLayout layout = new MigLayout();
        setLayout(layout);

        tabela.setFont(new Font("Yu Gothic", Font.BOLD, 12));
        tabela.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabela.getTableHeader().setReorderingAllowed(false);
        tabela.setOpaque(false);
        tabelaSorter.setModel((AbstractTableModel) tabela.getModel());
        tabela.setRowSorter(tabelaSorter);
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
        tabela.getColumnModel().getColumn(4).setCellRenderer(cellRenderer);
        tabela.getColumnModel().getColumn(5).setCellRenderer(cellRenderer);

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
        put(5, 1);
    }};

    protected void sort(int index) {
        // index of table column

        this.data.sort(new Comparator<Clan>() {
            int retVal = 0;

            public int compare(Clan c1, Clan c2) {
                switch (index) {
                    case 0:
                        retVal = c1.getNalog().getKorisnickoIme().compareTo(c2.getNalog().getKorisnickoIme());
                        break;
                    case 1:
                        retVal = c1.getIme().compareTo(c2.getIme());
                        break;
                    case 2:
                        retVal = c1.getPrezime().compareTo(c2.getPrezime());
                    case 3:
                        retVal = c1.getDatumRodjenja().compareTo(c2.getDatumRodjenja());
                        break;
                    case 4:
                        retVal = c1.getClanarina().getTip().compareTo(c2.getClanarina().getTip());
                        break;
                    case 5:
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
}
