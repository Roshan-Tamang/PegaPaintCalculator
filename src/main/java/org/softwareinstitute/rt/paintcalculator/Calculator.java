package org.softwareinstitute.rt.paintcalculator;


import org.softwareinstitute.rt.paintcalculator.data.PaintingSurface;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Calculator {

    private BigDecimal paintCostBD;
    private String finishChoice;
    private double additionalCost;
    private BigDecimal individualCostBD;
    private int numberOfCoatings;

    private BigDecimal totalCostBD;
    private BigDecimal totalLabourCost;

    Scanner sc = new Scanner(System.in);

    List<PaintingSurface> paintingSurfaceList = new ArrayList<>();

    String[][] paintList = {{"Blue", "£14.99"}, {"Green", "£13.99"}, {"Brown", "£12.99"}, {"Red", "£12.99"}, {"Yellow", "£14.99"}, {"Black", "£12.99"}};

    public void run() {

        selectTypeOfPaintSurface();

        estimateLabourCost();

        calculateTotalCost();

        System.out.println("The Total estimated cost is: £" + totalCostBD);

    }


    public void selectTypeOfPaintSurface() {

        String[] paintArea = {"Wall", "Door", "Window", "Ceiling", "Floor"};

        do {
            System.out.println("Select what you are painting:");

            for (String i : paintArea) {
                System.out.println(i);
            }

            String userChoice = sc.nextLine();

            for (String i : paintArea) {
                if (i.equalsIgnoreCase(userChoice)) {
                    createPaintArea(i);

                }
            }
            System.out.println("Press 1: Add another PaintSurface\n" +
                    "Press any other key: exit");
            int userChoiceInt = sc.nextInt();
            sc.nextLine();
            if (userChoiceInt != 1) {
                break;
            }
        } while (true);
    }

    public void createPaintArea(String type) {


        System.out.println("Enter the dimensions you want to paint (meters)?");
        System.out.println("Enter height");

        int height = sc.nextInt();
        //BigDecimal height = sc.nextBigDecimal();

        System.out.println("Enter width");

        int width = sc.nextInt();
        //BigDecimal width = sc.nextBigDecimal();
        sc.nextLine();

        int totalArea = height * width;
        //BigDecimal totalArea = height.multiply(width);

        int paintArea = totalArea;
        //  BigDecimal paintArea = totalArea;

        System.out.println("Are there are any areas you would like to exclude?");
        do {
            System.out.println("Press 1 for \"Yes\" or 0 for \"No\" ");
            int userChoice = sc.nextInt();
            sc.nextLine();
            if (userChoice == 1) {
                paintArea = excludeDimensions(totalArea);
                System.out.println("Are there any additional areas you would like to exclude?");
            } else if (userChoice == 0) {
                break;
            } else {
                System.out.println("Invalid input");
            }
        } while (true);

        if (paintArea > 0) {
            PaintingSurface paintingSurface = new PaintingSurface(type, height, width);
            paintingSurface.setTotalPaintArea(paintArea);

            String colour = selectColour();
            paintingSurface.setPaintColour(colour);
            selectPaintFinish();
            paintingSurface.setFinishType(finishChoice);

            numberOfCoatings();

            calculateCost(paintArea);

            paintingSurface.setCost(individualCostBD);

            paintingSurfaceList.add(paintingSurface);

        } else {
            System.out.println("Paint Surface are is 0. It will not be taken into account when calculating the cost");
        }
    }

    public int excludeDimensions(int totalArea) {

        int paintArea;

        System.out.println("What are the dimensions of areas you want to exclude:");
        System.out.println("Enter height");
        int excludeHeight = sc.nextInt();
        System.out.println("Enter width");
        int excludeWidth = sc.nextInt();

        int totalExcludeArea = excludeHeight * excludeWidth;

        paintArea = (totalExcludeArea < totalArea) ? totalArea - totalExcludeArea : 0;

        return paintArea;

    }

    public String selectColour() {

        boolean exit = true;

        String colourChoice;
        do {
            System.out.println("Please select the colour from the list by entering the name: ");
            for (int i = 0; i < paintList.length; i++) {
                System.out.println((i + 1) + ": " + paintList[i][0]);
            }

            colourChoice = sc.nextLine();

            for (String[] strings : paintList) {
                if (strings[0].equalsIgnoreCase(colourChoice)) {
                    extractCost(strings[1]);
                    exit = false;
                }
            }
            System.out.println("Invalid colour entered");


        } while (exit);

        return colourChoice;

    }


    public void selectPaintFinish() {

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

    public void numberOfCoatings() {

        System.out.println("Please enter how many coatings you want to apply");

        numberOfCoatings = sc.nextInt();
        sc.nextLine();

    }


    public void extractCost(String price) {

        price = price.substring(1);

        double paintCostD = Double.parseDouble(price);

        paintCostBD = BigDecimal.valueOf(paintCostD);

    }


    public void calculateCost(int paintingArea) {


        BigDecimal paintRequired = BigDecimal.valueOf(10);
        BigDecimal totalPaintCostBD = paintCostBD.add(BigDecimal.valueOf(additionalCost));
        BigDecimal paintingAreaBD = BigDecimal.valueOf(paintingArea);
        BigDecimal numOfCoatingsBD = BigDecimal.valueOf(numberOfCoatings);

        individualCostBD = totalPaintCostBD.multiply(paintingAreaBD);
        individualCostBD = individualCostBD.divide(paintRequired);

        individualCostBD = individualCostBD.multiply(numOfCoatingsBD);
        individualCostBD = individualCostBD.setScale(2, RoundingMode.HALF_UP);
    }

    private void estimateLabourCost() {

        int wagePerHours = 20;
        double timeTaken = 0.0;

        for (PaintingSurface i : paintingSurfaceList) {
            switch (i.getType()) {
                case "Walls", "Ceilings", "Floor":
                    timeTaken += 1;
                    break;
                case "Doors", "Windows":
                    timeTaken += 0.5;
                    break;
            }
        }

        timeTaken *= numberOfCoatings;

        totalLabourCost = BigDecimal.valueOf(wagePerHours).multiply(BigDecimal.valueOf(timeTaken));

    }

    private void calculateTotalCost() {

        totalCostBD = BigDecimal.ZERO;

        for (PaintingSurface i : paintingSurfaceList) {
            totalCostBD = totalCostBD.add(i.getCost());
        }

        totalCostBD.add(totalLabourCost);
    }


}
