package swing;

import mapa.MapaTransportu;
import mapa.StacjaKolejowa;
import pociag.Pociag;
import sim.RuchPociagow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Set;

public class MapaPanel extends JPanel {

    private MouseMode MouseMode;
    private final GUI gui;
    private StacjaKolejowa[] wybranaStacja;

    public MapaPanel(GUI gui, int mapaWight, int canvasHeight) {
        this.gui = gui;

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
                } else if (isMouseMode(swing.MouseMode.ADD_TRASE)) {
                    dodajTraseNaMapie(e, gui);
                } else if (isMouseMode(swing.MouseMode.DEFAULT)) {
                    if (czyKliknietoWPociag(e)) {
                        wyswietlRaportDlaPociagu(ktoryPociagKliknieto(e));
                        System.out.println("Kliknięto");
                    }
                    System.out.println("kliknieto na mape ale nie w punkt");
                }
            }
        });
        this.setVisible(true);
    }

    private boolean czyKliknietoWPociag(MouseEvent e) {
        int odleglosc = 8;
        for (Pociag p : Pociag.getPociagi()) {
            double dystans = Math.sqrt(Math.pow(e.getX() - p.szukajAktualnegoXPociagu(), 2) + (Math.pow(e.getY() - p.szukajAktualnegoYPociagu(), 2)));
            if (dystans < odleglosc) return true;
        }
        return false;
    }

    private Pociag ktoryPociagKliknieto(MouseEvent e) {

        int odleglosc = 8;
        for (Pociag p : Pociag.getPociagi()) {
            double dystans = Math.sqrt(Math.pow(e.getX() - p.szukajAktualnegoXPociagu(), 2) + (Math.pow(e.getY() - p.szukajAktualnegoYPociagu(), 2)));
            if (dystans < odleglosc) return p;
        }
        System.out.println("Nie klknieto pociagu");
        return null;
    }

    private void wyswietlRaportDlaPociagu(Pociag pociag) {
        RaportPanel.wyswietlNowyRaport(pociag);
    }

    private void dodajTraseNaMapie(MouseEvent e, GUI gui) {

        //nie zabezpiecza przed dodaniem ponownie tego samego polaczenia
        //wyrzuca bladprzy klikieciu dwa razy jednej stacji

        for (StacjaKolejowa sk : gui.mapaTransportu.getListStacjeKolejowe()) {

            if (sk.contains(e.getPoint())) {
                System.out.println("Kliknięto stację: " + sk);
                if (this.wybranaStacja == null) {
                    this.wybranaStacja = new StacjaKolejowa[2];
                    this.wybranaStacja[0] = sk;
                    return;
                }

                if (this.wybranaStacja[0] == sk) {
                    System.out.println("Nie można stworzyć połączenia stacji ze sobą.");
                    return;
                }

                this.wybranaStacja[1] = sk;

                if (gui.mapaTransportu.dodajTrase(this.wybranaStacja)) {
                    System.out.println("Dodano połączenie między stacją " + this.wybranaStacja[0].getNazwaStacji() + ", a " + this.wybranaStacja[1].getNazwaStacji());
                } else System.out.println("Połączenie już istnieje.");
                System.out.println();
                this.wybranaStacja = null;
                setMouseMode(swing.MouseMode.DEFAULT);

                gui.repaint();
            }
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
            case USUN_STACJE -> {
                Image image = StacjaKolejowa.dostarczZdjecieUsunietejStacji();
                Cursor c = toolkit.createCustomCursor(image, new Point(0, 0), "img");
                this.setCursor(c);
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
        rysujPolaczenia(g, gui.mapaTransportu);
        rysujStacje(g, gui.mapaTransportu);
        rysujPociagi(g, gui.mapaTransportu, gui.ruchPociagow);
    }

    private void rysujPociagi(Graphics g, MapaTransportu mapaTransportu, RuchPociagow ruchPociagow) {
        for (Pociag pociag : ruchPociagow.getPociagi()) {
            pociag.draw(g);
        }
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
