package wagony.typWagonu;

import java.util.Random;

public class TPMaterialyCiekle extends TowarowyPodstawowy {

    private int wagaDodatkowaWagonu = new Random().nextInt(10) + 5;
    private int calkowitaWagaWagonu;

    public TPMaterialyCiekle() {
        super();

        this.calkowitaWagaWagonu = super.podstawowaWagaWagonu + this.wagaDodatkowaWagonu;
    }

    @Override
    public int getCalkowitaWagaWagonu() {
        return calkowitaWagaWagonu;
    }

    @Override
    public String toString() {
        return "TPMaterialyCiekle{" +
                "wagaDodatkowaWagonu=" + wagaDodatkowaWagonu +
                ", calkowitaWagaWagonu=" + calkowitaWagaWagonu +
                ", nrIdentyfikacyjnyWagonu=" + nrIdentyfikacyjnyWagonu +
                '}';
    }
}
