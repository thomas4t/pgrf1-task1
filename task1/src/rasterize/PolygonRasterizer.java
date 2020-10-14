package rasterize;

import model.Line;
import model.Point;
import model.Polygon;

import java.awt.*;
import java.util.List;

public class PolygonRasterizer {
    private LineRasterizer lr;
    public PolygonRasterizer(LineRasterizer lineRasterizer){
        lr = lineRasterizer;
    }

    //TODO
    public void rasterize(Polygon polygon){
           for (int i = 0; i<polygon.points.size(); i++){
                Point p1 = polygon.points.get(i);
                Point p2 = polygon.points.get(i+1); //!!!!
                Line line = new Line(p1, p2, 0xffff00);
                lr.rasterize(line);
                //todo
           }
    }

}
