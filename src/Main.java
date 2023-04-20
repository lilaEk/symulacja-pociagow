import mapa.MapaTransportu;
import mapa.StacjaKolejowa;
import pociag.Pociag;
import sim.PociagSymulator;
import sim.RailroadHazard;
import sim.RuchPociagow;
import swing.GUI;

import java.util.Random;


public class Main {
    final static MapaTransportu mapaTransportu = new MapaTransportu(StacjaKolejowa.stworzStacje(20));
    final static RuchPociagow ruchPociagow = new RuchPociagow(mapaTransportu);
    final static int updatesPerSecond = 60;

    public static void main(String[] args) throws InterruptedException {

        for (int i = 0; i < 5; i++) {
            ruchPociagow.dodajPociag(Pociag.generujLosowyPociag(mapaTransportu, new Random().nextInt(10) + 5));
            System.out.println();
        }

        PociagSymulator pociagSymulator = new PociagSymulator(mapaTransportu, ruchPociagow);
        GUI gui = new GUI(mapaTransportu, ruchPociagow);

        Thread sim = new Thread(() -> {
            long now = System.currentTimeMillis();
            long tick = 0;
            while (true) {
                if (System.currentTimeMillis() - now < 1000 / updatesPerSecond) {
                    continue;
                }
                tick++;
                try {
                    pociagSymulator.update(System.currentTimeMillis() - now, tick, updatesPerSecond);
                } catch (RailroadHazard e) {
                    throw new RuntimeException(e);
                }
                gui.repaint();
                now = System.currentTimeMillis();
            }
        });
        sim.start();
    }
}