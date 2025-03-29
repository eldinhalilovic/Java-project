package gui;

import javax.swing.*;
import java.awt.*;
import logika.Game; 
/**Klasa definiše ploču za igru 2048. Iscrtava ploču, polja i ažurira ploču.
 * @param tabla - predstavlja ploču 4x4 u koju su smještene pločice*/
public class Board extends JPanel {
    private int[][] tabla; 
    private int velicinaT;
    /**Inicijalizuje ploču igre.
     * @param tabla - prima niz koji predstavlja ploču*/
    public Board(int[][] tabla, int velicinaTable) {
        this.tabla = tabla;
        this.velicinaT = velicinaTable;
        setPreferredSize(new Dimension(500, 500));
        setBackground(new Color(187, 173, 160));
    }
    /**Ugrađena funkcija koja se override-a, poziva se da se iscrta ploča.*/
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        iscrtajTablu(g);
    }
    /**Crta cijelu ploču igre kao i zasebne pločice.
     * @param g - Graphics apstraktna klasa koja se koristi za iscrtavanje na prozoru.*/
    private void iscrtajTablu(Graphics g) {
        int velicinaPolja = getWidth() / velicinaT;

        for (int i = 0; i < velicinaT; i++) {
            for (int j = 0; j < velicinaT; j++) {
                int vrijednost = tabla[i][j];
                iscrtajPolje(g, i, j, vrijednost, velicinaPolja);
            }
        }
    }
    /**Crta pojedinačno polje na ploči te ga postavlja na specifičnu boju zavisno od vrijednosti.
     * @param g - Graphics apstraktna klasa koristi se za iscrtavanje
     * @param red - red ploče
     * @param kolona - kolona ploče
     * @param vrijednost - vrijednost na ploči
     * @param velicina - veličina matrice
     * @param x - određuje x koordinatu pločice na panelu (za crtanje)
     * @param y - određuje y koordinatu pločice na panelu (za crtanje)*/
    private void iscrtajPolje(Graphics g, int red, int kolona, int vrijednost, int velicina) {
        int x = kolona * velicina;
        int y = red * velicina;

        g.setColor(dohvatiBojuTile(vrijednost));
        g.fillRoundRect(x, y, velicina - 5, velicina - 5, 10, 10);

        if (vrijednost != 0) {
            g.setColor(new Color(0,0,0));
            g.setFont(new Font("Arial", Font.BOLD, 40));
            String tekstVrijednosti = String.valueOf(vrijednost);
            FontMetrics metrika = g.getFontMetrics();
            int sirinaTeksta = metrika.stringWidth(tekstVrijednosti);
            int visinaTeksta = metrika.getHeight();
            g.drawString(tekstVrijednosti, x + (velicina - sirinaTeksta) / 2, y + (velicina + visinaTeksta) / 2);
        }
    }
    /**Ažurira matricu ploče i osvježava njen prikaz pozivom repaint() metode.
     * @param novaTabla - matrica koja sadrži pločice igre*/
    public void azurirajTablu(int[][] novaTabla) {
        this.tabla = novaTabla;
        repaint();
    }
    /**<pre>Metoda koju koristim da postavi boju na pozadini pločice. U zavisnosti koja je vrijednost dobija se druga boja pozadine.
     * @param vrijednost - predstavlja vrijednost koja se prikaže na pločici 
     * </pre>*/
    private Color dohvatiBojuTile(int vrijednost) {
        switch (vrijednost) { 						   // Sve boje su svjetlije nijanse
            case 2: return new Color(255, 102, 102);   // Crvena
            case 4: return new Color(102, 255, 102);   // Zelena
            case 8: return new Color(102, 178, 255);   // Plava
            case 16: return new Color(255, 255, 153);  // Žuta
            case 32: return new Color(153, 255, 255);  // Cijan
            case 64: return new Color(255, 153, 255);  // Magneta
            case 128: return new Color(255, 200, 102); // Narandžasta
            case 256: return new Color(178, 102, 214); // Ljubičasta
            case 512: return new Color(255, 182, 193); // Roza
            case 1024: return new Color(205, 133, 63); // Smeđa
            case 2048: return new Color(192, 192, 192); // Siva
            default: return new Color(205, 193, 180);  // Bež
        }
    }

}



