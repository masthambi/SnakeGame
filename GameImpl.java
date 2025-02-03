package play;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class GameImpl extends JPanel implements ActionListener, Game {

    static final int panelheight = 500;
    static final int panelwidth = 500;
    static final int unit_size = 20;
    static final int num_off_units = (panelheight * panelwidth) / (unit_size * unit_size);
    final int[] x = new int[num_off_units];
    final int[] y = new int[num_off_units];
    int snakelength = 5;
    int foodsWallowed;
    private char direction = 'd';
    int foodX;
    int foodY;
    Random random;
    Timer timer;
    boolean running = false;

    public GameImpl() {
        random = new Random();
        this.setSize(panelwidth, panelheight);
        this.setBackground(Color.black);
        this.setFocusable(true);
        this.addKeyListener(new MyKey(this));
        playGame();
    }

    public char getDirection() {
        return direction;
    }

    public void setDirection(char direction) {
        this.direction = direction;
    }

    @Override
    public void move() {
        for (int i = snakelength; i > 0; i--) {
            x[i] = x[i - 1];
            y[i] = y[i - 1];
        }
        if (direction == 'L') {
            x[0] = x[0] - unit_size;
        } else if (direction == 'R') {
            x[0] = x[0] + unit_size;
        } else if (direction == 'U') {
            y[0] = y[0] - unit_size;
        } else {
            y[0] = y[0] + unit_size;
        }
    }

    @Override
    public void checkHit() {
        for (int i = snakelength; i > 0; i--) {
            if (x[0] == x[i] && y[0] == y[i]) {
                running = false;
            }
            if (x[0] < 0 || x[0] > panelwidth || y[0] < 0 || y[0] > panelheight) {
                running = false;
            }
            if (!running) {
                timer.stop();
            }
        }
    }

    @Override
    public void addFood() {
        foodX = random.nextInt((int) (panelwidth / unit_size)) * unit_size;
        foodY = random.nextInt((int) (panelheight / unit_size)) * unit_size;
    }

    @Override
    public void checkFood() {
        if (x[0] == foodX && y[0] == foodY) {
            snakelength++;
            foodsWallowed++;
            addFood();
        }
    }

    @Override
    public void playGame() {
        running = true;
        addFood();
        timer = new Timer(200, this);
        timer.start();
    }

    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics); // This must be called to ensure the panel is cleared before drawing
        draw(graphics);
    }

    @Override
    public void draw(Graphics graphics) {
        if (running) {
            // Draw food
            graphics.setColor(new Color(214, 0, 0));
            graphics.fillOval(foodX, foodY, unit_size, unit_size);

            // Draw the snake
            graphics.setColor(Color.white);
            graphics.fillRect(x[0], y[0], unit_size, unit_size); // Snake head
            for (int i = 1; i < snakelength; i++) {
                graphics.setColor(new Color(212, 100, 215));
                graphics.fillRect(x[i], y[i], unit_size, unit_size); // Snake body
            }

            // Display score
            graphics.setColor(Color.red);
            graphics.setFont(new Font("sans serif", Font.ROMAN_BASELINE, 25));
            FontMetrics metrics = getFontMetrics(graphics.getFont());
            graphics.drawString("Score: " + foodsWallowed, (panelwidth - metrics.stringWidth("Score: " + foodsWallowed)) / 2, graphics.getFont().getSize());
        } else {
            gameOver(graphics);
        }
    }

    public void gameOver(Graphics graphics) {
        graphics.setColor(Color.white);
        graphics.setFont(new Font("sans serif", Font.ROMAN_BASELINE, 25));
        FontMetrics metrics = getFontMetrics(graphics.getFont());
        graphics.drawString("Game Over", (panelwidth - metrics.stringWidth("Game Over")) / 2, panelheight / 2);

        // Display score after game over
        graphics.setColor(Color.red);
        graphics.drawString("Score: " + foodsWallowed, (panelwidth - metrics.stringWidth("Score: " + foodsWallowed)) / 2, panelheight / 2 + 30);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (running) {
            move();
            checkFood();
            checkHit();
        }
        repaint(); // This triggers the paintComponent method
    }
}
