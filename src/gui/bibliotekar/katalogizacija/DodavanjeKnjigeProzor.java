package gui.bibliotekar.katalogizacija;

import com.toedter.calendar.JDateChooser;
import entities.Knjiga;
import entities.SadrzajKnjige;
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
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DodavanjeKnjigeProzor extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField naziv;
    private JTextField visina;
    private JTextField sirina;
    private JTextField izdavac;
    private JTextField tagovi;
    private JTextField nazivSadrzaja;
    private final ButtonGroup iznosenjeDozvola = new ButtonGroup();

    public static void main(Fabrika fabrika) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    DodavanjeKnjigeProzor frame = new DodavanjeKnjigeProzor(fabrika);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public DodavanjeKnjigeProzor(Fabrika fabrika) {
        setTitle("Dodavanje nove knjige");
        setIconImage(Toolkit.getDefaultToolkit().getImage(DodavanjeKnjigeProzor.class.getResource("/slike/logo.jpg")));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 650, 515);
        contentPane = new JPanel();
        contentPane.setBackground(Color.LIGHT_GRAY);
        contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        naziv = new JTextField();
        naziv.setForeground(Color.WHITE);
        naziv.setFont(new Font("Yu Gothic", Font.BOLD, 14));
        naziv.setOpaque(false);
        naziv.setBounds(112, 60, 140, 20);
        contentPane.add(naziv);
        naziv.setColumns(10);

        izdavac = new JTextField();
        izdavac.setForeground(Color.WHITE);
        izdavac.setFont(new Font("Yu Gothic", Font.BOLD, 14));
        izdavac.setOpaque(false);
        izdavac.setBounds(112, 135, 140, 20);
        contentPane.add(izdavac);
        izdavac.setColumns(10);

        visina = new JTextField();
        visina.setForeground(Color.WHITE);
        visina.setFont(new Font("Yu Gothic", Font.BOLD, 14));
        visina.setOpaque(false);
        visina.setBounds(112, 85, 140, 20);
        contentPane.add(visina);
        visina.setColumns(10);

        sirina = new JTextField();
        sirina.setForeground(Color.WHITE);
        sirina.setFont(new Font("Yu Gothic", Font.BOLD, 14));
        sirina.setOpaque(false);
        sirina.setBounds(112, 112, 140, 20);
        contentPane.add(sirina);
        sirina.setColumns(10);

        tagovi = new JTextField();
        tagovi.setForeground(Color.white);
        tagovi.setFont(new Font("Yu Gothic", Font.BOLD, 14));
        tagovi.setOpaque(false);
        tagovi.setBounds(112, 160, 140, 20);
        contentPane.add(tagovi);
        tagovi.setColumns(10);

        nazivSadrzaja = new JTextField();
        nazivSadrzaja.setForeground(Color.WHITE);
        nazivSadrzaja.setFont(new Font("Yu Gothic", Font.BOLD, 14));
        nazivSadrzaja.setOpaque(false);
        nazivSadrzaja.setBounds(112, 212, 140, 20);
        contentPane.add(nazivSadrzaja);
        nazivSadrzaja.setColumns(10);

        JLabel nazivSadrzajaL = new JLabel("Naziv:");
        nazivSadrzajaL.setForeground(Color.WHITE);
        nazivSadrzajaL.setFont(new Font("Yu Gothic", Font.BOLD, 13));
        nazivSadrzajaL.setBounds(10, 62, 130, 14);
        contentPane.add(nazivSadrzajaL);

        JLabel visinaL = new JLabel("Visina:");
        visinaL.setForeground(Color.WHITE);
        visinaL.setFont(new Font("Yu Gothic", Font.BOLD, 13));
        visinaL.setBounds(10, 87, 84, 14);
        contentPane.add(visinaL);

        JLabel sirinaL = new JLabel("Sirina:");
        sirinaL.setForeground(Color.WHITE);
        sirinaL.setFont(new Font("Yu Gothic", Font.BOLD, 13));
        sirinaL.setBounds(10, 112, 46, 14);
        contentPane.add(sirinaL);

        JLabel izdavacL = new JLabel("Izdavac:");
        izdavacL.setForeground(Color.WHITE);
        izdavacL.setFont(new Font("Yu Gothic", Font.BOLD, 13));
        izdavacL.setBounds(10, 137, 74, 14);
        contentPane.add(izdavacL);

        JLabel tagoviL = new JLabel("Tagovi:");
        tagoviL.setForeground(Color.WHITE);
        tagoviL.setFont(new Font("Yu Gothic", Font.BOLD, 13));
        tagoviL.setBounds(10, 163, 85, 14);
        contentPane.add(tagoviL);

        JDateChooser datumIzdanja = new JDateChooser();
        datumIzdanja.setBackground(Color.BLACK);
        datumIzdanja.setForeground(Color.WHITE);
        datumIzdanja.setOpaque(false);
        datumIzdanja.setSize(140, 20);
        datumIzdanja.setLocation(112, 185);
        getContentPane().add(datumIzdanja);

        JLabel datumIzdanjaL = new JLabel("Datum izdanja:");
        datumIzdanjaL.setForeground(Color.WHITE);
        datumIzdanjaL.setFont(new Font("Yu Gothic", Font.BOLD, 13));
        datumIzdanjaL.setBounds(10, 187, 130, 14);
        contentPane.add(datumIzdanjaL);

        JLabel sadrzaj = new JLabel("Naziv sadrzaja:");
        sadrzaj.setForeground(Color.WHITE);
        sadrzaj.setFont(new Font("Yu Gothic", Font.BOLD, 13));
        sadrzaj.setBounds(10, 212, 105, 14);
        contentPane.add(sadrzaj);

        JLabel dozvoljenoIznosenjeL = new JLabel("Dozvoljeno iznošenje:");
        dozvoljenoIznosenjeL.setForeground(Color.WHITE);
        dozvoljenoIznosenjeL.setFont(new Font("Yu Gothic", Font.BOLD, 13));
        dozvoljenoIznosenjeL.setBounds(10, 240, 140, 14);
        contentPane.add(dozvoljenoIznosenjeL);

        JRadioButton dozvoljenoIznosenje = new JRadioButton("Dozvoljeno: ");
        dozvoljenoIznosenje.setForeground(Color.WHITE);
        dozvoljenoIznosenje.setFont(new Font("Yu Gothic", Font.BOLD, 12));
        dozvoljenoIznosenje.setOpaque(false);
        iznosenjeDozvola.add(dozvoljenoIznosenje);
        dozvoljenoIznosenje.setBounds(78, 260, 114, 23);
        dozvoljenoIznosenje.setSelected(true);
        contentPane.add(dozvoljenoIznosenje);

        JRadioButton nijeDozvoljenoIznosenje = new JRadioButton("Nije dozvoljeno");
        nijeDozvoljenoIznosenje.setForeground(Color.WHITE);
        nijeDozvoljenoIznosenje.setFont(new Font("Yu Gothic", Font.BOLD, 12));
        nijeDozvoljenoIznosenje.setOpaque(false);
        iznosenjeDozvola.add(nijeDozvoljenoIznosenje);
        nijeDozvoljenoIznosenje.setBounds(178, 260, 140, 23);
        contentPane.add(nijeDozvoljenoIznosenje);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportBorder(null);
        scrollPane.setBounds(300, 60, 250, 110);
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
        tableModel.addColumn("Zanrovi");
        int i = 0;
        for (Zanr zanr: Zanr.values()) {
            tableModel.addRow(new Object[1]);
            tableModel.setValueAt(zanr, i, 0);
            i++;
        }
        JTable tabelaZanrova = new JTable(tableModel);
        tabelaZanrova.setFillsViewportHeight(true);
        tabelaZanrova.setColumnSelectionAllowed(true);
        tabelaZanrova.setCellSelectionEnabled(true);
        tabelaZanrova.setFont(new Font("Yu Gothic", Font.BOLD, 13));
        JTableHeader header = tabelaZanrova.getTableHeader();
        header.setFont(new Font("Yu Gothic", Font.BOLD, 12));
        scrollPane.setViewportView(tabelaZanrova);

        dozvoljenoIznosenje.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dozvoljenoIznosenje.setSelected(true);
                nijeDozvoljenoIznosenje.setSelected(false);
            }
        });

        nijeDozvoljenoIznosenje.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                nijeDozvoljenoIznosenje.setSelected(true);
                dozvoljenoIznosenje.setSelected(false);
            }
        });

        JButton dodajAutore = new JButton("Dodaj autora");
        dodajAutore.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    if (proveraValidnosti()) {
                        int id = fabrika.getMenadzerKnjiga().dobaviSlobodanIdKnjige();
                        String nazivKnjige = naziv.getText();
                        Dimension dimenzijeKnjige = new Dimension(Integer.parseInt(sirina.getText()), Integer.parseInt(visina.getText()));
                        String izdavacKnjige = izdavac.getText();
                        String nazivSadrzajaKnjige = nazivSadrzaja.getText();
                        LocalDate datumIzdanjaKnjige = datumIzdanja.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                        boolean iznosiSe = !nijeDozvoljenoIznosenje.isSelected();
                        List<String> tagoviKnjige = Arrays.asList(tagovi.getText().split(","));
                        List<Zanr> izabraniZanrovi = new ArrayList<>();
                        int kolona = 0;
                        int[] izabraneKolone = tabelaZanrova.getSelectedRows();
                        for (int red : izabraneKolone) {
                            String vrednost = tabelaZanrova.getModel().getValueAt(red, kolona).toString();
                            izabraniZanrovi.add(Zanr.valueOf(vrednost));
                        }
                        if (izabraniZanrovi.size() == 0) {
                            JOptionPane.showMessageDialog(contentPane, "Morate uneti barem jedan zanr. Molim Vas, pokusajte ponovo!", "Greska", JOptionPane.ERROR_MESSAGE);
                        } else {
                            Knjiga novaKnjiga = new Knjiga(id, nazivKnjige, dimenzijeKnjige, datumIzdanjaKnjige, iznosiSe, izdavacKnjige, new SadrzajKnjige(nazivSadrzajaKnjige, izabraniZanrovi), tagoviKnjige);
                            DodavanjeAutoraKnjige dodavanjeAutoraKnjige = new DodavanjeAutoraKnjige(fabrika, novaKnjiga);
                            dodavanjeAutoraKnjige.setVisible(true);
                            DodavanjeKnjigeProzor.this.setVisible(false);
                            DodavanjeKnjigeProzor.this.dispose();
                        }
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(contentPane,"Morate uneti datum izvodjenja. Molim Vas, pokušajte ponovo!","Greska",JOptionPane.ERROR_MESSAGE);
                }
            }

            public boolean proveraValidnosti() {
                if (naziv.getText().equals("") || izdavac.getText().equals("") || nazivSadrzaja.getText().equals("") || sirina.getText().equals("") || visina.getText().equals("")) {
                    JOptionPane.showMessageDialog(contentPane,"Prazna polja nisu dozvoljena. Molim Vas, pokušajte ponovo!","Greška",JOptionPane.ERROR_MESSAGE);
                    return false;
                }
                else if (!fabrika.getProvere().validacijaSlova(naziv.getText()) || !fabrika.getProvere().validacijaSlova(izdavac.getText()) || !fabrika.getProvere().validacijaSlova(nazivSadrzaja.getText())) {
                    JOptionPane.showMessageDialog(contentPane,"Naziv knjige, naziv sadrzaja knjige i izdavac moraju da sadrže samo slova. Molim Vas, pokušajte ponovo!","Greška",JOptionPane.ERROR_MESSAGE);
                    return false;
                } else if (!fabrika.getProvere().validacijaBrojeva(sirina.getText()) || !fabrika.getProvere().validacijaBrojeva(visina.getText())) {
                    JOptionPane.showMessageDialog(contentPane,"Dimenzije moraju da sadrže samo brojeve. Molim Vas, pokušajte ponovo!","Greška",JOptionPane.ERROR_MESSAGE);
                    return false;
                } else if (!tagovi.getText().contains(",")) {
                    JOptionPane.showMessageDialog(contentPane,"Tagovi moraju biti odvojeni zarezom. Molim Vas, pokušajte ponovo!","Greška",JOptionPane.ERROR_MESSAGE);
                    return false;
                }
                return true;
            }
        });

        dodajAutore.setBounds(300, 186, 150, 40);
        dodajAutore.setFont(new Font("Yu Gothic", Font.BOLD, 12));
        contentPane.add(dodajAutore);

        JLabel Background = new JLabel("");
        Background.setIcon(new ImageIcon(DodavanjeKnjigeProzor.class.getResource("/slike/backgroundTabela.jpg")));
        Background.setBounds(0, 0, 1189, 600);
        contentPane.add(Background);

    }


}
