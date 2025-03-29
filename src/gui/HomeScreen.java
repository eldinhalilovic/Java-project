package gui;

import javax.swing.*;

import logika.ScoreManager;
import java.util.List;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**Služi za kreiranje prozora HomeScreen koji sadrži naslov igre te četiri dugmeta koji djeluju kao meni za igru.*/
public class HomeScreen extends JFrame {
	/**<pre>Konstruktor HomeScreen() kreira početni izgled ekrana igre te postavlja ActionListenere za četiri dugmeta: 
	 *pokretanje nove igre, prikazivanje uputa, prikazivanje rezultata i informacija o projektu.
	 *Također definiše veličinu i pozicije elemanata na prozoru.
	 *@param naslovLabela - label za naslov (JLabel) 
	 *@param panelDugmadi - panel u koji se smještaju buttoni za skladan izgled (JPanel)
	 *@param dugmeNovaIgra - poziva metodu pokreniNovuIgru() (JButton)
	 *@param dugmeUpute - poziva metodu prikaziUpute() (JButton)
	 *@param dugmeRezultati - poziva metodu prikaziRezultate() (JButton)
	 *@param dugmeOProjektu - poziva metodu prikaziOProjektu() (JButton)
	 *</pre> */
    public HomeScreen() {
        setTitle("Dobrodošli u 2048");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JLabel naslovLabela = new JLabel("Dobrodošli u 2048", SwingConstants.CENTER);
        naslovLabela.setFont(new Font("Arial", Font.BOLD, 24));
        naslovLabela.setForeground(Color.BLUE);
        add(naslovLabela, BorderLayout.NORTH);

        JPanel panelDugmadi = new JPanel();
        panelDugmadi.setLayout(new GridLayout(4, 1, 10, 10));
        panelDugmadi.setBackground(new Color(187, 173, 160));

        JButton dugmeNovaIgra = new JButton("Pokreni novu igru");
        dugmeNovaIgra.setFont(new Font("Arial", Font.PLAIN, 18));
        dugmeNovaIgra.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pokreniNovuIgru();
            }
        });

        JButton dugmeUpute = new JButton("Upute");
        dugmeUpute.setFont(new Font("Arial", Font.PLAIN, 18));
        dugmeUpute.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                prikaziUpute();
            }
        });
        JButton dugmeRezultati = new JButton("Rezultati");
        dugmeRezultati.setFont(new Font("Arial", Font.PLAIN, 18));
        dugmeRezultati.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                prikaziRezultate();
            }
        });
        
        JButton dugmeOProjektu = new JButton("O projektu");
        dugmeOProjektu.setFont(new Font("Arial", Font.PLAIN, 18));
        dugmeOProjektu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                prikaziOProjektu();
            }
        });

        panelDugmadi.add(dugmeNovaIgra);
        panelDugmadi.add(dugmeUpute);
        panelDugmadi.add(dugmeRezultati);
        panelDugmadi.add(dugmeOProjektu);

        add(panelDugmadi, BorderLayout.CENTER);

        setSize(600, 500);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    /**<pre>Metoda pokreniNovuIgru() se poziva klikanje na dugme "Pokreni novu igru", traži od igrača unos njegovog imena. 
     *Nakon unosa funkcija poziva konstruktor klase Screen(imeIgraca) koji pokreče igru</pre>*/
    private void pokreniNovuIgru() {
        String imeIgraca = JOptionPane.showInputDialog(this, "Unesite vaše ime:", "Unos imena", JOptionPane.PLAIN_MESSAGE);
        
        if (imeIgraca != null && !imeIgraca.trim().isEmpty()) {
            Object[] options = {"4x4", "5x5"};
            int izbor = JOptionPane.showOptionDialog(this, 
                    "Izaberite veličinu table:", 
                    "Veličina igre", 
                    JOptionPane.DEFAULT_OPTION, 
                    JOptionPane.QUESTION_MESSAGE, 
                    null, options, options[0]);

            if (izbor == 0) {
                new Screen(imeIgraca, 4);
            } else if (izbor == 1) {
                new Screen(imeIgraca, 5); 
            }
            
            dispose(); 
        } else {
            JOptionPane.showMessageDialog(this, "Morate unijeti ime da biste započeli igru.", "Greška", JOptionPane.ERROR_MESSAGE);
        }
    }
    /**<pre>Metoda prikaziRezultate() se poziva klikanjem na dugme "Rezultati", 
     *cilj funkcije je da izbaci poruku na ekran sa dosadašnjim rezultatima u formatu
     *imeIgraca brojBodova. Koristi listu stringova koji se dobiju iz ScoreManager klase, tj. metode vratiSveRezultate().</pre>*/
    private void prikaziRezultate() {
        List<String> sviRezultati = ScoreManager.vratiSveRezultate();


        if (sviRezultati.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nema rezultata.", "Rezultati", JOptionPane.INFORMATION_MESSAGE);
        } else {
            StringBuilder rezultatiText = new StringBuilder("Rezultati:\n");

            for (String rezultat : sviRezultati) {
                rezultatiText.append(rezultat).append("\n");
            }

            JOptionPane.showMessageDialog(this, rezultatiText.toString(), "Rezultati", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    /**Metoda prikaziUpute() se poziva klikanjem na dugme Upute, jednostavna metoda koja ima cilj da ukratko prikaže upute igraču.*/
    private void prikaziUpute() {
        JOptionPane.showMessageDialog(this,
                "2048 Upute za igru:\n" +
                        "- Koristite w, a, s, d za pomjeranje gore, lijevo, dolje i desno respektivno.\n" +
                        "- Pločice sa istim brojem se spajaju (npr. 2+2 postaje 4, 4+4 = 8 ...).\n" +
                        "- Pokušajte dostići pločicu 2048.\n" +
                        "- Igra završava kada nema više poteza.\n",
                "Upute", JOptionPane.INFORMATION_MESSAGE);
    }
    /**Metoda prikaziOProjektu() se poziva klikanjem na dugme O projektu, jednostavna metoda koja ima cilj da ukratko prikaže informacije o projektu, 
     * nazivu profesora, asistenta, projekta i studenta.*/
    private void prikaziOProjektu() {
        JOptionPane.showMessageDialog(this,
                "O projektu:\n" +
                        "- Naziv predmeta: Razvoj softvera.\n" +
                        "- Predmetni profesor: Damir Hasić.\n" +
                        "- Predmetni asistent: Vedad Letić.\n" +
                        "- Naziv projekta: Igra 2048.\n" +
                        "- Student: Eldin Halilović\n",
                "O projektu", JOptionPane.INFORMATION_MESSAGE);
    }

}
