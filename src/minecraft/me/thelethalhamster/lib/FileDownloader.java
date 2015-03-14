package me.thelethalhamster.lib;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
/*
 * @author ReesZRB
 */
public class FileDownloader extends Thread implements Runnable
{
	private File macDir;
	private String url;
	private String filename;
	
	public FileDownloader(File mcDir, String uri, String filen)
	{
		macDir = mcDir;
		url = uri;
		filename = filen;
	}

	public String getExtn(){
		return url.substring(url.lastIndexOf('.'), url.length());
	}
	
	public void run()
	{
		try
		{
			URL dlURL = (new URL(url));
			String fileNameExtn = url.substring(url.lastIndexOf('.'), url.length());
			URLConnection dlConnection = dlURL.openConnection();
			dlConnection.connect();
			BufferedInputStream in = new BufferedInputStream(dlURL.openStream());
			OutputStream out = new FileOutputStream(macDir + "/" + filename + fileNameExtn);
			int count;
			byte data[] = new byte[1024];
			while ((count = in.read(data)) != -1)
			{
				out.write(data, 0, count);
			}
			out.flush();
			out.close();
			in.close();
		}catch (Exception e) {}
	}
}
