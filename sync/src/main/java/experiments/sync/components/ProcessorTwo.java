package experiments.sync.components;

import experiments.sync.model.Item;
import org.springframework.batch.item.ItemProcessor;

public class ProcessorTwo implements ItemProcessor<Item, Item> {
    @Override
    public Item process(Item item) {
        // do something
        return item;
    }
}
