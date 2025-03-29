package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**Definiše izgled prozora kada se igra završi. Sadrži dva dugmeta: jedno za ponovno igranje i drugo za odlazak na početni ekran
 * @param screen - koristi se za poziv reset metode da se obriše ploča (Screen)*/
public class GameEnd extends JFrame {

    private Screen screen;
    /**<pre>Prima instancu trenutnog ekrana koji se koristi za resetovanje igre ili otvaranje početnog ekrana.
     *Kreira izgled prozora nakon što se igra završi.
     * @param panel - kreira novi panel (JPanel)
     * @param label - label za naslov da je igra završena (JLabel)
     * @param playAgainButton - dugme koje se klikne ukoliko igrač želi opet igrati, poziva screen.resetujIgru() (JButton)
     * @param playAgainButton - dugme koje vodi korisnika na početni ekran (JButton)
     * </pre>*/
    public GameEnd(Screen screen) {
        this.screen = screen;

        setTitle("Igra je gotova!");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1));

        JLabel label = new JLabel("Igra je gotova!", JLabel.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 30));
        panel.add(label);

        JButton playAgainButton = new JButton("Igrajte opet");
        playAgainButton.setFont(new Font("Arial", Font.PLAIN, 18));
        playAgainButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                screen.resetujIgru();
                dispose();
            }
        });
        panel.add(playAgainButton);

        JButton homeScreenButton = new JButton("Početni ekran");
        homeScreenButton.setFont(new Font("Arial", Font.PLAIN, 18));
        homeScreenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                if (screen != null) {
                    screen.dispose();
                }
                new HomeScreen();
            }
        });
        panel.add(homeScreenButton);

        add(panel);
        setVisible(true);
    }
}
