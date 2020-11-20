import java.util.Scanner;

public class Magazin {

    static Scanner scanner = new Scanner(System.in);

    public static void addItem() throws Exception { //Принцип сегрегации интерфейса
        System.out.println("Выберете категорию:");
        Class[] categoriesList = Class.values();
        for (int i = 0; i < categoriesList.length; i++) { //Магические числа
            System.out.println(i + 1 + ") " + categoriesList[i]);
        }
        int input = scanner.nextInt();
        Class category = categoriesList[input - 1];
        System.out.println("Список товаров:");
        for (int i = 0; i < Main.catalogue.size(); i++) {
            Item item = Main.catalogue.get(i);
            if (item.getCategory().equals(category)) System.out.println(i + 1 + ") " + item.toString());
        }
        System.out.print("Введите номер товара (0 для выхода в главное меню) -> ");
        input = scanner.nextInt() - 1;
        if (input == (-1)) {
            System.out.println("Отправляем Вас назад . . .");
        } else {
            System.out.print("Введите количество -> ");
            int number = scanner.nextInt();
            if (Main.catalogue.get(input).getNumber() < number) {
                throw new Exception("Нет в наличии столько!");
            }
            try {
                Item newItem = new Item(Main.catalogue.get(input).getName(), Main.catalogue.get(input).getCategory(),
                        Main.catalogue.get(input).getNumber(), Main.catalogue.get(input).getPrice());
                newItem.setNumber(number);
                Main.basket.add(newItem);
                System.out.println(Main.catalogue.get(input).getNumber());
                Main.catalogue.get(input).setNumber(Main.catalogue.get(input).getNumber() - number);
            } catch (IndexOutOfBoundsException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void deleteItem() { //Принцип сегрегации интерфейса
        soutBasket();
        System.out.print("Выберете товар для удаления -> ");
        int num = scanner.nextInt() - 1;
        Main.basket.remove(num);
    }

    public static int basketPrice() {
        int sum = 0;
        for (Item item : Main.basket) {
            sum = sum + item.getNumber()*item.getPrice();
        }
        return sum;
    }

    public static void buy() throws Exception {
        if (Main.basket.size() == 0) {
            System.out.println("Корзина пуста!");
        } else {
            int sum = basketPrice();
            soutBasket();
            System.out.println("Нажмите ENTER чтобы подтвердить покупку");
            scanner.nextLine();
            scanner.nextLine();
            if (sum > Main.wallet) {
                throw new Exception("У ВАС НЕДОСТАТОЧНО СРЕДСТВ!");
            } else {
                Main.wallet = Main.wallet - sum;
                System.out.println("Успешно!");
                System.out.println("Ваш чек:");
                for (int i = 0; i < Main.basket.size(); i++) {
                    Item item = Main.basket.get(i);
                    System.out.println(i + 1 + ") " + item.toStringInBasket());
                }
                System.out.println("Сумма: " + sum + "$");
            }
        }
    }

    public static void soutBasket() { //DRY
        if (Main.basket.size() == 0) {
            System.out.println("Ваша корзина пуста!");
        } else {
            System.out.println("Ваша корзина:");
            for (int i = 0; i < Main.basket.size(); i++) {
                Item item = Main.basket.get(i);
                System.out.println(i + 1 + ") " + item.toStringInBasket());
            }
        }
    }
}
