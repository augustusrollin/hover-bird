import java.awt.Dimension;

public class Mode {
    public String modeName; // Name of the game mode
    public String modeBackground; // Background image for the game mode
    public static String characterImage; // Character image for the game mode (static to be shared across instances)
    public String obstacleImage; // Obstacle image for the game mode
    public static String boostedImage; // Charcter image for the game mode, only for boost active
    public Dimension boundingBox; // Dimensions of the game characters mode's bounding box
    public static String musicName;

    // Constructor
    public Mode() {
        modeName = "";
        modeBackground = "";
        characterImage = "";
        obstacleImage = "";
        boostedImage = "";
        musicName = "";
        boundingBox = new Dimension(0, 0);
    }

    // Create a new Mode instance for the future game mode
    public static Mode futureMode() {
        Mode m = new Mode();
        m.modeName = "future";
        m.modeBackground = "SpaceBackground2";
        m.characterImage = "SpaceshipTestWorking";
        m.boostedImage = "boostedSpaceship";
        m.obstacleImage = "cutskyscraperTest";
        m.musicName = "futureMusic";
        m.boundingBox = new Dimension(180, 160);
        return m;
    }

    // Create a new Mode instance for the original game mode
    /*
     * public static Mode originalMode() {
     * Mode m = new Mode();
     * m.modeName = "original";
     * m.modeBackground = "background";
     * m.characterImage = "birdWorking";
     * m.obstacleImage = "flappyPipe";
     * m.musicName = "crazyMusic";
     * m.boundingBox = new Dimension(250, 160);
     * return m;
     * }
     */
    // public static Mode originalMode() {
    // Mode m = new Mode();
    // m.modeName = "original";
    // m.modeBackground = "background";
    // m.characterImage = "birdWorking";
    // m.obstacleImage = "flappyPipe";
    // m.musicName = "crazyMusic";
    // m.boundingBox = new Dimension(250, 200);
    // return m;
    // }

    // Create a new Mode instance for the rainbow game mode
    public static Mode rainbowMode() {
        Mode m = new Mode();
        m.modeName = "rainbow";
        m.modeBackground = "rainbowBackground2";
        m.characterImage = "UnicornNew";
        m.boostedImage = "unicornBoosted";
        m.obstacleImage = "rainbowSkyscraper";
        m.musicName = "futureMusic";
        m.boundingBox = new Dimension(240, 150);
        return m;
    }

    // Create a new Mode instance for the hell game mode
    public static Mode hellMode() {
        Mode m = new Mode();
        m.modeName = "hell";
        m.modeBackground = "hellBackground4";
        m.characterImage = "hellSpaceshipWorking";
        m.boostedImage = "hellSpaceshipBoost";
        m.obstacleImage = "hellSkyscraper";
        m.musicName = "hellMusic";
        m.boundingBox = new Dimension(180, 160);
        return m;
    }

    public static Mode oceanMode() {
        Mode m = new Mode();
        m.modeName = "ocean";
        m.modeBackground = "oceanBackground";
        m.characterImage = "shark";
        m.boostedImage = "hellSpaceshipBoost";
        m.obstacleImage = "hellSkyscraper";
        m.musicName = "futureMusic";
        m.boundingBox = new Dimension(240, 240);
        return m;
    }
}
