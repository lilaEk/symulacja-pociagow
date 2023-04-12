public class Lokomotywa {

    //Każda lokomotywa posiada podstawowe informacje o sobie (nazwę, stacja macierzysta, stacja
    //źródłowa i docelowa transportu) oraz swój unikatowy numer identyfikacyjny nadawany automatycznie
    //podczas tworzenia obiektu.


    private static int nrIdentyfikacyjnyLokomotywy;
    private int counter=1;

    //private final String stacjaMacierzysta;

    public Lokomotywa(){
        this.nrIdentyfikacyjnyLokomotywy = counter;
        counter++;
    }

}
