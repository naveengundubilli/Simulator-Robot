package com.robot;

import com.robot.exception.SimulatorRobotException;
import com.robot.simulator.Commands;
import com.robot.simulator.Direction;
import com.robot.simulator.Position;
import com.robot.simulator.Robot;
import com.robot.simulator.SquareTableTop;

public class RobotGame {

    SquareTableTop squareTableTop;
    Robot robot;

    public RobotGame(SquareTableTop squareTableTop, Robot robot) {
        this.squareTableTop = squareTableTop;
        this.robot = robot;
    }

    /**
     * Places the robot on the squareTableTop  in position X,Y and facing NORTH, SOUTH, EAST or WEST
     *
     * @param position Robot position
     * @return true if placed successfully
     * @throws SimulatorRobotException
     */
    public boolean placeToyRobot(Position position) throws SimulatorRobotException {

        if (squareTableTop == null)
            throw new SimulatorRobotException("Invalid squareTableTop object");

        if (position == null)
            throw new SimulatorRobotException("Invalid position object");

        if (position.getDirection() == null)
            throw new SimulatorRobotException("Invalid direction value");

        // validate the position
        if (!squareTableTop.isValidPosition(position))
            return false;

        // if position is valid then assign values to fields
        robot.setPosition(position);
        return true;
    }

    /**
     * Evals and executes a string command.
     *
     * @param inputString command string
     * @return string value of the executed command
     * @throws com.robot.exception.SimulatorRobotException
     *
     */
    public String eval(String inputString) throws SimulatorRobotException {
        String[] args = inputString.split(" ");

        // validate command
        Commands command;
        try {
            command = Commands.valueOf(args[0]);
        } catch (IllegalArgumentException e) {
            throw new SimulatorRobotException("Invalid command");
        }
        if (command == Commands.PLACE && args.length < 2) {
            throw new SimulatorRobotException("Invalid command");
        }

        // validate PLACE params
        String[] params;
        int x = 0;
        int y = 0;
        Direction commandDirection = null;
        if (command == Commands.PLACE) {
            params = args[1].split(",");
            try {
                x = Integer.parseInt(params[0]);
                y = Integer.parseInt(params[1]);
                commandDirection = Direction.valueOf(params[2]);
            } catch (Exception e) {
                throw new SimulatorRobotException("Invalid command");
            }
        }

        String output="";
        Position newPosition=null;

        switch (command) {
            case PLACE:
                output = String.valueOf(placeToyRobot(new Position(x, y, commandDirection)));
                if(output.equalsIgnoreCase("false"))
                	output = "Out of Table";	
                else 
                    output="";
                break;
            case MOVE:
            	if(robot.getPosition()!=null){
            	 newPosition = robot.getPosition().getNextPosition();
                if (!squareTableTop.isValidPosition(newPosition))
                    output = "Invalid Position";
                else
                    robot.move(newPosition);
            	}else{
            		output = "Invalid Position";
            	}
                break;
            case LEFT:
                robot.rotateLeft();
                break;
            case RIGHT:
                robot.rotateRight();
                break;
            case REPORT:
                output = report();
                break;
            default:
                throw new SimulatorRobotException("Invalid command");
        }

        return output;
    }

    /**
     * Returns the X,Y and Direction of the robot
     */
    public String report() {
        if (robot.getPosition() == null)
            return "Ignored Move as Robot Placed Out Of Table";

        return "Output: "+robot.getPosition().getX() + "," + robot.getPosition().getY() + "," + robot.getPosition().getDirection().toString();
    }
}
