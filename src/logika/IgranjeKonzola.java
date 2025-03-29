package logika;

import java.util.Scanner;
/**Klasa koja se poziva prilikom pokretanja igre u konzoli. Osigurava dobru logiku i izgled igre.
 *@param igra - poziva konstruktor klase Game() koji osigurava pomjeranje u igri
 *@param skener - omogucava korisniku unos u konzoli*/
public class IgranjeKonzola {

    private Game igra;
    private Scanner skener;
    private int velicinaTable;
    /**Osigurava inicijalizaciju potrebnih komponenti za početak. */
    public IgranjeKonzola(int velicina) {
        this.velicinaTable = velicina;
    	this.igra = new Game(velicinaTable);
        this.skener = new Scanner(System.in);
    }
    /**<pre>Pokreće igru te omogućava korisniku unos kretanja u konzolu. 
     *Igra traje dok ne nestane poteza ili dok igrač ne pobijedi.</pre>*/
    public void pokreniIgru() {
        boolean krajIgre = false;

        System.out.println("Dobrodošli u 2048! Koristite 'w', 'a', 's', 'd' za kretanje gore, lijevo, dolje i desno.");

        while (!krajIgre) {
            ispisiTablu(igra.getTabla());

            System.out.print("Unesite potez (w/a/s/d): ");
            String unos = skener.nextLine();

            if (!jeValidanPotez(unos)) {
                System.out.println("Unos nije validan. Koristite 'w', 'a', 's', 'd'.");
                continue;
            }

            boolean pomaknuto = igra.pomjeri(konvertujUnosUSmjer(unos));

            if (!pomaknuto) {
                System.out.println("Potez nije moguć. Pokušajte drugi smjer.");
            }

            if (igra.jeIgraGotova()) {
                krajIgre = true;
                System.out.println("Kraj igre! Izgled ploče:");
                ispisiTablu(igra.getTabla());
            }
        }

        skener.close();
    }
    /**Ispisuje trenutni izgled ploče
     * @param tabla - niz koji sadrži trenutni izgled ploče za igru.*/
    private static void ispisiTablu(int[][] tabla) {
        for (int[] red : tabla) {
            for (int celija : red) {
                System.out.printf("%4d", celija);
            }
            System.out.println();
        }
        System.out.println();
    }
    /** Provjerava da li je konzolni unos za pomjeranje dobar. Vraća true ako jeste, inače false
     * @param unos - korisnički unos smjera (String)*/
    private static boolean jeValidanPotez(String unos) {
        return unos.equals("w") || unos.equals("a") || unos.equals("s") || unos.equals("d");
    }
    /**Nakon unosa smjera u konzolu, ova metoda pretvara unos u odgovarajući smjer kako bi se mogao iskoristiti za kretanje u klasi Game.
     * @param unos - korisnički unos smjera (String)*/
    private static String konvertujUnosUSmjer(String unos) {
        switch (unos) {
            case "w": return "gore";
            case "a": return "lijevo";
            case "s": return "dolje";
            case "d": return "desno";
            default: throw new IllegalArgumentException("Nevažeći unos!");
        }
    }
}
