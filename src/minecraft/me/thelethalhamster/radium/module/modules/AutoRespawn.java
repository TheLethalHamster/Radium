package me.thelethalhamster.radium.module.modules;

import me.thelethalhamster.radium.module.Category;
import me.thelethalhamster.radium.module.Module;
import me.thelethalhamster.radium.wrapper.RadiumWrapper;

public class AutoRespawn extends Module{

	public AutoRespawn() {
		super("AutoRespawn", 0, Category.PLAYER, true);
	}
	
	@Override
	public void onPlayerDeath(){
		RadiumWrapper.getInstance().getMinecraft().thePlayer.respawnPlayer();
		return;
	}
}
