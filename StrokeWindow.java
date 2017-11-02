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


    private void init(MyFrame frame) {//��ʼ������
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
        southPanel.setLayout(flow);//����Ϊ����

        JButton xistroke = new JButton("ϸ��");//ϸ�߰�ť������
        southPanel.add(xistroke);
        xistroke.addActionListener(new ActionListener() {// ��ϸ�ߡ���ť��Ӷ�������
            public void actionPerformed(final ActionEvent arg0) {// ���ʱ
                frame.strokeWidth = 1;
                // �������ʵ����ԣ������أ�����ĩ�������Σ����ߴ��ʼ��
                BasicStroke bs = new BasicStroke(frame.strokeWidth,
                        BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER);
                frame.canvas.g.setStroke(bs); // ��ͼ����ʹ�ô˻���
                frame.canvas.gclone.setStroke(bs); // ��ͼ����ʹ�ô˻���
                StrokeWindow.this.dispose();
            }
        });
        JButton zhongstroke = new JButton("����");//�ϴ��߰�ť������
        southPanel.add(zhongstroke);
        zhongstroke.addActionListener(new ActionListener() {// ���ϴ֡���ť��Ӷ�������
            public void actionPerformed(final ActionEvent arg0) {
                frame.strokeWidth = 2;
                // �������ʵ����ԣ������أ�����ĩ�������Σ����ߴ��ʼ��
                BasicStroke bs = new BasicStroke(frame.strokeWidth,
                        BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER);
                frame.canvas.g.setStroke(bs); // ��ͼ����ʹ�ô˻���
                frame.canvas.gclone.setStroke(bs); // ��ͼ����ʹ�ô˻���
                StrokeWindow.this.dispose();
            }
        });

        JButton custroke = new JButton("����");//���߰�ť������
        southPanel.add(custroke);
        custroke.addActionListener(new ActionListener() {// �����ߡ���ť��Ӷ�������
            public void actionPerformed(final ActionEvent arg0) {
                frame.strokeWidth = 4;
                // �������ʵ����ԣ������أ�����ĩ�������Σ����ߴ��ʼ��
                BasicStroke bs = new BasicStroke(frame.strokeWidth,
                        BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER);
                frame.canvas.g.setStroke(bs); // ��ͼ����ʹ�ô˻���
                frame.canvas.gclone.setStroke(bs); // ��ͼ����ʹ�ô˻���
                StrokeWindow.this.dispose();
            }
        });

        JButton cancel = new JButton("ȡ��");
        southPanel.add(cancel);

        cancel.addActionListener(new ActionListener() {//ȡ����ť������
            public void actionPerformed(ActionEvent e) {
                StrokeWindow.this.dispose();
            }
        });
        c.add(southPanel, "South");
        this.pack();
    }

}
