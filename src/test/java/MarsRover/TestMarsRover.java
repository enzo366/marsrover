package MarsRover;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class TestMarsRover {
    @Test
    public void testCreateMarsRoverWithNullStartingPointFails() {
        try {
            new MarsRover(null, null, null);
            fail();
        } catch (RuntimeException ex) {
            assertEquals(ex.getMessage(), MarsRover.INVALID_STARTING_POINT);
        }
    }

    @Test
    public void testCreateMarsRoverWithNullDirectionFails() {
        try {
            new MarsRover(new Point(0,0), null, null);
            fail();
        } catch (RuntimeException ex) {
            assertEquals(ex.getMessage(), MarsRover.INVALID_DIRECTION);
        }
    }

    @Test
    public void testCreateMarsRoverWithNullWorldBoundsFails() {
        try {
            new MarsRover(new Point(0,0), Direction.NORTH, null);
            fail();
        } catch (RuntimeException ex) {
            assertEquals(ex.getMessage(), MarsRover.INVALID_WORLD_BOUNDS);
        }
    }

    @Test
    public void testMarsRoverWontMoveIfCommandsAreNull() {
        Point startingPoint = new Point(0,0);
        MarsRover marsRover = new MarsRover(startingPoint, Direction.NORTH, new Point(3,3));
        try {
            marsRover.receiveCommands(null);
            fail();
        } catch (RuntimeException ex) {
            assertEquals(ex.getMessage(), MarsRover.INVALID_COMMANDS_PROVIDED);
        }
        assertEquals(marsRover.getPosition(), startingPoint);
    }

    @Test
    public void testMarsRoverWontMoveIfCommandsAreEmpty() {
        Point startingPoint = new Point(0,0);
        MarsRover marsRover = new MarsRover(startingPoint, Direction.NORTH, new Point(3,3));
        try {
            marsRover.receiveCommands("");
            fail();
        } catch (RuntimeException ex) {
            assertEquals(ex.getMessage(), MarsRover.INVALID_COMMANDS_PROVIDED);
        }
        assertEquals(marsRover.getPosition(), startingPoint);
        assertEquals(marsRover.getDirection(), Direction.NORTH);
    }

    @Test
    public void testASingleForwardCommandMovesTheRover() {
        Point startingPoint = new Point(0,0);
        MarsRover marsRover = new MarsRover(startingPoint, Direction.NORTH, new Point(3,3));
        marsRover.receiveCommands("F");
        assertEquals(marsRover.getPosition(), new Point(0,1));
        assertEquals(marsRover.getDirection(), Direction.NORTH);
    }

    @Test
    public void testASingleForwardCommandBeyondTheNorthWorldBoundMovesTheRover() {
        Point startingPoint = new Point(0,3);
        MarsRover marsRover = new MarsRover(startingPoint, Direction.NORTH, new Point(3,3));
        marsRover.receiveCommands("F");
        assertEquals(marsRover.getPosition(), new Point(0,0));
        assertEquals(marsRover.getDirection(), Direction.NORTH);
    }

    @Test
    public void testASingleForwardCommandBeyondTheSouthWorldBoundMovesTheRover() {
        Point startingPoint = new Point(0,0);
        MarsRover marsRover = new MarsRover(startingPoint, Direction.SOUTH, new Point(3,3));
        marsRover.receiveCommands("F");
        assertEquals(marsRover.getPosition(), new Point(0,3));
        assertEquals(marsRover.getDirection(), Direction.SOUTH);
    }

    @Test
    public void testASingleForwardCommandBeyondTheEastWorldBoundMovesTheRover() {
        Point startingPoint = new Point(3,0);
        MarsRover marsRover = new MarsRover(startingPoint, Direction.EAST, new Point(3,3));
        marsRover.receiveCommands("F");
        assertEquals(marsRover.getPosition(), new Point(0,0));
        assertEquals(marsRover.getDirection(), Direction.EAST);
    }

    @Test
    public void testASingleForwardCommandBeyondTheWestWorldBoundMovesTheRover() {
        Point startingPoint = new Point(0,0);
        MarsRover marsRover = new MarsRover(startingPoint, Direction.WEST, new Point(3,3));
        marsRover.receiveCommands("F");
        assertEquals(marsRover.getPosition(), new Point(3,0));
        assertEquals(marsRover.getDirection(), Direction.WEST);
    }

    @Test
    public void testASingleBackwardCommandMovesTheRover() {
        Point startingPoint = new Point(0,1);
        MarsRover marsRover = new MarsRover(startingPoint, Direction.NORTH, new Point(3,3));
        marsRover.receiveCommands("B");
        assertEquals(marsRover.getPosition(), new Point(0,0));
        assertEquals(marsRover.getDirection(), Direction.NORTH);
    }

    @Test
    public void testASingleBackwardCommandBeyondTheNorthWorldBoundMovesTheRover() {
        Point startingPoint = new Point(0,0);
        MarsRover marsRover = new MarsRover(startingPoint, Direction.NORTH, new Point(3,3));
        marsRover.receiveCommands("B");
        assertEquals(marsRover.getPosition(), new Point(0,3));
        assertEquals(marsRover.getDirection(), Direction.NORTH);
    }

    @Test
    public void testASingleBackwardCommandBeyondTheSouthWorldBoundMovesTheRover() {
        Point startingPoint = new Point(0,3);
        MarsRover marsRover = new MarsRover(startingPoint, Direction.SOUTH, new Point(3,3));
        marsRover.receiveCommands("B");
        assertEquals(marsRover.getPosition(), new Point(0,0));
        assertEquals(marsRover.getDirection(), Direction.SOUTH);
    }

    @Test
    public void testASingleBackwardCommandBeyondTheEastWorldBoundMovesTheRover() {
        Point startingPoint = new Point(0,0);
        MarsRover marsRover = new MarsRover(startingPoint, Direction.EAST, new Point(3,3));
        marsRover.receiveCommands("B");
        assertEquals(marsRover.getPosition(), new Point(3,0));
        assertEquals(marsRover.getDirection(), Direction.EAST);
    }

    @Test
    public void testASingleBackwardCommandBeyondTheWestWorldBoundMovesTheRover() {
        Point startingPoint = new Point(3,0);
        MarsRover marsRover = new MarsRover(startingPoint, Direction.WEST, new Point(3,3));
        marsRover.receiveCommands("B");
        assertEquals(marsRover.getPosition(), new Point(0,0));
        assertEquals(marsRover.getDirection(), Direction.WEST);
    }

    @Test
    public void testASingleTurnRightCommandChangesTheRoverFacingNorthDirection() {
        Point startingPoint = new Point(0,0);
        MarsRover marsRover = new MarsRover(startingPoint, Direction.NORTH, new Point(3,3));
        marsRover.receiveCommands("R");
        assertEquals(marsRover.getPosition(), new Point(0,0));
        assertEquals(marsRover.getDirection(), Direction.EAST);
    }

    @Test
    public void testASingleTurnRightCommandChangesTheRoverFacingEastDirection() {
        Point startingPoint = new Point(0,0);
        MarsRover marsRover = new MarsRover(startingPoint, Direction.EAST, new Point(3,3));
        marsRover.receiveCommands("R");
        assertEquals(marsRover.getPosition(), new Point(0,0));
        assertEquals(marsRover.getDirection(), Direction.SOUTH);
    }

    @Test
    public void testASingleTurnRightCommandChangesTheRoverFacingSouthDirection() {
        Point startingPoint = new Point(0,0);
        MarsRover marsRover = new MarsRover(startingPoint, Direction.SOUTH, new Point(3,3));
        marsRover.receiveCommands("R");
        assertEquals(marsRover.getPosition(), new Point(0,0));
        assertEquals(marsRover.getDirection(), Direction.WEST);
    }

    @Test
    public void testASingleTurnRightCommandChangesTheRoverFacingWestDirection() {
        Point startingPoint = new Point(0,0);
        MarsRover marsRover = new MarsRover(startingPoint, Direction.WEST, new Point(3,3));
        marsRover.receiveCommands("R");
        assertEquals(marsRover.getPosition(), new Point(0,0));
        assertEquals(marsRover.getDirection(), Direction.NORTH);
    }

    @Test
    public void testASingleTurnLeftCommandChangesTheRoverFacingNorthDirection() {
        Point startingPoint = new Point(0,0);
        MarsRover marsRover = new MarsRover(startingPoint, Direction.NORTH, new Point(3,3));
        marsRover.receiveCommands("L");
        assertEquals(marsRover.getPosition(), new Point(0,0));
        assertEquals(marsRover.getDirection(), Direction.WEST);
    }

    @Test
    public void testASingleTurnLeftCommandChangesTheRoverFacingEastDirection() {
        Point startingPoint = new Point(0,0);
        MarsRover marsRover = new MarsRover(startingPoint, Direction.EAST, new Point(3,3));
        marsRover.receiveCommands("L");
        assertEquals(marsRover.getPosition(), new Point(0,0));
        assertEquals(marsRover.getDirection(), Direction.NORTH);
    }

    @Test
    public void testASingleTurnLeftCommandChangesTheRoverFacingSouthDirection() {
        Point startingPoint = new Point(0,0);
        MarsRover marsRover = new MarsRover(startingPoint, Direction.SOUTH, new Point(3,3));
        marsRover.receiveCommands("L");
        assertEquals(marsRover.getPosition(), new Point(0,0));
        assertEquals(marsRover.getDirection(), Direction.EAST);
    }

    @Test
    public void testASingleTurnLeftCommandChangesTheRoverFacingWestDirection() {
        Point startingPoint = new Point(0,0);
        MarsRover marsRover = new MarsRover(startingPoint, Direction.WEST, new Point(3,3));
        marsRover.receiveCommands("L");
        assertEquals(marsRover.getPosition(), new Point(0,0));
        assertEquals(marsRover.getDirection(), Direction.SOUTH);
    }

    @Test
    public void testAnInvalidCommandDoesntMoveTheRover() {
        Point startingPoint = new Point(0,0);
        MarsRover marsRover = new MarsRover(startingPoint, Direction.NORTH, new Point(3,3));
        try {
            marsRover.receiveCommands("K");
            fail();
        } catch (RuntimeException ex) {
            assertEquals(ex.getMessage(), MarsRover.INVALID_COMMAND_PROVIDED + "K");
        }
        assertEquals(marsRover.getPosition(), new Point(0,0));
        assertEquals(marsRover.getDirection(), Direction.NORTH);
    }

    @Test
    public void testMultipleCommandDoesMoveTheRoverWithNoObstacles() {
        Point startingPoint = new Point(0,0);
        MarsRover marsRover = new MarsRover(startingPoint, Direction.NORTH, new Point(3,3));
        Point obstacle = marsRover.receiveCommands("FLB");
        assertEquals(marsRover.getPosition(), new Point(1,1));
        assertEquals(marsRover.getDirection(), Direction.WEST);
        assertNull(obstacle);
    }

    @Test
    public void testWhenAnObstacleMultipleCommandDoesMoveTheRoverUpToTheLastPossiblePointFacingNorth() {
        Point startingPoint = new Point(0,0);
        MarsRover marsRover = new MarsRover(startingPoint, Direction.NORTH, new Point(3,3), Arrays.asList(new Point(0,3), new Point(2,3)));
        Point obstacle = marsRover.receiveCommands("FFF");
        assertEquals(marsRover.getPosition(), new Point(0,2));
        assertEquals(marsRover.getDirection(), Direction.NORTH);
        assertEquals(obstacle, new Point(0, 3));
    }

    @Test
    public void testWhenAnObstacleMultipleCommandDoesMoveTheRoverUpToTheLastPossiblePointFacingEast() {
        Point startingPoint = new Point(0,0);
        MarsRover marsRover = new MarsRover(startingPoint, Direction.EAST, new Point(3,3), Arrays.asList(new Point(3,0), new Point(3,2)));
        Point obstacle = marsRover.receiveCommands("FFF");
        assertEquals(marsRover.getPosition(), new Point(2,0));
        assertEquals(marsRover.getDirection(), Direction.EAST);
        assertEquals(obstacle, new Point(3, 0));
    }
}
