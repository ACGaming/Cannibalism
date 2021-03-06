package sorazodia.cannibalism.mechanic.events;

import net.minecraft.entity.EntityList;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;
import sorazodia.cannibalism.config.ConfigHandler;
import sorazodia.cannibalism.main.Cannibalism;
import sorazodia.cannibalism.mechanic.nbt.CannibalismNBT;
import sorazodia.cannibalism.mob.EntityWendigo;

public class EntityNBTEvents
{

	@SubscribeEvent
	public void playerUpdate(PlayerTickEvent event)
	{
		if (ConfigHandler.allowMyth())
		{
			EntityPlayer player = event.player;
			CannibalismNBT nbt = CannibalismNBT.getNBT(player);
			float wendigoLevel = nbt.getWendigoValue();
			if (wendigoLevel < 100)
			{
				nbt.setWarningEffect(true);
				nbt.setWedigoSpawn(false);
			}

			if (!event.player.world.isRemote)
				addServerEffect(player, wendigoLevel, nbt);;
		}
	}
	
	private void addServerEffect(EntityPlayer player, float wendigoLevel, CannibalismNBT nbt)
	{
		if (wendigoLevel >= 25 && wendigoLevel < 100)
		{
			player.addPotionEffect(new PotionEffect(MobEffects.SPEED, 10));
			player.addExhaustion(0.02F);
		}
		if (wendigoLevel >= 50)
		{
			player.addPotionEffect(new PotionEffect(MobEffects.SPEED, 10, 1));
			player.addExhaustion(0.04F);
		}
		if (wendigoLevel >= 100)
		{
			if (nbt.doWarningEffect())
			{
				player.addPotionEffect(new PotionEffect(MobEffects.BLINDNESS, 100));
				player.addPotionEffect(new PotionEffect(MobEffects.NAUSEA, 100));
				nbt.setWarningEffect(false);
			}	
			
			//TODO player attack cooldown, remember that the timer needs to be increased and not reset
			player.getCooldownTracker().setCooldown(player.getHeldItem(player.getActiveHand()).getItem(), 0);
		}
		if (wendigoLevel >= 150)
		{
			player.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 10, 2));
			player.addExhaustion(0.08F);
		}
		if (wendigoLevel >= 240)
		{
			player.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 10, 1));
		}
		if (wendigoLevel >= 250 && nbt.wendigoSpawned() == false)
		{
			player.world.playSound(null, player.getPosition(), SoundEvents.ENTITY_WOLF_HOWL, SoundCategory.HOSTILE, 1, 0.5F);
			EntityWendigo wendigo = (EntityWendigo) EntityList.createEntityByIDFromName(new ResourceLocation(Cannibalism.MODID + ":wendigo"), player.world);
			wendigo.setLocationAndAngles(player.posX + 25, player.posY, player.posZ + 25, 0, 0);
			player.world.spawnEntity(wendigo);
			CannibalismNBT.getNBT(player).setWedigoSpawn(true);

		}
	}

}