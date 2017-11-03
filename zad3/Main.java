package zad3;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Warsztat: Gra w zgadywanie liczb 2.
 */
public class Main {

    public static void main(String[] args) {

        System.out.println("Pomyśl liczbę od 0 do 1000, a ja ją zgadnę w max. 10 próbach.");
        int attempts = 10, min = 0, max = 1000;
        int guess = ((max - min) / 2) + min;

        while (attempts > 0) {
            System.out.println("Zgaduję: " + guess);
            System.out.println("1 - więcej");
            System.out.println("2 - mniej");
            System.out.println("3 - trafiłeś");
            System.out.print("Wybierz: ");

            try {
                int answer = new Scanner(System.in).nextInt();

                switch (answer) {
                    case 1: {
                        min = guess;
                        break;
                    }
                    case 2: {
                        max = guess;
                        break;
                    }
                    case 3: {
                        System.out.println("Wygrałem");
                        System.exit(0);
                    }
                    default: {
                        System.out.println("Nie oszukuj");
                        continue;
                    }
                }
            } catch (InputMismatchException e) {
                System.out.println("Nie oszukuj");
            }

            guess = ((max - min) / 2) + min;
            attempts--;
        }
    }
}
