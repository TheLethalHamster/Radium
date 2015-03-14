package me.thelethalhamster.radium.module.modules;

import me.thelethalhamster.radium.module.Category;
import me.thelethalhamster.radium.module.Module;
import me.thelethalhamster.radium.values.Value;
import net.minecraft.client.entity.EntityOtherPlayerMP;
import net.minecraft.entity.Entity;

import org.darkstorm.minecraft.gui.component.BoundedRangeComponent.ValueDisplay;

public class KillAura extends Module
{
	private int ticks = 0;

	public Value auraRange = new Value("KillAura Range", 4, 1, 6, ValueDisplay.DECIMAL);
	public Value auraSpeed = new Value("KillAura Speed", 14, 1, 20, ValueDisplay.INTEGER);
	
	public KillAura() {
		super("KillAura", 0, Category.COMBAT, true);
	}
	
	public void onMotionUpdate()
	{
		ticks++;
		if (ticks >= 20 - speed())
		{
			for (Object o: getWorld().loadedEntityList)
			{
				if (o instanceof EntityOtherPlayerMP)
				{
					Entity e = (Entity) o;
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
