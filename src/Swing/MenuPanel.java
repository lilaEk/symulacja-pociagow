package Swing;

import javax.swing.*;
import java.awt.*;

public class MenuPanel extends JPanel {

    public MenuPanel(PrzyciskiPanel przyciskiPanel, RaportPanel raportPanel, int menuWidth, int canvasHeight, int canvasWidth) {
        this.setSize(menuWidth, canvasHeight);
        this.setLocation(canvasWidth - menuWidth, 0);

        this.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 0, Color.black));

        Color c2 = new Color(0xF6A2AF);
        przyciskiPanel.setBackground(c2);

        this.setLayout(new BorderLayout());

        przyciskiPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.black));
        przyciskiPanel.setPreferredSize(new Dimension(menuWidth, 450));
        this.add(przyciskiPanel, BorderLayout.PAGE_START);
        this.add(raportPanel, BorderLayout.CENTER);

    }
}
