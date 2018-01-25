package ui;

import databaseLayer.DBConnectionSql;
import business.*;
import controller.SystemController;
import databaseLayer.command.*;
import databaseLayer.dao.IDataFacade;
import databaseLayer.dao.MemberData;
import databaseLayer.dao.UserData;
public class Test {

	public static void main(String[] args) {
//	//	DatabaseFactory factory = DatabaseFactory.getFactory();
//	 //  DBConnectionSql connection = factory.createConnection(DatabaseType.FILE, "Z:\\Project\\RentalFramework\\dataAccess\\storage", "", "");
//	//	IDataAccess facade = new DataAccessFacade(connection);
//		Address addr = new Address("AA", "FAIRFIEL", "IOWA", "52557");
////		Member member = new Member("0019", "John", "Wall", "123-123545", addr);
////		IDataFacade manager = new MemberData();
////		manager.add(member, "MEMBERS");
//		
//		SystemController controller = new SystemController();
//		controller.addUser("101", "123", Auth.ADMIN);
//		
////		User user = new User("101", "123", Auth.BOTH);
////		DataInvoke invoke = new DataInvoke();
////		IDataFacade manager = new MemberData();
////		UserData userdata = new UserData();
////		ICommand commandInsert = new InsertCommand();
////		commandInsert.setDataManager(userdata);
////		commandInsert.setElement(user);
////		invoke.setCommand(commandInsert);
////		invoke.execute();
//		
//		//System.out.println(userdata.getElements());
//		DataInvoke invoke = new DataInvoke();
//		ICommand insertCommand = new InsertCommand();
//		insertCommand.setDataManager(new UserData());
//		insertCommand.setElement(new User("103", "123", Auth.ADMIN));
//		invoke.setCommand(insertCommand);
//		invoke.execute();
		
//		DataInvoke invoke = new DataInvoke();
//		ICommand deleteCommand = new DeleteCommand();
//		deleteCommand.setDataManager(new UserData());
//		deleteCommand.setElement(new User("103", "123", Auth.ADMIN));
//		invoke.setCommand(deleteCommand);
//		invoke.execute();
		SystemController c = new SystemController();
		c.updateUser("102", "1234", Auth.ADMIN);
		c.addUser("103", "1234", Auth.BOTH);
		c.deleteUser("103", "1234", Auth.BOTH);
		System.out.println(c.allUsers());
		}
}
