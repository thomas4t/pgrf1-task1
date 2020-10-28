package rasterize;

import model.Line;
import model.Point;
import model.Polygon;

import java.awt.*;
import java.util.List;

public class PolygonRasterizer {
    private LineRasterizer lr;

    public PolygonRasterizer(LineRasterizer lineRasterizer) {
        lr = lineRasterizer;
    }

    public void rasterize(Polygon polygon) {
        // make sure we render at least a line
        if(polygon.getSize() >=2){
            Point first = polygon.points.get(0);
            Point last = polygon.points.get(polygon.points.size() - 1);
            for (int i = 0; i < polygon.points.size(); i++) {
                Point p1 = polygon.points.get(i);
                Point p2;
                if (p1 == last) {
                    p2 = first;
                } else {
                    p2 = polygon.points.get(i + 1);
                }
                Line line = new Line(p1, p2, 0xffff00);
                lr.rasterize(line);
            }
        }
    }

}
