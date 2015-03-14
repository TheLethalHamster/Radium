package me.thelethalhamster.radium.module.modules;

import me.thelethalhamster.radium.module.Category;
import me.thelethalhamster.radium.module.Module;
import net.minecraft.network.play.client.C03PacketPlayer;

public class NoFall extends Module{

	public NoFall() {
		super("NoFall", 0, Category.PLAYER, true);
	}
	
	@Override
	public void onMotionUpdate(){
		if (!getPlayer().onGround){
			getPlayer().sendQueue.addToSendQueue(new C03PacketPlayer(true));
		}
	}
	
}
