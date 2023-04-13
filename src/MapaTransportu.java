import java.util.*;

public class MapaTransportu {

    private Map<StacjaKolejowa, List<StacjaKolejowa>> trasyKolejowe;

    public MapaTransportu(List<StacjaKolejowa> listaStacji) {
        this.trasyKolejowe = generujTrasyKolejowe(listaStacji);
    }

    private Map<StacjaKolejowa, List<StacjaKolejowa>> generujTrasyKolejowe(List<StacjaKolejowa> listaStacji) {
        Map<StacjaKolejowa, List<StacjaKolejowa>> trasyKolejowe = new HashMap<>();

        for (StacjaKolejowa sk : listaStacji) {
            if (!trasyKolejowe.containsKey(sk)) {
                trasyKolejowe.put(sk, new ArrayList<StacjaKolejowa>());
            }
            do {
                int losowyNr = (int) (Math.random() * listaStacji.size());
                trasyKolejowe.get(sk).add(listaStacji.get(losowyNr));
            } while (Math.random()<0.2);
        }

        return trasyKolejowe;
    }

    public Set<StacjaKolejowa> getListStacjeKolejowe() {
        return trasyKolejowe.keySet();
    }

    public List<StacjaKolejowa> getStacjeDocelowe(StacjaKolejowa sk) {
        return trasyKolejowe.get(sk);
    }
}
