import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    private GameRunner game;

    public GamePanel() {
        game = new GameRunner();
        new Thread(this).start();
    }

    public void update() {
        game.update();
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        try {
            super.paintComponent(g);

            Graphics2D g2D = (Graphics2D) g;
            for (Render r : game.getRenders())
                if (r.transform != null)
                    g2D.drawImage(r.image, r.transform, null);
                else
                    g.drawImage(r.image, r.x, r.y, null);

            g2D.setColor(Color.WHITE);

            if (!game.started) {
                g2D.setFont(new Font("Futurism", Font.PLAIN, 40));
                g2D.drawString("Press SPACE to start", 650, 480);
                g2D.drawString("O for Original Mode", 650, 530);
                g2D.drawString("F for Future Mode", 650, 580);
                g2D.drawString("J for Jungle Mode", 650, 630);
                g2D.drawString("B for Rainbow Mode", 650, 680);
                g2D.drawString("H for Hell Mode", 650, 730);
            } else {
                g2D.setFont(new Font("Futurism", Font.PLAIN, 44));
                g2D.drawString(Integer.toString(game.score), 30, 60);
            }

            if (game.gameover) {
                g2D.setFont(new Font("Futurism", Font.PLAIN, 40));
                g2D.drawString("Press R to restart", 650, 480);
            }
        } catch (Exception e) {
            System.out.println("Error occured");
        }
    }

    public void run() {
        try {
            while (true) {
                update();
                Thread.sleep(16);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
