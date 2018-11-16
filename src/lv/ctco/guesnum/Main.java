package lv.ctco.guesnum;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.*;

public class Main {
    private static Scanner scan = new Scanner(System.in);
    private static Random rand = new Random();
    static List<GameResult> results = new ArrayList<>();
    public static final File FILE_RESULT = new File("results.txt");

    public static void main(String[] args) {
        loadResults();
        String mystart = "Y";
        do {
            System.out.println("What is your name?");
            String name = scan.next();
            System.out.println("My name is " + name);
            long t1 = System.currentTimeMillis();
            System.out.println("What is your number?");
            int mnum;
            mnum = getMnum();

            System.out.println("My number = " + mnum);
            for (int i = 1; i <= 10; i++) {
                int rnum = rand.nextInt(10) + 1;
                System.out.println("Spoller" + i + " = " + rnum);
                if (mnum == rnum) {
                    System.out.println("ok");
                    GameResult r = new GameResult();
                    r.name = name;
                    r.triesCount = i;
                    r.duration = System.currentTimeMillis() - t1;
                    results.add(r);
                    break;
                } else {
                    System.out.println("not");
                }
            }
            //       }
            String startstr = "-";
            System.out.println("Start again ?");
            while (!checkInput(startstr)) {
                System.out.println("Input doesn't match Y or N. Try again.");
                startstr = scan.next().toUpperCase();
            }
            mystart = startstr;
        } while ("Y".equals(mystart));

        //       for (GameResult r : results) {
        //           System.out.println("Result: " + r.name + "; triesCount:" + r.triesCount + "; duration:" + r.duration/1000.0 ) ;
//
        //       }
        showResults();
        saveResults();


    }

    private static void showResults() {
        results.stream()
                .sorted(Comparator.<GameResult>comparingInt(r -> r.triesCount)
                        .<GameResult>thenComparingLong(r -> r.duration))
        .limit(3)
        .forEach(r -> r.displayResult());
        ;
//        for (GameResult r : results) {
//            r.displayResult();

//        }
    }

    private static int getMnum() {
        while (true) {
            try {
                int mnum = scan.nextInt();
                if (mnum < 1 || mnum > 10) {
                    System.out.println(" 0 - 10");
                    continue;
                }
                return mnum;
            } catch (InputMismatchException e)

            {
                scan.next();
                System.out.println("You are cheater");

            }
        }
    }

    private static boolean checkInput(String input) {
        String[] inputOptions = {"Y", "N"};
        for (String i : inputOptions) {
            if (input.equals(i)) {
                return true;
            }
        }
        return false;
    }

    private static void saveResults() {
        try (PrintWriter fileOut = new PrintWriter(FILE_RESULT)) {
            int skipCount = results.size() - 5;
    //    results.stream()
     //   .filter( r -> r.name.equals("Lena") )
     //   .anyMatch(r  -> r.triesCount == 5);
            results.stream()
                    .skip(skipCount)
                    .forEach(r -> fileOut.printf("%s %d %d\n", r.name, r.triesCount, r.duration ));

     //       for (GameResult r : results) {

     //        if (skipCount <= 0 ) {  fileOut.printf("%s %d %d\n",
      //                  r.name, r.triesCount, r.duration );}
      //                  skipCount--;
      //      }
            fileOut.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    private static void loadResults() {
        try (Scanner in = new Scanner(FILE_RESULT)) {
            while (in.hasNext()) {
                GameResult gr = new GameResult();
                gr.name = in.next();
                gr.triesCount = in.nextInt();
                gr.duration = in.nextLong();
                results.add(gr);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}

