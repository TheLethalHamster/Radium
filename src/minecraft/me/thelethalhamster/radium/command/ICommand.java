package me.thelethalhamster.radium.command;

public interface ICommand {
	String getAlias();
	String getDescription();
	String getSyntax();
	void onCommand(String command, String[] args) throws Exception;
}
