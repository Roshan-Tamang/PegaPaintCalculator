package org.softwareinstitute.rt.paintcalculator;


import org.softwareinstitute.rt.paintcalculator.data.Wall;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Calculator {

    private String colourChoice;
    private BigDecimal paintCostBD;
    private String finishChoice;
    private double additionalCost;
    private BigDecimal totalCost;
    private int numberOfCoatings;

    Scanner sc = new Scanner(System.in);

    List<Wall> wallList = new ArrayList<>();

    String[][] paintList = {{"Blue", "£29.99"}, {"Green", "£26.99"}, {"Brown", "£25.99"}, {"Red", "£23.99"}, {"Yellow", "£24.99"}, {"Black", "£25.99"}};

    public void run() {


        selectColour();
        selectPaintFinish();
        numberOfCoatings();

        enterWallDimensions();

        calculateCost();

        System.out.printf("You have chosen colour " + colourChoice + " with " + finishChoice + " finish" + ". The Total cost is: £" + totalCost);


    }

    private void selectPaintFinish() {

        int selection;

        System.out.print("""
                Please enter:\s
                1 for Flat/Matte\s
                2 for Eggshell\s
                3 for Satin\s
                4 for Semi-gloss\s
                5 for High-gloss\s
                """);


        do {
            selection = sc.nextInt();
            if (selection < 1 || selection > 5) {
                System.out.println("Invalid input. Please input 1-5");
            } else {
                switch (selection) {
                    case 1 -> {
                        finishChoice = "Matte";
                        additionalCost = 0.99;
                    }
                    case 2 -> {
                        finishChoice = "Eggshell";
                        additionalCost = 1.49;
                    }
                    case 3 -> {
                        finishChoice = "Satin";
                        additionalCost = 0.99;
                    }
                    case 4 -> {
                        finishChoice = "Semi-gloss";
                        additionalCost = 1.99;
                    }
                    case 5 -> {
                        finishChoice = "High-gloss";
                        additionalCost = 2.99;
                    }
                    default -> {
                        finishChoice = "None";
                        additionalCost = 0.0;
                    }
                }
                break;
            }

        } while (true);


    }

    private void numberOfCoatings() {

        System.out.println("Please enter how many coatings you want to apply");

        numberOfCoatings = sc.nextInt();

    }

    private void enterWallDimensions() {

        do {
            System.out.println("What are the wall dimensions that you want to cover?");
            System.out.println("Enter height");

            int height = sc.nextInt();

            System.out.println("Enter width");

            int width = sc.nextInt();
            sc.nextLine();

            Wall wall = new Wall(height, width);

            wallList.add(wall);


            System.out.println("press 1 to add another wall or any other number to exit");
            int userChoice = sc.nextInt();
            if (userChoice == 0) {
                break;
            }
        } while (true);

    }

    private void selectColour() {

        boolean exit = true;

        do {
            System.out.println("Please select the colour from the list by entering the name: ");
            for (int i = 0; i < paintList.length; i++) {
                System.out.println((i + 1) + ": " + paintList[i][0]);
            }

            colourChoice = sc.nextLine();

            for (int i = 0; i < paintList.length; i++) {
                if (paintList[i][0].equalsIgnoreCase(colourChoice)) {
                    extractCost(paintList[i][1]);
                    exit = false;
                }
            }
            System.out.println("Invalid colour entered");


        } while (exit);

    }

    private void extractCost(String price) {

        price = price.substring(1);

        double paintCostD = Double.valueOf(price);

        paintCostBD = BigDecimal.valueOf(paintCostD);

    }


    public void calculateCost() {

        int areaReqPaint = 0;

        for (Wall w :
                wallList) {
            areaReqPaint += w.getHeight() * w.getWidth();
        }

        BigDecimal totalPaintCostBD = paintCostBD.add(BigDecimal.valueOf(additionalCost));
        BigDecimal totalAreaColourBD = BigDecimal.valueOf(areaReqPaint);
        BigDecimal numOfCoatingsBD = BigDecimal.valueOf(numberOfCoatings);

        totalCost = totalPaintCostBD.multiply(totalAreaColourBD);

        totalCost = totalCost.multiply(numOfCoatingsBD);
        totalCost.setScale(2);
    }

}
