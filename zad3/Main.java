package zad3;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Warsztat: Gra w zgadywanie liczb 2.
 */
public class Main {

	public static void main(String[] args) {

		System.out.println("Pomyśl liczbę od 0 do 1000, a ja ją zgadnę w max. 10 próbach.");
		int attempts = 10, min = 0, max = 1000, guess;
		Scanner scan = new Scanner(System.in);

		while (attempts-- > 0) {
			guess = ((max - min) / 2) + min;
			System.out.println("Zgaduję: " + guess);
			System.out.println("1 - więcej");
			System.out.println("2 - mniej");
			System.out.println("3 - trafiłeś");
			System.out.print("Wybierz: ");

			try {
				int answer = scan.nextInt();

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
					return;
				}
				default: {
					System.out.println("Nie oszukuj");
					continue;
				}
				}
			} catch (InputMismatchException e) {
				System.out.println("Nie oszukuj");
				scan.next();
			}
		}
		System.out.println("Przegrałem");
		scan.close();
	}
}
