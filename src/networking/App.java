package networking;
import java.awt.FlowLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class App extends JFrame{
	
	JTextArea code;
	JTextField address;
	JButton btn;
	JPanel panel;
	JButton btnStart;

	//server vars.
	final int PORT=2852;
	ServerSocket server;
	App im;
	
	public App()
	{
		im=this;
		this.setSize(400, 400);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		add();
		this.setVisible(true);
		
	}
	private void add()
	{
		panel = new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.CENTER));
		panel.setSize(500,500);
		this.add(panel);
		
		code = new JTextArea(15, 35);
		JScrollPane scrollPane = new JScrollPane(code);
		code.setText("");
		panel.add(code);
		
		address=new JTextField(25);
		panel.add(address);
		
		btn=new JButton("Send");
		btn.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0) {
				im.send();
			}
	
		});
		
		panel.add(btn);
		
		btnStart=new JButton("StartServer");
		btnStart.addActionListener(new ActionListener()
				{

					@Override
					public void actionPerformed(ActionEvent arg0) {
						im.startServer();
					}
			
				});
		
		panel.add(btnStart);
	}
	
	public void send()
	{
		try {
			Socket con=new Socket(address.getText(),PORT);
			PrintWriter out=new PrintWriter(con.getOutputStream());
			out.write(code.getText());
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public boolean startServer(){
		try {
			server=new ServerSocket(PORT);
			System.out.println("Server is started on Port : "+PORT);
			Socket con=server.accept();
			System.out.println("Client Connected");
			handleConnection(con);
			server.close();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	//handling .
	public void handleConnection(Socket con)
	{
		try {
			Scanner in=new Scanner(con.getInputStream());
			while(in.hasNext())
			{
				//System.out.println(in.next());
				code.setText(code.getText()+in.nextLine());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String args[])
	{
		App app=new App();
		
	}
}
