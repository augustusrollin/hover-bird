
package HoverBird;
import java.util.*;
import java.awt.*;
public class Obstacle {
	private int width;
	private int height;
	private int x;
	private int y;
	private Color mainColor;
	private Color secondaryColor;
	private Color tertiaryColor;
	public Obstacle(int w, int h, int xLoc, int yLoc) {
		width = w;
		height = h;
		x = xLoc;
		y = yLoc;
	}
	public void generate(Graphics g) {
		g.setColor(mainColor);
		g.fillRect(x, y, width, height);
		g.drawRect(x, y, width, height);
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public Color getMainColor() {
		return mainColor;
	}
	public void setMainColor(Color mainColor) {
		this.mainColor = mainColor;
	}
	public Color getSecondaryColor() {
		return secondaryColor;
	}
	public void setSecondaryColor(Color secondaryColor) {
		this.secondaryColor = secondaryColor;
	}
	public Color getTertiaryColor() {
		return tertiaryColor;
	}
	public void setTertiaryColor(Color tertiaryColor) {
		this.tertiaryColor = tertiaryColor;
	}
}
