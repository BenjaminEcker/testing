import java.util.concurrent.ThreadLocalRandom;

public class PiCalculator implements Runnable{
    private int drops;

    public PiCalculator(int drops) {
        this.drops = drops;
    }

    @Override
    public void run() {
        int count = 0;
        for (int i = 0; i < drops; i++){
            double drop_X = ThreadLocalRandom.current().nextDouble();
            double drop_Y = ThreadLocalRandom.current().nextDouble();
            if (Math.pow(drop_Y, 2) + Math.pow(drop_X, 2) <= 1) {
                count++;
            }
        }
        Counter.getInstance().dropsHit += count;
    }
}
