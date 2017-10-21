/**
 * Created by wangcunxiang on 2017/10/21.
 */
public class Shapes {
    public static final int YUAN = 25377;
    public static final int FANG = 25637;
    private int type;
    private int width;
    private int height;

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

    public void setType(int type) {
        this.type = type;
    }

    public int getWidth() {
        return this.width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeigth() {
        return this.height;
    }

    public void setHeigth(int height) {
        this.height = height;
    }

    public String toString() {
        return "Shapes [type=" + this.type + ", width=" + this.width + ", height=" + this.height + "]";
    }
}
