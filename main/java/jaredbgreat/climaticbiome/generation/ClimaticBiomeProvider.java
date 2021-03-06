package jaredbgreat.climaticbiome.generation;

import jaredbgreat.climaticbiome.generation.map.MapRegistry;

import java.io.File;
import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraftforge.common.DimensionManager;


public class ClimaticBiomeProvider extends BiomeProvider {
	private World world;
	private MapRegistry finder;
	
	
	public ClimaticBiomeProvider(World world) {		
		super(/*world.getWorldInfo()*/);		
		this.world = world;
		finder = new MapRegistry(world.getSeed(), world);
	}
	

    @Nullable
    @Override
    public BlockPos findBiomePosition(int x, int z, int range, 
    			List<Biome> biomes, Random random) {
        int i = x - range >> 2;
        int j = z - range >> 2;
        int k = x + range >> 2;
        int l = z + range >> 2;
        int i1 = k - i + 1;
        int j1 = l - j + 1;
        Biome[] barr = new Biome[i1 * j1];
        barr = this.getBiomes(barr, i, j, i1, j1);
        BlockPos blockpos = null;
        int k1 = 0;

        for (int l1 = 0; l1 < i1 * j1; ++l1) {
            int i2 = i + l1 % i1 << 2;
            int j2 = j + l1 / i1 << 2;
            Biome biome = barr[l1];

            if (biomes.contains(biome) && (blockpos == null || random.nextInt(k1 + 1) == 0)) {
                blockpos = new BlockPos(i2, 0, j2);
                ++k1;
            }
        }
        return blockpos;
    }


    @Override
    public Biome[] getBiomesForGeneration(Biome[] biomes, int x, int z, int width, int height) {
        if(biomes == null || biomes.length < width * height) {
            biomes = new Biome[width * height];
        }
    	for(int i = 0; i < width; i++) 
    		for(int j = 0; j < height; j++) {
    			biomes[(j * width) + i] = findBiomeAt((x + i) * 4, (z + j) * 4);
    		}
        return biomes;
    }
    
    
    private Biome findBiomeAt(int x, int z) {
    	Biome[] chunk = new Biome[256];
    	finder.getChunkBiomeGen(x / 16, z / 16, chunk);
    	return chunk[(chunkModulus(z) * 16) + chunkModulus(x)];
    }
    

    @Override
    public Biome[] getBiomes(@Nullable Biome[] in, int x, int z, int width, int depth, boolean cacheFlag) {
    	//IntCache.resetIntCache();
        if((in == null) || (in.length < (width * depth))) {
            in = new Biome[width * depth];
        }
        // FIXME?? This should probably check more strictly if its an exact whole chunk...?
        if(in.length == 256) {
    		finder.getChunkBiomeGrid(x / 16, z / 16, in);
    	} else {
    		finder.getUnalignedBiomeGrid(x, z, width, depth, in);
    	}
        return in;
    }
    
    
    private int getIDForCoords(int x, int z) {
    	return Biome.getIdForBiome(getBiome(new BlockPos(x * 4, 64, z * 4)));
    }
    
    
    private int modRight4(int in) {
    	in %= 4;
    	if(in < 0) {
    		in += 4;
    	}
    	return in;
    }
    
    
    private int chunkModulus(int in) {
    	in %= 16;
    	if(in < 0) {
    		in += 16;
    	}
    	return in;
    }

    
    @Override
    public boolean areBiomesViable(int x, int z, int radius, List<Biome> allowed) {
    	int cr = radius / 16;
    	for(int i = -cr; i <= cr; i++)
        	for(int j = -cr; j <= cr; j++) {
        		if(!allowed.contains(finder.getBiomeBlock(x, z))) {
        			return false;
        		}
        	}
        return true;
    }

    
    public void cleanupCache() {
        super.cleanupCache();
        //finder.cleanCaches();
    }


}
