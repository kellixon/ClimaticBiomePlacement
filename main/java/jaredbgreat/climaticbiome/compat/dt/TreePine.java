package jaredbgreat.climaticbiome.compat.dt;

import jaredbgreat.climaticbiome.Info;
import jaredbgreat.climaticbiome.biomes.ModBiomes;
import jaredbgreat.climaticbiome.util.BlockRegistrar;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary.Type;

import com.ferreusveritas.dynamictrees.ModTrees;
import com.ferreusveritas.dynamictrees.api.TreeRegistry;
import com.ferreusveritas.dynamictrees.systems.featuregen.FeatureGenConiferTopper;
import com.ferreusveritas.dynamictrees.systems.featuregen.FeatureGenPodzol;
import com.ferreusveritas.dynamictrees.trees.Species;
import com.ferreusveritas.dynamictrees.trees.TreeFamily;

public class TreePine extends TreeFamily {
	
	public class PineSpeciesBase extends Species {
		
		public PineSpeciesBase(TreeFamily treeFamily) {
			// Purely an initial guess.
			setBasicGrowingParameters(0.2f, 16.0f, 3, 4, 1.3f);
			setGrowthLogicKit(TreeRegistry.findGrowthLogicKit(ModTrees.CONIFER));
			
			envFactor(Type.COLD, 0.50f);
			envFactor(Type.SNOWY, 0.25f);
			envFactor(Type.DRY, 0.75f);
			envFactor(Type.WASTELAND, 0.50f);
			envFactor(Type.FOREST, 1.05f);		
			
			setupStandardSeedDropping();
			
			//Add species features
			addGenFeature(new FeatureGenConiferTopper(getLeavesProperties()));
			addGenFeature(new FeatureGenPodzol());
			
		}
		
		@Override
		public boolean isBiomePerfect(Biome biome) {
			return biome == ModBiomes.pineWoods;
		}
		
	}
	
	
	public TreePine() {
		super(new ResourceLocation(Info.ID, "pine"));
		//Set up primitive log. This controls what is dropped on harvest.
		setPrimitiveLog(BlockRegistrar.blockPineLog.getDefaultState());
		DynamicTreeHelper.pineLeavesProperties.setTree(this);
	}

	
	@Override
	public void createSpecies() {
		setCommonSpecies(new PineSpeciesBase(this));
		getCommonSpecies().generateSeed();
	}



}
