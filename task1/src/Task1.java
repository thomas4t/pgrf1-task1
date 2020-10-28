import model.Point;
import model.Polygon;
import rasterize.DashedLineRasterizer;
import rasterize.FilledLineRasterizer;
import rasterize.PolygonRasterizer;
import rasterize.RasterBufferedImage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

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
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);

                // * on mouse release - appears as "CLICK" for user - render filled polygon
                Point p = new Point(e.getX(), e.getY());
                polygon.points.add(p);

                setInitialCanvas();
                filledPlgRasterizer.rasterize(polygon);
                panel.repaint();
            }
        });

        panel.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                super.mouseDragged(e);

                // * on mouse drag - render dashed polygon *preview*
                if (polygon.getSize() >= 2) {
                    polygon.removeLastPoint();
                }
                Point p = new Point(e.getX(), e.getY());
                polygon.points.add(p);

                setInitialCanvas();

                dashedPlgRasterizer.rasterize(polygon);
                panel.repaint();
            }

        });

        //make sure addKeyListener works
        panel.requestFocus();
        panel.requestFocusInWindow();

        panel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_C) {
                    polygon = new Polygon();
                    start();
                }
            }
        });
    }


    private void setInitialCanvas() {
        Graphics gr = rasterImg.getGraphics();
        gr.setColor(new Color(0x2f2f2f));
        gr.fillRect(0, 0, rasterImg.getWidth(), rasterImg.getHeight());
        rasterImg.getGraphics().drawString("Click or drag your mouse around to make magic happen.", 5, rasterImg.getHeight() - 20);
        rasterImg.getGraphics().drawString("Press C to reset.", 5, rasterImg.getHeight() - 5);
    }

    public void present(Graphics graphics) {
        rasterImg.repaint(graphics);
    }

    public void start() {
        setInitialCanvas();
        panel.repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Task1(800, 600).start());
    }

}
