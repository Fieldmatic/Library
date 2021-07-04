package gui.pretragaKnjiga;

import entities.Knjiga;
import entities.Pozajmica;
import entities.PrimerakKnjige;
import gui.pregledKnjiga.PregledKnjigaDialog;
import repository.Fabrika;
import userEntities.Clan;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class PregledKnjigaPozajmljivanje extends PregledKnjigaDialog {
    public PregledKnjigaPozajmljivanje(Fabrika repo, List<Knjiga> data) {
        super(repo, data);
        pregledKnjigaStanje();
    }

    private void pregledKnjigaStanje() {
        lblInfo.setText("Izaberite knjigu kako bi je pozajmili");
    }

    protected void initActions() {
        tabela.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
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
                                String korIme = JOptionPane.showInputDialog("Korisnicko ime clana: ");
                                Clan c = repo.getMenadzerClanova().pronadjiClanaPoKorImenu(korIme);
                                if (c.getClanarina().getDatumKraja().isBefore(LocalDate.now()))
                                    JOptionPane.showMessageDialog(null, "Clanu: " + korIme + " je istekla clanarina.", "Greška", JOptionPane.WARNING_MESSAGE);
                                else {
                                    PrimerakKnjige primerakKnjige = repo.getMenadzerKnjiga().nadjiSlobodanPrimerak((k));
                                    primerakKnjige.setPozajmljen(true);
                                    Pozajmica p = repo.getMenadzerPozajmica().kreirajPozajmicu(primerakKnjige, c);
                                    if (!c.uslovPozajmice())
                                        JOptionPane.showMessageDialog(null, "Clan: " + korIme + " je posudio maksimalan broj knjiga.", "Greška", JOptionPane.WARNING_MESSAGE);
                                    else {
                                        repo.getMenadzerPozajmica().dodajPozajmicu(p);
                                        c.dodajPozajmicu(p);
                                        repo.getMenadzerClanova().azurirajFajl();
                                        repo.getMenadzerKnjiga().azurirajFajl();
                                        JOptionPane.showMessageDialog(null, "Pozajmica uspesno dodata.", "Pozajmica", JOptionPane.INFORMATION_MESSAGE);
                                        PregledKnjigaPozajmljivanje.this.dispose();
                                    }
                                }

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
