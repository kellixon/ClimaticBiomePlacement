package jaredbgreat.climaticbiome.generation.biome.biomes;

import jaredbgreat.climaticbiome.biomes.pseudo.PseudoBiomes;
import jaredbgreat.climaticbiome.generation.biome.IBiomeSpecifier;
import jaredbgreat.climaticbiome.generation.generator.ChunkTile;

public class GetRiver implements IBiomeSpecifier {
	private static GetRiver river;
	private GetRiver() {
		super();
	}

	
	@Override
	public int getBiome(ChunkTile tile) {
		if((tile.getTemp() + (tile.getBiomeSeed() & 0x1)) < 5) {
			PseudoBiomes.deepFrozenRiver.getSubId();
		}
		return PseudoBiomes.deepRiver.getSubId();
	}
	
	
	public static GetRiver getRiver() {
		if(river == null) {
			river = new GetRiver();
		}
		return river;
	}

	
	@Override
	public boolean isEmpty() {
		return false;
	}

}
