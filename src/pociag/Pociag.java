package pociag;

import mapa.MapaTransportu;
import mapa.StacjaKolejowa;
import wagony.Wagon;

import java.awt.*;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Pociag {

    //todo trycatch

    private final Lokomotywa lokomotywa;
    private final List<Wagon> wagony;
    private double predkosc;
    protected LinkedList<StacjaKolejowa> zaplanowanaTrasaJazdy;
    private int aktualnaPosredniaTrasaPodrozy = 0;

    private StacjaKolejowa stacjaMacierzysta;
    private StacjaKolejowa stacjaZrodlowa;
    private StacjaKolejowa stacjaDocelowa;
    private double przebytaDroga;
    private long czasRozpoczeciaPostoju = 0;

    public Pociag(Lokomotywa lokomotywa, List<Wagon> wagony, MapaTransportu mapaTransportu) {
        super();
        this.lokomotywa = lokomotywa;
        this.wagony = wagony;
        this.predkosc = 100;

        stacjaMacierzysta = mapaTransportu.getLosowaStacja();
        // todo stacja docelowa nie może być macierzystą
        // todo isReachable - nowo powstala stacja bez polaczen nie moze byc docelowa dla zadnego pociagu
        stacjaDocelowa = mapaTransportu.getLosowaStacja();
    }

    public static Pociag generujLosowyPociag(MapaTransportu mapaTransportu) {
        int iloscWagonow = 5;
        return new Pociag(new Lokomotywa(), Wagon.stworzZestawWagonow(iloscWagonow), mapaTransportu);
    }

    public StacjaKolejowa getStacjaMacierzysta() {
        return stacjaMacierzysta;
    }

    public StacjaKolejowa getStacjaDocelowa() {
        return stacjaDocelowa;
    }

    public double nadajPredkosc() {
        // jesli wazy wiecej niz costam to losuj z mniejszych
        return Math.random() < 0.5 ? this.predkosc * 1.03 : this.predkosc * 0.97;
    }

    public Lokomotywa liczWage() {
        return null;
    }

    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setPaint(new Color(54, 182, 16));
        if (this.stacjaZrodlowa == null) {
            int dlugoscBoku = 10;
            StacjaKolejowa stacjaKolejowa = this.zaplanowanaTrasaJazdy.get(this.aktualnaPosredniaTrasaPodrozy);
            g2d.fillRect((int) stacjaKolejowa.getX() - dlugoscBoku / 2, (int) stacjaKolejowa.getY() - dlugoscBoku / 2, dlugoscBoku, dlugoscBoku);

            return;
        }
        skaluj(g2d);
    }

    public void skaluj(Graphics2D g2d) {
        StacjaKolejowa stacjaZrodlowa = this.stacjaZrodlowa;
        StacjaKolejowa najblizszaDocelowa = this.getNajblizszaDocelowa();
        double pelnaDlugosc = MapaTransportu.obliczDlugoscTrasy(stacjaZrodlowa, najblizszaDocelowa);
        double procentTrasy = this.przebytaDroga / pelnaDlugosc;


        double x = stacjaZrodlowa.getX();
        double x1 = najblizszaDocelowa.getX();
        double absX = Math.abs(x - x1);
        double y = stacjaZrodlowa.getY();
        double y1 = najblizszaDocelowa.getY();
        double absY = Math.abs(y - y1);

        if (procentTrasy > 1) {
            procentTrasy = 1;
        }
        absX = absX * procentTrasy;
        absY = absY * procentTrasy;
        if (x > x1) absX = -absX;
        if (y > y1) absY = -absY;

        g2d.fillRect((int) (x + absX) - 5, (int) (y + absY) - 5, 10, 10);

    }


    public LinkedList<StacjaKolejowa> getZaplanowanaTrasaJazdy() {
        return this.zaplanowanaTrasaJazdy;
    }

    public void setZaplanowanaTrasaJazdy(LinkedList<StacjaKolejowa> trasaJazdy) {
        this.zaplanowanaTrasaJazdy = trasaJazdy;
    }

    public void jedz(long deltaT, long tick, int updatesPerSecond) {
        //czas postoju 30 sec
        if (this.czasRozpoczeciaPostoju + 2_000 > System.currentTimeMillis()) return;
        if (this.stacjaZrodlowa == null) {
            this.stacjaZrodlowa = this.zaplanowanaTrasaJazdy.get(this.aktualnaPosredniaTrasaPodrozy);
        }

        // pociag jedzie
        this.przebytaDroga += obliczPrzebytaDroga(deltaT, this.predkosc);
        long l = deltaT * ((tick % updatesPerSecond) + 4);
        if (l > 1000) {
            this.predkosc = nadajPredkosc();
        }
        double dlugoscTrasy = MapaTransportu.obliczDlugoscTrasy(stacjaZrodlowa, getNajblizszaDocelowa());
        if (this.przebytaDroga >= dlugoscTrasy) {
            //todo wyciągnąć z mapy transportu - dlugosc tras

            this.stacjaZrodlowa = null;
            this.aktualnaPosredniaTrasaPodrozy++;
            this.czasRozpoczeciaPostoju = System.currentTimeMillis();
            this.przebytaDroga = 0;

            if (this.aktualnaPosredniaTrasaPodrozy == zaplanowanaTrasaJazdy.size()
                    && (getNajblizszaDocelowa() == this.stacjaDocelowa || getNajblizszaDocelowa() == this.stacjaMacierzysta)) {
                Collections.reverse(this.zaplanowanaTrasaJazdy);
                this.aktualnaPosredniaTrasaPodrozy = 0;
            }
        }
    }


    private double obliczPrzebytaDroga(long deltaTmilis, double predkoscKmH) {
        return convertMilisToSeconds(deltaTmilis) * convertToMetersPerSecond(predkoscKmH);
    }

    private double convertToMetersPerSecond(double predkoscKmH) {
        // 36 km/h to 10 m/s
        double ms = 3.6;
        return predkoscKmH / ms;
    }

    private double convertMilisToSeconds(long deltaTmilis) {
        // 1000ms to 1 sec
        return deltaTmilis / 1000.0;
    }

    private StacjaKolejowa getNajblizszaDocelowa() {
        if (this.aktualnaPosredniaTrasaPodrozy < this.zaplanowanaTrasaJazdy.size() - 1)
            return zaplanowanaTrasaJazdy.get(this.aktualnaPosredniaTrasaPodrozy + 1);
        return this.zaplanowanaTrasaJazdy.getLast();
    }
}