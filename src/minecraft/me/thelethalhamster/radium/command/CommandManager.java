package me.thelethalhamster.radium.command;

import java.util.ArrayList;

import me.thelethalhamster.radium.command.commands.*;
import me.thelethalhamster.radium.wrapper.RadiumWrapper;

public class CommandManager
{
	private static CommandManager theCommandManager = new CommandManager();
	
	public static CommandManager getInstance(){
		return theCommandManager;
	}
	
	private Command[] commands() {
		return new Command[] {
			new Bind(),
			new Help(),
		};
	}
	
	public CommandManager()
	{
		commands = new ArrayList();
		for (Command c: commands())
		{
			if (c != null)
			{
				commands.add(c);
			}
		}
	}
	
	public void callCommand(String input)
	{
		String[] split = input.split(" ");
		String command = split[0];
		String args = input.substring(command.length()).trim();
		for (Command c: getCommands())
		{
			if (c.getAlias().equalsIgnoreCase(command))
			{
				try
				{
					c.onCommand(args, args.split(" "));
				}
				catch (Exception e)
				{
					RadiumWrapper.getInstance().getRadiumUtil().addChatMessage("Invalid Usage! Correct Usage Is:");
					RadiumWrapper.getInstance().getRadiumUtil().addChatMessage(c.getSyntax());
				}
				return;
			}
		}
		
		RadiumWrapper.getInstance().getRadiumUtil().addChatMessage("Command \"" + command + "\" Not Found!");
	}
	
	public ArrayList<Command> getCommands()
	{
		return commands;
	}
	
	private ArrayList<Command> commands;
}
