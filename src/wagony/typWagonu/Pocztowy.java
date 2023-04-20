package wagony.typWagonu;

import wagony.Wagon;
import wagony.cechyWagonu.DostepDoSieciElektrycznej;
import wagony.cechyWagonu.RodzajZabezpieczen;

import java.util.ArrayList;
import java.util.Random;

public class Pocztowy extends Wagon implements DostepDoSieciElektrycznej {

    private boolean rampaZaladunkowa;
    private int iloscPaczekIListow;
    private boolean czyZaladowanoTowar;
    private int wagaDodatkowaWagonu = new Random().nextInt(10);
    private int calkowitaWagaWagonu;

    public Pocztowy() {
        super();
        this.calkowitaWagaWagonu = super.podstawowaWagaWagonu + this.wagaDodatkowaWagonu;

        super.rodzajZabezpieczenWagonu = new ArrayList<>();
        rodzajZabezpieczenWagonu.add(RodzajZabezpieczen.KAMERY_MONITORINGU);
        rodzajZabezpieczenWagonu.add(RodzajZabezpieczen.ZABEZPIECZENIA_PRZECIWPOZAROWE);
        rodzajZabezpieczenWagonu.add(RodzajZabezpieczen.SYSTEM_KONTROLI_DOSTEPU);

        this.rampaZaladunkowa = czyZawieraRampe(new Random().nextInt(2));
        this.iloscPaczekIListow = new Random().nextInt(1000);
        this.czyZaladowanoTowar = czyZaladowanoTowar();

    }

    public boolean czyZawieraRampe(int losowa) {
        if (losowa == 0) return true;
        return false;
    }

    public boolean czyZaladowanoTowar() {
        if (this.rampaZaladunkowa && this.iloscPaczekIListow > 500) return true;
        return false;
    }

    @Override
    public int getCalkowitaWagaWagonu() {
        return calkowitaWagaWagonu;
    }

    @Override
    public String toString() {
        return "Wagon pocztowy o numerze " + nrIdentyfikacyjnyWagonu + ". Całkowita waga wagonu wynosi " + calkowitaWagaWagonu +
                "Czy wagon posiada rampę załadunkową: " + rampaZaladunkowa + ". Wagon przewozi łącznie " +
                iloscPaczekIListow + " paczek i listów. Czy załadowano towar: " + czyZaladowanoTowar + ".";
    }
}
