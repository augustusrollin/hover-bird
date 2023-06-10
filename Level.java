public class Level {
    public String levelName;
    public static int levelSpeed; 


    // Constructor
    public Level() {
        // levelName = "";
        levelSpeed = 25;
    }

    public static Level easy() {
        Level l = new Level();
        // l.levelName = "easy";
        l.levelSpeed = 25;
        return l;
    }

    public static Level medium() {
        Level l = new Level();
        // l.levelName = "medium";
        l.levelSpeed = 30;
        return l;
    }

    public static Level hard() {
        Level l = new Level();
        // l.levelName = "hard";
        l.levelSpeed = 40;
        return l;
    }

    public static Level rodent() {
        Level l = new Level();
        // l.levelName = "rodent";
        l.levelSpeed = 50;
        return l;
    }
}