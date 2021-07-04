package gui.pretragaKnjiga;

import entities.Knjiga;
import entities.Pozajmica;
import gui.pregledKnjiga.PregledKnjigaDialog;
import repository.Fabrika;
import userEntities.Clan;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
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
                        Knjiga k = repo.getMenadzerKnjiga().pronadjiKnjiguPoId((Integer) tabela.getValueAt(row, 0));
                        if (!repo.getMenadzerKnjiga().imaSlobodanPrimerak(k))
                            JOptionPane.showMessageDialog(null, "Ni jedan primerak knjige nije dostupan za pozajmljivanje.", "Greška", JOptionPane.WARNING_MESSAGE);
                        else {
                            try {
                                Clan c = repo.getMenadzerClanova().pronadjiClanaPoKorImenu(JOptionPane.showInputDialog("Korisnicko ime clana: "));
                                Pozajmica p = repo.getMenadzerPozajmica().kreirajPozajmicu(repo.getMenadzerKnjiga().nadjiSlobodanPrimerak((k)), c);
                                repo.getMenadzerPozajmica().dodajPozajmicu(p);
                                c.dodajPozajmicu(p);
                                repo.getMenadzerClanova().azurirajFajl();
                                JOptionPane.showMessageDialog(null, "Pozajmica uspesno dodata.", "Pozajmica", JOptionPane.INFORMATION_MESSAGE);

                            } catch (NullPointerException | IOException ex) {
                                JOptionPane.showMessageDialog(null, "Clan nije pronadjen.", "Greška", JOptionPane.WARNING_MESSAGE);
                            }
                        }
                    }
                }
            }
        });
    }


}
