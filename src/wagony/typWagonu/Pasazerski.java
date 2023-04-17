package wagony.typWagonu;

import wagony.Wagon;
import wagony.cechyWagonu.DostepDoSieciElektrycznej;

public class Pasazerski

        // regulacja temperatury

        extends Wagon
        implements DostepDoSieciElektrycznej {

    private int liczbaMiejscSiedzacych;

    public Pasazerski() {
        super();
    }

}
