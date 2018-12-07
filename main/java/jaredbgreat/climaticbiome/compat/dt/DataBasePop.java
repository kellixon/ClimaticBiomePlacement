package jaredbgreat.climaticbiome.compat.dt;

import jaredbgreat.climaticbiome.biomes.ModBiomes;

import com.ferreusveritas.dynamictrees.api.TreeRegistry;
import com.ferreusveritas.dynamictrees.api.worldgen.BiomePropertySelectors.RandomSpeciesSelector;
import com.ferreusveritas.dynamictrees.api.worldgen.IBiomeDataBasePopulator;
import com.ferreusveritas.dynamictrees.trees.Species;
import com.ferreusveritas.dynamictrees.worldgen.BiomeDataBase;
import com.ferreusveritas.dynamictrees.worldgen.BiomeDataBase.Operation;

public class DataBasePop implements IBiomeDataBasePopulator {

	private static Species oak;
	private static Species acacia;
	private static Species jungle;
	private static Species apple;
	private static Species oakswamp;

	@Override
	public void populate(BiomeDataBase db) {
		// Define vanilla trees
		oak = TreeRegistry.findSpeciesSloppy("oak");
		acacia = TreeRegistry.findSpeciesSloppy("acacia");
		jungle = TreeRegistry.findSpeciesSloppy("jungle"); // FIXME: Create mini jungle tree variant
		apple = TreeRegistry.findSpeciesSloppy("apple");
		oakswamp = TreeRegistry.findSpeciesSloppy("oakswamp");
		// Actually set up biomes
		db.setSpeciesSelector(ModBiomes.warmForest, 
				              new RandomSpeciesSelector().add(oak, 4).add(DynamicTreeHelper.floridaPine, 1), 
				              Operation.REPLACE);
		db.setSpeciesSelector(ModBiomes.warmForestHills, 
	              			  new RandomSpeciesSelector().add(oak, 4).add(DynamicTreeHelper.floridaPine, 1), 
	                          Operation.REPLACE);
		db.setSpeciesSelector(ModBiomes.tropicalForest, 
							  new RandomSpeciesSelector().add(acacia, 4).add(oak, 2).add(jungle, 1), 
							  Operation.REPLACE);
		db.setSpeciesSelector(ModBiomes.tropicalForestHills, 
    			  			  new RandomSpeciesSelector().add(acacia, 4).add(oak, 2).add(jungle, 1), 
    			  			  Operation.REPLACE);
		db.setSpeciesSelector(ModBiomes.pineWoods, 
	  			  			  new RandomSpeciesSelector().add(DynamicTreeHelper.floridaPine, 4).add(oakswamp, 1), 
	  			  			  Operation.REPLACE);
	}

}
