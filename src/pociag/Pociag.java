package pociag;

import mapa.MapaTransportu;
import wagony.Wagon;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Pociag extends Ellipse2D.Double {

    private static Map<Lokomotywa, ArrayList<Wagon>> kolekcjaPociagow;
    private final Lokomotywa lokomotywa;
    private final List<Wagon> wagony;
    private double predkosc;

    public Pociag(Lokomotywa lokomotywa, List<Wagon> wagony) {
        this.lokomotywa = lokomotywa;
        this.wagony = wagony;
        this.predkosc = nadajPredkosc();
    }

    public static Pociag generujLosowyPociag(MapaTransportu mapaTransportu) {
        int iloscWagonow = 5;
        Pociag pociag = new Pociag(new Lokomotywa(mapaTransportu), Wagon.stworzZestawWagonow(iloscWagonow));
        return pociag;
    }

    public int nadajPredkosc() {
        // jesli wazy wiecej niz costam to losuj z mniejszych
        return 100;
    }

    public Lokomotywa liczWagę(ArrayList<Wagon> wagony) {
        return null;
    }

    public void draw(Graphics g) {

    }
}
