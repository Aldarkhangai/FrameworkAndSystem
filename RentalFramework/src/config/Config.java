package config;

public class Config {
	private String addressStorage = "ADDRESSES";
	private String checkInOutRecordStorage = "CHECKINOUTRECORDS";
	private String itemStorage = "ITEMS";
	private String memberStorage = "MEMBERS";
	private String userStorage = "USERS";

	private static class InnerConfig {
		private static Config configInstance = new Config();

	}

	public static Config getInstance() {
		return InnerConfig.configInstance;
	}

	public String getAddressStorage() {
		return addressStorage;
	}

	public void setAddressStorage(String addressStorage) {
		this.addressStorage = addressStorage;
	}

	public String getCheckInOutRecordStorage() {
		return checkInOutRecordStorage;
	}

	public void setCheckInOutRecordStorage(String checkInOutRecordStorage) {
		this.checkInOutRecordStorage = checkInOutRecordStorage;
	}

	public String getItemStorage() {
		return itemStorage;
	}

	public void setItemStorage(String itemStorage) {
		this.itemStorage = itemStorage;
	}

	public String getMemberStorage() {
		return memberStorage;
	}

	public void setMemberStorage(String memberStorage) {
		this.memberStorage = memberStorage;
	}

	public String getUserStorage() {
		return userStorage;
	}

	public void setUserStorage(String userStorage) {
		this.userStorage = userStorage;
	}

	@Override
	public String toString() {
		return "InnerConfig [addressStorage=" + addressStorage + ", checkInOutRecordStorage=" + checkInOutRecordStorage
				+ ", itemStorage=" + itemStorage + ", memberStorage=" + memberStorage + ", userStorage=" + userStorage
				+ "]";
	}
}
