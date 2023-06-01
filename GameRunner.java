import java.awt.AWTException;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Random;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class GameRunner {

    // public static final int PIPE_DELAY = 100;
    public static final int PIPE_DELAY = 60/(Obstacles.speed/10);
    private Properties prop;

    private Boolean paused;

    private int pauseDelay;
    private int restartDelay;
    private int pipeDelay;

    private Characters character;
    private ArrayList<Obstacles> pipes;
    private Keyboard keyboard;
    private Mode mode;

    public int score;
    public Boolean gameover;
    public static Boolean started;
    public boolean startMusic = true;

    public GameRunner() {
        keyboard = Keyboard.getInstance();
        mode = Mode.futureMode();
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

        character = new Characters(mode.characterImage, mode.boundingBox);
        pipes = new ArrayList<Obstacles>();
    }

    public void update() {
        watchForStart();
        watchForMode();
        if (!started)
            return;

        watchForPause();
        watchForReset();

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
        // renders.add(new Render(0, 0, "images/FuturisticSpaceBackground.png"));
        for (Obstacles pipe : pipes)
            renders.add(pipe.getRender());
        // renders.add(new Render(0, 720, "images/thin_background.png"));
        renders.add(new Render(0, 0, "images/"+mode.modeBackground+".png"));
        for (Obstacles pipe : pipes)
            renders.add(pipe.getRender());
        // renders.add(new Render(1000, 980, "images/BlackBar.png"));
        renders.add(character.getRender());
        return renders;
    }

    private void watchForStart() {
        if (!started && keyboard.isDown(KeyEvent.VK_SPACE)) {
            started = true;
            if (startMusic) {
                startMusic = false;
                Sounds music = new Sounds();
                try {
                    music.playSound("music/crazyMusic.wav");
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
    }

    private void watchForMode() {
        if (!started) {

            if (keyboard.isDown(KeyEvent.VK_O)) {
                mode = Mode.originalMode();
                restart();
            } else if (keyboard.isDown(KeyEvent.VK_F)) {
                mode = Mode.futureMode();
                restart();
            } else if (keyboard.isDown(KeyEvent.VK_J)) {
                mode = Mode.jungleMode();
                restart();
            } else if (keyboard.isDown(KeyEvent.VK_B)) {
                mode = Mode.rainbowMode();
                restart();
            }
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
        // Obstacles.characterBoost = 0;
        if (keyboard.isDown(KeyEvent.VK_R) && restartDelay <= 0) {
            restart();
            restartDelay = 10;
            Obstacles.characterBoost = 0;
            Characters.boosted = false;
            Sounds.clip.stop();
            return;
        }
    }

    private void movePipes() {
        pipeDelay--;

        if (pipeDelay < 0) {
            pipeDelay = PIPE_DELAY;
            Obstacles northPipe = null;
            Obstacles southPipe = null;

            // Look for pipes off the screen
            for (Obstacles pipe : pipes) {
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
                Obstacles pipe = new Obstacles("north", mode.obstacleImage);
                pipes.add(pipe);
                northPipe = pipe;
            } else {
                northPipe.reset();
            }

            if (southPipe == null) {
                Obstacles pipe = new Obstacles("south", mode.obstacleImage);
                pipes.add(pipe);
                southPipe = pipe;
            } else {
                southPipe.reset();
            }

            // northPipe.y = southPipe.y + southPipe.height + 175;
            Random rand = new Random();
            southPipe.position.y = -rand.nextInt(southPipe.boundingBox.height)-50;
            northPipe.position.y = southPipe.position.y + southPipe.boundingBox.height + 400;
        }

        for (Obstacles pipe : pipes) {
            pipe.update();
        }
    }
    private void checkForCollisions() {

        for (Obstacles pipe : pipes) {
            Sounds music = new Sounds();
            if (pipe.collides(character.position, character.boundingBox)) {
                gameover = true;
                character.isDead = true;
                Characters.rocketFuel = 10;
                Obstacles.characterBoost = 0;
            } else if (pipe.position.x == character.position.x && pipe.orientation.equalsIgnoreCase("south")) {
                score++;
            }
        }

        // Ground + Bird collision
        if (character.position.y + character.boundingBox.height > Window.HEIGHT - 80) {
            gameover = true;
            character.position.y = Window.HEIGHT - 80 - character.boundingBox.height;
        }
    }
}
