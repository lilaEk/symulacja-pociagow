package pociag;

import mapa.MapaTransportu;
import mapa.StacjaKolejowa;

import java.util.LinkedList;

public class Lokomotywa {

    private String nazwaLokomotywy;
    private final int maxLiczbaWagonów = 15;
    private final int maxUciag = 450; //ton
    private final int maxPredkosc = 200;
    private final int maxWagonyPodlaczoneDoElektrycznosci = 4; //potrzebujemy dostepu z pociagu

    private StacjaKolejowa stacjaMacierzysta;
    private StacjaKolejowa stacjaZrodlowa;
    private StacjaKolejowa stacjaDocelowa;
    protected LinkedList<StacjaKolejowa> mapaTrasyMacierzystaDocelowa;


    private static int nrIdentyfikacyjnyLokomotywy;
    private int counter = 1;

    public Lokomotywa(MapaTransportu mapaTransportu) {
        this.nrIdentyfikacyjnyLokomotywy = counter; // w pociagu
        counter++;
        stacjaMacierzysta = mapaTransportu.getLosowaStacja();
        // todo stacja docelowa nie może być macierzystą
        stacjaDocelowa = mapaTransportu.getLosowaStacja();
        this.nazwaLokomotywy = nadajNazwe();
    }

//    public ArrayList<pociag.Lokomotywa> stworzZestawLokomotyw(int iloscLokomotywc) {
//        return null;
//    }

    public String nadajNazwe() {
        this.nazwaLokomotywy = "A" + String.valueOf(nrIdentyfikacyjnyLokomotywy);
        return nazwaLokomotywy;
    }

//    public pociag.Lokomotywa aktualizujPredkosc() {
//        return null;
//    }


}
