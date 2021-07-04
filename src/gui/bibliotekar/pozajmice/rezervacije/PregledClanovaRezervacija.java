package gui.bibliotekar.pozajmice.rezervacije;

import gui.bibliotekar.pozajmice.clanovi.pregledClanova.PregledClanovaDialog;
import repository.Fabrika;
import userEntities.Clan;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class PregledClanovaRezervacija extends PregledClanovaDialog {

    public PregledClanovaRezervacija(Fabrika repo) {
        super(repo, repo.getMenadzerClanova().dobaviClanoveSaRezervacijomZaPreuzimanje());
        initActions();
    }

    @Override
    protected void initGUI() {
        super.initGUI();
        lblInfo = new JLabel("Za pregled rezervacija izaberite clana.");
        lblInfo.setIcon(new ImageIcon(PregledClanovaRezervacija.class.getResource("/slike/notification.png")));
        pretragaPanel.add(lblInfo);
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
                        new PregledRezervacijaDialog(repo, c);
                        PregledClanovaRezervacija.this.dispose();
                    }
                }
            }
        });
    }
}
