package gui;

import enumerations.VrstaNaloga;
import gui.bibliotekar.BibliotekarKatalogizacija;
import gui.bibliotekar.pozajmice.BibliotekarPozajmice;
import repository.Fabrika;
import userEntities.Bibliotekar;
import userEntities.Clan;
import userEntities.KorisnickiNalog;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Prijavljivanje extends JFrame {
    private static final long serialVersionUID = 1L;
    private JFrame frame;
    private JPanel contentPane;
    private final JTextField usernameText;
    private final JPasswordField passwordField;
    private JLabel label;


    public static void main(String[] args, Fabrika fabrika) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Prijavljivanje prijava = new Prijavljivanje(fabrika);
                    prijava.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }


    public Prijavljivanje(Fabrika fabrika) {
        setIconImage(Toolkit.getDefaultToolkit().getImage(Prijavljivanje.class.getResource("/slike/logo.jpg")));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 920, 644);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        passwordField = new JPasswordField();
        passwordField.setBounds(575, 115, 246, 37);
        passwordField.setFont(new Font("Arial Black", Font.PLAIN, 15));
        passwordField.setForeground(Color.WHITE);
        passwordField.setOpaque(false);
        contentPane.add(passwordField);

        usernameText = new JTextField();
        usernameText.setBounds(575, 49, 246, 37);
        usernameText.setFont(new Font("Arial Black", Font.PLAIN, 15));
        usernameText.setForeground(Color.WHITE);
        usernameText.setOpaque(false);
        contentPane.add(usernameText);
        usernameText.setColumns(10);
        JButton button = new JButton("Prijavi se");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = usernameText.getText();
                String password = String.valueOf(passwordField.getPassword());
                if (fabrika.getMenadzerKorisnickihNaloga().validnostPrijave(username, password)) {
                    KorisnickiNalog nalog = fabrika.getMenadzerKorisnickihNaloga().pronadjiNalogSaUsername(username);
                    if (nalog.getVrstaNaloga() == VrstaNaloga.bibliotekarZaPozajmice) {
                        Bibliotekar b = fabrika.getMenadzerBibliotekara().pronadjiBibliotekaraPoNalogu(nalog);
                        JOptionPane.showMessageDialog(contentPane, "Ulogovali ste se kao " + b.getIme() + " " + b.getPrezime() + ", bibliotekar za pozajmice.", "Uspesno prijavljivanje", JOptionPane.INFORMATION_MESSAGE);
                        BibliotekarPozajmice bp = new BibliotekarPozajmice(fabrika, b);
                        bp.setVisible(true);
                        Prijavljivanje.this.setVisible(false);
                        Prijavljivanje.this.dispose();
                    } else if (nalog.getVrstaNaloga() == VrstaNaloga.bibliotekarZaKatalogizaciju) {
                        Bibliotekar b = fabrika.getMenadzerBibliotekara().pronadjiBibliotekaraPoNalogu(nalog);
                        JOptionPane.showMessageDialog(contentPane, "Ulogovali ste se kao " + b.getIme() + " " + b.getPrezime() + ", bibliotekar za katalogizaciju.", "Uspesno prijavljivanje", JOptionPane.INFORMATION_MESSAGE);
                        BibliotekarKatalogizacija bibliotekarKatalogizacija = new BibliotekarKatalogizacija(fabrika, b);
                        bibliotekarKatalogizacija.setVisible(true);
                        Prijavljivanje.this.setVisible(false);
                        Prijavljivanje.this.dispose();
                    } else if (nalog.getVrstaNaloga() == VrstaNaloga.bibliotekar) {
                        Bibliotekar b = fabrika.getMenadzerBibliotekara().pronadjiBibliotekaraPoNalogu(nalog);
                        JOptionPane.showMessageDialog(contentPane, "Ulogovali ste se kao " + b.getIme() + " " + b.getPrezime() + ", bibliotekar.", "Uspesno prijavljivanje", JOptionPane.INFORMATION_MESSAGE);
                        //kreiranje instance glavnog prozora bibliotekara
                        //postaviti instancu kao vidljivu
                        Prijavljivanje.this.setVisible(false);
                        Prijavljivanje.this.dispose();
                    } else if (nalog.getVrstaNaloga() == VrstaNaloga.admin) {
                        Bibliotekar b = fabrika.getMenadzerBibliotekara().pronadjiBibliotekaraPoNalogu(nalog);
                        JOptionPane.showMessageDialog(contentPane, "Ulogovali ste se kao " + b.getIme() + " " + b.getPrezime() + ", admin bibliotekar.", "Uspesno prijavljivanje", JOptionPane.INFORMATION_MESSAGE);
                        //kreiranje instance glavnog prozora bibliotekara admina
                        //postaviti instancu kao vidljivu
                        Prijavljivanje.this.setVisible(false);
                        Prijavljivanje.this.dispose();
                    }
                    else if (nalog.getVrstaNaloga() == VrstaNaloga.clan) {
                        Clan c = fabrika.getMenadzerClanova().pronadjiClanaPoNalogu(nalog);
                        JOptionPane.showMessageDialog(contentPane, "Ulogovali ste se kao " + c.getIme() + " " + c.getPrezime() + ", clan.", "Uspesno prijavljivanje", JOptionPane.INFORMATION_MESSAGE);
                        //kreiranje instance glavnog prozora clana
                        //postaviti instancu kao vidljivu
                        Prijavljivanje.this.setVisible(false);
                        Prijavljivanje.this.dispose();
                    }
                }
            }
        });
        button.setBackground(Color.DARK_GRAY);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial Black", Font.PLAIN, 26));
        button.setBounds(575, 189, 246, 60);
        contentPane.add(button);


        JLabel passwordLabel = new JLabel("");
        passwordLabel.setBounds(500, 100, 65, 58);
        passwordLabel.setIcon(new ImageIcon(Prijavljivanje.class.getResource("/slike/lozinkaLogo.png")));
        contentPane.add(passwordLabel);

        JLabel usernameLabel = new JLabel("");
        usernameLabel.setBounds(518, 49, 45, 38);
        usernameLabel.setIcon(new ImageIcon(Prijavljivanje.class.getResource("/slike/korisnikLogo.png")));
        contentPane.add(usernameLabel);

        label = new JLabel("");
        label.setBackground(Color.BLUE);
        label.setIcon(new ImageIcon(Prijavljivanje.class.getResource("/slike/prijavaPozadina.png")));
        label.setBounds(-49, 0, 951, 600);
        contentPane.add(label);
    }
}

