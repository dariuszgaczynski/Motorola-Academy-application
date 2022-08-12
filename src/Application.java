import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.time.Duration;
import java.time.Instant;
import java.util.*;

public class Application {

    public static void main(String[] args) throws FileNotFoundException {

        String fileName = "Words.txt";
        Queue<String> words = GameManagment.readFile(fileName);
        System.out.println(words);
        Game newGame = GameManagment.chooseGameType();
        Instant i1 = Instant.now();
        String[][] gameArray = GameManagment.loadGame(newGame, words);
        String[][] xxxxArray = GameManagment.createXArray(newGame, words);
        GameManagment.printArrays(newGame, gameArray, xxxxArray);
        GameManagment.continueGame(newGame, i1, gameArray, xxxxArray);

    }

}