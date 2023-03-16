package org.softwareinstitute.rt.paintcalculator;

import java.math.BigDecimal;
import java.util.Scanner;

public class PaintCalculator {

    static String[][] paint = {{"Brown","£24.99"},{"Black","£25.99"},{"Red","£30,99"}};


    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Input the name of the color required");

        String color = sc.nextLine();

        System.out.println("Input the litre of paint required");

        int litreRequired = sc.nextInt();

        calculateCost(color,litreRequired);

    }

    public static void calculateCost(String color, int inputInt){



        Double x = Double.parseDouble(searchColor(color));

        BigDecimal op = BigDecimal.valueOf(x);

        BigDecimal po = BigDecimal.valueOf(inputInt);

        BigDecimal pop = op.multiply(po);

        x = pop.doubleValue();

        System.out.println("£" +x);
    }

    private static String searchColor(String color) {

        String cost;

        for(int i = 0; i < paint.length; i++){
            if(color.equals(paint[i][0])){
                cost = paint[i][1];
                cost = extractNumber(cost);
                return cost;
            }
        }

        return "Color not found";
    }


    public static String extractNumber(String cost){

        String[] splits= cost.split("£");

        cost = splits[1];

        return cost ;

    }





}