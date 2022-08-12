import java.io.File;
import java.io.FileNotFoundException;
import java.time.Duration;
import java.time.Instant;
import java.util.*;

public class GameManagment {

    public static String[][] memoGamePlayNextStep(String[][] gameArray, Game game, String[][] xxxxArray) {

        Scanner scanner = new Scanner(System.in);
        String[][] array = gameArray;
        String firstWord = null;
        String secondWord = null;
        int number1 = 0;
        int number2 = 0;

        boolean error1 = true;
        boolean error2 = true;

        do {
            try {
                System.out.println("Uncover first word by coordinates (ex. A1)");
                String[] coordinates = scanner.nextLine().split("");
                if (!coordinates[0].equals("A") && !coordinates[1].equals(game.getDifficulty().toString()))
                    throw new WrongCoordinatesException("Wrong coordinations! It must be: A1, A2,...");
                number1 = Integer.parseInt(coordinates[1]) - 1;
                firstWord = array[0][number1];
                printArraysWithFirstCoordinate(game, array, number1, firstWord, xxxxArray);
                error1 = false;
            } catch (WrongCoordinatesException ex) {
                System.out.println(ex.getMessage());
            } catch (ArrayIndexOutOfBoundsException ex) {
                System.out.println("Coordinates number is too big.");
            }
        } while (error1);

        do {
            try {
                System.out.println("Uncover second word by coordinates (ex. B3)");
                String[] coordinates2 = scanner.nextLine().split("");
                if (!coordinates2[0].equals("B") && !coordinates2[1].equals(game.getDifficulty().toString()))
                    throw new WrongCoordinatesException("Wrong coordinations! It must be: B1, B2,...");
                number2 = Integer.parseInt(coordinates2[1]) - 1;
                secondWord = array[1][number2];
                printArraysWithSecondCoordinate(game, array, number2, secondWord, xxxxArray);
                error2 = false;
            } catch (WrongCoordinatesException ex) {
                System.out.println(ex.getMessage());
            } catch (ArrayIndexOutOfBoundsException ex) {
                System.out.println("Coordinates number is too big.");

            }
        } while (error2);


        if (firstWord.equals(secondWord)) {
            xxxxArray[0][number1] = firstWord;
            xxxxArray[1][number2] = firstWord;
        } else {
            game.setChances(game.getChances() - 1);
        }

        printArrays(game, array, xxxxArray);

        return xxxxArray;
    }

    public static void printArraysWithFirstCoordinate(Game game, String[][] wordsArray, int position, String word, String[][] xxxxArray) {

        System.out.println("Level: " + game.getDifficulty().toString().toLowerCase(Locale.ROOT));
        System.out.println("Guess chances: " + game.getChances());
        System.out.println();
        System.out.print(" ");
        String[][] array;
        String[][] hiddenWords = wordsArray;
        String[] alphabet = ",A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z".split(",");
        String wordToPrintInArray = word;
        int positionInArray = position;


        if (game.getDifficulty().getDifficultyFromUser() == 1) {
            array = new String[3][5];

            for (int i = 1; i < array.length; i++) {
                array[i][0] = alphabet[i];
                for (int j = 1; j < Difficulty.EASY.getWordsAmount() + 1; j++) {
                    array[i][j] = xxxxArray[i-1][j-1];
                }
            }
            array[1][positionInArray + 1] = wordToPrintInArray;
            int counter = 1;

            for (int i = 0; i < 1; i++) {
                array[0][0] = "";
                for (int j = 1; j < Difficulty.EASY.getWordsAmount() + 1; j++) {
                    array[i][j] = String.valueOf(counter);
                    counter++;
                }
            }

            for (String[] row : array) {
                for (String character : row) {
                    System.out.print(character + " ");
                }
                System.out.println();
            }


        } else if (game.getDifficulty().getDifficultyFromUser() == 2) {
            array = new String[3][9];

            for (int i = 0; i < array.length; i++) {
                array[i][0] = alphabet[i];
                for (int j = 1; j < Difficulty.HARD.getWordsAmount() + 1; j++) {
                    array[i][j] = "X";
                }
            }
            int counter = 1;

            for (int i = 0; i < 1; i++) {
                array[0][0] = "";
                for (int j = 1; j < Difficulty.HARD.getWordsAmount() + 1; j++) {
                    array[i][j] = String.valueOf(counter);
                    counter++;
                }
            }

            for (String[] row : array) {
                for (String character : row) {
                    System.out.print(character + " ");
                }
                System.out.println();
            }

        }

    }

    public static void printArraysWithSecondCoordinate(Game game, String[][] wordsArray, int position, String word, String[][] xxxxArray) {

        System.out.println("Level: " + game.getDifficulty().toString().toLowerCase(Locale.ROOT));
        System.out.println("Guess chances: " + game.getChances());
        System.out.println();
        System.out.print(" ");
        String[][] array;
        String[][] hiddenWords = wordsArray;
        String[] alphabet = ",A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z".split(",");
        String wordToPrintInArray = word;
        int positionInArray = position;


        if (game.getDifficulty().getDifficultyFromUser() == 1) {
            array = new String[3][5];

            for (int i = 1; i < array.length; i++) {
                array[i][0] = alphabet[i];
                for (int j = 1; j < Difficulty.EASY.getWordsAmount() + 1; j++) {
                    array[i][j] = xxxxArray[i-1][j-1];
                }
            }
            array[2][positionInArray + 1] = wordToPrintInArray;
            int counter = 1;

            for (int i = 0; i < 1; i++) {
                array[0][0] = "";
                for (int j = 1; j < Difficulty.EASY.getWordsAmount() + 1; j++) {
                    array[i][j] = String.valueOf(counter);
                    counter++;
                }
            }

            for (String[] row : array) {
                for (String character : row) {
                    System.out.print(character + " ");
                }
                System.out.println();
            }


        } else if (game.getDifficulty().getDifficultyFromUser() == 2) {
            array = new String[3][9];

            for (int i = 0; i < array.length; i++) {
                array[i][0] = alphabet[i];
                for (int j = 1; j < Difficulty.HARD.getWordsAmount() + 1; j++) {
                    array[i][j] = "X";
                }
            }
            int counter = 1;

            for (int i = 0; i < 1; i++) {
                array[0][0] = "";
                for (int j = 1; j < Difficulty.HARD.getWordsAmount() + 1; j++) {
                    array[i][j] = String.valueOf(counter);
                    counter++;
                }
            }

            for (String[] row : array) {
                for (String character : row) {
                    System.out.print(character + " ");
                }
                System.out.println();
            }

        }

    }

    public static void printArrays(Game game, String[][] wordsArray, String[][] xxxxArray) {

        System.out.println("Level: " + game.getDifficulty().toString().toLowerCase(Locale.ROOT));
        System.out.println("Guess chances: " + game.getChances());
        System.out.println();
        System.out.print(" ");
        String[][] array;
        String[][] hiddenWords = wordsArray;
        String[] alphabet = ",A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z".split(",");


        if (game.getDifficulty().getDifficultyFromUser() == 1) {
            array = new String[3][5];

            for (int i = 1; i < array.length; i++) {
                array[i][0] = alphabet[i];
                for (int j = 1; j < Difficulty.EASY.getWordsAmount() + 1; j++) {
                    array[i][j] = xxxxArray[i-1][j-1];
                }
            }
            int counter = 1;

            for (int i = 0; i < 1; i++) {
                array[0][0] = "";
                for (int j = 1; j < Difficulty.EASY.getWordsAmount() + 1; j++) {
                    array[i][j] = String.valueOf(counter);
                    counter++;
                }
            }

            for (String[] row : array) {
                for (String character : row) {
                    System.out.print(character + " ");
                }
                System.out.println();
            }


        } else if (game.getDifficulty().getDifficultyFromUser() == 2) {
            array = new String[3][9];

            for (int i = 0; i < array.length; i++) {
                array[i][0] = alphabet[i];
                for (int j = 1; j < Difficulty.HARD.getWordsAmount() + 1; j++) {
                    array[i][j] = "X";
                }
            }
            int counter = 1;

            for (int i = 0; i < 1; i++) {
                array[0][0] = "";
                for (int j = 1; j < Difficulty.HARD.getWordsAmount() + 1; j++) {
                    array[i][j] = String.valueOf(counter);
                    counter++;
                }
            }

            for (String[] row : array) {
                for (String character : row) {
                    System.out.print(character + " ");
                }
                System.out.println();
            }

        }

    }

    public static String[][] createXArray(Game game, Queue<String> words) {
        String[][] wordsArray = new String[2][game.getDifficulty().getWordsAmount()];
        for (int i = 0; i < wordsArray.length; i++) {
            for (int j = 0; j < game.getDifficulty().getWordsAmount(); j++) {
                wordsArray[i][j] = "X";
            }
        }
        return wordsArray;
    }

    public static String[][] loadGame(Game game, Queue<String> words) {
        int wordsNumber = game.getDifficulty().getWordsAmount();
        int counter = 0;

        String[] firstRow = new String[wordsNumber];
        String[] secondRow = new String[wordsNumber];

        Collections.shuffle((List<?>) words);

        while (counter < wordsNumber) {
            firstRow[counter] = words.poll();
            counter++;
        }

        secondRow = Arrays.copyOf(firstRow, firstRow.length);
        Collections.shuffle(Arrays.asList(secondRow));

        String[][] wordsArray = new String[2][game.getDifficulty().getWordsAmount()];
        for (int i = 0; i < 1; i++) {
            for (int j = 0; j < game.getDifficulty().getWordsAmount(); j++) {
                wordsArray[i][j] = firstRow[j];
            }
        }

        for (int i = 1; i < 2; i++) {
            for (int j = 0; j < game.getDifficulty().getWordsAmount(); j++) {
                wordsArray[i][j] = secondRow[j];
            }
        }

        return wordsArray;

    }

    public static Game chooseGameType() {
        Scanner scanner = new Scanner(System.in);
        boolean error = true;
        int gameType = 0;



        do {
            System.out.println("Press " + Difficulty.EASY.getDifficultyFromUser() + " for Easy mode");
            System.out.println("Press " + Difficulty.HARD.getDifficultyFromUser() + " for Hard mode");
            System.out.print("Choose difficulty level: ");
            try {
                gameType = scanner.nextInt();
                scanner.nextLine();
                if (gameType == 1) {
                    System.out.println("Difficulty level: Easy");
                } else if (gameType == 2) {
                    System.out.println("Difficulty level: Hard");
                }
                if (gameType < 1 || gameType > 2)
                    throw new WrongDifficultyLevelNumberException("Chosen wrong difficulty level. Choose 1 or 2!");
                error = false;
            } catch (InputMismatchException ex) {
                System.out.println("Chosen wrong difficulty level. Choose integer!");
                scanner.nextLine();
            } catch (WrongDifficultyLevelNumberException ex) {
                System.out.println(ex.getMessage());
            }

        } while (error);

        Game game = new Game(gameType);

        return game;
    }

    public static Queue<String> readFile(String fileName) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(fileName));
        int lines = countLines(fileName);
        Queue<String> words = new LinkedList<>();
        for (int i = 0; i < lines; i++) {
            String word = scanner.nextLine();
            words.add(word);
        }
        return words;
    }

    public static void continueGame(Game newGame, Instant i1, String[][] gameArray, String[][] xxxxArray) {
        boolean gameStatus = false;
        do {
            xxxxArray = GameManagment.memoGamePlayNextStep(gameArray, newGame, xxxxArray);
            gameStatus = GameManagment.checkArrays(gameArray, xxxxArray);
        } while (newGame.getChances() > 0 && !gameStatus);
        Instant i2 = Instant.now();
        Duration duration = Duration.between(i1, i2);

        if (gameStatus) {
            System.out.println("Congratulations! You won!");
            System.out.println("You solved the memory game with " + newGame.getChances() + " chances left!");
            System.out.println("It took you: " + duration.getSeconds() + " seconds.");
        } else {
            System.out.println("You loose.");
        }
    }

    public static int countLines(String fileName) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(fileName));
        int lines = 0;
        while (scanner.hasNextLine()) {
            lines++;
            scanner.nextLine();
        }
        return lines;
    }

    public static boolean checkArrays(String[][] gameArray, String[][] xxxxArray) {
        return Arrays.deepEquals(gameArray, xxxxArray);
    }

}