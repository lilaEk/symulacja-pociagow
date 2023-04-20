package wagony.typWagonu;

import wagony.Wagon;
import wagony.cechyWagonu.DostepDoSieciElektrycznej;
import wagony.cechyWagonu.DostepDoWifi;

public class Pasazerski

        // regulacja temperatury

        extends Wagon
        implements DostepDoSieciElektrycznej, DostepDoWifi {

    private int liczbaMiejscSiedzacych;

    public Pasazerski() {
        super();
    }

}
