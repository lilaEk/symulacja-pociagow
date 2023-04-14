package Swing;

import Mapa.StacjaKolejowa;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class PrzyciskiPanel extends JPanel {
    public PrzyciskiPanel(GUI gui) {

        ArrayList<JButton> przyciskiMenu = new ArrayList<>();
        JButton dodaj_stacje_kolejowa = new JButton("Dodaj stację kolejową");
        dodaj_stacje_kolejowa.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                StacjaKolejowa nowaStacja = StacjaKolejowa.stworzStacje(1).get(0);
                GUI.mapaTransportu.addStacja(nowaStacja);
                gui.repaint();
                System.out.println("dodano stacje:" + nowaStacja);
            }
        });
        przyciskiMenu.add(dodaj_stacje_kolejowa);
        przyciskiMenu.add(new JButton("Dodaj lokomotywę"));
        przyciskiMenu.add(new JButton("Dodaj wagon do lokomotywy"));
        przyciskiMenu.add(new JButton("Stwórz połączenie miedzy stacjami"));
        przyciskiMenu.add(new JButton("Usuń stację kolejową"));
        przyciskiMenu.add(new JButton("Usuń lokomotywę"));
        przyciskiMenu.add(new JButton("Usuń wagon z lokomotywy"));
        przyciskiMenu.add(new JButton("Usuń połączenie między stacjami"));

        GridLayout gridLayout = new GridLayout(przyciskiMenu.size() / 2, 1);
        gridLayout.setHgap(10);
        gridLayout.setVgap(10);
        this.setLayout(gridLayout);

        for (JButton b : przyciskiMenu) {
            b.setSize(new Dimension(20, 20));
            Color c3 = new Color(0xD98A96);
            b.setBackground(c3);
            this.add(b);
        }
    }
}
