
import com.mr.util.DrawImageUtil;
import com.mr.util.ShapeWindow;
import com.mr.util.Shapes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

/**
 * ��ʻ�չʾ����
 * 
 * @������λ ����ʡ���տƼ����޹�˾
 * @��˾��ַ www.mingribook.com
 */
public class MyCanvas extends Canvas {
	private Image image = null; // ����������չʾ��ͼƬ����
	Graphics2D g;

	boolean rubber = false; // ��Ƥ��ʶ����
	boolean drawShape = false; // ��ͼ�α�ʶ����
	boolean text = false;//
	Color foreColor = Color.BLACK; // ����ǰ��ɫ
	Color backgroundColor = Color.WHITE; // ���屳��ɫ
	Shapes shape;// �滭��ͼ��
    private int x = -1; // ��һ�������Ƶ�ĺ�����
    private int y = -1; // ��һ�������Ƶ��������

	public MyCanvas(Graphics2D g){
		this.g = g;
	}
	/**
	 * ���û����е�ͼƬ��
	 * 
	 * @param image
	 *            - ������չʾ��ͼƬ����
	 */
	public void setImage(Image image) {
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