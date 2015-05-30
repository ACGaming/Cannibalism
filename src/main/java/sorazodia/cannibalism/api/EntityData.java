package sorazodia.cannibalism.api;

import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.world.World;

public class EntityData
{
	private Item[] drops;
	private float minDamage;
	private float maxDamage;
	private String name;

	public EntityData(String[] drop, float min, float max)
	{
		this("", drop, min, max);
	}

	public EntityData(String entityName, String[] drop, float min, float max)
	{
		drops = covertString(drop);
		minDamage = min;
		maxDamage = max;
		setName(entityName);
	}

	private Item[] covertString(String[] convert)
	{
		Item[] drops = new Item[convert.length];
		for (int x = 0; x < drops.length; x++)
		{
			drops[x] =  (Item) Item.itemRegistry.getObject(convert[x]);
		}

		return drops;
	}

	public Item[] getDrops()
	{
		return drops;
	}

	public void setDrops(Item[] drops)
	{
		this.drops = drops;
	}

	public float getMinDamage()
	{
		return minDamage;
	}

	public void setMinDamage(float minDamage)
	{
		this.minDamage = minDamage;
	}

	public float getMaxDamage()
	{
		return maxDamage;
	}

	public void setMaxDamage(float maxDamage)
	{
		this.maxDamage = maxDamage;
	}

	public EntityLivingBase getEntity(World world)
	{
		return (EntityLivingBase) EntityList.createEntityByName(name, world);
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

}