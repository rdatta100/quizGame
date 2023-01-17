import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Rani Datta
 * rdatta100@gmail.com
 * GitHub: rdatta100
 */

public class Game {
    int score;
    Game() {
        score = 0;
    }

    public void game0(ArrayList<FlashCard> quizGame, FlashCard c, Scanner in) {
        List<Integer> temp = new ArrayList<>();

        for (int i = 0; i < quizGame.size(); i++) {
            if (i != quizGame.indexOf(c)) {
                temp.add(i);
            }
        }
        Collections.shuffle(temp);
        int choice1 = temp.get(0);
        int choice2 = temp.get(1);
        int choice3 = temp.get(2);

        List<Integer> order = new ArrayList<Integer>() {{
            add(0);
            add(1);
            add(2);
            add(3);
        } };

        Collections.shuffle(order);

        int count = 1; // alphabet?
        System.out.println("\nDefinition: " + c.getDef());
        for (Integer i : order) {
            String str = "" + count + ") ";
            if (i == 0) {
                str += c.getWord();
            } else if (i == 1) {
                str += quizGame.get(choice1).getWord();
            } else if (i == 2) {
                str += quizGame.get(choice2).getWord();
            } else if (i == 3) {
                str += quizGame.get(choice3).getWord();
            }
            System.out.println(str);
            count++;
        }

        System.out.print("Which choice matches this definition? ");
        int input = in.nextInt();
        if(input == (order.indexOf(0)+1)) {
            System.out.println("Correct!");
            score++;
        }
        else {
            System.out.println("Incorrect :( The correct word is " + c.getWord());
        }

    }

    public void game1(ArrayList<FlashCard> quizlet, FlashCard c, Scanner in) {
        System.out.print("\nIs the definition of " + c.getWord() + ":");
        int max = 2;
        int num = (int) ((Math.random() * (max)));

        if (num == 0) {
            System.out.print("\n" + c.getDef());
        } else {
            int index = quizlet.indexOf(c);
            while (index == quizlet.indexOf(c)) {
                index = (int) (Math.random() * (quizlet.size()));
            }
            System.out.print("\n" + quizlet.get(index).getDef());
        }

        System.out.print("\nT or F ");
        String input = in.next().toLowerCase();
        if ((input.equals("t") && num == 0) || (input.equals("f") && num == 1))  {
            System.out.print("Correct!\n");
            score++;
        }
        else {
            System.out.println("Incorrect :( The correct definition of " + c.getWord() + " is " + c.getDef() + "\n");
        }


    }

    public void game2(ArrayList<FlashCard> quizlet, Scanner in) {
        List<Integer> temp = new ArrayList<>();

        for (int i = 0; i < quizlet.size(); i++) {
            temp.add(i);
        }

        Collections.shuffle(temp);
        ArrayList<FlashCard> choices = new ArrayList<>();
        ArrayList<String> choicesDefs = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            FlashCard hi = quizlet.get(temp.get(i));
            choices.add(hi);
            choicesDefs.add(hi.getDef());
        }

        Collections.shuffle(choicesDefs);

        System.out.printf(" %-50s  %-100s %n", "WORD", "DEFINITION ");
        int count = 1;
        for (int i = 0; i < choices.size(); i++) {
            String col1 = choices.get(i).getWord();
            String col2 = choicesDefs.get(i);
            System.out.printf(" %-50s  %-100s %n", col1, "" + count + ") " + col2);
            count++;
        }


        for (FlashCard card : choices) {
            System.out.print("\nWhich choice goes with " + card.getWord() + "? ");
            int input = in.nextInt();
            int correctNum = choicesDefs.indexOf(card.getDef()) + 1;
            if (input == correctNum) {
                System.out.println("Correct!");
                score++;
            } else {
                System.out.println("Incorrect! The correct choice is " + correctNum);
            }
        }

    }

    public int getScore() {
        return score;
    }

}
