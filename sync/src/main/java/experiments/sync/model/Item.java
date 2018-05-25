package experiments.sync.model;

public class Item {
    private final String name;
    private final Integer value;

    public Item(String name, Integer value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public Integer getValue() {
        return value;
    }

    public static Item createItem(Integer number) {
        return new Item(String.format("Item %s number", number), number);
    }

    @Override
    public String toString() {
        return "Item{" +
                "name='" + name + '\'' +
                ", value=" + value +
                '}';
    }
}
