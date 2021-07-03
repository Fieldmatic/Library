package auxiliary;

import repository.MenadzerKorisnickihNaloga;
import userEntities.KorisnickiNalog;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Provere {
    private MenadzerKorisnickihNaloga menadzerNaloga;

    public Provere(MenadzerKorisnickihNaloga mk){
        menadzerNaloga = mk;
    }

    public boolean validacijaSlova(String tekst) {
        char[] slova = tekst.toCharArray();

        for (char c : slova) {
            if(!Character.isLetter(c)) {
                return false;
            }
        }
        return true;
    }

    public boolean proveraIspravnostiKorisnickoImeLozinka(String tekst) {
        Pattern pattern = Pattern.compile("^[a-z0-9_-]{6,14}$");
        Matcher mtch = pattern.matcher(tekst);
        return mtch.matches();
    }

    public boolean validacijaJmbg(String tekst) {
        Pattern pattern = Pattern.compile("[0-9]{13}");
        Matcher mtch = pattern.matcher(tekst);
        return mtch.matches();
    }

    public boolean proveraDostupnostiKorisnickogImena(String tekst){
        for (KorisnickiNalog n : menadzerNaloga.getNalozi()){
            if (n.getKorisnickoIme().equals(tekst)) return false;
        }
        return true;
    }
}
