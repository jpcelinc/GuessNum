package lv.ctco.guessnum;

public class GameResult {
    String name;
    int triesCount;
    long duration;


    public void displayResult() {
        System.out.printf("%s %d %.2f sec\n",
                name,triesCount,duration/1000.0) ;
    }
}
