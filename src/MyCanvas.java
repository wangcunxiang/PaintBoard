
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.Stack;

public class MyCanvas extends Canvas {
	private BufferedImage image; // ����������չʾ��ͼƬ����
	Graphics2D g;
	int width;
	int height;

	boolean rubber = false; // ��Ƥ��ʶ����
	boolean drawShape = false; // ��ͼ�α�ʶ����
	boolean text = false;//
	boolean fill = false;//
	Color foreColor = Color.BLACK; // ����ǰ��ɫ
	Color backgroundColor = Color.WHITE; // ���屳��ɫ
	Shapes shape;// �滭��ͼ��
    private int x = -1; // ��һ�������Ƶ�ĺ�����
    private int y = -1; // ��һ�������Ƶ��������
    String intext;

	public MyCanvas(Graphics2D g){
		this.g = g;
	}
	/**
	 * ���û����е�ͼƬ��
	 * 
	 * @param
	 *            - ������չʾ��ͼƬ����
	 */
	public void setImage(BufferedImage image) {
		this.image = image; // Ϊ��Ա������ֵ
	}// setImage(Image image)����

	/**
	 * ��дpaint()�������ڻ����ϻ���ͼ��
	 */
	public void paint(Graphics g) {
		g.drawImage(image, 0, 0, null); // �ڻ����ϻ���ͼ��
	}// paint(Graphics g)����

	/**
	 * ��дupdate()�������������Խ����Ļ��ҫ�����⡣
	 */
	public void update(Graphics g) {
		paint(g); // ����paint����
	}// update(Graphics g)����

	public void addListener() {
		// �����������ƶ��¼�����
		addMouseMotionListener(new MouseMotionAdapter() {
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
				} else {
					// �������ָ�����״
					setCursor(Cursor
							.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));// �������ָ�����״Ϊʮ�ֹ��
				}
			}
		});
		addMouseListener(new MouseAdapter() {// ����������¼������¼�
			public void mouseReleased(final MouseEvent arg0) {// ������̧��ʱ
				x = -1; // ����¼��һ�������Ƶ�ĺ�����ָ���-1
				y = -1; // ����¼��һ�������Ƶ��������ָ���-1
			}

			public void mousePressed(MouseEvent e) {// ����������ʱ
                //System.out.println(e.getX()+"  "+e.getY());
                if(text){
                    g.setFont(new Font("����",Font.BOLD,20));
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
					repaint(); // ���»���
					drawShape = false;
				}
			}
		});

	}

}// MyCanvas�����