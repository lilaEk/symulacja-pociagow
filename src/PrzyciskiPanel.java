import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class PrzyciskiPanel extends JPanel {
    public PrzyciskiPanel() {

        ArrayList<JButton> przyciskiMenu = new ArrayList<>();
        przyciskiMenu.add(new JButton("Dodaj stację kolejową"));
        przyciskiMenu.add(new JButton("Dodaj lokomotywę"));
        przyciskiMenu.add(new JButton("Dodaj wagon do lokomotywy"));
        przyciskiMenu.add(new JButton("Stwórz połączenie miedzy stacjami"));
        przyciskiMenu.add(new JButton("Usuń stację kolejową"));
        przyciskiMenu.add(new JButton("Usuń lokomotywę"));
        przyciskiMenu.add(new JButton("Usuń wagon z lokomotywy"));
        przyciskiMenu.add(new JButton("Usuń połączenie między stacjami"));

        GridLayout gridLayout = new GridLayout(przyciskiMenu.size() / 2, 1);
        gridLayout.setHgap(10);
        gridLayout.setVgap(10);
        this.setLayout(gridLayout);

        for (JButton b : przyciskiMenu) {
            b.setSize(new Dimension(20, 20));
            Color c3 = new Color(0xD98A96);
            b.setBackground(c3);
            this.add(b );
        }
    }
}
