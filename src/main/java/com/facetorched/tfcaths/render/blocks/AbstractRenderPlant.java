package com.facetorched.tfcaths.render.blocks;

import com.bioxx.tfc.BlockSetup;
import com.bioxx.tfc.api.TFCBlocks;
import com.facetorched.tfcaths.blocks.BlockPlant;
import com.facetorched.tfcaths.enums.EnumVary;
import com.facetorched.tfcaths.util.AthsLogger;
import com.facetorched.tfcaths.util.AthsRandom;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.Random;

public abstract class AbstractRenderPlant implements ISimpleBlockRenderingHandler{
	public abstract boolean renderPlantBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer, Tessellator tessellator, int rgb, int meta, float scale, IIcon icon, Random random);
	
	public void renderSnow(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
		int meta = world.getBlockMetadata(x, y, z);
		if(block instanceof BlockPlant) {
			BlockPlant plant = (BlockPlant)block;
			if( plant.isVary(meta, EnumVary.SNOW)){
				renderer.setOverrideBlockTexture(BlockSetup.snow.getIcon(1, 0));
				renderer.setRenderBounds(0.0f, 0.0f, 0.0f, 1.0f, 0.125f, 1.0f);
				renderer.renderStandardBlockWithColorMultiplier(block, x, y, z, 1f, 1f, 1f);
				renderer.clearOverrideBlockTexture();
			}
		}
	}
	
	@SuppressWarnings("deprecation")
	public void renderLeaves(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
		Block left = world.getBlock(x - 1, y, z);
        if (left instanceof BlockLeaves)
        {
            RenderingRegistry.instance().renderWorldBlock(renderer, world, x, y, z, left, TFCBlocks.leavesFruitRenderId);
        }
	}
	
	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
		if(world.isSideSolid(x, y - 1, z, ForgeDirection.UP, true)) {
			renderSnow(world, x, y, z, block, modelId, renderer);
			renderLeaves(world, x, y, z, block, modelId, renderer);
		}
		
		Tessellator tessellator = Tessellator.instance;
		if(block.getLightValue() == 0.0f) {
			tessellator.setBrightness(block.getMixedBrightnessForBlock(world, x, y, z));
		}
		else {
			tessellator.setBrightness(0xF000B0);
		}
        int rgb = block.colorMultiplier(world, x, y, z);
        //System.out.println(rgb);
        tessellator.setColorOpaque_I(rgb);

        int meta = world.getBlockMetadata(x, y, z);
		IIcon icon = block.getIcon(0, meta);
		
		//add a bit of x z variation based on coords
		Random random = AthsRandom.getRandom(x, z);
		float scale = getRenderScale(block, random, meta);
		
		return renderPlantBlock(world, x, y, z, block, modelId, renderer, tessellator, rgb, meta, scale, icon, random);
	}
	
	/*
	 * random passed is supposed to have the same seed for each block
	 */
	public float getRenderScale(Block block, Random random, int meta) {
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
