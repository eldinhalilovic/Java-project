package konzola;

import logika.IgranjeKonzola;
import java.util.Scanner;

public class Main {
    /** Metoda koja se koristi za pokretanje igre u konzoli. */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int velicina;

        while (true) {
            System.out.print("Unesite veličinu table (4 za 4x4, 5 za 5x5): ");
            if (scanner.hasNextInt()) {
                velicina = scanner.nextInt();
                if (velicina == 4 || velicina == 5) {
                    break; // Valid input, exit loop
                }
            } else {
                scanner.next(); // Clear invalid input
            }
            System.out.println("Pogrešan unos! Molimo unesite 4 ili 5.");
        }

        IgranjeKonzola igranje = new IgranjeKonzola(velicina);
        igranje.pokreniIgru();

        scanner.close();
    }
}
