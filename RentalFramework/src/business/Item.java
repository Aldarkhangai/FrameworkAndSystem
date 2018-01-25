package business;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import iteratorItemCopy.AgregatateItemCopy;
import iteratorItemCopy.Iterator;

public class Item implements Serializable, AgregatateItemCopy {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String name;
	private String description;
	private List<ItemCopy> itemCopies;

	public Item(String id, String name, String description) {
		this.id = id;
		this.name = name;
		this.description = description;
		itemCopies = new ArrayList<>();
	}

	public String getId() {
		return id;
	}
	public int getNumCopies() {
		int i =0;
		for(ItemCopy c :itemCopies) {
			if(c.isAvailable()) i++;
		}
		return i;
		
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<ItemCopy> getItemCopies() {
		return itemCopies;
	}

	public void setItemCopies(List<ItemCopy> itemCopies) {
		this.itemCopies = itemCopies;
	}

	public void addCopy() {
		ItemCopy copy = new ItemCopy(itemCopies.size(), true, this);
		itemCopies.add(copy);

	}

	public boolean isAvailable() {
		if (itemCopies == null) {
			return false;
		}
		return itemCopies.stream().map(l -> l.isAvailable()).reduce(false, (x, y) -> x || y);
	}

	@Override
	public String toString() {
		return " ID: " + id + ",  available: " + isAvailable() + " ";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Item other = (Item) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public Iterator getIterator() {
		return new ItemCopyIterator();
	}

	private class ItemCopyIterator implements Iterator {
		int index;

		@Override
		public boolean hasNext() {

			if (index < itemCopies.size()) {
				return true;
			}
			return false;
		}

		@Override
		public Object next() {

			if (this.hasNext()) {
				return itemCopies.get(index++);
			}
			return null;
		}
	}
}
