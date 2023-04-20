package wagony.typWagonu;

import java.util.Random;

public class TPMaterialyGazowe extends TowarowyPodstawowy {

    private int wagaDodatkowaWagonu = new Random().nextInt(10) + 5;
    private int calkowitaWagaWagonu;

    public TPMaterialyGazowe() {
        super();

        this.calkowitaWagaWagonu = super.podstawowaWagaWagonu + this.wagaDodatkowaWagonu;
    }

    @Override
    public int getCalkowitaWagaWagonu() {
        return calkowitaWagaWagonu;
    }

    @Override
    public String toString() {
        return "TPMaterialyGazowe{" +
                "wagaDodatkowaWagonu=" + wagaDodatkowaWagonu +
                ", calkowitaWagaWagonu=" + calkowitaWagaWagonu +
                ", nrIdentyfikacyjnyWagonu=" + nrIdentyfikacyjnyWagonu +
                '}';
    }
}
