/**
 1. Напишите метод, на вход которого подаётся двумерный строковый массив размером 4х4,
   при подаче массива другого размера необходимо бросить исключение MyArraySizeException.

 2. Далее метод должен пройтись по всем элементам массива, преобразовать в int,
 и просуммировать. Если в каком-то элементе массива преобразование не удалось
 (например, в ячейке лежит символ или текст вместо числа), должно быть брошено исключение
 MyArrayDataException, с детализацией в какой именно ячейке лежат неверные данные.

 3. В методе main() вызвать полученный метод, обработать возможные исключения
 MySizeArrayException и MyArrayDataException, и вывести результат расчета.
 */

public class Main {


    public static void main(String[] args) {
        String[][] strings = new String[4][4];  // массив
        for (int i = 0; i < 4; i++) {
            for(int j = 0; j < 4; j++) {
                strings[i][j] = "1";
            }
        }
        strings[1][2] = "1d";                  // тестовая ячейка с проблемным парсом

            int m = 0;
        try {
            m = arrayParser(strings);
        } catch (MyArrayDataException e) {
//            e.printStackTrace();
            System.out.println(e);
        } catch (MyArraySizeException e) {
//            e.printStackTrace();
            System.out.println(e);
        } finally {
            if (m != 0) System.out.println(m);
        }
    }


    public static int arrayParser(String [][] array) throws MyArraySizeException, MyArrayDataException {
        int summ = 0;
        if (array.length != 4 || array[0].length != 4) {
            throw new MyArraySizeException("Массив не 4х4");
        }
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                try {
                    summ += Integer.parseInt(array[i][j]);
                } catch (NumberFormatException e) {
                    e.getCause();

                    throw new MyArrayDataException("Ячейка " + i + "" + j + " не парсится");
                }
            }

        }
        return summ;
    }
}
