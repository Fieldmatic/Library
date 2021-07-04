package main;

import gui.Prijavljivanje;
import repository.Fabrika;

import java.io.IOException;


public class Main {
    public static void main(String[] args) throws IOException {
        Fabrika fabrikaRepozitorijuma = new Fabrika();
        Prijavljivanje.main(args, fabrikaRepozitorijuma);
    }
}

