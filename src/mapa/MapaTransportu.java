package mapa;

import swing.GUI;

import java.util.*;

public class MapaTransportu {

    public Map<StacjaKolejowa, Set<StacjaKolejowa>> trasyKolejowe;
    private Map<StacjaKolejowa[], Double> dlugoscTras;

    private static double maxDlugoscTrasy = 450;

    public MapaTransportu(List<StacjaKolejowa> listaStacji) {
        this.dlugoscTras = new HashMap<StacjaKolejowa[], Double>();
        this.trasyKolejowe = generujTrasyKolejowe(listaStacji);
    }

    private Map<StacjaKolejowa, Set<StacjaKolejowa>> generujTrasyKolejowe(List<StacjaKolejowa> listaStacji) {
        Map<StacjaKolejowa, Set<StacjaKolejowa>> trasaKolejowa = new HashMap<>();

        for (StacjaKolejowa stacjaGlowna : listaStacji) {
            if (!trasaKolejowa.containsKey(stacjaGlowna)) {
                trasaKolejowa.put(stacjaGlowna, new HashSet<StacjaKolejowa>());
            }

            do {
                int losowyIndeksStacji = new Random().nextInt(listaStacji.size());
                StacjaKolejowa stacjaDocelowa = listaStacji.get(losowyIndeksStacji);

                dodajTrase(trasaKolejowa, stacjaGlowna, stacjaDocelowa);

            } while (trasaKolejowa.get(stacjaGlowna).size() == 0 || (trasaKolejowa.get(stacjaGlowna).size() < 3 && Math.random() < 0.5));
        }

        return trasaKolejowa;
    }

    public static double obliczDlugoscTrasy(StacjaKolejowa sk, StacjaKolejowa stacjaDocelowa) {

        double x2 = Math.pow(Math.abs(sk.getX() - stacjaDocelowa.getX()), 2);
        double y2 = Math.pow(Math.abs(sk.getY() - stacjaDocelowa.getY()), 2);
        double dlugoscTrasy = Math.sqrt(x2 + y2);
        return dlugoscTrasy;
    }

    private boolean dodajTrase(Map<StacjaKolejowa, Set<StacjaKolejowa>> trasaKolejowa, StacjaKolejowa sk, StacjaKolejowa stacjaDocelowa) {
        if (sk == stacjaDocelowa) return false;

        double dlugoscTrasy = obliczDlugoscTrasy(sk, stacjaDocelowa);
        if (dlugoscTrasy > maxDlugoscTrasy) return false;

        StacjaKolejowa[] paraStacji = {sk, stacjaDocelowa};
        if (dlugoscTras.containsKey(paraStacji)) {
            return false;
        } else {
            this.dlugoscTras.put(paraStacji, dlugoscTrasy);
        }

        trasaKolejowa.get(sk).add(stacjaDocelowa);
        if (trasaKolejowa.get(stacjaDocelowa) == null) {
            trasaKolejowa.put(stacjaDocelowa, new HashSet<StacjaKolejowa>());
        }
        if (jezeliTrasaIstnieje(sk, stacjaDocelowa)) {
            return false;
        }
        trasaKolejowa.get(stacjaDocelowa).add(sk);

        return true;

    }

    private boolean jezeliTrasaIstnieje(StacjaKolejowa sk, StacjaKolejowa stacjaDocelowa) {

        if (trasyKolejowe == null) return false;
        for (StacjaKolejowa sk1 : trasyKolejowe.get(sk)) {
            for (StacjaKolejowa stacjaDocelowa1 : trasyKolejowe.get(stacjaDocelowa)) {
                if (sk == stacjaDocelowa1) {
                    return true;
                }
            }
        }
        return false;
    }

    public Set<StacjaKolejowa> getListStacjeKolejowe() {
        return trasyKolejowe.keySet();
    }

    public Set<StacjaKolejowa> getStacjeDocelowe(StacjaKolejowa sk) {
        return trasyKolejowe.get(sk);
    }

    public StacjaKolejowa generujLosowaStacje() {
        return null;
    }

    public void addStacja(StacjaKolejowa generujLosowaStacje) {
        trasyKolejowe.put(generujLosowaStacje, new HashSet<>());
    }

    public void usunStacje(StacjaKolejowa stacja, GUI gui) {

        trasyKolejowe.remove(stacja);
    }

    private void usunTrasyDlaUsunietejStacji(StacjaKolejowa stacja, GUI gui) {

        for (StacjaKolejowa s : getListStacjeKolejowe()) {
            for (StacjaKolejowa stacjaKolejowa : (trasyKolejowe.get(stacja))) {
                StacjaKolejowa[] tmp1 = {stacjaKolejowa, stacja};
                StacjaKolejowa[] tmp2 = {stacja, stacjaKolejowa};
                if (dlugoscTras.containsKey(tmp1)) {
                    dlugoscTras.remove(tmp1);
                }
                if (dlugoscTras.containsKey(tmp2)) {
                    dlugoscTras.remove(tmp2);
                }
            }
        }
        gui.repaint();
    }

    public StacjaKolejowa getLosowaStacja() {
        return this.trasyKolejowe.keySet().stream().skip(new Random().nextInt(this.trasyKolejowe.keySet().size())).findFirst().orElse(null);
    }


    public boolean dodajTrase(StacjaKolejowa[] wybraneStacjeDoNowejTrasy) {
        return dodajTrase(this.trasyKolejowe, wybraneStacjeDoNowejTrasy[0], wybraneStacjeDoNowejTrasy[1]);
    }

    public String printGraph() {
        StringBuilder builder = new StringBuilder();

        for (StacjaKolejowa sk : trasyKolejowe.keySet()) {
            builder.append(sk.toString() + ": ");
            for (StacjaKolejowa node : trasyKolejowe.get(sk)) {
                builder.append(node.toString() + " ");
            }
            builder.append("\n");
        }
        return builder.toString();
    }

    public boolean usunTrase(StacjaKolejowa[] wybranaStacja) {
        if (jezeliTrasaIstnieje(wybranaStacja[0], wybranaStacja[1])) {
            return true;
        }
        return false;
    }
}
