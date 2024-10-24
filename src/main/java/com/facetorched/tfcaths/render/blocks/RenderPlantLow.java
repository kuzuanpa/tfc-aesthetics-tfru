package com.facetorched.tfcaths.render.blocks;

import com.bioxx.tfc.api.TFCBlocks;
import com.facetorched.tfcaths.AthsBlockSetup;
import com.facetorched.tfcaths.blocks.BlockPlantLow;
import cpw.mods.fml.client.registry.RenderingRegistry;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;

import java.util.Random;

public class RenderPlantLow extends AbstractRenderPlant{

	@SuppressWarnings("deprecation")
	@Override
	public boolean renderPlantBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId,
			RenderBlocks renderer, Tessellator tessellator, int rgb, int meta, float scale, IIcon icon, Random random) {
		if(!(block instanceof BlockPlantLow)) {
			return false;
		}
		RenderingRegistry.instance().renderWorldBlock(renderer, world, x, y, z, TFCBlocks.fruitTreeLeaves, TFCBlocks.leavesFruitRenderId);
		BlockPlantLow plantLow = (BlockPlantLow)block;
        tessellator.setColorOpaque_I(rgb); //need to call again because we rendered the leaves...
		renderer.drawCrossedSquares(plantLow.sideIcons[meta], x, y, z, scale);
		
		
		int rotation = 0;
		// render each layer
		for(int i = 0; i < 2; i++) {
			float h = 0.251f - (0.125f * (float)i);
			
	        // randomly rotate (and make sure layers never line up)
	        rotation = (rotation + random.nextInt(4 - i)) % 4;
	        
	        AthsRenderBlocks.drawGroundLayer(icon, x, y, z, h, rotation, rgb);
	        
	        float shade = 0.75f;
	        rgb = (int)((rgb & 0xff0000)*shade) & 0xff0000 | 
	        	  (int)((rgb & 0x00ff00)*shade) & 0x00ff00|
	        	  (int)((rgb & 0x0000ff)*shade) & 0x0000ff;
	        rotation ++;
		}
		return false;
	}

	@Override
	public int getRenderId() {
		return AthsBlockSetup.plantLowRenderID;
	}
}
