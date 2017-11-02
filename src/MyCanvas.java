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
	BufferedImage image; // ����������չʾ��ͼƬ����
	BufferedImage imageclone;

	Graphics gs;
	Graphics2D g;
	Graphics gsclone;
	Graphics2D gclone;
	int width;
	int height;

	boolean rubber = false; // ��Ƥ��ʶ����
	boolean drawShape = false; // ��ͼ�α�ʶ����
	boolean text = false;//
	boolean fill = false;//
	boolean undo = false;//
	boolean pen = true;//

	int key1 = 20;
	String key2 = "����";
	int wordsize = 20;
	String ziti = "����";

	Color foreColor = Color.BLACK; // ����ǰ��ɫ
	Color backgroundColor = Color.WHITE; // ���屳��ɫ
	Shapes shape;// �滭��ͼ��
	String inputShape="";
	private int x = -1; // ��һ�������Ƶ�ĺ�����
	private int y = -1; // ��һ�������Ƶ��������
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
		this.image = image; // Ϊ��Ա������ֵ
		this.imageclone = imageclon;
	}
	public void paint(Graphics g) {
		g.drawImage(image, 0, 0, null); // �ڻ����ϻ���ͼ��
	}// paint(Graphics g)����

	public void update(Graphics g) {
		paint(g); // ����paint����
	}// update(Graphics g)����

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
		// �����������ƶ��¼�����
		addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseDragged(final MouseEvent e) {// �������קʱ
				if (x > 0 && y > 0) {// ���x��y��������¼
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
					else if (rubber) {// ��Ƥ��ʶΪtrue����ʾʹ����Ƥ
						drawAll(gclone);
						array.clear();
						g.setColor(backgroundColor); // ��ͼ����ʹ�ñ���ɫ
						gclone.setColor(backgroundColor);
						g.fillRect(e.getX(), e.getY(), 10, 10); // ����껮����λ�û�����������
						gclone.fillRect(e.getX(), e.getY(), 10, 10);
						g.setColor(foreColor); // ��ͼ����ʹ�ñ���ɫ
						gclone.setColor(foreColor);
					} else if(pen){ // �����Ƥ��ʶΪfalse����ʾ�û��ʻ�ͼ
						g.setColor(foreColor);
						gclone.setColor(foreColor);
						g.drawLine(x, y, e.getX(), e.getY());// ����껮����λ�û�ֱ��
						gclone.drawLine(x, y, e.getX(), e.getY());
						x = e.getX(); // ��һ�������Ƶ�ĺ�����
						y = e.getY(); // ��һ�������Ƶ��������
					}// else����
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
				}// if����
				repaint(); // ���»���
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
				} else if(fill){
					// �������ָ�����״ΪͼƬ
					Toolkit kit = Toolkit.getDefaultToolkit();// ���ϵͳĬ�ϵ�������߰�
					Image img = kit.createImage("src/img/icon/���3.png");// ���ù��߰���ȡͼƬ
					// ���ù��߰�����һ���Զ���Ĺ�����,����ΪͼƬ������ȵ�(д��0,0����)�͹������
					Cursor c = kit.createCustomCursor(img, new Point(0, 0),
							"clear");
					setCursor(c);// ʹ���Զ���Ĺ��
				}else if(pen) {
					// �������ָ�����״
					Toolkit kit = Toolkit.getDefaultToolkit();// ���ϵͳĬ�ϵ�������߰�
					Image img = kit.createImage("src/img/icon/����1.png");
					Cursor c = kit.createCustomCursor(img, new Point(0, 0),
							"clear");
					setCursor(c);// ʹ���Զ���Ĺ��
				}else {
					// �������ָ�����״
					setCursor(Cursor
							.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));// �������ָ�����״Ϊʮ�ֹ��
				}
			}
		});
		addMouseListener(new MouseAdapter() {// ����������¼������¼�
			public void mouseReleased(final MouseEvent e) {// ������̧��ʱ
				if(text){//���������ı�
					g.setFont(new Font(ziti,Font.BOLD,wordsize));//�����ı�����
					g.setColor(foreColor);//�����ı���ɫ
					g.drawString(intext,e.getX(),e.getY());//����ı�
					gclone.setFont(new Font(ziti,Font.BOLD,wordsize));//�����ı�����
					gclone.setColor(foreColor);//�����ı���ɫ
					gclone.drawString(intext,e.getX(),e.getY());//����ı�
					repaint();
				}
				if(fill){//��������
					Queue<Point> queue;//���������
					queue = new LinkedList<Point>();//���������
					queue.add(new Point(e.getX()+10, e.getY()+10));//������λ����Ϊ����
					BasicStroke bs = new BasicStroke(1,
							BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER);
					g.setStroke(bs); // ��Ϊʹ�ôֻ���ʱ����书�ܻ����bug������ʱ����������Ϊϸ����
					gclone.setStroke(bs);
					//System.out.println(e.getX()+" "+e.getY());
					while(queue.size()!=0){//����δ��
						Point tem = queue.remove();//ȡ������ͷ
						if(tem.x > 0 && tem.x < width - 1 && tem.y > 0 && tem.y < height - 1){
							Color color1 = new Color(image.getRGB(tem.x+1, tem.y), true);//�ķ�����䷨
							Color color2 = new Color(image.getRGB(tem.x-1, tem.y), true);
							Color color3 = new Color(image.getRGB(tem.x, tem.y+1), true);
							Color color4 = new Color(image.getRGB(tem.x, tem.y-1), true);
							if(color1.equals(backgroundColor)){//ֻҪ���Ǳ���ɫ���ͼ������
								g.drawLine(tem.x,tem.y,tem.x+1,tem.y);
								gclone.drawLine(tem.x,tem.y,tem.x+1,tem.y);
								queue.add(new Point(tem.x+1, tem.y));//���õ�������
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
					g.setStroke(bs1); // ����ʹ�û�ԭ����
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
				x = -1; // ����¼��һ�������Ƶ�ĺ�����ָ���-1
				y = -1; // ����¼��һ�������Ƶ��������ָ���-1
			}

			public void mousePressed(MouseEvent e) {// ����������ʱ
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

}// MyCanvas�����