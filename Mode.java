import java.awt.Dimension;

public class Mode {
    public String modeName;
    public String modeBackground;
    public String characterImage;
    public String obstacleImage;
    // public String obstacleImageSouth;
    public Dimension boundingBox;
    // public String musicFile;

    public Mode() {
        modeName = "";
        modeBackground = "";
        characterImage = "";
        obstacleImage = "";
        // obstacleImageSouth = "";
        boundingBox = new Dimension(0, 0);
    }

    public static Mode futureMode() {
        Mode m = new Mode();
        m.modeName = "future";
        m.modeBackground = "SpaceBackground2";
        m.characterImage = "SpaceshipTestWorking";
        m.obstacleImage = "cutskyscraperTest";
        // m.obstacleImageSouth = "cutskyscraperTest";
        m.boundingBox = new Dimension(180, 160);
        return m;

    }

    public static Mode originalMode() {
        Mode m = new Mode();
        m.modeName = "original";
        m.modeBackground = "background";
        m.characterImage = "bird";
        m.obstacleImage = "pipe-";
        // m.obstacleImageSouth = "pipe-";
        m.boundingBox = new Dimension(180, 160);
        return m;
    }

    public static Mode rainbowMode() {
        Mode m = new Mode();
        m.modeName = "rainbow";
        m.modeBackground = "rainbowbackground";
        m.characterImage = "";
        m.obstacleImage = "";
        // m.obstacleImageSouth = "";
        m.boundingBox = new Dimension(180, 160);
        return m;
    }

    public static Mode jungleMode() {
        Mode m = new Mode();
        m.modeName = "jungle";
        m.modeBackground = "jungleBackground";
        m.characterImage = "monkey";
        m.obstacleImage = "";
        // m.obstacleImageSouth = "";
        m.boundingBox = new Dimension(180, 160);
        return m;
    }
}
