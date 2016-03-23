package net.darkaqua.blacksmith.util;

import net.darkaqua.blacksmith.vectors.Vect3i;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class WorldRef {

    private final World world;
    private final Vect3i position;

    public WorldRef(World world, Vect3i position) {
        this.world = world;
        this.position = position.copy();
    }

    public WorldRef(World worldIn, BlockPos pos) {
        this(worldIn, new Vect3i(pos));
    }

    public WorldRef create(Vect3i newpos){
        return new WorldRef(world, newpos);
    }

    public World getWorld() {
        return world;
    }

    public Vect3i getPosition() {
        return position.copy();
    }

    public BlockPos getBlockPos(){
        return position.toBlockPos();
    }

    public WorldRef copy() {
        return new WorldRef(world, position);
    }

    public WorldRef move(Vect3i dir) {
        return new WorldRef(world, getPosition().add(dir));
    }

    public WorldRef move(Direction dir) {
        return move(dir.toVect3i());
    }

	public IBlockState getBlockState() {
		return world.getBlockState(position.toBlockPos());
	}

	public void setBlockState(IBlockState state, int flags) {
		world.setBlockState(position.toBlockPos(), state, flags);
	}

	public void setBlockState(IBlockState state) {
		world.setBlockState(position.toBlockPos(), state, 3);
	}

    public void setAir() {
        world.setBlockToAir(position.toBlockPos());
    }

    public TileEntity getTileEntity() {
        return world.getTileEntity(position.toBlockPos());
    }

    public long getWorldTime() {
        return world.getWorldTime();
    }
}
