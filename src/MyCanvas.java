
import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Image;

/**
 * ��ʻ�չʾ����
 * 
 * @������λ ����ʡ���տƼ����޹�˾
 * @��˾��ַ www.mingribook.com
 */
public class MyCanvas extends Canvas {
	private Image image = null; // ����������չʾ��ͼƬ����

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

}// MyCanvas�����