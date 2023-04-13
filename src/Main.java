import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


//Napisz aplikację, która będzie służyła do symulacji i obsługi logistycznej linii kolejowych z
//uwzględnieniem stacji kolejowych, połączeń, skrzyżowań linii oraz różnych składów pociągów.

//Należy stworzyć wszystkie potrzebne klasy oraz uzupełnić każdą klasę przynajmniej dwoma
//dodatkowymi polami i metodami o tematyce odpowiadającej klasie (nie metody typu get i set).

//W ramach aplikacji musimy mieć możliwość z poziomu menu (poza innymi niżej opisanymi
//funkcjonalnościami) stworzenia nowej lokomotywy, wagonów, stacji kolejowych oraz połączeń
//między stacjami oraz możliwość wykonania zadań takich jak przypisanie wagonu do lokomotywy,
//załadowanie osób/ładunku do wagonów itp. Należy uwzględnić również usuwanie obiektów.

public class Main {

    final static int iloscStacji = 100;

    public static void main(String[] args) throws InterruptedException {

        new StacjaKolejowa();

        new GUI();

//        if (SwingUtilities.isEventDispatchThread()) {
//            // Do UI-related work here
//            System.out.println(true);
//        } else {
//            // Offload the work to the EDT using SwingUtilities.invokeLater()
//            SwingUtilities.invokeLater(() -> {
//                System.out.println(false);
//                // Do UI-related work here
//            });
//        }

    }
}