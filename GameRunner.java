import java.awt.AWTException;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Random;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.geom.*;
import javax.swing.*;
import java.awt.*;
import java.lang.Math;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.*;
import java.util.Random;

public class GameRunner {
    public static int PIPE_DELAY = 80 / (Obstacle.speed / 10); // the lower the number, the faster the pipes move
    private Properties prop;

    private Boolean paused;
    private int pauseDelay; // how long it takes to pause
    private int restartDelay; // how long it takes to restart
    private int pipeDelay;

    private Character character;
    private ArrayList<Obstacle> pipes;
    private Keyboard keyboard;
    private Mode mode;

    public int score;
    public static Boolean gameover;
    public static Boolean started;
    public boolean startMusic = true;

    public GameRunner() {
        keyboard = Keyboard.getInstance();
        mode = Mode.futureMode(); // default mode
        // mode = Mode.originalMode();
        restart();
    }

    public void restart() {
        // put mode in here
        paused = false;
        started = false;
        gameover = false;

        score = 0;
        pauseDelay = 0;
        restartDelay = 0;
        pipeDelay = 0;
        GamePanel.gameTime = 0;

        // restarts with same initial pipe speed, so rocket speed won't run over into
        // the nest game
        PIPE_DELAY = 80 / (Obstacle.speed / 10);
        Character.boosted = false;
        Character.rocketFuel = 400;

        character = new Character(mode.characterImage, mode.boundingBox);
        pipes = new ArrayList<Obstacle>();
    }

    public void update() {

        watchForStart(); // if user picks a space it starts
        watchForMode(); // user picks a mode
        if (!started)
            return;

        watchForPause(); // looks for when p is pressed
        watchForReset(); // looks for when r is pressed

        if (paused)
            return;

        character.update();

        if (gameover)
            return;

        movePipes();
        checkForCollisions();
    }

    public ArrayList<Render> getRenders() {

        ArrayList<Render> renders = new ArrayList<Render>();
        for (Obstacle pipe : pipes)
            renders.add(pipe.getRender());
        renders.add(new Render(0, 0, "images/" + mode.modeBackground + ".png"));
        for (Obstacle pipe : pipes)
            renders.add(pipe.getRender());
        renders.add(character.getRender());
        return renders;
    }

    private void watchForStart() {
        // checks to see if space was clicked
        if (!started && keyboard.isDown(KeyEvent.VK_SPACE)) {
            started = true;
            Obstacle.characterBoost = 0;
            Character.boosted = false;
            Sound audioPlayer = new Sound();
            try {
                audioPlayer.playSound("music/" + Mode.musicName + ".wav");
            } catch (AWTException e) {
                e.printStackTrace();
            } catch (UnsupportedAudioFileException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (LineUnavailableException e) {
                e.printStackTrace();
            }
        }
    }

    private void watchForMode() {

        if (!started) {
            if (keyboard.isDown(KeyEvent.VK_O)) { // if you click O original mode will play
                mode = Mode.originalMode();
                restart();
            } else if (keyboard.isDown(KeyEvent.VK_F)) { // if you click F future mode will play
                mode = Mode.futureMode();
                restart();
            } else if (keyboard.isDown(KeyEvent.VK_B)) { // if you click B rainbow mode will play
                mode = Mode.rainbowMode();
                restart();
            } else if (keyboard.isDown(KeyEvent.VK_H)) { // if you click H Hell mode will play
                mode = Mode.hellMode();
                Mode.boostedImage = "hellSpaceshipBoost";
                restart();
            }
            Character.image = Util.loadImage("images/" + Mode.characterImage + ".png");
        }
    }

    private void watchForPause() {

        if (pauseDelay > 0)
            pauseDelay--;

        if (keyboard.isDown(KeyEvent.VK_P) && pauseDelay <= 0) {
            paused = !paused;
            pauseDelay = 10;
        }
    }

    private void watchForReset() {

        if (restartDelay > 0)
            restartDelay--;

        if (keyboard.isDown(KeyEvent.VK_R) && restartDelay <= 0) {
            Sound.clip.stop();
            restart();
            restartDelay = 10;
            Obstacle.characterBoost = 0;
            Character.boosted = false;
            Character.image = Util.loadImage("images/" + Mode.characterImage + ".png");
            return;
        }
    }

    private void movePipes() {

        pipeDelay--;

        if (pipeDelay < 0) {
            pipeDelay = PIPE_DELAY;
            Obstacle northPipe = null;
            Obstacle southPipe = null;

            // Look for pipes off the screen
            for (Obstacle pipe : pipes) {
                if (pipe.position.x - pipe.boundingBox.width < 0) {
                    if (northPipe == null) {
                        northPipe = pipe;
                    } else if (southPipe == null) {
                        southPipe = pipe;
                        break;
                    }
                }
            }

            if (northPipe == null) {
                Obstacle pipe = new Obstacle("north", mode.obstacleImage);
                pipes.add(pipe);
                northPipe = pipe;
            } else {
                northPipe.reset();
            }

            if (southPipe == null) {
                Obstacle pipe = new Obstacle("south", mode.obstacleImage);
                pipes.add(pipe);
                southPipe = pipe;
            } else {
                southPipe.reset();
            }

            Random rand = new Random();
            southPipe.position.y = -rand.nextInt(southPipe.boundingBox.height);
            northPipe.position.y = southPipe.position.y + southPipe.boundingBox.height + 400;
        }
        for (Obstacle pipe : pipes) {
            pipe.update();
        }
        if (Character.boosted) {
            Character.rocketFuel--;
        }
        if (Character.rocketFuel < 1) {
            Obstacle.characterBoost = 0;
            PIPE_DELAY = 80 / (Obstacle.speed / 10);
            Character.sensitivity = 9;
            Character.boosted = false;
            Character.image = Util.loadImage("images/" + Mode.characterImage + ".png");
        }
    }

    private void checkForCollisions() {
        // pipe + bird collision
        for (Obstacle pipe : pipes) {
            int buffer = (Obstacle.speed + (int) Obstacle.characterBoost) / 2;
            if (pipe.collides(character.position, character.boundingBox)) {
                gameover = true;
                character.isDead = true;
                Character.rocketFuel = 10;
                Obstacle.characterBoost = 0;
            } else if (((pipe.position.x - buffer <= character.position.x)
                    && (pipe.position.x + buffer >= character.position.x))
                    && pipe.orientation.equalsIgnoreCase("south")) {
                score++;
            } else {
                System.out.println(pipe.position.x + " " + character.position.x);
            }
        }

        // Ground + Bird collision
        if (character.position.y + character.boundingBox.height > Window.HEIGHT - 80) {
            gameover = true;
            character.position.y = Window.HEIGHT - 80 - character.boundingBox.height;
        }
    }
}
