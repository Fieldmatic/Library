package gui.bibliotekar.pozajmice.clanovi;
import com.toedter.calendar.JDateChooser;
import entities.Clanarina;
import enumerations.Pol;
import enumerations.TipClanarine;
import enumerations.VrstaNaloga;
import gui.Prijavljivanje;
import gui.bibliotekar.pozajmice.BibliotekarPozajmiceProzor;
import repository.Fabrika;
import userEntities.Clan;
import userEntities.KorisnickiNalog;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Toolkit;
import javax.swing.UIManager;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;

import javax.swing.JRadioButton;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;

import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JPasswordField;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.ImageIcon;

public class RegistracijaClana extends JFrame {


    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField korisnickoIme;
    private JTextField ime;
    private JTextField prezime;
    private JTextField jmbg;
    private final ButtonGroup pol = new ButtonGroup();
    private JPasswordField lozinka;
    private final ButtonGroup trajanjeClanarine = new ButtonGroup();

    public static void main(Fabrika fabrika) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    RegistracijaClana frame = new RegistracijaClana(fabrika);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public RegistracijaClana(Fabrika fabrika) {
        setTitle("Registracija clana");
        setIconImage(Toolkit.getDefaultToolkit().getImage(RegistracijaClana.class.getResource("/slike/logo.jpg")));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 650, 515);
        contentPane = new JPanel();
        contentPane.setBackground(Color.LIGHT_GRAY);
        contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        korisnickoIme = new JTextField();
        korisnickoIme.setForeground(Color.WHITE);
        korisnickoIme.setFont(new Font("Yu Gothic", Font.BOLD, 14));
        korisnickoIme.setOpaque(false);
        korisnickoIme.setBounds(112, 60, 140, 20);
        contentPane.add(korisnickoIme);
        korisnickoIme.setColumns(10);

        ime = new JTextField();
        ime.setForeground(Color.WHITE);
        ime.setFont(new Font("Yu Gothic", Font.BOLD, 14));
        ime.setOpaque(false);
        ime.setBounds(112, 112, 140, 20);
        contentPane.add(ime);
        ime.setColumns(10);

        prezime = new JTextField();
        prezime.setForeground(Color.WHITE);
        prezime.setFont(new Font("Yu Gothic", Font.BOLD, 14));
        prezime.setOpaque(false);
        prezime.setBounds(112, 135, 140, 20);
        contentPane.add(prezime);
        prezime.setColumns(10);

        jmbg = new JTextField();
        jmbg.setForeground(Color.white);
        jmbg.setFont(new Font("Yu Gothic", Font.BOLD, 14));
        jmbg.setOpaque(false);
        jmbg.setBounds(112, 160, 140, 20);
        contentPane.add(jmbg);
        jmbg.setColumns(10);

        JDateChooser rodjendan = new JDateChooser();
        rodjendan.setBackground(Color.BLACK);
        rodjendan.setForeground(Color.WHITE);
        rodjendan.setOpaque(false);
        rodjendan.setSize(140, 20);
        rodjendan.setLocation(112, 185);
        getContentPane().add(rodjendan);

        JRadioButton musko = new JRadioButton("Musko");
        musko.setForeground(Color.WHITE);
        musko.setFont(new Font("Yu Gothic", Font.BOLD, 12));
        musko.setOpaque(false);
        pol.add(musko);
        musko.setSelected(true);
        musko.setBounds(100, 270, 85, 23);
        contentPane.add(musko);

        JRadioButton zensko = new JRadioButton("Zensko");
        zensko.setForeground(Color.WHITE);
        zensko.setFont(new Font("Yu Gothic", Font.BOLD, 12));
        zensko.setOpaque(false);
        pol.add(zensko);
        zensko.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                musko.setSelected(false);
                zensko.setSelected(true);

            }
        });
        zensko.setBounds(180, 270, 85, 23);
        contentPane.add(zensko);

        JComboBox<String> tipClana = new JComboBox<>();
        tipClana.setFont(new Font("Yu Gothic", Font.BOLD, 12));
        tipClana.setOpaque(false);
        tipClana.setToolTipText("Vrsta clanarine");
        tipClana.addItem("student");
        tipClana.addItem("penzioner");
        tipClana.addItem("pocasni clan");
        tipClana.addItem("zaposlen");
        tipClana.addItem("nezaposlen");
        tipClana.addItem("djak");
        tipClana.addItem("dete");
        tipClana.setBounds(112, 210, 140, 20);
        contentPane.add(tipClana);

        JLabel LkorisnickoIme = new JLabel("Korisnicko ime:");
        LkorisnickoIme.setForeground(Color.WHITE);
        LkorisnickoIme.setFont(new Font("Yu Gothic", Font.BOLD, 12));
        LkorisnickoIme.setBounds(10, 62, 130, 14);
        contentPane.add(LkorisnickoIme);

        JLabel Llozinka = new JLabel("Lozinka:");
        Llozinka.setForeground(Color.WHITE);
        Llozinka.setFont(new Font("Yu Gothic", Font.BOLD, 12));
        Llozinka.setBounds(10, 87, 84, 14);
        contentPane.add(Llozinka);

        lozinka = new JPasswordField();
        lozinka.setForeground(Color.WHITE);
        lozinka.setFont(new Font("Yu Gothic", Font.BOLD, 14));
        lozinka.setOpaque(false);
        lozinka.setBounds(112, 85, 140, 20);
        contentPane.add(lozinka);

        JLabel Lime = new JLabel("Ime:");
        Lime.setForeground(Color.WHITE);
        Lime.setFont(new Font("Yu Gothic", Font.BOLD, 12));
        Lime.setBounds(10, 112, 46, 14);
        contentPane.add(Lime);

        JLabel Lprezime = new JLabel("Prezime:");
        Lprezime.setForeground(Color.WHITE);
        Lprezime.setFont(new Font("Yu Gothic", Font.BOLD, 12));
        Lprezime.setBounds(10, 137, 74, 14);
        contentPane.add(Lprezime);

        JLabel Ljmbg = new JLabel("Jmbg:");
        Ljmbg.setForeground(Color.WHITE);
        Ljmbg.setFont(new Font("Yu Gothic", Font.BOLD, 12));
        Ljmbg.setBounds(10, 163, 85, 14);
        contentPane.add(Ljmbg);

        JLabel Lrodjendan = new JLabel("Datum rodjenja:");
        Lrodjendan.setForeground(Color.WHITE);
        Lrodjendan.setFont(new Font("Yu Gothic", Font.BOLD, 12));
        Lrodjendan.setBounds(10, 187, 130, 14);
        contentPane.add(Lrodjendan);

        JLabel LvrstaClanarine = new JLabel("Vrsta clanarine:");
        LvrstaClanarine.setForeground(Color.WHITE);
        LvrstaClanarine.setFont(new Font("Yu Gothic", Font.BOLD, 12));
        LvrstaClanarine.setBounds(10, 212, 120, 14);
        contentPane.add(LvrstaClanarine);

        JRadioButton clanarina1 = new JRadioButton("6 meseci");
        clanarina1.setForeground(Color.WHITE);
        clanarina1.setFont(new Font("Yu Gothic", Font.BOLD, 12));
        clanarina1.setOpaque(false);
        trajanjeClanarine.add(clanarina1);
        clanarina1.setBounds(78, 235, 114, 23);
        clanarina1.setSelected(true);
        contentPane.add(clanarina1);

        JRadioButton clanarina2 = new JRadioButton("12 meseci");
        clanarina2.setForeground(Color.WHITE);
        clanarina2.setFont(new Font("Yu Gothic", Font.BOLD, 12));
        clanarina2.setOpaque(false);
        trajanjeClanarine.add(clanarina2);
        clanarina2.setBounds(178, 235, 140, 23);
        contentPane.add(clanarina2);


        JButton Registruj = new JButton("Registruj");
        Registruj.setBackground(Color.BLUE);
        Registruj.setForeground(UIManager.getColor("Button.background"));
        Registruj.setFont(new Font("Yu Gothic UI", Font.BOLD, 16));
        Registruj.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int izbor = JOptionPane.showConfirmDialog(RegistracijaClana.this, "Jeste li sigurni? ", "Potvrda", JOptionPane.YES_NO_OPTION);
                if (izbor == JOptionPane.YES_OPTION) {
                    Pol sex;
                    LocalDate datum = LocalDate.now();
                    if (!fabrika.getProvere().proveraIspravnostiKorisnickoImeLozinka(korisnickoIme.getText())) {
                        JOptionPane.showMessageDialog(RegistracijaClana.this, "Pogresno korisnicko ime !", "Greska", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    if (!fabrika.getProvere().proveraIspravnostiKorisnickoImeLozinka((String.valueOf(lozinka.getPassword()).toString()))) {
                        JOptionPane.showMessageDialog(RegistracijaClana.this, "Pogresna lozinka !", "Greska", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    if (!fabrika.getProvere().proveraDostupnostiKorisnickogImena(korisnickoIme.getText())) {
                        JOptionPane.showMessageDialog(RegistracijaClana.this, "Korisnicko ime vec zauzeto!", "Greska", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    if (!fabrika.getProvere().validacijaSlova(ime.getText())) {
                        JOptionPane.showMessageDialog(RegistracijaClana.this, "Pogresno ime, mozete koristiti samo slova !", "Greska", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    if (!fabrika.getProvere().validacijaSlova(prezime.getText())) {
                        JOptionPane.showMessageDialog(RegistracijaClana.this, "Pogresno prezime, mozete koristiti samo slova !", "Greska", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    if (!fabrika.getProvere().validacijaJmbg(jmbg.getText())) {
                        JOptionPane.showMessageDialog(RegistracijaClana.this, "Pogresan maticni broj, mora sadrzati samo 13 cifri!", "Greska", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    if (rodjendan.getDate().after(Calendar.getInstance().getTime())) {
                        JOptionPane.showMessageDialog(RegistracijaClana.this, "Datum rodjenja je u buducnosti !", "Greska", JOptionPane.ERROR_MESSAGE);
                        return;

                    }
                    if (musko.isSelected()) {
                        sex = Pol.muski;
                    } else {
                        sex = Pol.zenski;
                    }
                    try{
                    TipClanarine tipc = TipClanarine.values()[tipClana.getSelectedIndex()];
                    Clan novi = new Clan(ime.getText(), prezime.getText(), rodjendan.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), jmbg.getText(), sex);
                    KorisnickiNalog nalog = new KorisnickiNalog(korisnickoIme.getText(), String.valueOf(lozinka.getPassword()), VrstaNaloga.clan, novi);
                    novi.setNalog(nalog);
                        fabrika.getMenadzerKorisnickihNaloga().dodajNalog(nalog);
                    if (clanarina1.isSelected()) {
                        Clanarina c = new Clanarina(tipc, LocalDate.now(), LocalDate.now().plusMonths(6), novi);
                        novi.setClanarina(c);
                        fabrika.getMenadzerClanarina().dodajClanarinu(c);

                    } else if (clanarina2.isSelected()) {
                        Clanarina c = new Clanarina(tipc, LocalDate.now(), LocalDate.now().plusMonths(12), novi);
                        novi.setClanarina(c);
                        fabrika.getMenadzerClanarina().dodajClanarinu(c);
                    }
                        fabrika.getMenadzerClanova().dodajClana(novi);
                        fabrika.getMenadzerClanova().azurirajFajl();
                        RegistracijaClana.this.dispose();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }
            }
        });
        Registruj.setBounds(112, 320, 140, 30);
        contentPane.add(Registruj);

        RegistracijaClana.this.getRootPane().setDefaultButton(Registruj);

        JLabel logoutLabel = new JLabel("");
        logoutLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int result =JOptionPane.showConfirmDialog(contentPane ,"Jeste li sigurni da zelite da izadjete? ", "Izlazak", JOptionPane.YES_NO_OPTION);
                if(result == JOptionPane.YES_OPTION){
                    RegistracijaClana.this.dispose();
                }
            }
        });
        logoutLabel.setIcon(new ImageIcon(BibliotekarPozajmiceProzor.class.getResource("/slike/odjaviSe.png")));
        logoutLabel.setBounds(535, 395, 100, 60);
        contentPane.add(logoutLabel);

        JLabel Background = new JLabel("");
        Background.setIcon(new ImageIcon(RegistracijaClana.class.getResource("/slike/registracijaPozadina.jpg")));
        Background.setBounds(0, 0, 659, 494);
        contentPane.add(Background);

        clanarina1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                clanarina1.setSelected(true);
                clanarina2.setSelected(false);
            }
        });

        clanarina2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                clanarina2.setSelected(true);
                clanarina1.setSelected(false);
            }
        });

        musko.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                musko.setSelected(true);
                zensko.setSelected(false);
            }
        });
    }
}
