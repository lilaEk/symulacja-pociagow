package pociag;

public class Lokomotywa {

    private String nazwaLokomotywy;
    private final int maxLiczbaWagonów = 15;
    private final int maxUciag = 450; //ton
    private final int maxPredkosc = 200;
    private final int maxWagonyPodlaczoneDoElektrycznosci = 4; //potrzebujemy dostepu z pociagu



    private static int nrIdentyfikacyjnyLokomotywy;
    private int counter = 1;

    public Lokomotywa() {
        this.nrIdentyfikacyjnyLokomotywy = counter; // w pociagu
        counter++;
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
