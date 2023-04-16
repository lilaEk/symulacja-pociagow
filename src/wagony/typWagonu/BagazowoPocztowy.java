package wagony.typWagonu;

import wagony.Wagon;
import wagony.cechyWagonu.RodzajZabezpieczen;

import java.util.ArrayList;
import java.util.Random;

public class BagazowoPocztowy
        extends Wagon {

    // max 2 wagony na lokomotywe


    private int iloscPrzechowywanegoBagazu; // rand
    private int iloscPaczekNaDanejTrasie; // zmienialna z odjazdem z kazdej stacji
    private String nadawca;
    private String odbiorca; //gettery i settery

    private int wagaDodatkowaWagonu = new Random().nextInt(5)+2;


    public BagazowoPocztowy() {
        super();
        this.wagaDodatkowaWagonu = wagaDodatkowaWagonu + podstawowaWagaWagonu;

        super.rodzajZabezpieczenWagonu = new ArrayList<>();
        rodzajZabezpieczenWagonu.add(RodzajZabezpieczen.KAMERY_MONITORINGU);
        rodzajZabezpieczenWagonu.add(RodzajZabezpieczen.ZABEZPIECZENIA_PRZECIWPOZAROWE);
        rodzajZabezpieczenWagonu.add(RodzajZabezpieczen.SYSTEM_KONTROLI_DOSTEPU);
    }

}
