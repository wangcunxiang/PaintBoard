

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

import javax.swing.*;


public class MyFrame extends JFrame implements FrameGetShape {
	BufferedImage image = new BufferedImage(570, 390,
			BufferedImage.TYPE_INT_BGR); // 创建一个8 位 BGR 颜色分量的图像
	Graphics gs = image.getGraphics(); // 获得图像的绘图对象
	Graphics2D g = (Graphics2D) gs; // 将绘图对象转换为Graphics2D类型
	MyCanvas canvas = new MyCanvas(g); // 创建画布对象


	private JToolBar toolBar;// 工具栏
	private JButton eraserButton;// 橡皮按钮
    private JButton strokeButton;
    private JToggleButton strokeButton1;// 细线按钮
	private JToggleButton strokeButton2;// 粗线按钮
	private JToggleButton strokeButton3;// 较粗按钮
	private JButton backgroundButton;// 背景色按钮
	private JButton foregroundButton;// 前景色按钮
	private JButton clearButton;// 清除按钮
	private JButton saveButton;// 保存按钮
	private JButton shapeButton;// 图形按钮
	private JButton textButton;//文本按钮
    private JButton fillButton;//填充按钮
	private JMenuItem strokeMenuItem1;// 细线菜单
	private JMenuItem strokeMenuItem2;// 粗线菜单
	private JMenuItem strokeMenuItem3;// 较粗菜单
	private JMenuItem clearMenuItem;// 清除菜单
	private JMenuItem foregroundMenuItem;// 前景色菜单
	private JMenuItem backgroundMenuItem;// 背景色菜单
	private JMenuItem eraserMenuItem;// 橡皮菜单
	private JMenuItem exitMenuItem;// 退出菜单
	private JMenuItem saveMenuItem;// 保存菜单

    private JTextField jtf;

    /**
	 * 构造方法
	 */
	public MyFrame() {
		setResizable(false);// 窗体不能改变大小
		setTitle("画图程序");// 设置标题
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// 窗体关闭则停止程序
		setBounds(500, 200, 574, 460);// 设置窗口位置和宽高


		init();// 组件初始化
		addListener();// 添加组件监听
	}

	/**
	 * 组件初始化
	 */
	private void init() {
		g.setColor(canvas.backgroundColor); // 用背景色设置绘图对象的颜色
        canvas.width = 570;
        canvas.height = 360;
		g.fillRect(0, 0, canvas.width, canvas.height); // 用背景色填充整个画布
		g.setColor(canvas.foreColor); // 用前景色设置绘图对象的颜色
		canvas.setImage(image); // 设置画布的图像
		getContentPane().add(canvas); // 将画布添加到窗体容器默认布局的中部位置
		toolBar = new JToolBar();// 初始化工具栏
		getContentPane().add(toolBar, BorderLayout.NORTH);// 工具栏添加到窗体最北位置


		saveButton = new JButton();// 初始化按钮对象，并添加文本内容
		saveButton.setToolTipText("保存");// 设置按钮鼠标悬停提示
		saveButton.setIcon(new ImageIcon("src/img/icon/保存.png"));// 设置按钮图标
		toolBar.add(saveButton);// 工具栏添加按钮
		toolBar.addSeparator();// 添加分割条

		strokeButton1 = new JToggleButton();// 初始化有选中状态的按钮对象，并添加文本内容
		strokeButton1.setToolTipText("细线");// 设置按钮鼠标悬停提示
		strokeButton1.setIcon(new ImageIcon("src/img/icon/1像素线条.png"));// 设置按钮图标
		strokeButton1.setSelected(true);// 细线按钮处于被选中状态
		toolBar.add(strokeButton1);// 工具栏添加按钮
		strokeButton2 = new JToggleButton();// 初始化有选中状态的按钮对象，并添加文本内容
		strokeButton2.setToolTipText("粗线");// 设置按钮鼠标悬停提示
		strokeButton2.setIcon(new ImageIcon("src/img/icon/2像素线条.png"));// 设置按钮图标
		toolBar.add(strokeButton2);// 工具栏添加按钮
		strokeButton3 = new JToggleButton();// 初始化有选中状态的按钮对象，并添加文本内容
		strokeButton3.setToolTipText("较粗");// 设置按钮鼠标悬停提示
		strokeButton3.setIcon(new ImageIcon("src/img/icon/4像素线条.png"));// 设置按钮图标
		ButtonGroup strokeGroup = new ButtonGroup();// 画笔粗细按钮组，保证同时只有一个按钮被选中
		strokeGroup.add(strokeButton1);// 按钮组添加按钮
		strokeGroup.add(strokeButton2);// 按钮组添加按钮
		strokeGroup.add(strokeButton3);// 按钮组添加按钮
		toolBar.add(strokeButton3);// 工具栏添加按钮
		toolBar.addSeparator();// 添加分割
		backgroundButton = new JButton();// 初始化按钮对象，并添加文本内容
		backgroundButton.setToolTipText("背景颜色");// 设置按钮鼠标悬停提示
		backgroundButton.setIcon(new ImageIcon("src/img/icon/背景色.png"));// 设置按钮图标
		toolBar.add(backgroundButton);// 工具栏添加按钮
		foregroundButton = new JButton();// 初始化按钮对象，并添加文本内容
		foregroundButton.setToolTipText("前景颜色");// 设置按钮鼠标悬停提示
		foregroundButton.setIcon(new ImageIcon("src/img/icon/前景色.png"));// 设置按钮图标
		toolBar.add(foregroundButton);// 工具栏添加按钮
		toolBar.addSeparator();// 添加分割条

		shapeButton = new JButton();// 初始化按钮对象，并添加文本内容
		shapeButton.setToolTipText("图形");// 设置按钮鼠标悬停提示
		shapeButton.setIcon(new ImageIcon("src/img/icon/形状.png"));// 设置按钮图标
		toolBar.add(shapeButton);// 工具栏添加按钮
		clearButton = new JButton();// 初始化按钮对象，并添加文本内容
		clearButton.setToolTipText("清除");// 设置按钮鼠标悬停提示
		clearButton.setIcon(new ImageIcon("src/img/icon/清除.png"));// 设置按钮图标
		toolBar.add(clearButton);// 工具栏添加按钮
		eraserButton = new JButton();// 初始化按钮对象，并添加文本内容
		eraserButton.setToolTipText("橡皮");// 设置按钮鼠标悬停提示
		eraserButton.setIcon(new ImageIcon("src/img/icon/橡皮.png"));// 设置按钮图标
		toolBar.add(eraserButton);// 工具栏添加按钮
        toolBar.addSeparator();// 添加分割条

        textButton = new JButton();// 初始化按钮对象，并添加文本内容
        textButton.setToolTipText("文本");// 设置按钮鼠标悬停提示
        textButton.setIcon(new ImageIcon("src/img/icon/橡皮.png"));// 设置按钮图标
        toolBar.add(textButton);// 工具栏添加按钮
        fillButton = new JButton();// 初始化按钮对象，并添加文本内容
        fillButton.setToolTipText("填充");// 设置按钮鼠标悬停提示
        fillButton.setIcon(new ImageIcon("src/img/icon/橡皮.png"));// 设置按钮图标
        toolBar.add(fillButton);// 工具栏添加按钮

		JMenuBar menuBar = new JMenuBar();// 创建菜单栏
		setJMenuBar(menuBar);// 窗体载入菜单栏

		JMenu systemMenu = new JMenu("系统");// 初始化菜单对象，并添加文本内容
		menuBar.add(systemMenu);// 菜单栏添加菜单对象
		saveMenuItem = new JMenuItem("保存");// 初始化菜单项对象，并添加文本内容
		systemMenu.add(saveMenuItem);// 菜单添加菜单项
		systemMenu.addSeparator();// 添加分割条
		exitMenuItem = new JMenuItem("退出");// 初始化菜单项对象，并添加文本内容
		systemMenu.add(exitMenuItem);// 菜单添加菜单项

		JMenu strokeMenu = new JMenu("线型");// 初始化菜单对象，并添加文本内容
		menuBar.add(strokeMenu);// 菜单栏添加菜单对象
		strokeMenuItem1 = new JMenuItem("细线");// 初始化菜单项对象，并添加文本内容
		strokeMenu.add(strokeMenuItem1);// 菜单添加菜单项
		strokeMenuItem2 = new JMenuItem("粗线");// 初始化菜单项对象，并添加文本内容
		strokeMenu.add(strokeMenuItem2);// 菜单添加菜单项
		strokeMenuItem3 = new JMenuItem("较粗");// 初始化菜单项对象，并添加文本内容
		strokeMenu.add(strokeMenuItem3);// 菜单添加菜单项

		JMenu colorMenu = new JMenu("颜色");// 初始化菜单对象，并添加文本内容
		menuBar.add(colorMenu);// 菜单栏添加菜单对象
		foregroundMenuItem = new JMenuItem("前景颜色");// 初始化菜单项对象，并添加文本内容
		colorMenu.add(foregroundMenuItem);// 菜单添加菜单项
		backgroundMenuItem = new JMenuItem("背景颜色");// 初始化菜单项对象，并添加文本内容
		colorMenu.add(backgroundMenuItem);// 菜单添加菜单项

		JMenu editMenu = new JMenu("编辑");// 初始化菜单对象，并添加文本内容
		menuBar.add(editMenu);// 菜单栏添加菜单对象
		clearMenuItem = new JMenuItem("清除");// 初始化菜单项对象，并添加文本内容
		editMenu.add(clearMenuItem);// 菜单添加菜单项
		eraserMenuItem = new JMenuItem("橡皮");// 初始化菜单项对象，并添加文本内容
		editMenu.add(eraserMenuItem);// 菜单添加菜单项

        jtf = new JTextField("dsiiutttv",20);
        jtf.setLocation(new Point(400,200));
        //this.setLocation(new Point(00,20));

        strokeButton = new JButton();// 初始化按钮对象，并添加文本内容
        strokeButton.setToolTipText("STROKE");// 设置按钮鼠标悬停提示
        strokeButton.setIcon(new ImageIcon("src/img/icon/橡皮.png"));// 设置按钮图标
        toolBar.add(strokeButton);// 工具栏添加按钮
        toolBar.addSeparator();// 添加分割条


	}

	/**
	 * 无组件添加动作监听
	 */

    private void addListener(){

        canvas.addListener();

        toolBar.addMouseMotionListener(new MouseMotionAdapter() {// 工具栏添加鼠标移动监听
            public void mouseMoved(final MouseEvent arg0) {// 当鼠标移动时
                setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));// 设置鼠标指针的形状为默认光标
            }
        });
        exitMenuItem.addActionListener(new ActionListener() {// 退出菜单栏添加动作监听
            public void actionPerformed(final ActionEvent e) {// 点击时
                System.exit(0);// 程序关闭
            }
        });
        eraserMenuItem.addActionListener(new ActionListener() {// 橡皮菜单栏添加动作监听
            public void actionPerformed(final ActionEvent e) {// 点击时
                if (canvas.rubber) { // 如果菜单的文字内容为“橡皮”
                    eraserButton.setToolTipText("橡皮");// 设置按钮鼠标悬停提示
                    // 设置按钮图标
                    eraserButton.setIcon(new ImageIcon("src/img/icon/橡皮.png"));
                    eraserMenuItem.setText("橡皮"); // 改变菜单上显示的文本为橡皮
                    g.setColor(canvas.foreColor); // 设置绘图对象的前景色
                    canvas.rubber = false;// 橡皮标识变量设为fasle，表示当前使用画笔
                } else { // 单击工具栏上的画图按钮，使用画笔
                    eraserMenuItem.setText("画图"); // 改变菜单上显示的文本为画图
                    eraserButton.setToolTipText("画图"); // 设置按钮鼠标悬停提示
                    // 设置按钮图标
                    eraserButton.setIcon(null);
                    g.setColor(canvas.backgroundColor); // 设置绘图对象的前景色
                    canvas.rubber = true;// 橡皮标识变量设为true，表示当前使用橡皮
                }
            }
        });

        strokeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StrokeWindow strokeWindow = new StrokeWindow(
                        MyFrame.this);// 创建画笔选择组件
                int strokeButtonWidth = strokeButton1.getWidth();// 获取画笔按钮宽度
                int strokeWindowWidth = strokeWindow.getWidth();// 获取画笔按钮高度
                int strokeButtonX = strokeButton1.getX();// 获取画笔按钮横坐标
                int strokeButtonY = strokeButton1.getY();// 获取画笔按钮纵坐标
                // 计算画笔组件横坐标，为画笔按钮下方与按钮居中对齐
                int strokeWindowX = getX() + strokeButtonX
                        - (strokeWindowWidth - strokeButtonWidth) / 2;
                // 计算画笔组件纵坐标，为画笔按钮下方
                int strokeWindowY = getY() + strokeButtonY + 80;
                // 设置画笔组件坐标位置
                strokeWindow.setLocation(strokeWindowX, strokeWindowY);
                strokeWindow.setVisible(true);// 画笔组件可见
            }
        });


        strokeButton1.addActionListener(new ActionListener() {// “细线”按钮添加动作监听
            public void actionPerformed(final ActionEvent arg0) {// 点击时
                // 声明画笔的属性，粗细为1像素，线条末端无修饰，折线处呈尖角
                BasicStroke bs = new BasicStroke(1,
                        BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER);
                g.setStroke(bs); // 画图工具使用此画笔
            }
        });

        strokeButton2.addActionListener(new ActionListener() {// “粗线”按钮添加动作监听
            public void actionPerformed(final ActionEvent arg0) {
                // 声明画笔的属性，粗细为2像素，线条末端无修饰，折线处呈尖角
                BasicStroke bs = new BasicStroke(2,
                        BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER);
                g.setStroke(bs); // 画图工具使用此画笔
            }
        });

        strokeButton3.addActionListener(new ActionListener() {// “较粗”按钮添加动作监听
            public void actionPerformed(final ActionEvent arg0) {
                // 声明画笔的属性，粗细为4像素，线条末端无修饰，折线处呈尖角
                BasicStroke bs = new BasicStroke(4,
                        BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER);
                g.setStroke(bs); // 画图工具使用此画笔
            }
        });


        backgroundButton.addActionListener(new ActionListener() {// 背景颜色按钮添加动作监听
            public void actionPerformed(final ActionEvent arg0) {// 点击时
                // 打开选择颜色对话框，参数依次为：父窗体、标题、默认选中的颜色（青色）
                Color bgColor = JColorChooser.showDialog(
                        MyFrame.this, "选择颜色对话框", Color.CYAN);
                if (bgColor != null) {// 如果选中的颜色不是空的
                    canvas.backgroundColor = bgColor;// 将选中的颜色赋给背景色变量
                }
                backgroundButton.setBackground(canvas.backgroundColor);// 背景色按钮的也更换为这种背景颜色
                g.setColor(canvas.backgroundColor); // 绘图工具使用背景色
                g.fillRect(0, 0, 570, 390); // 画一个背景颜色的方形填满整个画布
                g.setColor(canvas.foreColor); // 绘图工具使用前景色
                canvas.repaint(); // 更新画布
            }
        });
        foregroundButton.addActionListener(new ActionListener() {// 前景色颜色按钮添加动作监听
            public void actionPerformed(final ActionEvent arg0) {// 点击时
                // 打开选择颜色对话框,参数依次为：父窗体、标题、默认选中的颜色（青色）
                Color fColor = JColorChooser.showDialog(
                        MyFrame.this, "选择颜色对话框", Color.CYAN);
                if (fColor != null) {// 如果选中的颜色不是空的
                    canvas.foreColor = fColor;// 将选中的颜色赋给前景色变量
                }
                foregroundButton.setBackground(canvas.foreColor);// 前景色按钮的文字也更换为这种颜色
                g.setColor(canvas.foreColor); // 绘图工具使用前景色
            }
        });
        clearButton.addActionListener(new ActionListener() {// 清除按钮添加动作监听
            public void actionPerformed(final ActionEvent arg0) {// 点击时
                g.setColor(canvas.backgroundColor); // 绘图工具使用背景色
                g.fillRect(0, 0, 570, 390); // 画一个背景颜色的方形填满整个画布
                g.setColor(canvas.foreColor); // 绘图工具使用前景色
                canvas.repaint(); // 更新画布
            }
        });

        eraserButton.addActionListener(new ActionListener() {// 橡皮按钮添加动作监听
            public void actionPerformed(final ActionEvent arg0) {// 点击时
                if (canvas.rubber) { // 如果菜单的文字内容为“橡皮”
                    eraserButton.setToolTipText("橡皮");// 设置按钮鼠标悬停提示
                    // 设置按钮图标
                    eraserButton.setIcon(new ImageIcon(
                            "src/img/icon/橡皮.png"));
                    eraserMenuItem.setText("橡皮"); // 改变菜单上显示的文本为橡皮
                    g.setColor(canvas.foreColor); // 设置绘图对象的前景色
                    canvas.rubber = false;// 橡皮标识变量设为fasle，表示当前使用画笔
                } else { // 单击工具栏上的画图按钮，使用画笔
                    eraserMenuItem.setText("画图"); // 改变菜单上显示的文本为画图
                    eraserButton.setToolTipText("画图"); // 设置按钮鼠标悬停提示
                    // 设置按钮图标
                    eraserButton.setIcon(new ImageIcon(
                            "src/img/icon/画笔.png"));
                    g.setColor(canvas.backgroundColor); // 设置绘图对象的前景色
                    canvas.rubber = true;// 橡皮标识变量设为true，表示当前使用橡皮
                }
                canvas.text = false;
                canvas.drawShape = false;
                canvas.fill = false;
            }
        });

        textButton.addActionListener(new ActionListener() {// 文本按钮添加动作监听
            public void actionPerformed(final ActionEvent arg0) {// 点击时
                canvas.text = true;
                canvas.rubber = false;
                canvas.drawShape = false;
                canvas.fill = false;
                canvas.intext = jtf.getText();
                //System.out.println(canvas.intext);
            }
        });

        fillButton.addActionListener(new ActionListener() {// 填充按钮添加动作监听
            public void actionPerformed(final ActionEvent arg0) {// 点击时
                canvas.fill = true;
                canvas.text = false;
                canvas.rubber = false;
                canvas.drawShape = false;
            }
        });

        clearMenuItem.addActionListener(new ActionListener() {// 清除菜单添加动作监听
            public void actionPerformed(final ActionEvent e) {// 点击时
                g.setColor(canvas.backgroundColor); // 绘图工具使用背景色
                g.fillRect(0, 0, 570, 390); // 画一个背景颜色的方形填满整个画布
                g.setColor(canvas.foreColor); // 绘图工具使用前景色
                canvas.repaint(); // 更新画布
            }
        });

        strokeMenuItem1.addActionListener(new ActionListener() {// "细线"菜单添加动作监听
            public void actionPerformed(final ActionEvent e) {// 点击时
                // 声明画笔的属性，粗细为1像素，线条末端无修饰，折线处呈尖角
                BasicStroke bs = new BasicStroke(1,
                        BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER);
                g.setStroke(bs);// 画图工具使用此画笔
                strokeButton1.setSelected(true);// "细线"按钮设为选中状态
            }
        });
        strokeMenuItem2.addActionListener(new ActionListener() {// "粗线"菜单添加动作监听
            public void actionPerformed(final ActionEvent e) {// 点击时
                // 声明画笔的属性，粗细为2像素，线条末端无修饰，折线处呈尖角
                BasicStroke bs = new BasicStroke(2,
                        BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER);
                g.setStroke(bs); // 画图工具使用此画笔
                strokeButton2.setSelected(true);// "粗线"按钮设为选中状态
            }
        });
        strokeMenuItem3.addActionListener(new ActionListener() {// "较粗"菜单添加动作监听
            public void actionPerformed(final ActionEvent e) {// 点击时
                // 声明画笔的属性，粗细为4像素，线条末端无修饰，折线处呈尖角
                BasicStroke bs = new BasicStroke(4,
                        BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER);
                g.setStroke(bs); // 画图工具使用此画笔
                strokeButton3.setSelected(true);// "较粗"按钮设为选中状态
            }
        });
        foregroundMenuItem.addActionListener(new ActionListener() {// 前景色菜单添加动作监听
            public void actionPerformed(final ActionEvent e) {// 点击时
                // 打开选择颜色对话框,参数依次为：父窗体、标题、默认选中的颜色（青色）
                Color fColor = JColorChooser.showDialog(
                        MyFrame.this, "选择颜色对话框", Color.CYAN);
                if (fColor != null) {// 如果选中的颜色不是空的
                    canvas.foreColor = fColor;// 将选中的颜色赋给前景色变量
                }
                foregroundButton.setForeground(canvas.foreColor);// 前景色按钮的文字也更换为这种颜色
                g.setColor(canvas.foreColor); // 绘图工具使用前景色
            }
        });
        backgroundMenuItem.addActionListener(new ActionListener() {// 背景色菜单添加动作监听
            public void actionPerformed(final ActionEvent e) {// 点击时
                // 打开选择颜色对话框，参数依次为：父窗体、标题、默认选中的颜色（青色）
                Color bgColor = JColorChooser.showDialog(
                        MyFrame.this, "选择颜色对话框", Color.CYAN);
                if (bgColor != null) {// 如果选中的颜色不是空的
                    canvas.backgroundColor = bgColor;// 将选中的颜色赋给背景色变量
                }
                backgroundButton.setBackground(canvas.backgroundColor);// 背景色按钮的也更换为这种背景颜色
                g.setColor(canvas.backgroundColor); // 绘图工具使用背景色
                g.fillRect(0, 0, 570, 390); // 画一个背景颜色的方形填满整个画布
                g.setColor(canvas.foreColor); // 绘图工具使用前景色
                canvas.repaint(); // 更新画布
            }
        });
        saveButton.addActionListener(new ActionListener() {// 保存按钮添加动作监听
            public void actionPerformed(final ActionEvent arg0) {// 点击时
                DrawImageUtil.saveImage(MyFrame.this, image);// 打印图片
            }
        });
        saveMenuItem.addActionListener(new ActionListener() {// 保存菜单添加动作监听
            public void actionPerformed(ActionEvent e) {// 点击时
                DrawImageUtil.saveImage(MyFrame.this, image);// 打印图片
            }
        });

        shapeButton.addActionListener(new ActionListener() {// 图形按钮添加动作监听
            public void actionPerformed(ActionEvent e) {// 点击时
                ShapeWindow shapeWindow = new ShapeWindow(
                        MyFrame.this);// 创建图形选择组件
                int shapeButtonWidth = shapeButton.getWidth();// 获取图像按钮宽度
                int shapeWindowWidth = shapeWindow.getWidth();// 获取图形按钮高度
                int shapeButtonX = shapeButton.getX();// 获取图形按钮横坐标
                int shapeButtonY = shapeButton.getY();// 获取图形按钮纵坐标
                // 计算图形组件横坐标，为图形按钮下方与按钮居中对齐
                int shapeWindowX = getX() + shapeButtonX
                        - (shapeWindowWidth - shapeButtonWidth) / 2;
                // 计算图形组件纵坐标，为图形按钮下方
                int shapeWindowY = getY() + shapeButtonY + 80;
                // 设置图形组件坐标位置
                shapeWindow.setLocation(shapeWindowX, shapeWindowY);
                shapeWindow.setVisible(true);// 图形组件可见
            }
        });
    }

    /**
	 * FrameGetShape接口实现类，用于获得图形空间返回的被选中的图形
	 */
	public void getShape(Shapes shape) {
        canvas.shape = shape;// 将返回的图形对象付给类的全局变量
        canvas.drawShape = true;// 画图形标识变量为true，说明选择鼠标画的是图形，而不是线
	}// getShape()结束


	/**
	 * 程序运行主方法。
	 * 
	 * @param args
	 *            – 运行时参数，本程序用不到
	 */
	public static void main(String[] args) {
		MyFrame frame = new MyFrame();// 创建窗体对象
		frame.setVisible(true);// 让窗体可见
	}// main()结束

}// MyFrame类结束
