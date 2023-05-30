import java.awt.Dimension;

public class Mode {
    public String modeName;
    public String characterImage;
    public String obstacleImageNorth;
    public String obstacleImageSouth;
    public Dimension boundingBox;
    // public String musicFile;

    public Mode(){
        modeName = "";
        characterImage = "";
        obstacleImageNorth = "";
        obstacleImageSouth = "";
        boundingBox = new Dimension(0,0);
    }          

    public static Mode futureMode(){
        Mode m = new Mode();
        m.modeName = "future";
        m.characterImage = "SpaceshipTestWorking";
        m.obstacleImageNorth = "cutskyscraperTestnorth";
        m.obstacleImageSouth = "cutskyscraperTestsouth";
        m.boundingBox = new Dimension(180,160);
        return m;

    }
    public static Mode originalMode(){
        Mode m = new Mode();
        m.modeName = "original";
        m.characterImage = "bird";
        m.obstacleImageNorth = "";
        m.obstacleImageSouth = "";
        m.boundingBox = new Dimension(32,40);
        return m;
    }
    public static Mode rainbowMode(){
        Mode m = new Mode();
        m.modeName = "rainbow";
        m.characterImage = "";
        m.obstacleImageNorth = "";
        m.obstacleImageSouth = "";
        m.boundingBox = new Dimension(180,160);
        return m;
    }
    public static Mode jungleMode(){
        Mode m = new Mode();
        m.modeName = "jungle";
        m.characterImage = "";
        m.obstacleImageNorth = "";
        m.obstacleImageSouth = "";
        m.boundingBox = new Dimension(180,160);
        return m;
    }
}
