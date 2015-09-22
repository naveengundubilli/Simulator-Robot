package test;
import org.junit.Test;

import com.robot.exception.SimulatorRobotException;
import com.robot.simulator.Direction;
import com.robot.simulator.Position;
import com.robot.simulator.Robot;

public class SimulatorRobotTest {

	@Test
    public void testMovement1() throws SimulatorRobotException {

        Robot robot = new Robot(new Position(0, 0, Direction.NORTH));
        robot.move();
       
        System.out.println("Output:"+robot.getPosition().getX()+","+robot.getPosition().getY()+","+robot.getPosition().getDirection());  
      }
    
    @Test
    public void testMovement2() throws SimulatorRobotException {
    	
    	Robot robot = new Robot(new Position(0, 0, Direction.NORTH));
        robot.rotateLeft();
        
        System.out.println("Output:"+robot.getPosition().getX()+","+robot.getPosition().getY()+","+robot.getPosition().getDirection());
    }
    
    @Test
    public void testMovement3() throws SimulatorRobotException {
    	
    	Robot robot = new Robot(new Position(1, 2, Direction.EAST));
        robot.move();
        robot.move();
        robot.rotateLeft();
        robot.move();
        System.out.println("Output:"+robot.getPosition().getX()+","+robot.getPosition().getY()+","+robot.getPosition().getDirection());
    }

}