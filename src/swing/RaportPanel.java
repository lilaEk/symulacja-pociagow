package swing;

import pociag.Pociag;

import javax.swing.*;
import java.awt.*;

//import static javax.swing.JScrollPane.*;

public class RaportPanel extends JPanel {

    private final TextArea raport;
    private String tekstRaportu = "\nRAPORT POCIĄGU\n(wybierz pociąg, którego dane chcesz zobaczyć)\n\n";

    public RaportPanel() {

        Color c = new Color(0xFFD1DE);

        this.raport = new TextArea(12, 35);
        raport.setBackground(c);
        raport.setEditable(false);
        raport.setText(tekstRaportu);
        raport.setSize(300, 300);

        this.add(raport);

    }

    public void wyswietlNowyRaport(Pociag pociag) {
        String s = Pociag.zdajRaportPociagu(pociag);

        raport.setText(tekstRaportu + s);
        this.repaint();
    }
}
