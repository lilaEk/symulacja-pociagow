package wagony.typWagonu;

import wagony.Wagon;

import java.util.Random;

public class TowarowyPodstawowy extends Wagon {


    private int wagaDodatkowaWagonu = new Random().nextInt(10) + 5;
    private int calkowitaWagaWagonu;

    public TowarowyPodstawowy() {
        super();

        this.calkowitaWagaWagonu = super.podstawowaWagaWagonu + this.wagaDodatkowaWagonu;
    }

    @Override
    public int getCalkowitaWagaWagonu() {
        return calkowitaWagaWagonu;
    }

    @Override
    public String toString() {
        return "TowarowyPodstawowy{" +
                "wagaDodatkowaWagonu=" + wagaDodatkowaWagonu +
                ", calkowitaWagaWagonu=" + calkowitaWagaWagonu +
                ", nrIdentyfikacyjnyWagonu=" + nrIdentyfikacyjnyWagonu +
                '}';
    }
}
