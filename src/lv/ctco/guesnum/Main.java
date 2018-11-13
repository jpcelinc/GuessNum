package lv.ctco.guesnum;

import java.util.*;

public class Main {
    private static Scanner scan = new Scanner(System.in);
    private static Random rand = new Random();
    static List<GameResult> results = new ArrayList<>();
    public static void main(String[] args) {
       String mystart = "Y" ;
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
                    r.duration =  System.currentTimeMillis() - t1;
                    results.add(r);
                    break;
                } else {
                    System.out.println("not");
                }
            }
            //       }
            System.out.println("Start again ?");
            mystart = scan.next();
        } while ( "Y".equals(mystart) );

        for (GameResult r : results) {
            System.out.println("Result: " + r.name + "; triesCount:" + r.triesCount + "; duration:" + r.duration/1000.0 ) ;

        }
        for (GameResult r : results) {
            System.out.printf("%s %d %.2f sec\n",
                    r.name,r.triesCount,r.duration/1000.0) ;

        }
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
}

