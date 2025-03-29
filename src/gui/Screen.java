package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import logika.Game;
import logika.ScoreManager;

/**
 * Sadrži varijable i metode koje se koriste za prikazivanje sadržaja igre na prozoru ekrana.
 */
public class Screen extends JFrame {
    private Board board;
    private JLabel trenutniSkorLabela;
    private JLabel maksimalniSkorLabela;
    private int trenutniSkor = 0;
    private int maksimalniSkor;
    private Game igra;
    private String imeIgraca;
    private String maksSkor;
    private int velicinaTable; // Dodata varijabla za veličinu table

    /**
     * Konstruktor Screen koji omogućava izbor veličine table.
     * 
     * @param imeIgraca     Ime igrača
     * @param velicinaTable Veličina table (4x4 ili 5x5)
     */
    public Screen(String imeIgraca, int velicinaTable) {
        this.imeIgraca = imeIgraca;
        this.velicinaTable = velicinaTable;
        this.maksimalniSkor = ScoreManager.vratiNajveciRezultat();
        this.maksSkor = String.valueOf(maksimalniSkor);
        
        setTitle("Igra 2048");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(new BorderLayout());

        igra = new Game(velicinaTable); 

        JPanel panelSkora = kreirajPanelSkora();
        add(panelSkora, BorderLayout.NORTH);

        board = new Board(igra.getTabla(), velicinaTable); 
        add(board, BorderLayout.CENTER);

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                char keyChar = e.getKeyChar();
                String pravac = "";

                switch (keyChar) {
                    case 'w': pravac = "gore"; break;
                    case 'a': pravac = "lijevo"; break;
                    case 's': pravac = "dolje"; break;
                    case 'd': pravac = "desno"; break;
                    default: return;
                }

                if (igra.pomjeri(pravac)) {
                    azurirajTablu();
                    azurirajSkor(igra.getTabla());
                }
                if (igra.jeIgraGotova()) {
                    new ScoreManager(imeIgraca, trenutniSkor);
                    JOptionPane.showMessageDialog(
                            Screen.this,
                            "Igra je gotova! Vaš rezultat: " + trenutniSkor,
                            "Kraj igre",
                            JOptionPane.INFORMATION_MESSAGE
                    );
                    new GameEnd(Screen.this);
                }
            }
        });

        setFocusable(true);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    /**
     * Kreira panel koji prikazuje trenutni i maksimalni score.
     */
    private JPanel kreirajPanelSkora() {
        JPanel panelSkora = new JPanel();
        panelSkora.setLayout(new GridLayout(1, 2));
        panelSkora.setBackground(new Color(187, 173, 160));
        panelSkora.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        trenutniSkorLabela = new JLabel("Score: 0");
        trenutniSkorLabela.setFont(new Font("Arial", Font.BOLD, 18));
        trenutniSkorLabela.setForeground(Color.WHITE);
        trenutniSkorLabela.setHorizontalAlignment(SwingConstants.LEFT);

        maksimalniSkorLabela = new JLabel("Max score: " + maksSkor);
        maksimalniSkorLabela.setFont(new Font("Arial", Font.BOLD, 18));
        maksimalniSkorLabela.setForeground(Color.WHITE);
        maksimalniSkorLabela.setHorizontalAlignment(SwingConstants.RIGHT);

        panelSkora.add(trenutniSkorLabela);
        panelSkora.add(maksimalniSkorLabela);

        return panelSkora;
    }

    /**
     * Ažurira trenutni skor sabiranjem svih vrijednosti na tabli.
     * 
     * @param novaTabla Trenutna ploča sa vrijednostima
     */
    public void azurirajSkor(int[][] novaTabla) {
        trenutniSkor = 0;
        for (int i = 0; i < novaTabla.length; i++) {
            for (int j = 0; j < novaTabla[i].length; j++) {
                trenutniSkor += novaTabla[i][j];
            }
        }

        trenutniSkorLabela.setText("Score: " + trenutniSkor);
    }

    /**
     * Ažurira tablu pozivanjem metode iz klase Board.
     */
    public void azurirajTablu() {
        board.azurirajTablu(igra.getTabla());
    }

    /**
     * Restartuje igru i vraća sve parametre na početne vrijednosti.
     */
    public void resetujIgru() {
        igra.reset();
        board.azurirajTablu(igra.getTabla());
        this.maksimalniSkor = ScoreManager.vratiNajveciRezultat();
        this.maksSkor = String.valueOf(maksimalniSkor);
        trenutniSkor = 0;
        maksimalniSkorLabela.setText("Max Score: " + maksSkor);
        trenutniSkorLabela.setText("Score: 0");
    }
}
