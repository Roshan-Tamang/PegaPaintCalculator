package org.softwareinstitute.rt.paintcalculator;

import java.util.Scanner;

public class App {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Welcome to the paint calculator");

        do {

            Calculator calculator = new Calculator();

            calculator.run();

            System.out.println("To use paint calculator again, press 1");
            int userChoice = sc.nextInt();
            if(userChoice != 1){
                break;
            }
        } while (true);



    }

}