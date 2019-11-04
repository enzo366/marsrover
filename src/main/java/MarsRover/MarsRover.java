package MarsRover;

import java.util.List;

public class MarsRover {
    public static final String INVALID_STARTING_POINT = "Invalid starting point";
    public static final String INVALID_DIRECTION = "Invalid direction";
    public static final String INVALID_COMMANDS_PROVIDED = "Invalid commands provided";
    public static final String INVALID_WORLD_BOUNDS = "Invalid world bounds";
    public static final String INVALID_COMMAND_PROVIDED = "Invalid command provided: ";
    private Point position;
    private Direction direction;
    private Point worldBounds;
    private List<Point> obstacles;

    public MarsRover(Point position, Direction direction, Point worldBounds) {
        if (position == null) throw new RuntimeException(INVALID_STARTING_POINT);
        if (direction == null) throw new RuntimeException(INVALID_DIRECTION);
        if (worldBounds == null) throw new RuntimeException(INVALID_WORLD_BOUNDS);
        this.position = position;
        this.direction = direction;
        this.worldBounds = worldBounds;
    }

    public MarsRover(Point position, Direction direction, Point worldBounds, List<Point> obstacles) {
        this(position, direction, worldBounds);
        this.obstacles = obstacles;
    }

    public Point receiveCommands(String commands) {
        if (commands == null || commands.length() == 0) throw new RuntimeException(INVALID_COMMANDS_PROVIDED);
        Point firstObstacle = null;
        for (char command : commands.toCharArray()) {
            firstObstacle = receiveSingleCommand(command);
            if (firstObstacle != null) {
                break;
            }
        }
        return firstObstacle;
    }

    private Point receiveSingleCommand(char command) {
        switch (Character.toUpperCase(command)) {
            case 'F':
                return position.moveForward(direction, worldBounds, obstacles);
            case 'B':
                return position.moveBackward(direction, worldBounds, obstacles);
            case 'R':
                turnRight();
                break;
            case 'L':
                turnLeft();
                break;
            default:
                throw new RuntimeException(INVALID_COMMAND_PROVIDED + command);
        }
        return null;
    }

    private void turnRight() {
        switch (direction) {
            case NORTH:
                direction = Direction.EAST;
                break;
            case EAST:
                direction = Direction.SOUTH;
                break;
            case SOUTH:
                direction = Direction.WEST;
                break;
            case WEST:
                direction = Direction.NORTH;
                break;
        }
    }

    private void turnLeft() {
        switch (direction) {
            case NORTH:
                direction = Direction.WEST;
                break;
            case EAST:
                direction = Direction.NORTH;
                break;
            case SOUTH:
                direction = Direction.EAST;
                break;
            case WEST:
                direction = Direction.SOUTH;
                break;
        }
    }

    public Point getPosition() {
        return position;
    }

    public Direction getDirection() {
        return direction;
    }
}
