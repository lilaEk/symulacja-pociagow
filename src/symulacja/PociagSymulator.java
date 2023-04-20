package symulacja;

import mapa.MapaTransportu;
import mapa.StacjaKolejowa;
import pociag.Pociag;
import pociag.RailroadHazard;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class PociagSymulator {

    private final RuchPociagow ruchPociagow;
    private final MapaTransportu mapaTransportu;

    public PociagSymulator(MapaTransportu mapaTransportu, RuchPociagow ruchPociagow) {
        this.mapaTransportu = mapaTransportu;
        this.ruchPociagow = ruchPociagow;
    }

    public void update(long deltaT, long tick, int updatesPerSecond) {
        for (Pociag pociag : Pociag.getPociagi()) {
            if (pociag.getZaplanowanaTrasaJazdy() == null) {
                pociag.setZaplanowanaTrasaJazdy(generujTraseJazdy(pociag));
            }
            if (pociag.getZaplanowanaTrasaJazdy() != null) {
                try {
                    pociag.jedz(deltaT, tick, updatesPerSecond, ruchPociagow);
                } catch (RailroadHazard e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private LinkedList<StacjaKolejowa> generujTraseJazdy(Pociag pociag) {
        try {
            List<LinkedList<StacjaKolejowa>> paths = new ArrayList<>();
            StacjaKolejowa currentNode = pociag.getStacjaMacierzysta();
            List<StacjaKolejowa> visited = new ArrayList<>();
            visited.add(pociag.getStacjaMacierzysta());

            List<LinkedList<StacjaKolejowa>> ret = findPaths(pociag, paths, currentNode, visited);

            return ret.get(0);
        } catch (Exception e) {
            pociag.setStatus("Brak połączeń dla trasy między stacją " + pociag.getStacjaMacierzysta().getNazwaStacji() + ", a stacją " + pociag.getStacjaDocelowa().getNazwaStacji() + ". Dodaj połączenie ręcznie.");
        }
        return null;
    }

    private List<LinkedList<StacjaKolejowa>> findPaths(Pociag pociag, List<LinkedList<StacjaKolejowa>> paths, StacjaKolejowa currentNode, List<StacjaKolejowa> visited) {
        if (paths.size() > 0) return paths;
        if (currentNode.equals(pociag.getStacjaDocelowa()) && visited.size() > 1) {
            paths.add(new LinkedList<>(visited));
        } else {
            Set<StacjaKolejowa> nodes = mapaTransportu.getStacjeDocelowe(currentNode);
            for (StacjaKolejowa node : nodes) {
                if (visited.contains(node)) {
                    continue;
                }
                List<StacjaKolejowa> temp = new ArrayList<>(visited);
                temp.add(node);
                findPaths(pociag, paths, node, temp);
            }
        }
        return paths;
    }
}

