package com.mccoy;

import java.util.*;

/**
 * Path finding algorithm
 */
public class PathFinder {

   static char[][] AREA_MAP = new char[][]{
            {'S','0','.','0','.'},
            {'.','.','.','0','0'},
            {'0','0','.','0','F'},
            {'.','0','.','0','.'},
            {'.','0','.','.','.'},
            {'.','0','.','0','0'},
            {'.','0','.','0','0'}
    };

   static List<Point> VISITIED_POSITIONS = new ArrayList<>();

    static Point END_POSITION = new Point(2,4);

    public static void main(String[] args) {

        int[] start = new int[]{0,0};

        PRINT_MAP();

        if(findPath(start)) {
            System.out.println("Path Solved!");
            PRINT_MAP();
        } else {
            System.out.println("Path Unsolvable!");
        }


    }

    public static  boolean findPath(int[] position) {
        int col = position[1];
        int row = position[0];

        Point currentPoint = new Point(row, col);

        //check to see if we already visited this point
        if(VISITIED_POSITIONS.contains(currentPoint)) {
            return false;
        }

        VISITIED_POSITIONS.add(currentPoint);


        //we found the end
        if(END_POSITION.getCol() == currentPoint.getCol() && END_POSITION.getRow() == currentPoint.getRow()) {
            AREA_MAP[currentPoint.getRow()][currentPoint.getCol()] = '*';
            return true;
        }

        //hit boundary
        if(currentPoint.getRow() < 0  || currentPoint.getRow() >= AREA_MAP.length) {
            return false;
        }

        //hit boundary
        if(currentPoint.getCol() < 0 || currentPoint.getCol() >= AREA_MAP[0].length) {
            return false;
        }


        //hit wall
        if(AREA_MAP[currentPoint.getRow()][currentPoint.getCol()] == '0') {
            return false;
        }

        AREA_MAP[currentPoint.getRow()][currentPoint.getCol()] = '*';

        //move up
        boolean returnPath = findPath(new int[]{currentPoint.getRow(), currentPoint.getCol() - 1});

        if(!returnPath) {
            //move right
            returnPath = findPath(new int[]{currentPoint.getRow() + 1, currentPoint.getCol()});
        }

        if(!returnPath) {
            //move down
            returnPath = findPath(new int[]{currentPoint.getRow(), currentPoint.getCol() + 1});
        }

        if(!returnPath) {
            //move left
            returnPath = findPath(new int[]{currentPoint.getRow() - 1, currentPoint.getCol()});
        }

        System.out.printf("Path Taken -- row:%d col:%d\n", currentPoint.getRow(), currentPoint.getCol());
        return returnPath;
    }

    public static class Point {
        int row;
        int col;

        public Point(int row, int col) {
            this.row = row;
            this.col = col;
        }

        public int getRow() {
            return row;
        }

        public int getCol() {
            return col;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Point)) return false;
            Point point = (Point) o;
            return row == point.row &&
                    col == point.col;
        }

        @Override
        public int hashCode() {
            return Objects.hash(row, col);
        }
    }

    public static void PRINT_MAP(){
        for(char top : AREA_MAP[0]) {
            System.out.print(" - ");
        }
        System.out.print("-\n");
        for(char[] row : AREA_MAP) {
            System.out.print("|");
            for(char ch : row) {
                System.out.printf(" %s ",ch);
            }
            System.out.print("|\n");
        }
        for(char top : AREA_MAP[0]) {
            System.out.print(" - ");
        }
        System.out.print("-\n");
    }

}
