package gui.clan;

import entities.Knjiga;
import entities.PrimerakKnjige;
import entities.Rezervacija;
import enumerations.StatusRezervacije;
import gui.pregledKnjiga.PregledKnjigaDialog;
import repository.Fabrika;
import userEntities.Clan;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class PregledKnjigaRezervacija extends PregledKnjigaDialog {
    private Clan clan;
    private Fabrika fabrika;
    public PregledKnjigaRezervacija(Fabrika fabrika, List<Knjiga> data, Clan clan) {
        super(fabrika, data);
        this.clan = clan;
        this.fabrika = fabrika;
        pregledKnjigaStanje();
    }

    private void pregledKnjigaStanje() {
        lblInfo.setText("Izaberite knjigu kako bi je rezervisali");
        initActions();
    }

    protected void initActions() {
        tabela.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                try {
                    if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                        int row = tabela.getSelectedRow();
                        if (row == -1)
                            JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greška", JOptionPane.WARNING_MESSAGE);
                        else {
                            Knjiga k = repo.getMenadzerKnjiga().pronadjiKnjiguPoId((Integer) tabela.getValueAt(row, 0));
                            if (!repo.getMenadzerKnjiga().knjigaImaZauzetePrimerke(k)) {
                                JOptionPane.showMessageDialog(null, "Izabrana knjiga nema zauzete primerke, rezervacija nije potrebna.", "Greška", JOptionPane.WARNING_MESSAGE);
                                PregledKnjigaRezervacija.this.dispose();
                            }
                            else {
                                PrimerakKnjige primerakKnjige = repo.getMenadzerKnjiga().dobaviZauzetPrimerakKnjige(k);
                                Rezervacija rezervacija = new Rezervacija(fabrika.getMenadzerRezervacija().dobaviSlobodanIdRezervacije(), primerakKnjige, LocalDate.now(), StatusRezervacije.zahtevPoslat);
                                fabrika.getMenadzerRezervacija().dodajRezervaciju(rezervacija);
                                clan.dodajRezervaciju(rezervacija);
                                fabrika.getMenadzerClanova().azurirajFajl();
                                JOptionPane.showMessageDialog(null, "Rezervacija je uspešno dodata.", "Rezervacija", JOptionPane.INFORMATION_MESSAGE);
                            }
                        }
                    }
                } catch (NullPointerException | IOException ex) {
                    JOptionPane.showMessageDialog(null, "Član nije pronadjen.", "Greška", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
    }
}
