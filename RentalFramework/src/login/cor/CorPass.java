package login.cor;

import java.util.HashMap;
import business.User;
import databaseLayer.dao.UserData;

public class CorPass extends CorAbstractLogin {

	@Override
	public void handleRequest(String id, String password) {
		UserData da = new UserData();
		HashMap<String, User> map = da.getElements();
		String passwordFound = map.get(id).getPassword();
		if (!passwordFound.equals(password)) {
			CorProcessBuilder.value = 3;
		}
		

		else if (this.nextProcess != null)
		{	
			CorProcessBuilder.value = 0;
			this.nextProcess.handleRequest(id,password);
		}
			
		//return 10;
		}

}
