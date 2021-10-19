package com.facetorched.tfcaths.blocks;

import com.dunk.tfc.TerraFirmaCraft;
import com.facetorched.tfcaths.enums.EnumVary;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.world.IBlockAccess;

public class BlockPlantFoliage extends BlockPlantStraw{
	@SideOnly(Side.CLIENT)
	@Override
	public int colorMultiplier(IBlockAccess world, int x, int y, int z) {
		if(this.isVary(world.getBlockMetadata(x, y, z), EnumVary.SNOW)){
			return super.colorMultiplier(world, x, y, z);
		}
		return TerraFirmaCraft.proxy.grassColorMultiplier(world, x, y, z);
	}
}