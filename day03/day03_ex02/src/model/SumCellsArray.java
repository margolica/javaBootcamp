package model;

import userException.ExceedingNumberArrayElementsException;
import userException.ExceedingNumberThreadsException;

public class SumCellsArray {
    private static final int MAX_SIZE_ARRAY_SIZE = (int) (2 * Math.pow(10, 6));

    private static int countThread;
    private static int[] arrayInt;

    public SumCellsArray(String[] argc) {
        arrayInt = new int[Integer.parseInt(argc[0].split("=")[1])];
        countThread = (Integer.parseInt(argc[1].split("=")[1]));

        for (int i = 0; i < arrayInt.length; i++) {
            arrayInt[i] = 1;
        }

        if (arrayInt.length > MAX_SIZE_ARRAY_SIZE)
            throw new ExceedingNumberArrayElementsException("Превышен размер массива");
        if (countThread > arrayInt.length)
            throw new ExceedingNumberThreadsException("Превышено количество потоков");
    }

    public void start() {
        int a = (int) Math.ceil(arrayInt.length * 1.0 / countThread);
        for (int i = 0; i < countThread; ++i) {
            Task task;
            if (i == countThread - 1)
                task = new Task(a * i, arrayInt.length);
            else
                task = new Task(a * i, (a * i) + a);
            Thread thread = new Thread(task, "Thread " + i);
            thread.start();
        }
    }

    private static final class Task implements Runnable {
        private int beginPositionInArray;
        private int endPositionInArray;
        private int result;

        public Task(int beginPositionInArray, int endPositionInArray) {
            this.beginPositionInArray = beginPositionInArray;
            this.endPositionInArray = endPositionInArray;
        }

        @Override
        public void run() {
            for (int i = beginPositionInArray; i < endPositionInArray; i++)
                result += arrayInt[i];
            System.out.println(Thread.currentThread().getName() + " from " + beginPositionInArray + " to " + endPositionInArray + " sum " + result);
        }
    }
}
