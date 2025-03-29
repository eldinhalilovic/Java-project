package logika;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**<pre>Klasa koja upravlja rezultatima u igri. Koristi se za upis rezultata svih igrača u tekstualni fajl koji završe igru.
 *Također, koristi se za vraćanje najvećeg rezultata (Max score) i za vraćanje liste svih rezultata.
 * @param nazivIgraca - sadrži naziv igrača koji je završio igru i koji će biti upisan u fajl
 * @param rezultat - rezultat igrača koji će biti upisan u fajl
 * 
 * </pre>*/
public class ScoreManager {
    private String nazivIgraca; 
    private int rezultat; 
    /**Konstruktor se koristi da postavi nazivIgraca i rezultat u varijable te poziva metodu spremiRezultatUDatoteku().*/
    public ScoreManager(String nazivIgraca, int rezultat) {
        this.nazivIgraca = nazivIgraca;
        this.rezultat = rezultat;
        spremiRezultatUDatoteku();
    }
    /**Metoda otvara datoteku rezultati.txt i na kraj dodaje rezultat igre u formatu "nazivIgraca rezultat".*/
    private void spremiRezultatUDatoteku() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("rezultati.txt", true))) {
            writer.write("Igrač: " + nazivIgraca + ", Rezultat: " + rezultat);
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Greška prilikom spremanja rezultata: " + e.getMessage());
        }
    }
    /**<pre>Čita datoteku rezultati.txt i za svaku liniju preko regularnog izraza pronalazi rezultat i pretvara ga u int, 
     *zatim provjerava da li je najveći rezultat i ažurira varijablu najveciRezultat ako jeste.</pre> */
    public static int vratiNajveciRezultat() {
        int najveciRezultat = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader("rezultati.txt"))) {
            String linija;
            Pattern pattern = Pattern.compile("Igrač: .*?, Rezultat: (\\d+)");

            while ((linija = reader.readLine()) != null) {
                Matcher matcher = pattern.matcher(linija);
                if (matcher.find()) {
                    int rezultat = Integer.parseInt(matcher.group(1));
                    if (rezultat > najveciRezultat) {
                        najveciRezultat = rezultat;
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Greška prilikom čitanja rezultata: " + e.getMessage());
        }

        return najveciRezultat;
    }
    /**<pre>Koristi listu za smještanje svih rezultata u formatu "nazivIgraca rezultat". Vrijednost se spašava kao String u listi.
     *napunjena lista se vraća preko return. </pre>*/
    public static List<String> vratiSveRezultate() {
        List<String> rezultati = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("rezultati.txt"))) {
            String linija;
            Pattern pattern = Pattern.compile("Igrač: (.*?), Rezultat: (\\d+)");

            while ((linija = reader.readLine()) != null) {
                Matcher matcher = pattern.matcher(linija);
                if (matcher.find()) {
                    String imeIgraca = matcher.group(1);
                    int rezultat = Integer.parseInt(matcher.group(2));
                    rezultati.add(imeIgraca + " " + rezultat);
                }
            }
        } catch (IOException e) {
            System.err.println("Greška prilikom čitanja rezultata: " + e.getMessage());
        }

        return rezultati;
    }

    @Override
    public String toString() {
        return "Igrač: " + nazivIgraca + ", Rezultat: " + rezultat;
    }
}
