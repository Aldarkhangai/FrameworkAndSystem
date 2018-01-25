package databaseLayer.dao;

import java.util.HashMap;

import business.Address;
import business.Member;
import config.Config;

public class AddressData implements IDataFacade<Address>{
	private String STORAGE = Config.getInstance().getAddressStorage();
	@SuppressWarnings("unchecked")
	@Override
	public HashMap<String,Address> getElements() {
		return (HashMap<String, Address>) readFromStorage(STORAGE);
	}

	@Override
	public boolean add(Address address, String Type) {
		HashMap<String, Address> addressMap = null;
		if (getElements() == null)
			addressMap = new HashMap<String, Address>();
		else
			addressMap = getElements();

		String zip = address.getZip();
		addressMap.put(zip, address);
		saveToStorage(Type, addressMap);
		return true;
	}

	@Override
	public boolean update(Address address, String Type) {
		HashMap<String, Address> addressMap = getElements();
		for (Address m : addressMap.values()) {
			if (m.getZip().equals(address.getZip()))
				addressMap.remove(address.getZip());
			addressMap.put(address.getZip(), address);
		}
		saveToStorage(STORAGE, addressMap);
		return true;
	}

	@Override
	public boolean delete(Address address, String Type) {
		HashMap<String, Address> addressMap = getElements();
		for (Address m : addressMap.values()) {
			if (m.getZip().equals(address.getZip()))
				addressMap.remove(address.getZip());
		}
		saveToStorage(STORAGE, addressMap);
		return false;
	}


	@Override
	public Address search(String id) {
		HashMap<String, Address> addressMap = getElements();
		Address m = addressMap.get(id);
		return m;
	}

}
