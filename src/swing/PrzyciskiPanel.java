package swing;


import mapa.MapaTransportu;
import pociag.Pociag;
import sim.RuchPociagow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class PrzyciskiPanel extends JPanel {
    public PrzyciskiPanel(MapaPanel mapaPanel, RuchPociagow ruchPociagow, MapaTransportu mapaTransportu) {

        ArrayList<JButton> przyciskiMenu = new ArrayList<>();

        JButton dodaj_stacje_kolejowa = new JButton("Dodaj stację kolejową");
        dodaj_stacje_kolejowa.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (mapaPanel.getMouseMode() == MouseMode.ADD_STACJE) {
                    mapaPanel.setMouseMode(MouseMode.DEFAULT);
                    System.out.println("Tryb wybierania stacji zamknięty.");
                    System.out.println();
                    return;
                }
                System.out.println("Wybierz, gdzie chcesz umieścić stację.");
                mapaPanel.setMouseMode(MouseMode.ADD_STACJE);
            }
        });

        JButton usun_stacje_kolejowa = new JButton("Usuń stację kolejową");
        usun_stacje_kolejowa.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (mapaPanel.getMouseMode() == MouseMode.USUN_STACJE) {
                    mapaPanel.setMouseMode(MouseMode.DEFAULT);
                    System.out.println("Tryb usuwania stacji zamknięty.");
                    System.out.println();
                    return;
                }
                System.out.println("Wybierz, stację chcesz usunąć.");
                mapaPanel.setMouseMode(MouseMode.USUN_STACJE);
            }
        });

        JButton dodaj_wagon_do_lokomotywy = new JButton("Dodaj wagon do pociągu");
        dodaj_wagon_do_lokomotywy.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                System.out.println("Wybierz lokomotywę, do której chcesz dodać wagon.");
                //MapaPanel.ktoryPociagKliknieto().Pociag.dodajLosowyWagonDoPociagu();

                //.......................
                System.out.println("Dodano wagon o numerze ... do wybranego pociągu."); //todo
            }
        });

        JButton dodaj_pociag = new JButton("Dodaj pociąg");
        dodaj_pociag.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                Pociag pociag = Pociag.generujLosowyPociag(mapaTransportu);
                ruchPociagow.dodajPociag(pociag);
                System.out.println("Dodano pociąg o numerze " + pociag.getNazwaPociagu());
            }
        });

        JButton usun_pociag = new JButton("Usuń pociąg");
        usun_pociag.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                Pociag pociag = Pociag.losujPociag(mapaTransportu);
                ruchPociagow.usunPociag(pociag);
                System.out.println("Dodano pociąg o numerze " + pociag.getNazwaPociagu());
            }
        });

        JButton stworz_polaczenie_miedzy_stacjami = new JButton("Stwórz połączenie miedzy stacjami");
        stworz_polaczenie_miedzy_stacjami.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (mapaPanel.getMouseMode() == MouseMode.ADD_TRASE) {
                    mapaPanel.setMouseMode(MouseMode.DEFAULT);
                    System.out.println("Tryb tworzenia połączenia zamknięty.");
                    System.out.println();
                    return;
                }
                System.out.println("Wybierz dwie stacje do połączenia.");
                mapaPanel.setMouseMode(MouseMode.ADD_TRASE);
            }
        });

        JButton usun_polczenie_miedzy_stacjami = new JButton("Usuń połączenie między stacjami");
        usun_polczenie_miedzy_stacjami.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (mapaPanel.getMouseMode() == MouseMode.USUN_TRASE) {
                    mapaPanel.setMouseMode(MouseMode.DEFAULT);
                    System.out.println("Tryb usuwania połączenia zamknięty.");
                    System.out.println();
                    return;
                }
                System.out.println("Wybierz dwie stacje, między którymi chcesz usunąć trasę.");
                mapaPanel.setMouseMode(MouseMode.USUN_TRASE);
            }
        });


        przyciskiMenu.add(dodaj_stacje_kolejowa);
        przyciskiMenu.add(usun_stacje_kolejowa);

        przyciskiMenu.add(dodaj_pociag);
        przyciskiMenu.add(usun_pociag);

        przyciskiMenu.add(dodaj_wagon_do_lokomotywy);
        przyciskiMenu.add(new JButton("Usuń wagon z lokomotywy"));

        przyciskiMenu.add(stworz_polaczenie_miedzy_stacjami);
        przyciskiMenu.add(usun_polczenie_miedzy_stacjami);

        GridLayout gridLayout = new GridLayout(przyciskiMenu.size() / 2, 1);
        gridLayout.setHgap(10);
        gridLayout.setVgap(10);
        this.setLayout(gridLayout);

        for (JButton b : przyciskiMenu) {
            Color c3 = new Color(0xD98A96);
            b.setBackground(c3);
            this.add(b);
        }
    }
}
