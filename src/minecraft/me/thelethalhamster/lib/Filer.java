package me.thelethalhamster.lib;

import java.io.*;
import java.util.ArrayList;

import net.minecraft.client.Minecraft;
/*
 * @author Ensalada
 */
public class Filer
{
	protected Minecraft mc;
	private String fileName;
	private File path;
	
	public Filer(String fileName, String clientName)
	{
		mc = Minecraft.getMinecraft();
		fileName = fileName + ".txt";
		this.fileName = fileName;
		this.path = new File(mc.mcDataDir.getAbsolutePath() + File.separator + clientName + File.separator);
		if (!path.exists())
		{
			try {
				path.mkdir();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public final ArrayList<String> read()
	{
		ArrayList<String> list = new ArrayList<String>();
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new DataInputStream(new FileInputStream(new File(path, fileName).getAbsolutePath()))));
			while(true)
			{
				String text = br.readLine();
				if (text != null)
					list.add(text.trim());
				else
					break;
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public void write(String text)
	{
		write(new String[]{text});
	}
	
	public void write(String[] text)
	{
		if (text == null || text.length == 0 || text[0].trim() == "")
			return;
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(new File(path, fileName), true));
			for (String line: text)
			{
				bw.write(line);
				bw.write("\r\n");
			}
			bw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void remove(int line)
	{
		ArrayList<String> file = read();
		if (file.size() < line)
			return;
		clear();
		int loop = 1;
		for (String text: file)
		{
			if (loop != line)
				write(text);
			loop++;
		}
	}
	
	public void clear()
	{
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(new File(path, fileName)));
			bw.write("");
			bw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}