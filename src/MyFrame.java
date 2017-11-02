

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
            BufferedImage.TYPE_INT_BGR); // 创建一个8 位 BGR 颜色分量的图像
    BufferedImage imageclone = new BufferedImage(1570, 1390,
            BufferedImage.TYPE_INT_BGR);
    Graphics gs = image.getGraphics(); // 获得图像的绘图对象
    Graphics2D g = (Graphics2D) gs; // 将绘图对象转换为Graphics2D类型
    Graphics gsclone = imageclone.getGraphics();
    Graphics2D gclone = (Graphics2D) gsclone;
    MyCanvas canvas = new MyCanvas(g,gclone);


	private JToolBar toolBar;// 工具栏
	private JButton eraserButton;// 橡皮按钮
    private JButton penButton;// 画笔按钮
    private JButton strokeButton;// 线型按钮
	private JButton backgroundButton;// 背景色按钮
	private JButton foregroundButton;// 前景色按钮
	private JButton clearButton;// 清除按钮
	private JButton shapeButton;// 图形按钮
	private JButton textButton;//文本按钮
    private JButton fillButton;//填充按钮
    private JButton undoButton;//撤销按钮
    private JButton help;//帮助按钮
	private JMenuItem clearMenuItem;// 清除菜单
	private JMenuItem usehelp;// 前景色菜单
	private JMenuItem bughelp;// 背景色菜单
	private JMenuItem eraserMenuItem;// 橡皮菜单
	private JMenuItem exitMenuItem;// 退出菜单
    private JMenuItem newMenuItem;// 打开菜单
	private JMenuItem saveMenuItem;// 保存菜单
    private JMenuItem openMenuItem;// 打开菜单
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
    private JToolBar toolBar2;// 工具栏

    JFileChooser jfcSave;
    JFileChooser jfcOpen;
    private JTextField jtf;

    int number = 0;// 总共有几次撤销机会
    int strokeWidth = 1;// 画笔宽度

    /**
	 * 构造方法
	 */
	public MyFrame() {//构造函数
		setResizable(false);// 窗体不能改变大小
		setTitle("画图程序");// 设置标题
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// 窗体关闭则停止程序
		setBounds(100, 50, 1074, 710);// 设置窗口位置和宽高

		init();// 组件初始化
		addListener();// 添加组件监听
	}

	/**
	 * 组件初始化
	 */
	private void init() //画图板主界面的初始化

    {
		g.setColor(canvas.backgroundColor); // 用背景色设置绘图对象的颜色
        gclone.setColor(canvas.backgroundColor);
        canvas.width = 1070;//画布宽度
        canvas.height = 690;//画布长度
		g.fillRect(0, 0, canvas.width, canvas.height); // 用背景色填充整个画布
        gclone.fillRect(0, 0, canvas.width, canvas.height);
		g.setColor(canvas.foreColor); // 用前景色设置绘图对象的颜色
        gclone.setColor(canvas.foreColor);
		canvas.setImage(image, imageclone); // 设置画布的图像
		getContentPane().add(canvas); // 将画布添加到窗体容器默认布局的中部位置

		toolBar = new JToolBar();// 初始化工具栏
		getContentPane().add(toolBar, BorderLayout.NORTH);// 工具栏添加到窗体最北位置


        backgroundButton = new JButton();// 初始化按钮对象，并添加文本内容
        backgroundButton.setToolTipText("背景颜色");// 设置按钮鼠标悬停提示
        backgroundButton.setIcon(new ImageIcon("src/img/icon/背景颜色1.jpg"));// 设置按钮图标
        toolBar.add(backgroundButton);// 工具栏添加按钮
        foregroundButton = new JButton();// 初始化按钮对象，并添加文本内容
        foregroundButton.setToolTipText("前景颜色");// 设置按钮鼠标悬停提示
        foregroundButton.setIcon(new ImageIcon("src/img/icon/编辑颜色.png"));// 设置按钮图标
        toolBar.add(foregroundButton);// 工具栏添加按钮
        toolBar.addSeparator();// 添加分割条

        shapeButton = new JButton();// 初始化按钮对象，并添加文本内容
        shapeButton.setToolTipText("图形");// 设置按钮鼠标悬停提示
        shapeButton.setIcon(new ImageIcon("src/img/icon/图形选择.jpg"));// 设置按钮图标
        toolBar.add(shapeButton);// 工具栏添加按钮
        eraserButton = new JButton();// 初始化按钮对象，并添加文本内容
        eraserButton.setToolTipText("橡皮");// 设置按钮鼠标悬停提示
        eraserButton.setIcon(new ImageIcon("src/img/icon/橡皮.jpg"));// 设置按钮图标
        toolBar.add(eraserButton);// 工具栏添加按钮
        penButton = new JButton();// 初始化按钮对象，并添加文本内容
        penButton.setToolTipText("画笔");// 设置按钮鼠标悬停提示
        penButton.setIcon(new ImageIcon("src/img/icon/画笔.png"));// 设置按钮图标
        toolBar.add( penButton);// 工具栏添加按钮

        for(int i=0;i<30;i++)
        toolBar.addSeparator();// 添加分割条

        jtf = new JTextField("请于此处输入文本",10);
        toolBar.add(jtf);
        textButton = new JButton();// 初始化按钮对象，并添加文本内容
        textButton.setToolTipText("文本");// 设置按钮鼠标悬停提示


        textButton.setIcon(new ImageIcon("src/img/icon/文本按钮.jpg"));// 设置按钮图标
        toolBar.add(textButton);// 工具栏添加按钮
        fillButton = new JButton();// 初始化按钮对象，并添加文本内容
        fillButton.setToolTipText("填充");// 设置按钮鼠标悬停提示
        fillButton.setIcon(new ImageIcon("src/img/icon/填充.png"));// 设置按钮图标
        toolBar.add(fillButton);// 工具栏添加按钮

        JMenuBar menuBar = new JMenuBar();// 创建菜单栏
        setJMenuBar(menuBar);// 窗体载入菜单栏

        JMenu systemMenu = new JMenu("系统");// 初始化菜单对象，并添加文本内容
        menuBar.add(systemMenu);// 菜单栏添加菜单对象
        newMenuItem = new JMenuItem("新建图片");// 初始化菜单项对象，并添加文本内容
        systemMenu.add(newMenuItem);// 菜单添加菜单项
        systemMenu.addSeparator();// 添加分割条
        saveMenuItem = new JMenuItem("保存图片");// 初始化菜单项对象，并添加文本内容
        systemMenu.add(saveMenuItem);// 菜单添加菜单项
        systemMenu.addSeparator();// 添加分割条
        openMenuItem = new JMenuItem("打开图片");// 初始化菜单项对象，并添加文本内容
        systemMenu.add(openMenuItem);// 菜单添加菜单项
        systemMenu.addSeparator();// 添加分割条
        exitMenuItem = new JMenuItem("退出");// 初始化菜单项对象，并添加文本内容
        systemMenu.add(exitMenuItem);// 菜单添加菜单项
        wordMenu=new JMenu("文字设置");
        setwordsize = new JMenu("设置文字大小");
        //setwordcolor = new JMenuItem("设置文字颜色");
        wordsize1=new JMenuItem("20");// 初始化菜单项对象，并添加文本内容
        wordsize2=new JMenuItem("30");// 初始化菜单项对象，并添加文本内容
        wordsize3=new JMenuItem("40");// 初始化菜单项对象，并添加文本内容
        setwordsize.add(wordsize1);// 菜单添加菜单项
        setwordsize.add(wordsize2);// 菜单添加菜单项
        setwordsize.add(wordsize3);// 菜单添加菜单项
        setziti=new  JMenu("设置文字字体风格");

        //wordMenu.add(setwordcolor);
        wordMenu.add(setziti);
        ziti1 = new JMenuItem("楷体");//设置字体选项
        ziti2 = new JMenuItem("宋体");//设置字体选项
        ziti3 = new JMenuItem("黑体");//设置字体选项
        setziti.add(ziti1);// 菜单栏添加菜单对象
        setziti.add(ziti2);// 菜单栏添加菜单对象
        setziti.add(ziti3);// 菜单栏添加菜单对象
        wordMenu.add(setwordsize);// 菜单栏添加菜单对象
        menuBar.add(wordMenu);// 菜单栏添加菜单对象


        JMenu help = new JMenu("帮助说明");// 初始化菜单对象，并添加文本内容
        menuBar.add(help);// 菜单栏添加菜单对象
        usehelp = new JMenuItem("使用说明");// 初始化菜单项对象，并添加文本内容
        help.add(usehelp);// 菜单添加菜单项
        bughelp = new JMenuItem("bug说明");// 初始化菜单项对象，并添加文本内容
        help.add(bughelp);// 菜单添加菜单项



        strokeButton = new JButton();// 初始化按钮对象，并添加文本内容
        strokeButton.setToolTipText("线型粗细");// 设置按钮鼠标悬停提示
        strokeButton.setIcon(new ImageIcon("src/img/icon/粗细.png"));// 设置按钮图标
        toolBar.add(strokeButton);// 工具栏添加按钮
        toolBar.addSeparator();// 添加分割条

        undoButton = new JButton();// 初始化按钮对象，并添加文本内容
        undoButton.setToolTipText("撤销");// 设置按钮鼠标悬停提示
        undoButton.setIcon(new ImageIcon("src/img/icon/撤销.jpg"));// 设置按钮图标
        toolBar.add(undoButton);// 工具栏添加按钮
        clearButton = new JButton();// 初始化按钮对象，并添加文本内容
        clearButton.setToolTipText("清空");// 设置按钮鼠标悬停提示
        clearButton.setIcon(new ImageIcon("src/img/icon/清空1.jpg"));// 设置按钮图标
        toolBar.add(clearButton);// 工具栏添加按钮

        autosave();
        initFileChooser();

    }
    private void initFileChooser() {//设定文件打开、保存的参数
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
	 * 无组件添加动作监听
	 */

    private void addListener()//按钮监听器的实现
    {

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

        strokeButton.addActionListener(new ActionListener() {//线条按钮的设置和监听
            @Override
            public void actionPerformed(ActionEvent e) {
                StrokeWindow strokeWindow = new StrokeWindow(
                        MyFrame.this);// 创建画笔选择组件
                int strokeButtonWidth = strokeButton.getWidth();// 获取画笔按钮宽度
                int strokeWindowWidth = strokeWindow.getWidth();// 获取画笔按钮高度
                int strokeButtonX = strokeButton.getX();// 获取画笔按钮横坐标
                int strokeButtonY = strokeButton.getY();// 获取画笔按钮纵坐标
                // 计算画笔组件横坐标，为画笔按钮下方与按钮居中对齐
                int strokeWindowX = getX() + strokeButtonX
                        - (strokeWindowWidth - strokeButtonWidth) / 2;
                // 计算画笔组件纵坐标，为画笔按钮下方
                int strokeWindowY = getY() + strokeButtonY + 80;
                // 设置画笔组件坐标位置
                strokeWindow.setLocation(strokeWindowX, strokeWindowY);
                strokeWindow.setVisible(true);// 画笔组件可见
                canvas.text = false;
                canvas.rubber = false;
                canvas.pen = true;
                canvas.drawShape = false;
                canvas.fill = false;
                //System.out.println(strokeWidth);
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
                g.fillRect(0, 0, canvas.width, canvas.height); // 画一个背景颜色的方形填满整个画布
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
                g.fillRect(0, 0, canvas.width, canvas.height); // 画一个背景颜色的方形填满整个画布
                g.setColor(canvas.foreColor); // 绘图工具使用前景色
                gclone.setColor(canvas.backgroundColor); // 绘图工具使用背景色
                gclone.fillRect(0, 0, canvas.width, canvas.height); // 画一个背景颜色的方形填满整个画布
                gclone.setColor(canvas.foreColor); // 绘图工具使用前景色
                canvas.array.clear();//图形队列清空
                canvas.drawAll(g);
                canvas.repaint(); // 更新画布
            }
        });
        undoButton.addActionListener(new ActionListener() {// 撤销按钮添加动作监听
            public void actionPerformed(final ActionEvent arg0) {// 点击时
                if(number>0){//如果可以撤销
                    number--;
                    String name = "image"+number;//设定要打开的文件的名字
                    DrawImageUtil.openImage2(MyFrame.this, canvas, name);//调用函数，打开图形文件
                    canvas.repaint(); // 更新画布
                }

            }
        });
        eraserButton.addActionListener(new ActionListener() {// 橡皮按钮添加动作监听
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
        penButton.addActionListener(new ActionListener() {// 画笔按钮添加动作监听
            public void actionPerformed(final ActionEvent arg0) {
                canvas.text = false;
                canvas.rubber = false;
                canvas.pen = true;
                canvas.drawShape = false;
                canvas.fill = false;
                //g.setColor(Color.BLACK); // 绘图工具使用背景色
                //canvas.rectangle = false;
                //canvas.circle = false;
                //canvas.line = false;
                //canvas.fillShape = false;
            }
        });

        textButton.addActionListener(new ActionListener() {// 文本按钮添加动作监听
            public void actionPerformed(final ActionEvent arg0) {// 点击时
                canvas.text = true;
                canvas.rubber = false;
                canvas.pen = false;
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
                canvas.pen = false;;
                canvas.drawShape = false;
            }
        });


        newMenuItem.addActionListener(new ActionListener() {// 清除菜单添加动作监听
            public void actionPerformed(final ActionEvent e) {// 点击时
                canvas.foreColor=Color.black;
                canvas.backgroundColor=Color.white;
                canvas.text = false;
                canvas.rubber = false;
                canvas.pen = true;
                canvas.drawShape = false;
                canvas.fill = false;//重新选择使用画笔
                canvas.array.clear();
                strokeWidth = 1;
                BasicStroke bs = new BasicStroke(strokeWidth,
                        BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER);
                g.setStroke(bs); // 重置细画笔
                gclone.setStroke(bs); // 重置细画笔
                g.setColor(canvas.backgroundColor); // 绘图工具使用背景色
                gclone.setColor(canvas.backgroundColor);
                canvas.g.setStroke(bs); // 重置细画笔
                canvas.gclone.setStroke(bs); // 重置细画笔
                canvas.g.setColor(canvas.backgroundColor); // 绘图工具使用背景色
                canvas.gclone.setColor(canvas.backgroundColor);
                g.fillRect(0, 0, canvas.width, canvas.height); // 画一个背景颜色的方形填满整个画布
                gclone.fillRect(0, 0, canvas.width, canvas.height); // 画一个背景颜色的方形填满整个画布
                g.setColor(canvas.foreColor); // 绘图工具使用前景色
                gclone.setColor(canvas.foreColor); // 绘图工具使用前景色
                canvas.repaint(); // 更新画布
                number=0;//不可撤销成新建之前
                autosave();
            }
        });

        usehelp.addActionListener(new ActionListener() {// 使用说明菜单添加动作监听
            public void actionPerformed(final ActionEvent e) {// 点击时
                JOptionPane.showMessageDialog(null, "1.\t本程序不需要手动新建，可以直接进行编辑。\n" +
                        "2.\t在画完图形时可进行拖动该图形，进行其它操作之后，就不再可拖动。类似windows的画板。\n" +
                        "3.\t图形填充可在任何封闭区域内填充。\n", "使用说明", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        bughelp.addActionListener(new ActionListener() {// bug说明菜单添加动作监听
            public void actionPerformed(final ActionEvent e) {// 点击时
                JOptionPane.showMessageDialog(null, "1. 在部分系统，比如win7、win8上，在文本框输入文字后，菜单栏会被画布遮住。未找到有效解决方案。\n" +
                        "2. 由于磁盘读写的问题，保存、读取文件的操作偶尔会失效，文件未按要求保存，同时导致撤销功能偶尔出错。未找到有效解决方案。\n", "bug说明", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        saveMenuItem.addActionListener(new ActionListener() {// 保存菜单添加动作监听
            public void actionPerformed(ActionEvent e) {// 点击时
                DrawImageUtil.saveImage1(MyFrame.this, image);// 打印图片
            }
        });
        openMenuItem.addActionListener(new ActionListener() {// 打开菜单添加动作监听
            public void actionPerformed(ActionEvent e) {// 点击时
                DrawImageUtil.openImage1(MyFrame.this, canvas);// 打开图片
            }
        });
        shapeButton.addActionListener(new ActionListener() {// 图形按钮添加动作监听
            public void actionPerformed(ActionEvent e) {// 点击时
                ShapeWindow shapeWindow = new ShapeWindow(
                        MyFrame.this, canvas);// 创建图形选择组件
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
        wordsize1.addActionListener(new ActionListener() {// 设置字体大小20按钮添加动作监听
            public void actionPerformed(ActionEvent e) {
                canvas.key1 = 20;
                canvas.wordsize=Integer.valueOf(canvas.key1);
            }
        });
        wordsize2.addActionListener(new ActionListener() {// 设置字体大小30按钮添加动作监听
            public void actionPerformed(ActionEvent e) {
                canvas.key1 = 30;
                canvas.wordsize=Integer.valueOf(canvas.key1);
            }
        });
        wordsize3.addActionListener(new ActionListener() {// 设置字体大小40按钮添加动作监听
            public void actionPerformed(ActionEvent e) {
                canvas.key1 = 40;
                canvas.wordsize=Integer.valueOf(canvas.key1);
            }
        });
        setziti.addActionListener(new ActionListener() {// 设置字体格式按钮添加动作监听
            public void actionPerformed(ActionEvent e) {
                canvas.key2 = JOptionPane.showInputDialog(MyFrame.this,"输入字体：");
                canvas.ziti=canvas.key2;
            }
        });

        ziti1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {// 设置字体格式楷体按钮添加动作监听
                canvas.key2 = "楷体";
                canvas.ziti=canvas.key2;
            }
        });
        ziti2.addActionListener(new ActionListener() {// 设置字体格式宋体按钮添加动作监听
            public void actionPerformed(ActionEvent e) {
                canvas.key2 = "宋体";
                canvas.ziti=canvas.key2;
            }
        });
        ziti3.addActionListener(new ActionListener() {// 设置字体格式黑体按钮添加动作监听
            public void actionPerformed(ActionEvent e) {
                canvas.key2 = "黑体";
                canvas.ziti=canvas.key2;
            }
        });

        canvas.addListener(MyFrame.this);
    }

    /**
	 * FrameGetShape接口实现类，用于获得图形空间返回的被选中的图形
	 */
	public void getShape(Shapes shape) {
        canvas.shape = shape;// 将返回的图形对象付给类的全局变量
        canvas.drawShape = true;// 画图形标识变量为true，说明选择鼠标画的是图形，而不是线
	}// getShape()结束

    public void autosave(){//存储当前图片
	    String name = "image"+number;//设定要自动保存的文件名
        DrawImageUtil.saveImage2(MyFrame.this, image, name);//调用函数，保存文件
    }



	public static void main(String[] args) {
		MyFrame frame = new MyFrame();// 创建窗体对象
		frame.setVisible(true);// 让窗体可见
	}// main()结束

}// MyFrame类结束
