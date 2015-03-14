package me.thelethalhamster.radium.module.modules;

import me.thelethalhamster.radium.module.Category;
import me.thelethalhamster.radium.module.Module;
import me.thelethalhamster.radium.module.ModuleManager;

public class AllAura extends Module{

	public AllAura() {
		super("AllAura", 0, Category.COMBAT, true);
	}
	
	public void onEnable(){
		ModuleManager.getInstance().getModule(KillAura.class).toggleModule();
		ModuleManager.getInstance().getModule(MobAura.class).toggleModule();
		ModuleManager.getInstance().getModule(AnimalAura.class).toggleModule();
	}
	
	public void onDisable(){
		ModuleManager.getInstance().getModule(KillAura.class).toggleModule();
		ModuleManager.getInstance().getModule(MobAura.class).toggleModule();
		ModuleManager.getInstance().getModule(AnimalAura.class).toggleModule();
	}
}
