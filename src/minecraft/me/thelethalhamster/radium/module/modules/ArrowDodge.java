package me.thelethalhamster.radium.module.modules;

import me.thelethalhamster.radium.module.Category;
import me.thelethalhamster.radium.module.Module;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.projectile.EntityArrow;

public class ArrowDodge extends Module{

	public ArrowDodge() {
		super("ArrowDodge", 0, Category.COMBAT, true);
	}
	
	@Override
	public void onMotionUpdate(){
		try{
			for(Object o: getMinecraft().theWorld.loadedEntityList){
				if(o instanceof EntityArrow){
					EntityArrow e = (EntityArrow)o;
					if(getMinecraft().thePlayer.canEntityBeSeen(e) && getMinecraft().thePlayer.getDistanceSqToEntity(e) <= 20 && !e.isCollided){
						getMinecraft().thePlayer.motionX = 0.5D;
					}
				}
			}
		}catch(Exception e){
			
		}
	}

}
