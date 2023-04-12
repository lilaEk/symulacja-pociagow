import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class GUI extends Frame {

    final static int canvasWidth = 800;
    final static int canvasHeight = 800;

    static ArrayList<StacjaKolejowa> stacjaKolejowa = StacjaKolejowa.stworzZestawStacji(Main.iloscStacji);

    public GUI (){

        this.setSize(canvasWidth, canvasHeight);
        this.setResizable(true);
        this.setUndecorated(false); // przy true usuwa pasek
        this.setVisible(true);

        this.addWindowListener(
                new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        super.windowClosing(e);
                        System.exit(0);
                    }
                }
        );
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        for (StacjaKolejowa sk : stacjaKolejowa){
            sk.draw(g);
        }
    }


}
