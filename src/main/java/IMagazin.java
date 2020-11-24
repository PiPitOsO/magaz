import java.util.List;

public interface IMagazin { // Принцип инверсии зависимостей (можно было разделить на несколько интерфейсов для (Принцип разделения интерфейса))

    void addItem(List<Item> basket, List<Item> catalogue) throws Exception;

    void deleteItem(List<Item> basket);

    void clearBasket(List<Item> basket);

    void soutBasket(List<Item> basket);

    void basketPriceResult(List<Item> basket);

    void buy(int wallet, List<Item> basket) throws Exception;
}