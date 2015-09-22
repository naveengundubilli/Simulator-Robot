package test;
import org.junit.Assert;
import org.junit.Test;

import com.robot.simulator.Position;
import com.robot.simulator.SquareTableTop;

import static org.mockito.Mockito.*;


public class SquareTableTopTest {

    @Test
    public void testIsValidPosition() throws Exception {
        Position mockPosition = mock(Position.class);
        when(mockPosition.getX()).thenReturn(6);
        when(mockPosition.getY()).thenReturn(7);

        SquareTableTop tableTop = new SquareTableTop(4, 5);
        Assert.assertFalse(tableTop.isValidPosition(mockPosition));


        when(mockPosition.getX()).thenReturn(1);
        when(mockPosition.getY()).thenReturn(1);
        Assert.assertTrue(tableTop.isValidPosition(mockPosition));


        when(mockPosition.getX()).thenReturn(-1);
        when(mockPosition.getY()).thenReturn(-1);
        Assert.assertFalse(tableTop.isValidPosition(mockPosition));
    }

}
