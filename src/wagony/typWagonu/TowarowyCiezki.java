package wagony.typWagonu;

import wagony.Wagon;

import java.util.Random;

public class TowarowyCiezki extends Wagon {

    private int wagaDodatkowaWagonu = new Random().nextInt(15) + 10;
    private int calkowitaWagaWagonu;

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
        return "TowarowyCiezki{" +
                "wagaDodatkowaWagonu=" + wagaDodatkowaWagonu +
                ", calkowitaWagaWagonu=" + calkowitaWagaWagonu +
                ", nrIdentyfikacyjnyWagonu=" + nrIdentyfikacyjnyWagonu +
                '}';
    }
}
