//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Ellipse2D.Double;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JWindow;


public class StrokeWindow extends JWindow {

    private Shapes shapes;
    private MyFrame frame;

    public StrokeWindow(MyFrame frame) {
        this.frame = frame;
        this.init(frame);
    }


    private void init(MyFrame frame) {//初始化操作
        this.setSize(200, 100);
        Container c = this.getContentPane();
        c.setLayout(new BorderLayout());

        JPanel centerPanel = new JPanel();
        JPanel southPanel = new JPanel();
        Ellipse2D e = new Ellipse2D.Double(0.0D, 0.0D, 14.0D, 14.0D);

        //ShapeWindow.ShapeButton yuan = new ShapeWindow.ShapeButton(e);
        //centerPanel.add(yuan);

        Rectangle2D r = new java.awt.geom.Rectangle2D.Double(0.0D, 0.0D, 14.0D, 14.0D);

        //ShapeWindow.ShapeButton fang = new ShapeWindow.ShapeButton(r);
        //centerPanel.add(fang);

        c.add(centerPanel, "Center");
        FlowLayout flow = new FlowLayout(2);
        southPanel.setLayout(flow);//以上为设置

        JButton xistroke = new JButton("细线");//细线按钮的设置
        southPanel.add(xistroke);
        xistroke.addActionListener(new ActionListener() {// “细线”按钮添加动作监听
            public void actionPerformed(final ActionEvent arg0) {// 点击时
                frame.strokeWidth = 1;
                // 声明画笔的属性，几像素，线条末端无修饰，折线处呈尖角
                BasicStroke bs = new BasicStroke(frame.strokeWidth,
                        BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER);
                frame.canvas.g.setStroke(bs); // 画图工具使用此画笔
                frame.canvas.gclone.setStroke(bs); // 画图工具使用此画笔
                StrokeWindow.this.dispose();
            }
        });
        JButton zhongstroke = new JButton("中线");//较粗线按钮的设置
        southPanel.add(zhongstroke);
        zhongstroke.addActionListener(new ActionListener() {// “较粗”按钮添加动作监听
            public void actionPerformed(final ActionEvent arg0) {
                frame.strokeWidth = 2;
                // 声明画笔的属性，几像素，线条末端无修饰，折线处呈尖角
                BasicStroke bs = new BasicStroke(frame.strokeWidth,
                        BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER);
                frame.canvas.g.setStroke(bs); // 画图工具使用此画笔
                frame.canvas.gclone.setStroke(bs); // 画图工具使用此画笔
                StrokeWindow.this.dispose();
            }
        });

        JButton custroke = new JButton("粗线");//粗线按钮的设置
        southPanel.add(custroke);
        custroke.addActionListener(new ActionListener() {// “粗线”按钮添加动作监听
            public void actionPerformed(final ActionEvent arg0) {
                frame.strokeWidth = 4;
                // 声明画笔的属性，几像素，线条末端无修饰，折线处呈尖角
                BasicStroke bs = new BasicStroke(frame.strokeWidth,
                        BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER);
                frame.canvas.g.setStroke(bs); // 画图工具使用此画笔
                frame.canvas.gclone.setStroke(bs); // 画图工具使用此画笔
                StrokeWindow.this.dispose();
            }
        });

        JButton cancel = new JButton("取消");
        southPanel.add(cancel);

        cancel.addActionListener(new ActionListener() {//取消按钮的设置
            public void actionPerformed(ActionEvent e) {
                StrokeWindow.this.dispose();
            }
        });
        c.add(southPanel, "South");
        this.pack();
    }

}
