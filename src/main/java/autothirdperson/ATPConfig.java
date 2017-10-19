package autothirdperson;

import autothirdperson.Reference;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Config(modid = Reference.MODID)
public class ATPConfig {

	@Config.Comment("Enabled in boats")
	public static boolean enabledBoat = true;

	@Config.Comment("Enabled flying with elytra")
	public static boolean enabledElytra=true;

	@Config.Comment("Enabled on horses (and their ilk)")
	public static boolean enabledHorse=true;

	@Config.Comment("Enabled in minecarts")
	public static boolean enabledMinecart=true;

	@Config.Comment("Enabled on pigs")
	public static boolean enabledPig=true;

	@Mod.EventBusSubscriber(modid = Reference.MODID)
	private static class EventHandler {
		@SubscribeEvent
		public static void onConfigChanged(final ConfigChangedEvent.OnConfigChangedEvent event) {
			if (event.getModID().equals(Reference.MODID)) {
				ConfigManager.sync(Reference.MODID, Config.Type.INSTANCE);
			}
		}
	}
}
