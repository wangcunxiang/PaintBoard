import java.awt.Graphics;

/**
 * Created by wangcunxiang on 2017/10/18.
 */
public class Rectangle extends Shapes
{
    /**
     * This constructor of Rectangle creates and initializes a Rectangle object.
     * @param x starting x-value (top-left)
     * @param y starting y-value (top-left)
     * @param filled whether the Rectangle is filled or not
     */
    public Rectangle(int x, int y, int x2, int y2)
    {
        super(x, y, x2, y2);
    }

    /**
     * This method draws the Rectangle object with a color. It checks if it's filled or not.
     */
    public void draw(Graphics g)
    {
            g.drawRect(getX(), getY(), getWidth(), getHeight());
    }


}
