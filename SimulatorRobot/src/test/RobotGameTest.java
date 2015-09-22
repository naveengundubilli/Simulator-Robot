package test;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.robot.RobotGame;
import com.robot.exception.SimulatorRobotException;
import com.robot.simulator.Direction;
import com.robot.simulator.Position;
import com.robot.simulator.SquareTableTop;
import com.robot.simulator.Robot;

public class RobotGameTest {

    final int ROWS = 5;
    final int COLUMNS = 5;
    @Rule
    public org.junit.rules.ExpectedException thrown = ExpectedException.none();

    @Test
    public void testPlacing() throws SimulatorRobotException {

        SquareTableTop squareTableTop = new SquareTableTop(COLUMNS, ROWS);
        Robot toyRobot = new Robot();
        RobotGame game = new RobotGame(squareTableTop, toyRobot);

        Assert.assertTrue(game.placeToyRobot(new Position(0, 1, Direction.NORTH)));
        Assert.assertTrue(game.placeToyRobot(new Position(2, 2, Direction.SOUTH)));
        Assert.assertFalse(game.placeToyRobot(new Position(6, 6, Direction.WEST)));
        Assert.assertFalse(game.placeToyRobot(new Position(-1, 5, Direction.EAST)));
    }

    @Test
    public void testPlacingExceptions() throws SimulatorRobotException {

        SquareTableTop squareTableTop = new SquareTableTop(COLUMNS, ROWS);
        Robot toyRobot = new Robot();
        RobotGame game = new RobotGame(squareTableTop, toyRobot);

        thrown.expect(SimulatorRobotException.class);
        game.placeToyRobot(null);
        thrown.expect(SimulatorRobotException.class);
        game.placeToyRobot(new Position(0, 1, null));
    }

    @Test
    public void testEval() throws SimulatorRobotException {

        SquareTableTop tableTop = new SquareTableTop(COLUMNS, ROWS);
        Robot toyRobot = new Robot();
        RobotGame game = new RobotGame(tableTop, toyRobot);

        game.eval("PLACE 0,0,NORTH");
        Assert.assertEquals("Output: 0,0,NORTH", game.eval("REPORT"));

        game.eval("MOVE");
        game.eval("RIGHT");
        game.eval("MOVE");
        Assert.assertEquals("Output: 1,1,EAST", game.eval("REPORT"));

        // if it goes out of the tableTop it ignores the command
        for (int i = 0; i < 10; i++)
            game.eval("MOVE");
        Assert.assertEquals("Output: 5,1,EAST", game.eval("REPORT"));

        //rotate on itself
        for (int i = 0; i < 4; i++)
            game.eval("LEFT");
        Assert.assertEquals("Output: 5,1,EAST", game.eval("REPORT"));

        // invalid commands
        thrown.expect(SimulatorRobotException.class);
        Assert.assertEquals("Invalid command", game.eval("PLACE12NORTH"));
        thrown.expect(SimulatorRobotException.class);
        Assert.assertEquals("Invalid command", game.eval("LEFFT"));
        thrown.expect(SimulatorRobotException.class);
        Assert.assertEquals("Invalid command", game.eval("RIGHTT"));
    }
}
