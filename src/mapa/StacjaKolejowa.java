package mapa;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

//bo kolekcje


public class StacjaKolejowa {

    private String nazwaStacji;
    private int nrStacji;
    private static int counter = 1;
    private int X, Y;


    public StacjaKolejowa( int x, int y) {
        this.nazwaStacji = generateNazwaStacji();
        this.nrStacji = counter;
        counter++;

        this.X = x;
        this.Y = y;
    }

    public static ArrayList<StacjaKolejowa> stworzStacje(int iloscStacji) {

        ArrayList<StacjaKolejowa> listaStacji = new ArrayList<>(iloscStacji);

        for (int i = 0; i < iloscStacji; i++) {
            int X = (int) (Math.random() * 845);
            int Y = (int) (Math.random() * 700);
            listaStacji.add(new StacjaKolejowa(X,Y));
        }

        return listaStacji;
    }

    private static String generateNazwaStacji() {
        String tmpNumer;
        if (counter == 100) {
            tmpNumer = String.valueOf(counter);
        } else if (counter > 9) {
            tmpNumer = '0' + String.valueOf(counter);
        } else {
            tmpNumer = "00" + String.valueOf(counter);
        }

        String nazwaStacji = stworzPrzedrostek() + tmpNumer;
        return nazwaStacji;
    }

    public static String stworzPrzedrostek() {

        StringBuilder przedrostek = new StringBuilder();

        Random rand = new Random();
        char pierwszyZnak = (((char) (rand.nextInt(26) + 65)));
        char drugiZnak = (((char) (rand.nextInt(26) + 65)));
        char trzeciZnak = (((char) (rand.nextInt(26) + 65)));

        przedrostek.append(pierwszyZnak);
        przedrostek.append(drugiZnak);
        przedrostek.append(trzeciZnak);

        return przedrostek.toString();

        //26 letters of the English alphabet, the ASCII codes for uppercase letters start at 65, with 'A', and end at 90, with 'Z
        //Adding 97 to this value shifts the range to the ASCII character codes for lowercase letters, which start at 97.
        //integer between 97 (ASCII code for 'a') and 122 (ASCII code for 'z')

    }

    public static int getLiczbaStacji() {
        return counter;
    }

    public void wyswietlMojeStacje(ArrayList<StacjaKolejowa> list) {
        for (StacjaKolejowa stacja : list) {
            System.out.println(stacja);
        }
    }

    public String getNazwaStacji() {
        return nazwaStacji;
    }

    public int getNrStacji() {
        return nrStacji;
    }

    public static Image dostarczZdjecieStacji()  {
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File("assets/train_station_icon.png"));
        } catch (IOException ex) {
            System.out.println("Nieprawidłowe zdjęcie");
        }
        return image;
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

    public void drawStacja(Graphics g) {
        int wysokosc = 20;
        int szerokosc = 15;
        ImageObserver io = new ImageObserver() {
            @Override
            public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
                return false;
            }
        };
        g.drawImage(dostarczZdjecieStacji(), this.X - szerokosc / 2, this.Y - wysokosc, szerokosc, wysokosc, io);
    }

    public void drawTrasa(Graphics g, StacjaKolejowa sd) {
        g.drawLine(this.X, this.Y, sd.X, sd.Y);
    }
};