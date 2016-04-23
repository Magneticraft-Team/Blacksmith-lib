package net.darkaqua.blacksmith.util;

import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Vec3i;
import net.minecraft.world.World;

public class WorldRef {

    private final World world;
    private final BlockPos position;

    public WorldRef(World world, BlockPos position) {
        this.world = world;
        this.position = position;
    }

    public WorldRef create(BlockPos pos) {
        return new WorldRef(world, pos);
    }

    public World getWorld() {
        return world;
    }

    public BlockPos getPosition() {
        return position;
    }

    public WorldRef copy() {
        return new WorldRef(world, position);
    }

    public WorldRef move(Vec3i dir) {
        return new WorldRef(world, getPosition().add(dir));
    }

    public WorldRef move(EnumFacing dir) {
        return move(dir.getDirectionVec());
    }

	public IBlockState getBlockState() {
        return world.getBlockState(position);
    }

    public void setBlockState(IBlockState state) {
        world.setBlockState(position, state, 3);
    }

    public void setBlockState(IBlockState state, int flags) {
        world.setBlockState(position, state, flags);
    }

    public void setAir() {
        world.setBlockToAir(position);
    }

    public TileEntity getTileEntity() {
        return world.getTileEntity(position);
    }

    public long getWorldTime() {
        return world.getWorldTime();
    }
}
