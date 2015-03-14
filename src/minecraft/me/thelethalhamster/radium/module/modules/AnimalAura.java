package me.thelethalhamster.radium.module.modules;

import me.thelethalhamster.radium.module.Category;
import me.thelethalhamster.radium.module.Module;
import me.thelethalhamster.radium.values.Value;
import net.minecraft.client.entity.EntityOtherPlayerMP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityAnimal;

import org.darkstorm.minecraft.gui.component.BoundedRangeComponent.ValueDisplay;
import org.lwjgl.input.Keyboard;

public class AnimalAura extends Module
{
	private int ticks = 0;

	public Value auraRange = new Value("AnimalAura Range", 4, 1, 6, ValueDisplay.DECIMAL);
	public Value auraSpeed = new Value("AnimalAura Speed", 14, 1, 20, ValueDisplay.INTEGER);
	
	public AnimalAura() {
		super("AnimalAura", 0, Category.COMBAT, true);
	}
	
	public void onMotionUpdate()
	{
		ticks++;
		if (ticks >= 20 - speed())
		{
			for (Object o: getWorld().loadedEntityList)
			{
				if (o instanceof EntityAnimal)
				{
					EntityAnimal e = (EntityAnimal) o;
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
