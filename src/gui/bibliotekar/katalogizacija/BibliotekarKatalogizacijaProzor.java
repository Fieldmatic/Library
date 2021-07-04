package gui.bibliotekar.katalogizacija;

import gui.Prijavljivanje;
import gui.bibliotekar.pozajmice.clanovi.RegistracijaClana;
import gui.bibliotekar.pozajmice.clanovi.pregledClanova.PregledClanovaDialog;
import gui.pregledKnjiga.PregledKnjigaDialog;
import repository.Fabrika;
import userEntities.Bibliotekar;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BibliotekarKatalogizacijaProzor extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;


    public static void main(String[] args, Fabrika fabrika, Bibliotekar bibliotekar) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    BibliotekarKatalogizacijaProzor frame = new BibliotekarKatalogizacijaProzor(fabrika, bibliotekar);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public BibliotekarKatalogizacijaProzor(Fabrika fabrika, Bibliotekar bibliotekar) {
        setResizable(false);
        setTitle("Bibliotekar - katalogizacija");
        setIconImage(Toolkit.getDefaultToolkit().getImage(BibliotekarKatalogizacijaProzor.class.getResource("/slike/logo.jpg")));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 920, 590);

        JMenuBar menuBar = new JMenuBar();
        menuBar.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        setJMenuBar(menuBar);

        JLabel IkonicaKnjige = new JLabel("");
        IkonicaKnjige.setIcon(new ImageIcon(BibliotekarKatalogizacijaProzor.class.getResource("/slike/knjiga.png")));
        menuBar.add(IkonicaKnjige);

        JMenu KnjigeMeni = new JMenu("Knjige");
        KnjigeMeni.setFont(new Font("Yu Gothic", Font.PLAIN, 16));
        menuBar.add(KnjigeMeni);

        //azuriranje knjige
        JMenu knjigeMeniZaAzuriranje = new JMenu("Azuriranje");
        knjigeMeniZaAzuriranje.setFont(new Font("Yu Gothic", Font.BOLD, 12));
        knjigeMeniZaAzuriranje.setIcon(new ImageIcon(BibliotekarKatalogizacijaProzor.class.getResource("/slike/azuriranje.png")));
        JMenuItem dodavanjeKnjige = new JMenuItem("Dodavanje knjige");
        dodavanjeKnjige.setIcon(new ImageIcon(BibliotekarKatalogizacijaProzor.class.getResource("/slike/dodajKnjigu.png")));
        dodavanjeKnjige.setFont(new Font("Yu Gothic", Font.BOLD, 12));
        dodavanjeKnjige.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DodavanjeKnjigeProzor.main(fabrika);}
        });

        JMenuItem brisanjeKnjige = new JMenuItem("Brisanje knjige");
        brisanjeKnjige.setIcon(new ImageIcon(BibliotekarKatalogizacijaProzor.class.getResource("/slike/brisanjeKnjige.png")));
        brisanjeKnjige.setFont(new Font("Yu Gothic", Font.BOLD, 12));
        brisanjeKnjige.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //prozor za brisanje knjige

            }
        });
        JMenuItem modifikacijaKnjige = new JMenuItem("Izmena knjige");
        modifikacijaKnjige.setIcon(new ImageIcon(BibliotekarKatalogizacijaProzor.class.getResource("/slike/azuriranje.png")));
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
        PregledKnjiga.setIcon(new ImageIcon(BibliotekarKatalogizacijaProzor.class.getResource("/slike/pregled.png")));
        PregledKnjiga.setFont(new Font("Yu Gothic", Font.BOLD, 12));
        PregledKnjiga.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //prozorzapregled

            }
        });

        JMenuItem PretragaKnjiga = new JMenuItem("Pretraga knjiga");
        PretragaKnjiga.setIcon(new ImageIcon(BibliotekarKatalogizacijaProzor.class.getResource("/slike/pregled.png")));
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
        IkonicaClanovi.setIcon(new ImageIcon(BibliotekarKatalogizacijaProzor.class.getResource("/slike/clanovi.png")));
        menuBar.add(IkonicaClanovi);

        JMenu Clanovi = new JMenu("Clanovi");
        Clanovi.setFont(new Font("Yu Gothic", Font.PLAIN, 16));
        menuBar.add(Clanovi);

        JMenuItem PregledClanova = new JMenuItem("Pregled clanova");
        PregledClanova.setIcon(new ImageIcon(BibliotekarKatalogizacijaProzor.class.getResource("/slike/pregled.png")));
        PregledClanova.setFont(new Font("Yu Gothic", Font.BOLD, 12));
        PregledClanova.addActionListener(e -> new PregledClanovaDialog(fabrika, fabrika.getMenadzerClanova().getClanovi()));

        JMenuItem PregledClanovaKojiKasne = new JMenuItem("Pregled clanova koji kasne sa vracanjem knjiga");
        PregledClanovaKojiKasne.setIcon(new ImageIcon(BibliotekarKatalogizacijaProzor.class.getResource("/slike/pregled.png")));
        PregledClanovaKojiKasne.setFont(new Font("Yu Gothic", Font.BOLD, 12));
        //PregledClanovaKojiKasne.addActionListener(e -> pregledKasnjenja(fabrika));

        Clanovi.add(PregledClanova);
        Clanovi.add(PregledClanovaKojiKasne);

        JLabel ikonicaRecenzije = new JLabel("");
        ikonicaRecenzije.setIcon(new ImageIcon(BibliotekarKatalogizacijaProzor.class.getResource("/slike/recenzije.png")));
        menuBar.add(ikonicaRecenzije);

        JMenu recenzije = new JMenu("Recenzije");
        recenzije.setFont(new Font("Yu Gothic", Font.PLAIN, 16));
        menuBar.add(recenzije);

        JMenuItem odobravanjeRecenzije = new JMenuItem("Odobravanje recenzija");
        odobravanjeRecenzije.setIcon(new ImageIcon(BibliotekarKatalogizacijaProzor.class.getResource("/slike/odobravanje.png")));
        odobravanjeRecenzije.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //prozor za odobravanje recenzije
            }
        });
        odobravanjeRecenzije.setFont(new Font("Yu Gothic", Font.BOLD, 12));
        recenzije.add(odobravanjeRecenzije);

        JLabel IkonicaIzvestaj = new JLabel("");
        IkonicaIzvestaj.setIcon(new ImageIcon(BibliotekarKatalogizacijaProzor.class.getResource("/slike/izvestaj.png")));
        menuBar.add(IkonicaIzvestaj);

        JMenu Izvestaji = new JMenu("Izvestaji");
        Izvestaji.setFont(new Font("Yu Gothic", Font.PLAIN, 16));
        menuBar.add(Izvestaji);

        JMenuItem izvestajCitanost = new JMenuItem("Citanost");
        izvestajCitanost.setFont(new Font("Yu Gothic", Font.BOLD, 12));
        izvestajCitanost.setIcon(new ImageIcon(BibliotekarKatalogizacijaProzor.class.getResource("/slike/citanje.png")));
        izvestajCitanost.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //prozorIzvestajCitanost
            }
        });

        JMenuItem izvestajNabavka = new JMenuItem("Nabavka");
        izvestajNabavka.setFont(new Font("Yu Gothic", Font.BOLD, 12));
        izvestajNabavka.setIcon(new ImageIcon(BibliotekarKatalogizacijaProzor.class.getResource("/slike/nabavka.png")));
        izvestajNabavka.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //prozorizvestajNabavka
            }
        });

        JMenuItem izvestajRadUSmenama = new JMenuItem("Rad u smenama");
        izvestajRadUSmenama.setFont(new Font("Yu Gothic", Font.BOLD, 12));
        izvestajRadUSmenama.setIcon(new ImageIcon(BibliotekarKatalogizacijaProzor.class.getResource("/slike/smene.png")));
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

        JLabel logoutLabel = new JLabel("");
        logoutLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int result =JOptionPane.showConfirmDialog(contentPane ,"Jeste li sigurni da zelite da se odjavite?", "Odjava", JOptionPane.YES_NO_OPTION);
                if(result == JOptionPane.YES_OPTION){
                    BibliotekarKatalogizacijaProzor.this.dispose();
                    Prijavljivanje prijava = new Prijavljivanje(fabrika);
                    prijava.setVisible(true);
                }
            }
        });
        logoutLabel.setIcon(new ImageIcon(BibliotekarKatalogizacijaProzor.class.getResource("/slike/odjaviSe.png")));
        logoutLabel.setBounds(800, 425, 139, 98);
        contentPane.add(logoutLabel);

        JLabel Background = new JLabel("");
        Background.setIcon(new ImageIcon(BibliotekarKatalogizacijaProzor.class.getResource("/slike/bibliotekarPozadina.png")));
        Background.setBounds(0, 0, 907, 574);
        contentPane.add(Background);
    }
}
