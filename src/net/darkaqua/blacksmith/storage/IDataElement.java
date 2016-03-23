package net.darkaqua.blacksmith.storage;

public interface IDataElement {

    byte getID();

    IDataElement copy();

    Object getInternalObject();
}
