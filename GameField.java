import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

public class GameField extends JPanel implements ActionListener {
    private final int SIZE = 570;
    private final int DOT_SIZE = 16;
    private final int ALL_DOTS = 1225;
    private Image dot;
    private Image apple;
    private Image head;
    private Image headl;
    private Image headu;
    private Image headd;
    private int appleX;
    private int appleY;
    private int score = 0;
    private int[] x = new int[ALL_DOTS];
    private int[] y = new int[ALL_DOTS];
    private int dots;
    private Timer timer;
    private boolean left = false;
    private boolean right = true;
    private boolean up = false;
    private boolean down = false;
    private boolean inGame = true;

    public GameField() {
        setBackground(Color.black);
        loadImages();
        initGame();
        addKeyListener(new FieldKeyListener());
        setFocusable(true);
    }

    public void initGame() {
        dots = 3;
        for (int i = 0; i < dots; i++) {
            x[i] = 48 - i * DOT_SIZE;
            y[i] = 48;
        }
        timer = new Timer(150, this);
        timer.start();
        createApple();
    }

    public void createApple() {
        appleX = new Random().nextInt(35) * DOT_SIZE;
        appleY = new Random().nextInt(35) * DOT_SIZE;
    }

    public void loadImages() {
        ImageIcon a = new ImageIcon("apple.png");
        apple = a.getImage();
        ImageIcon d = new ImageIcon("body.jpg");
        dot = d.getImage();
        ImageIcon h = new ImageIcon("head.jpg");
        head = h.getImage();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (inGame) {
            g.setColor(Color.green);
            g.drawString("Your score: " + score, 30, 30);
            g.drawImage(apple, appleX, appleY, this);
            g.drawImage(head, x[0], y[0], this);
            for (int i = 1; i < dots; i++) {
                g.drawImage(dot, x[i], y[i], this);
            }
        } else {
            String str1 = "Final score: ";
            g.setColor(Color.white);
            g.drawString(str1 + score, 50, SIZE / 2 - 100);
            String str2 = "Game over";
            g.setColor(Color.white);
            g.drawString(str2, SIZE / 2 - 100, SIZE / 2);
            initGame();
        }
    }

    public void move() {
        for (int i = dots; i > 0; i--) {
            x[i] = x[i - 1];
            y[i] = y[i - 1];
        }
        if (left) {
            x[0] -= DOT_SIZE;
        }
        if (right) {
            x[0] += DOT_SIZE;
        }
        if (up) {
            y[0] -= DOT_SIZE;
        }
        if (down) {
            y[0] += DOT_SIZE;
        }
    }

    public void checkApple() {
        if (x[0] == appleX && y[0] == appleY) {
            dots++;
            score++;

            createApple();
        }
    }

    public void checkCollisions() {
        for (int i = dots; i > 0; i--) {
            if (i > 4 && x[0] == x[i] && y[0] == y[i]) {
                inGame = false;
            }
        }
        if (x[0] > SIZE || x[0] < 0 || y[0] > SIZE || y[0] < 0) {
            inGame = false;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (inGame) {
            checkApple();
            checkCollisions();
            move();
        }
        repaint();
    }

    class FieldKeyListener extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            super.keyPressed(e);
            int key = e.getKeyCode();
            if (key == KeyEvent.VK_LEFT && !right) {
                left = true;
                up = false;
                down = false;
                ImageIcon hl = new ImageIcon("headl.jpg");
                head = hl.getImage();
            }
            if (key == KeyEvent.VK_RIGHT && !left) {
                right = true;
                up = false;
                down = false;
                ImageIcon h = new ImageIcon("head.jpg");
                head = h.getImage();
            }
            if (key == KeyEvent.VK_UP && !down) {
                up = true;
                left = false;
                right = false;
                ImageIcon hu = new ImageIcon("headu.jpg");
                head = hu.getImage();
            }
            if (key == KeyEvent.VK_DOWN && !up) {
                down = true;
                left = false;
                right = false;
                ImageIcon hd = new ImageIcon("headd.jpg");
                head = hd.getImage();
            }
        }
    }
}