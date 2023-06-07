import java.awt.Dimension;

public class Mode {
    public String modeName; // Name of the game mode
    public String modeBackground; // Background image for the game mode
    public static String characterImage; // Character image for the game mode (static to be shared across instances)
    public String obstacleImage; // Obstacle image for the game mode
    public static String boostedImage; // Charcter image for the game mode, only for boost active
    public Dimension boundingBox; // Dimensions of the game mode's bounding box

    // Constructor
    public Mode() {
        modeName = "";
        modeBackground = "";
        characterImage = "";
        obstacleImage = "";
        boostedImage = "";
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
        m.boundingBox = new Dimension(180, 160);
        return m;
    }

    // Create a new Mode instance for the original game mode
    public static Mode originalMode() {
        Mode m = new Mode();
        m.modeName = "original";
        m.modeBackground = "background";
        m.characterImage = "bird";
        Mode.boostedImage = "bird";
        m.obstacleImage = "pipe-";
        m.boundingBox = new Dimension(200, 160);
        return m;
    }

    // Create a new Mode instance for the rainbow game mode
    public static Mode rainbowMode() {
        Mode m = new Mode();
        m.modeName = "rainbow";
        m.modeBackground = "rainbowbackground";
        m.characterImage = "UnicornWorking";
        Mode.boostedImage = "UnicornWorking";
        m.obstacleImage = "rainbowPipe";
        m.boundingBox = new Dimension(340, 270);
        return m;
    }

    // Create a new Mode instance for the hell game mode
    public static Mode hellMode() {
        Mode m = new Mode();
        m.modeName = "hell";
        m.modeBackground = "hellBackground5Clean";
        m.characterImage = "hellSpaceshipWorking";
        m.boostedImage = "hellSpaceshipBoost";
        Mode.boostedImage = "hellSpaceshipBoost";
        m.obstacleImage = "hellSkyscraper";
        m.boundingBox = new Dimension(180, 160);
        return m;
    }
}
