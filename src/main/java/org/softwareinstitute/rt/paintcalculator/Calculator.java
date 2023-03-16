package org.softwareinstitute.rt.paintcalculator;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Scanner;

public class Calculator {


    private int height;
    private int width;
    private int totalAreaColour;
    double costPerL;
    double totalCost;
    Scanner sc = new Scanner(System.in);


    public void run() {

        System.out.println("Welcome to the paint calculator");

        selectColour();

        if(!(costPerL == 0.0)){
            enterWallDimensions();

            calculateCost();

            System.out.println("Total cost: £"+totalCost);
        } else {
            System.out.println("Goodbye");
        }



    }

    private void enterWallDimensions() {

        System.out.println("What are the wall dimensions that you want to cover?");
        System.out.println("Enter height");

        height = sc.nextInt();

        System.out.println("Enter width");

        width = sc.nextInt();

        totalAreaColour = height*width;

    }

    private void selectColour() {

        System.out.printf("Please enter: \n" +
                "1 for Red \n" +
                "2 for Brown \n" +
                "3 for Black \n"+
                "0 to exit \n");

        int selection = sc.nextInt();

        switch (selection){
            case 1:
                costPerL = 24.99;
                break;
            case 2:
                costPerL = 25.99;
                break;
            case 3:
                costPerL = 30.99;
                break;
            default:
                costPerL = 0.0;
                break;

        }

    }

    public void calculateCost(){

        totalCost = totalAreaColour*costPerL;

        totalCost = (double) Math.round(totalCost*100)/100;

    }


}
