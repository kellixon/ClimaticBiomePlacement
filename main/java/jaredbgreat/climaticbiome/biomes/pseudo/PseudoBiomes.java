package jaredbgreat.climaticbiome.biomes.pseudo;

import jaredbgreat.climaticbiome.biomes.SubBiome;
import jaredbgreat.climaticbiome.biomes.SubBiomeRegistry;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;

public class PseudoBiomes {
	public static BiomeDeepRiver deepRiver;	
	public static BiomeDeepRiver deepFrozenRiver;	
	
	
	public static void createBiomes() {
		deepRiver = new BiomeDeepRiver(Biome.getBiome(7), 1, 
				new Biome.BiomeProperties("River").setBaseHeight(-0.8f).setHeightVariation(0.0f));
		deepFrozenRiver = new BiomeDeepRiver(Biome.getBiome(11), 1, 
				new Biome.BiomeProperties("FrozenRiver").setBaseHeight(-0.8f)
											            .setHeightVariation(0.0f)
												        .setTemperature(0.0F)
												        .setRainfall(0.5F)
												        .setSnowEnabled());
	}
	
	
	public static void registerBiomes() {
		SubBiomeRegistry reg = SubBiomeRegistry.getSubBiomeRegistry();
		reg.add(deepRiver);
	}
	
	
	public static void addSubBiome(ResourceLocation vanillaRes, int id,
				  float baseHeight, float heightVar) {
		Biome vanilla = Biome.REGISTRY.getObject(vanillaRes);
		SubBiome biome = new SubBiome(vanilla, id,  
				new Biome.BiomeProperties(vanillaRes.getResourcePath() + id)
					.setBaseHeight(baseHeight)
					.setHeightVariation(heightVar));
		SubBiomeRegistry.getSubBiomeRegistry().add(biome);
	}
	

}
