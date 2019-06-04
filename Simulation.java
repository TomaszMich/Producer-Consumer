import storage.Treasury;
import storage.Warehouse;

public class Simulation {

    public static void main(String[] arg) throws InterruptedException
    {
        Warehouse warehouse = new Warehouse();
        Treasury treasury = new Treasury();

        Producer producer1 = new Producer(warehouse);
        Producer producer2 = new Producer(warehouse);

        Consumer consumer1 = new Consumer(warehouse, treasury);
        Consumer consumer2 = new Consumer(warehouse, treasury);

        producer1.start();
        producer2.start();
        consumer1.start();
        consumer2.start();

        Thread.sleep(15000);
        producer1.interrupt();
        producer2.interrupt();
        consumer1.interrupt();
        consumer2.interrupt();

        warehouse.printStorage();
        treasury.printStorage();
    }
}
