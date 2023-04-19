package pociag;

import mapa.MapaTransportu;
import mapa.StacjaKolejowa;
import sim.RuchPociagow;
import wagony.Wagon;
import wagony.typWagonu.*;

import java.awt.*;
import java.util.List;
import java.util.*;

public class Pociag {

    //todo trycatch

    private final Lokomotywa lokomotywa;
    public static ArrayList<Pociag> pociagi = new ArrayList<>();
    private final List<Wagon> wagony;
    private static int nrIdentyfikacyjnyPociagu;
    protected LinkedList<StacjaKolejowa> zaplanowanaTrasaJazdy;
    private int aktualnaPosredniaTrasaPodrozy = 0;

    private StacjaKolejowa stacjaMacierzysta;
    private StacjaKolejowa stacjaZrodlowa;
    private StacjaKolejowa stacjaDocelowa;
    private double przebytaDroga;
    private long czasRozpoczeciaPostoju = 0;
    private String nazwaPociagu;
    private double predkosc = 100;
    private String status = "Pociąg jedzie bez zakłóceń.";
    private int counter = 1;


    public Pociag(Lokomotywa lokomotywa, List<Wagon> wagony, MapaTransportu mapaTransportu) {
        this.nrIdentyfikacyjnyPociagu = counter; // w pociagu
        counter++;
        this.nazwaPociagu = nadajNazwe();
        this.lokomotywa = lokomotywa;
        this.wagony = wagony;
        this.predkosc = 100;

        this.stacjaMacierzysta = mapaTransportu.getLosowaStacja();
        // todo isReachable - nowo powstala stacja bez polaczen nie moze byc docelowa dla zadnego pociagu
        do {
            this.stacjaDocelowa = mapaTransportu.getLosowaStacja();
        } while (this.stacjaMacierzysta == this.stacjaDocelowa);

        pociagi.add(this);
    }

    public static ArrayList<Pociag> getPociagi() {
        return pociagi;
    }

    public static Pociag generujLosowyPociag(MapaTransportu mapaTransportu) {
        int iloscWagonow = 5;
        return new Pociag(new Lokomotywa(), Wagon.stworzZestawWagonow(iloscWagonow), mapaTransportu);
    }

    public static Wagon dodajLosowyWagonDoPociagu() {
        int losowa = 13;
        Wagon nowoPodlaczanyWagon;
        switch (new Random().nextInt(losowa)) {
            case 0:
                return nowoPodlaczanyWagon = new BagazowoPocztowy();
            case 1:
                return nowoPodlaczanyWagon = new Chlodniczy();
            case 2:
                return nowoPodlaczanyWagon = new Pasazerski();
            case 3:
                return nowoPodlaczanyWagon = new Pocztowy();
            case 4:
                return nowoPodlaczanyWagon = new Restauracyjny();
            case 5:
                return nowoPodlaczanyWagon = new TowarowyCiezki();
            case 6:
                return nowoPodlaczanyWagon = new TCMaterialyToksyczne();
            case 7:
                return nowoPodlaczanyWagon = new TCMaterialyWybuchowe();
            case 8:
                return nowoPodlaczanyWagon = new TCMaterialyWybuchoweCiekle();
            case 9:
                return nowoPodlaczanyWagon = new TowarowyPodstawowy();
            case 10:
                return nowoPodlaczanyWagon = new TPChlodniczy();
            case 11:
                return nowoPodlaczanyWagon = new TPMaterialyCiekle();
            case 12:
                return nowoPodlaczanyWagon = new TPMaterialyGazowe();
        }
        return null;
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

    public static String zdajRaportPociagu(Pociag pociag) {
        String nazwaStacji = pociag.stacjaMacierzysta.getNazwaStacji();
        String lastStacji = pociag.stacjaZrodlowa != null ? pociag.stacjaZrodlowa.getNazwaStacji() : "brak";
        String zawartoscRaportu = pociag.toString() +
                "\nStacja macierzysta: " + nazwaStacji +
                "\nStacja, z której ostatnio wyjechano: " + lastStacji +
                "\nStacja docelowa: " + pociag.stacjaDocelowa.getNazwaStacji() +
                "\nAktualna prędkość: " + pociag.predkosc + // czy poprawnie
                "\nProcent ukończonej drogi na całej trasie: " + "DO DODANIA" + // todo
                "\nProcent ukończonej drogi na do najbliższej stacji: " + "DO DODANIA" + // todo
                "\nLiczba wagonów: " + "DO DODANIA" + //todo
                "\nRodzaj wagonów i zawartość: " + "DO DODANIA" //todo
                ;
        return zawartoscRaportu;
    }

    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setPaint(Color.GREEN);
        int dlugoscBoku = 10;
        if (this.zaplanowanaTrasaJazdy == null) {
            g2d.fillRect((int) this.stacjaMacierzysta.getX() - dlugoscBoku / 2, (int) this.stacjaMacierzysta.getY() - dlugoscBoku / 2, dlugoscBoku, dlugoscBoku);
            return;
        }
        if (this.stacjaZrodlowa == null) {

            StacjaKolejowa stacjaKolejowa = this.zaplanowanaTrasaJazdy.get(this.aktualnaPosredniaTrasaPodrozy);
            g2d.fillRect((int) stacjaKolejowa.getX() - dlugoscBoku / 2, (int) stacjaKolejowa.getY() - dlugoscBoku / 2, dlugoscBoku, dlugoscBoku);

            return;
        }
        skaluj(g2d);
    }

    public void skaluj(Graphics2D g2d) {

        double x = this.stacjaZrodlowa.getX();
        double y = this.stacjaZrodlowa.getY();

        g2d.fillRect((int) (x + getPozycjaX()) - 5, (int) (y + getPozycjaY()) - 5, 10, 10);
    }

    public double getPozycjaX() {
        if (this.stacjaZrodlowa == null) return this.stacjaMacierzysta.getX();
        StacjaKolejowa stacjaZrodlowa = this.stacjaZrodlowa;
        StacjaKolejowa najblizszaDocelowa = this.getNajblizszaDocelowa();
        double pelnaDlugosc = MapaTransportu.obliczDlugoscTrasy(stacjaZrodlowa, najblizszaDocelowa);
        double procentTrasy = this.przebytaDroga / pelnaDlugosc;

        double x = stacjaZrodlowa.getX();
        double x1 = najblizszaDocelowa.getX();
        double absX = Math.abs(x - x1);

        absX = absX * procentTrasy;
        if (x > x1) absX = -absX;
        return absX;
    }

    public double getPozycjaY() {
        if (this.stacjaZrodlowa == null) return this.stacjaMacierzysta.getY();
        StacjaKolejowa stacjaZrodlowa = this.stacjaZrodlowa;
        StacjaKolejowa najblizszaDocelowa = this.getNajblizszaDocelowa();
        double pelnaDlugosc = MapaTransportu.obliczDlugoscTrasy(stacjaZrodlowa, najblizszaDocelowa);
        double procentTrasy = this.przebytaDroga / pelnaDlugosc;

        double y = stacjaZrodlowa.getY();
        double y1 = najblizszaDocelowa.getY();
        double absY = Math.abs(y - y1);

        absY = absY * procentTrasy;
        if (y > y1) absY = -absY;
        return absY;
    }


    public LinkedList<StacjaKolejowa> getZaplanowanaTrasaJazdy() {
        return this.zaplanowanaTrasaJazdy;
    }

    public void setZaplanowanaTrasaJazdy(LinkedList<StacjaKolejowa> trasaJazdy) {
        this.zaplanowanaTrasaJazdy = trasaJazdy;
    }

    public void wyznaczObszarDookolaPociagu(Pociag pociag) {

    }

    public void jedz(long deltaT, long tick, int updatesPerSecond, RuchPociagow ruchPociagow) {
        //czas postoju 30 sec
        if (this.czasRozpoczeciaPostoju > System.currentTimeMillis()) return;
        if (this.stacjaZrodlowa == null) {
            // todo kolizja
            if (znajdzPociagNaTejsamejTrasie(ruchPociagow) == null) {
                this.stacjaZrodlowa = this.zaplanowanaTrasaJazdy.get(this.aktualnaPosredniaTrasaPodrozy);
            }
            return;
        }

        // pociag jedzie
        this.przebytaDroga += obliczPrzebytaDroga(deltaT, this.predkosc);
        long l = deltaT * ((tick % updatesPerSecond) + 4);
        if (l > 1000) {
            this.predkosc = nadajPredkosc(); // nie dziala
        }
        double dlugoscTrasy = MapaTransportu.obliczDlugoscTrasy(stacjaZrodlowa, getNajblizszaDocelowa());
        if (this.przebytaDroga >= dlugoscTrasy) {
            //todo wyciągnąć z mapy transportu - dlugosc tras

            this.stacjaZrodlowa = null;
            this.aktualnaPosredniaTrasaPodrozy++;
            this.czasRozpoczeciaPostoju = System.currentTimeMillis() + 2_000;
            this.przebytaDroga = 0;
            this.predkosc = predkosc;

            StacjaKolejowa currentSk = this.zaplanowanaTrasaJazdy.get(this.aktualnaPosredniaTrasaPodrozy);
            if (currentSk == this.stacjaDocelowa || currentSk == this.stacjaMacierzysta) {
                this.czasRozpoczeciaPostoju = System.currentTimeMillis() + 30_000;
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

    public void setStatus(String s) {
        this.status = s;
    }

    private StacjaKolejowa znajdzPociagNaTejsamejTrasie(RuchPociagow ruchPociagow) {
        for (Pociag pociag : ruchPociagow.getPociagi()) {
            if (this == pociag) continue;
            if (
                    (pociag.stacjaZrodlowa == this.getNajblizszaDocelowa() && pociag.getNajblizszaDocelowa() == this.zaplanowanaTrasaJazdy.get(this.aktualnaPosredniaTrasaPodrozy))
                // || pociag.stacjaZrodlowa == this.stacjaZrodlowa /*&& pociag.getNajblizszaDocelowa() == this.getNajblizszaDocelowa()*/ - nie moga wjechac na ta sama trase
            )
                return pociag.stacjaZrodlowa;
        }
        return null;
    }

    public String getNazwaPociagu() {
        return null;
        //todo identyfikator
    }

    public String nadajNazwe() {
        nazwaPociagu = "TRAIN" + String.valueOf(nrIdentyfikacyjnyPociagu);
        return nazwaPociagu;
    }
}