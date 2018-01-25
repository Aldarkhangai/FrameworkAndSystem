package login.cor;

public class CorProcessBuilder {
	private CorAbstractLogin handler;
	public static int value = 0;
	// other necessary code here
	public CorProcessBuilder() {
		buildChain();
	}

	private void buildChain() {
		CorAbstractLogin corNamePass = new CorNamePass();
		CorAbstractLogin corName = new CorName();
		CorAbstractLogin corPass = new CorPass();		
		corNamePass.nextProcess = corName;
		corName.nextProcess = corPass;
		handler = corNamePass;
	}

	public CorAbstractLogin getHandler() {
		return handler;
	}

	public void setHandler(CorAbstractLogin handler) {
		this.handler = handler;
	}
}
