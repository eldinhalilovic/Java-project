package logika;

import java.util.Random;

/**Klasa koja sadrži logiku za pomjeranje pločica u igri.
 * @param velicina - veličina ploče (int)
 * @param tabla - niz u koji se smještaju vrijednosti sa ploče (2D int niz)
 * @param slucajni - varijabla koja generiše random broj (Random)
 * */
public class Game {
    private int velicina;
    private int[][] tabla;
    private Random slucajni;
    
    /**Priprema igru za početak, generiše ploču sa 2 elementa na random mjestu*/
    public Game(int velicina) {
        if (velicina != 4 && velicina != 5) {
            throw new IllegalArgumentException("Dozvoljene veličine ploče su 4x4 ili 5x5.");
        }
        this.velicina = velicina;
        tabla = new int[velicina][velicina];
        slucajni = new Random();
        dodajPlocicu();
        dodajPlocicu();
    }

    /** Dodaje novu pločicu na random mjesto. Šansa za 2 je 90%, 10% za 4*/
    private void dodajPlocicu() {
        int vrijednost = slucajni.nextInt(10) < 9 ? 2 : 4;
        int red, kolona;
        do {
            red = slucajni.nextInt(velicina);
            kolona = slucajni.nextInt(velicina);
        } while (tabla[red][kolona] != 0);

        tabla[red][kolona] = vrijednost;
    }

    public boolean pomjeri(String smjer) {
        boolean pomjereno = false;
        switch (smjer.toLowerCase()) {
            case "gore":
                pomjereno = pomjeriGore();
                break;
            case "dolje":
                pomjereno = pomjeriDolje();
                break;
            case "lijevo":
                pomjereno = pomjeriLijevo();
                break;
            case "desno":
                pomjereno = pomjeriDesno();
                break;
            default:
                throw new IllegalArgumentException("Neispravan smjer: " + smjer);
        }
        if (pomjereno) {
            dodajPlocicu();
        }
        return pomjereno;
    }

    private boolean pomjeriGore() {
        boolean pomjereno = false;
        for (int kolona = 0; kolona < velicina; kolona++) {
            int[] kol = new int[velicina];
            for (int red = 0; red < velicina; red++) {
                kol[red] = tabla[red][kolona];
            }
            int[] spojenaKolona = spoji(kol);
            for (int red = 0; red < velicina; red++) {
                if (tabla[red][kolona] != spojenaKolona[red]) pomjereno = true;
                tabla[red][kolona] = spojenaKolona[red];
            }
        }
        return pomjereno;
    }

    private boolean pomjeriDolje() {
        boolean pomjereno = false;
        for (int kolona = 0; kolona < velicina; kolona++) {
            int[] kol = new int[velicina];
            for (int red = 0; red < velicina; red++) {
                kol[red] = tabla[velicina - 1 - red][kolona];
            }
            int[] spojenaKolona = spoji(kol);
            for (int red = 0; red < velicina; red++) {
                if (tabla[velicina - 1 - red][kolona] != spojenaKolona[red]) pomjereno = true;
                tabla[velicina - 1 - red][kolona] = spojenaKolona[red];
            }
        }
        return pomjereno;
    }

    private boolean pomjeriLijevo() {
        boolean pomjereno = false;
        for (int red = 0; red < velicina; red++) {
            int[] linija = tabla[red];
            int[] spojenaLinija = spoji(linija);
            for (int kolona = 0; kolona < velicina; kolona++) {
                if (tabla[red][kolona] != spojenaLinija[kolona]) pomjereno = true;
                tabla[red][kolona] = spojenaLinija[kolona];
            }
        }
        return pomjereno;
    }

    private boolean pomjeriDesno() {
        boolean pomjereno = false;
        for (int red = 0; red < velicina; red++) {
            int[] linija = new int[velicina];
            for (int kolona = 0; kolona < velicina; kolona++) {
                linija[kolona] = tabla[red][velicina - 1 - kolona];
            }
            int[] spojenaLinija = spoji(linija);
            for (int kolona = 0; kolona < velicina; kolona++) {
                if (tabla[red][velicina - 1 - kolona] != spojenaLinija[kolona]) pomjereno = true;
                tabla[red][velicina - 1 - kolona] = spojenaLinija[kolona];
            }
        }
        return pomjereno;
    }

    private int[] spoji(int[] linija) {
        int[] rezultat = new int[velicina];
        int indeks = 0;
        for (int i = 0; i < velicina; i++) {
            if (linija[i] != 0) {
                if (indeks > 0 && rezultat[indeks - 1] == linija[i]) {
                    rezultat[indeks - 1] *= 2;
                } else {
                    rezultat[indeks++] = linija[i];
                }
            }
        }
        return rezultat;
    }

    public boolean jeIgraGotova() {
        for (int red = 0; red < velicina; red++) {
            for (int kolona = 0; kolona < velicina; kolona++) {
                if (tabla[red][kolona] == 0) return false;
                if (red < velicina - 1 && tabla[red][kolona] == tabla[red + 1][kolona]) return false;
                if (kolona < velicina - 1 && tabla[red][kolona] == tabla[red][kolona + 1]) return false;
            }
        }
        return true;
    }

    public void reset() {
        tabla = new int[velicina][velicina];
        slucajni = new Random();
        dodajPlocicu();
        dodajPlocicu();
    }

    public int[][] getTabla() {
        return tabla;
    }
}
