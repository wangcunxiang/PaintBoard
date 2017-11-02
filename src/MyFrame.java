

import sun.java2d.loops.DrawPath;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * Created by wangcunxiang on 2017/10/18.
 */
public class MyFrame extends JFrame implements FrameGetShape {
    BufferedImage image = new BufferedImage(1570, 1390,
            BufferedImage.TYPE_INT_BGR); // ����һ��8 λ BGR ��ɫ������ͼ��
    BufferedImage imageclone = new BufferedImage(1570, 1390,
            BufferedImage.TYPE_INT_BGR);
    Graphics gs = image.getGraphics(); // ���ͼ��Ļ�ͼ����
    Graphics2D g = (Graphics2D) gs; // ����ͼ����ת��ΪGraphics2D����
    Graphics gsclone = imageclone.getGraphics();
    Graphics2D gclone = (Graphics2D) gsclone;
    MyCanvas canvas = new MyCanvas(g,gclone);


	private JToolBar toolBar;// ������
	private JButton eraserButton;// ��Ƥ��ť
    private JButton penButton;// ���ʰ�ť
    private JButton strokeButton;// ���Ͱ�ť
	private JButton backgroundButton;// ����ɫ��ť
	private JButton foregroundButton;// ǰ��ɫ��ť
	private JButton clearButton;// �����ť
	private JButton shapeButton;// ͼ�ΰ�ť
	private JButton textButton;//�ı���ť
    private JButton fillButton;//��䰴ť
    private JButton undoButton;//������ť
    private JButton help;//������ť
	private JMenuItem clearMenuItem;// ����˵�
	private JMenuItem usehelp;// ǰ��ɫ�˵�
	private JMenuItem bughelp;// ����ɫ�˵�
	private JMenuItem eraserMenuItem;// ��Ƥ�˵�
	private JMenuItem exitMenuItem;// �˳��˵�
    private JMenuItem newMenuItem;// �򿪲˵�
	private JMenuItem saveMenuItem;// ����˵�
    private JMenuItem openMenuItem;// �򿪲˵�
    private JMenu wordMenu;
    private JMenuBar menuBar;
    private JMenu setziti;
    private JButton wordButton;
    private JMenuItem ziti1;
    private JMenuItem ziti2;
    private JMenuItem ziti3;
    private JMenu setwordsize;
    private JMenuItem wordsize1;
    private JMenuItem wordsize2;
    private JMenuItem wordsize3;
    private JToolBar toolBar2;// ������

    JFileChooser jfcSave;
    JFileChooser jfcOpen;
    private JTextField jtf;

    int number = 0;// �ܹ��м��γ�������
    int strokeWidth = 1;// ���ʿ��

    /**
	 * ���췽��
	 */
	public MyFrame() {//���캯��
		setResizable(false);// ���岻�ܸı��С
		setTitle("��ͼ����");// ���ñ���
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// ����ر���ֹͣ����
		setBounds(100, 50, 1074, 710);// ���ô���λ�úͿ��

		init();// �����ʼ��
		addListener();// ����������
	}

	/**
	 * �����ʼ��
	 */
	private void init() //��ͼ��������ĳ�ʼ��

    {
		g.setColor(canvas.backgroundColor); // �ñ���ɫ���û�ͼ�������ɫ
        gclone.setColor(canvas.backgroundColor);
        canvas.width = 1070;//�������
        canvas.height = 690;//��������
		g.fillRect(0, 0, canvas.width, canvas.height); // �ñ���ɫ�����������
        gclone.fillRect(0, 0, canvas.width, canvas.height);
		g.setColor(canvas.foreColor); // ��ǰ��ɫ���û�ͼ�������ɫ
        gclone.setColor(canvas.foreColor);
		canvas.setImage(image, imageclone); // ���û�����ͼ��
		getContentPane().add(canvas); // ��������ӵ���������Ĭ�ϲ��ֵ��в�λ��

		toolBar = new JToolBar();// ��ʼ��������
		getContentPane().add(toolBar, BorderLayout.NORTH);// ��������ӵ������λ��


        backgroundButton = new JButton();// ��ʼ����ť���󣬲�����ı�����
        backgroundButton.setToolTipText("������ɫ");// ���ð�ť�����ͣ��ʾ
        backgroundButton.setIcon(new ImageIcon("src/img/icon/������ɫ1.jpg"));// ���ð�ťͼ��
        toolBar.add(backgroundButton);// ��������Ӱ�ť
        foregroundButton = new JButton();// ��ʼ����ť���󣬲�����ı�����
        foregroundButton.setToolTipText("ǰ����ɫ");// ���ð�ť�����ͣ��ʾ
        foregroundButton.setIcon(new ImageIcon("src/img/icon/�༭��ɫ.png"));// ���ð�ťͼ��
        toolBar.add(foregroundButton);// ��������Ӱ�ť
        toolBar.addSeparator();// ��ӷָ���

        shapeButton = new JButton();// ��ʼ����ť���󣬲�����ı�����
        shapeButton.setToolTipText("ͼ��");// ���ð�ť�����ͣ��ʾ
        shapeButton.setIcon(new ImageIcon("src/img/icon/ͼ��ѡ��.jpg"));// ���ð�ťͼ��
        toolBar.add(shapeButton);// ��������Ӱ�ť
        eraserButton = new JButton();// ��ʼ����ť���󣬲�����ı�����
        eraserButton.setToolTipText("��Ƥ");// ���ð�ť�����ͣ��ʾ
        eraserButton.setIcon(new ImageIcon("src/img/icon/��Ƥ.jpg"));// ���ð�ťͼ��
        toolBar.add(eraserButton);// ��������Ӱ�ť
        penButton = new JButton();// ��ʼ����ť���󣬲�����ı�����
        penButton.setToolTipText("����");// ���ð�ť�����ͣ��ʾ
        penButton.setIcon(new ImageIcon("src/img/icon/����.png"));// ���ð�ťͼ��
        toolBar.add( penButton);// ��������Ӱ�ť

        for(int i=0;i<30;i++)
        toolBar.addSeparator();// ��ӷָ���

        jtf = new JTextField("���ڴ˴������ı�",10);
        toolBar.add(jtf);
        textButton = new JButton();// ��ʼ����ť���󣬲�����ı�����
        textButton.setToolTipText("�ı�");// ���ð�ť�����ͣ��ʾ


        textButton.setIcon(new ImageIcon("src/img/icon/�ı���ť.jpg"));// ���ð�ťͼ��
        toolBar.add(textButton);// ��������Ӱ�ť
        fillButton = new JButton();// ��ʼ����ť���󣬲�����ı�����
        fillButton.setToolTipText("���");// ���ð�ť�����ͣ��ʾ
        fillButton.setIcon(new ImageIcon("src/img/icon/���.png"));// ���ð�ťͼ��
        toolBar.add(fillButton);// ��������Ӱ�ť

        JMenuBar menuBar = new JMenuBar();// �����˵���
        setJMenuBar(menuBar);// ��������˵���

        JMenu systemMenu = new JMenu("ϵͳ");// ��ʼ���˵����󣬲�����ı�����
        menuBar.add(systemMenu);// �˵�����Ӳ˵�����
        newMenuItem = new JMenuItem("�½�ͼƬ");// ��ʼ���˵�����󣬲�����ı�����
        systemMenu.add(newMenuItem);// �˵���Ӳ˵���
        systemMenu.addSeparator();// ��ӷָ���
        saveMenuItem = new JMenuItem("����ͼƬ");// ��ʼ���˵�����󣬲�����ı�����
        systemMenu.add(saveMenuItem);// �˵���Ӳ˵���
        systemMenu.addSeparator();// ��ӷָ���
        openMenuItem = new JMenuItem("��ͼƬ");// ��ʼ���˵�����󣬲�����ı�����
        systemMenu.add(openMenuItem);// �˵���Ӳ˵���
        systemMenu.addSeparator();// ��ӷָ���
        exitMenuItem = new JMenuItem("�˳�");// ��ʼ���˵�����󣬲�����ı�����
        systemMenu.add(exitMenuItem);// �˵���Ӳ˵���
        wordMenu=new JMenu("��������");
        setwordsize = new JMenu("�������ִ�С");
        //setwordcolor = new JMenuItem("����������ɫ");
        wordsize1=new JMenuItem("20");// ��ʼ���˵�����󣬲�����ı�����
        wordsize2=new JMenuItem("30");// ��ʼ���˵�����󣬲�����ı�����
        wordsize3=new JMenuItem("40");// ��ʼ���˵�����󣬲�����ı�����
        setwordsize.add(wordsize1);// �˵���Ӳ˵���
        setwordsize.add(wordsize2);// �˵���Ӳ˵���
        setwordsize.add(wordsize3);// �˵���Ӳ˵���
        setziti=new  JMenu("��������������");

        //wordMenu.add(setwordcolor);
        wordMenu.add(setziti);
        ziti1 = new JMenuItem("����");//��������ѡ��
        ziti2 = new JMenuItem("����");//��������ѡ��
        ziti3 = new JMenuItem("����");//��������ѡ��
        setziti.add(ziti1);// �˵�����Ӳ˵�����
        setziti.add(ziti2);// �˵�����Ӳ˵�����
        setziti.add(ziti3);// �˵�����Ӳ˵�����
        wordMenu.add(setwordsize);// �˵�����Ӳ˵�����
        menuBar.add(wordMenu);// �˵�����Ӳ˵�����


        JMenu help = new JMenu("����˵��");// ��ʼ���˵����󣬲�����ı�����
        menuBar.add(help);// �˵�����Ӳ˵�����
        usehelp = new JMenuItem("ʹ��˵��");// ��ʼ���˵�����󣬲�����ı�����
        help.add(usehelp);// �˵���Ӳ˵���
        bughelp = new JMenuItem("bug˵��");// ��ʼ���˵�����󣬲�����ı�����
        help.add(bughelp);// �˵���Ӳ˵���



        strokeButton = new JButton();// ��ʼ����ť���󣬲�����ı�����
        strokeButton.setToolTipText("���ʹ�ϸ");// ���ð�ť�����ͣ��ʾ
        strokeButton.setIcon(new ImageIcon("src/img/icon/��ϸ.png"));// ���ð�ťͼ��
        toolBar.add(strokeButton);// ��������Ӱ�ť
        toolBar.addSeparator();// ��ӷָ���

        undoButton = new JButton();// ��ʼ����ť���󣬲�����ı�����
        undoButton.setToolTipText("����");// ���ð�ť�����ͣ��ʾ
        undoButton.setIcon(new ImageIcon("src/img/icon/����.jpg"));// ���ð�ťͼ��
        toolBar.add(undoButton);// ��������Ӱ�ť
        clearButton = new JButton();// ��ʼ����ť���󣬲�����ı�����
        clearButton.setToolTipText("���");// ���ð�ť�����ͣ��ʾ
        clearButton.setIcon(new ImageIcon("src/img/icon/���1.jpg"));// ���ð�ťͼ��
        toolBar.add(clearButton);// ��������Ӱ�ť

        autosave();
        initFileChooser();

    }
    private void initFileChooser() {//�趨�ļ��򿪡�����Ĳ���
        //changeOutlook();
        jfcSave = new JFileChooser();
        jfcOpen = new JFileChooser();
        FileNameExtensionFilter jpeg = new FileNameExtensionFilter("JPEG(*.jpg;*jpeg;*.jpe;*jfif)", "jpg","jpeg","jpe","jfif");
        FileNameExtensionFilter png = new FileNameExtensionFilter("PNG(*.png)", "png");
        jfcSave.addChoosableFileFilter(jpeg);
        jfcSave.addChoosableFileFilter(png);
        jfcSave.setDialogType(JFileChooser.SAVE_DIALOG);
        jfcOpen.addChoosableFileFilter(jpeg);
        jfcOpen.addChoosableFileFilter(png);
        jfcOpen.setDialogType(JFileChooser.OPEN_DIALOG);
    }

	/**
	 * �������Ӷ�������
	 */

    private void addListener()//��ť��������ʵ��
    {

        toolBar.addMouseMotionListener(new MouseMotionAdapter() {// �������������ƶ�����
            public void mouseMoved(final MouseEvent arg0) {// ������ƶ�ʱ
                setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));// �������ָ�����״ΪĬ�Ϲ��
            }
        });
        exitMenuItem.addActionListener(new ActionListener() {// �˳��˵�����Ӷ�������
            public void actionPerformed(final ActionEvent e) {// ���ʱ
                System.exit(0);// ����ر�
            }
        });

        strokeButton.addActionListener(new ActionListener() {//������ť�����úͼ���
            @Override
            public void actionPerformed(ActionEvent e) {
                StrokeWindow strokeWindow = new StrokeWindow(
                        MyFrame.this);// ��������ѡ�����
                int strokeButtonWidth = strokeButton.getWidth();// ��ȡ���ʰ�ť���
                int strokeWindowWidth = strokeWindow.getWidth();// ��ȡ���ʰ�ť�߶�
                int strokeButtonX = strokeButton.getX();// ��ȡ���ʰ�ť������
                int strokeButtonY = strokeButton.getY();// ��ȡ���ʰ�ť������
                // ���㻭����������꣬Ϊ���ʰ�ť�·��밴ť���ж���
                int strokeWindowX = getX() + strokeButtonX
                        - (strokeWindowWidth - strokeButtonWidth) / 2;
                // ���㻭����������꣬Ϊ���ʰ�ť�·�
                int strokeWindowY = getY() + strokeButtonY + 80;
                // ���û����������λ��
                strokeWindow.setLocation(strokeWindowX, strokeWindowY);
                strokeWindow.setVisible(true);// ��������ɼ�
                canvas.text = false;
                canvas.rubber = false;
                canvas.pen = true;
                canvas.drawShape = false;
                canvas.fill = false;
                //System.out.println(strokeWidth);
            }
        });

        backgroundButton.addActionListener(new ActionListener() {// ������ɫ��ť��Ӷ�������
            public void actionPerformed(final ActionEvent arg0) {// ���ʱ
                // ��ѡ����ɫ�Ի��򣬲�������Ϊ�������塢���⡢Ĭ��ѡ�е���ɫ����ɫ��
                Color bgColor = JColorChooser.showDialog(
                        MyFrame.this, "ѡ����ɫ�Ի���", Color.CYAN);
                if (bgColor != null) {// ���ѡ�е���ɫ���ǿյ�
                    canvas.backgroundColor = bgColor;// ��ѡ�е���ɫ��������ɫ����
                }
                backgroundButton.setBackground(canvas.backgroundColor);// ����ɫ��ť��Ҳ����Ϊ���ֱ�����ɫ
                g.setColor(canvas.backgroundColor); // ��ͼ����ʹ�ñ���ɫ
                g.fillRect(0, 0, canvas.width, canvas.height); // ��һ��������ɫ�ķ���������������
                g.setColor(canvas.foreColor); // ��ͼ����ʹ��ǰ��ɫ
                canvas.repaint(); // ���»���
            }
        });
        foregroundButton.addActionListener(new ActionListener() {// ǰ��ɫ��ɫ��ť��Ӷ�������
            public void actionPerformed(final ActionEvent arg0) {// ���ʱ
                // ��ѡ����ɫ�Ի���,��������Ϊ�������塢���⡢Ĭ��ѡ�е���ɫ����ɫ��
                Color fColor = JColorChooser.showDialog(
                        MyFrame.this, "ѡ����ɫ�Ի���", Color.CYAN);
                if (fColor != null) {// ���ѡ�е���ɫ���ǿյ�
                    canvas.foreColor = fColor;// ��ѡ�е���ɫ����ǰ��ɫ����
                }
                foregroundButton.setBackground(canvas.foreColor);// ǰ��ɫ��ť������Ҳ����Ϊ������ɫ
                g.setColor(canvas.foreColor); // ��ͼ����ʹ��ǰ��ɫ
            }
        });
        clearButton.addActionListener(new ActionListener() {// �����ť��Ӷ�������
            public void actionPerformed(final ActionEvent arg0) {// ���ʱ
                g.setColor(canvas.backgroundColor); // ��ͼ����ʹ�ñ���ɫ
                g.fillRect(0, 0, canvas.width, canvas.height); // ��һ��������ɫ�ķ���������������
                g.setColor(canvas.foreColor); // ��ͼ����ʹ��ǰ��ɫ
                gclone.setColor(canvas.backgroundColor); // ��ͼ����ʹ�ñ���ɫ
                gclone.fillRect(0, 0, canvas.width, canvas.height); // ��һ��������ɫ�ķ���������������
                gclone.setColor(canvas.foreColor); // ��ͼ����ʹ��ǰ��ɫ
                canvas.array.clear();//ͼ�ζ������
                canvas.drawAll(g);
                canvas.repaint(); // ���»���
            }
        });
        undoButton.addActionListener(new ActionListener() {// ������ť��Ӷ�������
            public void actionPerformed(final ActionEvent arg0) {// ���ʱ
                if(number>0){//������Գ���
                    number--;
                    String name = "image"+number;//�趨Ҫ�򿪵��ļ�������
                    DrawImageUtil.openImage2(MyFrame.this, canvas, name);//���ú�������ͼ���ļ�
                    canvas.repaint(); // ���»���
                }

            }
        });
        eraserButton.addActionListener(new ActionListener() {// ��Ƥ��ť��Ӷ�������
            public void actionPerformed(final ActionEvent arg0) {
                canvas.text = false;
                canvas.rubber = true;
                canvas.pen = false;
                canvas.drawShape = false;
                canvas.fill = false;
                // canvas.circle = false;
                // canvas.rectangle = false;
                // canvas.line = false;
                // canvas.fillShape = false;
            }
        });
        penButton.addActionListener(new ActionListener() {// ���ʰ�ť��Ӷ�������
            public void actionPerformed(final ActionEvent arg0) {
                canvas.text = false;
                canvas.rubber = false;
                canvas.pen = true;
                canvas.drawShape = false;
                canvas.fill = false;
                //g.setColor(Color.BLACK); // ��ͼ����ʹ�ñ���ɫ
                //canvas.rectangle = false;
                //canvas.circle = false;
                //canvas.line = false;
                //canvas.fillShape = false;
            }
        });

        textButton.addActionListener(new ActionListener() {// �ı���ť��Ӷ�������
            public void actionPerformed(final ActionEvent arg0) {// ���ʱ
                canvas.text = true;
                canvas.rubber = false;
                canvas.pen = false;
                canvas.drawShape = false;
                canvas.fill = false;
                canvas.intext = jtf.getText();
                //System.out.println(canvas.intext);
            }
        });

        fillButton.addActionListener(new ActionListener() {// ��䰴ť��Ӷ�������
            public void actionPerformed(final ActionEvent arg0) {// ���ʱ
                canvas.fill = true;
                canvas.text = false;
                canvas.rubber = false;
                canvas.pen = false;;
                canvas.drawShape = false;
            }
        });


        newMenuItem.addActionListener(new ActionListener() {// ����˵���Ӷ�������
            public void actionPerformed(final ActionEvent e) {// ���ʱ
                canvas.foreColor=Color.black;
                canvas.backgroundColor=Color.white;
                canvas.text = false;
                canvas.rubber = false;
                canvas.pen = true;
                canvas.drawShape = false;
                canvas.fill = false;//����ѡ��ʹ�û���
                canvas.array.clear();
                strokeWidth = 1;
                BasicStroke bs = new BasicStroke(strokeWidth,
                        BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER);
                g.setStroke(bs); // ����ϸ����
                gclone.setStroke(bs); // ����ϸ����
                g.setColor(canvas.backgroundColor); // ��ͼ����ʹ�ñ���ɫ
                gclone.setColor(canvas.backgroundColor);
                canvas.g.setStroke(bs); // ����ϸ����
                canvas.gclone.setStroke(bs); // ����ϸ����
                canvas.g.setColor(canvas.backgroundColor); // ��ͼ����ʹ�ñ���ɫ
                canvas.gclone.setColor(canvas.backgroundColor);
                g.fillRect(0, 0, canvas.width, canvas.height); // ��һ��������ɫ�ķ���������������
                gclone.fillRect(0, 0, canvas.width, canvas.height); // ��һ��������ɫ�ķ���������������
                g.setColor(canvas.foreColor); // ��ͼ����ʹ��ǰ��ɫ
                gclone.setColor(canvas.foreColor); // ��ͼ����ʹ��ǰ��ɫ
                canvas.repaint(); // ���»���
                number=0;//���ɳ������½�֮ǰ
                autosave();
            }
        });

        usehelp.addActionListener(new ActionListener() {// ʹ��˵���˵���Ӷ�������
            public void actionPerformed(final ActionEvent e) {// ���ʱ
                JOptionPane.showMessageDialog(null, "1.\t��������Ҫ�ֶ��½�������ֱ�ӽ��б༭��\n" +
                        "2.\t�ڻ���ͼ��ʱ�ɽ����϶���ͼ�Σ�������������֮�󣬾Ͳ��ٿ��϶�������windows�Ļ��塣\n" +
                        "3.\tͼ���������κη����������䡣\n", "ʹ��˵��", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        bughelp.addActionListener(new ActionListener() {// bug˵���˵���Ӷ�������
            public void actionPerformed(final ActionEvent e) {// ���ʱ
                JOptionPane.showMessageDialog(null, "1. �ڲ���ϵͳ������win7��win8�ϣ����ı����������ֺ󣬲˵����ᱻ������ס��δ�ҵ���Ч���������\n" +
                        "2. ���ڴ��̶�д�����⣬���桢��ȡ�ļ��Ĳ���ż����ʧЧ���ļ�δ��Ҫ�󱣴棬ͬʱ���³�������ż������δ�ҵ���Ч���������\n", "bug˵��", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        saveMenuItem.addActionListener(new ActionListener() {// ����˵���Ӷ�������
            public void actionPerformed(ActionEvent e) {// ���ʱ
                DrawImageUtil.saveImage1(MyFrame.this, image);// ��ӡͼƬ
            }
        });
        openMenuItem.addActionListener(new ActionListener() {// �򿪲˵���Ӷ�������
            public void actionPerformed(ActionEvent e) {// ���ʱ
                DrawImageUtil.openImage1(MyFrame.this, canvas);// ��ͼƬ
            }
        });
        shapeButton.addActionListener(new ActionListener() {// ͼ�ΰ�ť��Ӷ�������
            public void actionPerformed(ActionEvent e) {// ���ʱ
                ShapeWindow shapeWindow = new ShapeWindow(
                        MyFrame.this, canvas);// ����ͼ��ѡ�����
                int shapeButtonWidth = shapeButton.getWidth();// ��ȡͼ��ť���
                int shapeWindowWidth = shapeWindow.getWidth();// ��ȡͼ�ΰ�ť�߶�
                int shapeButtonX = shapeButton.getX();// ��ȡͼ�ΰ�ť������
                int shapeButtonY = shapeButton.getY();// ��ȡͼ�ΰ�ť������
                // ����ͼ����������꣬Ϊͼ�ΰ�ť�·��밴ť���ж���
                int shapeWindowX = getX() + shapeButtonX
                        - (shapeWindowWidth - shapeButtonWidth) / 2;
                // ����ͼ����������꣬Ϊͼ�ΰ�ť�·�
                int shapeWindowY = getY() + shapeButtonY + 80;
                // ����ͼ���������λ��
                shapeWindow.setLocation(shapeWindowX, shapeWindowY);
                shapeWindow.setVisible(true);// ͼ������ɼ�
            }
        });
        wordsize1.addActionListener(new ActionListener() {// ���������С20��ť��Ӷ�������
            public void actionPerformed(ActionEvent e) {
                canvas.key1 = 20;
                canvas.wordsize=Integer.valueOf(canvas.key1);
            }
        });
        wordsize2.addActionListener(new ActionListener() {// ���������С30��ť��Ӷ�������
            public void actionPerformed(ActionEvent e) {
                canvas.key1 = 30;
                canvas.wordsize=Integer.valueOf(canvas.key1);
            }
        });
        wordsize3.addActionListener(new ActionListener() {// ���������С40��ť��Ӷ�������
            public void actionPerformed(ActionEvent e) {
                canvas.key1 = 40;
                canvas.wordsize=Integer.valueOf(canvas.key1);
            }
        });
        setziti.addActionListener(new ActionListener() {// ���������ʽ��ť��Ӷ�������
            public void actionPerformed(ActionEvent e) {
                canvas.key2 = JOptionPane.showInputDialog(MyFrame.this,"�������壺");
                canvas.ziti=canvas.key2;
            }
        });

        ziti1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {// ���������ʽ���尴ť��Ӷ�������
                canvas.key2 = "����";
                canvas.ziti=canvas.key2;
            }
        });
        ziti2.addActionListener(new ActionListener() {// ���������ʽ���尴ť��Ӷ�������
            public void actionPerformed(ActionEvent e) {
                canvas.key2 = "����";
                canvas.ziti=canvas.key2;
            }
        });
        ziti3.addActionListener(new ActionListener() {// ���������ʽ���尴ť��Ӷ�������
            public void actionPerformed(ActionEvent e) {
                canvas.key2 = "����";
                canvas.ziti=canvas.key2;
            }
        });

        canvas.addListener(MyFrame.this);
    }

    /**
	 * FrameGetShape�ӿ�ʵ���࣬���ڻ��ͼ�οռ䷵�صı�ѡ�е�ͼ��
	 */
	public void getShape(Shapes shape) {
        canvas.shape = shape;// �����ص�ͼ�ζ��󸶸����ȫ�ֱ���
        canvas.drawShape = true;// ��ͼ�α�ʶ����Ϊtrue��˵��ѡ����껭����ͼ�Σ���������
	}// getShape()����

    public void autosave(){//�洢��ǰͼƬ
	    String name = "image"+number;//�趨Ҫ�Զ�������ļ���
        DrawImageUtil.saveImage2(MyFrame.this, image, name);//���ú����������ļ�
    }



	public static void main(String[] args) {
		MyFrame frame = new MyFrame();// �����������
		frame.setVisible(true);// �ô���ɼ�
	}// main()����

}// MyFrame�����
