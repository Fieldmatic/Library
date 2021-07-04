package gui.bibliotekar.pozajmice.clanovi.pregledKasnjenja;

import gui.bibliotekar.pozajmice.clanovi.pregledClanova.PregledClanovaDialog;
import repository.Fabrika;
import userEntities.Clan;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;

public class PregledKasnjenjaDialog extends PregledClanovaDialog {
    public PregledKasnjenjaDialog(Fabrika repo, List<Clan> data) {
        super(repo, data);
        initGui();
        initActions();
    }

    private void initGui() {
        JLabel lblHint = new JLabel("Za pregled kasnjenja izaberite clana.");
        lblHint.setIcon(new ImageIcon(PregledKasnjenjaDialog.class.getResource("/slike/notification.png")));
        super.pretragaPanel.add(lblHint);
        super.setMinimumSize(new Dimension(650, 300));
    }

    private void initActions() {
        tabela.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    int row = tabela.getSelectedRow();
                    if (row == -1)
                        JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greška", JOptionPane.WARNING_MESSAGE);
                    else {
                        Clan c = repo.getMenadzerClanova().pronadjiClanaPoKorImenu((String) tabela.getValueAt(row, 0));
                        new DetaljnoKasnjenjeDialog(c);
                    }
                }
            }
        });
    }
}
