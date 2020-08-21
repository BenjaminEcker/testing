public class Counter {
    public int dropsHit;
    private static Counter instance;

    private Counter(){}

    public static Counter getInstance(){
        if(instance == null){
            instance = new Counter();
        }
        return instance;
    }

    public void incrementDropsHit(){
        dropsHit++;
    }

    public void reset(){
         dropsHit = 0;
    }
}
