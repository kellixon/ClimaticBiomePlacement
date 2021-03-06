package jaredbgreat.climaticbiome.generation.biome.biomes;

import jaredbgreat.climaticbiome.ConfigHandler;
import jaredbgreat.climaticbiome.generation.biome.BiomeList;
import jaredbgreat.climaticbiome.generation.biome.IBiomeSpecifier;
import jaredbgreat.climaticbiome.generation.biome.LeafBiome;
import jaredbgreat.climaticbiome.generation.biome.SeedDoubleBiome;
import jaredbgreat.climaticbiome.generation.biome.compat.BoP;
import jaredbgreat.climaticbiome.generation.biome.compat.userdef.DefReader;
import jaredbgreat.climaticbiome.generation.generator.ChunkTile;

// TODO / FIXME: Fix for use with mods!
public class GetSwamp implements IBiomeSpecifier {
	private static GetSwamp swamp;
	private GetSwamp() {
		super();
		init();
	}
	BiomeList cold;
	BiomeList cool;
	BiomeList warm;
	BiomeList hot;
	
	
	public void init() {
		cold = new BiomeList();
		cool = new BiomeList();
		warm = new BiomeList();
		hot  = new BiomeList();
		warm.addItem(new LeafBiome(6), 3);
		warm.addItem(new LeafBiome(134), 1);
		cool.addItem(new SeedDoubleBiome(134, 3, 6));
		if(ConfigHandler.useBoP) BoP.addSwamps(cold, cool, warm, hot);
		if(ConfigHandler.useCfg) {
			DefReader.readBiomeData(cold, "SwampCold.cfg");
			DefReader.readBiomeData(cold, "SwampCool.cfg");
			DefReader.readBiomeData(cold, "SwampWarm.cfg");
			DefReader.readBiomeData(cold, "SwampTropical.cfg");
		}
		// THIS MUST RUN LAST!!!
		fixSwamps();
	}
	

	@Override
	public int getBiome(ChunkTile tile) {
		int temp = tile.getTemp();
    	if(temp < 12) {
    		return cold.getBiome(tile);
    	} 
    	if(temp < 16) {
    		return cool.getBiome(tile);        		
    	} 
    	if(temp < 21) {
    		return warm.getBiome(tile);
    	}
    	return hot.getBiome(tile);
	}
	
	
	public static GetSwamp getSwamp() {
		if(swamp == null) {
			swamp = new GetSwamp();
		}
		return swamp;
	}
	
	
	/**
	 * This fixes possible problems with ocean,
	 * specifically, makes sure there are no 
	 * oceans types empty.  This way mods can 
	 * add temperature specific oceans, while 
	 * not relying on them.
	 */
	private void fixSwamps() {
		if(cool.isEmpty()) {
			cool = warm;
		}
		if(cold.isEmpty()) {
			cold = cool;
		}
		if(hot.isEmpty()) {
			hot = warm;
		}
	}


	@Override
	public boolean isEmpty() {
		return false;
	}

}
