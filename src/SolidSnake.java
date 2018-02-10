import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.Stack;

public class SolidSnake extends JPanel {
    int x = 0;
    int y = 0;
    static int DIRECTION = 0;
    Random rand = new Random();
    ArrayList<Pair> body = new ArrayList<Pair>();

    private void moveBall() {

    }


    public void printSnake(ArrayList<Pair> snake, Graphics2D g2d) {
        for (int i = 0; i < snake.size(); i++) {
            g2d.setColor(Color.BLACK);
            g2d.fillRect(snake.get(i).getFi() + 1, snake.get(i).getSe(), 9, 9);
        }
    }

    public void init() {
        body.add(new Pair(0, 0));
        body.add(new Pair(10, 0));
        body.add(new Pair(20, 0));

    }

    public int modulus(int x) {
        if (x >= 400)
            x = 0;
        if(x < 0)
            x=390;
        return x;

    }

    public void traversal() {
        if (body.size() > 0) {

            for (int i = body.size() - 2; i >= 0; i--) {
                body.get(i).setFi(body.get(i + 1).getFi());
                body.get(i).setSe(body.get(i + 1).getSe());
            }
            int topx = body.get(body.size() - 1).getFi();
            int topy = body.get(body.size() - 1).getSe();
            switch (DIRECTION) {
                case 0:
                    body.get(body.size() - 1).setFi(modulus(topx + 10));
                    break;
                case 1:
                    body.get(body.size() - 1).setFi(modulus(topx - 10));
                    break;
                case 2:
                    body.get(body.size() - 1).setSe(modulus(topy + 10));
                    break;
                case 3:
                    body.get(body.size() - 1).setSe(modulus(topy - 10));
                    break;
            }

        }
    }


    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        printSnake(body, g2d);
       /* g2d.setColor(Color.BLACK);
        g2d.fillRect(x, y, 10, 10);*/

    }

    public static void main(String args[]) {
        SolidSnake snake = new SolidSnake();
        snake.init();
        JFrame jFrame = new JFrame("Sample Game");
        jFrame.add(snake);
        jFrame.setSize(400, 400);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.setVisible(true);
        jFrame.setResizable(false);
        jFrame.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == 39) {
                    if (DIRECTION != 1)
                        DIRECTION = 0;
                }
                if (e.getKeyCode() == 37) {
                    if (DIRECTION != 0)
                        DIRECTION = 1;
                }
                if (e.getKeyCode() == 40) {
                    if (DIRECTION != 3)
                        DIRECTION = 2;
                }
                if (e.getKeyCode() == 38) {
                    if (DIRECTION != 2)
                        DIRECTION = 3;
                }

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });


        while (true) {
            snake.traversal();
            snake.repaint();

            try {
                Thread.sleep(105);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


}

class Pair {
    private Integer fi;
    private Integer se;

    Pair(int x, int y) {
        fi = x;
        se = y;
    }


    public Integer getFi() {
        return fi;
    }

    public void setFi(Integer fi) {
        this.fi = fi;
    }

    public Integer getSe() {
        return se;
    }

    public void setSe(Integer se) {
        this.se = se;
    }
}
