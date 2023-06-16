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
            position = new Point(620,200);
            int spacing = 50;
            int fontSize = 40;

            if (!gameRunner.started) { // game has not started
                // Display the start menu
                g2D.setFont(new Font("Futurism", Font.PLAIN, fontSize));
                // modes
                g2D.drawString("Press Enter to start", position.x, position.y + 0*spacing);
                g2D.drawString("F for Future Mode", position.x, position.y + 1*spacing);
                g2D.drawString("B for Rainbow Mode", position.x, position.y + 2*spacing);
                g2D.drawString("H for Hell Mode", position.x, position.y + 3*spacing);
                g2D.drawString("O for Ocean Mode", position.x, position.y + 4*spacing);
                g2D.drawString("P for Panda Mode", position.x, position.y + 5*spacing);
                g2D.drawString("A for Arctic Mode", position.x, position.y + 6*spacing);
                g2D.drawString("D for Desert Mode", position.x, position.y + 7*spacing);
                g2D.drawString("S for Summer Mode", position.x, position.y + 8*spacing);
                // NOTE: Leave a space inbetween the modes an levels
                //dificulties
                g2D.drawString("1 for easy difficulty", position.x, position.y + 10*spacing);
                g2D.drawString("2 for medium difficulty", position.x, position.y + 11*spacing);
                g2D.drawString("3 for hard difficulty", position.x, position.y + 12*spacing);
                g2D.drawString("4 for impossible difficulty", position.x, position.y + 13*spacing);

                g2D.drawString("Space for boost", (2 * position.x)-130, position.y + 0*spacing);
                g2D.drawString("P for pause", (2 * position.x)-130, position.y + 1*spacing);
                g2D.drawString("R for restart", (2 * position.x)-130, position.y + 2*spacing);
                g2D.drawString("Arrow keys for direction", (2 * position.x)-130, position.y + 3*spacing);
            } else {
                // Display the score and rocketfuel during gameplay
                g2D.setFont(new Font("Futurism", Font.PLAIN, fontSize));
                g2D.drawString(Integer.toString(gameRunner.score), 30, 60);
                if (!GameRunner.gameover) {
                    //g2D.drawString("Rocket Fuel: " + Integer.toString(Character.rocketFuel), 1150, 60);
                    Color darkRed = new Color(80, 0 ,0); // hell
                    Color purple = new Color(174, 55, 255); // future
                    Color pink = new Color(255, 84, 220); // rainbow
                    Color orange = new Color(255, 149, 102); // ocean
                    Color black = new Color(0, 0, 0); // panda
                    Color lightOrange = new Color(223, 183, 85); // panda
                    Color cyan = new Color(140, 226, 255); // ocean

                    if(Mode.characterImage.equals("hellSpaceshipWorking")){
                        g2D.setColor(darkRed);
                    } else if(Mode.characterImage.equals("SpaceshipTestWorking")){
                        g2D.setColor(purple);
                    } else if(Mode.characterImage.equals("UnicornNew")){
                        g2D.setColor(pink);
                    } else if(Mode.characterImage.equals("shark")){
                        g2D.setColor(orange);
                    } else if(Mode.characterImage.equals("panda")){
                        g2D.setColor(black);
                    } else if(Mode.characterImage.equals("camel")){
                        g2D.setColor(lightOrange);
                    }
                    else{
                        g2D.setColor(cyan);
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
