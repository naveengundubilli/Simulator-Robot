package com.robot;

import java.io.Console;

import com.robot.exception.SimulatorRobotException;
import com.robot.simulator.SquareTableTop;
import com.robot.simulator.Robot;

public class Main {

    public static void main(String[] args) {

        Console console = System.console();

        if (console == null) {
            System.err.println("No console.");
            System.exit(1);
        }

        SquareTableTop squareTableTop = new SquareTableTop(5, 5);
        Robot robot = new Robot();
        RobotGame robotGame = new RobotGame(squareTableTop, robot);

        System.out.println("Robot Simulator");
        System.out.println("Enter command, Valid commands are:");
        System.out.println("\'PLACE X,Y,NORTH|SOUTH|EAST|WEST\', MOVE, LEFT, RIGHT, REPORT or EXIT");

        boolean acceptInput = true;
        while (acceptInput) {
            String inputString = console.readLine("");
            if ("EXIT".equals(inputString)) {
                acceptInput = false;
            } else {
                try {
                    String outputVal = robotGame.eval(inputString);
                    System.out.println(outputVal);
                } catch (SimulatorRobotException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }
}