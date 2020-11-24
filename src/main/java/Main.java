import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        IMagazin mag = new Magazin(); // Принцип инверсии зависимостей
        List<Item> basket = new ArrayList<>();
        List<Item> catalogue = new ArrayList<>();
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
        Scanner scanner = new Scanner(System.in);
        int wallet = scanner.nextInt();
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
                    mag.addItem(basket, catalogue);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            } else if (input == 3) {
                mag.deleteItem(basket);
            } else if (input == 4) {
                mag.clearBasket(basket);
            } else if (input == 5) {
                mag.basketPriceResult(basket);
            } else if (input == 6) {
                mag.soutBasket(basket);
            } else if (input == 7) {
                try {
                    mag.buy(wallet, basket);
                    break;
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            } else {
                System.out.println("Вы ввели неверное значение, попробуйте еще раз");
            }
        }
    }
}