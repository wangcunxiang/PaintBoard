

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

/**
 * Created by wangcunxiang on 2017/10/21.
 */
public class ShapeWindow extends JWindow {
    private Shapes shapes;
    private FrameGetShape frame;

    public ShapeWindow(FrameGetShape frame) {
        this.frame = frame;
        this.init();
    }

    public ShapeWindow(int x, int y, FrameGetShape frame) {
        this.frame = frame;
        this.setLocation(x, y);
        this.init();
    }

    private void init() {
        this.setSize(200, 100);
        Container c = this.getContentPane();
        c.setLayout(new BorderLayout());
        JPanel centerPanel = new JPanel();
        JPanel southPanel = new JPanel();
        Ellipse2D e = new Ellipse2D.Double(0.0D, 0.0D, 14.0D, 14.0D);
        ShapeWindow.ShapeButton yuan = new ShapeWindow.ShapeButton(e);
        centerPanel.add(yuan);
        Rectangle2D r = new java.awt.geom.Rectangle2D.Double(0.0D, 0.0D, 14.0D, 14.0D);
        ShapeWindow.ShapeButton fang = new ShapeWindow.ShapeButton(r);
        centerPanel.add(fang);
        c.add(centerPanel, "Center");
        FlowLayout flow = new FlowLayout(2);
        southPanel.setLayout(flow);
        JButton cancel = new JButton("È¡Ïû");
        southPanel.add(cancel);
        cancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ShapeWindow.this.dispose();
            }
        });
        c.add(southPanel, "South");
        this.pack();
    }

    class ShapeButton extends JPanel {
        public ShapeButton(final Shape shape) {
            this.setSize(20, 20);
            this.setLayout(new BorderLayout());
            BufferedImage img = new BufferedImage(15, 15, 4);
            Graphics2D g = img.createGraphics();
            g.setColor(Color.WHITE);
            g.fillRect(0, 0, img.getWidth(), img.getHeight());
            g.setColor(Color.BLACK);
            g.draw(shape);
            JButton btnNewButton = new JButton();
            btnNewButton.setIcon(new ImageIcon(img));
            JPanel p = new JPanel();
            p.add(btnNewButton);
            this.add(p, "Center");
            JPanel south = new JPanel();
            south.setLayout(new FlowLayout());
            final JSpinner spinnerLeft = new JSpinner();
            spinnerLeft.setValue(Integer.valueOf(50));
            south.add(new JLabel("¿í"));
            south.add(spinnerLeft);
            final JSpinner spinnerRigth = new JSpinner();
            spinnerRigth.setValue(Integer.valueOf(50));
            south.add(new JLabel("¸ß"));
            south.add(spinnerRigth);
            this.add(south, "South");
            btnNewButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if(shape instanceof Ellipse2D) {
                        ShapeWindow.this.shapes = new Shapes(25377, ((Integer)spinnerLeft.getValue()).intValue(), ((Integer)spinnerRigth.getValue()).intValue());
                    }

                    if(shape instanceof Rectangle2D) {
                        ShapeWindow.this.shapes = new Shapes(25637, ((Integer)spinnerLeft.getValue()).intValue(), ((Integer)spinnerRigth.getValue()).intValue());
                    }

                    ShapeWindow.this.frame.getShape(ShapeWindow.this.shapes);
                    ShapeWindow.this.dispose();
                }
            });
        }
    }
}
