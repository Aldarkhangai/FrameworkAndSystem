package databaseLayer.dao;

import java.util.HashMap;

import business.Item;

public class ItemData implements IDataFacade<Item> {

	@SuppressWarnings("unchecked")
	@Override
	public HashMap<String, Item> getElements() {
		// TODO Auto-generated method stub
		return (HashMap<String, Item>) readFromStorage("ITEMS");
	}

	@Override
	public Item search(String id) {
		// TODO Auto-generated method stub
		HashMap<String, Item> hashMapBook = getElements();
		if (hashMapBook.keySet().contains(id))
			return hashMapBook.get(id);

		return null;
	}

	@Override
	public boolean add(Item element, String Type) {
		// TODO Auto-generated method stub
		HashMap<String, Item> hashMapItem = getElements();
		String keyItem = element.getId();
		if (hashMapItem.keySet().contains(keyItem))
			return false;
		else
			hashMapItem.put(keyItem, element);
		saveToStorage("ITEMS", hashMapItem);
		return true;

	}

	@Override
	public boolean update(Item element, String Type) {
		// TODO Auto-generated method stub
		HashMap<String, Item> hashMapItem = getElements();
		String keyItem = element.getId();
		if (hashMapItem.keySet().contains(keyItem)) {
			hashMapItem.replace(keyItem, element);
			saveToStorage("ITEMS", hashMapItem);
			return true;
		} else
			return false;
	}

	@Override
	public boolean delete(Item element, String Type) {
		// TODO Auto-generated method stub
		HashMap<String, Item> hashMapItem = getElements();
		String keyItem = element.getId();
		if (hashMapItem.keySet().contains(keyItem)) {
			hashMapItem.remove(keyItem);
		} else
			return false;

		return false;
	}

}
