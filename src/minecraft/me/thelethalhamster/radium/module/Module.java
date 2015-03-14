package me.thelethalhamster.radium.module;

import me.thelethalhamster.radium.wrapper.RadiumWrapper;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.play.server.S02PacketChat;

public class Module extends RadiumWrapper{

	private String name;
	private int keybind;
	private Category category;
	private boolean isvisible;
	private boolean state;
	private boolean save = true;
	
	public Module(String name, int key, Category category, boolean isvisible){
		this.setName(name);
		this.setKeyBind(key);
		this.setCategory(category);
		this.setVisible(isvisible);
		getRadiumUtil().printToConsole("All Modules Have Successfully Been Launched.");
	}
	
	public final void setName(String name){
		this.name = name;
	}
	
	public final void setKeyBind(int i){
		this.keybind = i;
	}
	
	public final void setCategory(Category category){
		this.category = category;
	}
	
	public final void setVisible(boolean b){
		this.isvisible = b;
	}
	
	public final void setState(boolean b){
		this.state = b;
		if(getState()) {
			onEnable();
		} else {
			onDisable();
		}
	}
	
	public final void setSavable(boolean b){
		this.save = b;
	}
	
	public final boolean isName(String s){
		if(s.equals(name))return true;
		return false;
	}
	
	public final boolean isKeyBind(int s){
		if(s == (keybind))return true;
		return false;
	}
	
	public final boolean isCategory(Category s){
		if(s == category)return true;
		return false;
	}
	
	public final boolean isVisible(boolean s){
		if(s == isvisible)return true;
		return false;
	}
	
	public final boolean isState(boolean s){
		if(s == state)return true;
		return false;
	}
	
	public final boolean isSavable(boolean s){
		if(s == save)return true;
		return false;
	}
	
	public final String getName(){
		return name;
	}
	
	public final int getKeyBind(){
		return keybind;
	}
	
	public final Category getCategory(){
		return category;
	}
	
	public final boolean getVisible(){
		return isvisible;
	}
	
	public final boolean getState(){
		return state;
	}
	
	public final boolean getSavable(){
		return save;
	}

	public void onToggle() {}
	
	public void onEnable() {}
	
	public void onDisable(){}
	
	public void onGameTick(){}
	
	public void preMotionUpdate(){}
	
	public void onMotionUpdate(){}
	
	public void postMotionUpdate(){}
	
	public void preAttackEntity(EntityPlayer par1EntityPlayer, Entity par2Entity){}
	
	public void onAttackEntity(EntityPlayer par1EntityPlayer, Entity par2Entity){}
	
	public void postAttackEntity(EntityPlayer par1EntityPlayer, Entity par2Entity){}
	
	public void onRender(){}
	
	public void onClickBlock(int i, int j, int k, int l){}
	
	public void onPlayerDeath(){}
	
	public void onPlayerRespawn(){}
	
	public void onMiddleClick(){}
	
	public GuiScreen onGuiScreen(GuiScreen guiscreen){
		return guiscreen;
	}
	
	public boolean onSendChatMessage(String s){
		return true;
	}
	
	public boolean onReceiveChatMessage(S02PacketChat packet){
		return true;
	}
	
	public final void toggleModule(){
		setState(!state);
		onToggle();
	}
}