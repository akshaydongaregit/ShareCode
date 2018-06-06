package networking;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

public class ClientItem extends TButton{
	
	Client parent=null;
	
	public ClientItem(String address)
	{
		super(address);
	}

	public Client getParent() {
		return parent;
	}

	public void setParent(Client parent) {
		this.parent = parent;
	}
	
}
