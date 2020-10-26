package rasterize;

import model.Line;

public class FilledLineRasterizer extends LineRasterizer {
    public FilledLineRasterizer(Raster raster) {
        super(raster);
    }

    @Override
    public void rasterize(Line line) {
        int color = line.getColor();

        int x1 = line.getX1();
        int x2 = line.getX2();
        int y1 = line.getY1();
        int y2 = line.getY2();

        // Bresenhamův algoritmus
        if ((x1 == x2) && (y1 == y2)) {
            raster.setPixel(x1, y1, color);

        } else {
            int dx = Math.abs(x2 - x1);
            int dy = Math.abs(y2 - y1);
            int delta = dx - dy;

            int shift_x, shift_y;

            if (x1 < x2) shift_x = 1; else shift_x = -1;
            if (y1 < y2) shift_y = 1; else shift_y = -1;

            while ((x1 != x2) || (y1 != y2)) {

                int p = 2 * delta;

                if (p > -dy) {
                    delta = delta - dy;
                    x1 = x1 + shift_x;
                }
                if (p < dx) {
                    delta = delta + dx;
                    y1 = y1 + shift_y;
                }
                raster.setPixel(x1, y1, color);
            }
        }
    }
}
