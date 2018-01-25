package login.cor;

public class CorNamePass extends CorAbstractLogin {

	@Override
	public void handleRequest(String id, String password) {
		if(id.equals("") || password.equals(""))
		{			
			CorProcessBuilder.value = 1;
		}	
		else if (this.nextProcess != null)
		{	
			CorProcessBuilder.value = 0;
			this.nextProcess.handleRequest(id,password);
		}


	}

}