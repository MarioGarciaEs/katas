package com.mario;

import java.awt.Point;
import java.util.Map;
import java.util.Optional;

import static java.util.Map.entry;

// https://www.codewars.com/kata/58663693b359c4a6560001d6/train/java
public class MazeRunner {

    private static final int WALL = 1;
    private static final int START = 2;
    private static final int FINISH = 3;
    public static final String FINISH_MSG = "Finish";
    public static final String DEAD_MSG = "Dead";
    public static final String LOST_MSG = "Lost";

    private static final Map<String, Point> directionsMap = Map.ofEntries(
        entry("N", new Point(0, -1)),
        entry("W", new Point(-1, 0)),
        entry("E", new Point(1, 0)),
        entry("S", new Point(0, 1))
    );

    public static String walk(int[][] maze, String[] directions) {
        Point currentPoint = findStartPoint(maze).orElseThrow();

        for(String direction : directions) {
            if(!directionsMap.containsKey(direction)) {
               throw new IllegalArgumentException("Invalid direction");
            }

            Point directionVector = directionsMap.get(direction);
            currentPoint.translate(directionVector.x, directionVector.y);

            if(!isInBoardLimits(maze, currentPoint) || isPositionAWall(maze, currentPoint)) {
                return DEAD_MSG;
            }

            if(isAtFinishPosition(maze, currentPoint)) {
                return FINISH_MSG;
            }
        }

        return LOST_MSG;
    }

    private static Optional<Point> findStartPoint(int[][] maze) {
        for(int y = 0; y < maze.length; y++) {
            for(int x = 0; x < maze.length; x++) {
                if(maze[y][x] == MazeRunner.START) {
                    return Optional.of(new Point(x, y));
                }
            }
        }

        return Optional.empty();
    }

    private static boolean isInBoardLimits(int[][] maze, Point currentPoint) {
        return currentPoint.x >= 0 && currentPoint.x < maze.length && currentPoint.y >= 0 && currentPoint.y < maze.length;
    }

    private static boolean isPositionAWall(int[][] maze, Point currentPoint) {
        return maze[currentPoint.y][currentPoint.x] == WALL;
    }

    private static boolean isAtFinishPosition(int[][] maze, Point currentPoint) {
        return maze[currentPoint.y][currentPoint.x] == FINISH;
    }

}
