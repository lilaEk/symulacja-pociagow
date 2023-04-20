package wagony.typWagonu;

import java.util.Random;

public class TCMaterialyToksyczne extends TowarowyCiezki {

    private int wagaDodatkowaWagonu = new Random().nextInt(15) + 10;
    private int calkowitaWagaWagonu;

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
        return "TCMaterialyToksyczne{" +
                "wagaDodatkowaWagonu=" + wagaDodatkowaWagonu +
                ", calkowitaWagaWagonu=" + calkowitaWagaWagonu +
                ", nrIdentyfikacyjnyWagonu=" + nrIdentyfikacyjnyWagonu +
                '}';
    }
}