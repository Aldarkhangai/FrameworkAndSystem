package databaseLayer.dao;

import java.util.HashMap;

import business.*;
import config.Config;

public class CheckInOutRecordData implements IDataFacade<CheckInOutRecord> {

	private String STORAGE = Config.getInstance().getCheckInOutRecordStorage();

	@SuppressWarnings("unchecked")
	@Override
	public HashMap<String, CheckInOutRecord> getElements() {
		return (HashMap<String, CheckInOutRecord>) readFromStorage(STORAGE);
	}

	@Override
	public boolean add(CheckInOutRecord checkInOutRecord, String Type) {
		HashMap<String, CheckInOutRecord> checkInOutRecords = null;
		if (getElements() == null)
			checkInOutRecords = new HashMap<String, CheckInOutRecord>();
		else
			checkInOutRecords = getElements();
		checkInOutRecords.put(checkInOutRecord.getId(), checkInOutRecord);
		saveToStorage(STORAGE, checkInOutRecords);
		return true;
	}

	@Override
	public boolean update(CheckInOutRecord checkInOutRecord, String Type) {
		HashMap<String, CheckInOutRecord> checkInOutRecords = getElements();
		if (checkInOutRecords.containsKey(checkInOutRecord.getId()))
			checkInOutRecords.remove(checkInOutRecord.getId());
		checkInOutRecords.put(checkInOutRecord.getId(), checkInOutRecord);
		saveToStorage(STORAGE, checkInOutRecords);
		return true;
	}

	@Override
	public boolean delete(CheckInOutRecord checkInOutRecord, String Type) {
		HashMap<String, CheckInOutRecord> checkInOutRecords = getElements();
		if (checkInOutRecords.containsKey(checkInOutRecord.getId()))
			checkInOutRecords.remove(checkInOutRecord.getId());
		saveToStorage(STORAGE, checkInOutRecords);
		return true;
	}

	@Override
	public CheckInOutRecord search(String id) {
		HashMap<String, CheckInOutRecord> checkInOutRecords = getElements();
		CheckInOutRecord m = checkInOutRecords.get(id);
		return m;
	}
}
