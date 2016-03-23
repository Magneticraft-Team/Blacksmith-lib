package net.darkaqua.blacksmith.block;

import com.google.common.collect.Lists;
import net.darkaqua.blacksmith.raytrace.Cube;
import net.darkaqua.blacksmith.raytrace.RayTraceResult;
import net.darkaqua.blacksmith.raytrace.RayTraceUtil;
import net.darkaqua.blacksmith.util.WorldRef;
import net.darkaqua.blacksmith.vectors.Vect3d;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.IBlockAccess;

import java.util.List;

/**
 * Created by cout970 on 20/03/2016.
 */
public interface IBlockBoxes {

    default Cube getSelectionCube(WorldRef ref) {
        return getBounds(ref);
    }

    /**
     * This method gets the collision cubes used to collide entities with the block
     *
     * @param ref    the block reference
     * @param entity the entity colliding, may be null
     * @return the collision cubes
     */
    default List<Cube> getCollisionCubes(WorldRef ref, Entity entity) {
        return Lists.newArrayList(getBounds(ref));
    }

    /**
     * This method checks if the ray(start, end) collides with this block and returns
     * the point where the ray intersects with this block
     *
     * @param ref the block reference
     * @param start the start point of the ray
     * @param end the end point of the ray
     * @return the result of the ray trace
     */
    default RayTraceResult rayTraceBlock(WorldRef ref, Vect3d start, Vect3d end){

        for(Cube c : getCollisionCubes(ref, null)){
            RayTraceResult res = RayTraceUtil.collisionRayTrace(ref.getPosition(), c, start, end);
            if (res != null) {
                return res;
            }
        }
        return null;
    }

    default Cube getBounds(WorldRef ref){
        return getBounds(ref.getWorld(), ref.getBlockPos(), ref.getBlockState());
    }

    default Cube getBounds(IBlockAccess worldAccess, BlockPos pos, IBlockState state){
        return Cube.fullBlock();
    }
}
