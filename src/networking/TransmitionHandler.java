package networking;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Scanner;

public class TransmitionHandler {

	public void sendFile(File file,OutputStream out)
	{
		if(file.isDirectory() || !file.exists())
			return ;
		
		String fileName=file.getName();
		
		System.out.println("Sending file .. "+fileName);
		
		try {
			PrintWriter pout=new PrintWriter(out);
			//out.write("RECIEVE_FILE".getBytes());
			pout.println("RECIEVE_FILE");
			pout.flush();
			pout.println(fileName);
			pout.flush();
			
			//sending actually file content.
			Scanner in=new Scanner(file);
			while(in.hasNextLine())
			{
				pout.println(in.nextLine());
				pout.flush();
			}
			pout.println("END_OF_FILE");
			pout.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void recieveFile(InputStream in,Scanner sin)
	{
		System.out.println("Recieving file .. ");
		byte data[]=new byte[100];
		try {
			//Scanner sin=new Scanner(in);
			
			//in.read(data,0,100);
			String recived=sin.nextLine();//new String(data);
			System.out.println("	: "+recived);
			
			File file=new File("D:\\tresk\\"+recived);
			if(!file.exists())
				file.createNewFile();
			
			FileWriter fwr=new FileWriter(file);
			while(sin.hasNextLine())
			{
				String rec=sin.nextLine();
				if(rec.contains("END_OF_FILE"))
					break;
				fwr.write(rec+"\n");
			}
			fwr.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("-- Recieved --");
	}
	
}
