import mapa.MapaTransportu;
import mapa.StacjaKolejowa;
import pociag.Pociag;
import sim.PociagSymulator;
import sim.RuchPociagow;
import swing.GUI;

import java.util.Date;


public class Main {
    final static MapaTransportu mapaTransportu = new MapaTransportu(StacjaKolejowa.stworzStacje(20));
    final static RuchPociagow ruchPociagow = new RuchPociagow(mapaTransportu);
    final static int updatesPerSecond = 1;

    public static void main(String[] args) throws InterruptedException {

        ruchPociagow.dodajPociag(Pociag.generujLosowyPociag(mapaTransportu));

        PociagSymulator pociagSymulator = new PociagSymulator(mapaTransportu, ruchPociagow);
        GUI gui = new GUI(mapaTransportu, ruchPociagow);


        Thread sim = new Thread(() -> {
            long now = System.currentTimeMillis();
            while (true) {
                if (System.currentTimeMillis() - now < 1000 / updatesPerSecond) {
                    continue;
                }
                now = System.currentTimeMillis();
                System.out.println(new Date().toString());
                pociagSymulator.update();
                gui.repaint();
            }
        });
        sim.start();
    }
}