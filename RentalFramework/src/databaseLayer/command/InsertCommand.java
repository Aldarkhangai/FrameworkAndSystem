package databaseLayer.command;

import databaseLayer.dao.IDataFacade;

public class InsertCommand implements ICommand {
	private IDataFacade dataManager;
	private Object object;
	private String type;

	@Override
	public boolean execute() {
		return dataManager.add(object, type);
	}

	@Override
	public void setElement(Object object) {
		this.object = object;
	}

	@Override
	public void setDataManager(IDataFacade dataManager) {
		this.dataManager = dataManager;
	}

	@Override
	public boolean undo() {
		return dataManager.delete(object, type);
	}
}
