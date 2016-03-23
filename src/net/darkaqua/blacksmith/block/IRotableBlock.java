package net.darkaqua.blacksmith.block;


import net.darkaqua.blacksmith.util.Direction;
import net.minecraft.block.state.IBlockState;

/**
 * Created by cout970 on 06/02/2016.
 */
public interface IRotableBlock {

    Direction[] getValidRotations();

    Direction getActualRotation(IBlockState data);

    IBlockState stepRotation(IBlockState data);

    IBlockState withRotation(IBlockState data, Direction dir);
}
