package glavniProzori;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BibliotekarKatalogizacija extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;


    public static void main(String[] args/*, RepoContainer repo, Bibliotekar bibliotekar*/) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    BibliotekarKatalogizacija frame = new BibliotekarKatalogizacija(/*repo, bibliotekar*/);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public BibliotekarKatalogizacija(/*RepoContainer repo, Bibliotekar bibliotekar*/) {
        setResizable(false);
        setTitle("Bibliotekar za katalogizaciju");
        setIconImage(Toolkit.getDefaultToolkit().getImage(Prijavljivanje.class.getResource("/slike/logo.jpg")));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 920, 590);

        JMenuBar menuBar = new JMenuBar();
        menuBar.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        setJMenuBar(menuBar);

        JLabel IkonicaKnjige = new JLabel("");
        IkonicaKnjige.setIcon(new ImageIcon(BibliotekarKatalogizacija.class.getResource("/slike/knjiga.png")));
        menuBar.add(IkonicaKnjige);

        JMenu KnjigeMeni = new JMenu("Knjige");
        KnjigeMeni.setFont(new Font("Yu Gothic", Font.PLAIN, 16));
        menuBar.add(KnjigeMeni);

        //azuriranje knjige
        JMenu knjigeMeniZaAzuriranje = new JMenu("Azuriranje");
        knjigeMeniZaAzuriranje.setFont(new Font("Yu Gothic", Font.BOLD, 12));
        knjigeMeniZaAzuriranje.setIcon(new ImageIcon(BibliotekarKatalogizacija.class.getResource("/slike/azuriranje.png")));
        JMenuItem dodavanjeKnjige = new JMenuItem("Dodavanje knjige");
        dodavanjeKnjige.setIcon(new ImageIcon(BibliotekarKatalogizacija.class.getResource("/slike/dodajKnjigu.png")));
        dodavanjeKnjige.setFont(new Font("Yu Gothic", Font.BOLD, 12));
        dodavanjeKnjige.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //prozor za dodavanje knjige

            }
        });
        JMenuItem brisanjeKnjige = new JMenuItem("Brisanje knjige");
        brisanjeKnjige.setIcon(new ImageIcon(BibliotekarKatalogizacija.class.getResource("/slike/brisanjeKnjige.png")));
        brisanjeKnjige.setFont(new Font("Yu Gothic", Font.BOLD, 12));
        brisanjeKnjige.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //prozor za brisanje knjige

            }
        });
        JMenuItem modifikacijaKnjige = new JMenuItem("Izmena knjige");
        modifikacijaKnjige.setIcon(new ImageIcon(BibliotekarKatalogizacija.class.getResource("/slike/azuriranje.png")));
        modifikacijaKnjige.setFont(new Font("Yu Gothic", Font.BOLD, 12));
        modifikacijaKnjige.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //prozor za modifikaciju knjige

            }
        });
        knjigeMeniZaAzuriranje.add(dodavanjeKnjige);
        knjigeMeniZaAzuriranje.add(brisanjeKnjige);
        knjigeMeniZaAzuriranje.add(modifikacijaKnjige);

        JMenuItem PregledKnjiga = new JMenuItem("Pregled Knjiga");
        PregledKnjiga.setIcon(new ImageIcon(BibliotekarKatalogizacija.class.getResource("/slike/pregled.png")));
        PregledKnjiga.setFont(new Font("Yu Gothic", Font.BOLD, 12));
        PregledKnjiga.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //prozorzapregled

            }
        });

        JMenuItem PretragaKnjiga = new JMenuItem("Pretraga knjiga");
        PretragaKnjiga.setIcon(new ImageIcon(BibliotekarKatalogizacija.class.getResource("/slike/pregled.png")));
        PretragaKnjiga.setFont(new Font("Yu Gothic", Font.BOLD, 12));
        PretragaKnjiga.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //prozorZaPretragu
            }
        });
        KnjigeMeni.add(PretragaKnjiga);
        KnjigeMeni.add(PregledKnjiga);
        KnjigeMeni.add(knjigeMeniZaAzuriranje);

        JLabel IkonicaClanovi = new JLabel("");
        IkonicaClanovi.setIcon(new ImageIcon(BibliotekarKatalogizacija.class.getResource("/slike/clanovi.png")));
        menuBar.add(IkonicaClanovi);

        JMenu Clanovi = new JMenu("Clanovi");
        Clanovi.setFont(new Font("Yu Gothic", Font.PLAIN, 16));
        menuBar.add(Clanovi);

        JMenuItem PregledClanova = new JMenuItem("Pregled clanova");
        PregledClanova.setIcon(new ImageIcon(BibliotekarKatalogizacija.class.getResource("/slike/pregled.png")));
        PregledClanova.setFont(new Font("Yu Gothic", Font.BOLD, 12));
        PregledClanova.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //prozorZaPrikazClanova
            }
        });

        JMenuItem PregledClanovaKojiKasne = new JMenuItem("Pregled clanova koji kasne sa vracanjem knjiga");
        PregledClanovaKojiKasne.setIcon(new ImageIcon(BibliotekarKatalogizacija.class.getResource("/slike/pregled.png")));
        PregledClanovaKojiKasne.setFont(new Font("Yu Gothic", Font.BOLD, 12));
        PregledClanovaKojiKasne.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //prozorZaPrikazClanova
            }
        });

        Clanovi.add(PregledClanova);
        Clanovi.add(PregledClanovaKojiKasne);

        JLabel IkonicaRezervacije = new JLabel("");
        IkonicaRezervacije.setIcon(new ImageIcon(BibliotekarKatalogizacija.class.getResource("/slike/rezervacije.png")));
        menuBar.add(IkonicaRezervacije);

        JMenu recenzije = new JMenu("Recenzije");
        recenzije.setFont(new Font("Yu Gothic", Font.PLAIN, 16));
        menuBar.add(recenzije);

        JMenuItem odobravanjeRecenzije = new JMenuItem("Odobravanje recenzija");
        odobravanjeRecenzije.setIcon(new ImageIcon(BibliotekarKatalogizacija.class.getResource("/slike/odobravanje.png")));
        odobravanjeRecenzije.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //prozor za odobravanje recenzije
            }
        });
        odobravanjeRecenzije.setFont(new Font("Yu Gothic", Font.BOLD, 12));
        recenzije.add(odobravanjeRecenzije);

        JLabel IkonicaIzvestaj = new JLabel("");
        IkonicaIzvestaj.setIcon(new ImageIcon(BibliotekarKatalogizacija.class.getResource("/slike/izvestaj.png")));
        menuBar.add(IkonicaIzvestaj);

        JMenu Izvestaji = new JMenu("Izvestaji");
        Izvestaji.setFont(new Font("Yu Gothic", Font.PLAIN, 16));
        menuBar.add(Izvestaji);

        JMenuItem izvestajCitanost = new JMenuItem("Citanost");
        izvestajCitanost.setFont(new Font("Yu Gothic", Font.BOLD, 12));
        izvestajCitanost.setIcon(new ImageIcon(BibliotekarKatalogizacija.class.getResource("/slike/citanje.png")));
        izvestajCitanost.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //prozorIzvestajCitanost
            }
        });

        JMenuItem izvestajNabavka = new JMenuItem("Nabavka");
        izvestajNabavka.setFont(new Font("Yu Gothic", Font.BOLD, 12));
        izvestajNabavka.setIcon(new ImageIcon(BibliotekarKatalogizacija.class.getResource("/slike/nabavka.png")));
        izvestajNabavka.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //prozorizvestajNabavka
            }
        });

        JMenuItem izvestajRadUSmenama = new JMenuItem("Rad u smenama");
        izvestajRadUSmenama.setFont(new Font("Yu Gothic", Font.BOLD, 12));
        izvestajRadUSmenama.setIcon(new ImageIcon(BibliotekarKatalogizacija.class.getResource("/slike/smene.png")));
        izvestajRadUSmenama.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //prozorizvestajRadUSmenama
            }
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
                int choice = JOptionPane.showConfirmDialog(BibliotekarKatalogizacija.this, "Jeste li sigurni? ", "Potvrda", JOptionPane.YES_NO_OPTION);
                if (choice == JOptionPane.YES_OPTION) {
                    BibliotekarKatalogizacija.this.dispose();
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
        Background.setIcon(new ImageIcon(BibliotekarKatalogizacija.class.getResource("/slike/bibliotekarPozadina.png")));
        Background.setBounds(0, 0, 907, 574);
        contentPane.add(Background);
    }
}
