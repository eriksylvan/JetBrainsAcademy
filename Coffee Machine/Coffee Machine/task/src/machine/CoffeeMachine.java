package machine;

import java.util.Arrays;
import java.util.Scanner;

public class CoffeeMachine {
    static int waterAmount = 400;
    static int milkAmount = 540;
    static int beansAmount = 120;
    static int moneyAmount = 550;
    static int cupsAmount = 9;

    static Scanner scanner = new Scanner(System.in);

    //Inventory
    static void inventory() {
        System.out.println("The coffee machine has:");
        System.out.println(waterAmount + " of water");
        System.out.println(milkAmount + " of milk");
        System.out.println(beansAmount + " of coffee beans");
        System.out.println(cupsAmount + " of disposable cups");
        System.out.println(moneyAmount + " of money");
        System.out.println();
    }

    //Buy
    static void buy() {
        System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino:");
        String selection = scanner.next();
        int water = 0, milk = 0, beans = 0, cups = 0, money = 0;
        boolean back = false;
        switch (selection) {
            case "1":
                //For the espresso, the coffee machine needs 250 ml of water and 16 g of coffee beans. It costs $4.
                water = 250;
                beans = 16;
                cups = 1;
                money= 4;
                break;
            case "2":
                //For the latte, the coffee machine needs 350 ml of water, 75 ml of milk, and 20 g of coffee beans. It costs $7.
                water= 350;
                milk= 75;
                beans= 20;
                cups= 1;
                money= 7;
                break;
            case "3":
                //And for the cappuccino, the coffee machine needs 200 ml of water, 100 ml of milk, and 12 g of coffee beans. It costs $6.
                water= 200;
                milk= 100;
                beans= 12;
                cups= 1;
                money= 6;
                break;
            case "back":
                back = true;
                break;
            default:
                System.out.println("Invalid selection");
                back = true;
        }
        if (!back){
            if (waterAmount < water)
                System.out.println("Sorry, not enough water!");
            else if (milkAmount < milk)
                System.out.println("Sorry, not enough milk!");
            else if (beansAmount < beans)
                System.out.println("Sorry, not enough beans!");
            else if (cupsAmount < cups)
                System.out.println("Sorry, not enough cups!");
            else {
                System.out.println("I have enough resources, making you a coffee!");
                waterAmount -= water;
                milkAmount -= milk;
                beansAmount -= beans;
                cupsAmount -= cups;
                moneyAmount += money;
            }

        }
    }

    //Fill
    static void fill() {
        System.out.println("Write how many ml of water do you want to add:");
        int water = scanner.nextInt();
        System.out.println("Write how many ml of milk do you want to add:");
        int milk = scanner.nextInt();
        System.out.println("Write how many grams of coffee beans do you want to add:");
        int beans = scanner.nextInt();
        System.out.println("Write how many disposable cups of coffee do you want to add:");
        int cups = scanner.nextInt();
        waterAmount += water;
        milkAmount += milk;
        beansAmount += beans;
        cupsAmount += cups;
    }

    //take
    static void take() {
        System.out.println("I gave you $" + moneyAmount);
        moneyAmount = 0;
    }

    public static void main(String[] args) {

        // Main loop
        while (true) {
            //inventory();
            System.out.println("Write action (buy, fill, take, remaining, exit):");
            String action = scanner.next();
            switch (action) {
                case "buy":
                    buy();
                    break;
                case "fill":
                    fill();
                    break;
                case "take":
                    take();
                    break;
                case "remaining":
                    inventory();
                    break;
                case "exit":
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid action");
                    break;
            }
            //inventory();
            //break;
        }
    }
}