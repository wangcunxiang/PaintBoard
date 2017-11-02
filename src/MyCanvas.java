import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.Stack;
import java.util.Queue;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by wangcunxiang on 2017/10/18.
 */
public class MyCanvas extends Canvas {
	BufferedImage image; // 创建画板中展示的图片对象
	BufferedImage imageclone;

	Graphics gs;
	Graphics2D g;
	Graphics gsclone;
	Graphics2D gclone;
	int width;
	int height;

	boolean rubber = false; // 橡皮标识变量
	boolean drawShape = false; // 画图形标识变量
	boolean text = false;//
	boolean fill = false;//
	boolean undo = false;//
	boolean pen = true;//

	int key1 = 20;
	String key2 = "楷体";
	int wordsize = 20;
	String ziti = "楷体";

	Color foreColor = Color.BLACK; // 定义前景色
	Color backgroundColor = Color.WHITE; // 定义背景色
	Shapes shape;// 绘画的图形
	String inputShape="";
	private int x = -1; // 上一次鼠标绘制点的横坐标
	private int y = -1; // 上一次鼠标绘制点的纵坐标
	private int lastX = -1;
	private int lastY = -1;
	private int las2X = -1;
	private int las2Y = -1;
	private int saveX = -1;
	private int saveY = -1;
	boolean canMove =false;
	boolean nowMove =false;
	int cur = 0;
	int base = 10;
	public ArrayList<Shapes> array;
	String intext;

	public MyCanvas(Graphics2D g, Graphics2D gclone){
		this.g = g;
		this.gclone = gclone;
		g.setColor(foreColor);
		gclone.setColor(foreColor);
		array = new ArrayList<Shapes>();
	}
	public void setImage(BufferedImage image,BufferedImage imageclon) {
		this.image = image; // 为成员变量赋值
		this.imageclone = imageclon;
	}
	public void paint(Graphics g) {
		g.drawImage(image, 0, 0, null); // 在画布上绘制图像
	}// paint(Graphics g)结束

	public void update(Graphics g) {
		paint(g); // 调用paint方法
	}// update(Graphics g)结束

	public void drawAll(Graphics g){
		for (Shapes s: array)
		{
			s.draw(g);
		}
	}

	public void drawthis(String shape,int x1,int y1,int x2,int y2){
		if(shape.equals("circle")){
			int temp;
			if(x2<x1){temp = x1;x1 = x2; x2 = temp; }
			if(y2<y1){temp = y1;y1 = y2; y2 = temp; }
			g.drawOval(x1,y1,x2-x1,y2-y1);
		}
		else if(shape.equals("rectangle")){
			int temp;
			if(x2<x1){temp = x1;x1 = x2; x2 = temp; }
			if(y2<y1){temp = y1;y1 = y2; y2 = temp; }
			g.drawRect(x1,y1,x2-x1,y2-y1);
		}
		else g.drawLine(x1,y1,x2, y2);
	}

	public void savethis(String shape,int x1,int y1,int x2,int y2){
		if(shape.equals("circle")){
			int temp;
			if(x2<x1){temp = x1;x1 = x2; x2 = temp; }
			if(y2<y1){temp = y1;y1 = y2; y2 = temp; }
			Shapes s =  new Circle(x1,y1,x2,y2);
			array.add(new Circle(x1,y1,x2,y2));
		}
		else if(shape.equals("rectangle")){
			int temp;
			if(x2<x1){temp = x1;x1 = x2; x2 = temp; }
			if(y2<y1){temp = y1;y1 = y2; y2 = temp; }
			array.add(new Rectangle(x1,y1,x2,y2));
		}
		else if(shape.equals("Line")){
			array.add(new Line(x1,y1,x2,y2));
		}
	}

	public void addListener(MyFrame frame) {
		// 画板添加鼠标移动事件监听
		addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseDragged(final MouseEvent e) {// 当鼠标拖拽时
				if (x > 0 && y > 0) {// 如果x和y存在鼠标记录
					//System.out.println( "++" + nowMove);
					if(nowMove){
						//System.out.println( lastX +" "+ las2X + " "+ array.get(array.size() - 1).getX());

						rubber = false;
						pen = false;
						las2X = lastX;
						las2Y = lastY;
						array.get(array.size() - 1).setX(saveX  - x +  las2X);
						array.get(array.size() - 1).setY(saveY  - y +  las2Y);
						g.setColor(backgroundColor);
						array.get(array.size() - 1).draw(g);
						//drawthis(inputShape, x,y,las2X,las2Y);
						if (cur++ % 10 == 0)image.setData(imageclone.getData());
						lastX = e.getX();
						lastY = e.getY();
						//lastY = e.getY();
						g.setColor(foreColor);
						/*for (Shapes s: array)
						{
							if(s!=array.get(array.size() - 1))s.draw(g);
						}*/
						array.get(array.size() - 1).setX(saveX  - x +  lastX);
						array.get(array.size() - 1).setY(saveY  - y +  lastY);
						array.get(array.size() - 1).draw(g);
					}
					else if (rubber) {// 橡皮标识为true，表示使用橡皮
						drawAll(gclone);
						array.clear();
						g.setColor(backgroundColor); // 绘图工具使用背景色
						gclone.setColor(backgroundColor);
						g.fillRect(e.getX(), e.getY(), 10, 10); // 在鼠标划过的位置画填充的正方型
						gclone.fillRect(e.getX(), e.getY(), 10, 10);
						g.setColor(foreColor); // 绘图工具使用背景色
						gclone.setColor(foreColor);
					} else if(pen){ // 如果橡皮标识为false，表示用画笔画图
						g.setColor(foreColor);
						gclone.setColor(foreColor);
						g.drawLine(x, y, e.getX(), e.getY());// 在鼠标划过的位置画直线
						gclone.drawLine(x, y, e.getX(), e.getY());
						x = e.getX(); // 上一次鼠标绘制点的横坐标
						y = e.getY(); // 上一次鼠标绘制点的纵坐标
					}// else结束
					else if(inputShape.equals("circle")||inputShape.equals("rectangle")||inputShape.equals("Line")){
						rubber = false;
						pen = false;
						las2X = lastX;
						las2Y = lastY;
						g.setColor(backgroundColor);
						drawthis(inputShape, x,y,las2X,las2Y);
						if (cur++ % 2 == 0)image.setData(imageclone.getData());
						lastX = e.getX();
						lastY = e.getY();
						g.setColor(foreColor);
						//drawAll(g);
						drawthis(inputShape, x,y,lastX,lastY);
						//repaint();
					}
				}// if结束
				repaint(); // 更新画布
			}

			public void mouseMoved(final MouseEvent arg0) {// 当鼠标移动时
				if (rubber) {// 如果使用橡皮
					// 设置鼠标指针的形状为图片
					Toolkit kit = Toolkit.getDefaultToolkit();// 获得系统默认的组件工具包
					Image img = kit.createImage("src/img/icon/鼠标橡皮.png");// 利用工具包获取图片
					// 利用工具包创建一个自定义的光标对象,参数为图片，光标热点(写成0,0就行)和光标描述
					Cursor c = kit.createCustomCursor(img, new Point(0, 0),
							"clear");
					setCursor(c);// 使用自定义的光标
				} else if(fill){
					// 设置鼠标指针的形状为图片
					Toolkit kit = Toolkit.getDefaultToolkit();// 获得系统默认的组件工具包
					Image img = kit.createImage("src/img/icon/填充3.png");// 利用工具包获取图片
					// 利用工具包创建一个自定义的光标对象,参数为图片，光标热点(写成0,0就行)和光标描述
					Cursor c = kit.createCustomCursor(img, new Point(0, 0),
							"clear");
					setCursor(c);// 使用自定义的光标
				}else if(pen) {
					// 设置鼠标指针的形状
					Toolkit kit = Toolkit.getDefaultToolkit();// 获得系统默认的组件工具包
					Image img = kit.createImage("src/img/icon/画笔1.png");
					Cursor c = kit.createCustomCursor(img, new Point(0, 0),
							"clear");
					setCursor(c);// 使用自定义的光标
				}else {
					// 设置鼠标指针的形状
					setCursor(Cursor
							.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));// 设置鼠标指针的形状为十字光标
				}
			}
		});
		addMouseListener(new MouseAdapter() {// 画板鼠标点击事件监听事件
			public void mouseReleased(final MouseEvent e) {// 当按键抬起时
				if(text){//如果是添加文本
					g.setFont(new Font(ziti,Font.BOLD,wordsize));//设置文本类型
					g.setColor(foreColor);//设置文本颜色
					g.drawString(intext,e.getX(),e.getY());//添加文本
					gclone.setFont(new Font(ziti,Font.BOLD,wordsize));//设置文本类型
					gclone.setColor(foreColor);//设置文本颜色
					gclone.drawString(intext,e.getX(),e.getY());//添加文本
					repaint();
				}
				if(fill){//如果是填充
					Queue<Point> queue;//声明点队列
					queue = new LinkedList<Point>();//创建点队列
					queue.add(new Point(e.getX()+10, e.getY()+10));//加入点击位置作为种子
					BasicStroke bs = new BasicStroke(1,
							BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER);
					g.setStroke(bs); // 因为使用粗画笔时，填充功能会出现bug，故暂时将其先设置为细画笔
					gclone.setStroke(bs);
					//System.out.println(e.getX()+" "+e.getY());
					while(queue.size()!=0){//队列未空
						Point tem = queue.remove();//取出队列头
						if(tem.x > 0 && tem.x < width - 1 && tem.y > 0 && tem.y < height - 1){
							Color color1 = new Color(image.getRGB(tem.x+1, tem.y), true);//四方向填充法
							Color color2 = new Color(image.getRGB(tem.x-1, tem.y), true);
							Color color3 = new Color(image.getRGB(tem.x, tem.y+1), true);
							Color color4 = new Color(image.getRGB(tem.x, tem.y-1), true);
							if(color1.equals(backgroundColor)){//只要不是背景色，就加入队列
								g.drawLine(tem.x,tem.y,tem.x+1,tem.y);
								gclone.drawLine(tem.x,tem.y,tem.x+1,tem.y);
								queue.add(new Point(tem.x+1, tem.y));//将该点加入队列
							}
							if(color2.equals(backgroundColor)){
								g.drawLine(tem.x,tem.y,tem.x-1,tem.y);
								gclone.drawLine(tem.x,tem.y,tem.x-1,tem.y);
								queue.add(new Point(tem.x-1, tem.y));
							}
							if(color3.equals(backgroundColor)){
								g.drawLine(tem.x,tem.y,tem.x,tem.y+1);
								gclone.drawLine(tem.x,tem.y,tem.x,tem.y+1);
								queue.add(new Point(tem.x, tem.y+1));
							}
							if(color4.equals(backgroundColor)){
								g.drawLine(tem.x,tem.y,tem.x,tem.y-1);
								gclone.drawLine(tem.x,tem.y,tem.x,tem.y-1);
								queue.add(new Point(tem.x, tem.y-1));
							}
						}
					}
					BasicStroke bs1 = new BasicStroke(frame.strokeWidth,
							BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER);
					g.setStroke(bs1); // 重新使用回原画笔
					gclone.setStroke(bs1);
					repaint();
				}
				//frame.number++;
				//frame.autosave();

				savethis(inputShape,x,y,e.getX(),e.getY());
				//System.out.println(canMove);
				if(canMove){
					canMove = false;
					pen =true;
					//System.out.println("wawa");
					drawAll(gclone);
				}
				if(!inputShape.equals("")){
					canMove = true;
					nowMove = false;
					inputShape = "";

				}
				else{
					nowMove = false;
					canMove = false;
				}

				frame.number++;
				frame.autosave();
				x = -1; // 将记录上一次鼠标绘制点的横坐标恢复成-1
				y = -1; // 将记录上一次鼠标绘制点的纵坐标恢复成-1
			}

			public void mousePressed(MouseEvent e) {// 当按键按下时
				//System.out.println(canMove);
				if(rubber)inputShape = "";
				x = e.getX();
				y = e.getY();
				if(canMove){
					if(	x >= array.get(array.size() - 1).getX() &&
							x <= array.get(array.size() - 1).getX() + array.get(array.size() - 1).getWidth() &&
							y <= array.get(array.size() - 1).getY() + array.get(array.size() - 1).getHeight() &&
							y >= array.get(array.size() - 1).getY() ){
						nowMove = true;
						saveX = array.get(array.size() - 1).getX();
						saveY = array.get(array.size() - 1).getY();
					}
					else{
						//System.out.println("+++++++++++++++++");
						drawAll(g);
						drawAll(gclone);
					}
				}
					//drawAll(gclone);
					//array.clear();
			}
		});

	}

}// MyCanvas类结束