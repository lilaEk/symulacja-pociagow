import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.awt.Paint; //????????
import java.awt.Color;
import java.awt.Graphics;

import java.io.IOException;
import java.util.ArrayList;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JButton;

public class GUI extends JFrame {

    final static int canvasWidth = 1200;
    final static int canvasHeight = 700;

    ArrayList<JButton> przyciskiMenu;
    JFrame frame;

    static ArrayList<StacjaKolejowa> stacjaKolejowa = StacjaKolejowa.stworzZestawStacji(Main.iloscStacji);

    public GUI (){

//        this.setVisible(true);
//        this.setSize(canvasWidth, canvasHeight);
//        this.setResizable(false);
//        this.setLayout(null);

        frame = new JFrame();
        frame.setSize(canvasWidth, canvasHeight);
        frame.setLocation(50,50);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setResizable(false);
        frame.setUndecorated(false); // przy true usuwa pasek
        frame.setLayout(null);

        frame.setTitle("Mapa kolejowa");

        frame.setVisible(true);


//        JPanel mapa = new JPanel();
//        {
//            mapa.setSize(850, canvasHeight);
//            mapa.setLocation(0,0);
//            mapa.setLayout(null);
//
//            Color c1 = new Color(0xFDDCBA);
//            mapa.setBackground(c1);
//
//            mapa.setVisible(true);
//        }

        JPanel mapaStacji = null;
        {
            frame.add(mapaStacji = new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    for (StacjaKolejowa sk : StacjaKolejowa.stworzZestawStacji(Main.iloscStacji)) {
                        sk.draw(g);
                    }
                }
            });

            mapaStacji.setSize(850, canvasHeight);
            mapaStacji.setLocation(0, 0);
            mapaStacji.setLayout(null);

            Color c1 = new Color(0xFDDCBA);
            mapaStacji.setBackground(c1);

            mapaStacji.setVisible(true);
        }

        JPanel menu = new JPanel();
        {
            menu.setSize(350, canvasHeight);
            menu.setLocation(851,0);
            menu.setLayout(null);

            Color c2 = new Color(0xF6A2AF);
            menu.setBackground(c2);

            menu.setVisible(true);
        }

        frame.add(menu);


        przyciskiMenu = new ArrayList<>();

        JButton przyciskMenu = new JButton("Menu");{
            frame.add(przyciskMenu);
            przyciskMenu.setBounds(871, 40,309,40);
            przyciskMenu.setBackground(Color.RED);
        }

        przyciskiMenu.add(new JButton("Dodaj stację kolejową"));
        przyciskiMenu.add(new JButton("Dodaj lokomotywę"));
        przyciskiMenu.add(new JButton("Dodaj wagon do lokomotywy"));
        przyciskiMenu.add(new JButton("Stwórz połączenie miedzy stacjami"));
        przyciskiMenu.add(new JButton("Usuń stację kolejową"));
        przyciskiMenu.add(new JButton("Usuń lokomotywę"));
        przyciskiMenu.add(new JButton("Usuń wagon z lokomotywy"));
        przyciskiMenu.add(new JButton("Usuń połączenie między stacjami"));


        int tempY = 100;
        for (JButton b : przyciskiMenu){
            frame.add(b);
            b.setBounds(871, tempY,309,30);
            tempY = tempY+45;
            Color c3 = new Color(0xD98A96);
            b.setBackground(c3);
        }

        this.addWindowListener(
                new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        super.windowClosing(e);
                        System.exit(0);
                    }
                }
        );
    }

    @Override
        public void paint(Graphics g) {
        super.paint(g);

        for (StacjaKolejowa sk : StacjaKolejowa.stworzZestawStacji(Main.iloscStacji)){
            sk.draw(g);
        }
        repaint();
    }

//    public void drawLine(Graphics g){
//        super.paint(g);
//        g.drawLine(800,0,800,700);
//        frame.repaint();
//    }

}