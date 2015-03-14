package me.thelethalhamster.radium.module;

import java.util.ArrayList;

import me.thelethalhamster.radium.gui.ClickGUI;
import me.thelethalhamster.radium.module.modules.*;

public class ModuleManager {
	private ArrayList<Module> mods = new ArrayList<Module>();
	private static ModuleManager theModuleManager;
	
	public static ModuleManager getInstance(){
		if(theModuleManager == null) theModuleManager = new ModuleManager();
		return theModuleManager;
	}
	
	public ModuleManager(){
		initModules();
		Gui();
	}
	
	private void initModules(){
		getModules().add(new AimBot());
		getModules().add(new AllAura());
		getModules().add(new AnimalAura());
		getModules().add(new AnimalESP());
		getModules().add(new AntiBlindness());
		getModules().add(new AntiVelocity());
		getModules().add(new ArrowDodge());
		getModules().add(new ArrowESP());
		getModules().add(new Astronaut());
		getModules().add(new AutoArmor());
		getModules().add(new AutoBlock());
		getModules().add(new AutoEat());
		getModules().add(new AutoJump());
		getModules().add(new AutoMine());
		getModules().add(new AutoRespawn());
		getModules().add(new AutoSoup());
		getModules().add(new AutoSword());
		getModules().add(new AutoWalk());
		getModules().add(new BowAimbot());
		getModules().add(new BreadCrumb());
		getModules().add(new BunnyHop());
		getModules().add(new ChestESP());
		getModules().add(new ClickNuker());
		getModules().add(new Criticals());
		getModules().add(new Derp());
		getModules().add(new Dolphin());
		getModules().add(new FastLadder());
		getModules().add(new FastPlace());
		getModules().add(new Flight());
		getModules().add(new FullBright());
		getModules().add(new Glide());
		getModules().add(new GodMode());
		getModules().add(new HighJump());
		getModules().add(new JetPackFly());
		getModules().add(new JumpStep());
		getModules().add(new KillAura());
		getModules().add(new MobAura());
		getModules().add(new MobESP());
		getModules().add(new NoFall());
		getModules().add(new NoHurtCam());
		getModules().add(new Nuker());
		getModules().add(new Phase());
		getModules().add(new PlayerESP());
		getModules().add(new ProphuntESP());
		getModules().add(new Regen());
		getModules().add(new Sneak());
		getModules().add(new SpaceLadder());
		getModules().add(new SpeedMine());
		getModules().add(new Spider());
		getModules().add(new Sprint());
		getModules().add(new Step());
		getModules().add(new SuperRegen());
		getModules().add(new Timer());
		getModules().add(new Tracers());
		getModules().add(new Twerk());
	}
	
	private void Gui(){
		getModules().add(new ClickGUI());
	}

	public ArrayList<Module> getModules(){
		return mods;
	}
	
	public Module getModule(Class <? extends Module> clazz){
		for(Module mod: getModules())
		{
			if(mod.getClass() == clazz)
			{
				return mod;
			}
		}
		
		return null;
	}
}