package storage;

import java.util.HashMap;

public abstract class Storage
{
    HashMap<simulation.Gem, Integer> stock = new HashMap<>();

    Storage()
    {
        for (simulation.Gem gem : simulation.Gem.values())
            stock.put(gem, 0);
    }

    public synchronized void add(simulation.Gem gem, int amount)
    {
        stock.put(gem, stock.get(gem) + amount);
        System.out.println("Added " + amount + " of " + gem.toString() + " to " + this.getClass().getSimpleName());
        notify();
    }

    public void printStorage()
    {
        System.out.println("\n" + this.getClass().getSimpleName() + " has:");
        for (simulation.Gem gem : simulation.Gem.values())
        {
            System.out.println(gem.toString() + ": " + stock.get(gem));
        }
    }
}
