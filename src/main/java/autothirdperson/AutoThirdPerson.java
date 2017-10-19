package autothirdperson;

import autothirdperson.Reference;
import autothirdperson.EventHandler;
import autothirdperson.Logger;
import autothirdperson.ATPConfig;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;

@Mod(modid = Reference.MODID, version = Reference.VERSION, clientSideOnly = true)

public class AutoThirdPerson {

	@Mod.Instance(Reference.MODID)
	public static AutoThirdPerson INSTANCE;

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		Logger.setLogger(event.getModLog());
		MinecraftForge.EVENT_BUS.register(EventHandler.INSTANCE);
	}

	@Mod.EventHandler
	public void init(FMLInitializationEvent event) {
	}

	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event) {
	}
}
