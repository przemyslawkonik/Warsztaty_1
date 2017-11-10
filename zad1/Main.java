package zad1;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

/**
 * Warsztat: Gra w zgadywanie liczb.
 */
public class Main {

	public static void main(String[] args) {
		final int secretNumber = new Random().nextInt(100) + 1;
		int inputNumber = 0;
		final Scanner scan = new Scanner(System.in);

		do {
			System.out.print("Zgadnij liczbę: ");
			try {
				inputNumber = scan.nextInt();

				if (inputNumber < secretNumber) {
					System.out.println("Za mało");
				} else if (inputNumber > secretNumber) {
					System.out.println("Za dużo");
				} else {
					System.out.println("Zgadłeś");
				}
			} catch (InputMismatchException e) {
				System.out.println("To nie jest liczba");
			}
		} while (secretNumber != inputNumber);
		scan.close();
	}
}
