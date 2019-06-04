import storage.Treasury;
import storage.Warehouse;
import simulation.Gem;
import java.util.Random;

public class Consumer extends Thread {
    private Warehouse warehouse;
    private Treasury treasury;
    final static int MAX_AMOUNT = 5;
    final static int MAX_SLEEP = 5;
    private boolean run = true;

    public Consumer(Warehouse warehouse, Treasury treasury) {
        this.warehouse = warehouse;
        this.treasury = treasury;
    }

    public void run()
    {
        Random rand = new Random();
        while (run)
        {
            int gem = rand.nextInt(Gem.values().length);
            int amount = rand.nextInt(MAX_AMOUNT) + 1;
            System.out.println("Consumer wants " + amount + " of " + Gem.values()[gem].toString());
            amount = warehouse.get(Gem.values()[gem], amount);
            System.out.println("Consumer got " + amount + " of " + Gem.values()[gem].toString());
            try
            {
                Thread.sleep(3000); // time for travel to treasury
            } catch (InterruptedException e)
            {
                interrupt();
                run = false;
            }
            treasury.add(Gem.values()[gem], amount);
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
