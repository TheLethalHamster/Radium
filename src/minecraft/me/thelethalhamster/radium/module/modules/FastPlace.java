package me.thelethalhamster.radium.module.modules;

import me.thelethalhamster.radium.module.Category;
import me.thelethalhamster.radium.module.Module;
import net.minecraft.client.Minecraft;

public class FastPlace extends Module {

	public FastPlace() {
		super("FastPlace", 0, Category.PLAYER, true);
	}

	public void onMotionUpdate(){
		
		getMinecraft().rightClickDelayTimer = 0;
	}
}
