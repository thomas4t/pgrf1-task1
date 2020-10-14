package model;

public class Point {

    private final int x, y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Point(double x, double y) {
        this.x = (int) Math.round(x);
        this.y = (int) Math.round(y);
    }

    public int getY(){return y;}

    //TODO
    float distanceTo(Point p){
        return 0;
    }

    // Point p = new Point(3,4);
    // p = new Point(5, p.getY());
}
