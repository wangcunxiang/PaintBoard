

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

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;

import com.mr.util.DrawImageUtil;
import com.mr.util.FrameGetShape;
import com.mr.util.ShapeWindow;
import com.mr.util.Shapes;

/**
 * ��ͼ������
 * 
 * @������λ ����ʡ���տƼ����޹�˾
 * @��˾��ַ www.mingribook.com
 */
public class MyFrame extends JFrame implements FrameGetShape {
	BufferedImage image = new BufferedImage(570, 390,
			BufferedImage.TYPE_INT_BGR); // ����һ��8 λ BGR ��ɫ������ͼ��
	Graphics gs = image.getGraphics(); // ���ͼ��Ļ�ͼ����
	Graphics2D g = (Graphics2D) gs; // ����ͼ����ת��ΪGraphics2D����
	MyCanvas canvas = new MyCanvas(); // ������������
	Color foreColor = Color.BLACK; // ����ǰ��ɫ
	Color backgroundColor = Color.WHITE; // ���屳��ɫ
	boolean rubber = false; // ��Ƥ��ʶ����
	boolean drawShape = false; // ��ͼ�α�ʶ����
	Shapes shape;// �滭��ͼ��
	int x = -1; // ��һ�������Ƶ�ĺ�����
	int y = -1; // ��һ�������Ƶ��������
	private JToolBar toolBar;// ������
	private JButton eraserButton;// ��Ƥ��ť
	private JToggleButton strokeButton1;// ϸ�߰�ť
	private JToggleButton strokeButton2;// ���߰�ť
	private JToggleButton strokeButton3;// �ϴְ�ť
	private JButton backgroundButton;// ����ɫ��ť
	private JButton foregroundButton;// ǰ��ɫ��ť
	private JButton clearButton;// �����ť
	private JButton saveButton;// ���水ť
	private JButton shapeButton;// ͼ�ΰ�ť
	private JMenuItem strokeMenuItem1;// ϸ�߲˵�
	private JMenuItem strokeMenuItem2;// ���߲˵�
	private JMenuItem strokeMenuItem3;// �ϴֲ˵�
	private JMenuItem clearMenuItem;// ����˵�
	private JMenuItem foregroundMenuItem;// ǰ��ɫ�˵�
	private JMenuItem backgroundMenuItem;// ����ɫ�˵�
	private JMenuItem eraserMenuItem;// ��Ƥ�˵�
	private JMenuItem exitMenuItem;// �˳��˵�
	private JMenuItem saveMenuItem;// ����˵�

	/**
	 * ���췽��
	 */
	public MyFrame() {
		setResizable(false);// ���岻�ܸı��С
		setTitle("��ͼ����");// ���ñ���
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// ����ر���ֹͣ����
		setBounds(500, 100, 574, 460);// ���ô���λ�úͿ��

		init();// �����ʼ��
		addListener();// ����������
	}

	/**
	 * �����ʼ��
	 */
	private void init() {
		g.setColor(backgroundColor); // �ñ���ɫ���û�ͼ�������ɫ
		g.fillRect(0, 0, 583, 498); // �ñ���ɫ�����������
		g.setColor(foreColor); // ��ǰ��ɫ���û�ͼ�������ɫ
		canvas.setImage(image); // ���û�����ͼ��
		getContentPane().add(canvas); // ��������ӵ���������Ĭ�ϲ��ֵ��в�λ��
		toolBar = new JToolBar();// ��ʼ��������
		getContentPane().add(toolBar, BorderLayout.NORTH);// ��������ӵ������λ��


		saveButton = new JButton();// ��ʼ����ť���󣬲�����ı�����
		saveButton.setToolTipText("����");// ���ð�ť�����ͣ��ʾ
		saveButton.setIcon(new ImageIcon("src/img/icon/����.png"));// ���ð�ťͼ��
		toolBar.add(saveButton);// ��������Ӱ�ť
		toolBar.addSeparator();// ��ӷָ���

		strokeButton1 = new JToggleButton();// ��ʼ����ѡ��״̬�İ�ť���󣬲�����ı�����
		strokeButton1.setToolTipText("ϸ��");// ���ð�ť�����ͣ��ʾ
		strokeButton1.setIcon(new ImageIcon("src/img/icon/1��������.png"));// ���ð�ťͼ��
		strokeButton1.setSelected(true);// ϸ�߰�ť���ڱ�ѡ��״̬
		toolBar.add(strokeButton1);// ��������Ӱ�ť
		strokeButton2 = new JToggleButton();// ��ʼ����ѡ��״̬�İ�ť���󣬲�����ı�����
		strokeButton2.setToolTipText("����");// ���ð�ť�����ͣ��ʾ
		strokeButton2.setIcon(new ImageIcon("src/img/icon/2��������.png"));// ���ð�ťͼ��
		toolBar.add(strokeButton2);// ��������Ӱ�ť
		strokeButton3 = new JToggleButton();// ��ʼ����ѡ��״̬�İ�ť���󣬲�����ı�����
		strokeButton3.setToolTipText("�ϴ�");// ���ð�ť�����ͣ��ʾ
		strokeButton3.setIcon(new ImageIcon("src/img/icon/4��������.png"));// ���ð�ťͼ��
		ButtonGroup strokeGroup = new ButtonGroup();// ���ʴ�ϸ��ť�飬��֤ͬʱֻ��һ����ť��ѡ��
		strokeGroup.add(strokeButton1);// ��ť����Ӱ�ť
		strokeGroup.add(strokeButton2);// ��ť����Ӱ�ť
		strokeGroup.add(strokeButton3);// ��ť����Ӱ�ť
		toolBar.add(strokeButton3);// ��������Ӱ�ť
		toolBar.addSeparator();// ��ӷָ�
		backgroundButton = new JButton();// ��ʼ����ť���󣬲�����ı�����
		backgroundButton.setToolTipText("������ɫ");// ���ð�ť�����ͣ��ʾ
		backgroundButton.setIcon(new ImageIcon("src/img/icon/����ɫ.png"));// ���ð�ťͼ��
		toolBar.add(backgroundButton);// ��������Ӱ�ť
		foregroundButton = new JButton();// ��ʼ����ť���󣬲�����ı�����
		foregroundButton.setToolTipText("ǰ����ɫ");// ���ð�ť�����ͣ��ʾ
		foregroundButton.setIcon(new ImageIcon("src/img/icon/ǰ��ɫ.png"));// ���ð�ťͼ��
		toolBar.add(foregroundButton);// ��������Ӱ�ť
		toolBar.addSeparator();// ��ӷָ���

		shapeButton = new JButton();// ��ʼ����ť���󣬲�����ı�����
		shapeButton.setToolTipText("ͼ��");// ���ð�ť�����ͣ��ʾ
		shapeButton.setIcon(new ImageIcon("src/img/icon/��״.png"));// ���ð�ťͼ��
		toolBar.add(shapeButton);// ��������Ӱ�ť
		clearButton = new JButton();// ��ʼ����ť���󣬲�����ı�����
		clearButton.setToolTipText("���");// ���ð�ť�����ͣ��ʾ
		clearButton.setIcon(new ImageIcon("src/img/icon/���.png"));// ���ð�ťͼ��
		toolBar.add(clearButton);// ��������Ӱ�ť
		eraserButton = new JButton();// ��ʼ����ť���󣬲�����ı�����
		eraserButton.setToolTipText("��Ƥ");// ���ð�ť�����ͣ��ʾ
		eraserButton.setIcon(new ImageIcon("src/img/icon/��Ƥ.png"));// ���ð�ťͼ��
		toolBar.add(eraserButton);// ��������Ӱ�ť

		JMenuBar menuBar = new JMenuBar();// �����˵���
		setJMenuBar(menuBar);// ��������˵���

		JMenu systemMenu = new JMenu("ϵͳ");// ��ʼ���˵����󣬲�����ı�����
		menuBar.add(systemMenu);// �˵�����Ӳ˵�����
		saveMenuItem = new JMenuItem("����");// ��ʼ���˵�����󣬲�����ı�����
		systemMenu.add(saveMenuItem);// �˵���Ӳ˵���
		systemMenu.addSeparator();// ��ӷָ���
		exitMenuItem = new JMenuItem("�˳�");// ��ʼ���˵�����󣬲�����ı�����
		systemMenu.add(exitMenuItem);// �˵���Ӳ˵���

		JMenu strokeMenu = new JMenu("����");// ��ʼ���˵����󣬲�����ı�����
		menuBar.add(strokeMenu);// �˵�����Ӳ˵�����
		strokeMenuItem1 = new JMenuItem("ϸ��");// ��ʼ���˵�����󣬲�����ı�����
		strokeMenu.add(strokeMenuItem1);// �˵���Ӳ˵���
		strokeMenuItem2 = new JMenuItem("����");// ��ʼ���˵�����󣬲�����ı�����
		strokeMenu.add(strokeMenuItem2);// �˵���Ӳ˵���
		strokeMenuItem3 = new JMenuItem("�ϴ�");// ��ʼ���˵�����󣬲�����ı�����
		strokeMenu.add(strokeMenuItem3);// �˵���Ӳ˵���

		JMenu colorMenu = new JMenu("��ɫ");// ��ʼ���˵����󣬲�����ı�����
		menuBar.add(colorMenu);// �˵�����Ӳ˵�����
		foregroundMenuItem = new JMenuItem("ǰ����ɫ");// ��ʼ���˵�����󣬲�����ı�����
		colorMenu.add(foregroundMenuItem);// �˵���Ӳ˵���
		backgroundMenuItem = new JMenuItem("������ɫ");// ��ʼ���˵�����󣬲�����ı�����
		colorMenu.add(backgroundMenuItem);// �˵���Ӳ˵���

		JMenu editMenu = new JMenu("�༭");// ��ʼ���˵����󣬲�����ı�����
		menuBar.add(editMenu);// �˵�����Ӳ˵�����
		clearMenuItem = new JMenuItem("���");// ��ʼ���˵�����󣬲�����ı�����
		editMenu.add(clearMenuItem);// �˵���Ӳ˵���
		eraserMenuItem = new JMenuItem("��Ƥ");// ��ʼ���˵�����󣬲�����ı�����
		editMenu.add(eraserMenuItem);// �˵���Ӳ˵���


	}

	/**
	 * �������Ӷ�������
	 */
	private void addListener() {
		// �����������ƶ��¼�����
		canvas.addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseDragged(final MouseEvent e) {// �������קʱ
				if (x > 0 && y > 0) {// ���x��y��������¼
					if (rubber) {// ��Ƥ��ʶΪtrue����ʾʹ����Ƥ
						g.setColor(backgroundColor); // ��ͼ����ʹ�ñ���ɫ
						g.fillRect(x, y, 10, 10); // ����껮����λ�û�����������
					} else { // �����Ƥ��ʶΪfalse����ʾ�û��ʻ�ͼ
						g.drawLine(x, y, e.getX(), e.getY());// ����껮����λ�û�ֱ��
					}// else����
				}// if����
				x = e.getX(); // ��һ�������Ƶ�ĺ�����
				y = e.getY(); // ��һ�������Ƶ��������
				canvas.repaint(); // ���»���
			}

			public void mouseMoved(final MouseEvent arg0) {// ������ƶ�ʱ
				if (rubber) {// ���ʹ����Ƥ
					// �������ָ�����״ΪͼƬ
					Toolkit kit = Toolkit.getDefaultToolkit();// ���ϵͳĬ�ϵ�������߰�
					Image img = kit.createImage("src/img/icon/�����Ƥ.png");// ���ù��߰���ȡͼƬ
					// ���ù��߰�����һ���Զ���Ĺ�����,����ΪͼƬ������ȵ�(д��0,0����)�͹������
					Cursor c = kit.createCustomCursor(img, new Point(0, 0),
							"clear");
					setCursor(c);// ʹ���Զ���Ĺ��
				} else {
					// �������ָ�����״
					setCursor(Cursor
							.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));// �������ָ�����״Ϊʮ�ֹ��
				}
			}
		});
		canvas.addMouseListener(new MouseAdapter() {// ����������¼������¼�
			public void mouseReleased(final MouseEvent arg0) {// ������̧��ʱ
				x = -1; // ����¼��һ�������Ƶ�ĺ�����ָ���-1
				y = -1; // ����¼��һ�������Ƶ��������ָ���-1
			}

			public void mousePressed(MouseEvent e) {// ����������ʱ
				if (drawShape) {// �����ʱ��껭����ͼ��
					switch (shape.getType()) {// �ж�ͼ�ε�����
					case Shapes.YUAN:// �����Բ��
						// �������꣬����괦��ͼ�ε�����λ��
						int yuanX = e.getX() - shape.getWidth() / 2;
						int yuanY = e.getY() - shape.getHeigth() / 2;
						// ����Բ��ͼ�Σ���ָ������Ϳ��
						Ellipse2D yuan = new Ellipse2D.Double(yuanX, yuanY,
								shape.getWidth(), shape.getHeigth());
						g.draw(yuan);// ��ͼ���߻���Բ��
						break;// ����switch���
					case Shapes.FANG:// ����Ƿ���
						// �������꣬����괦��ͼ�ε�����λ��
						int fangX = e.getX() - shape.getWidth() / 2;
						int fangY = e.getY() - shape.getHeigth() / 2;
						// ��������ͼ�Σ���ָ������Ϳ��
						Rectangle2D fang = new Rectangle2D.Double(fangX, fangY,
								shape.getWidth(), shape.getHeigth());
						g.draw(fang);// ��ͼ���߻��˷���
						break;// ����switch���
					}
					canvas.repaint(); // ���»���
					drawShape = false;
				}
			}
		});
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
		eraserMenuItem.addActionListener(new ActionListener() {// ��Ƥ�˵�����Ӷ�������
					public void actionPerformed(final ActionEvent e) {// ���ʱ
						if (rubber) { // ����˵�����������Ϊ����Ƥ��
							eraserButton.setToolTipText("��Ƥ");// ���ð�ť�����ͣ��ʾ
							// ���ð�ťͼ��
							eraserButton.setIcon(new ImageIcon("src/img/icon/��Ƥ.png"));
							eraserMenuItem.setText("��Ƥ"); // �ı�˵�����ʾ���ı�Ϊ��Ƥ
							g.setColor(foreColor); // ���û�ͼ�����ǰ��ɫ
							rubber = false;// ��Ƥ��ʶ������Ϊfasle����ʾ��ǰʹ�û���
						} else { // �����������ϵĻ�ͼ��ť��ʹ�û���
							eraserMenuItem.setText("��ͼ"); // �ı�˵�����ʾ���ı�Ϊ��ͼ
							eraserButton.setToolTipText("��ͼ"); // ���ð�ť�����ͣ��ʾ
							// ���ð�ťͼ��
							eraserButton.setIcon(null);
							g.setColor(backgroundColor); // ���û�ͼ�����ǰ��ɫ
							rubber = true;// ��Ƥ��ʶ������Ϊtrue����ʾ��ǰʹ����Ƥ
						}
					}
				});

		strokeButton1.addActionListener(new ActionListener() {// ��ϸ�ߡ���ť��Ӷ�������
					public void actionPerformed(final ActionEvent arg0) {// ���ʱ
						// �������ʵ����ԣ���ϸΪ1���أ�����ĩ�������Σ����ߴ��ʼ��
						BasicStroke bs = new BasicStroke(1,
								BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER);
						g.setStroke(bs); // ��ͼ����ʹ�ô˻���
					}
				});

		strokeButton2.addActionListener(new ActionListener() {// �����ߡ���ť��Ӷ�������
					public void actionPerformed(final ActionEvent arg0) {
						// �������ʵ����ԣ���ϸΪ2���أ�����ĩ�������Σ����ߴ��ʼ��
						BasicStroke bs = new BasicStroke(2,
								BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER);
						g.setStroke(bs); // ��ͼ����ʹ�ô˻���
					}
				});

		strokeButton3.addActionListener(new ActionListener() {// ���ϴ֡���ť��Ӷ�������
					public void actionPerformed(final ActionEvent arg0) {
						// �������ʵ����ԣ���ϸΪ4���أ�����ĩ�������Σ����ߴ��ʼ��
						BasicStroke bs = new BasicStroke(4,
								BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER);
						g.setStroke(bs); // ��ͼ����ʹ�ô˻���
					}
				});

		backgroundButton.addActionListener(new ActionListener() {// ������ɫ��ť��Ӷ�������
					public void actionPerformed(final ActionEvent arg0) {// ���ʱ
						// ��ѡ����ɫ�Ի��򣬲�������Ϊ�������塢���⡢Ĭ��ѡ�е���ɫ����ɫ��
						Color bgColor = JColorChooser.showDialog(
								MyFrame.this, "ѡ����ɫ�Ի���", Color.CYAN);
						if (bgColor != null) {// ���ѡ�е���ɫ���ǿյ�
							backgroundColor = bgColor;// ��ѡ�е���ɫ��������ɫ����
						}
						backgroundButton.setBackground(backgroundColor);// ����ɫ��ť��Ҳ����Ϊ���ֱ�����ɫ
						g.setColor(backgroundColor); // ��ͼ����ʹ�ñ���ɫ
						g.fillRect(0, 0, 570, 390); // ��һ��������ɫ�ķ���������������
						g.setColor(foreColor); // ��ͼ����ʹ��ǰ��ɫ
						canvas.repaint(); // ���»���
					}
				});
		foregroundButton.addActionListener(new ActionListener() {// ǰ��ɫ��ɫ��ť��Ӷ�������
					public void actionPerformed(final ActionEvent arg0) {// ���ʱ
						// ��ѡ����ɫ�Ի���,��������Ϊ�������塢���⡢Ĭ��ѡ�е���ɫ����ɫ��
						Color fColor = JColorChooser.showDialog(
								MyFrame.this, "ѡ����ɫ�Ի���", Color.CYAN);
						if (fColor != null) {// ���ѡ�е���ɫ���ǿյ�
							foreColor = fColor;// ��ѡ�е���ɫ����ǰ��ɫ����
						}
						foregroundButton.setBackground(foreColor);// ǰ��ɫ��ť������Ҳ����Ϊ������ɫ
						g.setColor(foreColor); // ��ͼ����ʹ��ǰ��ɫ
					}
				});
		clearButton.addActionListener(new ActionListener() {// �����ť��Ӷ�������
					public void actionPerformed(final ActionEvent arg0) {// ���ʱ
						g.setColor(backgroundColor); // ��ͼ����ʹ�ñ���ɫ
						g.fillRect(0, 0, 570, 390); // ��һ��������ɫ�ķ���������������
						g.setColor(foreColor); // ��ͼ����ʹ��ǰ��ɫ
						canvas.repaint(); // ���»���
					}
				});

		eraserButton.addActionListener(new ActionListener() {// ��Ƥ��ť��Ӷ�������
					public void actionPerformed(final ActionEvent arg0) {// ���ʱ
						if (rubber) { // ����˵�����������Ϊ����Ƥ��
							eraserButton.setToolTipText("��Ƥ");// ���ð�ť�����ͣ��ʾ
							// ���ð�ťͼ��
							eraserButton.setIcon(new ImageIcon(
									"src/img/icon/��Ƥ.png"));
							eraserMenuItem.setText("��Ƥ"); // �ı�˵�����ʾ���ı�Ϊ��Ƥ
							g.setColor(foreColor); // ���û�ͼ�����ǰ��ɫ
							rubber = false;// ��Ƥ��ʶ������Ϊfasle����ʾ��ǰʹ�û���
						} else { // �����������ϵĻ�ͼ��ť��ʹ�û���
							eraserMenuItem.setText("��ͼ"); // �ı�˵�����ʾ���ı�Ϊ��ͼ
							eraserButton.setToolTipText("��ͼ"); // ���ð�ť�����ͣ��ʾ
							// ���ð�ťͼ��
							eraserButton.setIcon(new ImageIcon(
									"src/img/icon/����.png"));
							g.setColor(backgroundColor); // ���û�ͼ�����ǰ��ɫ
							rubber = true;// ��Ƥ��ʶ������Ϊtrue����ʾ��ǰʹ����Ƥ
						}
					}
				});

		clearMenuItem.addActionListener(new ActionListener() {// ����˵���Ӷ�������
					public void actionPerformed(final ActionEvent e) {// ���ʱ
						g.setColor(backgroundColor); // ��ͼ����ʹ�ñ���ɫ
						g.fillRect(0, 0, 570, 390); // ��һ��������ɫ�ķ���������������
						g.setColor(foreColor); // ��ͼ����ʹ��ǰ��ɫ
						canvas.repaint(); // ���»���
					}
				});

		strokeMenuItem1.addActionListener(new ActionListener() {// "ϸ��"�˵���Ӷ�������
					public void actionPerformed(final ActionEvent e) {// ���ʱ
						// �������ʵ����ԣ���ϸΪ1���أ�����ĩ�������Σ����ߴ��ʼ��
						BasicStroke bs = new BasicStroke(1,
								BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER);
						g.setStroke(bs);// ��ͼ����ʹ�ô˻���
						strokeButton1.setSelected(true);// "ϸ��"��ť��Ϊѡ��״̬
					}
				});
		strokeMenuItem2.addActionListener(new ActionListener() {// "����"�˵���Ӷ�������
					public void actionPerformed(final ActionEvent e) {// ���ʱ
						// �������ʵ����ԣ���ϸΪ2���أ�����ĩ�������Σ����ߴ��ʼ��
						BasicStroke bs = new BasicStroke(2,
								BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER);
						g.setStroke(bs); // ��ͼ����ʹ�ô˻���
						strokeButton2.setSelected(true);// "����"��ť��Ϊѡ��״̬
					}
				});
		strokeMenuItem3.addActionListener(new ActionListener() {// "�ϴ�"�˵���Ӷ�������
					public void actionPerformed(final ActionEvent e) {// ���ʱ
						// �������ʵ����ԣ���ϸΪ4���أ�����ĩ�������Σ����ߴ��ʼ��
						BasicStroke bs = new BasicStroke(4,
								BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER);
						g.setStroke(bs); // ��ͼ����ʹ�ô˻���
						strokeButton3.setSelected(true);// "�ϴ�"��ť��Ϊѡ��״̬
					}
				});
		foregroundMenuItem.addActionListener(new ActionListener() {// ǰ��ɫ�˵���Ӷ�������
					public void actionPerformed(final ActionEvent e) {// ���ʱ
						// ��ѡ����ɫ�Ի���,��������Ϊ�������塢���⡢Ĭ��ѡ�е���ɫ����ɫ��
						Color fColor = JColorChooser.showDialog(
								MyFrame.this, "ѡ����ɫ�Ի���", Color.CYAN);
						if (fColor != null) {// ���ѡ�е���ɫ���ǿյ�
							foreColor = fColor;// ��ѡ�е���ɫ����ǰ��ɫ����
						}
						foregroundButton.setForeground(foreColor);// ǰ��ɫ��ť������Ҳ����Ϊ������ɫ
						g.setColor(foreColor); // ��ͼ����ʹ��ǰ��ɫ
					}
				});
		backgroundMenuItem.addActionListener(new ActionListener() {// ����ɫ�˵���Ӷ�������
					public void actionPerformed(final ActionEvent e) {// ���ʱ
						// ��ѡ����ɫ�Ի��򣬲�������Ϊ�������塢���⡢Ĭ��ѡ�е���ɫ����ɫ��
						Color bgColor = JColorChooser.showDialog(
								MyFrame.this, "ѡ����ɫ�Ի���", Color.CYAN);
						if (bgColor != null) {// ���ѡ�е���ɫ���ǿյ�
							backgroundColor = bgColor;// ��ѡ�е���ɫ��������ɫ����
						}
						backgroundButton.setBackground(backgroundColor);// ����ɫ��ť��Ҳ����Ϊ���ֱ�����ɫ
						g.setColor(backgroundColor); // ��ͼ����ʹ�ñ���ɫ
						g.fillRect(0, 0, 570, 390); // ��һ��������ɫ�ķ���������������
						g.setColor(foreColor); // ��ͼ����ʹ��ǰ��ɫ
						canvas.repaint(); // ���»���
					}
				});
		saveButton.addActionListener(new ActionListener() {// ���水ť��Ӷ�������
					public void actionPerformed(final ActionEvent arg0) {// ���ʱ
						DrawImageUtil.saveImage(MyFrame.this, image);// ��ӡͼƬ
					}
				});
		saveMenuItem.addActionListener(new ActionListener() {// ����˵���Ӷ�������
					public void actionPerformed(ActionEvent e) {// ���ʱ
						DrawImageUtil.saveImage(MyFrame.this, image);// ��ӡͼƬ
					}
				});

		shapeButton.addActionListener(new ActionListener() {// ͼ�ΰ�ť��Ӷ�������
					public void actionPerformed(ActionEvent e) {// ���ʱ
						ShapeWindow shapeWindow = new ShapeWindow(
								MyFrame.this);// ����ͼ��ѡ�����
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
	}


	/**
	 * FrameGetShape�ӿ�ʵ���࣬���ڻ��ͼ�οռ䷵�صı�ѡ�е�ͼ��
	 */
	public void getShape(Shapes shape) {
		this.shape = shape;// �����ص�ͼ�ζ��󸶸����ȫ�ֱ���
		drawShape = true;// ��ͼ�α�ʶ����Ϊtrue��˵��ѡ����껭����ͼ�Σ���������
	}// getShape()����


	/**
	 * ����������������
	 * 
	 * @param args
	 *            �C ����ʱ�������������ò���
	 */
	public static void main(String[] args) {
		MyFrame frame = new MyFrame();// �����������
		frame.setVisible(true);// �ô���ɼ�
	}// main()����

}// MyFrame�����
