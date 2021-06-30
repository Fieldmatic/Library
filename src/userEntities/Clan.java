package userEntities;

import entities.Clanarina;

public class Clan extends Korisnik {
    private Clanarina clanarina;

    public Clanarina getClanarina() {
        return clanarina;
    }

    public void setClanarina(Clanarina clanarina) {
        this.clanarina = clanarina;
    }

    @Override
    public String toString() {
        return super.toString() + "Clan{" +
                "clanarina=" + clanarina +
                '}';
    }
}
