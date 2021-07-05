package gui.bibliotekar.katalogizacija;

import entities.*;
import enumerations.UlogaAutora;
import enumerations.Zanr;
import gui.clan.PregledIstorijeIznajmljivanja;
import repository.Fabrika;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DodavanjeAutoraKnjige extends JFrame  {
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField ime;
    private JTextField prezime;
    private List<Autorstvo> noviAutori = new ArrayList<>();

    public static void main(Fabrika fabrika, Knjiga knjiga) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    DodavanjeAutoraKnjige frame = new DodavanjeAutoraKnjige(fabrika, knjiga);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public DodavanjeAutoraKnjige(Fabrika fabrika, Knjiga knjiga) {
        setTitle("Dodavanje autora knjige");
        setIconImage(Toolkit.getDefaultToolkit().getImage(DodavanjeKnjigeProzor.class.getResource("/slike/logo.jpg")));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 650, 515);
        contentPane = new JPanel();
        contentPane.setBackground(Color.LIGHT_GRAY);
        contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        ime = new JTextField();
        ime.setForeground(Color.WHITE);
        ime.setFont(new Font("Yu Gothic", Font.BOLD, 13));
        ime.setOpaque(false);
        ime.setBounds(310, 60, 140, 20);
        contentPane.add(ime);
        ime.setColumns(10);

        prezime = new JTextField();
        prezime.setForeground(Color.WHITE);
        prezime.setFont(new Font("Yu Gothic", Font.BOLD, 13));
        prezime.setOpaque(false);
        prezime.setBounds(310, 90, 140, 20);
        contentPane.add(prezime);
        prezime.setColumns(10);

        JLabel imeL = new JLabel("Ime autora:");
        imeL.setForeground(Color.WHITE);
        imeL.setFont(new Font("Yu Gothic", Font.BOLD, 13));
        imeL.setBounds(200, 62, 130, 14);
        contentPane.add(imeL);

        JLabel prezimeL = new JLabel("Prezime autora:");
        prezimeL.setForeground(Color.WHITE);
        prezimeL.setFont(new Font("Yu Gothic", Font.BOLD, 13));
        prezimeL.setBounds(200, 92, 130, 14);
        contentPane.add(prezimeL);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportBorder(null);
        scrollPane.setBounds(200, 120, 250, 110);
        contentPane.add(scrollPane);

        DefaultTableModel tableModel = new DefaultTableModel() {

            private static final long serialVersionUID = 1L;
            @Override
            public Class<?> getColumnClass(int column){
                switch(column) {
                    case 0:
                        return String.class;
                    default:
                        return String.class;
                }
            }
        };
        tableModel.addColumn("Uloga autora");
        int i = 0;
        for (UlogaAutora ulogaAutora: UlogaAutora.values()) {
            tableModel.addRow(new Object[1]);
            tableModel.setValueAt(ulogaAutora, i, 0);
            i++;
        }
        JTable tabelaUloga = new JTable(tableModel);
        tabelaUloga.setFillsViewportHeight(true);
        tabelaUloga.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabelaUloga.setFont(new Font("Yu Gothic", Font.BOLD, 13));
        JTableHeader header = tabelaUloga.getTableHeader();
        header.setFont(new Font("Yu Gothic", Font.BOLD, 12));
        scrollPane.setViewportView(tabelaUloga);

        JButton dodajAutora = new JButton("Dodaj autora");
        dodajAutora.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                    String imeNovogAutora = ime.getText();
                    String prezimeNovogAutora = prezime.getText();
                    int izabranRed = tabelaUloga.getSelectedRow();
                    if (izabranRed == -1) {
                        JOptionPane.showMessageDialog(contentPane, "Morate uneti ulogu autora. Molim Vas, pokusajte ponovo!", "Greska", JOptionPane.ERROR_MESSAGE);
                    } else if (imeNovogAutora.equals("") || prezimeNovogAutora.equals("")) {
                        JOptionPane.showMessageDialog(contentPane,"Prazna polja nisu dozvoljena. Molim Vas, pokušajte ponovo!","Greška",JOptionPane.ERROR_MESSAGE);
                    } else if (!fabrika.getProvere().validacijaSlova(imeNovogAutora) || !fabrika.getProvere().validacijaSlova(prezimeNovogAutora)) {
                        JOptionPane.showMessageDialog(contentPane,"Imena i prezimena autora moraju da sadrže samo slova. Molim Vas, pokušajte ponovo!","Greška",JOptionPane.ERROR_MESSAGE);
                    } else {
                        String vrednost = tabelaUloga.getModel().getValueAt(izabranRed, tabelaUloga.getSelectedColumn()).toString();
                        noviAutori.add(new Autorstvo(new Autor(imeNovogAutora, prezimeNovogAutora), UlogaAutora.valueOf(vrednost)));
                        knjiga.setAutori(noviAutori);
                        prezime.setText("");
                        ime.setText("");
                    }
            }
        });
        dodajAutora.setBounds(170, 280, 150, 40);
        dodajAutora.setFont(new Font("Yu Gothic", Font.BOLD, 12));
        contentPane.add(dodajAutora);

        JButton dodajKnjigu = new JButton("Dodaj knjigu");
        dodajKnjigu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    if (noviAutori.isEmpty()) {
                        JOptionPane.showMessageDialog(contentPane, "Morate uneti barem jednog autora i njegovu ulogu. Molim Vas, pokusajte ponovo!", "Greska", JOptionPane.ERROR_MESSAGE);
                    } else if (!fabrika.getMenadzerKnjiga().knjigaImaPisca(knjiga)) {
                        JOptionPane.showMessageDialog(contentPane, "Morate uneti pisca knjige. Molim Vas, pokusajte ponovo!", "Greska", JOptionPane.ERROR_MESSAGE);
                    } else {
                        fabrika.getMenadzerKnjiga().dodajKnjigu(knjiga);
                        PrimerakKnjige noviPrimerak = new PrimerakKnjige(fabrika.getMenadzerKnjiga().dobaviSlobodanIdPrimeraka(), knjiga);
                        knjiga.dodajNoviPrimerak(noviPrimerak);
                        fabrika.getMenadzerKnjiga().azurirajFajl();
                        JOptionPane.showMessageDialog(contentPane, "Uspesno ste dodali novu knjigu.", "Obavestenje", JOptionPane.INFORMATION_MESSAGE);
                        DodavanjeAutoraKnjige.this.setVisible(false);
                        DodavanjeAutoraKnjige.this.dispose();
                    }
                } catch (
            IOException ioException) {
                ioException.printStackTrace();
            }
            }
        });
        dodajKnjigu.setBounds(330, 280, 150, 40);
        dodajKnjigu.setFont(new Font("Yu Gothic", Font.BOLD, 12));
        contentPane.add(dodajKnjigu);

        JLabel Background = new JLabel("");
        Background.setIcon(new ImageIcon(DodavanjeAutoraKnjige.class.getResource("/slike/backgroundTabela.jpg")));
        Background.setBounds(0, 0, 1189, 386);
        contentPane.add(Background);
    }

}
