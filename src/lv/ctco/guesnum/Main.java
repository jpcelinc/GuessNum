package lv.ctco.guesnum;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Main {
    private static Scanner scan = new Scanner(System.in);
    private static Random rand = new Random();

    public static void main(String[] args) {
        //       System.out.println( "What is your name?");
        //       String name  = scan.next();
        //       System.out.println( "My name is " + name );
        System.out.println("What is your number?");
        int mnum;
        try {
            mnum = scan.nextInt();
        } catch (InputMismatchException e)

        {
            System.out.println("You are cheater");
            return;
        }
        System.out.println("My number = " + mnum);
        for (int i = 1; i <= 10; i++) {
            int rnum = rand.nextInt(10) + 1;
            System.out.println("Spoller" + i + " = " + rnum);
            if (mnum == rnum) {
                System.out.println("ok");
                return;
            } else {
                System.out.println("not");
            }
        }
        //       }
    }
}

