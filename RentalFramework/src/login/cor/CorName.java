package login.cor;

import java.util.HashMap;

import business.User;
import databaseLayer.dao.UserData;

public class CorName extends CorAbstractLogin{

	@Override
	public void handleRequest(String id, String password) {		
		UserData da = new UserData();
		HashMap<String, User> map = da.getElements();
		
		if (!map.containsKey(id)) {						
			CorProcessBuilder.value = 2;
		}			
		else if (this.nextProcess != null)
		{	
			CorProcessBuilder.value = 0;
			this.nextProcess.handleRequest(id,password);
		}
	}
}
