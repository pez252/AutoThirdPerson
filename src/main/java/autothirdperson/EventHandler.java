package autothirdperson;

import autothirdperson.Logger;
import autothirdperson.ATPConfig;

import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;

import net.minecraft.client.Minecraft;
import net.minecraft.world.World;
import net.minecraft.client.entity.EntityPlayerSP;

import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.AbstractHorse;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.item.EntityBoat;

public class EventHandler {

	public static final EventHandler INSTANCE = new EventHandler();

	public final Minecraft minecraft = Minecraft.getMinecraft();

	public static int lastRiding = 0;
	public static int lastView = 0;

	@SubscribeEvent
	public void onClientTick(ClientTickEvent event){

		final World world = this.minecraft.world;
		if (world == null) {
			return;
		}

		final EntityPlayerSP player = this.minecraft.player;
		if (player == null) {
			return;
		}

		if (player.isElytraFlying() && ATPConfig.enabledElytra) {
			if (lastRiding == -1) {
				// Already flying. Do nothing.
				return;
			} else {
				lastRiding = -1;
				lastView = minecraft.gameSettings.thirdPersonView;
				minecraft.gameSettings.thirdPersonView=1;
				Logger.info("Elytra flying. View mode set to 1.");
				return;
			}
		} else if (player.isRiding()) {
			final Entity ridingEntity = player.getRidingEntity();
			if (lastRiding == ridingEntity.getEntityId()) {
				// Riding same entity. Do nothing.
				return;
			} else {
				// Riding a new entity. Find out what type and take action.
				if (lastRiding < 1 ) {
					// We were not riding an entity before. Save the current view to be restored later.
					lastView = minecraft.gameSettings.thirdPersonView;
				}
				lastRiding = ridingEntity.getEntityId();

				if (ridingEntity instanceof AbstractHorse && ATPConfig.enabledHorse) {
					minecraft.gameSettings.thirdPersonView=1;
					Logger.info("Riding a horse. View mode set to 1.");
					return;
				}
				if (ridingEntity instanceof EntityMinecart && ATPConfig.enabledMinecart) {
					minecraft.gameSettings.thirdPersonView=1;
					Logger.info("Riding a minecart. View mode set to 1.");
					return;
				}
				if (ridingEntity instanceof EntityBoat && ATPConfig.enabledBoat) {
					minecraft.gameSettings.thirdPersonView=1;
					Logger.info("Ridding a boat. View mode set to 1.");
					return;
				}
				if (ridingEntity instanceof EntityPig && ATPConfig.enabledPig) {
					minecraft.gameSettings.thirdPersonView=1;
					Logger.info("Riding a pig. View mode set to 1.");
					return;
				}

				// We're riding an unknown entity or a disabled one. Restore the previous view.
				minecraft.gameSettings.thirdPersonView = lastView;
			}
		} else {
			if (lastRiding == 0) {
				return;
			} else {
				lastRiding = 0;
				minecraft.gameSettings.thirdPersonView=lastView;
				Logger.info("Stopped riding. View mode set back to "+String.valueOf(lastView));
			}
		}
		return;

	}
}
