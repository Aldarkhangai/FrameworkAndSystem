package databaseLayer.command;

import databaseLayer.*;

import java.util.HashMap;
import java.util.Map;


public class StoreCommandFactory implements ICommandFactory {
    private static ICommandFactory instance = new StoreCommandFactory();
    private static Map<ObjectState, ICommand> storeCommands = new HashMap<>();

    static {
        storeCommands.put(ObjectState.INSERTED, new InsertCommand());
        storeCommands.put(ObjectState.UPDATED, new UpdateCommand());
        storeCommands.put(ObjectState.DELETED, new DeleteCommand());
    }

    private StoreCommandFactory(){}

    public static ICommandFactory getInstance(){
        return instance;
    }

	@Override
	public ICommand createCommand(ObjectState oState) {
        if(storeCommands.containsKey(oState))
            return storeCommands.get(oState);
        return null;
	}
}
