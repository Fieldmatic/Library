package gui.pretragaKnjiga;

import com.toedter.calendar.JDateChooser;
import enumerations.UlogaAutora;
import enumerations.Zanr;
import net.miginfocom.swing.MigLayout;
import repository.Fabrika;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PretragaKnjigaProzor extends JFrame {

    private Fabrika repo;
    private JPanel contentPane;
    private JTextField tfNazivKnjige;
    private JComboBox<Object> cbNazivSadrzaja;
    private JComboBox<Object> cbZanr;
    private JTextField tfImeAutora;
    private JTextField tfPrezimeAutora;
    private JTextField tfUlogaAutora;
    private JTextField tfTagovi;
    private JTextField tfOcena; // zmijenicu sa StarRating
    private JDateChooser dcDatIzdavanja;
    private JTextField tfIzdavac;

    public static void main(Fabrika fabrika) {
        EventQueue.invokeLater(() -> {
            try {
                PretragaKnjigaProzor frame = new PretragaKnjigaProzor(fabrika);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public PretragaKnjigaProzor(Fabrika repo) {
        this.repo = repo;
        pretragaKnjigaProzor();
    }

    private void pretragaKnjigaProzor() {
        setTitle("Pretraga knjiga");
        setIconImage(Toolkit.getDefaultToolkit().getImage(PretragaKnjigaProzor.class.getResource("/slike/logo.jpg")));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
        addTextField(tfNazivKnjige, contentPane);

        addSadrzajPanel();
        addAutorPanel();

        addLabel("Tagovi:", contentPane);
        addTextField(tfTagovi, contentPane);

        addLabel("Ocena:", contentPane);
        addTextField(tfOcena, contentPane);

        addLabel("Datum izdavanja:", contentPane);
        addDateChooser(dcDatIzdavanja);

        addLabel("Izdavac", contentPane);
        addTextField(tfIzdavac, contentPane);

        addButtonPretrazi();
    }

    private void addButtonPretrazi() {
        JButton btnPretrazi = new JButton("Pretrazi");
        btnPretrazi.setBackground(Color.BLUE);
        btnPretrazi.setForeground(UIManager.getColor("Button.background"));
        btnPretrazi.setFont(new Font("Yu Gothic UI", Font.BOLD, 14));
        getContentPane().add(btnPretrazi, "center, span 2");
    }

    private void addDateChooser(JDateChooser dc) {
        dc = new JDateChooser();
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
        addTextField(tfImeAutora, autorPanel);
        addLabel("Prezime:", autorPanel);
        addTextField(tfPrezimeAutora, autorPanel);
        addLabel("Uloga:", autorPanel);
        addComboBox(Arrays.asList(UlogaAutora.values()), cbZanr, autorPanel);

        getContentPane().add(autorPanel, "wrap, span");

    }

    private void addComboBox(List<Object> data, JComboBox<Object> cb, JPanel panel) {
        cb = new JComboBox<>();
        cb.setBackground(Color.LIGHT_GRAY);
        cb.setForeground(Color.WHITE);
        cb.setFont(new Font("Yu Gothic", Font.BOLD, 12));
        for (Object o : data)
            cb.addItem(o);
        cb.setPrototypeDisplayValue("XXXXXXXXXXX");
        panel.add(cb);
    }


    private void addTextField(JTextField tf, JPanel panel) {
        tf = initTextField();
        panel.add(tf);
    }

    private JTextField initTextField() {
        JTextField tf = new JTextField();
        tf.setForeground(Color.WHITE);
        tf.setFont(new Font("Yu Gothic", Font.BOLD, 12));
        tf.setOpaque(false);
        tf.setColumns(10);
        return tf;
    }

    private void addLabel(String text, JPanel panel) {
        JLabel lbl = new JLabel(text);
        lbl.setFont(new Font("Yu Gothic UI", Font.BOLD, 14));
        panel.add(lbl);
    }
}
