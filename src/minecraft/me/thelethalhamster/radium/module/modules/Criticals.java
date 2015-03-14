package me.thelethalhamster.radium.module.modules;

import me.thelethalhamster.radium.module.Category;
import me.thelethalhamster.radium.module.Module;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;

import org.lwjgl.input.Keyboard;

public class Criticals extends Module{

	public Criticals() {
		super("Criticals", 0, Category.COMBAT, true);
	}
	
	@Override
	public void onAttackEntity(EntityPlayer par1EntityPlayer, Entity par2Entity){
		if(isState(true) && par1EntityPlayer instanceof EntityPlayerSP && getMinecraft().thePlayer.onGround)
    	{
			par1EntityPlayer.motionY = 0.125D;
			getMinecraft().thePlayer.onCriticalHit(par2Entity);
    	}
	}

}
