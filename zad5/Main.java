package zad5;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Wyszukiwarka najpopularniejszych słów
 */
public class Main {

    private static final int MIN_LENGTH = 3;

    public static void main(String[] args) {
        saveWordsFromServiceToFile("http://www.interia.pl/", "span.title", "files/popular_words.txt");
        createTop("files/popular_words.txt", "files/most_popular_words.txt", 10);
    }

    static void saveWordsFromServiceToFile(String service, String cssQuery, String file) {
        try {
            FileWriter fileWriter = new FileWriter(file);
            for (Element elem : getDataFromService(service, cssQuery)) {
                StringTokenizer tokenizer = new StringTokenizer(elem.text(), "0123456789,.-#/ ");
                while (tokenizer.hasMoreTokens()) {
                    fileWriter.append(tokenizer.nextToken() + "\n");
                }
            }
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void createTop(String sourceFile, String targetFile, int top) {
        HashMap<String, Integer> map = new HashMap<>();
        fillMap(sourceFile, map);
        sortMap(map);

        try {
            FileWriter fileWriter = new FileWriter(targetFile);
            for (Map.Entry<String, Integer> m : map.entrySet()) {
                fileWriter.append(m.getKey() + " " + m.getValue() + "\n");
                if (--top <= 0) {
                    break;
                }
            }
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Elements getDataFromService(String service, String cssQuery) {
        Connection connect = Jsoup.connect(service);
        Elements links = new Elements();
        try {
            Document document = connect.get();
            links = document.select(cssQuery);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return links;
    }

    private static void fillMap(String file, Map<String, Integer> map) {
        try {
            Scanner scan = new Scanner(new File(file));
            while (scan.hasNext()) {
                String word = scan.next();
                if (word.length() > MIN_LENGTH) {
                    putKeyToMap(word, map);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void putKeyToMap(String key, Map<String, Integer> map) {
        if (!map.containsKey(key)) {
            map.put(key, 1);
        } else {
            int value = map.get(key);
            value++;
            map.replace(key, value);
        }
    }

    private static void sortMap(Map<String, Integer> map) {
        map.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));
    }
}
