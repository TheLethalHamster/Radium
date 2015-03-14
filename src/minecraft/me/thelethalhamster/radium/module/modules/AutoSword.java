package me.thelethalhamster.radium.module.modules;

import me.thelethalhamster.radium.module.Category;
import me.thelethalhamster.radium.module.Module;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;

public class AutoSword extends Module{

	public AutoSword() {
		super("AutoSword", 0, Category.COMBAT, true);
	}
	
	public void onAttackEntity(EntityPlayer par1EntityPlayer, Entity par2Entity){
		getBestWeapon(par2Entity);
	}
	
	public void getBestWeapon(Entity e){
		float damageModifier = 0;
		int newItem = -1;
		for (int slot = 0; slot < 9; slot++) 
		{
			ItemStack stack = getPlayer().inventory.mainInventory[slot];
			if(stack == null){
				continue;
			}
			if(stack.getItem() instanceof ItemSword){
				ItemSword is = (ItemSword)stack.getItem();
				float damage = is.func_150931_i()+ (is.hasEffect(stack) ? getEnchantDamageVsEntity(stack, e) : 0);
				if(damage >= damageModifier){
					newItem = slot;
					damageModifier = damage;
				}
			}
		}
		if (newItem > -1) 
		{ 
			getPlayer().inventory.currentItem = newItem; 
		}
	}

	public int getEnchantDamageVsEntity(ItemStack i, Entity e)
	{
		if(e instanceof EntityZombie || e instanceof EntityPigZombie || e instanceof EntitySkeleton)
		{
			return EnchantmentHelper.getEnchantmentLevel(Enchantment.sharpness.effectId, i) + EnchantmentHelper.getEnchantmentLevel(Enchantment.smite.effectId, i);
		}else
		if(e instanceof EntitySpider)
		{
			return EnchantmentHelper.getEnchantmentLevel(Enchantment.sharpness.effectId, i) + EnchantmentHelper.getEnchantmentLevel(Enchantment.baneOfArthropods.effectId, i);
		}else
		{
			return EnchantmentHelper.getEnchantmentLevel(Enchantment.sharpness.effectId, i);
		}
	}
	
}
