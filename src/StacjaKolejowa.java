import java.awt.*;
import java.util.*;

//bo kolekcje
import java.util.Comparator;
import java.io.Serializable;
import java.lang.Iterable;


public class StacjaKolejowa {

    private String nazwaStacji;
    private int nrStacji;
    private static int counter = 1; //jak działa static tutaj?
    private int X,Y;

    int tempSize = 400; //do usuniecia


    public StacjaKolejowa(){}
    public StacjaKolejowa (String nazwaStacji){
        this.nazwaStacji = nazwaStacji;
        this.nrStacji = counter;
        counter++;

        this.X = setX();
        this.Y = setY();
    }

    public static ArrayList<StacjaKolejowa> stworzZestawStacji(int iloscStacji){

        ArrayList<StacjaKolejowa> listaStacji = new ArrayList<>(iloscStacji);

        for (int i=0; i<iloscStacji; i++){

            String tmpNumer;
            if (counter==100) {
                tmpNumer = String.valueOf(counter);
            } else if (counter>9) {
                tmpNumer = '0'+String.valueOf(counter);
            } else {
                tmpNumer = "00"+String.valueOf(counter);
            }

            String nazwaStacji = stworzPrzedrostek()+tmpNumer;
            listaStacji.add(new StacjaKolejowa(nazwaStacji));
        }

        return listaStacji;
    }

    public static String stworzPrzedrostek(){

        StringBuilder przedrostek = new StringBuilder();

        Random rand = new Random();
        char pierwszyZnak = (((char)(rand.nextInt(26)+65)));
        char drugiZnak = (((char)(rand.nextInt(26)+65)));
        char trzeciZnak = (((char)(rand.nextInt(26)+65)));

        przedrostek.append(pierwszyZnak);
        przedrostek.append(drugiZnak);
        przedrostek.append(trzeciZnak);
        przedrostek.append(" ");

        return przedrostek.toString();

        //26 letters of the English alphabet, the ASCII codes for uppercase letters start at 65, with 'A', and end at 90, with 'Z
        //Adding 97 to this value shifts the range to the ASCII character codes for lowercase letters, which start at 97.
        //integer between 97 (ASCII code for 'a') and 122 (ASCII code for 'z')

    }

    public void wyswietlMojeStacje(ArrayList<StacjaKolejowa> list){
        for (StacjaKolejowa stacja : list){
            System.out.println(stacja);
        }
    }

    public int setX(){
        int x = (int)(Math.random()*tempSize);
        return x;
    }

    public int setY(){
        int y = (int)(Math.random()*tempSize);
        return y;
    }

    public String getNazwaStacji() {
        return nazwaStacji;
    }

    public int getNrStacji() {
        return nrStacji;
    }

    public int getX() {
        return X;
    }

    public int getY() {
        return Y;
    }

    @Override
    public String toString() {
        return "Stacja Kolejowa " + nazwaStacji + " o numerze " + nrStacji + ".";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StacjaKolejowa that = (StacjaKolejowa) o;
        return nrStacji == that.nrStacji && Objects.equals(nazwaStacji, that.nazwaStacji);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nazwaStacji, nrStacji);
    }

    public void draw(Graphics g){
        g.setColor(Color.PINK);
        g.drawRect(this.setX(),this.getY(), 5,5);
    };
}