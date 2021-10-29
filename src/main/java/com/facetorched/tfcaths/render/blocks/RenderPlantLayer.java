package com.facetorched.tfcaths.render.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.util.ForgeDirection;

public class RenderPlantLayer extends AbstractRenderPlant{

	@Override
	public boolean renderPlantBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId,
			RenderBlocks renderer, Tessellator tessellator, int rgb, int meta, float scale, IIcon icon, Random random) {
		if (world.isSideSolid(x, y-1, z, ForgeDirection.UP, false))
		{
			renderer.setRenderBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.01F, 1.0F);
			renderer.renderStandardBlock(block, x, y, z);
			return true;
		}
		return false;
	}

	@Override
	public int getRenderId() {
		// TODO Auto-generated method stub
		return 0;
	}
}
