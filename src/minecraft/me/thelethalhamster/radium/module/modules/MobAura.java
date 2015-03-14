package me.thelethalhamster.radium.module.modules;

import me.thelethalhamster.radium.module.Category;
import me.thelethalhamster.radium.module.Module;
import me.thelethalhamster.radium.values.Value;
import net.minecraft.entity.monster.EntityMob;

import org.darkstorm.minecraft.gui.component.BoundedRangeComponent.ValueDisplay;

public class MobAura extends Module
{
	private int ticks = 0;

	public Value auraRange = new Value("MobAura Range", 4, 1, 6, ValueDisplay.DECIMAL);
	public Value auraSpeed = new Value("MobAura Speed", 14, 1, 20, ValueDisplay.INTEGER);
	
	public MobAura() {
		super("MobAura", 0, Category.COMBAT, true);
	}
	
	public void onMotionUpdate()
	{
		ticks++;
		if (ticks >= 20 - speed())
		{
			for (Object o: getWorld().loadedEntityList)
			{
				if (o instanceof EntityMob)
				{
					EntityMob e = (EntityMob) o;
					if (getPlayer().getDistanceToEntity(e) <= getRange())
					{
						getPlayer().swingItem();
						getMinecraft().playerController.attackEntity(getPlayer(), e);
						ticks = 0;
						break;
					}
				}
			}
		}
	}
	
	private int speed()
	{
		return (int)auraSpeed.getValue();
	}
	
	private float getRange()
	{
		return (float)auraRange.getValue();
	}
	
}
