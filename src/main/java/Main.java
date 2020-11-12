import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    static List<Item> basket = new ArrayList<>(); //Принцип инверсии зависимостей
    static List<Item> catalogue = new ArrayList<>(); //Принцип инверсии зависимостей
    static Scanner scanner = new Scanner(System.in);
    static int wallet;

    public static void main(String[] args) {
        catalogue.add(new Item(Products.aPHONE12, Class.PHONE, 12, 1000));
        catalogue.add(new Item(Products.aPHONE12mini, Class.PHONE, 14, 900));
        catalogue.add(new Item(Products.aPHONE12pro, Class.PHONE, 10, 1200));
        catalogue.add(new Item(Products.aPHONE12proMAX, Class.PHONE, 12, 1300));
        catalogue.add(new Item(Products.aWATHSeries6, Class.WATCH, 11, 540));
        catalogue.add(new Item(Products.aWATHSeries5, Class.WATCH, 10, 450));
        catalogue.add(new Item(Products.aWATHSeries4, Class.WATCH, 9, 360));
        catalogue.add(new Item(Products.aWATHse, Class.WATCH, 14, 410));
        catalogue.add(new Item(Products.aPAD, Class.PAD, 13, 1000));
        catalogue.add(new Item(Products.aPADair, Class.PAD, 18, 800));
        catalogue.add(new Item(Products.aPADmini, Class.PAD, 10, 900));
        catalogue.add(new Item(Products.aPADpro, Class.PAD, 11, 1300));
        System.out.println("\nAnanas-Express");
        System.out.println("Добро пожаловать в маленький интернет-магазинчик ананасовой продукции!");
        System.out.print("Какую сумму Вы хотите добавить на виртуальный кошелек? -> ");
        wallet = scanner.nextInt();
        System.out.println("Ваш баланс: " + wallet + "$");
        while (true) {
            System.out.println("Выберете действие (введите номер):");
            System.out.println("1) Показать мой баланс\n" +
                    "2) Добавить товар в корзину\n" +
                    "3) Удалить товар из корзины\n" +
                    "4) Очистить корзину\n" +
                    "5) Стоимость всей корзины\n" +
                    "6) Показать всю корзину\n" +
                    "7) Перейти к оформлению заказа");
            int input = scanner.nextInt();
            if (input == 1) {
                System.out.println(wallet + "$");
            } else if (input == 2) {
                try {
                    addItem();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            } else if (input == 3) {
                deleteItem();
                System.out.println("Выберете позицию для удаления");
            } else if (input == 4) {
                basket.clear();
                System.out.println("Корзина пуста");
            } else if (input == 5) {
                System.out.println(basketPrice() + "$");
            } else if (input == 6) {
                soutBasket();
            } else if (input == 7) {
                try {
                    buy();
                    break;
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            } else {
                System.out.println("Вы ввели неверное значение, попробуйте еще раз");
            }
        }
    }

    public static void addItem() throws Exception { //Принцип сегрегации интерфейса
        System.out.println("Выберете категорию:");
        Class[] categoriesList = Class.values();
        for (int i = 0; i < categoriesList.length; i++) { //Магические числа
            System.out.println(i + 1 + ") " + categoriesList[i]);
        }
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
                        catalogue.get(input).getNumber(), catalogue.get(input).getPrice());
                newItem.setNumber(number);
                basket.add(newItem);
                System.out.println(catalogue.get(input).getNumber());
                catalogue.get(input).setNumber(catalogue.get(input).getNumber() - number);
            } catch (IndexOutOfBoundsException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void deleteItem() { //Принцип сегрегации интерфейса
        soutBasket();
        System.out.print("Выберете товар для удаления -> ");
        int num = scanner.nextInt() - 1;
        basket.remove(num);
    }

    public static int basketPrice() { //Принцип сегрегации интерфейса
        int sum = 0;
        for (Item item : basket) {
            sum = sum + item.getNumber()*item.getPrice();
        }
        return sum;
    }

    public static void buy() throws Exception { //Принцип сегрегации интерфейса
        if (basket.size() == 0) {
            System.out.println("Корзина пуста!");
        } else {
            int sum = basketPrice();
            soutBasket();
            System.out.println("Нажмите ENTER чтобы подтвердить покупку");
            scanner.nextLine();
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

    public static void soutBasket() { //DRY и принцип сегрегации интерфейса
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
}