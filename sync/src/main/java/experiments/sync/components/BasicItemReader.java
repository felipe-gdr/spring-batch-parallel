package experiments.sync.components;

import experiments.sync.model.Item;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemReaderException;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.LinkedList;
import java.util.Queue;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

@Component("basicItemReader")
public class BasicItemReader implements ItemReader<Item> {
    private Queue<Item> items = new LinkedList<>();

    private static final Integer ITEM_COUNT = 5000;

    @PostConstruct
    public void readItems() {
        items.addAll(
                IntStream.range(0, ITEM_COUNT)
                        .mapToObj(Item::createItem)
                        .collect(toList())
        );
    }

    @Override
    public Item read() throws ItemReaderException {
        return items.poll();
    }
}
