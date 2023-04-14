import java.util.*;

public class MapaTransportu {

    private Map<StacjaKolejowa, Set<StacjaKolejowa>> trasyKolejowe;

    public MapaTransportu(List<StacjaKolejowa> listaStacji) {
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

                if (dodajTrase(listaStacji, trasaKolejowa, sk, losowyIndeksStacji, stacjaDocelowa)) x++;

            } while ((trasaKolejowa.get(sk) == null) || (Math.random() < 0.2) && x < 4);
        }

        return trasaKolejowa;
    }

    private boolean dodajTrase(List<StacjaKolejowa> listaStacji, Map<StacjaKolejowa, Set<StacjaKolejowa>> trasaKolejowa, StacjaKolejowa sk, int losowaStacja, StacjaKolejowa tmpStacja) {
        if (!czyWartosciSiePowtarzaja(sk, tmpStacja, trasaKolejowa)) {
            trasaKolejowa.get(sk).add(listaStacji.get(losowaStacja));
            return true;
        }
        return false;
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
