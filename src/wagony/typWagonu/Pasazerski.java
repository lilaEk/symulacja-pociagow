package wagony.typWagonu;

import wagony.Wagon;
import wagony.cechyWagonu.DostepDoSieciElektrycznej;
import wagony.cechyWagonu.DostepDoWifi;
import wagony.cechyWagonu.RodzajZabezpieczen;

import java.util.ArrayList;
import java.util.Random;

public class Pasazerski

        extends Wagon implements DostepDoSieciElektrycznej, DostepDoWifi {

    private int liczbaMiejscSiedzacych = 150;
    private int obecnaIloscPasazerow;
    private int wagaDodatkowaWagonu = new Random().nextInt(3);
    private int calkowitaWagaWagonu;

    public Pasazerski() {
        super();
        this.calkowitaWagaWagonu = super.podstawowaWagaWagonu + this.wagaDodatkowaWagonu;

        super.rodzajZabezpieczenWagonu = new ArrayList<>();
        rodzajZabezpieczenWagonu.add(RodzajZabezpieczen.KAMERY_MONITORINGU);
        rodzajZabezpieczenWagonu.add(RodzajZabezpieczen.ZABEZPIECZENIA_PRZECIWPOZAROWE);
        rodzajZabezpieczenWagonu.add(RodzajZabezpieczen.HAMULEC_RECZNY);

        this.obecnaIloscPasazerow = new Random().nextInt(200);
        if (this.obecnaIloscPasazerow > liczbaMiejscSiedzacych)
            System.out.println("Brak miejsc siedzących dla wszystkich pasażerów wagonu " + nrIdentyfikacyjnyWagonu);
    }

    @Override
    public int getCalkowitaWagaWagonu() {
        return calkowitaWagaWagonu;
    }

    @Override
    public String toString() {
        return "Wagon pasażerski o numerze " + nrIdentyfikacyjnyWagonu + ". Całkowita waga wagonu wynosi " + calkowitaWagaWagonu +
                ". Liczba miejsc siedzacych to " + liczbaMiejscSiedzacych + ", a obecna ilość pasażerów wysoni " + obecnaIloscPasazerow + "." +
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