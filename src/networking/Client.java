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
			Socket con=new Socket(address,PORT);
			System.out.println("Connected to "+address);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
}
