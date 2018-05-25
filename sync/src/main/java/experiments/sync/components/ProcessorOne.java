package experiments.sync.components;

import experiments.sync.model.Item;
import org.springframework.batch.item.ItemProcessor;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class ProcessorOne implements ItemProcessor<Item, Item> {
    private static final Date START_TIME = new Date();

    private static int counter = 0;

    @Override
    public Item process(Item item) {
        // Simulate long running processing
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        counter++;

        if(counter % 10 == 0) {
            System.out.print(".");
        }

        if(counter % 500 == 0) {
            final long elapsedTime = TimeUnit.MILLISECONDS.toSeconds(new Date().getTime() - START_TIME.getTime());
            System.out.printf(" %s in %s seconds %n", counter, elapsedTime);
        }

        return item;
    }
}
