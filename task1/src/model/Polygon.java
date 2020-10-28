package model;

import java.util.ArrayList;
import java.util.List;

public class Polygon {
    public List<Point> points = new ArrayList<>();

    public void removeLastPoint() {
        points.remove(points.size() - 1);
    }

    public int getSize(){
        return points.size();
    }
}
