import javax.swing.*;
import java.awt.*;
import java.awt.Point;

public class GamePanel extends JPanel implements Runnable {

    public static int gameTime = 0;
    private GameRunner gameRunner;
    private Point position;

    public GamePanel() {
        gameRunner = new GameRunner();
        new Thread(this).start();
    }

    public void update() {
        gameRunner.update();
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        try {
            super.paintComponent(g);

            Graphics2D g2D = (Graphics2D) g;
            // Render all the objects in the gameRunner
            for (ImageRender imgRender : gameRunner.getRenders()) {
                if (imgRender.transform != null){
                    g2D.drawImage(imgRender.image, imgRender.transform, null);
                }
                else{
                    g.drawImage(imgRender.image, imgRender.x, imgRender.y, null);
                }
            }

            g2D.setColor(Color.WHITE);
            position = new Point(650,330);
            int spacing = 50;
            int fontSize = 40;

            if (!gameRunner.started) { // game has not started
                // Display the start menu
                g2D.setFont(new Font("Futurism", Font.PLAIN, fontSize));
                // modes
                g2D.drawString("Press SPACE to start", position.x, position.y + 0*spacing);
                g2D.drawString("F for Future Mode", position.x, position.y + 1*spacing);
                g2D.drawString("B for Rainbow Mode", position.x, position.y + 2*spacing);
                g2D.drawString("H for Hell Mode", position.x, position.y + 3*spacing);
                // NOTE: Leave a space inbetween the modes an levels
                //dificulties
                g2D.drawString("1 for easy difficulty", position.x, position.y + 5*spacing);
                g2D.drawString("2 for medium difficulty", position.x, position.y + 6*spacing);
                g2D.drawString("3 for hard difficulty", position.x, position.y + 7*spacing);
                g2D.drawString("4 for impossible difficulty", position.x, position.y + 8*spacing);
            } else {
                // Display the score and rocketfuel during gameplay
                g2D.setFont(new Font("Futurism", Font.PLAIN, fontSize));
                g2D.drawString(Integer.toString(gameRunner.score), 30, 60);
                if (!GameRunner.gameover) {
                    //g2D.drawString("Rocket Fuel: " + Integer.toString(Character.rocketFuel), 1150, 60);
                    Color darkRed = new Color(80, 0 ,0);
                    Color purple = new Color(174, 55, 255);
                    
                    // TODO: Add the other modes here
                    if(Mode.characterImage.equals("hellSpaceshipWorking")){
                        g2D.setColor(darkRed);
                    } else {
                        g2D.setColor(purple);
                    }
                    g2D.fillRect(1150,10, Character.rocketFuel/4, 60); // rocket fuel drawing
                    g2D.setColor(Color.white);
                    g2D.drawRect(1150,10, 350, 60);
                }
            }

            if (gameRunner.gameover) {
                // Display the gameRunner over message
                g2D.setFont(new Font("Futurism", Font.PLAIN, fontSize));
                g2D.drawString("Press R to restart", position.x, position.y + 5*spacing);
            }
        } catch (Exception e) {
            System.out.println("Error occurred");
        }
    }

    public void run() {
        try {
            while (true) {
                update(); // game panel update
                Thread.sleep(16); // increases the speed of the program/frames per second
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
