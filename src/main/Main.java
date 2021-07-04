package main;

import gui.pretragaKnjiga.PretragaKnjigaProzor;
import repository.Fabrika;

import java.io.IOException;


public class Main {
    public static void main(String[] args) throws IOException {
        Fabrika fabrikaRepozitorijuma = new Fabrika();
        PretragaKnjigaProzor.main(fabrikaRepozitorijuma);
//        BibliotekarPozajmiceProzor.main(args, fabrikaRepozitorijuma, new Bibliotekar());
//        Prijavljivanje.main(args, fabrikaRepozitorijuma);
    }
}
