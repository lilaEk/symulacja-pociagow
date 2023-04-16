package mapa;

import java.util.*;
// przy tworzeniu mapy wylicza środek i wypisuje tam koszt
public class MapaTransportu {

    private Map<StacjaKolejowa, Set<StacjaKolejowa>> trasyKolejowe;
    private Map<StacjaKolejowa[], Double> dlugoscTras;

    private static double maxDlugoscTrasy = 450;

    public MapaTransportu(List<StacjaKolejowa> listaStacji) {
        this.dlugoscTras = new HashMap<StacjaKolejowa[], Double>();
        this.trasyKolejowe = generujTrasyKolejowe(listaStacji);

    }

    private Map<StacjaKolejowa, Set<StacjaKolejowa>> generujTrasyKolejowe(List<StacjaKolejowa> listaStacji) {
        Map<StacjaKolejowa, Set<StacjaKolejowa>> trasaKolejowa = new HashMap<>();

        for (StacjaKolejowa stacjaGlowna : listaStacji) {
            if (!trasaKolejowa.containsKey(stacjaGlowna)) {
                trasaKolejowa.put(stacjaGlowna, new HashSet<StacjaKolejowa>());
            }

            do {
                int losowyIndeksStacji = new Random().nextInt(listaStacji.size());
                StacjaKolejowa stacjaDocelowa = listaStacji.get(losowyIndeksStacji);

//                boolean b = dodajTrase(listaStacji, trasaKolejowa, stacjaGlowna, stacjaDocelowa);
                dodajTrase(listaStacji, trasaKolejowa, stacjaGlowna, stacjaDocelowa);
                // zmiana random w nastepnej linijce - losowa ilosc stacji

            } while (trasaKolejowa.get(stacjaGlowna).size() == 0 || (trasaKolejowa.get(stacjaGlowna).size() < 4 && Math.random() < 0.5));
        }

        return trasaKolejowa;
    }

    private boolean dodajTrase(List<StacjaKolejowa> listaStacji, Map<StacjaKolejowa, Set<StacjaKolejowa>> trasaKolejowa, StacjaKolejowa sk, StacjaKolejowa stacjaDocelowa) {
        if (!czyWartosciSiePowtarzaja(sk, stacjaDocelowa, trasaKolejowa)) {

            StacjaKolejowa[] paraStacji = {sk, stacjaDocelowa};
            double dlugoscTrasy = obliczDlugoscTrasy(sk, stacjaDocelowa);

            if (dlugoscTrasy > maxDlugoscTrasy) return false;

            if (sk==stacjaDocelowa) return false;

            this.dlugoscTras.put(paraStacji, dlugoscTrasy);
            trasaKolejowa.get(sk).add(stacjaDocelowa);

            return true;
        }
        return false;
    }

    private double obliczDlugoscTrasy(StacjaKolejowa sk, StacjaKolejowa stacjaDocelowa) {

        double x2 = Math.pow(Math.abs(sk.getX() - stacjaDocelowa.getX()), 2);
        double y2 = Math.pow(Math.abs(sk.getY() - stacjaDocelowa.getY()), 2);
        double dlugoscTrasy = Math.sqrt(x2 + y2);
        return dlugoscTrasy;
    }

    public Set<StacjaKolejowa> getListStacjeKolejowe() {
        return trasyKolejowe.keySet();
    }

    public Set<StacjaKolejowa> getStacjeDocelowe(StacjaKolejowa sk) {
        return trasyKolejowe.get(sk);
    }

    public boolean czyWartosciSiePowtarzaja(StacjaKolejowa stacjaGlowna, StacjaKolejowa stacjaDocelowa, Map<StacjaKolejowa, Set<StacjaKolejowa>> mapaStacji) {

        if (!mapaStacji.containsKey(stacjaDocelowa)) return false;

        for (StacjaKolejowa temp : mapaStacji.get(stacjaDocelowa)) {
            if (stacjaGlowna == temp) return true;
        }
        return false;
    }

    public StacjaKolejowa generujLosowaStacje() {
        return null;
    }

    public void addStacja(StacjaKolejowa generujLosowaStacje) {
        trasyKolejowe.put(generujLosowaStacje,new HashSet<>());
    }
}
