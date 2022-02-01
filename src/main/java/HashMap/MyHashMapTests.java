package HashMap;

import java.util.Set;

public class MyHashMapTests {
    public static void main(String[] args) {
        MyHashMap<Integer, String> hashMap = new MyHashMap<>();

        hashMap.put(1, "doll");
        hashMap.put(2, "constructor");
        hashMap.put(3, "typewriter");

        System.out.println("Всего купили подарков в магазине - " + hashMap.size());
        System.out.println("_______________________");
        Set<Integer> keys = hashMap.keySet();

        System.out.println("Список куплених игрушек");
        for (Integer key : keys) {
            System.out.print(key + ": ");
            System.out.println(hashMap.get(key));
        }
        System.out.println("_______________________");
        System.out.println("Заменили в магазине игрушку");

        hashMap.replace(1, "robot");
        for (Integer key : keys) {
            System.out.print(key + ": ");
            System.out.println(hashMap.get(key));
        }
        System.out.println("_______________________");

        System.out.println("Подарили игрушку племяннику - " + hashMap.remove(2));
        System.out.println("Осталось игрушек - " + hashMap.size());
        hashMap.clear();
        System.out.println("Количество игрушек после того как все роздарили - " + hashMap.size());

    }
}
