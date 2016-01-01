package sorazodia.cannibalism.items;

import sorazodia.cannibalism.config.ConfigHandler;
import sorazodia.cannibalism.mechanic.nbt.CannibalismNBT;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemFlesh extends ItemFood
{

	public ItemFlesh(int hunger, float saturation, boolean isWolfFood)
	{
		super(hunger, saturation, isWolfFood);
	}

	public ItemFlesh(int hunger, float saturation)
	{
		this(hunger, saturation, true);
	}

	@Override
	public void onFoodEaten(ItemStack stack, World world, EntityPlayer player)
	{
		if (CannibalismNBT.getNBT(player) != null && ConfigHandler.getMyth() == true && !world.isRemote)
		{
			CannibalismNBT nbt = CannibalismNBT.getNBT(player);
			float wendigoLevel = nbt.getWendigoValue();
			
			nbt.changeWendigoValue(10);
			player.getFoodStats().addStats((int)wendigoLevel / 10, wendigoLevel / 10);
		}
	}

}
