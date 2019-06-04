package storage;

public class Warehouse extends Storage
{
    public Warehouse() { super(); }

    public synchronized int get(simulation.Gem gem, int amount)
    {
        try
        {
            while (stock.get(gem) == 0)
                wait();
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }

        if (stock.get(gem) >= amount)
        {
            stock.put(gem, stock.get(gem) - amount);
            System.out.println("Removed " + amount + " of " + gem.toString() + " from Warehouse");
            notify();
            return amount;
        }
        else
        {
            int quantity = stock.get(gem);
            stock.put(gem, 0);
            System.out.println("Removed " + quantity + " of " + gem.toString() + " from Warehouse");
            notify();
            return quantity;
        }
    }
}
