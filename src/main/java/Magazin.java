import java.util.List;
import java.util.Scanner;

public class Magazin implements IMagazin{

    static Scanner scanner = new Scanner(System.in);

    @Override
    public void addItem(List<Item> basket, List<Item> catalogue) throws Exception {
        System.out.println("Выберете категорию:");
        Class[] categoriesList = Class.values();
        for (int i = 0; i < categoriesList.length; i++) { // Магические числа
            System.out.println(i + 1 + ") " + categoriesList[i]);
        }
        Scanner scanner = new Scanner(System.in);
        int input = scanner.nextInt();
        Class category = categoriesList[input - 1];
        System.out.println("Список товаров:");
        for (int i = 0; i < catalogue.size(); i++) {
            Item item = catalogue.get(i);
            if (item.getCategory().equals(category)) System.out.println(i + 1 + ") " + item.toString());
        }
        System.out.print("Введите номер товара (0 для выхода в главное меню) -> ");
        input = scanner.nextInt() - 1;
        if (input == (-1)) {
            System.out.println("Отправляем Вас назад . . .");
        } else {
            System.out.print("Введите количество -> ");
            int number = scanner.nextInt();
            if (catalogue.get(input).getNumber() < number) {
                throw new Exception("Нет в наличии столько!");
            }
            try {
                Item newItem = new Item(catalogue.get(input).getName(), catalogue.get(input).getCategory(),
                        catalogue.get(input).getNumber(), catalogue.get(input).getPrice()) {
                };
                newItem.setNumber(number);
                basket.add(newItem);
                System.out.println("Добавлено в корзину");
                catalogue.get(input).setNumber(catalogue.get(input).getNumber() - number);
            } catch (IndexOutOfBoundsException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    @Override
    public void deleteItem(List<Item> basket) {
        soutBasket(basket);
        System.out.print("Выберете товар для удаления -> ");
        int num = scanner.nextInt() - 1;
        basket.remove(num);
    }

    @Override
    public void clearBasket(List<Item> basket) {
        basket.clear();
        System.out.println("Корзина пуста");
    }

    @Override
    public void soutBasket(List<Item> basket) { //DRY
        if (basket.size() == 0) {
            System.out.println("Ваша корзина пуста!");
        } else {
            System.out.println("Ваша корзина:");
            for (int i = 0; i < basket.size(); i++) {
                Item item = basket.get(i);
                System.out.println(i + 1 + ") " + item.toStringInBasket());
            }
        }
    }

    private int basketPrice(List<Item> basket) {  // Принцип открытости/закрытости
        int sum = 0;
        for (Item item : basket) {
            sum = sum + item.getNumber()*item.getPrice();
        }
        return sum;
    }

    @Override
    public void basketPriceResult(List<Item> basket) {
        System.out.println(basketPrice(basket) + "$");
    }

    @Override
    public void buy(int wallet, List<Item> basket) throws Exception {
        if (basket.size() == 0) {
            System.out.println("Корзина пуста!");
        } else {
            int sum = basketPrice(basket);
            soutBasket(basket);
            System.out.println("Нажмите ENTER чтобы подтвердить покупку");
            scanner.nextLine();
            if (sum > wallet) {
                throw new Exception("У ВАС НЕДОСТАТОЧНО СРЕДСТВ!");
            } else {
                wallet = wallet - sum;
                System.out.println("Успешно!");
                System.out.println("Ваш чек:");
                for (int i = 0; i < basket.size(); i++) {
                    Item item = basket.get(i);
                    System.out.println(i + 1 + ") " + item.toStringInBasket());
                }
                System.out.println("Сумма: " + sum + "$");
            }
        }
    }
}