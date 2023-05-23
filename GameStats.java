public class GameStats{

    private int score;       
    private int highScore ;  
    private boolean isGameOver;

    public void incrementScore(){
        score++;
    } 
    public void reset(){
        score = 0;
    }          
    public void updateHighScore(){
        highScore++;
    }
    public int getScore(){
        return score;
    }
    public int getHighScore(){
        return highScore;
    }
    public boolean isGameOver(){
        if(isGameOver == true)
            return isGameOver;
        else return isGameOver;
    }
}