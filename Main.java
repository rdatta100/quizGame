import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Created by Rani Datta
 * rdatta100@gmail.com
 * GitHub: rdatta100
 */

public class Main {
    public static ArrayList<FlashCard> quizGame;
    public static int size;
    public static ArrayList<String> incorrect;
    public static int numIncorrect;

    public static void main(String[] args){
        quizGame = new ArrayList<FlashCard>();
        incorrect = new ArrayList<String>();
        size = 0;
        numIncorrect = 0;

        System.out.println("\n\nWelcome to Rani's Quizzing Game :) ");
        System.out.println("Let us make some flashcards first!");
        Scanner in = new Scanner(System.in);
        start(in);
    }

    public static void start(Scanner in) {
        boolean go = true;
        System.out.println("\nType 'DONE' when you are finished");

        while(go) {
            System.out.print("\nWhat word would you like to add? ");
            String word = in.nextLine();
            if (word.equals("")) {
                System.out.println("Please type something in.");
            } else {
                if (word.equals("DONE") || word.equals("done")) {
                    go = false;
                }
                else {
                    System.out.print("What is the corresponding definition? ");
                    String def = in.nextLine();
                    quizGame.add(new FlashCard(word, def));
                    size++;
                }
                System.out.println("Added!");
            }

        }

        processCommands(in);

    }

    public static void processCommands(Scanner in) {
        try {
            System.out.println("\nNow what would you like to do with your flashcards? ");
            System.out.print("The options are 1) learn 2) flashcards 3) quizzing game 4) matching game 5) add more cards 6) exit: ");
            int input = in.nextInt();
            if (input == 1) {
                learn(in);
            } else if (input == 2) {
                flashcard(in);
            } else if (input == 3) {
                quizzing(in);
            } else if (input == 4) {
                matchingGame(in);
            } else if (input == 5) {
                start(in);
            } else if (input == 6) {
                System.out.println("\nThank you! Bye :) ");
            }
            else {
                System.out.println("\nThat is not a valid input!");
            }
        }
        catch (InterruptedException e) {

        }

    }

    public static void learn(Scanner in) throws InterruptedException {
        for (FlashCard c : quizGame) {
            System.out.println("\n" + c.getWord() + ": " + c.getDef());
            TimeUnit.SECONDS.sleep(1);
        }

        processCommands(in);
    }

    public static void flashcard(Scanner in) throws InterruptedException {
        Collections.shuffle(quizGame);

        for (FlashCard c : quizGame) {
            System.out.println("\n" + c.getDef());
            System.out.print("What word is this: ");
            String input = in.next();
            if (input.equals(c.getWord())) {
                System.out.println("Correct!");
                if (incorrect.contains(c.getWord())) {
                    incorrect.remove(c.getWord());
                    numIncorrect--;
                    c.markIncorrect(0);
                }
            }
            else {
                System.out.println("Incorrect! The correct word is: " + c.getWord());
                c.markIncorrect(1);
                if (!incorrect.contains(c.getWord())) {
                    incorrect.add(c.getWord());
                    numIncorrect++;
                }
            }
        }

        if (numIncorrect > 0) {
            System.out.println("\nThese are the words you have gotten wrong:");
            int count = 0;
            for (String str : incorrect) {
                count++;
                System.out.println("" + count + ") " + str);
                TimeUnit.SECONDS.sleep(1);
            }
        }

        processCommands(in);

    }

    public static void quizzing(Scanner in) {
        // YOU NEED MINIMUM FOUR WORDS FOR THIS GAME TO WORK!
        if (size < 4) {
            System.out.println("You need at least four words for this game to work!");
        } else {
            Game g = new Game();
            int max = 3;
            int min = 0;

            for (FlashCard c : quizGame) {
                int num = (int) ((Math.random() * (max - min)) + min);
                if (num == 0) {
                    g.game0(quizGame, c, in);
                } else if (num == 1) {
                    g.game1(quizGame, c, in);
                }
            }
        }

        processCommands(in);
    }

    public static void matchingGame(Scanner in) {
        // YOU NEED MINIMUM FOUR WORDS FOR THIS GAME TO WORK!
        if (size < 4) {
            System.out.println("You need at least four words for this game to work!");
        } else {
            Game g = new Game();
            g.game2(quizGame, in);
            System.out.println("\nCongrats! You scored a " + g.getScore() + "/" + 4);
        }

        processCommands(in);
    }


}