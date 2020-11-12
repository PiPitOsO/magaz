import java.util.List;

public class Recommendations { //Принцип единственной ответственности и принцип сегрегации интерфейса
    public Item recommendation(List<Item> basket) {
        Item itemForRec = null;
        int num = 0;
        for (Item item : basket) {
            int sells = item.getNumber();
            if (sells > num) {
                num = sells;
                itemForRec = item;
            }
        }
        return itemForRec;
    }
}