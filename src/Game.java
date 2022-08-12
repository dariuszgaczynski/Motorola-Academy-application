public class Game {

    private Difficulty difficulty;
    private int chances;

    public Game(int userAnswer) {
        if (userAnswer == 1) {
            this.difficulty = Difficulty.EASY;
            this.chances = 10;
        } else if (userAnswer == 2) {
            this.difficulty = Difficulty.HARD;
            this.chances = 15;
        }

    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public int getChances() {
        return chances;
    }

    public void setChances(int chances) {
        this.chances = chances;
    }

}