package jaredbgreat.climaticbiome.proxy;

import jaredbgreat.climaticbiome.ClimaticBiomes;
import jaredbgreat.climaticbiome.compat.dt.DynamicTreeHelper;
import net.minecraft.block.BlockLeaves;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;

public class ClientProxy implements IProxy {

	@Override
	public void registerItemRender(Item item, int meta, String id) {
		ModelLoader.setCustomModelResourceLocation(item, meta, 
				new ModelResourceLocation(item.getRegistryName(), id));
	}

	@Override
	public void fixRenders(BlockLeaves in) {
		in.setGraphicsLevel(Minecraft.getMinecraft().isFancyGraphicsEnabled());
	}

	@Override
	public void preInit() {
    	if(ClimaticBiomes.gotDT) {
    		DynamicTreeHelper.clientPreInit();
    	}
	}

	@Override
	public void init() {
    	if(ClimaticBiomes.gotDT) {
    		DynamicTreeHelper.clientInit();
    	}
	}
	
	

}
