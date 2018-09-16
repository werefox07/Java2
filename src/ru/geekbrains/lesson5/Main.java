package ru.geekbrains.lesson5;

public class Main {

    public static void main(String[] args) {
        method1();
        method2();
    }

    public static void method1 () {
        int size = 10000000;
        float[] arr = new float[size];
        for(int i = 0; i<arr.length; i++) {
            arr[i] = 1;
        }
        Estimator obj = new Estimator(arr);
        long a = System.currentTimeMillis();
        obj.run();
        System.out.println(System.currentTimeMillis() - a);

    }

    public static void method2 () {
        int size = 10000000;
        int h = size / 2;
        float[] arr = new float[size];
        for(int i = 0; i<arr.length; i++) {
            arr[i] = 1;
        }
        float[] a1= new float[h];
        float[] a2 = new float[h];
        long a = System.currentTimeMillis();
        System.arraycopy(arr, 0, a1, 0, h);
        System.arraycopy(arr, h, a2,0, h);
        Estimator obj1 = new Estimator(a1);
        Estimator obj2 = new Estimator(a2);
        Thread t1 = new Thread(obj1);
        Thread t2 = new Thread(obj2);
        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.arraycopy(a1, 0, arr, 0, h);
        System.arraycopy(a2, 0, arr, h, h);
        System.out.println(System.currentTimeMillis() - a);

    }


}
