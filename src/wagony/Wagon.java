package wagony;

import pociag.Pociag;
import wagony.cechyWagonu.RodzajZabezpieczen;
import wagony.typWagonu.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class Wagon {

    protected final int podstawowaWagaWagonu = 30; //ton
    protected int nrIdentyfikacyjnyWagonu;
    protected static int counter = 1;

    protected ArrayList<RodzajZabezpieczen> rodzajZabezpieczenWagonu;

    protected int calkowitaWagaWagonu;

    public static List<Wagon> stworzZestawWagonow(int iloscWagonow) {
        List<Wagon> listaWagonow = new ArrayList<>();

        try {
            int waga = 0;
            for (int i = 0; i < iloscWagonow; i++) {
                Wagon losowy = dodajLosowyWagonDoPociagu();
                if (waga + losowy.getCalkowitaWagaWagonu() > Pociag.getMaxUciag()) {
                    throw new Exception("Wagon przekroczy dozwoloną wagę.");
                }
                listaWagonow.add(losowy);
                waga = waga + losowy.getCalkowitaWagaWagonu();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return listaWagonow;
    }


    public Wagon() {
        this.nrIdentyfikacyjnyWagonu = counter;
        counter++;
    }

    public static Wagon dodajLosowyWagonDoPociagu() {
        int losowa = new Random().nextInt(13);
        Wagon nowoPodlaczanyWagon;
        switch (losowa) {
            case 0:
                return nowoPodlaczanyWagon = new BagazowoPocztowy();
            case 1:
                return nowoPodlaczanyWagon = new Chlodniczy();
            case 2:
                return nowoPodlaczanyWagon = new Pasazerski();
            case 3:
                return nowoPodlaczanyWagon = new Pocztowy();
            case 4:
                return nowoPodlaczanyWagon = new Restauracyjny();
            case 5:
                return nowoPodlaczanyWagon = new TowarowyCiezki();
            case 6:
                return nowoPodlaczanyWagon = new TCMaterialyToksyczne();
            case 7:
                return nowoPodlaczanyWagon = new TCMaterialyWybuchowe();
            case 8:
                return nowoPodlaczanyWagon = new TCMaterialyWybuchoweCiekle();
            case 9:
                return nowoPodlaczanyWagon = new TowarowyPodstawowy();
            case 10:
                return nowoPodlaczanyWagon = new TPChlodniczy();
            case 11:
                return nowoPodlaczanyWagon = new TPMaterialyCiekle();
            case 12:
                return nowoPodlaczanyWagon = new TPMaterialyGazowe();
        }
        return null;
    }

    public static void dodajLosowyWagonDoPociagu(Pociag p) {
        int losowa = new Random().nextInt(12);
        Wagon nowoPodlaczanyWagon;
        switch (losowa) {
            case 0:
                nowoPodlaczanyWagon = new BagazowoPocztowy();
                break;
            case 1:
                nowoPodlaczanyWagon = new Chlodniczy();
                break;
            case 2:
                nowoPodlaczanyWagon = new Pasazerski();
                break;
            case 3:
                nowoPodlaczanyWagon = new Pocztowy();
                break;
            case 4:
                nowoPodlaczanyWagon = new Restauracyjny();
                break;
            case 5:
                nowoPodlaczanyWagon = new TowarowyCiezki();
                break;
            case 6:
                nowoPodlaczanyWagon = new TCMaterialyToksyczne();
                break;
            case 7:
                nowoPodlaczanyWagon = new TCMaterialyWybuchowe();
                break;
            case 8:
                nowoPodlaczanyWagon = new TCMaterialyWybuchoweCiekle();
                break;
            case 9:
                nowoPodlaczanyWagon = new TowarowyPodstawowy();
                break;
            case 10:
                nowoPodlaczanyWagon = new TPChlodniczy();
                break;
            case 11:
                nowoPodlaczanyWagon = new TPMaterialyCiekle();
                break;
            case 12:
                nowoPodlaczanyWagon = new TPMaterialyGazowe();
                break;
            default:
                nowoPodlaczanyWagon = new Pasazerski();
        }
        p.addWagon(nowoPodlaczanyWagon);
    }

    public int getCalkowitaWagaWagonu() {
        return calkowitaWagaWagonu;
    }


}
