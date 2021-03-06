package jaredbgreat.climaticbiome.generation.biome.biomes;

import jaredbgreat.climaticbiome.ConfigHandler;
import jaredbgreat.climaticbiome.generation.biome.BiomeClimateTable;
import jaredbgreat.climaticbiome.generation.biome.BiomeList;
import jaredbgreat.climaticbiome.generation.biome.IBiomeSpecifier;
import jaredbgreat.climaticbiome.generation.biome.LeafBiome;
import jaredbgreat.climaticbiome.generation.biome.compat.BoP;
import jaredbgreat.climaticbiome.generation.biome.compat.userdef.DefReader;
import jaredbgreat.climaticbiome.generation.generator.ChunkTile;


public class GetOcean implements IBiomeSpecifier {
	private static GetOcean oceans;
	private GetOcean() {
		super();
		init();
	}
	BiomeList frozen;
	BiomeList cold;
	BiomeList cool;
	BiomeList warm;
	BiomeList hot;
	BiomeList dfrozen;
	BiomeList dcold;
	BiomeList dcool;
	BiomeList dwarm;
	BiomeList dhot;
	IBiomeSpecifier islands1; // Main land biomes
	IBiomeSpecifier islands2; // Special island-only biomes
	
	
	public void init() {
		// Shallows
		frozen = new BiomeList();
		cold   = new BiomeList();
		cool   = new BiomeList();
		warm   = new BiomeList();
		hot    = new BiomeList();
		//Deeps
		dfrozen = new BiomeList();
		dcold   = new BiomeList();
		dcool   = new BiomeList();
		dwarm   = new BiomeList();
		dhot    = new BiomeList();
		// Islands
		islands1 = BiomeClimateTable.getLandTable();
		islands2 = GetIslands.getIslands();
		// Add biomes
		cool.addItem(new LeafBiome(0));
		dcool.addItem(new LeafBiome(24));
		frozen.addItem(new LeafBiome(10));
		dfrozen.addItem(new LeafBiome(10));
		if(ConfigHandler.useBoP) BoP.addOceans(frozen, cold, cool, warm, hot, 
				dfrozen, dcold, dcool, dwarm, dhot);
		if(ConfigHandler.useCfg) {
			DefReader.readBiomeData(frozen, "OceanFrozen.cfg");
			DefReader.readBiomeData(cold, "OceanCold.cfg");
			DefReader.readBiomeData(cool, "OceanCool.cfg");
			DefReader.readBiomeData(warm, "OceanWarm.cfg");
			DefReader.readBiomeData(hot, "OceanHot.cfg");
			DefReader.readBiomeData(dfrozen, "DeepOceanFrozen.cfg");
			DefReader.readBiomeData(dcold, "DeepOceanCold.cfg");
			DefReader.readBiomeData(dcool, "DeepOceanCool.cfg");
			DefReader.readBiomeData(dwarm, "DeepOceanWarm.cfg");
			DefReader.readBiomeData(dhot, "DeepOceanHot.cfg");
		}
		// MUST BE LAST, ALWAYS!!!
		fixOceans();
	}	

	
	@Override
	public int getBiome(ChunkTile tile) {
		int temp = tile.getTemp();
		int seed = tile.getBiomeSeed();
		// FIXME: WRONG NOISE!    Create other noise,
		//        this is not the noise I want!
		//        This means adding ice noise to the main map.
		int iceNoise = tile.getNoise();
		tile.nextBiomeSeed();
		if(((seed % 5) == 0) && notNearEdge(tile)) {
			int noise = tile.getNoise();
			if((seed % 31) == 0) {
				if((tile.getTemp() > 9) && (tile.getTemp() < 19)
						                && (tile.getWet() > 3)  
						                && (tile.getVal() < 4)) {
					if(noise < 5) {
						return 14;
					}
					if(noise < 7) {
						return 15;
					}
				}
				return 0;				
			} else if((seed & 1) == 0) {
				if(noise > (4 + (seed % 3))) {
					return islands1.getBiome(tile.nextBiomeSeed());
				}
			} else {
				if(noise > (2 + (seed % 3))) {
					return islands2.getBiome(tile);
				}				
			}
	        if(((iceNoise / 2) - temp) > -1) {
	        	return frozen.getBiome(tile);
	        }
        	if(temp < 6) {
        		return cold.getBiome(tile);
        	} 
        	if(temp < 12) {
        		return cool.getBiome(tile);        		
        	} 
        	if(temp < 18) {
        		return warm.getBiome(tile);
        	}
        	return hot.getBiome(tile);
        
		}
        if(((iceNoise / 2) - temp) > -1) {
        	if(tile.getVal() < 2) {
        		return dfrozen.getBiome(tile);        		
        	}
        	return frozen.getBiome(tile);
        }
        if((tile.getVal()) < 3) {
        	if(temp < 7) {
        		return dcold.getBiome(tile);
        	} 
        	if(temp < 13) {
        		return dcool.getBiome(tile);        		
        	} 
        	if(temp < 19) {
        		return dwarm.getBiome(tile);
        	}
        	return dhot.getBiome(tile);
        } else {
        	if(temp < 6) {
        		return cold.getBiome(tile);
        	} 
        	if(temp < 12) {
        		return cool.getBiome(tile);        		
        	} 
        	if(temp < 18) {
        		return warm.getBiome(tile);
        	}
        	return hot.getBiome(tile);
        }
    }
	
	
	public static GetOcean getOcean() {
		if(oceans == null) {
			oceans = new GetOcean();			
		}
		return oceans;
	}
	
	
	/**
	 * This fixes possible problems with ocean,
	 * specifically, makes sure there are no 
	 * oceans types empty.  This way mods can 
	 * add temperature specific oceans, while 
	 * not relying on them.
	 */
	private void fixOceans() {
		if(warm.isEmpty()) {
			warm = cool;
		}
		if(hot.isEmpty()) {
			hot = warm;
		}
		if(cold.isEmpty()) {
			cold = cool;
		}
		if(frozen.isEmpty()) {
			// Should never be true, but just in case.
			frozen = cold; 
		}
		if(dcool.isEmpty()) {
			// Should never be true, but just in case.
			dcool = cool;
		}
		if(dwarm.isEmpty()) {
			dwarm = dcool;
		}
		if(dhot.isEmpty()) {
			dhot = dwarm;
		}
		if(dcold.isEmpty()) {
			dcold = dcool;
		}
		if(dfrozen.isEmpty()) {
			// Should never be true, but just in case.
			dfrozen = dcold; 
		}
	}
	
	
	private boolean notNearEdge(ChunkTile tile) {
		int x = tile.getX();
		int z = tile.getZ();
		return ((x > 5) && (x < 250)) && ((z > 5) && (z < 250));
	}


	@Override
	public boolean isEmpty() {
		return false;
	}


}
