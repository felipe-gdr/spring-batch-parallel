package experiments.sync.components;

import experiments.sync.model.Item;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component("processorTwo")
public class ProcessorTwo implements ItemProcessor<Item, Item> {
    @Override
    public Item process(Item item) {
        // do something
        return item;
    }
}
