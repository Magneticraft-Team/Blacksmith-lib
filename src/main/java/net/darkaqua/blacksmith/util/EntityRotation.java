package net.darkaqua.blacksmith.util;


import net.darkaqua.blacksmith.vectors.Vect3d;
import net.minecraft.entity.Entity;
import net.minecraft.util.EnumFacing;

/**
 * Created by cout970 on 11/01/2016.
 */
public class EntityRotation {

    private float pitch;
    private float yaw;

    public EntityRotation(float pitch, float yaw) {
        this.pitch = pitch;
        this.yaw = yaw;
    }

    public EntityRotation(double pitch, double yaw) {
        this.pitch = (float) pitch;
        this.yaw = (float) yaw;
    }

    public EntityRotation(Vect3d look) {
        Vect3d vec = look.copy().normalize();
        //TODO test
        this.pitch = (float) Math.atan2(vec.getY(), vec.getZ());
        this.yaw = (float) Math.atan2(vec.getX(), vec.getZ());
    }

    public EntityRotation(Entity entity) {
        this(entity.rotationPitch, entity.rotationYaw);
    }

    public static float wrapTo180(float angle) {
        angle %= 360.0F;

        if (angle >= 180.0F) {
            angle -= 360.0F;
        }

        if (angle < -180.0F) {
            angle += 360.0F;
        }

        return angle;
    }

    public float getPitch() {
        return pitch;
    }

    public void setPitch(float pitch) {
        this.pitch = pitch;
    }

    public float getYaw() {
        return yaw;
    }

    public void setYaw(float yaw) {
        this.yaw = yaw;
    }

    public float getYawWrappedTo180() {
        return wrapTo180(yaw);
    }

    public EnumFacing toHorizontalAxis() {
        float yaw = getYawWrappedTo180();
        if ((yaw < 45 && yaw >= 0) || (yaw > -45 && yaw <= 0)) {
            return EnumFacing.SOUTH;
        } else if (yaw >= 45 && yaw < 135) {
            return EnumFacing.WEST;
        } else if (yaw <= -45 && yaw > -135) {
            return EnumFacing.EAST;
        } else if ((yaw >= 135 && yaw <= 180) || (yaw <= -135 && yaw >= -180)) {
            return EnumFacing.NORTH;
        }
        throw new IllegalStateException("Invalid yaw: " + yaw);
    }

    public Vect3d getLookVector(){
        double cos0 = Math.cos(-yaw * 0.017453292F - Math.PI);
        double sin0 = Math.sin(-yaw * 0.017453292F - Math.PI);
        double cos1 = -Math.cos(-pitch * 0.017453292F);
        double sin1 = Math.sin(-pitch * 0.017453292F);
        return new Vect3d(sin0 * cos1, sin1, cos0 * cos1);
    }
}
