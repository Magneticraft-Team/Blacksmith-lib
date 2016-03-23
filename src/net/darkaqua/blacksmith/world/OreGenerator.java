package net.darkaqua.blacksmith.world;

import com.google.common.base.Predicate;
import net.darkaqua.blacksmith.util.WorldRef;
import net.darkaqua.blacksmith.vectors.Vect3i;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

import java.util.Random;

/**
 * Created by cout970 on 18/12/2015.
 */
public class OreGenerator {

    protected IBlockState ore;
    protected int blocksPerVein;
    protected Predicate<IBlockState> predicate;

    public OreGenerator(Block ore, int number) {
        this(ore.getDefaultState(), number, input -> input != null && input.getBlock().equals(Blocks.stone)
        );
    }

    public OreGenerator(IBlockState ore, int number) {
        this(ore, number, input -> input != null && input.getBlock().equals(Blocks.stone)
        );
    }

    public OreGenerator(IBlockState ore, int number, Predicate<IBlockState> target) {
        this.ore = ore;
        blocksPerVein = number;
        predicate = target;
    }

    public OreGenerator(Block ore, int meta, int number, Predicate<IBlockState> target) {
        this(ore.getStateFromMeta(meta), number, target);
    }

    public void generate(WorldRef ref, Random random) {
        generate(ref.getWorld(), random, ref.getPosition().getX(), ref.getPosition().getY(), ref.getPosition().getZ());
    }

    public void generate(World world, Random random, int x, int y, int z) {
        float angle = random.nextFloat() * (float) Math.PI;
        double posX = (x + 8) + Math.sin(angle) * blocksPerVein / 8.0F;
        double negX = (x + 8) - Math.sin(angle) * blocksPerVein / 8.0F;
        double posZ = (z + 8) + Math.cos(angle) * blocksPerVein / 8.0F;
        double negZ = (z + 8) - Math.cos(angle) * blocksPerVein / 8.0F;
        double y1 = y + random.nextInt(3) - 2;
        double y2 = y + random.nextInt(3) - 2;

        for (int n = 0; n <= blocksPerVein; ++n) {

            double xPlace = posX + (negX - posX) * n / blocksPerVein;
            double yPlace = y1 + (y2 - y1) * n / blocksPerVein;
            double zPlace = posZ + (negZ - posZ) * n / blocksPerVein;
            double scale = random.nextDouble() * blocksPerVein / 16.0D;
            double desp = (Math.sin(n * Math.PI / blocksPerVein) + 1.0F) * scale + 1.0D;

            int minX = floor_double(xPlace - desp / 2.0D);
            int minY = floor_double(yPlace - desp / 2.0D);
            int minZ = floor_double(zPlace - desp / 2.0D);
            int maxX = floor_double(xPlace + desp / 2.0D);
            int maxY = floor_double(yPlace + desp / 2.0D);
            int maxZ = floor_double(zPlace + desp / 2.0D);

            for (int i = minX; i <= maxX; ++i) {
                double xDistance = ((double) i + 0.5D - xPlace) / (desp / 2.0D);

                if (xDistance * xDistance < 1.0D) {
                    for (int j = minY; j <= maxY; ++j) {
                        double yDistance = ((double) j + 0.5D - yPlace) / (desp / 2.0D);

                        if (xDistance * xDistance + yDistance * yDistance < 1.0D) {
                            for (int k = minZ; k <= maxZ; ++k) {

                                double zDistance = ((double) k + 0.5D - zPlace) / (desp / 2.0D);
                                Vect3i blockPos = new Vect3i(i, j, k);
                                WorldRef ref = new WorldRef(world, blockPos);

                                if (xDistance * xDistance + yDistance * yDistance + zDistance * zDistance < 1.0D) {
                                    if (ref.getBlockState().getBlock().isReplaceableOreGen(ref.getWorld(), ref.getPosition().toBlockPos(), predicate)) {
                                        ref.setBlockState(ore, 2);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public static int floor_double(double value) {
        int i = (int) value;
        return value < (double) i ? i - 1 : i;
    }

    public IBlockState getOre() {
        return ore;
    }

    public int getBlocksPerVein() {
        return blocksPerVein;
    }

    public void setBlocksPerVein(int blocksPerVein) {
        this.blocksPerVein = blocksPerVein;
    }
}
