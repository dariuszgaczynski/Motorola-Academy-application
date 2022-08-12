public enum Difficulty {
    EASY(1, 4), HARD(2, 8);

    private final int difficultyFromUser;
    private final int wordsAmount;

    Difficulty(int difficultyFromUser, int wordsAmount) {
        this.difficultyFromUser = difficultyFromUser;
        this.wordsAmount = wordsAmount;
    }

    public int getDifficultyFromUser() {
        return difficultyFromUser;
    }

    public int getWordsAmount() {
        return wordsAmount;
    }

}
