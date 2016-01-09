package sorazodia.cannibalism.mechanic.events;

import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import sorazodia.cannibalism.config.ConfigHandler;
import sorazodia.cannibalism.main.Cannibalism;
import sorazodia.cannibalism.mechanic.nbt.CannibalismNBT;
import sorazodia.cannibalism.mob.EntityWendigo;

public class EntityNBTEvents
{

	@SubscribeEvent
	public void entityCreateEvent(EntityConstructing create)
	{
		if (ConfigHandler.getMyth() == true && (create.entity instanceof EntityPlayer) && CannibalismNBT.getNBT((EntityLivingBase) create.entity) == null)
		{
			CannibalismNBT.register((EntityLivingBase) create.entity);
		}
	}

	@SubscribeEvent
	public void playerUpdate(LivingUpdateEvent updateEvent)
	{
		if (!updateEvent.entityLiving.worldObj.isRemote && updateEvent.entityLiving instanceof EntityPlayer && ConfigHandler.getMyth() && CannibalismNBT.getNBT(updateEvent.entityLiving) != null)
		{
			EntityPlayer player = (EntityPlayer) updateEvent.entityLiving;
			float wendigoLevel = CannibalismNBT.getNBT(player).getWendigoValue();

			addWendigoAbility(player, wendigoLevel);
			wendigoSpawn(player, wendigoLevel, CannibalismNBT.getNBT(player));
		}
	}

	private void addWendigoAbility(EntityPlayer player, float wendigoLevel)
	{
		if (wendigoLevel >= 25 && wendigoLevel < 100)
		{
			player.addPotionEffect(new PotionEffect(Potion.moveSpeed.id, 10));
			player.addExhaustion(0.02F);
		}
		if (wendigoLevel >= 50)
		{
			player.addPotionEffect(new PotionEffect(Potion.moveSpeed.id, 10, 1));
			player.addExhaustion(0.04F);
		}
		if (wendigoLevel >= 150)
		{
			player.addPotionEffect(new PotionEffect(Potion.damageBoost.id, 10, 2));
			player.addExhaustion(0.08F);
		}
		if (wendigoLevel >= 240)
		{
			player.addPotionEffect(new PotionEffect(Potion.resistance.id, 10, 1));
		}
	}

	private void wendigoSpawn(EntityPlayer player, float wendigoLevel, CannibalismNBT nbt)
	{
		if (wendigoLevel >= 100 && nbt.doWarningEffect())
		{
			player.addPotionEffect(new PotionEffect(Potion.blindness.id, 100));
			player.addPotionEffect(new PotionEffect(Potion.confusion.id, 100));
			nbt.setWarningEffect(false);
		}

		if (wendigoLevel >= 250 && nbt.wendigoSpawned() == false)
		{
			EntityWendigo wendigo = (EntityWendigo) EntityList.createEntityByName(Cannibalism.MODID + ".wendigo", player.worldObj);
			wendigo.setLocationAndAngles(player.posX * 2, player.posY, player.posZ * 2, 0, 0);
			player.worldObj.spawnEntityInWorld(wendigo);
			CannibalismNBT.getNBT(player).setWedigoSpawn(true);

		}
	}

}