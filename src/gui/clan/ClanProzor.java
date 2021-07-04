package gui.clan;

import gui.Prijavljivanje;
import gui.pretragaKnjiga.PretragaKnjigaProzor;
import repository.Fabrika;
import userEntities.Clan;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ClanProzor extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private Clan clan;


    public static void main(String[] args, Fabrika fabrika, Clan c) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ClanProzor frame = new ClanProzor(fabrika, c);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public ClanProzor(Fabrika fabrika, Clan c) {
        this.clan = c;
        setResizable(false);
        setTitle("Clan");
        setIconImage(Toolkit.getDefaultToolkit().getImage(ClanProzor.class.getResource("/slike/logo.jpg")));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 920, 590);

        JMenuBar menuBar = new JMenuBar();
        menuBar.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        setJMenuBar(menuBar);

        JLabel ikonicaKnjige = new JLabel("");
        ikonicaKnjige.setIcon(new ImageIcon(ClanProzor.class.getResource("/slike/knjiga.png")));
        menuBar.add(ikonicaKnjige);

        JMenu knjigeMeni = new JMenu("Knjige");
        knjigeMeni.setFont(new Font("Yu Gothic", Font.PLAIN, 16));
        menuBar.add(knjigeMeni);


        JMenuItem pregledKnjiga = new JMenuItem("Pregled knjiga");
        pregledKnjiga.setIcon(new ImageIcon(ClanProzor.class.getResource("/slike/pregled.png")));
        pregledKnjiga.setFont(new Font("Yu Gothic", Font.BOLD, 12));
        pregledKnjiga.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //prozorzapregled

            }
        });


        JMenuItem pretragaKnjiga = new JMenuItem("Pretraga knjiga");
        pretragaKnjiga.setIcon(new ImageIcon(ClanProzor.class.getResource("/slike/pregled.png")));
        pretragaKnjiga.setFont(new Font("Yu Gothic", Font.BOLD, 12));
        pretragaKnjiga.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {PretragaKnjigaProzor.main(fabrika, clan);}

        });
        knjigeMeni.add(pretragaKnjiga);
        knjigeMeni.add(pregledKnjiga);

        JLabel ikonicaIznajmljivanje = new JLabel("");
        ikonicaIznajmljivanje.setIcon(new ImageIcon(ClanProzor.class.getResource("/slike/iznajmljivanje.png")));
        menuBar.add(ikonicaIznajmljivanje);

        JMenu iznajmljivanja = new JMenu("Iznajmljivanja");
        iznajmljivanja.setFont(new Font("Yu Gothic", Font.PLAIN, 16));
        menuBar.add(iznajmljivanja);

        JMenuItem istorijaZaduzenja = new JMenuItem("Pregled istorije zaduzenja");
        istorijaZaduzenja.setIcon(new ImageIcon(ClanProzor.class.getResource("/slike/pregled.png")));
        istorijaZaduzenja.setFont(new Font("Yu Gothic", Font.BOLD, 12));
        istorijaZaduzenja.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {PregledIstorijeIznajmljivanja.main(fabrika, c);}
        });


        JMenuItem trenutnaZaduzenja = new JMenuItem("Pregled trenutnih zaduzenja");
        trenutnaZaduzenja.setIcon(new ImageIcon(ClanProzor.class.getResource("/slike/pregled.png")));
        trenutnaZaduzenja.setFont(new Font("Yu Gothic", Font.BOLD, 12));
        trenutnaZaduzenja.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //prozorZaPregledTrenutnihZaduzenja
            }
        });

        iznajmljivanja.add(istorijaZaduzenja);
        iznajmljivanja.add(trenutnaZaduzenja);

        JLabel IkonicaRezervacije = new JLabel("");
        IkonicaRezervacije.setIcon(new ImageIcon(ClanProzor.class.getResource("/slike/rezervacije.png")));
        menuBar.add(IkonicaRezervacije);

        JMenu rezervacije = new JMenu("Rezervacije");
        rezervacije.setFont(new Font("Yu Gothic", Font.PLAIN, 16));
        menuBar.add(rezervacije);

        JMenuItem odobravanjeRezervacije = new JMenuItem("Rezervisi knjigu");
        odobravanjeRezervacije.setIcon(new ImageIcon(ClanProzor.class.getResource("/slike/rezervisiKnjigu.png")));
        odobravanjeRezervacije.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //prozor za rezervisanje knjige
            }
        });
        odobravanjeRezervacije.setFont(new Font("Yu Gothic", Font.BOLD, 12));
        rezervacije.add(odobravanjeRezervacije);

        JLabel ikonicaClanarina = new JLabel("");
        ikonicaClanarina.setIcon(new ImageIcon(ClanProzor.class.getResource("/slike/clanarina.png")));
        menuBar.add(ikonicaClanarina);

        JMenu clanarina = new JMenu("Clanarina");
        clanarina.setFont(new Font("Yu Gothic", Font.PLAIN, 16));
        menuBar.add(clanarina);

        JMenuItem produzenjeClanarine = new JMenuItem("Produzi clanarinu");
        produzenjeClanarine.setFont(new Font("Yu Gothic", Font.BOLD, 12));
        produzenjeClanarine.setIcon(new ImageIcon(ClanProzor.class.getResource("/slike/plus.png")));
        produzenjeClanarine.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //prozor za produzenje clanarine
            }
        });
        clanarina.add(produzenjeClanarine);

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
                    ClanProzor.this.dispose();
                    Prijavljivanje prijava = new Prijavljivanje(fabrika);
                    prijava.setVisible(true);
                }
            }
        });
        logoutLabel.setIcon(new ImageIcon(ClanProzor.class.getResource("/slike/odjaviSe.png")));
        logoutLabel.setBounds(800, 425, 139, 98);
        contentPane.add(logoutLabel);

        JLabel Background = new JLabel("");
        Background.setIcon(new ImageIcon(ClanProzor.class.getResource("/slike/bibliotekarPozadina.png")));
        Background.setBounds(0, 0, 907, 574);
        contentPane.add(Background);
    }
}
