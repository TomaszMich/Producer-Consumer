import storage.Warehouse;
import simulation.Gem;
import java.util.Random;

public class Producer extends Thread {
    private Warehouse warehouse;
    final static int MAX_AMOUNT = 5;
    final static int MAX_SLEEP = 5;
    private boolean run = true;

    Producer(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    public void run()
    {
        Random rand = new Random();
        while(run)
        {
            int gem = rand.nextInt(Gem.values().length);
            int amount = rand.nextInt(MAX_AMOUNT) + 1;
            System.out.println("Produced " + amount + " of " + Gem.values()[gem].toString());
            warehouse.add(Gem.values()[gem], amount);
            try
            {
                Thread.sleep((rand.nextInt(MAX_SLEEP) + 1) * 1000);
            } catch (InterruptedException e)
            {
                interrupt();
                run = false;
            }
        }
    }
}
