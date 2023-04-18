package wagony;

import wagony.cechyWagonu.RodzajZabezpieczen;
import wagony.typWagonu.Chlodniczy;

import java.util.ArrayList;
import java.util.List;

public abstract class Wagon {

    protected final int podstawowaWagaWagonu = 30; //ton
    protected int nrIdentyfikacyjnyWagonu;
    protected static int counter = 1;

    protected ArrayList<RodzajZabezpieczen> rodzajZabezpieczenWagonu;


    public Wagon() {
        this.nrIdentyfikacyjnyWagonu = counter;
        counter++;
    }

    public static List<Wagon> stworzZestawWagonow(int iloscWagonow) {
        List<Wagon> listaW = new ArrayList<>();
        for (int i = 0; i < iloscWagonow; i++) {
            listaW.add(new Chlodniczy());
        }
        return listaW;
    }
}
