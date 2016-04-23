package net.darkaqua.blacksmith.block;

import com.google.common.collect.ImmutableList;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;

/**
 * Created by cout970 on 20/03/2016.
 */
public interface IStatefulBlock {

    default IBlockState getStateFromMetadata(int meta) {
        return getBlockState().getValidStates().get(meta);
    }

    default int getMetaFromBlockState(IBlockState state){
        ImmutableList<IBlockState> states = getBlockState().getValidStates();
        for (int i = 0; i < states.size(); i++) {
            if (state == states.get(i)){
                return i;
            }
        }
        return 0;
    }

    BlockState getBlockState();
}
