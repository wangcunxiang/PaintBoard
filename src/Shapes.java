import java.awt.*;

/**
 * Created by wangcunxiang on 2017/10/21.
 */
public class Shapes {
    public static final int YUAN = 25377;
    public static final int FANG = 25637;
    private int x;
    private int y;
    private int type;
    private int width;
    private int height;
    private int beforeX;
    private int beforeY;
    private int stokewidth;

    private Color c;

    public Shapes() {
    }

    public Shapes(int type, int width, int height) {
        this.type = type;
        this.width = width;
        this.height = height;
    }

    public int getType() {
        return this.type;
    }

    public Shapes(int x,int y,int x2,int y2){
        this.x = x;
        this.y = y;
        width = x2 - x;
        height = y2 - y;
        c = Color.BLACK;
    }

    public void draw(Graphics g){};

    public void setType(int type) {
        this.type = type;
    }

    public int getWidth() {
        return this.width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int width) {
        this.height = height;
    }

    public int getHeight() {
        return this.height;
    }

    public void setStokewidth(int Strokewidth){
        this.stokewidth = Strokewidth;
    }

    public int getStokewidth(){
        return this.stokewidth;
    }

    public int getX(){return x;}

    public int getY(){return y;}

    public void setX(int x){this.x = x;}

    public void setY(int y){this.y = y;}

    public String toString() {
        return "Shapes [type=" + this.type + ", width=" + this.width + ", height=" + this.height + "]";
    }
}
