package jaredbgreat.climaticbiome.biomes;

import jaredbgreat.climaticbiome.generation.chunk.biomes.GetChaparral;
import jaredbgreat.climaticbiome.generation.chunk.biomes.GetSubtropicalForest;
import jaredbgreat.climaticbiome.generation.chunk.biomes.GetTropicalForest;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;


@Mod.EventBusSubscriber
public class ModBiomes {
	
	public static WarmForest     warmForest;
	public static TropicalForest tropicalForest;
	public static WarmForest     warmForestHills;
	public static TropicalForest tropicalForestHills;
	public static Pinewoods      pineWoods;
	public static Scrub          denseScrub;
	public static Scrub          dryScrub;
	public static Scrub          denseScrubHills;
	public static Scrub          dryScrubHills;
    
    
    public static void createBiomes() {
		warmForest = new WarmForest();
		BiomeDictionary.registerBiomeType(warmForest, Type.FOREST, Type.CONIFEROUS);
		tropicalForest = new TropicalForest();
		BiomeDictionary.registerBiomeType(tropicalForest, Type.FOREST, Type.JUNGLE, Type.HOT);
		pineWoods = new Pinewoods();
		BiomeDictionary.registerBiomeType(pineWoods, Type.FOREST, Type.WET, Type.HOT, Type.CONIFEROUS, Type.SWAMP);
		warmForestHills 
				= new WarmForest(new Biome.BiomeProperties("Subtropical Forest Hills")
					.setTemperature(0.8F)
					.setRainfall(0.85F)
					.setBaseHeight(0.45F)
					.setHeightVariation(0.3F));
		BiomeDictionary.registerBiomeType(warmForestHills, Type.FOREST, Type.CONIFEROUS, Type.HILLS);
		tropicalForestHills 
				= new TropicalForest(new Biome.BiomeProperties("Subtropical Forest Hills")
					.setTemperature(0.9F)
					.setRainfall(0.7F)
					.setBaseHeight(0.45F)
					.setHeightVariation(0.3F));
		BiomeDictionary.registerBiomeType(tropicalForestHills, Type.FOREST, Type.JUNGLE, Type.HOT, Type.HILLS);
		denseScrub = new Scrub(Scrub.Type.DENSE, 
				new Biome.BiomeProperties("Dense Scrub")
					.setTemperature(1.0F)
					.setRainfall(0.1F)
					.setBaseHeight(0.125F)
					.setHeightVariation(0.05F));
		BiomeDictionary.registerBiomeType(denseScrub, Type.SPARSE);
		dryScrub = new Scrub(Scrub.Type.DRY, 
				new Biome.BiomeProperties("Dry Scrub")
					.setTemperature(1.25F)
					.setRainfall(0.05F)
					.setRainDisabled()
					.setBaseHeight(0.125F)
					.setHeightVariation(0.05F));
		BiomeDictionary.registerBiomeType(dryScrub, Type.SPARSE, Type.DRY);
		denseScrubHills = new Scrub(Scrub.Type.DENSE, 
				new Biome.BiomeProperties("Dense Scrub Hills")
					.setTemperature(1.0F)
					.setRainfall(0.1F)
					.setBaseHeight(0.45F)
					.setHeightVariation(0.3F));
		BiomeDictionary.registerBiomeType(denseScrubHills, Type.SPARSE, Type.HILLS);
		dryScrubHills = new Scrub(Scrub.Type.DRY, 
				new Biome.BiomeProperties("Dry Scrub Hills")
					.setTemperature(1.25F)
					.setRainfall(0.0F)
					.setRainDisabled()
					.setBaseHeight(0.45F)
					.setHeightVariation(0.3F));
		BiomeDictionary.registerBiomeType(dryScrubHills, Type.SPARSE, Type.DRY, Type.HILLS);
    }
    

	@SubscribeEvent
	public static void registerBiomes(RegistryEvent.Register<Biome> event) {
    	ModBiomes.createBiomes();
		event.getRegistry().register(warmForest
				.setRegistryName("subtropical_forest"));
		event.getRegistry().register(tropicalForest
				.setRegistryName("tropical_forest"));
		event.getRegistry().register(pineWoods
				.setRegistryName("pine_swamp"));
		event.getRegistry().register(warmForestHills
				.setRegistryName("subtropical_forest_hills"));
		event.getRegistry().register(tropicalForestHills
				.setRegistryName("tropical_forest_hills"));
		event.getRegistry().register(denseScrub
				.setRegistryName("dense_scrub"));
		event.getRegistry().register(dryScrub
				.setRegistryName("dry_scrub"));
		event.getRegistry().register(denseScrubHills
				.setRegistryName("dense_scrub_hills"));
		event.getRegistry().register(dryScrubHills
				.setRegistryName("dry_scrub_hills"));
		GetChaparral.init();
		GetSubtropicalForest.init();
		GetTropicalForest.init();		
	}
}
