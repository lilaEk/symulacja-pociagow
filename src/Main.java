import mapa.MapaTransportu;
import mapa.StacjaKolejowa;
import pociag.Pociag;
import sim.PociagSymulator;
import sim.RuchPociagow;
import swing.GUI;


public class Main {
    final static MapaTransportu mapaTransportu = new MapaTransportu(StacjaKolejowa.stworzStacje(20));
    final static RuchPociagow ruchPociagow = new RuchPociagow(mapaTransportu);
    final static int updatesPerSecond = 60;

    public static void main(String[] args) throws InterruptedException {

        ruchPociagow.dodajPociag(Pociag.generujLosowyPociag(mapaTransportu));

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
                pociagSymulator.update(System.currentTimeMillis() - now, tick, updatesPerSecond);
                gui.repaint();
                now = System.currentTimeMillis();
            }
        });
        sim.start();
    }
}