package me.thelethalhamster.radium.module.modules;

import org.darkstorm.minecraft.gui.component.BoundedRangeComponent.ValueDisplay;

import me.thelethalhamster.radium.module.Category;
import me.thelethalhamster.radium.module.Module;
import me.thelethalhamster.radium.values.Value;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;

public class AimBot extends Module{
	
	public Value aimbotRange = new Value("AimBot Range", 10, 5, 500, ValueDisplay.INTEGER);

	public AimBot() {
		super("AimBot", 0, Category.COMBAT, true);
	}

	@Override
	public void onMotionUpdate(){
		for(Object o: getWorld().loadedEntityList) {
			if(o instanceof EntityPlayer) {
				EntityPlayer e = (EntityPlayer) o;
				boolean checks = !(e instanceof EntityPlayerSP) && getPlayer().getDistanceToEntity(e) <= range() && getPlayer().canEntityBeSeen(e) && !e.isDead;
				if(checks) {
					faceEntity(e);
				}
			}
		}
	}
	
	public void faceEntity(Entity entity)
    {
		double x = entity.posX - getPlayer().posX;
		double z = entity.posZ - getPlayer().posZ;
		double y = entity.posY + (entity.getEyeHeight()/1.4D) - getPlayer().posY + (getPlayer().getEyeHeight()/1.4D);
		double helper = MathHelper.sqrt_double(x * x + z * z);

		float newYaw = (float)((Math.toDegrees(-Math.atan(x / z))));
		float newPitch = (float)-Math.toDegrees(Math.atan(y / helper));

		if(z < 0 && x < 0) { newYaw = (float)(90D + Math.toDegrees(Math.atan(z / x))); }
		else if(z < 0 && x > 0) { newYaw = (float)(-90D + Math.toDegrees(Math.atan(z / x))); }

		getPlayer().rotationYaw = newYaw; 
		getPlayer().rotationPitch = newPitch;
		getPlayer().rotationYawHead = newPitch;
    }
	
	private int range(){
		return (int)aimbotRange.getValue();
	}
}
