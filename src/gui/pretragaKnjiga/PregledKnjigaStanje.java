package gui.pretragaKnjiga;

import entities.Knjiga;
import gui.pregledKnjiga.PregledKnjigaDialog;
import repository.Fabrika;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;

public class PregledKnjigaStanje extends PregledKnjigaDialog {
    public PregledKnjigaStanje(Fabrika repo, List<Knjiga> data) {
        super(repo, data);
        pregledKnjigaStanje();
    }

    private void pregledKnjigaStanje() {
        lblInfo.setText("Za informacije o stanju izaberite knjigu.");
        initActions();
    }

    @Override
    protected void initActions() {
        tabela.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    int row = tabela.getSelectedRow();
                    if (row == -1)
                        JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greška", JOptionPane.WARNING_MESSAGE);
                    else {
                        Knjiga k = repo.getMenadzerKnjiga().pronadjiKnjiguPoId((int) tabela.getValueAt(row, 0));
                        new PregledPrimerakaKnjigeDialog(repo, k);
                    }
                }
            }
        });
    }
}
