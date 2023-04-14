package Swing;

import Mapa.MapaTransportu;
import Mapa.StacjaKolejowa;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.Set;

public class MapaPanel extends JPanel {

    private MouseMode MouseMode;
    private final MapaTransportu mapaTransportu;

    public MapaPanel(GUI gui, MapaTransportu mapaTransportu, int mapaWight, int canvasHeight) {
        this.mapaTransportu = mapaTransportu;

        this.setSize(mapaWight, canvasHeight);
        this.setLocation(0, 0);
        this.setLayout(null);
        this.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.black));
        Color c1 = new Color(0xFDDCBA);
        this.setBackground(c1);
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (isMouseMode(Swing.MouseMode.ADD_STACJE)) {
                    super.mouseClicked(e);
                    System.out.println(e.getX() + " " + e.getY());
                    StacjaKolejowa nowaStacja = new StacjaKolejowa(e.getX(), e.getY());
                    GUI.mapaTransportu.addStacja(nowaStacja);
                    setMouseMode(MouseMode.DEFAULT);
                    gui.repaint();
                    System.out.println("dodano stacje:" + nowaStacja);
                }
            }
        });
        this.setVisible(true);
    }

    private boolean isMouseMode(MouseMode mode) {
        return mode == this.MouseMode;
    }

    public void setMouseMode(MouseMode addStacje) {
        MouseMode = addStacje;
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        switch (addStacje) {
            case ADD_STACJE:
                Image image = StacjaKolejowa.dostarczZdjecieStacji();
                Cursor c = toolkit.createCustomCursor(image, new Point(0,0), "img");
                this.setCursor(c);
                break;
            case DEFAULT:
                setCursor(Cursor.getDefaultCursor());
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        rysujPolaczenia(g, mapaTransportu);
        rysujStacje(g, mapaTransportu);
    }

    private void rysujPolaczenia(Graphics g, MapaTransportu mapaTransportu) {
        for (StacjaKolejowa sk : mapaTransportu.getListStacjeKolejowe()) {
            Set<StacjaKolejowa> stacjeDocelowe = mapaTransportu.getStacjeDocelowe(sk);
            for (StacjaKolejowa sd : stacjeDocelowe) {
                sk.drawTrasa(g, sd);
            }
        }
    }

    private void rysujStacje(Graphics g, MapaTransportu mapaTransportu) {

        for (StacjaKolejowa sk : mapaTransportu.getListStacjeKolejowe()) {
            sk.drawStacja(g);
        }
    }
}
