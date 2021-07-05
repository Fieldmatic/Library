package gui.bibliotekar.pozajmice;

import entities.Rezervacija;
import enumerations.StatusRezervacije;
import gui.clan.PregledIstorijeIznajmljivanja;
import gui.pretragaKnjiga.PozadinskiPanel;
import net.miginfocom.swing.MigLayout;
import repository.Fabrika;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.List;

public class OdobravanjeRezervacije extends JFrame {
    protected Fabrika fabrika;
    protected List<Rezervacija> data;
    protected JTable tabela;

    public OdobravanjeRezervacije(Fabrika fabrika) {
        this.fabrika = fabrika;
        this.data = fabrika.getMenadzerRezervacija().dobaviRezervacijeZaOdobravanje();
        this.tabela = new JTable(new PregledRezervacijaModel(this.data, this.fabrika));
        initDialog();
    }

    private void initDialog() {
        this.setTitle("Odobravanje rezervacija");
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
        tabela.setOpaque(false);
        JScrollPane sc = new JScrollPane(tabela);
        sc.getViewport().setOpaque(false);
        sc.setOpaque(false);

        tabela.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        tabela.setOpaque(false);
        tabela.setGridColor(Color.BLACK);
        tabela.setBorder(null);
        tabela.getTableHeader().setOpaque(false);
        tabela.getTableHeader().setBackground(Color.GRAY);
        tabela.setForeground(Color.BLACK);
        tabela.setSurrendersFocusOnKeystroke(true);
        tabela.setSelectionBackground(Color.GRAY);
        tabela.setSelectionForeground(Color.WHITE);
        tabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
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
        sc.setViewportView(tabela);

        PozadinskiPanel panel = new PozadinskiPanel("src/slike/backgroundTabela.jpg");
        panel.setLayout(layout);
        panel.add(sc, "pushx, growx");
        add(panel, "pushx, growx");


        tabela.getTableHeader().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int index = tabela.getTableHeader().columnAtPoint(e.getPoint());
            }
        });

        add(pretragaPanel(), BorderLayout.SOUTH);
    }

    private JPanel pretragaPanel() {
        JPanel p = new JPanel();
        p.setBackground(Color.YELLOW);

        JButton odobriRez = new JButton("Odobri rezervaciju");
        odobriRez.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int row = tabela.getSelectedRow();
                    if (row == -1)
                        JOptionPane.showMessageDialog(null, "Morate odabrati barem jednu u tabeli.", "Greška", JOptionPane.WARNING_MESSAGE);
                    else {
                        Rezervacija izabranaRezervacija = fabrika.getMenadzerRezervacija().pronadjiRezervacijuPoId((int) tabela.getValueAt(row, 0));
                        izabranaRezervacija.setStatus(StatusRezervacije.odobrena);
                        fabrika.getMenadzerClanova().azurirajFajl();
                        JOptionPane.showMessageDialog(p,"Uspešno ste odobrili rezervaciju.","Obaveštenje",JOptionPane.INFORMATION_MESSAGE);
                    }
                } catch (
            IOException ioException) {
                ioException.printStackTrace();
            }
            }
        });

        JButton odbijRez = new JButton("Odbij rezervaciju");
        odbijRez.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int row = tabela.getSelectedRow();
                    if (row == -1)
                        JOptionPane.showMessageDialog(null, "Morate odabrati barem jednu u tabeli.", "Greška", JOptionPane.WARNING_MESSAGE);
                    else {
                        Rezervacija izabranaRezervacija = fabrika.getMenadzerRezervacija().pronadjiRezervacijuPoId((int) tabela.getValueAt(row, 0));
                        izabranaRezervacija.setStatus(StatusRezervacije.odbijena);
                        fabrika.getMenadzerClanova().azurirajFajl();
                        JOptionPane.showMessageDialog(p,"Uspešno ste odbili rezervaciju.","Obaveštenje",JOptionPane.INFORMATION_MESSAGE);
                    }
                } catch (
                        IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });

        odobriRez.setFont(new Font("Yu Gothic", Font.BOLD, 12));
        odbijRez.setFont(new Font("Yu Gothic", Font.BOLD, 12));
        p.add(odobriRez);
        p.add(odbijRez);
        return p;
    }
}
