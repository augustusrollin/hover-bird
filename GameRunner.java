import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Random;
public class GameRunner {

    public static final int PIPE_DELAY = 100;

    private Properties prop;

    private Boolean paused;

    private int pauseDelay;
    private int restartDelay;
    private int pipeDelay;

    private Characters character;
    private ArrayList<Obstacles> pipes;
    private Keyboard keyboard;

    public int score;
    public Boolean gameover;
    public Boolean started;

    public GameRunner() {
        keyboard = Keyboard.getInstance();
        restart();
    }

    public void restart() {
        paused = false;
        started = false;
        gameover = false;

        score = 0;
        pauseDelay = 0;
        restartDelay = 0;
        pipeDelay = 0;

        character = new Characters();
        pipes = new ArrayList<Obstacles>();
    }

    public void update() {
        watchForStart();

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
        renders.add(new Render(0, 0, "images/FuturisticSpaceBackground.png"));
        for (Obstacles pipe : pipes)
            renders.add(pipe.getRender());
        renders.add(new Render(0, 720, "images/thin_background.png"));
        renders.add(new Render(0, 0, "images/SpaceBackground2.png"));
        for (Obstacles pipe : pipes)
            renders.add(pipe.getRender());
        // renders.add(new Render(1000, 980, "images/BlackBar.png"));
        renders.add(character.getRender());
        return renders;
    }

    private void watchForStart() {
        if (!started && keyboard.isDown(KeyEvent.VK_SPACE)) {
            started = true;
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
            restart();
            restartDelay = 10;
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
                if (pipe.x - pipe.width < 0) {
                    if (northPipe == null) {
                        northPipe = pipe;
                    } else if (southPipe == null) {
                        southPipe = pipe;
                        break;
                    }
                }
            }

            if (northPipe == null) {
                Obstacles pipe = new Obstacles("north");
                pipes.add(pipe);
                northPipe = pipe;
            } else {
                northPipe.reset();
            }

            if (southPipe == null) {
                Obstacles pipe = new Obstacles("south");
                pipes.add(pipe);
                southPipe = pipe;
            } else {
                southPipe.reset();
            }

            //northPipe.y = southPipe.y + southPipe.height + 175;
            Random rand = new Random();
            southPipe.y = -rand.nextInt(401);
            northPipe.y = southPipe.y + southPipe.height + 300;
        }

        for (Obstacles pipe : pipes) {
            pipe.update();
        }
    }

    private void checkForCollisions() {

        for (Obstacles pipe : pipes) {
            if (pipe.collides(character.x, character.y, character.width, character.height)) {
                gameover = true;
                character.isDead = true;
            } else if (pipe.x == character.x && pipe.orientation.equalsIgnoreCase("south")) {
                score++;
            }
        }

        // Ground + Bird collision
        if (character.y + character.height > Window.HEIGHT - 80) {
            gameover = true;
            character.y = Window.HEIGHT - 80 - character.height;
        }
    }
}
