package org.softwareinstitute.rt.paintcalculator;


import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Scanner;

public class Calculator {


    private int totalAreaColour;
    double costPerL;
    BigDecimal totalCost;
    int numberOfCoatings;

    Scanner sc = new Scanner(System.in);


    public void run() {

        System.out.println("Welcome to the paint calculator");

        do {
            selectColour();
            numberOfCoatings();

            if (!(costPerL == 0.0)) {
                enterWallDimensions();

                calculateCost();

                System.out.println("Total cost: £" + totalCost);

                System.out.println("Press 0 to exit");
                int userChoice = sc.nextInt();
                if (userChoice == 0) {
                    System.out.println("Goodbye");
                    break;
                }
                else {
                    totalAreaColour = 0;
                    costPerL = 0.0;
                    totalCost = BigDecimal.ZERO;
                    numberOfCoatings = 0;
                }
            }
        } while (true);


    }

    private void numberOfCoatings() {

        System.out.println("Please enter how many coatings you want to apply");

        numberOfCoatings = sc.nextInt();

    }

    private void enterWallDimensions() {

        int areaColour;

        do {
            System.out.println("What are the wall dimensions that you want to cover?");
            System.out.println("Enter height");

            int height = sc.nextInt();

            System.out.println("Enter width");

            int width = sc.nextInt();
            sc.nextLine();

            areaColour = height * width;

            System.out.println("press 1 to add another wall or any other number to exit");
            int userChoice = sc.nextInt();
            if (userChoice == 0) {
                totalAreaColour = areaColour;
                break;
            } else {
                totalAreaColour += areaColour;
            }
        } while (true);

    }

    private void selectColour() {

        System.out.print("""
                Please enter:\s
                1 for Red\s
                2 for Brown\s
                3 for Black\s
                """);

        int selection = sc.nextInt();

        switch (selection) {
            case 1 -> costPerL = 24.99;
            case 2 -> costPerL = 25.99;
            case 3 -> costPerL = 30.99;
            default -> costPerL = 0.0;
        }

    }
    

    public void calculateCost() {

        BigDecimal costPerLBD = BigDecimal.valueOf(costPerL);
        BigDecimal totalAreaColourBD = BigDecimal.valueOf(totalAreaColour);
        BigDecimal numOfCoatingsBD = BigDecimal.valueOf(numberOfCoatings);

        totalCost = costPerLBD.multiply(totalAreaColourBD);

        totalCost = totalCost.multiply(numOfCoatingsBD);
        totalCost.setScale(2);
    }

}
