import javax.xml.transform.Result;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        List<Thread> threads = new LinkedList<>();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter number of calculations to perform: ");
        int numberCalculations = Integer.parseInt(scanner.nextLine().trim());
        System.out.println("Please enter number of threads to use: ");
        Integer numberThreads = Integer.parseInt(scanner.nextLine().trim());

        System.out.println("Performing sequential calculation with 10000000 runs.");
        PiCalculator piCalculator = new PiCalculator(numberCalculations);
        long startTime = System.currentTimeMillis();
        piCalculator.run();
        long endTime = System.currentTimeMillis();
        long timeLapsed = endTime - startTime;
        double pi = 4 * ((double)Counter.getInstance().dropsHit / numberCalculations);
        System.out.println("Result: " + pi);
        System.out.println("Elapsed time: "+ timeLapsed);

        Counter.getInstance().reset();
        int sharedCalculations = numberCalculations / numberThreads;
        System.out.println("Performing threaded calculation with " + sharedCalculations + " runs on " + numberThreads + " threads.");
        for (int i = 0; i < numberThreads; i++) {
            threads.add(new Thread(new PiCalculator(sharedCalculations)));
        }
        startTime = System.currentTimeMillis();
        for (int i = 0; i < numberThreads; i++) {
            threads.get(i).start();
        }

        for (int i = 0; i < numberThreads; i++) {
            try {
                threads.get(i).join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        endTime = System.currentTimeMillis();
        timeLapsed = endTime - startTime;
        pi = 4 * ((double)Counter.getInstance().dropsHit / numberCalculations);
        System.out.println("Result: " + pi);
        System.out.println("Elapsed time: "+ timeLapsed);
    }
}
