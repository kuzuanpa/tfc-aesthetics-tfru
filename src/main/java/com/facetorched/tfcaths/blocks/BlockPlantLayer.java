package com.facetorched.tfcaths.blocks;

import com.facetorched.tfcaths.AthsBlockSetup;

import net.minecraft.block.material.Material;

public class BlockPlantLayer extends BlockPlant{
	public BlockPlantLayer() {
		super(Material.vine);
		setHasNoDrops();
		setLayerBounds(.0625f);
		this.renderId = AthsBlockSetup.plantLayerRenderID;
	}
}
