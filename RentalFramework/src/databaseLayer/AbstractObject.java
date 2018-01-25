package databaseLayer;

public class AbstractObject {
	private ObjectState elementState = ObjectState.NONE;

	public ObjectState getElementState() {
		return elementState;
	}

	public void setElementState(ObjectState aElementState) {
		elementState = aElementState;
	}
}
