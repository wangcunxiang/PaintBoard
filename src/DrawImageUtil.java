import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by wangcunxiang on 2017/10/21.
 */
public class DrawImageUtil {
    public DrawImageUtil() {
    }

    public static void saveImage1(MyFrame frame, BufferedImage saveImage) {
        //JFileChooser jfcSave = new JFileChooser();
        int res = frame.jfcSave.showSaveDialog(frame);//ѡȡ�ļ�
        if (res == JFileChooser.APPROVE_OPTION) {
            File file = frame.jfcSave.getSelectedFile();
            String fname = file.getName();
            if(fname.indexOf(".")==-1){
                file=new File(frame.jfcSave.getCurrentDirectory(),fname+".bmp");
                fname = file.getName();
            }
            try {
                ImageIO.write(saveImage,fname.substring(fname.lastIndexOf(".") + 1),file);//�����ļ�
            } catch (IOException e) {
                // TODO �Զ����ɵ� catch ��
                e.printStackTrace();
            }
            System.out.println("File Save Finished!");
        }
    }
    public static void saveImage2(MyFrame frame, BufferedImage saveImage, String name) {
        String dir = System.getProperty("user.dir")+"\\saveimage";//�趨�Զ������Ŀ¼
        File file = new File(dir,name+".bmp");
        String fname = file.getName();
        try {
            ImageIO.write(saveImage,fname.substring(fname.lastIndexOf(".") + 1),file);//�����ļ�
        } catch (IOException e) {
            // TODO �Զ����ɵ� catch ��
            e.printStackTrace();
        }
    }
    public static void openImage1(MyFrame frame, MyCanvas canvas) {
        //JFileChooser jfcOpen = new JFileChooser();
        int res = frame.jfcOpen.showOpenDialog(frame);
        if(res == JFileChooser.APPROVE_OPTION) {
            File file = frame.jfcOpen.getSelectedFile();
            try {
                frame.image = ImageIO.read(file);//��ȡ�ļ�
                canvas.setImage(frame.image, frame.imageclone);//�����趨image����
                frame.gs = frame.image.getGraphics();
                frame.g = (Graphics2D)frame.gs;
                canvas.gs = frame.gs;
                canvas.g = frame.g;
                canvas.g.setColor(canvas.foreColor);
                canvas.gclone = frame.g;
                canvas.gclone.setColor(canvas.foreColor);
                //System.out.println(canvas.foreColor);
                BasicStroke bs = new BasicStroke(frame.strokeWidth,
                        BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER);
                frame.g.setStroke(bs); // ��ͼ����ʹ�ô˻���
                frame.gclone.setStroke(bs); // ��ͼ����ʹ�ô˻���
                canvas.repaint();
            }catch(Exception err) {
                System.out.println(err.getMessage());
            }
        }
    }
    public static void openImage2(MyFrame frame, MyCanvas canvas, String name) {
        File file = new File(System.getProperty("user.dir")+"\\saveimage\\"+name+".bmp");//�趨�Զ���ȡ��λ��
        try {
            canvas.image = ImageIO.read(file);//��ȡ�ļ�
            //frame.imageclone = frame.image;
            canvas.imageclone.setData(canvas.image.getData());/*//�����趨image����
            canvas.setImage(frame.image, frame.imageclone);*/

            canvas.gs = canvas.image.getGraphics();
            canvas.g = (Graphics2D)canvas.gs;
            canvas.gsclone = canvas.imageclone.getGraphics();
            canvas.gclone = (Graphics2D)canvas.gsclone;

            canvas.array.clear();

            canvas.g.setColor(canvas.foreColor);
            canvas.gclone.setColor(canvas.foreColor);
            BasicStroke bs = new BasicStroke(frame.strokeWidth,
                    BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER);
            frame.g.setStroke(bs); // ��ͼ����ʹ�ô˻���
            frame.gclone.setStroke(bs); // ��ͼ����ʹ�ô˻���
            canvas.g.setStroke(bs); // ��ͼ����ʹ�ô˻���
            canvas.gclone.setStroke(bs); // ��ͼ����ʹ�ô˻���
            canvas.repaint();
        }catch(Exception err) {
            System.out.println(err.getMessage());
        }
    }
}
