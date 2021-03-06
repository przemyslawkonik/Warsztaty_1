package zad2;

import java.util.Arrays;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Warsztat: Symulator LOTTO.
 */
public class Main {

	static final int NUMBER = 6;
	static final int SCOPE = 49;

	public static void main(String[] args) {

		// pobranie liczb od uzytkownika
		Integer[] playerNumbers = getPlayerNumbers();

		// sortowanie i wyświetlenie liczb użytkownika
		Arrays.sort(playerNumbers);
		System.out.println("Skreślone liczby: " + Arrays.toString(playerNumbers));

		// sortowanie i wyświetlenie wylosowanych liczb
		Integer[] generatedNumbers = generateNumbers();
		Arrays.sort(generatedNumbers);
		System.out.println("Wylosowane liczby: " + Arrays.toString(generatedNumbers));

		// sprawdzenie wygranej
		int match = matches(playerNumbers, generatedNumbers);
		if (match >= 3) {
			System.out.println("Trafiłeś: " + match);
		}
	}

	static Integer[] getPlayerNumbers() {
		Integer[] numbers = new Integer[NUMBER];
		Scanner scan = new Scanner(System.in);

		for (int i = 0; i < NUMBER;) {
			try {
				System.out.print("Skreśl " + (i+1) + " liczbę: ");
				int input = scan.nextInt();

				if (input < 1 || input > SCOPE) {
					System.out.println("Liczba poza zakresem");
				} else if (Arrays.asList(numbers).contains(input)) {
					System.out.println("Ta liczba została już skreślona");
				} else {
					numbers[i++] = input;
				}
			} catch (InputMismatchException e) {
				System.out.println("To nie jest liczba");
				scan.next();
			}
		}
		scan.close();
		return numbers;
	}

	private static Integer[] generateNumbers() {
		Integer[] arr = new Integer[SCOPE];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = i + 1;
		}
		Collections.shuffle(Arrays.asList(arr));
		return Arrays.copyOf(arr, NUMBER);
	}

	private static int matches(Integer[] arr1, Integer[] arr2) {
		int match = 0;
		for (Integer i : arr1) {
			for (Integer j : arr2) {
				if (i.equals(j))
					match++;
			}
		}
		return match;
	}
}