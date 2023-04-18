package pociag;

import mapa.MapaTransportu;
import mapa.StacjaKolejowa;
import wagony.Wagon;

import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Pociag {

    private static Map<Lokomotywa, ArrayList<Wagon>> kolekcjaPociagow;
    private final Lokomotywa lokomotywa;
    private final List<Wagon> wagony;
    private double predkosc;
    protected LinkedList<StacjaKolejowa> mapaTrasyMacierzystaDocelowa;
    private boolean isSpawned = false;
    private StacjaKolejowa stacjaMacierzysta;
    private StacjaKolejowa stacjaZrodlowa;
    private StacjaKolejowa stacjaDocelowa;

    public Pociag(Lokomotywa lokomotywa, List<Wagon> wagony, MapaTransportu mapaTransportu) {
        super();
        this.lokomotywa = lokomotywa;
        this.wagony = wagony;
        this.predkosc = nadajPredkosc();

        stacjaMacierzysta = mapaTransportu.getLosowaStacja();
        // todo stacja docelowa nie może być macierzystą
        stacjaDocelowa = mapaTransportu.getLosowaStacja();
    }

    public static Pociag generujLosowyPociag(MapaTransportu mapaTransportu) {
        int iloscWagonow = 5;
        Pociag pociag = new Pociag(new Lokomotywa(), Wagon.stworzZestawWagonow(iloscWagonow), mapaTransportu);
        return pociag;
    }

    public int nadajPredkosc() {
        // jesli wazy wiecej niz costam to losuj z mniejszych
        return 100;
    }

    public Lokomotywa liczWage(ArrayList<Wagon> wagony) {
        return null;
    }

    public void draw(Graphics g) {
        if (!this.isSpawned) {
            StacjaKolejowa stacjaMacierzysta = this.stacjaMacierzysta;
            int dlugoscBoku = 10;
            g.drawRect((int) stacjaMacierzysta.getX() - dlugoscBoku / 2, (int) stacjaMacierzysta.getY() - dlugoscBoku / 2, dlugoscBoku, dlugoscBoku);
        }

    }
}
