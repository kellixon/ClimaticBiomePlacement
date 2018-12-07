package jaredbgreat.climaticbiome.compat.dt;

import jaredbgreat.climaticbiome.Info;
import jaredbgreat.climaticbiome.util.BlockRegistrar;
import net.minecraft.util.ResourceLocation;

import com.ferreusveritas.dynamictrees.api.TreeRegistry;
import com.ferreusveritas.dynamictrees.api.WorldGenRegistry;
import com.ferreusveritas.dynamictrees.blocks.LeavesProperties;
import com.ferreusveritas.dynamictrees.trees.Species;

public class DynamicTreeHelper {
	public static Species floridaPine;	
	public static LeavesProperties pineLeavesProperties;
	

	public static void preInit() {
		pineLeavesProperties 
			= new LeavesProperties(BlockRegistrar.blockPineNeedles.getDefaultState());
	}
	
	
	public static void init() {
		floridaPine = TreeRegistry.findSpecies(new ResourceLocation(Info.ID, "pine"));
		
		// Lastly ... I guess ....
		WorldGenRegistry.registerBiomeDataBasePopulator(new DataBasePop());
	}
	
	
	public void postInit() {
		
	}
	
}
