package gui.bibliotekar.pozajmice;

import entities.Pozajmica;
import enumerations.StatusPozajmice;
import gui.Prijavljivanje;
import gui.bibliotekar.pozajmice.clanovi.RegistracijaClana;
import gui.bibliotekar.pozajmice.clanovi.pregledClanova.PregledClanovaDialog;
import gui.bibliotekar.pozajmice.clanovi.pregledKasnjenja.PregledKasnjenjaDialog;
import gui.pregledKnjiga.PregledKnjigaDialog;
import gui.pretragaKnjiga.PretragaKnjigaProzor;
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

public class BibliotekarPozajmiceProzor extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;


    public static void main(String[] args, Fabrika fabrika, Bibliotekar bibliotekar) {
        EventQueue.invokeLater(() -> {
            try {
                BibliotekarPozajmiceProzor frame = new BibliotekarPozajmiceProzor(fabrika, bibliotekar);
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public BibliotekarPozajmiceProzor(Fabrika fabrika, Bibliotekar bibliotekar) {
        setResizable(false);
        setTitle("Bibliotekar - pozajmice");
        setIconImage(Toolkit.getDefaultToolkit().getImage(BibliotekarPozajmiceProzor.class.getResource("/slike/logo.jpg")));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 920, 590);

        JMenuBar menuBar = new JMenuBar();
        menuBar.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        setJMenuBar(menuBar);

        JLabel IkonicaKnjige = new JLabel("");
        IkonicaKnjige.setIcon(new ImageIcon(BibliotekarPozajmiceProzor.class.getResource("/slike/knjiga.png")));
        menuBar.add(IkonicaKnjige);

        JMenu KnjigeMeni = new JMenu("Knjige");
        KnjigeMeni.setFont(new Font("Yu Gothic", Font.PLAIN, 16));
        menuBar.add(KnjigeMeni);

        JMenuItem PregledKnjiga = new JMenuItem("Pregled Knjiga");
        PregledKnjiga.setIcon(new ImageIcon(BibliotekarPozajmiceProzor.class.getResource("/slike/pregled.png")));
        PregledKnjiga.setFont(new Font("Yu Gothic", Font.BOLD, 12));
        PregledKnjiga.addActionListener(e -> new PregledKnjigaDialog(fabrika, fabrika.getMenadzerKnjiga().getKnjige()));

        JMenuItem PretragaKnjiga = new JMenuItem("Pretraga knjiga");
        PretragaKnjiga.setIcon(new ImageIcon(BibliotekarPozajmiceProzor.class.getResource("/slike/pregled.png")));
        PretragaKnjiga.setFont(new Font("Yu Gothic", Font.BOLD, 12));
        PretragaKnjiga.addActionListener(e -> {
           new PretragaKnjigaProzor(fabrika);
        });
        KnjigeMeni.add(PretragaKnjiga);
        KnjigeMeni.add(PregledKnjiga);

        JLabel IkonicaClanovi = new JLabel("");
        IkonicaClanovi.setIcon(new ImageIcon(BibliotekarPozajmiceProzor.class.getResource("/slike/clanovi.png")));
        menuBar.add(IkonicaClanovi);

        JMenu Clanovi = new JMenu("Clanovi");
        Clanovi.setFont(new Font("Yu Gothic", Font.PLAIN, 16));
        menuBar.add(Clanovi);

        JMenuItem PregledClanova = new JMenuItem("Pregled clanova");
        PregledClanova.setIcon(new ImageIcon(BibliotekarPozajmiceProzor.class.getResource("/slike/pregled.png")));
        PregledClanova.setFont(new Font("Yu Gothic", Font.BOLD, 12));
        PregledClanova.addActionListener(e -> new PregledClanovaDialog(fabrika, fabrika.getMenadzerClanova().getClanovi()));

        JMenuItem PregledClanovaKojiKasne = new JMenuItem("Pregled clanova koji kasne sa vracanjem knjiga");
        PregledClanovaKojiKasne.setIcon(new ImageIcon(BibliotekarPozajmiceProzor.class.getResource("/slike/pregled.png")));
        PregledClanovaKojiKasne.setFont(new Font("Yu Gothic", Font.BOLD, 12));
        PregledClanovaKojiKasne.addActionListener(e -> pregledKasnjenja(fabrika));

        JMenuItem DeaktivacijaClana = new JMenuItem("Deaktivacija Clana");
        DeaktivacijaClana.setIcon(new ImageIcon(BibliotekarPozajmiceProzor.class.getResource("/slike/deaktivacija.png")));
        DeaktivacijaClana.setFont(new Font("Yu Gothic", Font.BOLD, 12));
        DeaktivacijaClana.addActionListener(e -> {
            //prozorZaDeaktivaciju
        });

        JMenuItem DodavanjeClana = new JMenuItem("Dodaj Clana");
        DodavanjeClana.setIcon(new ImageIcon(BibliotekarPozajmiceProzor.class.getResource("/slike/dodavanje.png")));
        DodavanjeClana.setFont(new Font("Yu Gothic", Font.BOLD, 12));
        DodavanjeClana.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {RegistracijaClana.main(fabrika);}
         });
        Clanovi.add(PregledClanova);
        Clanovi.add(PregledClanovaKojiKasne);
        Clanovi.add(DeaktivacijaClana);
        Clanovi.add(DodavanjeClana);

        JLabel IkonicaRezervacije = new JLabel("");
        IkonicaRezervacije.setIcon(new ImageIcon(BibliotekarPozajmiceProzor.class.getResource("/slike/rezervacije.png")));
        menuBar.add(IkonicaRezervacije);

        JMenu Rezervacije = new JMenu("Rezervacije");
        Rezervacije.setFont(new Font("Yu Gothic", Font.PLAIN, 16));
        menuBar.add(Rezervacije);

        JMenuItem OdobravanjeRezervacija = new JMenuItem("Odobravanje rezervacija");
        OdobravanjeRezervacija.setIcon(new ImageIcon(BibliotekarPozajmiceProzor.class.getResource("/slike/odobravanje.png")));
        OdobravanjeRezervacija.addActionListener(e -> {
            //prozorZaOdobravanjeRezervacije
        });
        OdobravanjeRezervacija.setFont(new Font("Yu Gothic", Font.BOLD, 12));
        Rezervacije.add(OdobravanjeRezervacija);

        JLabel IkonicaIzvestaj = new JLabel("");
        IkonicaIzvestaj.setIcon(new ImageIcon(BibliotekarPozajmiceProzor.class.getResource("/slike/izvestaj.png")));
        menuBar.add(IkonicaIzvestaj);

        JMenu Izvestaji = new JMenu("Izvestaji");
        Izvestaji.setFont(new Font("Yu Gothic", Font.PLAIN, 16));
        menuBar.add(Izvestaji);

        JMenuItem izvestajCitanost = new JMenuItem("Citanost");
        izvestajCitanost.setFont(new Font("Yu Gothic", Font.BOLD, 12));
        izvestajCitanost.setIcon(new ImageIcon(BibliotekarPozajmiceProzor.class.getResource("/slike/citanje.png")));
        izvestajCitanost.addActionListener(e -> {
            //prozorIzvestajCitanost
        });

        JMenuItem izvestajNabavka = new JMenuItem("Nabavka");
        izvestajNabavka.setFont(new Font("Yu Gothic", Font.BOLD, 12));
        izvestajNabavka.setIcon(new ImageIcon(BibliotekarPozajmiceProzor.class.getResource("/slike/nabavka.png")));
        izvestajNabavka.addActionListener(e -> {
            //prozorizvestajNabavka
        });

        JMenuItem izvestajRadUSmenama = new JMenuItem("Rad u smenama");
        izvestajRadUSmenama.setFont(new Font("Yu Gothic", Font.BOLD, 12));
        izvestajRadUSmenama.setIcon(new ImageIcon(BibliotekarPozajmiceProzor.class.getResource("/slike/smene.png")));
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

        JLabel logoutLabel = new JLabel("");
        logoutLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int result =JOptionPane.showConfirmDialog(contentPane ,"Jeste li sigurni da zelite da se odjavite?", "Odjava", JOptionPane.YES_NO_OPTION);
                if(result == JOptionPane.YES_OPTION){
                    BibliotekarPozajmiceProzor.this.dispose();
                    Prijavljivanje prijava = new Prijavljivanje(fabrika);
                    prijava.setVisible(true);
                }
            }
        });
        logoutLabel.setIcon(new ImageIcon(BibliotekarPozajmiceProzor.class.getResource("/slike/odjaviSe.png")));
        logoutLabel.setBounds(800, 425, 139, 98);
        contentPane.add(logoutLabel);

        JLabel Background = new JLabel("");
        Background.setIcon(new ImageIcon(BibliotekarPozajmiceProzor.class.getResource("/slike/bibliotekarPozadina.png")));
        Background.setBounds(0, 0, 907, 574);
        contentPane.add(Background);
    }

    private Object pregledKasnjenja(Fabrika repo) {
            List<Clan> clanovi = new ArrayList<>();
            for (Clan c : repo.getMenadzerClanova().getClanovi()) {
                for (Pozajmica p : c.getPozajmice()) {
                    if (p.getStatus().equals(StatusPozajmice.istekla)) {
                        clanovi.add(c);
                        break;
                    }
                }
            }
            if (!clanovi.isEmpty())
                return new PregledKasnjenjaDialog(repo, clanovi);
            else
                JOptionPane.showMessageDialog(null, "Nema clanova sa kasnjenjem.", "Greška", JOptionPane.WARNING_MESSAGE);
            return null;
    }
}
