package databaseLayer.command;

import databaseLayer.dao.IDataFacade;

public class UpdateCommand implements ICommand {
	private IDataFacade dataManager;
    private Object object;
    private String type;

	@Override
    public boolean execute() {
        return dataManager.update(object,type);
    }

    @Override
    public boolean undo() {
        return false;
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
