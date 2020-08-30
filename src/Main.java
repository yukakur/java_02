

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        /**
         * 1. Создать массив с набором слов (10-20 слов, должны встречаться повторяющиеся).
         * Найти и вывести список уникальных слов, из которых состоит массив (дубликаты не считаем).
         * Посчитать сколько раз встречается каждое слово.
         */
        Map<String, Integer> map = new HashMap<>();

        String[] uniqueWords = {"Hello", " Bonjour", "Hola", "Salut", "Buongiorno", "Ciao",
                "Hi", "Hey", "Bonjour", "Come stai", "Salut", "Nice to meet you", "Comment ca va", "Hey"};

        for (String x : uniqueWords) {
            Boolean exist = false;
            if (map.size() == 0) map.put(x, 1);
            else {
                for (Map.Entry<String, Integer> entry : map.entrySet()) {
                    if (x.equals(entry.getKey())) {
                        entry.setValue(entry.getValue() + 1);
                        exist = true;
                    }
                }
                if (!exist) map.put(x, 1);
            }
        }
        for(Map.Entry<String, Integer> entry: map.entrySet()) {
            System.out.println("Слово: " + entry.getKey() + " встречается " + entry.getValue() + " раз");
        }
        /**
         * Написать простой класс Телефонный Справочник, который хранит в себе список фамилий и телефонных номеров.
         * В этот телефонный справочник с помощью метода add() можно добавлять записи. С помощью метода get()
         * искать номер телефона по фамилии. Следует учесть, что под одной фамилией может быть несколько телефонов
         * (в случае однофамильцев), тогда при запросе такой фамилии должны выводиться все телефоны.
         */


        System.out.println();
        System.out.println("\nЗадание №2\n");

        PhoneBook book = new PhoneBook();
        book.add("Ivanov", 89011111111l);
        book.add("Ivanov", 89022222222l);
        book.add("Petrov", 89033333333l);
        book.add("Sidorov", 89044444444l);
        book.add("Dobrinev", 89055555555l);
        book.add("Derevyanko", 89065556655l);
        book.add("Mikhailov", 89077777777l);
        book.add("Sidorov", 89088888888l);

        book.get("Ivanov");
        book.get("Sidorov");
        book.get("Derevyanko");
        book.get("Babaev");







    }






}
