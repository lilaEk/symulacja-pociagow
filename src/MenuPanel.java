import javax.swing.*;
import java.awt.*;

public class MenuPanel extends JPanel {
    public MenuPanel(int menuWidth, int canvasHeight, int canvasWidth) {

        this.setSize(menuWidth, canvasHeight);
        this.setLocation(canvasWidth-menuWidth, 0);
        this.setLayout(null);
        this.setBorder(BorderFactory.createLineBorder(Color.black));

        Color c2 = new Color(0xF6A2AF);
        this.setBackground(c2);

        this.setVisible(true);
    }
}
