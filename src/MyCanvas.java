
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.Stack;

public class MyCanvas extends Canvas {
	private BufferedImage image; // 创建画板中展示的图片对象
	Graphics2D g;
	int width;
	int height;

	boolean rubber = false; // 橡皮标识变量
	boolean drawShape = false; // 画图形标识变量
	boolean text = false;//
	boolean fill = false;//
	Color foreColor = Color.BLACK; // 定义前景色
	Color backgroundColor = Color.WHITE; // 定义背景色
	Shapes shape;// 绘画的图形
    private int x = -1; // 上一次鼠标绘制点的横坐标
    private int y = -1; // 上一次鼠标绘制点的纵坐标
    String intext;

	public MyCanvas(Graphics2D g){
		this.g = g;
	}
	/**
	 * 设置画板中的图片。
	 * 
	 * @param
	 *            - 画板中展示的图片对象
	 */
	public void setImage(BufferedImage image) {
		this.image = image; // 为成员变量赋值
	}// setImage(Image image)结束

	/**
	 * 重写paint()方法，在画布上绘制图像。
	 */
	public void paint(Graphics g) {
		g.drawImage(image, 0, 0, null); // 在画布上绘制图像
	}// paint(Graphics g)结束

	/**
	 * 重写update()方法，这样可以解决屏幕闪耀的问题。
	 */
	public void update(Graphics g) {
		paint(g); // 调用paint方法
	}// update(Graphics g)结束

	public void addListener() {
		// 画板添加鼠标移动事件监听
		addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseDragged(final MouseEvent e) {// 当鼠标拖拽时
				if (x > 0 && y > 0) {// 如果x和y存在鼠标记录
					if (rubber) {// 橡皮标识为true，表示使用橡皮
						g.setColor(backgroundColor); // 绘图工具使用背景色
						g.fillRect(x, y, 10, 10); // 在鼠标划过的位置画填充的正方型
					} else { // 如果橡皮标识为false，表示用画笔画图
						g.drawLine(x, y, e.getX(), e.getY());// 在鼠标划过的位置画直线
					}// else结束
				}// if结束
				x = e.getX(); // 上一次鼠标绘制点的横坐标
				y = e.getY(); // 上一次鼠标绘制点的纵坐标
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
				} else {
					// 设置鼠标指针的形状
					setCursor(Cursor
							.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));// 设置鼠标指针的形状为十字光标
				}
			}
		});
		addMouseListener(new MouseAdapter() {// 画板鼠标点击事件监听事件
			public void mouseReleased(final MouseEvent arg0) {// 当按键抬起时
				x = -1; // 将记录上一次鼠标绘制点的横坐标恢复成-1
				y = -1; // 将记录上一次鼠标绘制点的纵坐标恢复成-1
			}

			public void mousePressed(MouseEvent e) {// 当按键按下时
                //System.out.println(e.getX()+"  "+e.getY());
                if(text){
                    g.setFont(new Font("楷体",Font.BOLD,20));
                    g.setColor(foreColor);
                    g.drawString(intext,e.getX(),e.getY());
                    repaint();
                }
                if(fill){
                	Stack<Point> stack = new Stack();
                	stack.push(new Point(e.getX(), e.getY()));
                	while(!stack.empty()){
                		Point tem = stack.pop();
						System.out.println(tem.x+" "+tem.y);
                		if(tem.x > 0 && tem.x < width - 1 && tem.y > 0 && tem.y < height - 1){
							Color color1 = new Color(image.getRGB(tem.x+1, tem.y), true);
							Color color2 = new Color(image.getRGB(tem.x-1, tem.y), true);
							Color color3 = new Color(image.getRGB(tem.x, tem.y+1), true);
							Color color4 = new Color(image.getRGB(tem.x, tem.y-1), true);
							if(color1.equals(backgroundColor)){
								System.out.println(backgroundColor);
								g.drawLine(tem.x,tem.y,tem.x+1,tem.y);
								stack.push(new Point(tem.x+1, tem.y));
							}
							if(color2.equals(backgroundColor)){
								g.drawLine(tem.x,tem.y,tem.x-1,tem.y);
								stack.push(new Point(tem.x-1, tem.y));
							}
							if(color3.equals(backgroundColor)){
								g.drawLine(tem.x,tem.y,tem.x,tem.y+1);
								stack.push(new Point(tem.x, tem.y+1));
							}
							if(color4.equals(backgroundColor)){
								g.drawLine(tem.x,tem.y,tem.x,tem.y-1);
								stack.push(new Point(tem.x, tem.y-1));
							}
						}
					}
					repaint();

				}
				if (drawShape) {// 如果此时鼠标画的是图形
					switch (shape.getType()) {// 判断图形的种类
						case Shapes.YUAN:// 如果是圆形
							// 计算坐标，让鼠标处于图形的中心位置
							int yuanX = e.getX() - shape.getWidth() / 2;
							int yuanY = e.getY() - shape.getHeigth() / 2;
							// 创建圆形图形，并指定坐标和宽高
							Ellipse2D yuan = new Ellipse2D.Double(yuanX, yuanY,
									shape.getWidth(), shape.getHeigth());
							g.draw(yuan);// 画图工具画此圆形
							break;// 结束switch语句
						case Shapes.FANG:// 如果是方形
							// 计算坐标，让鼠标处于图形的中心位置
							int fangX = e.getX() - shape.getWidth() / 2;
							int fangY = e.getY() - shape.getHeigth() / 2;
							// 创建方形图形，并指定坐标和宽高
							Rectangle2D fang = new Rectangle2D.Double(fangX, fangY,
									shape.getWidth(), shape.getHeigth());
							g.draw(fang);// 画图工具画此方形
							break;// 结束switch语句
					}
					repaint(); // 更新画布
					drawShape = false;
				}
			}
		});

	}

}// MyCanvas类结束