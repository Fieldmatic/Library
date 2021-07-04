package gui.clan;

import entities.Knjiga;
import entities.Recenzija;
import gui.bibliotekar.pozajmice.clanovi.RegistracijaClana;
import net.miginfocom.swing.MigLayout;
import repository.Fabrika;
import userEntities.Clan;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class OstavljanjeRecenzijeDijalog extends JFrame {
    protected Fabrika fabrika;
    protected Clan c;
    protected Knjiga k;
    private JPanel contentPane;
    private JTextField ocena;
    private JTextField komentar;

    public OstavljanjeRecenzijeDijalog(Fabrika fabrika, Clan c, Knjiga k) {
        this.fabrika = fabrika;
        this.c=c;
        this.k = k;
        initDialog();
    }

    private void initDialog() {
        this.setTitle("Ostavljanje recenzije");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        initGUI();
        this.setVisible(true);
    }

    private void initGUI() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(RegistracijaClana.class.getResource("/slike/logo.jpg")));
        setBounds(200,200,400,300);
        contentPane = new JPanel();
        contentPane.setBackground(Color.LIGHT_GRAY);
        contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        ocena = new JTextField();
        ocena.setForeground(Color.WHITE);
        ocena.setFont(new Font("Yu Gothic", Font.BOLD, 12));
        ocena.setOpaque(false);
        ocena.setBounds(112, 60, 200, 20);
        contentPane.add(ocena);
        ocena.setColumns(10);

        komentar = new JTextField();
        komentar.setForeground(Color.WHITE);
        komentar.setFont(new Font("Yu Gothic", Font.BOLD, 12));
        komentar.setOpaque(false);
        komentar.setBounds(112, 112, 200, 50);
        contentPane.add(komentar);
        komentar.setColumns(10);

        JLabel ocenal = new JLabel("Ocena (1-5) :");
        ocenal.setForeground(Color.WHITE);
        ocenal.setFont(new Font("Yu Gothic", Font.BOLD, 14));
        ocenal.setBounds(10, 62, 130, 14);
        contentPane.add(ocenal);

        JLabel komentarL = new JLabel("Komentar : ");
        komentarL.setForeground(Color.WHITE);
        komentarL.setFont(new Font("Yu Gothic", Font.BOLD, 14));
        komentarL.setBounds(10, 112, 90, 14);
        contentPane.add(komentarL);

        JButton dodaj = new JButton("Dodaj");
        dodaj.setBackground(Color.BLUE);
        dodaj.setForeground(UIManager.getColor("Button.background"));
        dodaj.setFont(new Font("Yu Gothic UI", Font.BOLD, 16));
        dodaj.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int izbor = JOptionPane.showConfirmDialog(OstavljanjeRecenzijeDijalog.this, "Jeste li sigurni? ", "Potvrda", JOptionPane.YES_NO_OPTION);
                if (izbor == JOptionPane.YES_OPTION) {
                String koment = komentar.getText();
                int vrednostocene = Integer.parseInt(ocena.getText());
                Recenzija r = new Recenzija(vrednostocene, koment, c, k);
                fabrika.getMenadzerKnjiga().pronadjiKnjiguPoId(k.getId()).dodajRecenziju(r);
                    try {
                        fabrika.getMenadzerKnjiga().azurirajFajl();
                        JOptionPane.showMessageDialog(OstavljanjeRecenzijeDijalog.this, "Uspesno ste ocenili knjigu","Obavestenje", JOptionPane.INFORMATION_MESSAGE);
                        OstavljanjeRecenzijeDijalog.this.dispose();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }

            }
        });
        dodaj.setBounds(160, 170, 100, 35);
        contentPane.add(dodaj);
    }
}
