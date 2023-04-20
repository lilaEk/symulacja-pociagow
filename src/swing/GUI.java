package swing;

import mapa.MapaTransportu;
import symulacja.RuchPociagow;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GUI extends JFrame {

    final static int canvasWidth = 1200;
    final static int canvasHeight = 700;
    final static int mapaWight = 850;
    final static int menuWight = canvasWidth - mapaWight;
    final MapaTransportu mapaTransportu;
    final RuchPociagow ruchPociagow;
    final RaportPanel raportPanel;


    public GUI(MapaTransportu mapaTransportu, RuchPociagow ruchPociagow) {
        this.mapaTransportu = mapaTransportu;
        this.ruchPociagow = ruchPociagow;

        this.setSize(canvasWidth, canvasHeight);
        this.setLocation(50, 50);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(true);
        this.setUndecorated(false);
        this.setLayout(null);
        this.setTitle("Mapa kolejowa");

        MapaPanel mapaPanel = new MapaPanel(this, mapaWight, canvasHeight);
        this.add(mapaPanel);
        PrzyciskiPanel przyciskiPanel = new PrzyciskiPanel(mapaPanel, ruchPociagow, mapaTransportu);
        this.raportPanel = new RaportPanel();
        MenuPanel menuPanel = new MenuPanel(przyciskiPanel, this.raportPanel, menuWight, canvasHeight, canvasWidth);

        this.add(menuPanel);

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                System.exit(0);
            }
        });
        this.setVisible(true);
    }

    public MapaTransportu getMapaTransportu() {
        return mapaTransportu;
    }

    public RuchPociagow getRuchPociagow() {
        return ruchPociagow;
    }

}