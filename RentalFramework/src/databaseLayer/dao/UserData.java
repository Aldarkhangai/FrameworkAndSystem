package databaseLayer.dao;
import java.util.HashMap;
import business.*;
import config.Config;
public class UserData implements IDataFacade<User>{
	
	private String STORAGE = Config.getInstance().getUserStorage();

	@SuppressWarnings("unchecked")
	@Override
	public HashMap<String, User> getElements() {
		return (HashMap<String, User>)readFromStorage(STORAGE);
	}

	@Override
	public User search(String id) {
		HashMap<String, User> userMap = getElements();
		User m = userMap.get(id);
		return m;
	}

	@Override
	public boolean add(User user, String Type) {
		HashMap<String, User> userMap = null;
		if (getElements() == null)
			userMap = new HashMap<String, User>();
		else
			userMap = getElements();

		String keyItem = user.getId();
		if (userMap.keySet().contains(keyItem))
			return false;
		else
			userMap.put(keyItem, user);
		saveToStorage(STORAGE, userMap);
		return true;
	}

	@Override
	public boolean update(User user, String Type) {
		HashMap<String, User> hashMapUser = getElements();
		String keyUser = user.getId();
		if (hashMapUser.keySet().contains(keyUser)) {
			hashMapUser.replace(keyUser, user);
			saveToStorage(STORAGE, hashMapUser);
			return true;
		} else
			return false;
	}

	@Override
	public boolean delete(User user, String Type) {
		HashMap<String, User> hashMapUser = getElements();
		String keyUser = user.getId();
		if (hashMapUser.keySet().contains(keyUser)) {
			hashMapUser.remove(keyUser);
			saveToStorage(STORAGE, hashMapUser);
			return true;
		} else
			return false;
	}

}
