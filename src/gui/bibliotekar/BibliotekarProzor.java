package gui.bibliotekar;

import entities.Pozajmica;
import enumerations.StatusPozajmice;
import gui.Prijavljivanje;
import gui.bibliotekar.pozajmice.clanovi.pregledClanova.PregledClanovaDialog;
import gui.bibliotekar.pozajmice.clanovi.pregledKasnjenja.PregledKasnjenjaDialog;
import repository.Fabrika;
import userEntities.Bibliotekar;
import userEntities.Clan;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class BibliotekarProzor extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;


    public static void main(String[] args, Fabrika fabrika, Bibliotekar bibliotekar) {
        EventQueue.invokeLater(() -> {
            try {
                BibliotekarProzor frame = new BibliotekarProzor(fabrika, bibliotekar);
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public BibliotekarProzor(Fabrika fabrika, Bibliotekar bibliotekar) {
        setResizable(false);
        setTitle("Bibliotekar");
        setIconImage(Toolkit.getDefaultToolkit().getImage(BibliotekarProzor.class.getResource("/slike/logo.jpg")));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 920, 590);

        JMenuBar menuBar = new JMenuBar();
        menuBar.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        setJMenuBar(menuBar);

        JLabel IkonicaKnjige = new JLabel("");
        IkonicaKnjige.setIcon(new ImageIcon(BibliotekarProzor.class.getResource("/slike/knjiga.png")));
        menuBar.add(IkonicaKnjige);

        JMenu KnjigeMeni = new JMenu("Knjige");
        KnjigeMeni.setFont(new Font("Yu Gothic", Font.PLAIN, 16));
        menuBar.add(KnjigeMeni);

        JMenu knjigeMeniZaAzuriranje = new JMenu("Azuriranje");
        knjigeMeniZaAzuriranje.setFont(new Font("Yu Gothic", Font.BOLD, 12));
        knjigeMeniZaAzuriranje.setIcon(new ImageIcon(BibliotekarKatalogizacijaProzor.class.getResource("/slike/azuriranje.png")));
        JMenuItem dodavanjeKnjige = new JMenuItem("Dodavanje knjige");
        dodavanjeKnjige.setIcon(new ImageIcon(BibliotekarKatalogizacijaProzor.class.getResource("/slike/dodajKnjigu.png")));
        dodavanjeKnjige.setFont(new Font("Yu Gothic", Font.BOLD, 12));
        dodavanjeKnjige.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //prozor za dodavanje knjige

            }
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
        PregledKnjiga.setIcon(new ImageIcon(BibliotekarProzor.class.getResource("/slike/pregled.png")));
        PregledKnjiga.setFont(new Font("Yu Gothic", Font.BOLD, 12));
        PregledKnjiga.addActionListener(e -> {
            //prozorzapregled

        });

        JMenuItem PretragaKnjiga = new JMenuItem("Pretraga knjiga");
        PretragaKnjiga.setIcon(new ImageIcon(BibliotekarProzor.class.getResource("/slike/pregled.png")));
        PretragaKnjiga.setFont(new Font("Yu Gothic", Font.BOLD, 12));
        PretragaKnjiga.addActionListener(e -> {
            //prozorZaPretragu
        });
        KnjigeMeni.add(PretragaKnjiga);
        KnjigeMeni.add(PregledKnjiga);
        KnjigeMeni.add(knjigeMeniZaAzuriranje);

        JLabel IkonicaClanovi = new JLabel("");
        IkonicaClanovi.setIcon(new ImageIcon(BibliotekarProzor.class.getResource("/slike/clanovi.png")));
        menuBar.add(IkonicaClanovi);

        JMenu Clanovi = new JMenu("Clanovi");
        Clanovi.setFont(new Font("Yu Gothic", Font.PLAIN, 16));
        menuBar.add(Clanovi);

        JMenuItem PregledClanova = new JMenuItem("Pregled clanova");
        PregledClanova.setIcon(new ImageIcon(BibliotekarProzor.class.getResource("/slike/pregled.png")));
        PregledClanova.setFont(new Font("Yu Gothic", Font.BOLD, 12));
        PregledClanova.addActionListener(e -> new PregledClanovaDialog(fabrika, fabrika.getMenadzerClanova().getClanovi()));

        JMenuItem PregledClanovaKojiKasne = new JMenuItem("Pregled clanova koji kasne sa vracanjem knjiga");
        PregledClanovaKojiKasne.setIcon(new ImageIcon(BibliotekarProzor.class.getResource("/slike/pregled.png")));
        PregledClanovaKojiKasne.setFont(new Font("Yu Gothic", Font.BOLD, 12));
        //PregledClanovaKojiKasne.addActionListener(e -> pregledKasnjenja(fabrika));

        JMenuItem DeaktivacijaClana = new JMenuItem("Deaktivacija Clana");
        DeaktivacijaClana.setIcon(new ImageIcon(BibliotekarProzor.class.getResource("/slike/deaktivacija.png")));
        DeaktivacijaClana.setFont(new Font("Yu Gothic", Font.BOLD, 12));
        DeaktivacijaClana.addActionListener(e -> {
            //prozorZaDeaktivaciju
        });

        Clanovi.add(PregledClanova);
        Clanovi.add(PregledClanovaKojiKasne);
        Clanovi.add(DeaktivacijaClana);

        JLabel IkonicaRezervacije = new JLabel("");
        IkonicaRezervacije.setIcon(new ImageIcon(BibliotekarProzor.class.getResource("/slike/rezervacije.png")));
        menuBar.add(IkonicaRezervacije);

        JMenu Rezervacije = new JMenu("Rezervacije");
        Rezervacije.setFont(new Font("Yu Gothic", Font.PLAIN, 16));
        menuBar.add(Rezervacije);

        JMenuItem OdobravanjeRezervacija = new JMenuItem("Odobravanje rezervacija");
        OdobravanjeRezervacija.setIcon(new ImageIcon(BibliotekarProzor.class.getResource("/slike/odobravanje.png")));
        OdobravanjeRezervacija.addActionListener(e -> {
            //prozorZaOdobravanjeRezervacije
        });
        OdobravanjeRezervacija.setFont(new Font("Yu Gothic", Font.BOLD, 12));
        Rezervacije.add(OdobravanjeRezervacija);

        JLabel IkonicaIzvestaj = new JLabel("");
        IkonicaIzvestaj.setIcon(new ImageIcon(BibliotekarProzor.class.getResource("/slike/izvestaj.png")));
        menuBar.add(IkonicaIzvestaj);

        JMenu Izvestaji = new JMenu("Izvestaji");
        Izvestaji.setFont(new Font("Yu Gothic", Font.PLAIN, 16));
        menuBar.add(Izvestaji);

        JMenuItem izvestajCitanost = new JMenuItem("Citanost");
        izvestajCitanost.setFont(new Font("Yu Gothic", Font.BOLD, 12));
        izvestajCitanost.setIcon(new ImageIcon(BibliotekarProzor.class.getResource("/slike/citanje.png")));
        izvestajCitanost.addActionListener(e -> {
            //prozorIzvestajCitanost
        });

        JMenuItem izvestajNabavka = new JMenuItem("Nabavka");
        izvestajNabavka.setFont(new Font("Yu Gothic", Font.BOLD, 12));
        izvestajNabavka.setIcon(new ImageIcon(BibliotekarProzor.class.getResource("/slike/nabavka.png")));
        izvestajNabavka.addActionListener(e -> {
            //prozorizvestajNabavka
        });

        JMenuItem izvestajRadUSmenama = new JMenuItem("Rad u smenama");
        izvestajRadUSmenama.setFont(new Font("Yu Gothic", Font.BOLD, 12));
        izvestajRadUSmenama.setIcon(new ImageIcon(BibliotekarProzor.class.getResource("/slike/smene.png")));
        izvestajRadUSmenama.addActionListener(e -> {
            //prozorizvestajRadUSmenama
        });
        Izvestaji.add(izvestajCitanost);
        Izvestaji.add(izvestajNabavka);
        Izvestaji.add(izvestajRadUSmenama);

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
                    BibliotekarProzor.this.dispose();
                    Prijavljivanje prijava = new Prijavljivanje(fabrika);
                    prijava.setVisible(true);
                }
            }
        });
        logoutLabel.setIcon(new ImageIcon(BibliotekarProzor.class.getResource("/slike/odjaviSe.png")));
        logoutLabel.setBounds(800, 425, 139, 98);
        contentPane.add(logoutLabel);

        JLabel Background = new JLabel("");
        Background.setIcon(new ImageIcon(BibliotekarProzor.class.getResource("/slike/bibliotekarPozadina.png")));
        Background.setBounds(0, 0, 907, 574);
        contentPane.add(Background);
    }
}
