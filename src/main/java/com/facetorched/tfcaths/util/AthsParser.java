package com.facetorched.tfcaths.util;

import com.bioxx.tfc.BlockSetup;
import com.bioxx.tfc.WorldGen.TFCBiome;
import com.bioxx.tfc.api.Enums.EnumTree;
import com.facetorched.tfcaths.WorldGen.Generators.AthsWorldGenPlants;
import com.facetorched.tfcaths.WorldGen.Generators.PlantSpawnData;
import com.facetorched.tfcaths.blocks.BlockPlant;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.oredict.OreDictionary;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.HashSet;

public class AthsParser {
	public static Block getBlockFromName(String blockName) {
		Block ret = Block.getBlockFromName(blockName);
		
		if (ret == null){
			AthsLogger.error("config file contains invalid block name " + blockName);
			throw new java.lang.IllegalArgumentException("invalid block name " + blockName);
		}
		return ret;
	}
	
	public static Block[] getBlockFromName(String[] blockNames) {
		Block[] ret = new Block[blockNames.length];
		for(int i = 0; i < blockNames.length; i++) {
			ret[i] = getBlockFromName(blockNames[i]);
		}
		return ret;
	}
	
	public static boolean isHolding(World world, EntityPlayer player, String oreDict) {
		if (!world.isRemote){
			ItemStack equip = player.getCurrentEquippedItem();
			if (equip != null){
				int[] equipIDs = OreDictionary.getOreIDs(equip);
				for (int id : equipIDs){
					String name = OreDictionary.getOreName(id);
					if (name.startsWith(oreDict)){
						return true;
					}
				}
			}
		}
		return false;
	}
	
	public static void damageItem(EntityPlayer player, ItemStack is) {
		is.damageItem(1, player);
		if (is.stackSize == 0)
			player.inventory.setInventorySlotContents(player.inventory.currentItem, null);
	}
	
	public static String[] getBiomes()
	{
		TFCBiome[] biomeList = TFCBiome.getBiomeGenArray();
		if(biomeList.length == 0)
			throw new IllegalStateException();
		int biome_count = 0;
		for (TFCBiome biome : biomeList){
			if (biome != null){
				biome_count ++;
			}
		}
		String[] biomeNames = new String[biome_count];
		int index = 0;
		for (TFCBiome biome : biomeList){
			if (biome != null){
				biomeNames[index] = biome.biomeName;
				index++;
			}
		}
		return biomeNames;
	}
	
	public static EnumTree getTreeFromName(String name) {
		for(EnumTree et : EnumTree.values()) {
			if(et.name().toLowerCase().equals(name.toLowerCase())) { // disgusting use of the .name() method
				return et;
			}
		}
		return null;
	}
	
	public static ArrayList<BlockMetaPair> getTrunkBlocks(int treeID){
		ArrayList<BlockMetaPair> ret = new ArrayList<BlockMetaPair>();
		if (treeID > 15) {
			ret.add(new BlockMetaPair(BlockSetup.logNatural2, treeID % 16));
			//ret.add(new BlockMetaPair(BlockSetup.woodVert2, treeID % 16)); // player placed
		}
		else {
			ret.add(new BlockMetaPair(BlockSetup.logNatural, treeID));
			//ret.add(new BlockMetaPair(BlockSetup.woodVert, treeID)); // player placed
		}
		return ret;
	}
	
	public static String[] add(String[] src, String str) {
		String[] dest = new String[src.length + 1];
		for(int i = 0; i < src.length; i++) {
			dest[i] = src[i];
		}
		dest[src.length] = str;
		return dest;
	}
	
	public static String[] append(String[] src1, String[] src2) {
		String[] dest = new String[src1.length + src2.length];
		for(int i = 0; i < src1.length; i++) {
			dest[i] = src1[i];
		}
		for(int i = 0; i < src2.length; i++) {
			dest[i + src1.length] = src2[i];
		}
		return dest;
	}
	public static String[] append(String[][] srcs) {
		String[] dest = srcs[1];
		for(int i = 1; i < srcs.length; i++) {
			dest = append(dest, srcs[i]);
		}
		return dest;
	}
	
	public static int[] add(int[] src, int value) {
		if(src == null) {
			return new int[] {value};
		}
		int[] dest = new int[src.length + 1];
		for(int i = 0; i < src.length; i++) {
			dest[i] = src[i];
		}
		dest[src.length] = value;
		return dest;
	}
	
	public static String[] prefix(String[] src, String str) {
		String[] dest = new String[src.length];
		for(int i = 0; i < src.length; i++) {
			dest[i] = str + src[i];
		}
		return dest;
	}
	
	public static String[] suffix(String[] src, String str) {
		String[] dest = new String[src.length];
		for(int i = 0; i < src.length; i++) {
			dest[i] = src[i] + str;
		}
		return dest;
	}
	
	public static boolean isNegativeDirection(ForgeDirection d) {
		return d.equals(ForgeDirection.DOWN) || d.equals(ForgeDirection.NORTH) || d.equals(ForgeDirection.WEST);
	}
	
	public static boolean contains(String[] collection, String key) {
		for(String str : collection) {
			if(str.equals(key))
				return true;
		}
		return false;
	}
	
	/*
	 * -1 is wild card
	 */
	public static boolean contains(int[] collection, int key) {
		for(int i : collection) {
			if(i == key || i == -1)
				return true;
		}
		return false;
	}
	
	public static boolean contains(Block[] collection, Block key) {
		for(Block block : collection) {
			if(block.equals(key))
				return true;
		}
		return false;
	}
	
	public static int find(String[] collection, String key) {
		for(int i = 0; i < collection.length; i++) {
			if(collection[i].equals(key))
				return i;
		}
		return -1;
	}
	
	public static HashSet<BlockPlant> getAthsPlants(){
		return getAthsPlants(BlockPlant.class);
	}
	
	public static HashSet<BlockPlant> getAthsPlants(Class<?> subclass){
		HashSet<BlockPlant> result = new HashSet<BlockPlant>();
		for(PlantSpawnData d : AthsWorldGenPlants.plantList.values()) {
			Block b = d.block;
			if(b != null && b instanceof BlockPlant && subclass.isInstance(b)) {
				result.add((BlockPlant)b);
			}
		}
		return result;
	}

	@Nullable
	public static ItemStack getSaplingItem(EnumTree enumTree){
		switch (enumTree){
			case OAK:        return new ItemStack(BlockSetup.sapling,0,0);
			case ASPEN:      return new ItemStack(BlockSetup.sapling,0,1);
			case BIRCH:      return new ItemStack(BlockSetup.sapling,0,2);
			case CHESTNUT:   return new ItemStack(BlockSetup.sapling,0,3);
			case DOUGLASFIR: return new ItemStack(BlockSetup.sapling,0,4);
			case HICKORY:    return new ItemStack(BlockSetup.sapling,0,5);
			case MAPLE:      return new ItemStack(BlockSetup.sapling,0,6);
			case ASH:        return new ItemStack(BlockSetup.sapling,0,7);
			case PINE:       return new ItemStack(BlockSetup.sapling,0,8);
			case REDWOOD:    return new ItemStack(BlockSetup.sapling,0,9);
			case SPRUCE:     return new ItemStack(BlockSetup.sapling,0,10);
			case SYCAMORE:   return new ItemStack(BlockSetup.sapling,0,11);
			case WHITECEDAR: return new ItemStack(BlockSetup.sapling,0,12);
			case WHITEELM:   return new ItemStack(BlockSetup.sapling,0,13);
			case WILLOW:     return new ItemStack(BlockSetup.sapling,0,14);
			case KAPOK:      return new ItemStack(BlockSetup.sapling,0,15);
			case KOA:
			case UTACACIA:   return new ItemStack(BlockSetup.sapling2,0,0);
		}
		return null;
	}
}
