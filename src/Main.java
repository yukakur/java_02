public class Main {

    public static void main(String[] args) {
        DoubleLinkedList list = new DoubleLinkedList();
        list.add("1");
//        System.out.println(list);
        list.add("2");
//        System.out.println(list);
        list.add("3");
//        System.out.println(list);
        list.add("4");
        System.out.println(list);
        list.remove("4");
        System.out.println(list);

        for (int i = 0; i < 3; i++) {
            System.out.println(list.getInfo());
            list.iterator().next();

        }





    }
}
