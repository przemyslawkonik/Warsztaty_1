package zad1;

import java.util.Random;
import java.util.Scanner;

/**
 * Warsztat: Gra w zgadywanie liczb.
 */
public class Main {

    public static void main(String[] args) {
        final int secretNumber = new Random().nextInt(100) + 1;
        final Scanner scan = new Scanner(System.in);
        int inputNumber;

        while (true) {
            System.out.print("Zgadnij liczbę: ");
            while (!scan.hasNextInt()) {
                System.out.print("Podaj poprawna wartosc: ");
                scan.next();
            }
            inputNumber = scan.nextInt();

            if (inputNumber < secretNumber) {
                System.out.println("Za mało");
            } else if (inputNumber > secretNumber) {
                System.out.println("Za dużo");
            } else {
                System.out.println("Zgadłeś");
                scan.close();
                return;
            }
        }
    }
}
