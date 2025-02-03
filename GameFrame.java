package play;

import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {

    public static void Frame() {
        GameFrame gameFrame = new GameFrame();
        GameImpl gameimpl = new GameImpl();

        gameFrame.setTitle("Snake Game");
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameFrame.setResizable(false);

        // We add the game panel to the frame
        gameFrame.add(gameimpl);
        gameimpl.setPreferredSize(new Dimension(GameImpl.panelwidth, GameImpl.panelheight));
        
     

        // Set the preferred size for the game panel
        gameimpl.setBounds(0, 0, GameImpl.panelwidth, GameImpl.panelheight);

        // Pack the frame and make it visible
        gameFrame.pack();
        gameFrame.setVisible(true);
        gameFrame.setLocationRelativeTo(null); // Center the window
    }
}


