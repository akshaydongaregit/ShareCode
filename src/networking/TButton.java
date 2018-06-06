package networking;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;

import javax.swing.JPanel;

public class TButton extends JPanel {

	TLayout layout=TLayout.getDefaultLayout();
	String text="";
	TButtonActionListener actionListener=null;
	
	public TButton()
	{
		super();
		this.setBackground(layout.getBackground());
		layout.setBackground(new Color(10,40,255,250));
		super.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent arg0) {
				//System.out.println("mouse clicked");
				if(actionListener!=null)
					actionListener.onAction();
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				
			}
				
		});
	}
	public TButton(String text)
	{
		this();
		this.text=text;
	}
	@Override
	public void paint(Graphics g)
	{
		super.paint(g);
		Graphics2D g2d=(Graphics2D)g;
		 g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		 g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
			        RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);
		 this.drawBack(g2d);
		this.drawText(g2d);
	}
	private void drawBack(Graphics2D g2d)
	{
		g2d.setColor(layout.getBackground());
		g2d.fillRect(0,0,this.getWidth(),this.getHeight());
	}
	private void drawText(Graphics2D g2d)
	{
		g2d.setColor(layout.getColor());
		Font font=layout.getFont();
		g2d.setFont(font);
		
		AffineTransform affinetransform = new AffineTransform();     
		FontRenderContext frc = new FontRenderContext(affinetransform,true,true);     
		
		int textwidth = (int)(font.getStringBounds(text, frc).getWidth());
		int textheight = (int)(font.getStringBounds(text, frc).getHeight());
		int x=(this.getWidth()-textwidth)/2;
		int y=(this.getHeight()-textheight)/2+20;
		g2d.drawString(text,x,y);
	}
	
	public TLayout getTLayout() {
		return layout;
	}
	
	public void setTLayout(TLayout layout) {
		this.layout = layout;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}

	public TButtonActionListener getActionListener() {
		return actionListener;
	}
	public void setActionListener(TButtonActionListener actionListener) {
		this.actionListener = actionListener;
	}
	
}
