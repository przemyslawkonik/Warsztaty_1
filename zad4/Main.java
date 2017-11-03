package zad4;

import java.util.Random;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Warsztat: Kostka do gry.
 *
 * Do weryfikowania wejścia używam wyrażenia regularnego z podziałem na grupy:
 * Grupa 1: ([1-9]*[0-9]*) to ilość rzutów kostką (opcjonalnie)
 * Grupa 2: ([1-9][0-9]*) to liczba oczek na kostce
 * Grupa 3: ([+-][1-9][0-9]*)? to modyfikator + lub - z wartością (opcjonalnie)
 *
 * Do wyciągania wartości z poszczególnych grup używam metody group(int group) klasy Matcher, która zwraca odpowiedni String.
 * Następnie wykonuje parsowanie na typ int bądź char w przypadku modyfikatora +/-
 */
public class Main {

    private static final String regex = "([1-9]*[0-9]*)D([1-9][0-9]*)([+-][1-9][0-9]*)?";

    public static void main(String[] args) {
        String input = new Scanner(System.in).nextLine();

        if(input.matches(regex)) {
            System.out.println("Wynik rzutu: " + calculateDiceCast(input));
        } else {
            System.out.println("Niepoprawny kod");
        }

    }

    private static int calculateDiceCast(String input) {
        int dice = 0, times = 1, tail = 0;
        char operator = ' ';

        //wartości w postaci stringów
        String[] values = pullValues(input);

        //parsowanie wartości na odpowiednie typy
        if (values[0] != null && !values[0].equals("")) {
            times = Integer.parseInt(values[0]);
        }
        if (values[1] != null && !values[1].equals("")) {
            dice = Integer.parseInt(values[1]);
        }
        if (values[2] != null && !values[2].equals("")) {
            operator = values[2].charAt(0);
            tail = Integer.parseInt(values[2].substring(1));
        }

        //obliczenie rzutu
        int result = 0;
        while (times-- > 0) {
            result += new Random().nextInt(dice) + 1;
        }

        if (operator == '+')
            result += tail;
        else if (operator == '-')
            result -= tail;

        //zwracamy rezultat
        return result;
    }

    private static String[] pullValues(String input) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);

        String[] values = new String[3];

        //gdy metoda matches() zwróci true to dopiero wtedy można używać innych metod: start(), end(), group()
        if(matcher.matches()) {
            values[0] = matcher.group(1);   //ilość rzutów
            values[1] = matcher.group(2);   //ilość oczek
            values[2] = matcher.group(3);   //modyfikator z wartością
        }
        return values;
    }
}
