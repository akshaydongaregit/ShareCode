package networking;

import java.net.Socket;

import javax.swing.JFrame;

public class Client extends JFrame{
	
	final int PORT=2852;
	Socket socket=null;
	ClientItem item;
	
	public void connectTo(String address)
	{
		try {
			socket=new Socket(address,PORT);
			System.out.println("Connected to "+address);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}

	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}

	public ClientItem getItem() {
		return item;
	}

	public void setItem(ClientItem item) {
		this.item = item;
	}

	public int getPORT() {
		return PORT;
	}
	
	
}
