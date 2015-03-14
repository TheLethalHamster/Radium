package me.thelethalhamster.radium.command.commands;

import me.thelethalhamster.radium.command.Command;
import me.thelethalhamster.radium.command.CommandManager;
import me.thelethalhamster.radium.wrapper.RadiumWrapper;

public class Help extends Command
{

	public String getAlias() {
		return "help";
	}

	public String getDescription() {
		return "Supplies You With A List Of Useful Commands";
	}

	public String getSyntax() {
		return ".help";
	}

	public void onCommand(String full, String[] split) throws Exception {
		for (Command c: CommandManager.getInstance().getCommands()){
			RadiumWrapper.getInstance().getRadiumUtil().addChatMessage(c.getAlias() + " - " + c.getDescription());
		}
	}
}
