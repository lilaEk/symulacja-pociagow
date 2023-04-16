import mapa.MapaTransportu;
import mapa.StacjaKolejowa;
import swing.GUI;
import java.util.ArrayList;


public class Main {
    public static final int iloscStacji = 20;
    final static ArrayList<StacjaKolejowa> stacjeKolejowe = StacjaKolejowa.stworzStacje(iloscStacji);
    public static void main(String[] args) throws InterruptedException {

//        final ArrayList<StacjaKolejowa> stacjeKolejowe = StacjaKolejowa.stworzStacje(iloscStacji);
        new GUI(new MapaTransportu(stacjeKolejowe));

    }

    public static int getIloscStacji(){
        return iloscStacji;
    } // STATIC ???
}