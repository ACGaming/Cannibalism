package sorazodia.cannibalism.items;

import java.util.List;
import java.util.UUID;

import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.common.util.EnumHelper;
import sorazodia.cannibalism.main.Cannibalism;
import sorazodia.cannibalism.mob.EntityWendigo;

/**
 * Wa ha ha, My own special knife :P #evilDev
 * 
 * @author SoraZodia
 */
public class ItemDevKnife extends ItemKnife
{

	private static final ToolMaterial dev = EnumHelper.addToolMaterial("Dev", 4, -1, 100.0F, Float.MAX_VALUE, 5);

	public ItemDevKnife()
	{
		super(dev);
	}

	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
	{
		player.setItemInUse(stack, getMaxItemUseDuration(stack));

		if (!world.isRemote)
		{
			if (player.isSneaking() && player.getUniqueID().equals(UUID.fromString("f10820b2-ad08-4b82-aca2-75b0445b6a1f")))
			{
				EntityWendigo wendigo = (EntityWendigo) EntityList.createEntityByName(Cannibalism.MODID
						+ ".wendigo", world);
				wendigo.setLocationAndAngles(player.posX, player.posY, player.posZ, 0, 0);
				world.spawnEntityInWorld(wendigo);
			}
		}

		return stack;
	}

	@Override
	public boolean itemInteractionForEntity(ItemStack stack, EntityPlayer player, EntityLivingBase target)
	{
		if (player.worldObj.isRemote)
		{
			player.addChatMessage(new ChatComponentTranslation("item.devKnife.format"));
			player.addChatMessage(new ChatComponentTranslation("item.devKnife.mobName", EntityList.getEntityString(target)));
		}
		return true;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4)
	{
		list.add(StatCollector.translateToLocal("item.devKnife.lore1"));
		list.add(StatCollector.translateToLocal("item.devKnife.lore2"));
	}

}
