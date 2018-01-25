package login.cor;

public abstract class CorAbstractLogin {
	protected CorAbstractLogin nextProcess;
	
	abstract public void handleRequest(String id, String password);

	boolean canHandleRequest(String id, String password) {

		if (id == "") {
			return true;
		}

		return false;

	}
}
