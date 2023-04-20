package wagony.typWagonu;

import java.util.Random;

public class TCMaterialyWybuchoweCiekle extends TCMaterialyWybuchowe {

    private int wagaDodatkowaWagonu = new Random().nextInt(15) + 10;
    private int calkowitaWagaWagonu;

    public TCMaterialyWybuchoweCiekle() {
        super();
        this.calkowitaWagaWagonu = super.podstawowaWagaWagonu + this.wagaDodatkowaWagonu;
    }

    @Override
    public int getCalkowitaWagaWagonu() {
        return calkowitaWagaWagonu;
    }

    @Override
    public String toString() {
        return "TCMaterialyWybuchoweCiekle{" +
                "wagaDodatkowaWagonu=" + wagaDodatkowaWagonu +
                ", calkowitaWagaWagonu=" + calkowitaWagaWagonu +
                ", nrIdentyfikacyjnyWagonu=" + nrIdentyfikacyjnyWagonu +
                '}';
    }
}