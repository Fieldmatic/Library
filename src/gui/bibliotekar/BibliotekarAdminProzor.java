package gui.bibliotekar;

import entities.Pozajmica;
import enumerations.StatusPozajmice;
import enumerations.TipClanarine;
import enumerations.TrajanjeClanarine;
import gui.Prijavljivanje;
import gui.bibliotekar.katalogizacija.BibliotekarKatalogizacijaProzor;
import gui.bibliotekar.katalogizacija.DodavanjeKnjigeProzor;
import gui.bibliotekar.pozajmice.BibliotekarPozajmiceProzor;
import gui.bibliotekar.pozajmice.clanovi.RegistracijaClana;
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

public class BibliotekarAdminProzor extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;


    public static void main(String[] args, Fabrika fabrika, Bibliotekar bibliotekar) {
        EventQueue.invokeLater(() -> {
            try {
                BibliotekarAdminProzor frame = new BibliotekarAdminProzor(fabrika, bibliotekar);
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public BibliotekarAdminProzor(Fabrika fabrika, Bibliotekar bibliotekar) {
        setResizable(false);
        setTitle("Bibliotekar-Admin");
        setIconImage(Toolkit.getDefaultToolkit().getImage(BibliotekarAdminProzor.class.getResource("/slike/logo.jpg")));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 920, 590);

        JMenuBar menuBar = new JMenuBar();
        menuBar.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        setJMenuBar(menuBar);

        JLabel IkonicaKnjige = new JLabel("");
        IkonicaKnjige.setIcon(new ImageIcon(BibliotekarAdminProzor.class.getResource("/slike/knjiga.png")));
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
                @Override
                public void actionPerformed (ActionEvent e){
                    DodavanjeKnjigeProzor.main(fabrika);
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
        PregledKnjiga.setIcon(new ImageIcon(BibliotekarAdminProzor.class.getResource("/slike/pregled.png")));
        PregledKnjiga.setFont(new Font("Yu Gothic", Font.BOLD, 12));
        PregledKnjiga.addActionListener(e -> {
            //prozorzapregled

        });

        JMenuItem PretragaKnjiga = new JMenuItem("Pretraga knjiga");
        PretragaKnjiga.setIcon(new ImageIcon(BibliotekarAdminProzor.class.getResource("/slike/pregled.png")));
        PretragaKnjiga.setFont(new Font("Yu Gothic", Font.BOLD, 12));
        PretragaKnjiga.addActionListener(e -> {
            //prozorZaPretragu
        });
        KnjigeMeni.add(PretragaKnjiga);
        KnjigeMeni.add(PregledKnjiga);
        KnjigeMeni.add(knjigeMeniZaAzuriranje);

        JLabel IkonicaClanovi = new JLabel("");
        IkonicaClanovi.setIcon(new ImageIcon(BibliotekarAdminProzor.class.getResource("/slike/clanovi.png")));
        menuBar.add(IkonicaClanovi);

        JMenu Clanovi = new JMenu("Clanovi");
        Clanovi.setFont(new Font("Yu Gothic", Font.PLAIN, 16));
        menuBar.add(Clanovi);

        JMenuItem PregledClanova = new JMenuItem("Pregled clanova");
        PregledClanova.setIcon(new ImageIcon(BibliotekarAdminProzor.class.getResource("/slike/pregled.png")));
        PregledClanova.setFont(new Font("Yu Gothic", Font.BOLD, 12));
        PregledClanova.addActionListener(e -> new PregledClanovaDialog(fabrika, fabrika.getMenadzerClanova().getClanovi()));

        JMenuItem PregledClanovaKojiKasne = new JMenuItem("Pregled clanova koji kasne sa vracanjem knjiga");
        PregledClanovaKojiKasne.setIcon(new ImageIcon(BibliotekarAdminProzor.class.getResource("/slike/pregled.png")));
        PregledClanovaKojiKasne.setFont(new Font("Yu Gothic", Font.BOLD, 12));
        PregledClanovaKojiKasne.addActionListener(e -> pregledKasnjenja(fabrika));

        JMenuItem DeaktivacijaClana = new JMenuItem("Deaktivacija Clana");
        DeaktivacijaClana.setIcon(new ImageIcon(BibliotekarAdminProzor.class.getResource("/slike/deaktivacija.png")));
        DeaktivacijaClana.setFont(new Font("Yu Gothic", Font.BOLD, 12));
        DeaktivacijaClana.addActionListener(e -> {
            //prozorZaDeaktivaciju
        });

        JMenuItem DodavanjeClana = new JMenuItem("Dodaj Clana");
        DodavanjeClana.setIcon(new ImageIcon(BibliotekarPozajmiceProzor.class.getResource("/slike/dodavanje.png")));
        DodavanjeClana.setFont(new Font("Yu Gothic", Font.BOLD, 12));
        DodavanjeClana.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RegistracijaClana.main(fabrika);}
        });

        Clanovi.add(PregledClanova);
        Clanovi.add(PregledClanovaKojiKasne);
        Clanovi.add(DeaktivacijaClana);
        Clanovi.add(DodavanjeClana);

        JLabel IkonicaRezervacije = new JLabel("");
        IkonicaRezervacije.setIcon(new ImageIcon(BibliotekarAdminProzor.class.getResource("/slike/rezervacije.png")));
        menuBar.add(IkonicaRezervacije);

        JMenu Rezervacije = new JMenu("Rezervacije");
        Rezervacije.setFont(new Font("Yu Gothic", Font.PLAIN, 16));
        menuBar.add(Rezervacije);

        JMenuItem OdobravanjeRezervacija = new JMenuItem("Odobravanje rezervacija");
        OdobravanjeRezervacija.setIcon(new ImageIcon(BibliotekarAdminProzor.class.getResource("/slike/odobravanje.png")));
        OdobravanjeRezervacija.addActionListener(e -> {
            //prozorZaOdobravanjeRezervacije
        });
        OdobravanjeRezervacija.setFont(new Font("Yu Gothic", Font.BOLD, 12));
        Rezervacije.add(OdobravanjeRezervacija);

        JLabel IkonicaIzvestaj = new JLabel("");
        IkonicaIzvestaj.setIcon(new ImageIcon(BibliotekarAdminProzor.class.getResource("/slike/izvestaj.png")));
        menuBar.add(IkonicaIzvestaj);

        JMenu Izvestaji = new JMenu("Izvestaji");
        Izvestaji.setFont(new Font("Yu Gothic", Font.PLAIN, 16));
        menuBar.add(Izvestaji);

        JMenuItem izvestajCitanost = new JMenuItem("Citanost");
        izvestajCitanost.setFont(new Font("Yu Gothic", Font.BOLD, 12));
        izvestajCitanost.setIcon(new ImageIcon(BibliotekarAdminProzor.class.getResource("/slike/citanje.png")));
        izvestajCitanost.addActionListener(e -> {
            //prozorIzvestajCitanost
        });

        JMenuItem izvestajNabavka = new JMenuItem("Nabavka");
        izvestajNabavka.setFont(new Font("Yu Gothic", Font.BOLD, 12));
        izvestajNabavka.setIcon(new ImageIcon(BibliotekarAdminProzor.class.getResource("/slike/nabavka.png")));
        izvestajNabavka.addActionListener(e -> {
            //prozorizvestajNabavka
        });

        JMenuItem izvestajRadUSmenama = new JMenuItem("Rad u smenama");
        izvestajRadUSmenama.setFont(new Font("Yu Gothic", Font.BOLD, 12));
        izvestajRadUSmenama.setIcon(new ImageIcon(BibliotekarAdminProzor.class.getResource("/slike/smene.png")));
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

        JMenu cenovnik = new JMenu("Cenovnik");
        cenovnik.setIcon(new ImageIcon(BibliotekarAdminProzor.class.getResource("/slike/euro.png")));
        cenovnik.setFont(new Font("Yu Gothic", Font.PLAIN, 16));
        menuBar.add(cenovnik);

        JMenu godisnjaClanarina = new JMenu("Godisnja clanarina");
        godisnjaClanarina.setFont(new Font("Yu Gothic", Font.BOLD, 12));
        godisnjaClanarina.setIcon(new ImageIcon(BibliotekarAdminProzor.class.getResource("/slike/euro.png")));
        godisnjaClanarina.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //

            }
        });
        cenovnik.add(godisnjaClanarina);
        JMenuItem student = new JMenuItem("Student");
        student.setFont(new Font("Yu Gothic", Font.PLAIN, 12));
        student.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String bonus = JOptionPane.showInputDialog(BibliotekarAdminProzor.this, "Trenutna cena je " + String.valueOf(fabrika.getMenadzercena().pronadjiPoTipuiTrajanju(TipClanarine.student, TrajanjeClanarine.godinu).getCena() + ", Upisite novu cenu: "), "Cena godisnje clanarine studenta", JOptionPane.INFORMATION_MESSAGE);
                    if(bonus != null) {
                        fabrika.getMenadzercena().pronadjiPoTipuiTrajanju(TipClanarine.student, TrajanjeClanarine.godinu).setCena(Double.parseDouble(bonus));
                        fabrika.getMenadzercena().azurirajFajl();
                        JOptionPane.showMessageDialog(contentPane, "Uspesno ste promenili cenu", "Uspesna izmena", JOptionPane.INFORMATION_MESSAGE);

                    }

                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(BibliotekarAdminProzor.this, "Pogresan tip, mozete koristiti samo brojeve!", "Error", JOptionPane.ERROR_MESSAGE);
                }

            }
        });
        godisnjaClanarina.add(student);

        JMenuItem penzioner = new JMenuItem("Penzioner");
        penzioner.setFont(new Font("Yu Gothic", Font.PLAIN, 12));
        penzioner.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String bonus = JOptionPane.showInputDialog(BibliotekarAdminProzor.this, "Trenutna cena je " + String.valueOf(fabrika.getMenadzercena().pronadjiPoTipuiTrajanju(TipClanarine.penzioner, TrajanjeClanarine.godinu).getCena() + ", Upisite novu cenu: "), "Cena godisnje clanarine penzionera", JOptionPane.INFORMATION_MESSAGE);
                    if(bonus != null) {
                        fabrika.getMenadzercena().pronadjiPoTipuiTrajanju(TipClanarine.penzioner, TrajanjeClanarine.godinu).setCena(Double.parseDouble(bonus));
                        fabrika.getMenadzercena().azurirajFajl();
                        JOptionPane.showMessageDialog(contentPane, "Uspesno ste promenili cenu", "Uspesna izmena", JOptionPane.INFORMATION_MESSAGE);
                    }

                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(BibliotekarAdminProzor.this, "Pogresan tip, mozete koristiti samo brojeve!", "Error", JOptionPane.ERROR_MESSAGE);
                }

            }
        });
        godisnjaClanarina.add(penzioner);

        JMenuItem pocasniClan = new JMenuItem("Pocasni clan");
        pocasniClan.setFont(new Font("Yu Gothic", Font.PLAIN, 12));
        pocasniClan.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String bonus = JOptionPane.showInputDialog(BibliotekarAdminProzor.this, "Trenutna cena je " + String.valueOf(fabrika.getMenadzercena().pronadjiPoTipuiTrajanju(TipClanarine.pocasniClan, TrajanjeClanarine.godinu).getCena() + ", Upisite novu cenu: "), "Cena godisnje clanarine pocasnog clana", JOptionPane.INFORMATION_MESSAGE);
                    if(bonus != null) {
                        fabrika.getMenadzercena().pronadjiPoTipuiTrajanju(TipClanarine.pocasniClan, TrajanjeClanarine.godinu).setCena(Double.parseDouble(bonus));
                        fabrika.getMenadzercena().azurirajFajl();
                        JOptionPane.showMessageDialog(contentPane, "Uspesno ste promenili cenu", "Uspesna izmena", JOptionPane.INFORMATION_MESSAGE);
                    }

                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(BibliotekarAdminProzor.this, "Pogresan tip, mozete koristiti samo brojeve!", "Error", JOptionPane.ERROR_MESSAGE);
                }

            }
        });
        godisnjaClanarina.add(pocasniClan);

        JMenuItem djakc = new JMenuItem("Djak");
        djakc.setFont(new Font("Yu Gothic", Font.PLAIN, 12));
        djakc.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String bonus = JOptionPane.showInputDialog(BibliotekarAdminProzor.this, "Trenutna cena je " + String.valueOf(fabrika.getMenadzercena().pronadjiPoTipuiTrajanju(TipClanarine.djak, TrajanjeClanarine.godinu).getCena() + ", Upisite novu cenu: "), "Cena godisnje clanarine djaka", JOptionPane.INFORMATION_MESSAGE);
                    if(bonus != null) {
                        fabrika.getMenadzercena().pronadjiPoTipuiTrajanju(TipClanarine.djak, TrajanjeClanarine.godinu).setCena(Double.parseDouble(bonus));
                        fabrika.getMenadzercena().azurirajFajl();
                        JOptionPane.showMessageDialog(contentPane, "Uspesno ste promenili cenu", "Uspesna izmena", JOptionPane.INFORMATION_MESSAGE);
                    }

                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(BibliotekarAdminProzor.this, "Pogresan tip, mozete koristiti samo brojeve!", "Error", JOptionPane.ERROR_MESSAGE);
                }

            }
        });
        godisnjaClanarina.add(djakc);

        JMenuItem zaposlen = new JMenuItem("Zaposlen");
        zaposlen.setFont(new Font("Yu Gothic", Font.PLAIN, 12));
        zaposlen.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String bonus = JOptionPane.showInputDialog(BibliotekarAdminProzor.this, "Trenutna cena je " + String.valueOf(fabrika.getMenadzercena().pronadjiPoTipuiTrajanju(TipClanarine.zaposlen, TrajanjeClanarine.godinu).getCena() + ", Upisite novu cenu: "), "Cena godisnje clanarine zaposlenog", JOptionPane.INFORMATION_MESSAGE);
                    if(bonus != null) {
                        fabrika.getMenadzercena().pronadjiPoTipuiTrajanju(TipClanarine.zaposlen, TrajanjeClanarine.godinu).setCena(Double.parseDouble(bonus));
                        fabrika.getMenadzercena().azurirajFajl();
                        JOptionPane.showMessageDialog(contentPane, "Uspesno ste promenili cenu", "Uspesna izmena", JOptionPane.INFORMATION_MESSAGE);
                    }

                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(BibliotekarAdminProzor.this, "Pogresan tip, mozete koristiti samo brojeve!", "Error", JOptionPane.ERROR_MESSAGE);
                }

            }
        });
        godisnjaClanarina.add(zaposlen);

        JMenuItem nezaposlen = new JMenuItem("Nezaposlen");
        nezaposlen.setFont(new Font("Yu Gothic", Font.PLAIN, 12));
        nezaposlen.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String bonus = JOptionPane.showInputDialog(BibliotekarAdminProzor.this, "Trenutna cena je " + String.valueOf(fabrika.getMenadzercena().pronadjiPoTipuiTrajanju(TipClanarine.nezaposlen, TrajanjeClanarine.godinu).getCena() + ", Upisite novu cenu: "), "Cena godisnje clanarine nezaposlenog", JOptionPane.INFORMATION_MESSAGE);
                    if(bonus != null) {
                        fabrika.getMenadzercena().pronadjiPoTipuiTrajanju(TipClanarine.nezaposlen, TrajanjeClanarine.godinu).setCena(Double.parseDouble(bonus));
                        fabrika.getMenadzercena().azurirajFajl();
                        JOptionPane.showMessageDialog(contentPane, "Uspesno ste promenili cenu", "Uspesna izmena", JOptionPane.INFORMATION_MESSAGE);
                    }

                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(BibliotekarAdminProzor.this, "Pogresan tip, mozete koristiti samo brojeve!", "Error", JOptionPane.ERROR_MESSAGE);
                }

            }
        });
        godisnjaClanarina.add(nezaposlen);

        JMenuItem dete = new JMenuItem("Dete");
        dete.setFont(new Font("Yu Gothic", Font.PLAIN, 12));
        dete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String bonus = JOptionPane.showInputDialog(BibliotekarAdminProzor.this, "Trenutna cena je " + String.valueOf(fabrika.getMenadzercena().pronadjiPoTipuiTrajanju(TipClanarine.dete, TrajanjeClanarine.godinu).getCena() + ", Upisite novu cenu: "), "Cena godisnje clanarine deteta", JOptionPane.INFORMATION_MESSAGE);
                    if(bonus != null) {
                        fabrika.getMenadzercena().pronadjiPoTipuiTrajanju(TipClanarine.dete, TrajanjeClanarine.godinu).setCena(Double.parseDouble(bonus));
                        fabrika.getMenadzercena().azurirajFajl();
                        JOptionPane.showMessageDialog(contentPane, "Uspesno ste promenili cenu", "Uspesna izmena", JOptionPane.INFORMATION_MESSAGE);
                    }

                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(BibliotekarAdminProzor.this, "Pogresan tip, mozete koristiti samo brojeve!", "Error", JOptionPane.ERROR_MESSAGE);
                }

            }
        });
        godisnjaClanarina.add(dete);





        JMenu polaGodine = new JMenu("Polugodisnja clanarina");
        polaGodine.setFont(new Font("Yu Gothic", Font.BOLD, 12));
        polaGodine.setIcon(new ImageIcon(BibliotekarAdminProzor.class.getResource("/slike/euro.png")));
        polaGodine.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //
            }
        });
        cenovnik.add(polaGodine);

        JMenuItem studentPola = new JMenuItem("Student");
        studentPola.setFont(new Font("Yu Gothic", Font.PLAIN, 12));
        studentPola.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String bonus = JOptionPane.showInputDialog(BibliotekarAdminProzor.this, "Trenutna cena je " + String.valueOf(fabrika.getMenadzercena().pronadjiPoTipuiTrajanju(TipClanarine.student, TrajanjeClanarine.polaGodine).getCena() + ", Upisite novu cenu: "), "Cena polugodisnje clanarine studenta", JOptionPane.INFORMATION_MESSAGE);
                    if(bonus != null) {
                        fabrika.getMenadzercena().pronadjiPoTipuiTrajanju(TipClanarine.student, TrajanjeClanarine.polaGodine).setCena(Double.parseDouble(bonus));
                        fabrika.getMenadzercena().azurirajFajl();
                        JOptionPane.showMessageDialog(contentPane, "Uspesno ste promenili cenu", "Uspesna izmena", JOptionPane.INFORMATION_MESSAGE);
                    }

                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(BibliotekarAdminProzor.this, "Pogresan tip, mozete koristiti samo brojeve!", "Error", JOptionPane.ERROR_MESSAGE);
                }

            }
        });
        polaGodine.add(studentPola);

        JMenuItem penzionerPola = new JMenuItem("Penzioner");
        penzionerPola.setFont(new Font("Yu Gothic", Font.PLAIN, 12));
        penzionerPola.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String bonus = JOptionPane.showInputDialog(BibliotekarAdminProzor.this, "Trenutna cena je " + String.valueOf(fabrika.getMenadzercena().pronadjiPoTipuiTrajanju(TipClanarine.penzioner, TrajanjeClanarine.polaGodine).getCena() + ", Upisite novu cenu: "), "Cena polugodisnje clanarine penzionera", JOptionPane.INFORMATION_MESSAGE);
                    if(bonus != null) {
                        fabrika.getMenadzercena().pronadjiPoTipuiTrajanju(TipClanarine.penzioner, TrajanjeClanarine.polaGodine).setCena(Double.parseDouble(bonus));
                        fabrika.getMenadzercena().azurirajFajl();
                        JOptionPane.showMessageDialog(contentPane, "Uspesno ste promenili cenu", "Uspesna izmena", JOptionPane.INFORMATION_MESSAGE);
                    }

                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(BibliotekarAdminProzor.this, "Pogresan tip, mozete koristiti samo brojeve!", "Error", JOptionPane.ERROR_MESSAGE);
                }

            }
        });
        polaGodine.add(penzionerPola);

        JMenuItem pocasniClanPola = new JMenuItem("Pocasni clan");
        pocasniClanPola.setFont(new Font("Yu Gothic", Font.PLAIN, 12));
        pocasniClanPola.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String bonus = JOptionPane.showInputDialog(BibliotekarAdminProzor.this, "Trenutna cena je " + String.valueOf(fabrika.getMenadzercena().pronadjiPoTipuiTrajanju(TipClanarine.pocasniClan, TrajanjeClanarine.polaGodine).getCena() + ", Upisite novu cenu: "), "Cena polugodisnje clanarine pocasnog clana", JOptionPane.INFORMATION_MESSAGE);
                    if(bonus != null) {
                        fabrika.getMenadzercena().pronadjiPoTipuiTrajanju(TipClanarine.pocasniClan, TrajanjeClanarine.polaGodine).setCena(Double.parseDouble(bonus));
                        fabrika.getMenadzercena().azurirajFajl();
                        JOptionPane.showMessageDialog(contentPane, "Uspesno ste promenili cenu", "Uspesna izmena", JOptionPane.INFORMATION_MESSAGE);
                    }

                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(BibliotekarAdminProzor.this, "Pogresan tip, mozete koristiti samo brojeve!", "Error", JOptionPane.ERROR_MESSAGE);
                }

            }
        });
        polaGodine.add(pocasniClanPola);

        JMenuItem djakPola = new JMenuItem("Djak");
        djakPola.setFont(new Font("Yu Gothic", Font.PLAIN, 12));
        djakPola.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String bonus = JOptionPane.showInputDialog(BibliotekarAdminProzor.this, "Trenutna cena je " + String.valueOf(fabrika.getMenadzercena().pronadjiPoTipuiTrajanju(TipClanarine.djak, TrajanjeClanarine.polaGodine).getCena() + ", Upisite novu cenu: "), "Cena polugodisnje clanarine djaka", JOptionPane.INFORMATION_MESSAGE);
                    if(bonus != null) {
                        fabrika.getMenadzercena().pronadjiPoTipuiTrajanju(TipClanarine.djak, TrajanjeClanarine.polaGodine).setCena(Double.parseDouble(bonus));
                        fabrika.getMenadzercena().azurirajFajl();
                        JOptionPane.showMessageDialog(contentPane, "Uspesno ste promenili cenu", "Uspesna izmena", JOptionPane.INFORMATION_MESSAGE);
                    }

                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(BibliotekarAdminProzor.this, "Pogresan tip, mozete koristiti samo brojeve!", "Error", JOptionPane.ERROR_MESSAGE);
                }

            }
        });
        polaGodine.add(djakPola);

        JMenuItem zaposlenPola = new JMenuItem("Zaposlen");
        zaposlenPola.setFont(new Font("Yu Gothic", Font.PLAIN, 12));
        zaposlenPola.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String bonus = JOptionPane.showInputDialog(BibliotekarAdminProzor.this, "Trenutna cena je " + String.valueOf(fabrika.getMenadzercena().pronadjiPoTipuiTrajanju(TipClanarine.zaposlen, TrajanjeClanarine.polaGodine).getCena() + ", Upisite novu cenu: "), "Cena polugodisnje clanarine zaposlenog", JOptionPane.INFORMATION_MESSAGE);
                    if(bonus != null) {
                        fabrika.getMenadzercena().pronadjiPoTipuiTrajanju(TipClanarine.zaposlen, TrajanjeClanarine.polaGodine).setCena(Double.parseDouble(bonus));
                        fabrika.getMenadzercena().azurirajFajl();
                        JOptionPane.showMessageDialog(contentPane, "Uspesno ste promenili cenu", "Uspesna izmena", JOptionPane.INFORMATION_MESSAGE);
                    }

                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(BibliotekarAdminProzor.this, "Pogresan tip, mozete koristiti samo brojeve!", "Error", JOptionPane.ERROR_MESSAGE);
                }

            }
        });
        polaGodine.add(zaposlenPola);

        JMenuItem nezaposlenPola = new JMenuItem("Nezaposlen");
        nezaposlenPola.setFont(new Font("Yu Gothic", Font.PLAIN, 12));
        nezaposlenPola.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String bonus = JOptionPane.showInputDialog(BibliotekarAdminProzor.this, "Trenutna cena je " + String.valueOf(fabrika.getMenadzercena().pronadjiPoTipuiTrajanju(TipClanarine.nezaposlen, TrajanjeClanarine.polaGodine).getCena() + ", Upisite novu cenu: "), "Cena polugodisnje clanarine nezaposlenog", JOptionPane.INFORMATION_MESSAGE);
                    if(bonus != null) {
                        fabrika.getMenadzercena().pronadjiPoTipuiTrajanju(TipClanarine.nezaposlen, TrajanjeClanarine.polaGodine).setCena(Double.parseDouble(bonus));
                        fabrika.getMenadzercena().azurirajFajl();
                        JOptionPane.showMessageDialog(contentPane, "Uspesno ste promenili cenu", "Uspesna izmena", JOptionPane.INFORMATION_MESSAGE);
                    }

                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(BibliotekarAdminProzor.this, "Pogresan tip, mozete koristiti samo brojeve!", "Error", JOptionPane.ERROR_MESSAGE);
                }

            }
        });
        polaGodine.add(nezaposlenPola);

        JMenuItem detePola = new JMenuItem("Dete");
        detePola.setFont(new Font("Yu Gothic", Font.PLAIN, 12));
        detePola.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String bonus = JOptionPane.showInputDialog(BibliotekarAdminProzor.this, "Trenutna cena je " + String.valueOf(fabrika.getMenadzercena().pronadjiPoTipuiTrajanju(TipClanarine.dete, TrajanjeClanarine.polaGodine).getCena() + ", Upisite novu cenu: "), "Cena polugodisnje clanarine deteta", JOptionPane.INFORMATION_MESSAGE);
                    if(bonus != null) {
                        fabrika.getMenadzercena().pronadjiPoTipuiTrajanju(TipClanarine.dete, TrajanjeClanarine.polaGodine).setCena(Double.parseDouble(bonus));
                        fabrika.getMenadzercena().azurirajFajl();
                        JOptionPane.showMessageDialog(contentPane, "Uspesno ste promenili cenu", "Uspesna izmena", JOptionPane.INFORMATION_MESSAGE);
                    }

                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(BibliotekarAdminProzor.this, "Pogresan tip, mozete koristiti samo brojeve!", "Error", JOptionPane.ERROR_MESSAGE);
                }

            }
        });
        polaGodine.add(detePola);

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
                    BibliotekarAdminProzor.this.dispose();
                    Prijavljivanje prijava = new Prijavljivanje(fabrika);
                    prijava.setVisible(true);
                }
            }
        });
        logoutLabel.setIcon(new ImageIcon(BibliotekarAdminProzor.class.getResource("/slike/odjaviSe.png")));
        logoutLabel.setBounds(800, 425, 139, 98);
        contentPane.add(logoutLabel);

        JLabel Background = new JLabel("");
        Background.setIcon(new ImageIcon(BibliotekarAdminProzor.class.getResource("/slike/bibliotekarPozadina.png")));
        Background.setBounds(0, 0, 907, 574);
        contentPane.add(Background);
    }

    private Object pregledKasnjenja(Fabrika fabrika) {
        List<Clan> clanovi = new ArrayList<>();
        for (Clan c : fabrika.getMenadzerClanova().getClanovi()) {
            for (Pozajmica p : c.getPozajmice()) {
                if (p.getStatus().equals(StatusPozajmice.istekla)) {
                    clanovi.add(c);
                    break;
                }
            }
        }
        if (!clanovi.isEmpty())
            return new PregledKasnjenjaDialog(fabrika, clanovi);
        else
            JOptionPane.showMessageDialog(null, "Nema clanova sa kasnjenjem.", "Greška", JOptionPane.WARNING_MESSAGE);
        return null;
    }
}
