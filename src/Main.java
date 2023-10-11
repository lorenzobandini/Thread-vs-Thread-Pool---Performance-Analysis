import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    
    public static void main(String[] args) throws IOException {

        System.out.print("Enter the number of threads: ");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());

        System.out.println("Execution of separate threads");
        
        Thread[] threads = new Thread[n];
        
        long inizio = System.currentTimeMillis();
        for(int i = 0; i < n; i++){
            threads[i] = new Thread(new Task());
            threads[i].start();
        }

        for(int i = 0; i < n; i++){
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                System.out.println("Join Error");
            }
        }
        long fine = System.currentTimeMillis();
        System.out.println("Thread execution time: " + (fine - inizio) + " ms");

        System.out.print("Enter the thread pool size: ");
        int dim = Integer.parseInt(reader.readLine());
        reader.close();
        
        ExecutorService executor = Executors.newFixedThreadPool(dim);
        System.out.println("Execution using a thread pool");
        inizio = System.currentTimeMillis();
        for(int i = 0; i < n; i++){
            executor.execute(new Task());
        }
        executor.shutdown();
        while(!executor.isTerminated());
        fine = System.currentTimeMillis();
        System.out.println("Thread pool execution time: " + (fine - inizio) + " ms");
    }
}
