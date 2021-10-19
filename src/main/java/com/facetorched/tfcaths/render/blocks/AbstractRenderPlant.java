package com.facetorched.tfcaths.render.blocks;

import java.util.Random;

import com.dunk.tfc.BlockSetup;
import com.dunk.tfc.Blocks.Flora.BlockLeafLitter;
import com.dunk.tfc.api.TFCBlocks;
import com.facetorched.tfcaths.blocks.BlockPlant;
import com.facetorched.tfcaths.enums.EnumVary;
import com.facetorched.tfcaths.util.AthsLogger;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.world.IBlockAccess;

public abstract class AbstractRenderPlant implements ISimpleBlockRenderingHandler{
	public void renderSnow(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
		int meta = world.getBlockMetadata(x, y, z);
		if(block instanceof BlockPlant) {
			BlockPlant plant = (BlockPlant)block;
			if( plant.isVary(meta, EnumVary.SNOW)){
				renderer.setOverrideBlockTexture(BlockSetup.snow.getIcon(1, 0));
				renderer.setRenderBounds(0.0f, 0.0f, 0.0f, 1.0f, 0.125f, 1.0f);
				renderer.renderStandardBlock(block, x, y, z);
				renderer.clearOverrideBlockTexture();
			}
		}
	}
	
	public void renderLeaves(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
		Block left = world.getBlock(x - 1, y, z);
        if (left instanceof BlockLeafLitter)
        {
            RenderingRegistry.instance().renderWorldBlock(renderer, world, x, y, z, left, TFCBlocks.leafLitterRenderId);
        }
	}
	
	/*
	 * random passed is supposed to have the same seed for each block
	 */
	public float getScale(Block block, Random random, int meta) {
		float scale = 1.0F;
		try {
			scale = ((BlockPlant)block).getScale();
		}
		catch(ClassCastException e) {
			AthsLogger.error(e);
		}
		return scale;
	}
	
	@Override
	public boolean shouldRender3DInInventory(int modelId) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) {
		// TODO Auto-generated method stub
	}
}