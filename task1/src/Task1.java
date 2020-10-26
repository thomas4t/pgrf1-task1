import model.Line;
import model.Point;
import model.Polygon;
import rasterize.DashedLineRasterizer;
import rasterize.FilledLineRasterizer;
import rasterize.PolygonRasterizer;
import rasterize.RasterBufferedImage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * trida pro kresleni na platno: zobrazeni pixelu, ovladani mysi
 *
 * @author PGRF FIM UHK
 * @version 2020
 */
public class Task1 {

    private final JPanel panel;
    private final RasterBufferedImage rasterImg;
    private final FilledLineRasterizer filledLineRasterizer;
    private final DashedLineRasterizer dashedLineRasterizer;

    private final PolygonRasterizer filledPlgRasterizer;
    private final PolygonRasterizer dashedPlgRasterizer;

    Polygon polygon;


    public Task1(int width, int height) {
        JFrame frame = new JFrame();

        frame.setLayout(new BorderLayout());
        frame.setTitle("UHK FIM PGRF : " + this.getClass().getName());
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        rasterImg = new RasterBufferedImage(width, height);
        filledLineRasterizer = new FilledLineRasterizer(rasterImg);
        dashedLineRasterizer = new DashedLineRasterizer(rasterImg);

        filledPlgRasterizer = new PolygonRasterizer(filledLineRasterizer);
        dashedPlgRasterizer = new PolygonRasterizer(dashedLineRasterizer);

        polygon = new Polygon();

        panel = new JPanel() {
            private static final long serialVersionUID = 1L;

            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                present(g);
            }
        };
        panel.setPreferredSize(new Dimension(width, height));

        frame.add(panel);
        frame.pack();
        frame.setVisible(true);

        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                    /*
                    * Part 2 - creating a polygon
                    */
                    Point p = new Point(e.getX(), e.getY());
                    polygon.points.add(p);
                    // make sure we render something
                    int size =polygon.points.size();
                    if(size >= 2){
                        clear();
                        // even numbers render filled
                        if(size % 2 == 0){
                            filledPlgRasterizer.rasterize(polygon);
                        }else{
                            // odd numbers render dashed
                            dashedPlgRasterizer.rasterize(polygon);
                        }

                        panel.repaint();
                    }
            }
        });

        //make sure addKeyListener works
        panel.requestFocus();
        panel.requestFocusInWindow();

        panel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_C){
                    polygon = new Polygon();
                    start();
                }
            }
        });
    }

    public void clear() {
        Graphics gr = rasterImg.getGraphics();
        gr.setColor(new Color(0x2f2f2f));
        gr.fillRect(0, 0, rasterImg.getWidth(), rasterImg.getHeight());
    }

    public void present(Graphics graphics) {
        rasterImg.repaint(graphics);
    }

    public void start() {
        clear();
        rasterImg.getGraphics().drawString("Click around to make stuff happen, press C to clear.", 5, rasterImg.getHeight() - 5);
        panel.repaint();
        Line l = new Line(20,500,400,50, 0x2f2f2f);
        dashedLineRasterizer.rasterize(l);

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Task1(800, 600).start());
    }

}
