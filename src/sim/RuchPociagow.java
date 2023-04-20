package sim;

import mapa.MapaTransportu;
import pociag.Pociag;

import java.util.ArrayList;
import java.util.List;

public class RuchPociagow {
    // baza pociagow

    private final List<Pociag> pociagi = new ArrayList<>();
    private final MapaTransportu mapaTransportu;

    public RuchPociagow(MapaTransportu mapaTransportu) {
        this.mapaTransportu = mapaTransportu;
    }

    public void dodajPociag(Pociag pociag) {
        this.pociagi.add(pociag);
    }

    public List<Pociag> getPociagi() {
        return this.pociagi;
    }

    public void usunPociag(Pociag pociag) {
        pociagi.remove(pociag);
    }
}
