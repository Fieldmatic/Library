package gui.pretragaKnjiga;

import com.toedter.calendar.JDateChooser;
import net.miginfocom.swing.MigLayout;
import repository.Fabrika;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
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

    public PretragaKnjigaProzor(Fabrika repo) {
        this.repo = repo;
        initGUI();
    }

    private void pretragaKnjigaProzor() {
        setTitle("Pretraga knjiga");
        setIconImage(Toolkit.getDefaultToolkit().getImage(PretragaKnjigaProzor.class.getResource("/slike/logo.jpg")));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        initGUI();
        pack();
        setVisible(true);
    }

    private void initGUI() {
        contentPane = new JPanel();
        contentPane.setBackground(Color.LIGHT_GRAY);
        contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
        setContentPane(contentPane);
        MigLayout layout = new MigLayout("wrap 2", "[sg1][sg1]", "");
        contentPane.setLayout(layout);

        addLabel("Naziv:", (JPanel) getContentPane());
        addTextField(tfNazivKnjige, (JPanel) getContentPane());

        addSadrzajPanel();
        addAutorPanel();

        addLabel("Tagovi:", (JPanel) getContentPane());
        addTextField(tfTagovi, (JPanel) getContentPane());

        addLabel("Ocena:", (JPanel) getContentPane());
        addTextField(tfOcena, (JPanel) getContentPane());

        addLabel("Datum izdavanaj:", (JPanel) getContentPane());
        addDateChooser(dcDatIzdavanja);

        addTextField(tfImeAutora, (JPanel) getContentPane());
        addTextField(tfPrezimeAutora, (JPanel) getContentPane());
        addTextField(tfIzdavac, (JPanel) getContentPane());

        addButtonPretrazi();
        JButton btnPretrazi = new JButton("Pretrazi");

    }

    private void addButtonPretrazi() {
        JButton btnPretrazi = new JButton("Pretrazi");
        btnPretrazi.setBackground(Color.BLUE);
        btnPretrazi.setForeground(UIManager.getColor("Button.background"));
        btnPretrazi.setFont(new Font("Yu Gothic UI", Font.BOLD, 16));
        getContentPane().add(btnPretrazi);
    }

    private void addDateChooser(JDateChooser dc) {
        dc = new JDateChooser();
        dc.setBackground(Color.BLACK);
        dc.setForeground(Color.WHITE);
        dc.setOpaque(false);
        getContentPane().add(dc);
    }

    private void addAutorPanel() {

        /*addLabel("Ime:", sadrzajPanel);
        addTextField(tfImeAutora, sadrzajPanel);
        addLabel("Prezime:", sadrzajPanel);
        addTextField(tfPrezimeAutora, sadrzajPanel);
        addLabel("Uloga:", sadrzajPanel);*/

    }

    private void addSadrzajPanel() {
        JPanel sadrzajPanel = new JPanel();
        MigLayout layout = new MigLayout("wrap 2", "[sg1][sg1]", "");
        sadrzajPanel.setLayout(layout);

        addLabel("Naziv:", sadrzajPanel);
        addComboBox(Stream.of("Beletristrika", "Publicistika").collect(Collectors.toList()), cbNazivSadrzaja, sadrzajPanel);
        addLabel("Zanr:", sadrzajPanel);
    }

    private void addComboBox(List<Object> data, JComboBox<Object> cb, JPanel panel) {
        cb = new JComboBox<>();
        cb.setForeground(Color.WHITE);
        cb.setFont(new Font("Yu Gothic", Font.BOLD, 14));
        cb.setOpaque(false);
        for (Object o : data)
            cb.addItem(o);
        panel.add(cb);
    }


    private void addTextField(JTextField tf, JPanel panel) {
        initTextField(tf);
        panel.add(tf);
    }

    private void initTextField(JTextField tf) {
        tf = new JTextField();
        tf.setForeground(Color.WHITE);
        tf.setFont(new Font("Yu Gothic", Font.BOLD, 14));
        tf.setOpaque(false);
        tf.setColumns(10);
    }

    private void addLabel(String text, JPanel panel) {
        JLabel lbl = new JLabel(text);
        panel.add(lbl);
    }
}
