package controller;

import java.util.List;

import business.Address;
import business.Auth;
import business.CheckInOutRecord;
import business.Item;
import business.Member;
import business.User;
import business.CheckInOutRecord.RECORDSTATUS;
import javafx.collections.ObservableList;
import login.cor.LoginException;

public interface ControllerInterface {
	public Auth login(String id, String password) throws LoginException;

	public void librarianLogin(String memberId, String isbn) throws LoginException;

	public void memberRecord(String id) throws LoginException;

	public void bookRecord(String isbn) throws LoginException;

	public List<String> allMemberIds();

	public List<String> allBookIds();

	public ObservableList<Member> allMembers();

	public List<Address> addresses();

	public boolean addMember(String memId, String fName, String lName, String phone, Address addr);

	public Address createAddress(String street, String city, String state, String zip);

	public boolean removeMember(String memId, String fName, String lName, String phone, Address addr);

	public boolean updateMember(String memId, String fName, String lName, String phone, Address addr);
	
	void loadItemMap(List<Item> items);
	
	public List<Item> items() ;
	
	public boolean addUser(String id, String password,Auth type);
	
	public void updateUser(String id, String password,Auth type) ;
	
	public void deleteUser(String id, String password,Auth type) ;
	
	public ObservableList<User> allUsers(); 
	
	public Auth[] allAuth();

	public void updateCheckInOutRecordStatus(CheckInOutRecord checkInOutRecord, RECORDSTATUS status);

	void addCheckInOutRecordStatus(CheckInOutRecord checkInOutRecord);

	void deleteCheckInOutRecord(CheckInOutRecord checkInOutRecord);

	List<CheckInOutRecord> getAllCheckInOutRecords();

	List<CheckInOutRecord> getCheckInOutRecordBy(Member member, Item item);
}
