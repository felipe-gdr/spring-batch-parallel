package experiments.sync.components;

import experiments.sync.model.Item;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Date;

@Component("processorOne")
public class ProcessorOne implements ItemProcessor<Item, Item> {
    private Date startDate;
    private int counter;

    @PostConstruct
    public void ProcessorOne() {
        this.resetCounter();
    }

    public void resetCounter() {
        counter = 0;
        startDate = new Date();
    }

    @Override
    public Item process(Item item) {
        // Simulate long running processing
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        writeStats();

        return item;
    }

    private synchronized void writeStats() {
        counter++;

        if(counter % 10 == 0) {
            System.out.print(".");
        }

        if(counter % 500 == 0) {
            final double elapsedTime = (new Date().getTime() - startDate.getTime()) / 1000.0;
            System.out.printf(" %s in %s seconds %n", counter, elapsedTime);
        }

    }

}
