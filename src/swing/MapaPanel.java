package swing;

import mapa.MapaTransportu;
import mapa.StacjaKolejowa;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Set;

public class MapaPanel extends JPanel {

    private MouseMode MouseMode;
    private final MapaTransportu mapaTransportu;
    private StacjaKolejowa[] wybranaStacja;

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
                super.mouseClicked(e);
                // todo zamienić na switch
                if (isMouseMode(swing.MouseMode.ADD_STACJE)) {
                    dodajStacjeNaMapie(e, gui);
                }
                if (isMouseMode(swing.MouseMode.ADD_TRASE)) {
                    dodajTraseNaMapie(e, gui);
                }

            }
        });
        this.setVisible(true);
    }

    private void dodajTraseNaMapie(MouseEvent e, GUI gui) {
        for (StacjaKolejowa sk : mapaTransportu.getListStacjeKolejowe())
            if (sk.contains(e.getPoint())) {
                System.out.println("Kliknięto stację: " + sk);
                if (this.wybranaStacja == null) {
                    this.wybranaStacja = new StacjaKolejowa[2];
                    this.wybranaStacja[0] = sk;
                    return;
                }
                if (this.wybranaStacja[0] != sk) this.wybranaStacja[1] = sk;

                if (this.mapaTransportu.dodajTrase(this.wybranaStacja)) {
                    System.out.println("Dodano połączenie między stacją " + this.wybranaStacja[0].getNazwaStacji() + ", a " + this.wybranaStacja[1].getNazwaStacji());
                } else System.out.println("Połączenie już istnieje.");
                System.out.println();
                this.wybranaStacja = null;
                setMouseMode(swing.MouseMode.DEFAULT);

                gui.repaint();
            }
    }

    private void dodajStacjeNaMapie(MouseEvent e, GUI gui) {
        System.out.println("Wybrano współrzędne " + e.getX() + " " + e.getY());
        StacjaKolejowa nowaStacja = new StacjaKolejowa(e.getX(), e.getY());
        gui.getMapaTransportu().addStacja(nowaStacja);
        setMouseMode(swing.MouseMode.DEFAULT);
        System.out.println("Dodano stację: " + nowaStacja);
        System.out.println();
        gui.repaint();
    }

    private boolean isMouseMode(MouseMode mode) {
        return mode == this.MouseMode;
    }

    public void setMouseMode(MouseMode addStacje) {
        MouseMode = addStacje;
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        switch (addStacje) {
            case ADD_STACJE -> {
                Image image = StacjaKolejowa.dostarczZdjecieStacji();
                Cursor c = toolkit.createCustomCursor(image, new Point(0, 0), "img");
                this.setCursor(c);
            }
            case ADD_TRASE -> {
                repaint();
            }
            case DEFAULT -> {
                setCursor(Cursor.getDefaultCursor());
                repaint();
            }
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
            sk.drawStacja(g, getMouseMode());
        }
    }

    public swing.MouseMode getMouseMode() {
        return this.MouseMode;
    }
}
