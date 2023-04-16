import mapa.StacjaKolejowa;
import wagony.Wagon;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public abstract class Lokomotywa {

    private int iloscLokomotyw;
    //private

    private static final int maxLiczbaWagonów = 15;
    private static final int maxWaga = 450; //ton
    private static final int maxPredkosc = 200;
    private static final int maxWagonyPodlaczoneDoElektrycznosci = 4; //potrzebujemy dostepu z pociagu

    private String nazwaLokomotywy;
    private double waga;
    private double predkosc;
    private StacjaKolejowa stacjaMacierzysta;
    private StacjaKolejowa stacjaZrodlowa;
    private StacjaKolejowa stacjaDocelowa;
    protected HashMap<StacjaKolejowa, StacjaKolejowa> mapaTrasyMacierzystaDocelowa;


    private static int nrIdentyfikacyjnyLokomotywy;
    private int counter=1;

    public Lokomotywa(){
        this.nrIdentyfikacyjnyLokomotywy = counter;
        counter++;
        stacjaMacierzysta = Main.stacjeKolejowe.get(new Random().nextInt(Main.getIloscStacji()));
        stacjaDocelowa = Main.stacjeKolejowe.get(new Random().nextInt(Main.getIloscStacji()));
        mapaTrasyMacierzystaDocelowa.put(stacjaMacierzysta, stacjaDocelowa);
    }

    public ArrayList<Lokomotywa> stworzZestawLokomotyw(int iloscLokomotywc){
        return null;
    }

    public Lokomotywa nadajNazwe(){
        return null;
    }

    public Lokomotywa nadajPredkosc(){
        // jesli wazy wiecej niz costam to losuj z mniejszych
        return null;
    }

    public Lokomotywa aktualizujPredkosc(){
        return null;
    }


}
