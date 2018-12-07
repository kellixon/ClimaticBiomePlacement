package jaredbgreat.climaticbiome.compat.dt.trees;

import jaredbgreat.climaticbiome.biomes.ModBiomes;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;

import com.ferreusveritas.dynamictrees.ModTrees;
import com.ferreusveritas.dynamictrees.api.TreeRegistry;
import com.ferreusveritas.dynamictrees.systems.featuregen.FeatureGenCocoa;
import com.ferreusveritas.dynamictrees.systems.featuregen.FeatureGenUndergrowth;
import com.ferreusveritas.dynamictrees.systems.featuregen.FeatureGenVine;
import com.ferreusveritas.dynamictrees.trees.Species;
import com.ferreusveritas.dynamictrees.trees.TreeFamily;

public class TreeSmallJungle extends Species {
	
	// OK, how do I register this as a new variant of the jungle family?
	TreeSmallJungle(TreeFamily treeFamily) {
		super(treeFamily.getName(), treeFamily);
		
		//Jungle Trees are tall, wildly growing, fast growing trees with low branches to provide inconvenient obstruction and climbing
		setBasicGrowingParameters(0.2f, 14.0f, 3, 2, 1.0f);
		setGrowthLogicKit(TreeRegistry.findGrowthLogicKit(ModTrees.JUNGLE));
		
		envFactor(Type.COLD, 0.15f);
		envFactor(Type.DRY,  0.20f);
		envFactor(Type.HOT, 1.1f);
		envFactor(Type.WET, 1.1f);
		
		setupStandardSeedDropping();
		
		//Add species features
		addGenFeature(new FeatureGenCocoa());
		addGenFeature(new FeatureGenVine());
		addGenFeature(new FeatureGenUndergrowth());
	}
	
	@Override
	public boolean isBiomePerfect(Biome biome) {
		return (biome == ModBiomes.tropicalForest) 
				|| (biome == ModBiomes.tropicalForestHills);
	};
	
}
