public class Item { //Принцип единственной ответственности
    private final Products name;
    private final Class category;
    private int number;
    private final int price;

    public Item(Products name, Class category, int number, int price) {
        this.name = name;
        this.category = category;
        this.number = number;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Товар: " + name +
                ", количество на складах - " + number +
                ", цена - " + price + "$";
    }

    public String toStringInBasket() {
        return "Товар: " + name +
                ", количество в корзине - " + number +
                ", цена - " + price + " (" + price * number + ")";
    }

    public Products getName() {
        return name;
    }

    public Class getCategory() {
        return category;
    }

    public int getNumber() {
        return number;
    }

    public int getPrice() {
        return price;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}