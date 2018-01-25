package databaseLayer.command;

import databaseLayer.*;

public interface ICommandFactory {
	ICommand createCommand(ObjectState oState);
}
