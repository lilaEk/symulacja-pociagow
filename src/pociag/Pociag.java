package pociag;

import mapa.MapaTransportu;
import mapa.StacjaKolejowa;
import sim.RailroadHazard;
import sim.RuchPociagow;
import wagony.Wagon;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.List;
import java.util.*;

public class Pociag extends Rectangle2D.Double {

    private static final int maxUciag = 800;
    private static int maxLiczbaWagonow = 15;
    private static int nrIdentyfikacyjnyPociagu;
    private static int counter = 1;
    private final int maxPredkosc = 200;
    private final int maxWagonyPodlaczoneDoElektrycznosci = 5;
    public final List<Wagon> wagony;
    public StacjaKolejowa stacjaZrodlowa;
    private String nazwaPociagu;
    private double predkosc = 100;
    public static ArrayList<Pociag> pociagi = new ArrayList<>();
    protected LinkedList<StacjaKolejowa> zaplanowanaTrasaJazdy;

    private StacjaKolejowa stacjaMacierzysta;
    private StacjaKolejowa stacjaDocelowa;
    public int liczbaWagonow;

    private double przebytaDroga;
    private int aktualnaPosredniaTrasaPodrozy = 0;
    private long czasRozpoczeciaPostoju = 0;
    private String status = "spawned";
    private ArrayList<Wagon> listaWagonowPociagu;


    public Pociag(List<Wagon> wagony, MapaTransportu mapaTransportu) {
        // dla rysowania kwadratu na mapie
        this.width = 10;
        this.height = this.width;

        this.nrIdentyfikacyjnyPociagu = counter;
        counter++;
        this.nazwaPociagu = nadajNazwe();
        this.wagony = wagony;
        this.predkosc = 100;

        this.stacjaMacierzysta = mapaTransportu.getLosowaStacja();
        do {
            this.stacjaDocelowa = mapaTransportu.getLosowaStacja();
        } while (this.stacjaMacierzysta == this.stacjaDocelowa);

        pociagi.add(this);
        this.liczbaWagonow = policzWagony();
    }

    public static Pociag generujLosowyPociag(MapaTransportu mapaTransportu, int iloscWagonow) {
        try {
            if (iloscWagonow > maxLiczbaWagonow) {
                throw new Exception("Przekroczono dopuszczalną ilość wagonów.");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return new Pociag(Wagon.stworzZestawWagonow(iloscWagonow), mapaTransportu);
    }

    public synchronized static ArrayList<Pociag> getPociagi() {
        return pociagi;
    }

    public static String zdajRaportWagonow(Pociag pociag) {
        String informacjeWagony = "\nRodzaj wagonów i zawartość: ";
        StringBuilder sb = new StringBuilder(informacjeWagony);
        int counter = 1;
        for (Wagon wagon : pociag.wagony) {
            sb.append("\n" + counter + ". " + wagon);
            counter++;
        }
        return sb.toString();
    }

    public static String zdajRaportPociagu(Pociag pociag) {
        String nazwaStacji = pociag.stacjaMacierzysta.getNazwaStacji();
        String ostatniaPoprzedniaStacji = pociag.stacjaZrodlowa != null ? pociag.stacjaZrodlowa.getNazwaStacji() : pociag.stacjaMacierzysta.getNazwaStacji();
        String docelowa = pociag.stacjaDocelowa != null ? pociag.stacjaDocelowa.getNazwaStacji() : "Nie utworzono trasy - brak stacji docelowej";
        String procent1 = pociag.procentTrasyMiedzyStacjami() != null ? pociag.procentTrasyMiedzyStacjami() : "Nie utworzono trasy - 0%";
        String procent2 = null; //todo


        String zawartoscRaportu = pociag.toString() + "\nStatus pociągu: " + pociag.status +
                "\nStacja macierzysta: " + nazwaStacji +
                "\nStacja, z której ostatnio wyjechano: " + ostatniaPoprzedniaStacji +
                "\nStacja docelowa: " + docelowa +
                "\nAktualna prędkość: " + pociag.predkosc +
                "\nProcent ukończonej drogi na całej trasie: " + procent2 +
                "\nProcent ukończonej drogi na do najbliższej stacji: " + procent1 +
                "\nLiczba wagonów: " + pociag.liczbaWagonow +
                zdajRaportWagonow(pociag);

        return zawartoscRaportu;
    }


    public static void usunPociag(Pociag pociag) {
        pociagi.remove(pociag);
    }

    public static int getMaxUciag() {
        return maxUciag;
    }

    public static Pociag losujPociag() {
        return pociagi.get(new Random().nextInt(pociagi.size()));
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

    private int policzWagony() {
        int iloscWagonow = new Random().nextInt(10) + 5;
        return iloscWagonow;
    }

    private int getMaxLiczbaWagonow() {
        return getMaxLiczbaWagonow();
    }

    @Override
    public String toString() {
        return "Pociag o nazwie " + nazwaPociagu;
    }

    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setPaint(Color.GREEN);
        int dlugoscBoku = 10;
        if (this.zaplanowanaTrasaJazdy == null) {
            this.x = (int) this.stacjaMacierzysta.getX() - this.width / 2;
            this.y = (int) this.stacjaMacierzysta.getY() - this.width / 2;
            g2d.fill(this);
            return;
        }
        if (this.stacjaZrodlowa == null) {
            StacjaKolejowa stacjaKolejowa = this.zaplanowanaTrasaJazdy.get(this.aktualnaPosredniaTrasaPodrozy);
            this.x = (int) stacjaKolejowa.getX() - this.width / 2;
            this.y = (int) stacjaKolejowa.getY() - this.width / 2;
            g2d.fill(this);
            return;
        }
        skaluj(g2d);
    }

    public void skaluj(Graphics2D g2d) {
        double x = this.stacjaZrodlowa.getX();
        double y = this.stacjaZrodlowa.getY();
        this.x = x + getPozycjaX() - this.width / 2;
        this.y = y + getPozycjaY() - this.width / 2;
        g2d.fill(this);
    }

    public String procentTrasyMiedzyStacjami() {
        if (this.stacjaZrodlowa == null) return "0 %";
        double pelnaDlugosc = MapaTransportu.obliczDlugoscTrasy(this.stacjaZrodlowa, this.getNajblizszaDocelowa());
        double procentTrasy = this.przebytaDroga / pelnaDlugosc; // todo oblicz przebytą drogę
        return String.valueOf(procentTrasy * 100) + " %";
    }


    public double getPozycjaX() {

        //test
        if (this.getNajblizszaDocelowa() == null) {
            return stacjaMacierzysta.getX();
        }

        StacjaKolejowa stacjaZrodlowa;
        if (this.stacjaZrodlowa != null) {
            stacjaZrodlowa = this.stacjaZrodlowa;
        } else {
            stacjaZrodlowa = this.stacjaMacierzysta;
        }

        StacjaKolejowa najblizszaDocelowa = this.getNajblizszaDocelowa();
        double pelnaDlugosc = MapaTransportu.obliczDlugoscTrasy(stacjaZrodlowa, najblizszaDocelowa);
        double procentTrasy = this.przebytaDroga / pelnaDlugosc;

        double x = stacjaZrodlowa.getX();
        double x1 = najblizszaDocelowa.getX();
        double absX = Math.abs(x - x1);

        absX = absX * procentTrasy;
        if (x > x1) absX = (-absX);
        return absX;
    }

    public double getPozycjaY() {

        if (this.getNajblizszaDocelowa() == null) {
            return stacjaMacierzysta.getY();
        }

        StacjaKolejowa stacjaZrodlowa;
        if (this.stacjaZrodlowa != null) {
            stacjaZrodlowa = this.stacjaZrodlowa;
        } else {
            stacjaZrodlowa = this.stacjaMacierzysta;
        }

        StacjaKolejowa najblizszaDocelowa = this.getNajblizszaDocelowa();
        double pelnaDlugosc = MapaTransportu.obliczDlugoscTrasy(stacjaZrodlowa, najblizszaDocelowa);
//        double procentTrasy = this.obliczPrzebytaDroga(convertToMetersPerSecond(delta), this.predkosc ) / pelnaDlugosc;
        double procentTrasy = this.przebytaDroga / pelnaDlugosc; // przebyta droga==0

        double y = stacjaZrodlowa.getY();
        double y1 = najblizszaDocelowa.getY();
        double absY = Math.abs(y - y1);

        absY = absY * procentTrasy;
        if (y > y1) absY = (-absY);

        return absY;
    }

    public LinkedList<StacjaKolejowa> getZaplanowanaTrasaJazdy() {
        return this.zaplanowanaTrasaJazdy;
    }

    public void setZaplanowanaTrasaJazdy(LinkedList<StacjaKolejowa> trasaJazdy) {
        this.zaplanowanaTrasaJazdy = trasaJazdy;
    }

    public int liczWage(Pociag pociag) {
        int wagaPociagu = 0;
        for (Wagon wagon : wagony) {
            wagaPociagu = wagaPociagu + wagon.getCalkowitaWagaWagonu();
        }
        return wagaPociagu;
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
        if (this.zaplanowanaTrasaJazdy == null) {
            this.status = "Brak dostępnych połączeń. Dodaj połączenie ręcznie.";
            return null;
        }
        if (this.aktualnaPosredniaTrasaPodrozy < this.zaplanowanaTrasaJazdy.size() - 1)
            return zaplanowanaTrasaJazdy.get(this.aktualnaPosredniaTrasaPodrozy + 1);
        return this.zaplanowanaTrasaJazdy.getLast();
    }

    public void setStatus(String s) {
        this.status = s;
    }

    public void jedz(long deltaT, long tick, int updatesPerSecond, RuchPociagow ruchPociagow) throws RailroadHazard {

        if (this.czasRozpoczeciaPostoju > System.currentTimeMillis()) return;
        if (this.stacjaZrodlowa == null) {
            if (znajdzPociagNaTejsamejTrasie(ruchPociagow) == null) {
                this.stacjaZrodlowa = this.zaplanowanaTrasaJazdy.get(this.aktualnaPosredniaTrasaPodrozy);
            }
            this.status = "Oczekuje aż inny pociąg zjedzie z trasy.";
            return;
        }
        // pociag jedzie
        this.status = "Jedzie.";
        this.przebytaDroga += obliczPrzebytaDroga(deltaT, this.predkosc);
        long l = deltaT * ((tick % updatesPerSecond) + 4);
        if (l / 1000 > 0) {
            this.predkosc = nadajPredkosc();
            if (predkosc > 200) throw new RailroadHazard("Prędkość niedopuszczalna." + this.toString());

        }
        double dlugoscTrasy = MapaTransportu.obliczDlugoscTrasy(stacjaZrodlowa, getNajblizszaDocelowa());
        if (this.przebytaDroga >= dlugoscTrasy) {

            this.stacjaZrodlowa = null;
            this.aktualnaPosredniaTrasaPodrozy++;
            this.czasRozpoczeciaPostoju = System.currentTimeMillis() + 2_000;
            this.status = "Czeka na postoju - 2 sek.";
            this.przebytaDroga = 0;
            this.predkosc = predkosc;

            StacjaKolejowa currentSk = this.zaplanowanaTrasaJazdy.get(this.aktualnaPosredniaTrasaPodrozy);
            if (currentSk == this.stacjaDocelowa || currentSk == this.stacjaMacierzysta) {
                this.czasRozpoczeciaPostoju = System.currentTimeMillis() + 30_000;
                this.status = "Czeka na postoju - 30 sek.";
                Collections.reverse(this.zaplanowanaTrasaJazdy);
                this.aktualnaPosredniaTrasaPodrozy = 0;
            }
        }

    }

    public String getNazwaPociagu() {
        return nazwaPociagu;
    }

    public String nadajNazwe() {
        nazwaPociagu = "TRAIN" + String.valueOf(nrIdentyfikacyjnyPociagu);
        return nazwaPociagu;
    }

    private StacjaKolejowa znajdzPociagNaTejsamejTrasie(RuchPociagow ruchPociagow) {
        for (Pociag pociag : pociagi) {
            if (this == pociag) continue;
            if ((pociag.stacjaZrodlowa == this.getNajblizszaDocelowa() && pociag.getNajblizszaDocelowa() == this.zaplanowanaTrasaJazdy.get(this.aktualnaPosredniaTrasaPodrozy))) {
                return pociag.stacjaZrodlowa;
            }
            if ((pociag.getNajblizszaDocelowa() == this.getNajblizszaDocelowa() && pociag.stacjaZrodlowa == this.zaplanowanaTrasaJazdy.get(this.aktualnaPosredniaTrasaPodrozy))) {
                return pociag.stacjaZrodlowa;
            }
        }
        return null;
    }

    public int getMaxPredkosc() {
        return maxPredkosc;
    }

    public int getMaxWagonyPodlaczoneDoElektrycznosci() {
        return maxWagonyPodlaczoneDoElektrycznosci;
    }

    public void addWagon(Wagon nowoPodlaczanyWagon) {
        this.wagony.add(nowoPodlaczanyWagon);
    }
}