package wagony.typWagonu;

import wagony.Wagon;
import wagony.cechyWagonu.RodzajTowaruCiezkiego;

import java.util.Random;

public class TowarowyCiezki extends Wagon {

    private int wagaDodatkowaWagonu = new Random().nextInt(15) + 10;
    private int calkowitaWagaWagonu;
    final private RodzajTowaruCiezkiego rodzajTowaruCiezkiego = RodzajTowaruCiezkiego.WEGIEL;

    public TowarowyCiezki() {
        super();
        this.calkowitaWagaWagonu = super.podstawowaWagaWagonu + this.wagaDodatkowaWagonu;
    }

    @Override
    public int getCalkowitaWagaWagonu() {
        return calkowitaWagaWagonu;
    }

    @Override
    public String toString() {
        return "Wagon towarowy ciężki o numerze " + nrIdentyfikacyjnyWagonu + ". Całkowita waga wagonu wynosi " + calkowitaWagaWagonu
                + ". Rodzaj przechowywanego towaru: " + this.rodzajTowaruCiezkiego;
    }
}
