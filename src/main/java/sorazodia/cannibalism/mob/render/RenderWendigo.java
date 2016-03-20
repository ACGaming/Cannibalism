package sorazodia.cannibalism.mob.render;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import sorazodia.cannibalism.main.Cannibalism;
import sorazodia.cannibalism.mob.EntityWendigo;
import sorazodia.cannibalism.mob.mobel.ModelWendigo;

public class RenderWendigo extends RenderLiving<EntityWendigo>
{
	private ResourceLocation wendigoTexture = new ResourceLocation(Cannibalism.MODID, "textures/entity/wendigo.png");

	public RenderWendigo(RenderManager manager)
	{
		super(manager, new ModelWendigo(), 0.5F);
	}

	@Override
	public ResourceLocation getEntityTexture(EntityWendigo entity)
	{
		return wendigoTexture;
	}

}
