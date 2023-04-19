package swing;

import pociag.Pociag;

import javax.swing.*;
import java.awt.*;

//import static javax.swing.JScrollPane.*;

public class RaportPanel extends JPanel {

    private static String tekstRaportu = "\nRAPORT POCIĄGU\n(wybierz pociąg, którego dane chcesz zobaczyć)\n\n";
    private TextArea raport;

    public RaportPanel() {

        Color c = new Color(0xFFD1DE);

        this.raport = new TextArea(12, 35);
        raport.setBackground(c);
        raport.setVisible(true);
        raport.setEditable(false);

        raport.setText(tekstRaportu);
//        raport.append(tekstRaportu);

//        JScrollPane scrollPane = new JScrollPane(raport);
////        scrollPane.createVerticalScrollBar();
////        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
////        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
//        scrollPane.setBounds(800, 500, 250, 150);


//        scrollPane.setVisible(true);
        this.add(raport);

    }

    public static String wyswietlNowyRaport(Pociag pociag) {
        String nowyTekst = tekstRaportu + Pociag.zdajRaportPociagu(pociag);
        return nowyTekst;
    }
}
