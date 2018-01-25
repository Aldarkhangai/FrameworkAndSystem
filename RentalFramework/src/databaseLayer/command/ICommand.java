package databaseLayer.command;

import databaseLayer.dao.IDataFacade;

public interface ICommand {
	boolean execute();
	boolean undo();
    void setElement(Object object);
    void setDataManager(IDataFacade dataManager);
}
