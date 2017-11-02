package zad2;

import java.util.Arrays;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Warsztat: Symulator LOTTO.
 */
public class Main {

    public static void main(String[] args) {

        Integer[] playerNumbers = new Integer[6];

        //pętla pobierająca 6 liczb od użytkownika
        int i = 0;
        while (i < playerNumbers.length) {
            try {
                System.out.print("Skreśl " + (i + 1) + " liczbę: ");
                int number = new Scanner(System.in).nextInt();

                if (number < 1 || number > 49) {
                    System.out.println("Liczba poza zakresem");
                } else if (isRepeat(number, playerNumbers)) {
                    System.out.println("Ta liczba została już skreślona");
                } else {
                    playerNumbers[i++] = number;
                }
            } catch (InputMismatchException e) {
                System.out.println("To nie jest liczba");
            }
        }

        //sortowanie i wyświetlenie liczb użytkownika
        Arrays.sort(playerNumbers);
        System.out.println("Skreślone liczby: " + Arrays.toString(playerNumbers));

        //sortowanie i wyświetlenie wylosowanych liczb
        Integer[] generator = generateNumbers();
        Arrays.sort(generator);
        System.out.println("Wylosowane liczby: " + Arrays.toString(generator));

        //sprawdzenie wygranej
        int match = numberOfMatches(playerNumbers, generator);
        if (match >= 3) {
            System.out.println("Trafiłeś: " + match);
        }
    }

    private static boolean isRepeat(int number, Integer[] numbers) {
        for (Integer i : numbers) {
            if (i != null && i.equals(number)) {
                return true;
            }
        }
        return false;
    }

    private static Integer[] generateNumbers() {
        Integer[] arr = new Integer[49];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i + 1;
        }
        Collections.shuffle(Arrays.asList(arr));
        return Arrays.copyOf(arr, 6);
    }

    private static int numberOfMatches(Integer[] player, Integer[] generator) {
        int match = 0;
        for (Integer i : player) {
            for (Integer j : generator) {
                if (i.equals(j))
                    match++;
            }
        }
        return match;
    }
}