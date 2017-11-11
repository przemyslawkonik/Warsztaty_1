#### Warsztat: Wyszukiwarka najpopularniejszych słów

1. Zaimportuj do projektu bibliotekę jsoup, możesz ją pobrać z adresu: https://jsoup.org/download.
2. Wyszukaj w popularnych serwisach internetowych nagłówków artykułów,
 a następnie zapisz pojedyncze słowa w nich występujące do pliku o nazwie `popular_words.txt`.
Przykład pobrania tytułów z tagu html **span** z atrybutem class o wartości **title**
````java
Connection connect = Jsoup.connect("http://www.onet.pl/");
try {
    Document document = connect.get();
    Elements links = document.select("span.title");
    for (Element elem : links) {
        System.out.println(elem.text());
    }
} catch (IOException e) {
    e.printStackTrace();
}

````
3. Wywołaj pobieranie dla wybranych serwisów internetowych.
4. Wczytaj utworzony plik `popular_words.txt` i na jego podstawie utwórz plik `most_popular_words.txt`,
 który zawierać będzie 10 najbardziej popularnych słów.
5. Utwórz tablicę elementów wykluczonych np. **i**, **lub** , ewentualnie pomiń wszystkie elementy 3-znakowe. 
