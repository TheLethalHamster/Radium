package me.thelethalhamster.radium.command.commands;

import me.thelethalhamster.radium.command.Command;
import me.thelethalhamster.radium.file.FileManager;
import me.thelethalhamster.radium.module.Module;
import me.thelethalhamster.radium.module.ModuleManager;
import me.thelethalhamster.radium.wrapper.RadiumWrapper;

import org.lwjgl.input.Keyboard;

public class Bind extends Command
{

	public String getAlias() {
		return "bind";
	}

	public String getDescription() {
		return "Allows You To Bind And Unbind Keys From Modules";
	}

	public String getSyntax() {
		return ".bind set <Module Name> <Key> | .bind del <Module Name> | .bind clear";
	}

	public void onCommand(String command, String[] args) throws Exception {
		if (args[0].equalsIgnoreCase("set"))
		{
			String modname = args[1];
			int key = Keyboard.getKeyIndex(args[2]);
			for(Module m : ModuleManager.getInstance().getModules()){
				if(m.getName().equalsIgnoreCase(modname)){
					m.setKeyBind(key);
					RadiumWrapper.getInstance().getRadiumUtil().addChatMessage(modname + " Has Successfully Been Binded To " + args[2]);
				}
			}
		}
		if (args[0].equalsIgnoreCase("del"))
		{
			String modname = args[1];
			for(Module m : ModuleManager.getInstance().getModules()){
				if(m.getName().equalsIgnoreCase(modname)){
					m.setKeyBind(0);
					RadiumWrapper.getInstance().getRadiumUtil().addChatMessage(modname + " Has Successfully Been Unbinded");
				}
			}
		}
		if (args[0].equalsIgnoreCase("clear"))
		{
			for(Module m : ModuleManager.getInstance().getModules()){
				m.setKeyBind(0);
			}
			RadiumWrapper.getInstance().getRadiumUtil().addChatMessage("All KeyBinds Have Successfully Been Cleared");
		}
		
		FileManager.getInstance().saveKeybinds();
	}
}
