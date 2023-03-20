package org.softwareinstitute.rt.paintcalculator;

import java.util.Scanner;

public class App {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Welcome to the paint calculator");

        do {

            Calculator calculator = new Calculator();

            calculator.run();

            System.out.println("Press 1: Use paint calculator again\n" +
                    "Press any other key: Exit");
            int userChoice = sc.nextInt();
            sc.nextLine();
            if(userChoice != 1){
                break;
            }
        } while (true);



    }

}