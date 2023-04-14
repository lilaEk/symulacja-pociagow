import javax.swing.*;
import java.awt.*;
import java.util.Set;

public class MapaPanel extends JPanel {

    private final MapaTransportu mapaTransportu;

    public MapaPanel(MapaTransportu mapaTransportu, int mapaWight, int canvasHeight) {
        this.mapaTransportu = mapaTransportu;

        this.setSize(mapaWight, canvasHeight);
        this.setLocation(0, 0);
        this.setLayout(null);
        this.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1,Color.black));

        Color c1 = new Color(0xFDDCBA);
        this.setBackground(c1);

        this.setVisible(true);
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
            for (StacjaKolejowa sd : stacjeDocelowe){
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
