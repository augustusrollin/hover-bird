import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    public static int gameTime = 0;
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
            // Render all the objects in the game
            for (ImageRender r : game.getRenders()) {
                if (r.transform != null){
                    g2D.drawImage(r.image, r.transform, null);
                }
                else{
                    g.drawImage(r.image, r.x, r.y, null);
                }
            }

            g2D.setColor(Color.WHITE);

            if (!game.started) {
                // Display the start menu
                g2D.setFont(new Font("Futurism", Font.PLAIN, 40));
                g2D.drawString("Press SPACE to start", 650, 330);
                g2D.drawString("F for Future Mode", 650, 380);
                g2D.drawString("B for Rainbow Mode", 650, 430);
                g2D.drawString("H for Hell Mode", 650, 480);

                g2D.drawString("1 for easy difficulty", 650, 580);
                g2D.drawString("2 for medium difficulty", 650, 630);
                g2D.drawString("3 for hard difficulty", 650, 680);
                g2D.drawString("4 for impossible difficulty", 650, 730);
            } else {
                // Display the score and rocketfuel during gameplay
                g2D.setFont(new Font("Futurism", Font.PLAIN, 44));
                g2D.drawString(Integer.toString(game.score), 30, 60);
                if (!GameRunner.gameover) {
                    //g2D.drawString("Rocket Fuel: " + Integer.toString(Character.rocketFuel), 1150, 60);
                    Color darkRed = new Color(80, 0 ,0);
                    Color purple = new Color(174, 55, 255);
                    if(Mode.characterImage.equals("hellSpaceshipWorking")){
                        g2D.setColor(darkRed);
                    } else {
                        g2D.setColor(purple);
                    }
                    g2D.fillRect(1150,10, Character.rocketFuel/4, 60);
                    g2D.setColor(Color.white);
                    g2D.drawRect(1150,10, 350, 60);
                }
            }

            if (game.gameover) {
                // Display the game over message
                g2D.setFont(new Font("Futurism", Font.PLAIN, 40));
                g2D.drawString("Press R to restart", 650, 480);
            }
        } catch (Exception e) {
            System.out.println("Error occurred");
        }
    }

    public void run() {
        try {
            while (true) {
                update();
                Thread.sleep(22); // increases the speed of the program
                gameTime += 16;
                if (gameTime % 20 == 0 && !GameRunner.paused && Character.rocketFuel < 1400) {
                    Character.rocketFuel++;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
