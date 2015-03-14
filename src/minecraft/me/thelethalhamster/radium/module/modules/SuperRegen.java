package me.thelethalhamster.radium.module.modules;

import me.thelethalhamster.radium.module.Category;
import me.thelethalhamster.radium.module.Module;
import me.thelethalhamster.radium.values.Value;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

import org.darkstorm.minecraft.gui.component.BoundedRangeComponent.ValueDisplay;

public class SuperRegen extends Module{
	
	public Value regenSpeed = new Value("SuperRegen Speed", 2, 1, 1000, ValueDisplay.INTEGER);
	
	public SuperRegen() {
		super("SuperRegeneration", 0, Category.COMBAT, true);
	}
	
	@Override
	public void onMotionUpdate(){
		getPlayer().addPotionEffect(new PotionEffect(Potion.regeneration.getId(), 999999999, speed()));
	}
	
	@Override
	public void onDisable(){
		getPlayer().removePotionEffect(Potion.regeneration.getId());
	}
	
	private int speed()
	{
		return (int)regenSpeed.getValue();
	}
}
