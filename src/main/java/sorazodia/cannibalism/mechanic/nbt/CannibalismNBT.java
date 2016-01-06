package sorazodia.cannibalism.mechanic.nbt;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

public class CannibalismNBT implements IExtendedEntityProperties
{
	public static final String NBTNAME = "cannibalismVariables";
	private float wendigoLevel;
	private boolean spawnWendigo;
	private boolean applyEffect;

	public CannibalismNBT()
	{
		this.wendigoLevel = 0;
		this.spawnWendigo = false;
		this.applyEffect = true;
	}

	@Override
	public void saveNBTData(NBTTagCompound compound)
	{
		NBTTagCompound nbt = new NBTTagCompound();
		nbt.setFloat("wendigo", wendigoLevel);
		nbt.setBoolean("wendigoExist", spawnWendigo);
		nbt.setBoolean("applyWarningEffect", applyEffect);
		compound.setTag(NBTNAME, nbt);
	}

	@Override
	public void loadNBTData(NBTTagCompound compound)
	{
		NBTTagCompound nbt = compound.getCompoundTag(NBTNAME);
		wendigoLevel = nbt.getFloat("wendigo");
		spawnWendigo = nbt.getBoolean("wendigoExist");
		applyEffect = nbt.getBoolean("applyWarningEffect");
	}

	@Override
	public void init(Entity entity, World world)
	{
	}

	public void changeWendigoValue(float amount)
	{
		wendigoLevel += amount;
	}

	public void setWendigoValue(float amount)
	{
		wendigoLevel = amount;
	}

	public float getWendigoValue()
	{
		return wendigoLevel;
	}

	public static final CannibalismNBT getNBT(EntityLivingBase entity)
	{
		return (CannibalismNBT) entity.getExtendedProperties(NBTNAME);
	}

	public boolean wendigoSpawned()
	{
		return spawnWendigo;
	}

	public void setWedigoSpawn(boolean doSpawn)
	{
		spawnWendigo = doSpawn;
	}
	
	public boolean doWarningEffect()
	{
		return applyEffect;
	}

	public void setWarningEffect(boolean doApply)
	{
		applyEffect = doApply;
	}


	public static final void register(EntityLivingBase entity)
	{
		entity.registerExtendedProperties(NBTNAME, new CannibalismNBT());
	}

}
