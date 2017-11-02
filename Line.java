import java.awt.Graphics;

/**
 * Created by wangcunxiang on 2017/10/18.
 */
public class Line extends Shapes
{

    /**
     * This constructor of Circle creates and initializes a Circle object (actually ovals).
     * @param x the starting x-value (top-left)
     * @param y the starting y-value (top-left)
     */
    public Line(int x, int y,int x2,int y2)
    {
        super(x, y, x2, y2);
    }

    /**
     * This method draws the Circle object with a color. It checks if it's filled or not.
     */
    public void draw(Graphics g)
    {
        //g.setColor(super.getColor());
        g.drawLine(getX(), getY(), getWidth() + getX(), getHeight() + getY());
    }



}
