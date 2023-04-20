package swing;

import pociag.Pociag;

import javax.swing.*;
import java.awt.*;


public class RaportPanel extends JPanel {

    private final TextArea raport;
    private String tekstRaportu = "\nRAPORT POCIĄGU\n(wybierz pociąg, którego dane chcesz zobaczyć)\n\n";
    private Pociag pociag;

    public RaportPanel() {
        super(new BorderLayout());
        Color c = new Color(0xFFD1DE);

        this.raport = new TextArea(12, 35);
        raport.setBackground(c);
        raport.setEditable(false);
        raport.setText(tekstRaportu);
        raport.setSize(300, 300);

        this.add(raport);

    }

    public void setPociag(Pociag pociag) {
        this.pociag = pociag;
    }

    public void wyswietlNowyRaport() {
        if (this.pociag == null) return;
        String s = Pociag.zdajRaportPociagu(this.pociag);
        raport.setText(tekstRaportu + s);
        this.repaint();
    }
}
