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

    public int getX() {
        return x;
    }

    public int getY(){
        return y;
    }


    float distanceTo(Point p){
        int x1,x2,y1,y2;
        x1=this.x;
        y1=this.y;
        x2=p.getX();
        y2=p.getY();

        return (float) Math.sqrt((x2-x1)*(x2-x1) + (y2-y1)*(y2-y1));
    }

    }
