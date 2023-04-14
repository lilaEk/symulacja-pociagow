import java.util.*;

public class MapaTransportu {

    private Map<StacjaKolejowa, Set<StacjaKolejowa>> trasyKolejowe;
    private Map<StacjaKolejowa[], Double> dlugoscTras;

    private double maxDlugoscTrasy = 250;

    public MapaTransportu(List<StacjaKolejowa> listaStacji) {
        this.dlugoscTras = new HashMap<StacjaKolejowa[], Double>();
        this.trasyKolejowe = generujTrasyKolejowe(listaStacji);

    }

    private Map<StacjaKolejowa, Set<StacjaKolejowa>> generujTrasyKolejowe(List<StacjaKolejowa> listaStacji) {
        Map<StacjaKolejowa, Set<StacjaKolejowa>> trasaKolejowa = new HashMap<>();

        for (StacjaKolejowa sk : listaStacji) {
            if (!trasaKolejowa.containsKey(sk)) {
                trasaKolejowa.put(sk, new HashSet<StacjaKolejowa>());
            }
            int x = 0;
            do {
                int losowyIndeksStacji = (int) (Math.random() * listaStacji.size());
                StacjaKolejowa stacjaDocelowa = listaStacji.get(losowyIndeksStacji);

                if (dodajTrase(listaStacji, trasaKolejowa, sk, stacjaDocelowa)) x++;

            } while ((Math.random() < 0.2) && x < 4);
        }

        return trasaKolejowa;
    }

    private boolean dodajTrase(List<StacjaKolejowa> listaStacji, Map<StacjaKolejowa, Set<StacjaKolejowa>> trasaKolejowa, StacjaKolejowa sk, StacjaKolejowa stacjaDocelowa) {
        if (!czyWartosciSiePowtarzaja(sk, stacjaDocelowa, trasaKolejowa)) {

            StacjaKolejowa[] paraStacji = {sk, stacjaDocelowa};
            double dlugoscTrasy = obliczDlugoscTrasy(sk, stacjaDocelowa);
            if (dlugoscTrasy > maxDlugoscTrasy) return false;

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

    public boolean czyWartosciSiePowtarzaja(StacjaKolejowa sk, StacjaKolejowa losowa, Map<StacjaKolejowa, Set<StacjaKolejowa>> mapaStacji) {

        if (!mapaStacji.containsKey(losowa)) return false;

        for (StacjaKolejowa temp : mapaStacji.get(losowa)) {
            if (sk == temp) return true;
        }
        return false;
    }

}
