package ru.geekbrains.lesson2;

public class Main {
    public static void main(String[] args) {
        String[][] arr = new String[4][];
        arr[0] = new String[] {"1", "2", "3", "4"};
        arr[1] = new String[] {"1", "2", "3", "4"};
        arr[2] = new String[] {"1", "2", "3", "4"};
        arr[3] = new String[] {"1", "2", "3", "4"};

        String[][] arrErr1 = new String[4][];
        arrErr1[0] = new String[] {"1", "2", "3", "4"};
        arrErr1[1] = new String[] {"1", "2", "3", "4", "5"};
        arrErr1[2] = new String[] {"1", "2", "3", "4"};
        arrErr1[3] = new String[] {"1", "2", "3", "4"};

        String[][] arrErr2 = new String[4][];
        arrErr2[0] = new String[] {"1", "2", "3", "4"};
        arrErr2[1] = new String[] {"1", "2", "3", "4"};
        arrErr2[2] = new String[] {"1", "2", "три", "4"};
        arrErr2[3] = new String[] {"1", "2", "3", "4"};

        try {
            System.out.println("Сумма ячеек массива: " + sumEl(arr));     //демонстрация вывода без ошибки
            System.out.println("Сумма ячеек массива: " + sumEl(arrErr1)); //демонстрация вывода с ошибкой размера
        }
        catch (MyArraySizeException | MyArrayDataException e) {
            System.out.println(e.getMessage());
        }
        try {
            System.out.println("Сумма ячеек массива: " + sumEl(arrErr2)); //демонстрация вывода с ошибкой типа данных
        }
        catch (MyArraySizeException | MyArrayDataException e) {
            System.out.println(e.getMessage());
        }
    }

    public static int sumEl (String[][] arr) throws MyArraySizeException, MyArrayDataException {
        if (arr.length != 4) {
            throw new MyArraySizeException("Массив не 4*4");
        }
        for (int i = 0; i<4; i++) {
            if(arr[i].length !=4 ) {
                throw new MyArraySizeException("Массив не 4*4");
            }
        }
        int summ = 0;
        for (int i = 0; i<4; i++) {
            for(int j=0; j<4; j++) {
                try {
                    summ += Integer.parseInt(arr[i][j]);
                }
                catch (Exception e) {
                    throw new MyArrayDataException("Неверные данные в ячейке [" + i + ";" + j + "]");
                }
            }
        }
        return summ;
    }

}
