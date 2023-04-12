package TypWagonu;

public abstract class Wagon {

    private static int nrIdentyfikacyjnyWagonu;
    private int counter=1;

    //Istnieją różnego typu wagony kolejowe. Każdy z typów wagonów posiada inny zestaw cech
    //(np. nadawca, zabezpieczenia, waga netto, waga brutto, liczba miejsc siedzących itp.). W przypadku
    //każdego typu wagonu należy dodać ponad wymienione co najmniej dwie unikalne cechy do
    //każdego typu wagonu. Każdy wagon posiada swój unikatowy numer identyfikacyjny nadawany
    //automatycznie podczas tworzenia obiektu.

    public Wagon(){
        this.nrIdentyfikacyjnyWagonu = counter;
        counter++;
    }

}
