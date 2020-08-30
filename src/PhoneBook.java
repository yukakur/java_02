import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PhoneBook { // телефонный справочник, с одной телефонной книгой

    public static Map<Long, String> map = new HashMap<>(); // книга общая для всех

    public void add(String lastName, long number) {
        map.put(number, lastName);
    }
    public void get(String lastName) {
        List<Long> list = new ArrayList<>();
        for(Map.Entry<Long,String> entry: map.entrySet()) {
            if(lastName.equals(entry.getValue())) list.add(entry.getKey());
        }
        if(list.size() == 0) System.out.println("Нет таких в списке");
        else for(Long x: list) System.out.println(x + " - " + lastName );
    }


}
