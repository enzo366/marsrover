package MarsRover;

import java.util.List;

public class Point {
	private int x;
	private int y;

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Point other = (Point) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public boolean isCollidingALimit(Direction direction, Point extent, boolean inverted) {
	    if (inverted) {
            switch (direction) {
                case NORTH:
                    return getY() == 0;
                case SOUTH:
                    return getY() == extent.getY();
                case EAST:
                    return getY() == 0;
                case WEST:
                    return getX() == extent.getX();
            }
        } else {
            switch (direction) {
                case NORTH:
                    return getY() == extent.getY();
                case SOUTH:
                    return getY() == 0;
                case EAST:
                    return getX() == extent.getX();
                case WEST:
                    return getY() == 0;
            }
        }
        return false;
    }

    private Point getFirstCollidingObstacle(Direction direction, List<Point> obstacles, boolean inverted) {
        int offset = inverted ? 1 : -1;
        for (Point obstacle : obstacles) {
            Point obstacleLimit = new Point(obstacle.x + offset,obstacle.y + offset);
            if (isCollidingALimit(direction, obstacleLimit, inverted)) {
                return obstacle;
            }
        }
        return null;
    }

    public Point moveForward(Direction direction, Point worldBounds, List<Point> obstacles) {
        if (obstacles != null) {
            Point obstacle = getFirstCollidingObstacle(direction, obstacles, false);
            if (obstacle != null) {
                return obstacle;
            }
        }
        boolean isOutside = isCollidingALimit(direction, worldBounds, false);
        switch (direction) {
            case NORTH:
                this.y = isOutside ? 0 : y + 1;
                break;
            case SOUTH:
                this.y = isOutside ? worldBounds.getY() : y - 1;
                break;
            case EAST:
                this.x = isOutside ? 0 : x + 1;
                break;
            case WEST:
                this.x = isOutside ? worldBounds.getX() : x - 1;
                break;
        }
        return null;
    }

    public Point moveBackward(Direction direction, Point worldBounds, List<Point> obstacles) {
        if (obstacles != null) {
            Point obstacle = getFirstCollidingObstacle(direction, obstacles, true);
            if (obstacle != null) {
                return obstacle;
            }
        }
        boolean isOutside = isCollidingALimit(direction, worldBounds, true);
        switch (direction) {
            case NORTH:
                this.y = isOutside ? worldBounds.getY() : y - 1;
                break;
            case SOUTH:
                this.y = isOutside ? 0 : y + 1;
                break;
            case EAST:
                this.x = isOutside ? worldBounds.getX() : x - 1;
                break;
            case WEST:
                this.x = isOutside ? 0 : x + 1;
                break;
        }
        return null;
    }

    @Override
    public String toString() {
        return "x:" + this.x + ", y:" + this.y;
    }
}
