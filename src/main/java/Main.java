import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    static List<Item> basket = new ArrayList<>();
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
                    Magazin.addItem();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            } else if (input == 3) {
                Magazin.deleteItem();
                System.out.println("Выберете позицию для удаления");
            } else if (input == 4) {
                basket.clear();
                System.out.println("Корзина пуста");
            } else if (input == 5) {
                System.out.println(Magazin.basketPrice() + "$");
            } else if (input == 6) {
                Magazin.soutBasket();
            } else if (input == 7) {
                try {
                    Magazin.buy();
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