package sim;

import mapa.MapaTransportu;
import pociag.Pociag;

import java.util.ArrayList;
import java.util.List;

public class RuchPociagow {
    private final List<Pociag> pociagi = new ArrayList<>();
    private final MapaTransportu mapaTransportu;

    public RuchPociagow(MapaTransportu mapaTransportu) {
        this.mapaTransportu = mapaTransportu;
    }

    public void dodajPociag(Pociag pociag) {
//        new Pociag(new Lokomotywa(mapaTransportu), Wagon.stworzZestawWagonow(5) );
        this.pociagi.add(pociag);
    }

    public List<Pociag> getPociagi() {
        return this.pociagi;
    }
}
