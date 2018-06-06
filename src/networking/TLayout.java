package networking;

import java.awt.Color;
import java.awt.Font;

public class TLayout {
	Color color;
	Color background;
	Font font;
	Color onHover;
	
	public static TLayout getDefaultLayout()
	{
		return new TLayout(new Color(255,255,255,255),new Color(0,0,250,255),
				new Font("Tamoha",Font.BOLD, 16), new Color(0,0,0,255));
	}
	
	public TLayout(Color color, Color background, Font font, Color onHover) {
		super();
		this.color = color;
		this.background = background;
		this.font = font;
		this.onHover = onHover;
	}
	
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	public Color getBackground() {
		return background;
	}
	public void setBackground(Color background) {
		this.background = background;
	}
	public Font getFont() {
		return font;
	}
	public void setFont(Font font) {
		this.font = font;
	}
	public Color getOnHover() {
		return onHover;
	}
	public void setOnHover(Color onHover) {
		this.onHover = onHover;
	}
	
}
