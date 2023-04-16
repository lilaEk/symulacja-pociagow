package wagony.typWagonu;

import wagony.cechyWagonu.DostepDoSieciElektrycznej;
import wagony.Wagon;
import wagony.cechyWagonu.RodzajDostepnejKuchni;
import wagony.cechyWagonu.RodzajZabezpieczen;

import java.util.ArrayList;
import java.util.Random;

public class Restauracyjny

        // może byc tylko w pociagu gdzie jest pasazerski
        // moze byc tylko jeden!
        // interface dostep do wifi
        // rodzaj zabezpieczen

        extends Wagon
        implements DostepDoSieciElektrycznej {

    private final static int iloscStolikow = 15;
    private final static int maxIloscGosci = 80;
    private int wagaWagonu;
    private RodzajDostepnejKuchni rodzajKuchni;

    public Restauracyjny() {
        super();
        this.wagaWagonu = podstawowaWagaWagonu;
        this.rodzajKuchni = wybierzRodzajKuchni(new Random().nextInt(7));

        super.rodzajZabezpieczenWagonu = new ArrayList<>();
        rodzajZabezpieczenWagonu.add(RodzajZabezpieczen.KAMERY_MONITORINGU);
        rodzajZabezpieczenWagonu.add(RodzajZabezpieczen.ZABEZPIECZENIA_PRZECIWPOZAROWE);
        rodzajZabezpieczenWagonu.add(RodzajZabezpieczen.HAMULEC_RECZNY);
    }

    public RodzajDostepnejKuchni wybierzRodzajKuchni(int number){
        //mozliwosc zmiany smaku
        switch (number){ //czy tak sie robi switche??
            case 0: return RodzajDostepnejKuchni.KUCHNIA_WLOSKA;
            case 1: return RodzajDostepnejKuchni.KUCHNIA_POLSKA;
            case 2: return RodzajDostepnejKuchni.KUCHNIA_CHINSKA;
            case 3: return RodzajDostepnejKuchni.KUCHNIA_INDYJSKA;
            case 4: return RodzajDostepnejKuchni.KUCHNIA_JAPONSKA;
            case 5: return RodzajDostepnejKuchni.KUCHNIA_FRANCUSKA;
            case 6: return RodzajDostepnejKuchni.KUCHNIA_AMERYKANSKA;
            default: return RodzajDostepnejKuchni.BRAK_INFORMACJI;
        }
    }
}