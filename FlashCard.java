/**
 * Created by Rani Datta
 * rdatta100@gmail.com
 * GitHub: rdatta100
 */

public class FlashCard {
    String word;
    String def;
    int numIncorrect;

    FlashCard(String w, String d) {
        this.word = w;
        this.def = d;
        this.numIncorrect = 0;
    }

    public String getWord() {
        return word;
    }

    public String getDef() {
        return def;
    }

    public void markIncorrect(int val) {
        numIncorrect += val;
    }

}
