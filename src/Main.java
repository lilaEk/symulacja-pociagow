import java.awt.*;
import java.awt.event.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Random;

//Napisz aplikację, która będzie służyła do symulacji i obsługi logistycznej linii kolejowych z
//uwzględnieniem stacji kolejowych, połączeń, skrzyżowań linii oraz różnych składów pociągów.

//Należy stworzyć wszystkie potrzebne klasy oraz uzupełnić każdą klasę przynajmniej dwoma
//dodatkowymi polami i metodami o tematyce odpowiadającej klasie (nie metody typu get i set).

//W ramach aplikacji musimy mieć możliwość z poziomu menu (poza innymi niżej opisanymi
//funkcjonalnościami) stworzenia nowej lokomotywy, wagonów, stacji kolejowych oraz połączeń
//między stacjami oraz możliwość wykonania zadań takich jak przypisanie wagonu do lokomotywy,
//załadowanie osób/ładunku do wagonów itp. Należy uwzględnić również usuwanie obiektów.

public class Main
        extends Frame {

    final static int iloscStacji = 100;

    public static void main(String[] args) {

        new GUI();


    }
}