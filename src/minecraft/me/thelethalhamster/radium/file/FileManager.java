package me.thelethalhamster.radium.file;

import me.thelethalhamster.lib.Filer;
import me.thelethalhamster.radium.module.Module;
import me.thelethalhamster.radium.module.ModuleManager;

import org.lwjgl.input.Keyboard;

public class FileManager {
	private static FileManager INSTANCE = new FileManager();
	
	private Filer theKeybinds = new Filer("keybinds", "Radium");
	
	public FileManager(){
		try{
			loadKeybinds();
		}catch(Exception e){
			
		}
	}
	
	public static FileManager getInstance(){
		return INSTANCE;
	}
	
	public void saveKeybinds() throws Exception{
		theKeybinds.clear();
		for(Module m: ModuleManager.getInstance().getModules()){
			String line = (m.getName() + ":" + Keyboard.getKeyName(m.getKeyBind())).toLowerCase();
			theKeybinds.write(line);
		}
	}
	
	private void loadKeybinds() throws Exception{
		for(String s: theKeybinds.read()){
			for(Module m: ModuleManager.getInstance().getModules()){
				String name = s.split(":")[0];
				int keybind = Keyboard.getKeyIndex(s.split(":")[1].toUpperCase());
				if(m.getName().equalsIgnoreCase(name)){
					m.setKeyBind(keybind);
				}
			}
		}
	}
}
