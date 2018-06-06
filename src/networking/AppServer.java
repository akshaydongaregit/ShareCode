package networking;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class AppServer {

	final int PORT=2852;
	ServerSocket server;
	String status="stopped";
	
	public boolean startServer(){
		
		Thread t=new Thread(new Runnable()
		{
			@Override
			public void run() {
				try {
					server=new ServerSocket(PORT);
					System.out.println("Server is started on Port : "+PORT);
					while(true)
					{
						Socket con=server.accept();
						System.out.println("Client Connected");
						handleConnection(con);
					}
					/*server.close();
					System.out.println("Server stopped");*/
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		
		Thread t2=new Thread(new Runnable()
		{
			@Override
			public void run() {
				while(getStatus().equalsIgnoreCase("running"))
				{
				
				}
				t.stop();
				try {
					server.close();
				}catch (IOException e) {
				
					e.printStackTrace();
				}
				System.out.println("Server stopped");
			}
		});
		
		t.start();
		t2.start();
		this.setStatus("running");
		
		return true;
	}

	public ServerSocket getServer() {
		return server;
	}

	public void setServer(ServerSocket server) {
		this.server = server;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getPORT() {
		return PORT;
	}
	public void handleConnection(Socket con)
	{
		System.out.println("Handling connection");
	}
}
