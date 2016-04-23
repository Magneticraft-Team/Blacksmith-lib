package net.darkaqua.blacksmith.block;

import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;

/**
 * Created by cout970 on 06/02/2016.
 */
public interface IRotatableBlock {
    PropertyDirection ROTATION = PropertyDirection.create("rotation");

    default EnumFacing[] getValidRotations() {
        return EnumFacing.VALUES;
    }

    default EnumFacing getCurrentRotation(IBlockState data) {
        return data.getValue(ROTATION);
    }

    default IBlockState stepRotation(IBlockState data) {
        return data.cycleProperty(ROTATION);
    }

    default IBlockState withRotation(IBlockState data, EnumFacing dir) {
        return data.withProperty(ROTATION, dir);
    }
}
