public class Task implements Runnable{

    @Override
    public void run() {
        long inizio = System.currentTimeMillis();
        for(int n = 1; n<10000000; n++ ){
            isPrime(n);
        }
        long fine = System.currentTimeMillis();
        System.out.println("Execution time of "+ Thread.currentThread().getName() + " : " + (fine - inizio) + " ms");
    }

    public static boolean isPrime(int n) {

        if((n > 2 && n % 2 == 0) || n == 1) {
            return false;
        }

        for (int i = 3; i <= (int)Math.sqrt(n); i += 2) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
}
