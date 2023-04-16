package wagony;

import wagony.cechyWagonu.RodzajZabezpieczen;

import java.util.ArrayList;

public abstract class Wagon {

    protected final int podstawowaWagaWagonu = 30; //ton
    protected int nrIdentyfikacyjnyWagonu;
    protected static int counter=1;

    protected ArrayList<RodzajZabezpieczen> rodzajZabezpieczenWagonu;


    public Wagon(){
        this.nrIdentyfikacyjnyWagonu = counter;
        counter++;
    }

}
