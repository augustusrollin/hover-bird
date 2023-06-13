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

    public static Boolean paused;
    private int pauseDelay; // how long it takes to pause
    private int restartDelay; // how long it takes to restart
    private int pipeDelay;

    private Character character;
    private ArrayList<Obstacle> pipes;
    private Keyboard keyboard;
    private Mode mode;
    public Level level;

    public int score;
    public static Boolean gameover;
    public static Boolean started;
    public boolean startMusic = true;

    public GameRunner() {
        keyboard = Keyboard.getInstance();
        mode = Mode.futureMode(); // default mode
        level = Level.easy(); // default level speed
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
        Obstacle.speed = 20;
        Character.sensitivity = 9;
        // restarts with same initial pipe speed, so rocket speed won't run over into
        // the nest game
        PIPE_DELAY = 80 / (Obstacle.speed / 10);
        // level.levelSpeed = 25;
        Character.boosted = false;
        Character.rocketFuel = 100;

        character = new Character(mode.characterImage, mode.boundingBox);
        pipes = new ArrayList<Obstacle>();
    }

    public void update() {

        if (!started) {
            watchForStart(); // if user picks a space it starts
            watchForMode(); // user picks a mode
            watchForDifficulty(); // user picks a difficulty
            return;
        }

        watchForPause(); // looks for when p is pressed
        watchForReset(); // looks for when r is pressed
        watchForSong(); // looks for when the music is over in order to replay it

        if (paused) {
            return;
        }

        character.update();

        if (gameover) {
            return;
        }

        movePipes();
        checkForCollisions();
    }

    public ArrayList<ImageRender> getRenders() {

        ArrayList<ImageRender> renders = new ArrayList<ImageRender>();
        for (Obstacle pipe : pipes) {
            renders.add(pipe.getRender());
        }
        renders.add(new ImageRender(0, 0, "images/" + mode.modeBackground + ".png"));
        for (Obstacle pipe : pipes) {
            renders.add(pipe.getRender());
        }
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
            if (keyboard.isDown(KeyEvent.VK_F)) { // if you click F future mode will play
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
            Character.image = ImageUtil.loadImage("images/" + Mode.characterImage + ".png");
        }
    }

    private void watchForDifficulty() {

        if (!started) {
            if (keyboard.isDown(KeyEvent.VK_1)) { // if you click 1 it will be easy difficulty
                level = Level.easy();
                restart();
            } else if (keyboard.isDown(KeyEvent.VK_2)) { // if you click 2 it will be medium difficulty
                level = Level.medium();
                restart();
            } else if (keyboard.isDown(KeyEvent.VK_3)) { // if you click 3 it will be hard difficulty
                level = Level.hard();
                restart();
            } else if (keyboard.isDown(KeyEvent.VK_4)) { // if you click 4 it will be impossible difficulty
                level = Level.impossible();
                restart();
            }
            Character.image = ImageUtil.loadImage("images/" + Mode.characterImage + ".png");
        }
    }

    private void watchForPause() {

        if (pauseDelay > 0) {
            pauseDelay--;
        }

        if (keyboard.isDown(KeyEvent.VK_P) && pauseDelay <= 0) {
            paused = !paused;
            pauseDelay = 10;
        }
    }

    private void watchForSong() {
        if (GamePanel.gameTime > Sound.clip.getMicrosecondLength() * 1000) {
            Sound player = new Sound();
            Sound.clip.stop();
            try {
                player.playSound(Mode.musicName);
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

    private void watchForReset() {

        if (restartDelay > 0) {
            restartDelay--;
        }

        if (keyboard.isDown(KeyEvent.VK_R) && restartDelay <= 0) {
            Sound.clip.stop();
            restart();
            restartDelay = 10;
            Obstacle.characterBoost = 0;
            Character.boosted = false;
            Character.image = ImageUtil.loadImage("images/" + Mode.characterImage + ".png");
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
            /*
             * for (Obstacle pipe : pipes) {
             * if (pipe.position.x - pipe.boundingBox.width < 0) {
             * if (northPipe == null) {
             * northPipe = pipe;
             * } else if (southPipe == null) {
             * southPipe = pipe;
             * break;
             * }
             * }
             * }
             */

            if (northPipe == null) {
                Obstacle pipe = new Obstacle("north", mode.obstacleImage);
                pipes.add(pipe);
                northPipe = pipe;
            } /*
               * else {
               * // northPipe.reset();
               * }
               */

            if (southPipe == null) {
                Obstacle pipe = new Obstacle("south", mode.obstacleImage);
                pipes.add(pipe);
                southPipe = pipe;
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
            Character.image = ImageUtil.loadImage("images/" + Mode.characterImage + ".png");
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
                
                if (score % 5 == 0){
                    Obstacle.speed += level.speedIncrement;
                    PIPE_DELAY =  80 / (Obstacle.speed / 9);
                    Character.sensitivity = 1 + Obstacle.speed/2;
                }
                
            } else {
                // System.out.println(pipe.position.x + " " + character.position.x);
            }
        }

        // Ground + Bird collision
        if (character.position.y + character.boundingBox.height > HoverBird.HEIGHT - 80) {
            gameover = true;
            character.position.y = HoverBird.HEIGHT - 80 - character.boundingBox.height;
        }
    }
}
