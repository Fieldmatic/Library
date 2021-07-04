package gui.clan;

import entities.Knjiga;
import entities.Pozajmica;
import entities.Recenzija;
import enumerations.StatusPozajmice;
import gui.pregledKnjiga.PregledAutoraKnjigeDialog;
import repository.Fabrika;
import userEntities.Clan;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.time.LocalDate;

public class PregledIstorijeIznajmljivanja extends JFrame {


    private static final long serialVersionUID = -2632112847009848357L;
    private JPanel contentPane;

    public static void main(Fabrika fabrika, Clan clan) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    PregledIstorijeIznajmljivanja frame = new PregledIstorijeIznajmljivanja(fabrika, clan);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }


    public PregledIstorijeIznajmljivanja(Fabrika fabrika, Clan clan) {
        setResizable(false);
        setTitle("Pregled istorije pozajmljivanja");
        setIconImage(Toolkit.getDefaultToolkit().getImage(PregledIstorijeIznajmljivanja.class.getResource("/slike/logo.jpg")));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 1195, 417);
        contentPane = new JPanel();
        contentPane.setOpaque(false);
        contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportBorder(null);
        scrollPane.setBackground(Color.BLACK);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.setViewportBorder(BorderFactory.createEmptyBorder());
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setOpaque(false);
        scrollPane.setBounds(0, 0, 1189, 386);
        contentPane.add(scrollPane);

        DefaultTableModel tableModel = new DefaultTableModel() {

            boolean[] canEdit = new boolean[]{
                    false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }

            private static final long serialVersionUID = 1L;
            @Override
            public Class<?> getColumnClass(int column){
                switch(column) {
                    case 0:
                        return int.class;
                    case 2:
                        return String.class;
                    case 3:
                        return LocalDate.class;
                    default:
                        return StatusPozajmice.class;
                }
            }
        };
        tableModel.addColumn("ID");
        tableModel.addColumn("ID Primerka");
        tableModel.addColumn("Naziv knjige");
        tableModel.addColumn("Datum pozajmljivanja");
        tableModel.addColumn("Rok za vracanje");
        tableModel.addColumn("Status");
        tableModel.addColumn("Recenzija");

        int i = 0;
        for (Pozajmica p: clan.getPozajmice()) {
            tableModel.addRow(new Object[1]);
            tableModel.setValueAt(p.getId(), i, 0);
            tableModel.setValueAt(p.getPozajmljenPrimerak().getId(), i, 1);
            tableModel.setValueAt(p.getPozajmljenPrimerak().getKnjiga().getNaziv(), i, 2);
            tableModel.setValueAt(p.getDatumPocetka(), i, 3);
            tableModel.setValueAt(p.getDatumKraja(), i, 4);
            tableModel.setValueAt(p.getStatus().toString(), i, 5);
            tableModel.setValueAt("nije ostavljena", i, 6);
            for(Recenzija r : p.getPozajmljenPrimerak().getKnjiga().getRecenzije()){
                if (r.getClan().getNalog().getKorisnickoIme().equals(clan.getNalog().getKorisnickoIme())){
                    tableModel.setValueAt("ostavljena", i, 6);
                }
            }
            i++;
            }

        JTable table = new JTable(tableModel);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.setOpaque(false);
        table.setGridColor(Color.BLACK);
        table.setBorder(null);
        table.getTableHeader().setOpaque(false);
        table.getTableHeader().setBackground(Color.GRAY);
        table.setForeground(Color.BLACK);
        table.setSurrendersFocusOnKeystroke(true);
        table.setSelectionBackground(Color.GRAY);
        table.setSelectionForeground(Color.WHITE);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setFillsViewportHeight(true);
        ((DefaultTableCellRenderer) table.getDefaultRenderer(Object.class)).setOpaque(false);
        table.setFont(new Font("Yu Gothic", Font.BOLD, 13));
        table.setBackground(Color.WHITE);
        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
        cellRenderer.setHorizontalAlignment(JLabel.CENTER);
        cellRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        cellRenderer.setOpaque(false);
        cellRenderer.setBackground(Color.WHITE);

        table.getColumnModel().getColumn(0).setPreferredWidth(10);
        table.getColumnModel().getColumn(0).setCellRenderer(cellRenderer);
        table.getColumnModel().getColumn(1).setPreferredWidth(10);
        table.getColumnModel().getColumn(1).setCellRenderer(cellRenderer);
        table.getColumnModel().getColumn(2).setPreferredWidth(50);
        table.getColumnModel().getColumn(2).setCellRenderer(cellRenderer);
        table.getColumnModel().getColumn(3).setPreferredWidth(35);
        table.getColumnModel().getColumn(3).setCellRenderer(cellRenderer);
        table.getColumnModel().getColumn(4).setPreferredWidth(35);
        table.getColumnModel().getColumn(4).setCellRenderer(cellRenderer);
        table.getColumnModel().getColumn(5).setPreferredWidth(35);
        table.getColumnModel().getColumn(5).setCellRenderer(cellRenderer);
        table.getColumnModel().getColumn(6).setPreferredWidth(35);
        table.getColumnModel().getColumn(6).setCellRenderer(cellRenderer);

        scrollPane.setViewportView(table);

        table.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    int row = table.getSelectedRow();
                    if (row == -1)
                        JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greška", JOptionPane.WARNING_MESSAGE);
                    else {
                        Knjiga k = fabrika.getMenadzerKnjiga().pronadjiPrimerakPoId((int) table.getValueAt(row, 1)).getKnjiga();
                        new PregledAutoraKnjigeDialog(k);
                    }
                }
            }
        });


        JLabel Background = new JLabel("");
        Background.setIcon(new ImageIcon(PregledIstorijeIznajmljivanja.class.getResource("/slike/backgroundTabela.jpg")));
        Background.setBounds(0, 0, 1189, 386);
        contentPane.add(Background);
    }
}
