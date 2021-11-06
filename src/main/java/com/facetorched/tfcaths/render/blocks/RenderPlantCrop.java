package com.facetorched.tfcaths.render.blocks;

import java.util.Random;

import com.facetorched.tfcaths.AthsBlockSetup;
import com.facetorched.tfcaths.AthsGlobal;
import com.facetorched.tfcaths.blocks.BlockPlant;
import com.facetorched.tfcaths.util.AthsRandom;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;

public class RenderPlantCrop extends AbstractRenderPlant{

	public boolean renderPlantBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId,
			RenderBlocks renderer, Tessellator tesselator, int rgb, int meta, float scale, IIcon icon, Random random) {
		athsRenderBlockCrops(icon, x + random.nextDouble()/4.0, y, z + random.nextDouble()/4.0, scale, random);
		return false;
	}
	
	/**
     * Custom Implementation of Render block crops implementation (modified cpw's code)
     */
    public static void athsRenderBlockCrops(IIcon icon, double x, double y, double z, float scale, Random random)
    {
        Tessellator tessellator = Tessellator.instance;
        
        double d3;
        double d5;
        // randomly mirror the texture!
        if(random.nextBoolean()) {
        	d3 = (double)icon.getMinU();
            d5 = (double)icon.getMaxU();
        }
        else {
        	d3 = (double)icon.getMaxU();
            d5 = (double)icon.getMinU();
        }
        double d4 = (double)icon.getMinV();
        double d6 = (double)icon.getMaxV();
        double d7 = x + 0.5D - 0.25D;
        double d8 = x + 0.5D + 0.25D;
        double d9 = z + 0.5D - 0.5D * (double)scale;
        double d10 = z + 0.5D + 0.5D * (double)scale;
        tessellator.addVertexWithUV(d7, y + (double)scale, d9, d3, d4);
        tessellator.addVertexWithUV(d7, y + 0.0D, d9, d3, d6);
        tessellator.addVertexWithUV(d7, y + 0.0D, d10, d5, d6);
        tessellator.addVertexWithUV(d7, y + (double)scale, d10, d5, d4);
        tessellator.addVertexWithUV(d7, y + (double)scale, d10, d3, d4);
        tessellator.addVertexWithUV(d7, y + 0.0D, d10, d3, d6);
        tessellator.addVertexWithUV(d7, y + 0.0D, d9, d5, d6);
        tessellator.addVertexWithUV(d7, y + (double)scale, d9, d5, d4);
        tessellator.addVertexWithUV(d8, y + (double)scale, d10, d3, d4);
        tessellator.addVertexWithUV(d8, y + 0.0D, d10, d3, d6);
        tessellator.addVertexWithUV(d8, y + 0.0D, d9, d5, d6);
        tessellator.addVertexWithUV(d8, y + (double)scale, d9, d5, d4);
        tessellator.addVertexWithUV(d8, y + (double)scale, d9, d3, d4);
        tessellator.addVertexWithUV(d8, y + 0.0D, d9, d3, d6);
        tessellator.addVertexWithUV(d8, y + 0.0D, d10, d5, d6);
        tessellator.addVertexWithUV(d8, y + (double)scale, d10, d5, d4);
        d7 = x + 0.5D - 0.5D * (double)scale;
        d8 = x + 0.5D + 0.5D * (double)scale;
        d9 = z + 0.5D - 0.25D;
        d10 = z + 0.5D + 0.25D;
        tessellator.addVertexWithUV(d7, y + (double)scale, d9, d3, d4);
        tessellator.addVertexWithUV(d7, y + 0.0D, d9, d3, d6);
        tessellator.addVertexWithUV(d8, y + 0.0D, d9, d5, d6);
        tessellator.addVertexWithUV(d8, y + (double)scale, d9, d5, d4);
        tessellator.addVertexWithUV(d8, y + (double)scale, d9, d3, d4);
        tessellator.addVertexWithUV(d8, y + 0.0D, d9, d3, d6);
        tessellator.addVertexWithUV(d7, y + 0.0D, d9, d5, d6);
        tessellator.addVertexWithUV(d7, y + (double)scale, d9, d5, d4);
        tessellator.addVertexWithUV(d8, y + (double)scale, d10, d3, d4);
        tessellator.addVertexWithUV(d8, y + 0.0D, d10, d3, d6);
        tessellator.addVertexWithUV(d7, y + 0.0D, d10, d5, d6);
        tessellator.addVertexWithUV(d7, y + (double)scale, d10, d5, d4);
        tessellator.addVertexWithUV(d7, y + (double)scale, d10, d3, d4);
        tessellator.addVertexWithUV(d7, y + 0.0D, d10, d3, d6);
        tessellator.addVertexWithUV(d8, y + 0.0D, d10, d5, d6);
        tessellator.addVertexWithUV(d8, y + (double)scale, d10, d5, d4);
    }

	@Override
	public int getRenderId() {
		return AthsBlockSetup.plantCrossRenderID;
	}
}
