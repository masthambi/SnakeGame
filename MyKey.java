package play;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MyKey extends KeyAdapter {

    private GameImpl gameimpl;

    public MyKey(GameImpl gameimpl) {
        this.gameimpl = gameimpl;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        char currentDirection = gameimpl.getDirection();

        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                if (currentDirection != 'R') {
                    gameimpl.setDirection('L');
                }
                break;
            case KeyEvent.VK_RIGHT:
                if (currentDirection != 'L') {
                    gameimpl.setDirection('R');
                }
                break;
            case KeyEvent.VK_UP:
                if (currentDirection != 'D') {
                    gameimpl.setDirection('U');
                }
                break;
            case KeyEvent.VK_DOWN:
                if (currentDirection != 'U') {
                    gameimpl.setDirection('D');
                }
                break;
        }
    }
}
