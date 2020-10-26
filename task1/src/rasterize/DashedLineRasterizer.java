package rasterize;

import model.Line;

import java.awt.*;

public class DashedLineRasterizer extends LineRasterizer{

    public DashedLineRasterizer(Raster raster) {
        super(raster);
    }

    @Override
    public void rasterize(Line line) {
        int x1 = line.getX1();
        int x2 = line.getX2();
        int y1 = line.getY1();
        int y2 = line.getY2();

        Graphics g = ((RasterBufferedImage)raster).getImg().getGraphics();
        Graphics2D g2d = (Graphics2D) g;

        float[] dash1 = { 2f, 0f, 2f };

        BasicStroke bs1 = new BasicStroke(1,
                BasicStroke.CAP_BUTT,
                BasicStroke.JOIN_ROUND,
                1.0f,
                dash1,
                2f);
        g2d.setStroke(bs1);
        g2d.drawLine(x1, y1, x2, y2);
    }

}
