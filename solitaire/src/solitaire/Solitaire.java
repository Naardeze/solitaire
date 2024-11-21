package solitaire;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import solitaire.card.Card;

final public class Solitaire extends JDialog {
    final public static JButton AUTO = new JButton("AUTO");
    
    public static enum GameType {
        Card_1(1), Card_3(3);
        
        final private int value;
        
        GameType(int value) {
            this.value = value;
        }
        
        public int getValue() {
            return value;
        }
    }
    
    private Game game = new Game(GameType.Card_1);
    
    private Solitaire() {
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Game");
        
        for (GameType gameType : GameType.values()) {
            menu.add(gameType.name()).addActionListener(e -> {
                game = new Game(gameType);

                setContentPane(game);
                validate();
            });
        }
        
        AUTO.setBorder(BorderFactory.createLineBorder(Color.darkGray));
        AUTO.addActionListener(e -> {
            AUTO.setVisible(false);
            
            game.removeMouseListener(game);
            new Thread(game).start();
        });
        
        menuBar.add(menu);
        menuBar.add(AUTO);
        
        setTitle("Solitaire");
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setResizable(false);
        setJMenuBar(menuBar);
        setContentPane(game);
        pack();
        setVisible(true);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) throws IOException {
        Card.face = ImageIO.read(new File("face.png"));
        
        new Solitaire();
    }
    
}
