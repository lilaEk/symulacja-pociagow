import mapa.MapaTransportu;
import mapa.StacjaKolejowa;
import pociag.Pociag;
import sim.PociagSymulator;
import sim.RailroadHazard;
import sim.RuchPociagow;
import swing.GUI;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.Random;

import static pociag.Pociag.zdajRaportWagonow;


public class Main {
    final static MapaTransportu mapaTransportu = new MapaTransportu(StacjaKolejowa.stworzStacje(100));
    final static RuchPociagow ruchPociagow = new RuchPociagow(mapaTransportu);
    final static int updatesPerSecond = 60;

    public static void main(String[] args) throws InterruptedException {

        for (int i = 0; i < 25; i++) {
            Pociag.generujLosowyPociag(mapaTransportu, new Random().nextInt(10) + 5);
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

        Thread statsSave = new Thread(() -> {
            long now = System.currentTimeMillis();
            while (true) {
                if (System.currentTimeMillis() - now < 5000) {
                    continue;
                }
                dodajDoPlikuStatystyki();
                now = System.currentTimeMillis();
            }
        });
        statsSave.start();
    }

    public static void dodajDoPlikuStatystyki() {
        String wpisz = "\nAKTUALIZACJA\n\n " + new Date() + "\n";

        StringBuilder sb = new StringBuilder(wpisz);

        for (Pociag pociag : Pociag.pociagi) {
            sb.append(pociag.getNazwaPociagu());
            sb.append(zdajRaportWagonow(pociag));
            sb.append("\n\n");
        }

        FileWriter fw = null;
        try {
            File file = new File("AppState.txt");
            fw = new FileWriter(file, true);
            String str = sb.toString();
            fw.write(str);
            fw.flush();
            fw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}