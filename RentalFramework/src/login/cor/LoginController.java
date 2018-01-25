package login.cor;

import java.util.HashMap;

import business.Auth;
import business.User;
import databaseLayer.dao.UserData;

public class LoginController{
	
	private static LoginController instance = new LoginController();

	private LoginController() {
	}

	public static LoginController getInstance() {
		return instance;
	}
	
	private CorProcessBuilder chain;
	
	public Auth login(String id, String password)  throws LoginException{
		
		LoginController client = new LoginController();
		client.chain = new CorProcessBuilder();
		client.sendRequest(id,password);
		
		switch(chain.value)
		{
			case 1:	throw new LoginException("ID or Password empty");
			case 2:	throw new LoginException("ID " + id + " not found");
			case 3:	throw new LoginException("Password incorrect");
		}
		
				
			UserData da = new UserData();
			HashMap<String, User> map = da.getElements();
			return map.get(id).getAuthorization();
		
	}
	public void sendRequest(String id, String password) {
		chain.getHandler().handleRequest(id,password);
	}	

}
