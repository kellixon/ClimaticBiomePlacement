package jaredbgreat.climaticbiome.proxy;

import net.minecraft.block.BlockLeaves;
import net.minecraft.item.Item;

public interface IProxy {

	public void registerItemRender(Item item, int meta, String id);
	public void fixRenders(BlockLeaves in);
	
}
