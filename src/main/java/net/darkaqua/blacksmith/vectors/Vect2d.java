package net.darkaqua.blacksmith.vectors;

import net.minecraft.nbt.NBTTagCompound;

import javax.annotation.Nonnull;

/**
 * Created by cout970 on 15/12/2015.
 */
public class Vect2d implements Comparable<Vect2d> {

    protected double x;
    protected double y;

    public Vect2d(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Vect2d(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Vect2d(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public Vect2d(Vect2d vec) {
        this(vec.getX(), vec.getY());
    }

    public Vect2d(NBTTagCompound pos) {
        this(pos.getDouble("x"), pos.getDouble("y"));
    }

    public Vect2d(int[] ar) {
        this(ar[0], ar[1]);
    }

    public Vect2d(float[] ar) {
        this(ar[0], ar[1]);
    }

    public Vect2d(double[] ar) {
        this(ar[0], ar[1]);
    }

    public static Vect2d nullVector() {
        return new Vect2d(0, 0);
    }

    public Vect2d getOpposite() {
        return new Vect2d(-x, -y);
    }

    public Vect2d set(double x, double y) {
        this.x = x;
        this.y = y;
        return this;
    }

    public double getX() {
        return this.x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return this.y;
    }

    public void setY(double y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return String.format("Vect2d: x: %.3f, y: %.3f", getX(), getY());
    }

    public Vect2d add(Vect2d v) {
        x += v.x;
        y += v.y;
        return this;
    }

    public Vect2d sub(Vect2d v) {
        x -= v.x;
        y -= v.y;
        return this;
    }

    public Vect2d multiply(Vect2d i) {
        x *= i.getX();
        y *= i.getY();
        return this;
    }

    public Vect2d div(Vect2d i) {
        x /= i.getX();
        y /= i.getY();
        return this;
    }

    public Vect2d add(double a, double b) {
        x += a;
        y += b;
        return this;
    }

    public Vect2d sub(double a, double b) {
        x -= a;
        y -= b;
        return this;
    }

    public Vect2d multiply(double i) {
        x *= i;
        y *= i;
        return this;
    }

    public Vect2d div(double i) {
        x /= i;
        y /= i;
        return this;
    }

    public Vect2d abs() {
        x = Math.abs(x);
        y = Math.abs(y);
        return this;
    }

    public Vect2d copy() {
        return new Vect2d(x, y);
    }

    /**
     * Returns a array of doubles with the components of the vector
     */
    public double[] doubleArray() {
        return new double[]{x, y};
    }

    /**
     * Returns the magnitude squared of the vector
     */
    public double magSquared() {
        return x * x + y * y;
    }

    /**
     * Returns the magnitude of the vector
     */
    public double mag() {
        return Math.sqrt(magSquared());
    }

    /**
     * Returns the distance from this point to the point specified by the first argument
     */
    public double distance(Vect2d vector) {
        Vect2d line = vector.copy().add(getOpposite());
        return Math.sqrt(line.magSquared());
    }

    public double dotProduct(Vect2d vec) {
        return vec.x * x + vec.y * y;
    }

    /**
     * Creates a new vector2d with magnitude equals 0
     */
    public Vect2d unitVector() {
        if (isNullVector())
            return nullVector();
        return this.copy().multiply(1 / mag());
    }

    public Vect2d normalize() {
        double mag = mag();
        if (mag != 0) {
            this.multiply(1 / mag);
        }
        return this;
    }

    public boolean isNullVector() {
        return x == 0 && y == 0;
    }

    public boolean isNullVector(double tolerance) {
        return Math.abs(x) <= tolerance && Math.abs(y) <= tolerance;
    }

    public double angle(Vect2d vec) {
        double aux = mag() * vec.mag();
        if (aux == 0)
            return 0;
        return Math.acos(dotProduct(vec) / aux);
    }

    public NBTTagCompound save() {
        NBTTagCompound list = new NBTTagCompound();
        list.setDouble("x", x);
        list.setDouble("y", y);
        return list;
    }

    @Override
    public int compareTo(@Nonnull Vect2d vec) {
        return (getY() == vec.getY() ? getX() - vec.getX() : getY() - vec.getY()) > 0 ? 1 : -1;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (!(o instanceof Vect2d)) return false;

        Vect2d vect2d = (Vect2d) o;

        return Double.compare(vect2d.x, x) == 0 && Double.compare(vect2d.y, y) == 0;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(x);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(y);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
