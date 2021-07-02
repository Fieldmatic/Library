package gui.bibliotekar.pozajmice;

import repository.Fabrika;
import userEntities.Bibliotekar;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BibliotekarPozajmice extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;


    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                BibliotekarPozajmice frame = new BibliotekarPozajmice(new Fabrika(), new Bibliotekar());
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public BibliotekarPozajmice(Fabrika repo, Bibliotekar bibliotekar) {
        setResizable(false);
        setTitle("Bibliotekar pozajmice");
        //setIconImage(Toolkit.getDefaultToolkit().getImage(MedicalTechnicianScreen.class.getResource("/image/medlab.png")));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 920, 590);

        JMenuBar menuBar = new JMenuBar();
        menuBar.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        setJMenuBar(menuBar);

        JLabel IkonicaKnjige = new JLabel("");
        IkonicaKnjige.setIcon(new ImageIcon(BibliotekarPozajmice.class.getResource("/slike/knjiga.png")));
        menuBar.add(IkonicaKnjige);

        JMenu KnjigeMeni = new JMenu("Knjige");
        KnjigeMeni.setFont(new Font("Yu Gothic", Font.PLAIN, 16));
        menuBar.add(KnjigeMeni);

        JMenuItem PregledKnjiga = new JMenuItem("Pregled Knjiga");
        PregledKnjiga.setIcon(new ImageIcon(BibliotekarPozajmice.class.getResource("/slike/pregled.png")));
        PregledKnjiga.setFont(new Font("Yu Gothic", Font.BOLD, 12));
        PregledKnjiga.addActionListener(e -> {
            //prozorzapregled

        });

        JMenuItem PretragaKnjiga = new JMenuItem("Pretraga knjiga");
        PretragaKnjiga.setIcon(new ImageIcon(BibliotekarPozajmice.class.getResource("/slike/pregled.png")));
        PretragaKnjiga.setFont(new Font("Yu Gothic", Font.BOLD, 12));
        PretragaKnjiga.addActionListener(e -> {
           //prozorZaPretragu
        });
        KnjigeMeni.add(PretragaKnjiga);
        KnjigeMeni.add(PregledKnjiga);

        JLabel IkonicaClanovi = new JLabel("");
        IkonicaClanovi.setIcon(new ImageIcon(BibliotekarPozajmice.class.getResource("/slike/clanovi.png")));
        menuBar.add(IkonicaClanovi);

        JMenu Clanovi = new JMenu("Clanovi");
        Clanovi.setFont(new Font("Yu Gothic", Font.PLAIN, 16));
        menuBar.add(Clanovi);

        JMenuItem PregledClanova = new JMenuItem("Pregled clanova");
        PregledClanova.setIcon(new ImageIcon(BibliotekarPozajmice.class.getResource("/slike/pregled.png")));
        PregledClanova.setFont(new Font("Yu Gothic", Font.BOLD, 12));
        PregledClanova.addActionListener(e -> new PregledClanovaDialog(repo));

        JMenuItem PregledClanovaKojiKasne = new JMenuItem("Pregled clanova koji kasne sa vracanjem knjiga");
        PregledClanovaKojiKasne.setIcon(new ImageIcon(BibliotekarPozajmice.class.getResource("/slike/pregled.png")));
        PregledClanovaKojiKasne.setFont(new Font("Yu Gothic", Font.BOLD, 12));
        PregledClanovaKojiKasne.addActionListener(e -> {
            //prozorZaPrikazClanova
        });

        JMenuItem DeaktivacijaClana = new JMenuItem("Deaktivacija Clana");
        DeaktivacijaClana.setIcon(new ImageIcon(BibliotekarPozajmice.class.getResource("/slike/deaktivacija.png")));
        DeaktivacijaClana.setFont(new Font("Yu Gothic", Font.BOLD, 12));
        DeaktivacijaClana.addActionListener(e -> {
            //prozorZaDeaktivaciju
        });


        Clanovi.add(PregledClanova);
        Clanovi.add(PregledClanovaKojiKasne);
        Clanovi.add(DeaktivacijaClana);

        JLabel IkonicaRezervacije = new JLabel("");
        IkonicaRezervacije.setIcon(new ImageIcon(BibliotekarPozajmice.class.getResource("/slike/rezervacije.png")));
        menuBar.add(IkonicaRezervacije);

        JMenu Rezervacije = new JMenu("Rezervacije");
        Rezervacije.setFont(new Font("Yu Gothic", Font.PLAIN, 16));
        menuBar.add(Rezervacije);

        JMenuItem OdobravanjeRezervacija = new JMenuItem("Odobravanje rezervacija");
        OdobravanjeRezervacija.setIcon(new ImageIcon(BibliotekarPozajmice.class.getResource("/slike/odobravanje.png")));
        OdobravanjeRezervacija.addActionListener(e -> {
            //prozorZaOdobravanjeRezervacije
        });
        OdobravanjeRezervacija.setFont(new Font("Yu Gothic", Font.BOLD, 12));
        Rezervacije.add(OdobravanjeRezervacija);

        JLabel IkonicaIzvestaj = new JLabel("");
        IkonicaIzvestaj.setIcon(new ImageIcon(BibliotekarPozajmice.class.getResource("/slike/izvestaj.png")));
        menuBar.add(IkonicaIzvestaj);

        JMenu Izvestaji = new JMenu("Izvestaji");
        Izvestaji.setFont(new Font("Yu Gothic", Font.PLAIN, 16));
        menuBar.add(Izvestaji);

        JMenuItem izvestajCitanost = new JMenuItem("Citanost");
        izvestajCitanost.setFont(new Font("Yu Gothic", Font.BOLD, 12));
        izvestajCitanost.setIcon(new ImageIcon(BibliotekarPozajmice.class.getResource("/slike/citanje.png")));
        izvestajCitanost.addActionListener(e -> {
            //prozorIzvestajCitanost
        });

        JMenuItem izvestajNabavka = new JMenuItem("Nabavka");
        izvestajNabavka.setFont(new Font("Yu Gothic", Font.BOLD, 12));
        izvestajNabavka.setIcon(new ImageIcon(BibliotekarPozajmice.class.getResource("/slike/nabavka.png")));
        izvestajNabavka.addActionListener(e -> {
            //prozorizvestajNabavka
        });

        JMenuItem izvestajRadUSmenama = new JMenuItem("Rad u smenama");
        izvestajRadUSmenama.setFont(new Font("Yu Gothic", Font.BOLD, 12));
        izvestajRadUSmenama.setIcon(new ImageIcon(BibliotekarPozajmice.class.getResource("/slike/smene.png")));
        izvestajRadUSmenama.addActionListener(e -> {
            //prozorizvestajRadUSmenama
        });


        Izvestaji.add(izvestajCitanost);
        Izvestaji.add(izvestajNabavka);
        Izvestaji.add(izvestajRadUSmenama);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel Logout = new JLabel("");
        Logout.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int choice = JOptionPane.showConfirmDialog(BibliotekarPozajmice.this, "Jeste li sigurni? ", "Potvrda", JOptionPane.YES_NO_OPTION);
                if (choice == JOptionPane.YES_OPTION) {
                    BibliotekarPozajmice.this.dispose();
                    //MainScreen.main(repo);  login prozor
                }
                else {
                    return;
                }
            }
        });
        //Logout.setIcon(new ImageIcon(MedicalTechnicianScreen.class.getResource("/image/logout.png")));
        Logout.setBounds(606, 304, 40, 40);
        contentPane.add(Logout);

        JLabel Background = new JLabel("");
        Background.setIcon(new ImageIcon(BibliotekarPozajmice.class.getResource("/slike/bibliotekarPozadina.png")));
        Background.setBounds(0, 0, 907, 574);
        contentPane.add(Background);
    }
}
