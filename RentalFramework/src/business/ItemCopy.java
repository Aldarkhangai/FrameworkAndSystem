package business;

import java.io.Serializable;

public class ItemCopy implements Serializable {
	/**
	 * Ely
	 */
	private static final long serialVersionUID = 1L;
	private int copyNum;
	private boolean isAvailable;
	private Item item;

	public ItemCopy(int copyNum, boolean isAvailable, Item item) {
		super();
		this.copyNum = copyNum;
		this.isAvailable = isAvailable;
		this.item = item;
	}

	public int getCopyNum() {
		return copyNum;
	}

	public void setCopyNum(int copyNum) {
		this.copyNum = copyNum;
	}

	public boolean isAvailable() {
		return isAvailable;
	}

	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

}