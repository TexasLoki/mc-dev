package net.minecraft.server;

public class ItemMapEmpty extends ItemWorldMapBase {

    protected ItemMapEmpty() {
        this.a(CreativeModeTab.f);
    }

    public ItemStack a(ItemStack itemstack, World world, EntityHuman entityhuman) {
        ItemStack itemstack1 = new ItemStack(Items.MAP, 1, world.b("map"));
        String s = "map_" + itemstack1.getData();
        WorldMap worldmap = new WorldMap(s);

        world.a(s, (PersistentBase) worldmap);
        worldmap.scale = 0;
        int i = 128 * (1 << worldmap.scale);

        worldmap.centerX = (int) (Math.round(entityhuman.locX / (double) i) * (long) i);
        worldmap.centerZ = (int) (Math.round(entityhuman.locZ / (double) i) * (long) i);
        worldmap.map = (byte) world.worldProvider.dimension;
        worldmap.c();
        --itemstack.count;
        if (itemstack.count <= 0) {
            return itemstack1;
        } else {
            if (!entityhuman.inventory.pickup(itemstack1.cloneItemStack())) {
                entityhuman.drop(itemstack1, false);
            }

            return itemstack;
        }
    }
}
