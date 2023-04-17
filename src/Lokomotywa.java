import mapa.StacjaKolejowa;

import java.util.ArrayList;
import java.util.HashMap;

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
        this.nrIdentyfikacyjnyLokomotywy = counter; // w pociagu
        counter++;
        stacjaMacierzysta = Main.mapaTransportu.getLosowaStacja();
        // todo stacja docelowa nie może być macierzystą
        stacjaDocelowa = Main.mapaTransportu.getLosowaStacja();
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
