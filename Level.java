public class Level {
    public String levelName;
    public static int levelSpeed; 
    public static int speedIncrement;
    public static int timeInterval;

    // Constructor
    public Level() {
        // levelName = "";
        levelSpeed = 25;
        speedIncrement = 1;
    }

    public static Level easy() {
        Level l = new Level();
        // l.levelName = "easy";
        l.levelSpeed = 25;
        l.speedIncrement = 1;
        GameRunner.PIPE_DELAY = 80 / (Obstacle.speed / 10);
        return l;
    }

    public static Level medium() {
        Level l = new Level();
        // l.levelName = "medium";
        l.levelSpeed = 30;
        l.speedIncrement = 2;
        GameRunner.PIPE_DELAY = 80 / (Obstacle.speed / 10);
        return l;
    }

    public static Level hard() {
        Level l = new Level();
        // l.levelName = "hard";
        l.levelSpeed = 40;
        l.speedIncrement = 3;
        GameRunner.PIPE_DELAY = 80 / (Obstacle.speed / 10);
        return l;
    }

    public static Level impossible() {
        Level l = new Level();
        // l.levelName = "impossible";
        l.levelSpeed = 50;
        l.speedIncrement = 4;
        GameRunner.PIPE_DELAY = 80 / (Obstacle.speed / 10);
        return l;
    }
}