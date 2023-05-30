import java.awt.Dimension;

public class Mode {
    public String modeName;
    public String modeBackground;
    public String characterImage;
    public String obstacleImageNorth;
    public String obstacleImageSouth;
    public Dimension boundingBox;
    // public String musicFile;

    public Mode() {
        modeName = "";
        modeBackground = "";
        characterImage = "";
        obstacleImageNorth = "";
        obstacleImageSouth = "";
        boundingBox = new Dimension(0, 0);
    }

    public static Mode futureMode() {
        Mode m = new Mode();
        m.modeName = "future";
        m.modeBackground = "SpaceBackground2";
        m.characterImage = "SpaceshipTestWorking";
        m.obstacleImageNorth = "cutskyscraperTestnorth";
        m.obstacleImageSouth = "cutskyscraperTestsouth";
        m.boundingBox = new Dimension(180, 160);
        return m;

    }

    public static Mode originalMode() {
        Mode m = new Mode();
        m.modeName = "original";
        m.modeBackground = "background";
        m.characterImage = "bird";
        m.obstacleImageNorth = "";
        m.obstacleImageSouth = "";
        m.boundingBox = new Dimension(180, 160);
        return m;
    }

    public static Mode rainbowMode() {
        Mode m = new Mode();
        m.modeName = "rainbow";
        m.modeBackground = "SpaceBackground2";
        m.characterImage = "";
        m.obstacleImageNorth = "";
        m.obstacleImageSouth = "";
        m.boundingBox = new Dimension(180, 160);
        return m;
    }

    public static Mode jungleMode() {
        Mode m = new Mode();
        m.modeName = "jungle";
        m.modeBackground = "monkeybackground2";
        m.characterImage = "";
        m.obstacleImageNorth = "";
        m.obstacleImageSouth = "";
        m.boundingBox = new Dimension(180, 160);
        return m;
    }
}
