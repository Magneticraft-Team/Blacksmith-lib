package net.darkaqua.blacksmith.storage;


/**
 * Created by cout970 on 29/12/2015.
 */
public interface IDataStorage {

    void loadData(IDataCompound data);

    void saveData(IDataCompound data);
}
