package Swing;

import Mapa.MapaTransportu;
import Swing.MapaPanel;
import Swing.MenuPanel;
import Swing.PrzyciskiPanel;
import Swing.RaportPanel;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;

public class GUI extends JFrame {

    final static int canvasWidth = 1200;
    final static int canvasHeight = 700;
    final static int mapaWight = 850;
    final static int menuWight = canvasWidth - mapaWight;
    static MapaTransportu mapaTransportu;

    public GUI(MapaTransportu mapaTransportu) {
        this.mapaTransportu = mapaTransportu;

        this.setSize(canvasWidth, canvasHeight);
        this.setLocation(50, 50);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(true);
        this.setUndecorated(false); // przy true usuwa pasek
        this.setLayout(null);
        this.setTitle("Mapa kolejowa");

        this.add(new MapaPanel(this.mapaTransportu, mapaWight, canvasHeight));
        PrzyciskiPanel przyciskiPanel = new PrzyciskiPanel(this);
        RaportPanel raportPanel = new RaportPanel();

        MenuPanel menuPanel = new MenuPanel( przyciskiPanel, raportPanel, menuWight, canvasHeight, canvasWidth);


//        menuPanel.setLayout(new BorderLayout());
//        menuPanel.add(przyciskiPanel,BorderLayout.PAGE_START);
//        menuPanel.add(raportPanel,BorderLayout.PAGE_END);

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
}