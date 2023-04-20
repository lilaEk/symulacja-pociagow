package wagony.typWagonu;

import wagony.Wagon;
import wagony.cechyWagonu.DostepDoSieciElektrycznej;
import wagony.cechyWagonu.DostepDoWifi;
import wagony.cechyWagonu.RodzajDostepnejKuchni;
import wagony.cechyWagonu.RodzajZabezpieczen;

import java.util.ArrayList;
import java.util.Random;

public class Restauracyjny

        extends Wagon implements DostepDoSieciElektrycznej, DostepDoWifi {

    private final static int iloscStolikow = 15;
    private final static int maxIloscGosci = 80;
    private int obecnaIloscGosci;
    private RodzajDostepnejKuchni rodzajKuchni;
    private int wagaDodatkowaWagonu = new Random().nextInt(3);
    private int calkowitaWagaWagonu;


    public Restauracyjny() {
        super();
        this.calkowitaWagaWagonu = super.podstawowaWagaWagonu + this.wagaDodatkowaWagonu;
        this.rodzajKuchni = wybierzRodzajKuchni(new Random().nextInt(7));

        super.rodzajZabezpieczenWagonu = new ArrayList<>();
        rodzajZabezpieczenWagonu.add(RodzajZabezpieczen.KAMERY_MONITORINGU);
        rodzajZabezpieczenWagonu.add(RodzajZabezpieczen.ZABEZPIECZENIA_PRZECIWPOZAROWE);
        rodzajZabezpieczenWagonu.add(RodzajZabezpieczen.HAMULEC_RECZNY);

        this.obecnaIloscGosci = new Random().nextInt(100);
        if (obecnaIloscGosci > maxIloscGosci)
            System.out.println(("Za dużo gości w wagonie " + nrIdentyfikacyjnyWagonu));
    }

    public RodzajDostepnejKuchni wybierzRodzajKuchni(int number) {
        switch (number) {
            case 0:
                return RodzajDostepnejKuchni.KUCHNIA_WLOSKA;
            case 1:
                return RodzajDostepnejKuchni.KUCHNIA_POLSKA;
            case 2:
                return RodzajDostepnejKuchni.KUCHNIA_CHINSKA;
            case 3:
                return RodzajDostepnejKuchni.KUCHNIA_INDYJSKA;
            case 4:
                return RodzajDostepnejKuchni.KUCHNIA_JAPONSKA;
            case 5:
                return RodzajDostepnejKuchni.KUCHNIA_FRANCUSKA;
            case 6:
                return RodzajDostepnejKuchni.KUCHNIA_AMERYKANSKA;
            default:
                return RodzajDostepnejKuchni.BRAK_INFORMACJI;
        }
    }

    @Override
    public int getCalkowitaWagaWagonu() {
        return calkowitaWagaWagonu;
    }

    @Override
    public String toString() {
        return "Wagon restauracyjny o numerze " + nrIdentyfikacyjnyWagonu + ". Całkowita waga wagonu wynosi " + calkowitaWagaWagonu +
                ". Wagon serwuje kuchnię: " + String.valueOf(rodzajKuchni) + ". Aktualnie w wagonie przebywa " + obecnaIloscGosci + "." +
                " Wagon posiada zabezpieczenia: " + rodzajZabezpieczenWagonu;
    }

    @Override
    public void podlaczDoGniazdka() {
        System.out.println("Podłączono urządzenie do prądu.");
    }

    @Override
    public void udostepnijWifi() {
        System.out.println("Goście mogą swobodnie korzystać z WIFI.");
    }
}