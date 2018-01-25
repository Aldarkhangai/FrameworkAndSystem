package databaseLayer.command;

import databaseLayer.dao.IDataFacade;
import databaseLayer.dao.MemberData;

public class DeleteCommand implements ICommand {

	private IDataFacade dataManager;
	private Object object;
	private String type;
	@Override
	public boolean execute() {
		return dataManager.delete(object,type);
	}

	@Override
	public boolean undo() {
		return dataManager.add(object,type);
	}

	@Override
	public void setElement(Object object) {
		this.object = object;
	}

	@Override
	public void setDataManager(IDataFacade dataManager) {
		this.dataManager = dataManager;
	}

}
