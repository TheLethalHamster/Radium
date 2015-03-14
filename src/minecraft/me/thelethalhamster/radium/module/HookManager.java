package me.thelethalhamster.radium.module;

import me.thelethalhamster.radium.command.CommandManager;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.play.server.S02PacketChat;

public class HookManager {
	
	private static HookManager theHookManager;
	
	public static HookManager getInstance(){
		if(theHookManager == null) theHookManager = new HookManager();
		return theHookManager;
	}
	
	public void onGameTick(){//Minecraft.java
		for(Module m : ModuleManager.getInstance().getModules()){
			if(m.isState(true)){
				m.onGameTick();
			}
		}
	}
	
	public void preMotionUpdate(){//EntityClientPlayerMP.java
		for(Module m : ModuleManager.getInstance().getModules()){
			if(m.isState(true)){
				m.preMotionUpdate();
			}
		}
	}

	public void onMotionUpdate(){//EntityClientPlayerMP.java
		for(Module m : ModuleManager.getInstance().getModules()){
			if(m.isState(true)){
				m.onMotionUpdate();
			}
		}
	}
	
	public void postMotionUpdate(){//EntityClientPlayerMP.java
		for(Module m : ModuleManager.getInstance().getModules()){
			if(m.isState(true)){
				m.postMotionUpdate();
			}
		}
	}
	
	public void preAttackEntity(EntityPlayer par1EntityPlayer, Entity par2Entity){//PlayerControllerMP.java
		for(Module m : ModuleManager.getInstance().getModules()){
			if(m.isState(true)){
				m.preAttackEntity(par1EntityPlayer, par2Entity);
			}
		}
	}
	
	public void onAttackEntity(EntityPlayer par1EntityPlayer, Entity par2Entity){//PlayerControllerMP.java
		for(Module m : ModuleManager.getInstance().getModules()){
			if(m.isState(true)){
				m.onAttackEntity(par1EntityPlayer, par2Entity);
			}
		}
	}
	
	public void postAttackEntity(EntityPlayer par1EntityPlayer, Entity par2Entity){//PlayerControllerMP.java
		for(Module m : ModuleManager.getInstance().getModules()){
			if(m.isState(true)){
				m.postAttackEntity(par1EntityPlayer, par2Entity);
			}
		}
	}
	
	public void onRender(){//EntityRenderer.java
		for(Module m : ModuleManager.getInstance().getModules()){
			if(m.isState(true)){
				m.onRender();
			}
		}
	}
	
	public void onClickBlock(int i, int j, int k, int l){//PlayerControllerMP.java
		for(Module m : ModuleManager.getInstance().getModules()){
			if(m.isState(true)){
				m.onClickBlock( i, j, k, l);
			}
		}
	}
	
	public void onPlayerDeath(){//Minecraft.java
		for(Module m : ModuleManager.getInstance().getModules()){
			if(m.isState(true)){
				m.onPlayerDeath();
			}
		}
	}
	
	public void onPlayerRespawn(){//EntityClientPlayerMP.java
		for(Module m : ModuleManager.getInstance().getModules()){
			if(m.isState(true)){
				m.onPlayerRespawn();
			}
		}
	}
	
	public void onMiddleClick(){//Minecraft.java
		for(Module m : ModuleManager.getInstance().getModules()){
			if(m.isState(true)){
				m.onMiddleClick();
			}
		}
	}
	
	public GuiScreen onGuiScreen(GuiScreen guiscreen){//Minecraft.java
		for(Module m : ModuleManager.getInstance().getModules()){
			if(m.isState(true)){
				return m.onGuiScreen(guiscreen);
			}
		}
		return guiscreen;
	}
	
	public boolean onSendChatMessage(String s){//EntityClientPlayerMP
		if(s.startsWith(".")){
			CommandManager.getInstance().callCommand(s.substring(1));
			return false;
		}
		
		for(Module m: ModuleManager.getInstance().getModules()){
			if(m.isState(true)){
				return m.onSendChatMessage(s);
			}
		}
		return true;
	}
	
	public boolean onReceiveChatMessage(S02PacketChat packet){//NetHandlerPlayClient.java
		for(Module m : ModuleManager.getInstance().getModules()){
			if(m.isState(true)){
				return m.onReceiveChatMessage(packet);
			}
		}
		return true;
	}
	
	public void KeyBind(int i){//Minecraft.java
		for(Module m : ModuleManager.getInstance().getModules()){
			if(m.isKeyBind(i)){
				m.toggleModule();
				break;
			}
		}
	}
}