package me.thelethalhamster.radium.module.modules;

import me.thelethalhamster.radium.module.Category;
import me.thelethalhamster.radium.module.Module;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.potion.Potion;

public class AntiBlindness extends Module{

	public AntiBlindness() {
		super("AntiBlindness", 0, Category.MISC, true);
	}
	
	@Override
	public void onMotionUpdate(){
		getMinecraft().thePlayer.removePotionEffect(Potion.blindness.getId());
	}

}
