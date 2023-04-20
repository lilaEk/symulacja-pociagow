package wagony.typWagonu;

import wagony.Wagon;
import wagony.cechyWagonu.DostepDoSieciElektrycznej;
import wagony.cechyWagonu.RodzajPrzewozonegoTowaru;
import wagony.cechyWagonu.RodzajZabezpieczen;

import java.util.ArrayList;
import java.util.Random;

public class Chlodniczy

        extends Wagon
        implements DostepDoSieciElektrycznej {

    private int wagaDodatkowaWagonu = new Random().nextInt(5)+2;
    private int pozadanaTemperatura;
    private RodzajPrzewozonegoTowaru rodzajPrzewozonegoTowaru;
    private int calkowitaWagaWagonu;


    public Chlodniczy() {
        super();

        this.calkowitaWagaWagonu = super.podstawowaWagaWagonu + this.wagaDodatkowaWagonu;

        super.rodzajZabezpieczenWagonu = new ArrayList<>();
        rodzajZabezpieczenWagonu.add(RodzajZabezpieczen.ZABEZPIECZENIA_PRZECIWPOZAROWE);
        rodzajZabezpieczenWagonu.add(RodzajZabezpieczen.SYSTEM_KONTROLI_DOSTEPU);
        rodzajZabezpieczenWagonu.add(RodzajZabezpieczen.MONITORING_TEMPERATURY);

        this.rodzajPrzewozonegoTowaru = dodajPrzewozonyTowar(new Random().nextInt(3));
        this.pozadanaTemperatura = ustawPozodanaTemperatura(rodzajPrzewozonegoTowaru);
    }

    public RodzajPrzewozonegoTowaru dodajPrzewozonyTowar(int number) {
        switch (number) {
            case 0:
                return RodzajPrzewozonegoTowaru.OWOCE_I_WARZYWA;
            case 1:
                return RodzajPrzewozonegoTowaru.PRODUKTY_MIĘSNE;
            case 2:
                return RodzajPrzewozonegoTowaru.PRODUKTY_MROZONE;
            default:
                return RodzajPrzewozonegoTowaru.BRAK_INFORMACJI;
        }
    }

    public int ustawPozodanaTemperatura(RodzajPrzewozonegoTowaru rodzajPrzewozonegoTowaru) {
        switch (rodzajPrzewozonegoTowaru) {
            case OWOCE_I_WARZYWA:
                return new Random().nextInt(11);
            case PRODUKTY_MIĘSNE:
                return new Random().nextInt(4) - 2;
            case PRODUKTY_MROZONE:
                return new Random().nextInt(7) - 25;
            default:
                return 10;
        }
    }

    @Override
    public int getCalkowitaWagaWagonu() {
        return calkowitaWagaWagonu;
    }

    @Override
    public String toString() {
        return "Wagon chłodniczy o numerze " + nrIdentyfikacyjnyWagonu + ". Całkowita waga wagonu wynosi " + calkowitaWagaWagonu +
                ". Wagon przewozi " + String.valueOf(rodzajPrzewozonegoTowaru) + " w odpowiedniej temperaturze " + pozadanaTemperatura + "." +
                " Wagon posiada zabezpieczenia: " + rodzajZabezpieczenWagonu;


    }
}
