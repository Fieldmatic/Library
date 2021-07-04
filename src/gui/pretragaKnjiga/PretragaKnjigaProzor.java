package gui.pretragaKnjiga;

import com.toedter.calendar.JDateChooser;
import entities.Knjiga;
import enumerations.UlogaAutora;
import enumerations.VrstaNaloga;
import enumerations.Zanr;
import gui.pregledKnjiga.PregledKnjigaDialog;
import net.miginfocom.swing.MigLayout;
import repository.Fabrika;
import userEntities.Korisnik;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PretragaKnjigaProzor extends JFrame {

    private Fabrika repo;
    private Korisnik korisnik;
    private JPanel contentPane;
    private JTextField tfNazivKnjige;
    private JComboBox<Object> cbNazivSadrzaja;
    private JComboBox<Object> cbZanr;
    private JTextField tfImeAutora;
    private JTextField tfPrezimeAutora;
    private JComboBox<Object> cbUlogaAutora;
    private JTextField tfTagovi;
    private JTextField tfOcena; // zmijenicu sa StarRating
    private JDateChooser dcDatIzdavanja;
    private JTextField tfIzdavac;
    private JButton btnPretrazi;
    private List<Knjiga> rezultatPretrage;

    public PretragaKnjigaProzor(Fabrika repo, Korisnik k) {
        this.repo = repo;
        this.korisnik = k;
        rezultatPretrage = new ArrayList<>();
        pretragaKnjigaProzor();
    }

    public static void main(Fabrika fabrika, Korisnik k) {
        EventQueue.invokeLater(() -> {
            try {
                PretragaKnjigaProzor frame = new PretragaKnjigaProzor(fabrika, k);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private void pretragaKnjigaProzor() {
        setTitle("Pretraga knjiga");
        setIconImage(Toolkit.getDefaultToolkit().getImage(PretragaKnjigaProzor.class.getResource("/slike/logo.jpg")));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getRootPane().setDefaultButton(btnPretrazi);
        setLocationRelativeTo(null);
        setResizable(false);
        initGUI();
        pack();
        setVisible(true);
    }

    private void initGUI() {
        contentPane = new PozadinskiPanel();
        contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));

        setContentPane(contentPane);
        MigLayout layout = new MigLayout("wrap 2", "[sg1][sg1]", "");
        contentPane.setLayout(layout);

        addLabel("Naziv:", contentPane);
        tfNazivKnjige = new JTextField();
        addTextField(tfNazivKnjige, contentPane);

        cbNazivSadrzaja = new JComboBox<>();
        cbZanr = new JComboBox<>();
        addSadrzajPanel();
        cbUlogaAutora = new JComboBox<>();
        addAutorPanel();

        addLabel("Tagovi:", contentPane);
        tfTagovi = new JTextField();
        addTextField(tfTagovi, contentPane);

        addLabel("Ocena:", contentPane);
        tfOcena = new JTextField();
        addTextField(tfOcena, contentPane);

        addLabel("Datum izdavanja:", contentPane);
        dcDatIzdavanja = new JDateChooser();
        addDateChooser(dcDatIzdavanja);

        addLabel("Izdavac", contentPane);
        tfIzdavac = new JTextField();
        addTextField(tfIzdavac, contentPane);

        addButtonPretrazi();
        initActions();
    }


    private void addDateChooser(JDateChooser dc) {
        dc.setBackground(Color.BLACK);
        dc.setForeground(Color.WHITE);
        getContentPane().add(dc);
    }

    private void addSadrzajPanel() {
        JPanel sadrzajPanel = new JPanel();
        sadrzajPanel.setOpaque(false);
        MigLayout layout = new MigLayout("wrap 2", "[sg1][sg1]", "");
        sadrzajPanel.setLayout(layout);
        sadrzajPanel.setBorder(BorderFactory.createTitledBorder(null, "Sadrzaj", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Yu Gothic", Font.BOLD, 12)));

        addLabel("Naziv:", sadrzajPanel);
        addComboBox(Stream.of("Beletristrika", "Publicistika").collect(Collectors.toList()), cbNazivSadrzaja, sadrzajPanel);
        addLabel("Zanr:", sadrzajPanel);
        addComboBox(Arrays.asList(Zanr.values()), cbZanr, sadrzajPanel);

        getContentPane().add(sadrzajPanel, "wrap, span");
    }

    private void addAutorPanel() {
        JPanel autorPanel = new JPanel();
        autorPanel.setOpaque(false);
        MigLayout layout = new MigLayout("wrap 2", "[sg1][sg1]", "");
        autorPanel.setLayout(layout);
        autorPanel.setBorder(BorderFactory.createTitledBorder(null, "Autor", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Yu Gothic", Font.BOLD, 12)));

        addLabel("Ime:", autorPanel);
        tfImeAutora = new JTextField();
        addTextField(tfImeAutora, autorPanel);
        addLabel("Prezime:", autorPanel);
        tfPrezimeAutora = new JTextField();
        addTextField(tfPrezimeAutora, autorPanel);
        addLabel("Uloga:", autorPanel);
        addComboBox(Arrays.asList(UlogaAutora.values()), cbUlogaAutora, autorPanel);

        getContentPane().add(autorPanel, "wrap, span");

    }

    private void addComboBox(List<Object> data, JComboBox<Object> cb, JPanel panel) {
        cb.setBackground(Color.LIGHT_GRAY);
        cb.setForeground(Color.WHITE);
        cb.setFont(new Font("Yu Gothic", Font.BOLD, 12));
        for (Object o : data)
            cb.addItem(o);
        cb.setPrototypeDisplayValue("XXXXXXXXXXX");
        cb.setSelectedItem(null);
        panel.add(cb);
    }


    private void addTextField(JTextField tf, JPanel panel) {
        initTextField(tf);
        panel.add(tf);
    }

    private void initTextField(JTextField tf) {
        tf.setForeground(Color.WHITE);
        tf.setFont(new Font("Yu Gothic", Font.BOLD, 12));
        tf.setOpaque(false);
        tf.setColumns(10);
    }

    private void addLabel(String text, JPanel panel) {
        JLabel lbl = new JLabel(text);
        lbl.setFont(new Font("Yu Gothic UI", Font.BOLD, 14));
        panel.add(lbl);
    }

    private void addButtonPretrazi() {
        btnPretrazi = new JButton("Pretrazi");
        btnPretrazi.setBackground(Color.BLUE);
        btnPretrazi.setForeground(UIManager.getColor("Button.background"));
        btnPretrazi.setFont(new Font("Yu Gothic UI", Font.BOLD, 14));
        getContentPane().add(btnPretrazi, "center, span 2");
    }

    private void initActions() {
        btnPretrazi.addActionListener(e -> {
            uradiPretragu();
            if (!rezultatPretrage.isEmpty())
                if (korisnik.getNalog().getVrstaNaloga().equals(VrstaNaloga.bibliotekarZaPozajmice))
                    new PregledKnjigaPozajmljivanje(repo, rezultatPretrage);
                else
                    new PregledKnjigaDialog(repo, rezultatPretrage);
            else
                 JOptionPane.showMessageDialog(this, "Nije pronadjena ni jedna takva knjiga", "Nema takve knjige", JOptionPane.INFORMATION_MESSAGE);
        });
    }

    private void uradiPretragu() {
        if (!tfNazivKnjige.getText().equals(""))
            napraviPresjek(nadjiKnjigePoNazivu(tfNazivKnjige));
        if (cbNazivSadrzaja.getSelectedItem() != null)
            napraviPresjek(nadjiKnjigePoNazivuSadrzaja(cbNazivSadrzaja));
         if (cbZanr.getSelectedItem() != null)
            napraviPresjek(nadjiKnjigePoZanru(cbZanr));
         if (!tfImeAutora.getText().equals(""))
            napraviPresjek(nadjiKnjigePoImenuAutora(tfImeAutora));
         if (!tfPrezimeAutora.getText().equals(""))
            napraviPresjek(nadjiKnjigePoPrezimenuAutora(tfPrezimeAutora));
         if (!tfTagovi.getText().equals(""))
            napraviPresjek(nadjiKnjigePoTagovima(tfTagovi));
         if (!tfOcena.getText().equals(""))
            napraviPresjek(nadjiKnjigePoOceni(tfOcena));
         if (dcDatIzdavanja.getDate() != null)
            napraviPresjek(nadjiKnjigePoDatIzdavanja(dcDatIzdavanja));
         if (!tfIzdavac.getText().equals(""))
            napraviPresjek(nadjiKnjigePoIzdavacu(tfIzdavac));

        System.out.println(rezultatPretrage);

    }

    private void napraviPresjek(List<Knjiga> knjige) {
        if (rezultatPretrage.isEmpty())
                rezultatPretrage.addAll(knjige);
        else {
            rezultatPretrage = new ArrayList<>(presjeci(knjige));
        }

    }

    private Set<Knjiga> presjeci(List<Knjiga> knjige) {
       return rezultatPretrage.stream()
          .distinct()
          .filter(knjige::contains)
          .collect(Collectors.toSet());
    }

    private List<Knjiga> nadjiKnjigePoIzdavacu(JTextField tfIzdavac) {
        return repo.getMenadzerKnjiga().nadjiKnjigePoIzdavacu(tfIzdavac.getText());
    }

    private List<Knjiga> nadjiKnjigePoDatIzdavanja(JDateChooser dcDatIzdavanja) {
        return repo.getMenadzerKnjiga().nadjiKnjigePoDatIzdavanja(dcDatIzdavanja.getDate());
    }

    private List<Knjiga> nadjiKnjigePoOceni(JTextField tfOcena) {
        return repo.getMenadzerKnjiga().nadjiKnjigePoOceni(Integer.parseInt(tfOcena.getText()));
    }

    private List<Knjiga> nadjiKnjigePoTagovima(JTextField tfTagovi) {
        List<Knjiga> ret = new ArrayList<>();
        for (String s: tfTagovi.getText().split(","))
            ret.addAll(repo.getMenadzerKnjiga().nadjiKnjigePoTagovima(s.trim()));
        return ret;
    }

    private List<Knjiga> nadjiKnjigePoPrezimenuAutora(JTextField tfPrezimeAutora) {
        return repo.getMenadzerKnjiga().nadjiKnjigePoPrezimenuAutora(tfPrezimeAutora.getText());
    }

    private List<Knjiga> nadjiKnjigePoImenuAutora(JTextField tfImeAutora) {
        return repo.getMenadzerKnjiga().nadjiKnjigePoImenuAutora(tfImeAutora.getText());
    }

    private List<Knjiga> nadjiKnjigePoZanru(JComboBox<Object> cbZanr) {
       return repo.getMenadzerKnjiga().nadjiKnjigePoZanru((Zanr) cbZanr.getSelectedItem());
    }

    private List<Knjiga> nadjiKnjigePoNazivuSadrzaja(JComboBox<Object> cbNazivSadrzaja) {
        return repo.getMenadzerKnjiga().nadjiKnjigePoNazivuSadrzaja((String) cbNazivSadrzaja.getSelectedItem());
    }


    private List<Knjiga> nadjiKnjigePoNazivu (JTextField tfNazivKnjige) {
        return repo.getMenadzerKnjiga().nadjiKnjigePoNazivu(tfNazivKnjige.getText());
    }
}
