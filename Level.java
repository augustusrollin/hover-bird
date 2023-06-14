public class Level {
    public String levelName;
    public static int levelSpeed; 
    public static int speedIncrement;
    public static int timeInterval;
    public static int decremantTimeInterval;
    public static int decremant;
    public static int fullBoostBar;

    // Constructor
    public Level() {
        // levelName = "";
        levelSpeed = 25;
        speedIncrement = 2;
        timeInterval = 20;
        decremantTimeInterval = 50;
        decremant = 5;
        fullBoostBar = 100;
    }

    public static Level easy() {
        Level l = new Level();
        // l.levelName = "easy";
        l.levelSpeed = 25;
        l.speedIncrement = 2;
        timeInterval = 20;
        decremantTimeInterval = 50;
        decremant = 5;
        fullBoostBar = 100;
        GameRunner.PIPE_DELAY = 80 / (Obstacle.speed / 10);
        return l;
    }

    public static Level medium() {
        Level l = new Level();
        // l.levelName = "medium";
        l.levelSpeed = 30;
        l.speedIncrement = 3;
        timeInterval = 20;
        decremantTimeInterval = 50;
        decremant = 5;
        fullBoostBar = 100;
        GameRunner.PIPE_DELAY = 80 / (Obstacle.speed / 10);
        return l;
    }

    public static Level hard() {
        Level l = new Level();
        // l.levelName = "hard";
        l.levelSpeed = 40;
        l.speedIncrement = 3;
        timeInterval = 15;
        decremantTimeInterval = 50;
        decremant = 5;
        fullBoostBar = 100;
        GameRunner.PIPE_DELAY = 80 / (Obstacle.speed / 10);
        return l;
    }

    public static Level impossible() {
        Level l = new Level();
        // l.levelName = "impossible";
        l.levelSpeed = 50;
        l.speedIncrement = 4;
        timeInterval = 15;
        decremantTimeInterval = 50;
        decremant = 5;
        fullBoostBar = 100;
        GameRunner.PIPE_DELAY = 80 / (Obstacle.speed / 10);
        return l;
    }
}