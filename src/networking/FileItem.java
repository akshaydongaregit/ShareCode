package networking;

import java.io.File;

import javax.swing.JFrame;

public class FileItem extends TButton{
	
	File file;
	
	public FileItem()
	{
		super();
	}
	public FileItem(String str)
	{
		super(str);
	}
	public File getFile() {
		return file;
	}
	public void setFile(File path) {
		this.file = path;
	}
	
}
