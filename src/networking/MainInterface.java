package networking;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class MainInterface extends JFrame{

	Dimension windowSize=new Dimension(600,800);
	JPanel back=new JPanel();
	JPanel connectPanel=new JPanel();
	TButton close;
	TButton start;
	TButton stop;
	JTextField address;
	TButton connect;
	ClientList conList=new ClientList();
	
	AppServer server=new AppServer();
	Client clients[]=new Client[10];
	int clientCount=0;
	
	String userName="Akshay";
	
	public MainInterface()
	{
		initSelf();
		initComp();
		initConnectPanel();
		initConList();
		this.setVisible(true);
		
		server.setInterf(this);
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
	
	private void initConList()
	{
		Dimension conListSize=new Dimension(500,540);
		conList.setSize(conListSize);
		conList.setLocation(50,140);
		conList.setBackground(new Color(255,255,255,255));
		conList.setLayout(null);
		back.add(conList);
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
		
		//configure start button.
		start=new TButton("Start Server");
		start.setActionListener(new TButtonActionListener() {
			@Override
			public void onAction() {
				startServer();
			}
		});
		
		TLayout layout=TLayout.getDefaultLayout();
		layout.setBackground(new Color(81,62,222));
		layout.setColor(new Color(255,255,255));
		layout.setFont(new Font("Calibri",Font.BOLD,20));
		start.setTLayout(layout);
		
		start.setSize(250,40);
		back.add(start);
		start.setLocation(50,60);
		
		//configure stop button.
		stop=new TButton("Stop Server");
		stop.setActionListener(new TButtonActionListener() {
			@Override
			public void onAction() {
				stopServer();
			}
		});
		
		TLayout layout1=TLayout.getDefaultLayout();
		layout1.setBackground(new Color(244,0,22));
		layout1.setColor(new Color(255,255,255));
		layout1.setFont(new Font("Calibri",Font.BOLD,20));
		stop.setTLayout(layout1);
		
		stop.setSize(250,40);
		back.add(stop);
		stop.setLocation((int) (start.location().getX()+start.getWidth()),60);
		
		
	}
	
	private void initConnectPanel()
	{
		//configure connect panel.
		Dimension connectPanelSize=new Dimension((int) windowSize.getWidth(),100);
		connectPanel.setSize(connectPanelSize);
		connectPanel.setLocation(0,(int) (windowSize.getHeight()-connectPanelSize.height));
		connectPanel.setBackground(new Color(81,222,62,250));
		connectPanel.setLayout(null);
		back.add(connectPanel);
		
		//configure address field.
		address=new JTextField("localhost");
		address.setBackground(new Color(255,255,255));
		address.setBorder(BorderFactory.createLineBorder(new Color(0,0,0,0)));
		address.setFont(new Font("Calibri",Font.PLAIN,16));
		address.setForeground(new Color(0,0,200));
		connectPanel.add(address);
		address.setBounds(40,30, 320,40);
		
		//configure connect button.
				connect=new TButton("Connect");
				connect.setActionListener(new TButtonActionListener() {
					@Override
					public void onAction() {
						connect();
					}
				});
				TLayout layout=TLayout.getDefaultLayout();
				layout.setBackground(new Color(81,62,222));
				layout.setColor(new Color(255,255,255));
				layout.setFont(new Font("Calibri",Font.BOLD,20));
				connect.setTLayout(layout);
				
				connect.setSize(190,40);
				connectPanel.add(connect);
				connect.setLocation(360,30);
	}
	private void initSelf()
	{
		this.setTitle("Share Easily - Akshay");
		this.setSize(windowSize);
		this.setLocation(200,100);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setUndecorated(true);
		this.setBackground(new Color(0,0,0,0));
		
		back.setSize(windowSize);
		back.setLocation(0,0);
		back.setBackground(new Color(0,0,100,200));
		back.setLayout(null);
		this.add(back);
	}
	
	private void startServer()
	{
		if(server==null)
			server=new AppServer();
		if(server.getStatus().equalsIgnoreCase("stopped"))
			server.startServer();
		refreshInterface();
		
	}
	
	private void stopServer()
	{
		System.out.println("stopping server "+server.getStatus());
		if(server.getStatus().equalsIgnoreCase("running"))
			server.setStatus("stopped");
		//System.out.println("stopping server "+server.getStatus());
	}
	private void connect()
	{
		clients[clientCount]=new Client();
		clients[clientCount].connectTo(address.getText());
		clientCount++;
	}
	public void refreshInterface()
	{
		
	}
	public void addConnection(Client client)
	{
		clients[clientCount]=client;
		clientCount++;
		ClientItem item=client.getItem();
		
		//configure item.
			
				item.setActionListener(new TButtonActionListener() {
					@Override
					public void onAction() {
						System.exit(0);
					}
				});
			
				item.setSize(200,40);
				item.setLocation(50,10+clientCount*40);
				
		conList.add(item);
		conList.repaint();
		this.repaint();
	}
	public static void main(String[] args) {
		
		MainInterface interf=new MainInterface();

	}

}
