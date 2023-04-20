package wagony.typWagonu;

import wagony.cechyWagonu.RodzajTowaruCiezkiego;

import java.util.Random;

public class TCMaterialyToksyczne extends TowarowyCiezki {

    private int wagaDodatkowaWagonu = new Random().nextInt(15) + 10;
    private int calkowitaWagaWagonu;
    final private RodzajTowaruCiezkiego rodzajTowaruCiezkiego = RodzajTowaruCiezkiego.RTEC;

    public TCMaterialyToksyczne() {
        super();
        this.calkowitaWagaWagonu = super.podstawowaWagaWagonu + this.wagaDodatkowaWagonu;
    }

    @Override
    public int getCalkowitaWagaWagonu() {
        return calkowitaWagaWagonu;
    }

    @Override
    public String toString() {
        return "Wagon towarowy ciężki przewożący materiały toksyczne o numerze " + nrIdentyfikacyjnyWagonu + ". Całkowita waga wagonu wynosi " + calkowitaWagaWagonu
                + ". Rodzaj przechowywanego towaru: " + this.rodzajTowaruCiezkiego;
    }
}