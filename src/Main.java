import mapa.MapaTransportu;
import mapa.StacjaKolejowa;
import swing.GUI;


public class Main {
    public static final int iloscStacji = 20;
    static MapaTransportu mapaTransportu = new MapaTransportu(StacjaKolejowa.stworzStacje(20));

    public static void main(String[] args) throws InterruptedException {
//        new PociagSymulator(mapaTransportu);
        new GUI(mapaTransportu);

    }

}