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
        int res = frame.jfcSave.showSaveDialog(frame);//选取文件
        if (res == JFileChooser.APPROVE_OPTION) {
            File file = frame.jfcSave.getSelectedFile();
            String fname = file.getName();
            if(fname.indexOf(".")==-1){
                file=new File(frame.jfcSave.getCurrentDirectory(),fname+".bmp");
                fname = file.getName();
            }
            try {
                ImageIO.write(saveImage,fname.substring(fname.lastIndexOf(".") + 1),file);//保存文件
            } catch (IOException e) {
                // TODO 自动生成的 catch 块
                e.printStackTrace();
            }
            System.out.println("File Save Finished!");
        }
    }
    public static void saveImage2(MyFrame frame, BufferedImage saveImage, String name) {
        String dir = System.getProperty("user.dir")+"\\saveimage";//设定自动保存的目录
        File file = new File(dir,name+".bmp");
        String fname = file.getName();
        try {
            ImageIO.write(saveImage,fname.substring(fname.lastIndexOf(".") + 1),file);//保存文件
        } catch (IOException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        }
    }
    public static void openImage1(MyFrame frame, MyCanvas canvas) {
        //JFileChooser jfcOpen = new JFileChooser();
        int res = frame.jfcOpen.showOpenDialog(frame);
        if(res == JFileChooser.APPROVE_OPTION) {
            File file = frame.jfcOpen.getSelectedFile();
            try {
                frame.image = ImageIO.read(file);//读取文件
                canvas.setImage(frame.image, frame.imageclone);//重新设定image参数
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
                frame.g.setStroke(bs); // 画图工具使用此画笔
                frame.gclone.setStroke(bs); // 画图工具使用此画笔
                canvas.repaint();
            }catch(Exception err) {
                System.out.println(err.getMessage());
            }
        }
    }
    public static void openImage2(MyFrame frame, MyCanvas canvas, String name) {
        File file = new File(System.getProperty("user.dir")+"\\saveimage\\"+name+".bmp");//设定自动读取的位置
        try {
            canvas.image = ImageIO.read(file);//读取文件
            //frame.imageclone = frame.image;
            canvas.imageclone.setData(canvas.image.getData());/*//重新设定image参数
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
            frame.g.setStroke(bs); // 画图工具使用此画笔
            frame.gclone.setStroke(bs); // 画图工具使用此画笔
            canvas.g.setStroke(bs); // 画图工具使用此画笔
            canvas.gclone.setStroke(bs); // 画图工具使用此画笔
            canvas.repaint();
        }catch(Exception err) {
            System.out.println(err.getMessage());
        }
    }
}
