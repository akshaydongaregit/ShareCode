package networking;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.File;
import java.net.Socket;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ClientInterface extends JFrame{

	Dimension windowSize=new Dimension(600,800);
	JPanel back=new JPanel();
	TButton close;
	TButton send;
	JPanel sentList=new JPanel();
	JPanel sendPanel=new JPanel();
	JTextField txtFile;
	FileItem []files=new FileItem[10];
	int fileCount=0;
	Client client=null;
	
	public ClientInterface() {
		initSelf();
		initComp();
		initSentList();
		initConnectPanel();
		
		this.setVisible(true);
	}
	
	
	@Override
	public void paint(Graphics g)
	{
		super.paint(g);
		
		Graphics2D g2d=(Graphics2D)g;
		g2d.setStroke(new BasicStroke(5));
		g2d.setColor(new Color(88,88,222));
		g2d.drawRect(0, 0,(int)windowSize.getWidth(),(int)windowSize.getHeight());
	}

	private void initSentList()
	{
		Dimension conListSize=new Dimension(500,540);
		sentList.setSize(conListSize);
		sentList.setLocation(50,80);
		sentList.setBackground(new Color(255,255,255,255));
		sentList.setLayout(null);
		back.add(sentList);
	}
	

	private void initConnectPanel()
	{
		//configure connect panel.
		Dimension connectPanelSize=new Dimension((int) windowSize.getWidth(),100);
		sendPanel.setSize(connectPanelSize);
		sendPanel.setLocation(0,(int) (windowSize.getHeight()-connectPanelSize.height));
		sendPanel.setBackground(new Color(81,222,62,250));
		sendPanel.setLayout(null);
		back.add(sendPanel);
		
		//configure address field.
		txtFile=new JTextField("");
		txtFile.setBackground(new Color(255,255,255));
		txtFile.setBorder(BorderFactory.createLineBorder(new Color(0,0,0,0)));
		txtFile.setFont(new Font("Calibri",Font.PLAIN,16));
		txtFile.setForeground(new Color(0,0,200));
		sendPanel.add(txtFile);
		txtFile.setBounds(40,30, 320,40);
		
		
		//configure connect button.
		send=new TButton("Send");
		send.setActionListener(new TButtonActionListener() {
			@Override
			public void onAction() {
				send();
			}
		});
				TLayout layout=TLayout.getDefaultLayout();
				layout.setBackground(new Color(81,62,222));
				layout.setColor(new Color(255,255,255));
				layout.setFont(new Font("Calibri",Font.BOLD,20));
				send.setTLayout(layout);
				send.setSize(190,40);
				sendPanel.add(send);
				send.setLocation(360,30);
	}
	
	private void initComp()
	{
		//configure close button
		close=new TButton("X");
		close.setActionListener(new TButtonActionListener() {
			@Override
			public void onAction() {
				System.exit(0);
			}
		});
	
		close.setSize(40,40);
		back.add(close);
		close.setLocation(550,10);
	}
	
	private void initSelf()
	{
		this.setTitle("Share Easily - Akshay");
		this.setSize(windowSize);
		this.setLocation(100,-10);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setUndecorated(true);
		this.setBackground(new Color(0,0,0,0));
		
		back.setSize(windowSize);
		back.setLocation(0,0);
		back.setBackground(new Color(0,0,100,200));
		back.setLayout(null);
		this.add(back);
	}	
	
	private void addFile(FileItem item)
	{
		if(!item.getFile().exists())
			return;
		
		files[fileCount]=item;
		fileCount++;
		
		TLayout layout=TLayout.getDefaultLayout();
		layout.setBackground(new Color(81,62,222));
		layout.setColor(new Color(255,255,255));
		layout.setFont(new Font("Calibri",Font.BOLD,20));
		item.setTLayout(layout);
		item.setSize(190,40);
		sentList.add(item);
		item.setLocation(30,10+(fileCount-1)*42);
		
		sendFile(item.getFile());
	}
	private void sendFile(File file)
	{
		
	}
	private void send()
	{
		File file=new File(txtFile.getText());
		FileItem item=new FileItem(file.getName());
		item.setFile(file);
		addFile(item);
	}
	
	public Client getClient() {
		return client;
	}


	public void setClient(Client client) {
		this.client = client;
	}


	public static void main(String[] args) {
		ClientInterface inter=new ClientInterface();
	}
	
}
