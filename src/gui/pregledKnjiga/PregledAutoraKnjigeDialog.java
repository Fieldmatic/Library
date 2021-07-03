package gui.pregledKnjiga;

import entities.Autorstvo;
import entities.Knjiga;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class PregledAutoraKnjigeDialog extends JDialog {
    protected List<Autorstvo> data;
    protected JTable tabela;

    public PregledAutoraKnjigeDialog(Knjiga c) {
        this.data = c.getAutori();
        this.tabela = new JTable(new PregledAutoraKnjigeModel(this.data));
        initDialog();
    }

    private void initDialog() {
        this.setTitle("Pregled autora knjige");
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        initGUI();
        this.pack();
        this.setVisible(true);
    }

    private void initGUI() {
        MigLayout layout = new MigLayout();
        setLayout(layout);

        tabela.setFont(new Font("Yu Gothic", Font.BOLD, 12));
        tabela.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabela.getTableHeader().setReorderingAllowed(false);
        JScrollPane sc = new JScrollPane(tabela);
        add(sc, "pushx, growx");
    }
}