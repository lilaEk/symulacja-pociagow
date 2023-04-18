package sim;

import mapa.MapaTransportu;
import mapa.StacjaKolejowa;
import pociag.Pociag;

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

    public void update() {
        for (Pociag pociag : ruchPociagow.getPociagi()) {
            if (pociag.getZaplanowanaTrasaJazdy() == null) {
                pociag.setZaplanowanaTrasaJazdy(generujTraseJazdy(pociag));
            }
        }
    }

    private LinkedList<StacjaKolejowa> generujTraseJazdy(Pociag pociag) {
        List<LinkedList<StacjaKolejowa>> paths = new ArrayList<>();
        StacjaKolejowa currentNode = pociag.getStacjaMacierzysta();
        List<StacjaKolejowa> visited = new ArrayList<>();
        visited.add(pociag.getStacjaMacierzysta());

        List<LinkedList<StacjaKolejowa>> ret = findPaths(pociag, paths, currentNode, visited);
        //todo znajdz najkrotsza sciezke na podstawie dlugosci tras
        return ret.get(1);
    }

    private List<LinkedList<StacjaKolejowa>> findPaths(Pociag pociag, List<LinkedList<StacjaKolejowa>> paths, StacjaKolejowa currentNode, List<StacjaKolejowa> visited) {
        if (currentNode.equals(pociag.getStacjaDocelowa())) {
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

