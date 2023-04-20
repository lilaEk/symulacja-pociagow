package wagony.typWagonu;

import wagony.Wagon;
import wagony.cechyWagonu.RodzajZabezpieczen;

import java.util.ArrayList;
import java.util.Random;

public class BagazowoPocztowy extends Wagon {

    // max 2 wagony na lokomotywe


    private int iloscPrzechowywanegoBagazu;
    private boolean czyNadanoPrzesylki;

    private String nadawca;
    private String odbiorca;
    private double sredniaWagaBagazu = 0.5;

    private int wagaDodatkowaWagonu = new Random().nextInt(5) + 2;
    private int calkowitaWagaWagonu;


    public BagazowoPocztowy() {
        super();
        this.iloscPrzechowywanegoBagazu = new Random().nextInt(80) + 20;
        this.calkowitaWagaWagonu = obliczWage(iloscPrzechowywanegoBagazu);

        super.rodzajZabezpieczenWagonu = new ArrayList<>();
        rodzajZabezpieczenWagonu.add(RodzajZabezpieczen.KAMERY_MONITORINGU);
        rodzajZabezpieczenWagonu.add(RodzajZabezpieczen.ZABEZPIECZENIA_PRZECIWPOZAROWE);
        rodzajZabezpieczenWagonu.add(RodzajZabezpieczen.SYSTEM_KONTROLI_DOSTEPU);

        this.czyNadanoPrzesylki = sprawdzCzyNadanoPrzesylki();
        if (czyNadanoPrzesylki) {
            setNadawca("Pani Ania");
            setOdbiorca("Pani Zosia");

        }
    }

    public int obliczWage(int iloscBagazu) {
        return this.wagaDodatkowaWagonu = (int) (wagaDodatkowaWagonu + podstawowaWagaWagonu + iloscBagazu * sredniaWagaBagazu);
    }

    public boolean sprawdzCzyNadanoPrzesylki() {
        int losowa = new Random().nextInt(2);
        switch (losowa) {
            case 0:
                return true;
            case 1:
                return false;
        }
        return false;
    }

    public String getNadawca() {
        return nadawca;
    }

    public void setNadawca(String nadawca) {
        this.nadawca = nadawca;
    }

    public String getOdbiorca() {
        return odbiorca;
    }

    public void setOdbiorca(String odbiorca) {
        this.odbiorca = odbiorca;
    }

    @Override
    public String toString() {
        String info = "Wagon bagażowo-pocztowy o numerze" + nrIdentyfikacyjnyWagonu + ". Całkowita waga wagonu wynosi " + calkowitaWagaWagonu + ". Wagon przechowuje " + iloscPrzechowywanegoBagazu + " sztuk bagażu.";
        if (!this.czyNadanoPrzesylki) return info;
        info = info + " Nadadano przesyłki. Nadawca: " + nadawca + ", odbiorca: " + odbiorca + ".";
        return info;
    }
}
