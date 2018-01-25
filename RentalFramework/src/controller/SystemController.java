package controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import business.Address;
import business.Auth;
import business.CheckInOutRecord;
import business.CheckInOutRecord.RECORDSTATUS;
import business.Item;
import business.Member;
import business.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import login.cor.LoginController;
import login.cor.LoginException;
import databaseLayer.ObjectState;
import databaseLayer.command.DataInvoke;
import databaseLayer.command.DeleteCommand;
import databaseLayer.command.ICommand;
import databaseLayer.command.ICommandFactory;
import databaseLayer.command.InsertCommand;
import databaseLayer.command.StoreCommandFactory;
import databaseLayer.command.UpdateCommand;
import databaseLayer.dao.AddressData;
import databaseLayer.dao.CheckInOutRecordData;
import databaseLayer.dao.IDataFacade;
import databaseLayer.dao.ItemData;
import databaseLayer.dao.MemberData;
import databaseLayer.dao.UserData;

public class SystemController implements ControllerInterface {
	public static Auth currentAuth = null;

	private Member member;
	private Item book;

	private static LoginController instance = LoginController.getInstance();

	public Auth login(String id, String password) throws LoginException {
		return currentAuth = instance.login(id, password);
	}

	@Override
	public List<String> allMemberIds() {
		MemberData da = new MemberData();
		List<String> retval = new ArrayList<>();
		retval.addAll(da.getElements().keySet());
		return retval;
	}

	@Override
	public List<String> allBookIds() {
		ItemData da = new ItemData();
		List<String> retval = new ArrayList<>();
		retval.addAll(da.getElements().keySet());
		return retval;
	}

	public void librarianLogin(String id, String isbn) throws LoginException {
		MemberData daMember = new MemberData();
		ItemData daItem = new ItemData();
		@SuppressWarnings("unchecked")
		HashMap<String, Member> map = daMember.getElements();
		if (!map.containsKey(id)) {
			throw new LoginException("ID " + id + " not found");
		}

		HashMap<String, Member> membersHashMap = new HashMap<String, Member>();
		membersHashMap = daMember.getElements();

		if (membersHashMap != null) {
			Collection<Member> libraryMembersCollection = membersHashMap.values();

			member = libraryMemberExists(libraryMembersCollection, id);
			if (member == null) {
				throw new LoginException("ID " + id + " not found");
			}
		}

		HashMap<String, Item> bookMap = daItem.getElements();
		if (!bookMap.containsKey(isbn)) {
			throw new LoginException("ISBN " + isbn + " not found");
		}

		HashMap<String, Item> booksHashMap = new HashMap<String, Item>();
		booksHashMap = daItem.getElements();

		if (booksHashMap != null) {
			Collection<Item> booksCollection = booksHashMap.values();

			book = bookExists(booksCollection, isbn);
			if (book == null) {
				throw new LoginException("ISBN " + isbn + " not found");
			}
		}
	}

	private Member libraryMemberExists(Collection<Member> libraryMembersCollection, String memberId) {
		for (Member libraryMember : libraryMembersCollection) {
			if (libraryMember.getMemberId().equals(memberId)) {
				return libraryMember;
			}
		}
		return null;
	}

	private Item bookExists(Collection<Item> booksCollection, String bookISBNNo) {
		for (Item book : booksCollection) {
			if (book.getId().equals(bookISBNNo)) {
				return book;
			}
		}
		return null;
	}

	public void memberRecord(String id) throws LoginException {

	}

	private boolean isBookCopyOverDue(LocalDate dueDate) {
		return dueDate.isBefore(LocalDate.now());
	}

	@Override
	public ObservableList<Member> allMembers() {
		MemberData da = new MemberData();
		ObservableList<Member> retval = FXCollections.observableArrayList();
		retval.addAll(da.getElements().values());
		return retval;
	}

	@Override
	public List<Address> addresses() {
		AddressData da = new AddressData();
		List<Address> addresses = new ArrayList<>();
		addresses.addAll(da.getElements().values());
		return addresses;
	}

	@Override
	public Address createAddress(String street, String city, String state, String zip) {
		Address addr = new Address(street, city, state, zip);
		return addr;
	}

	@Override
	public boolean addMember(String memId, String fName, String lName, String phone, Address addr) {
		member = new Member(memId, fName, lName, phone, addr);
		MemberData da = new MemberData();
		return da.add(member, "MEMBERS");
	}

	@Override
	public boolean removeMember(String memId, String fName, String lName, String phone, Address addr)  {
		member = new Member(memId, fName, lName, phone, addr);
		MemberData da = new MemberData();
		return da.delete(member, "MEMBERS");
	}

	@Override
	public boolean updateMember(String memId, String fName, String lName, String phone, Address addr) {
		member = new Member(memId, fName, lName, phone, addr);
		MemberData da = new MemberData();
		return da.update(member, "MEMBERS");
	}

	ItemData item = new ItemData();

	@Override
	public void loadItemMap(List<Item> items) {
		for (Item i : items) {
			item.add(i, "ITEMS");
		}
	}

	@Override
	public List<Item> items() {
		List<Item> items = new ArrayList<>();
		items.addAll(item.getElements().values());
		return items;

	}

	@Override
	public void bookRecord(String isbn) throws LoginException {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean addUser(String id, String password,Auth type){
		ICommandFactory factory = StoreCommandFactory.getInstance();
		ICommand insertCommand = factory.createCommand(ObjectState.INSERTED);
		DataInvoke invoke = new DataInvoke();
		insertCommand.setDataManager(new UserData());
		insertCommand.setElement(new User(id, password, type));
		invoke.setCommand(insertCommand);
		invoke.execute();
		return true;
	}
	@Override
	public void updateUser(String id, String password,Auth type) {
		ICommandFactory factory = StoreCommandFactory.getInstance();
		ICommand updateCommand = factory.createCommand(ObjectState.UPDATED);
		DataInvoke invoke = new DataInvoke();
		updateCommand.setDataManager(new UserData());
		updateCommand.setElement(new User(id, password, type));
		invoke.setCommand(updateCommand);
		invoke.execute();

	}
	@Override
	public void deleteUser(String id, String password,Auth type) {
		ICommandFactory factory = StoreCommandFactory.getInstance();
		ICommand deleteCommand = factory.createCommand(ObjectState.DELETED);
		DataInvoke invoke = new DataInvoke();
		deleteCommand.setDataManager(new UserData());
		deleteCommand.setElement(new User(id, password, type));
		invoke.setCommand(deleteCommand);
		invoke.execute();
	}
	
	@Override
	public ObservableList<User> allUsers() {
		UserData da = new UserData();
		ObservableList<User> retval = FXCollections.observableArrayList();
		retval.addAll(da.getElements().values());
		return retval;
	}
	
	@Override
	public Auth[] allAuth(){
		return Auth.values();
	}
	@Override
	public void addCheckInOutRecordStatus(CheckInOutRecord checkInOutRecord) {
		DataInvoke invoke = new DataInvoke();
		ICommand insertCommand = new InsertCommand();
		insertCommand.setDataManager(new CheckInOutRecordData());
		insertCommand.setElement(checkInOutRecord);
		invoke.setCommand(insertCommand);
		invoke.execute();
		ICommandFactory factory = StoreCommandFactory.getInstance();
		factory.createCommand(ObjectState.INSERTED);
	}

	@Override
	public void updateCheckInOutRecordStatus(CheckInOutRecord checkInOutRecord, RECORDSTATUS status) {
		checkInOutRecord.setStatus(status);
		DataInvoke invoke = new DataInvoke();
		ICommand updateCommand = new UpdateCommand();
		updateCommand.setDataManager(new CheckInOutRecordData());
		updateCommand.setElement(checkInOutRecord);
		invoke.setCommand(updateCommand);
		invoke.execute();
		ICommandFactory factory = StoreCommandFactory.getInstance();
		factory.createCommand(ObjectState.UPDATED);
	}

	@Override
	public void deleteCheckInOutRecord(CheckInOutRecord checkInOutRecord) {
		DataInvoke invoke = new DataInvoke();
		ICommand deleteCommand = new DeleteCommand();
		deleteCommand.setDataManager(new CheckInOutRecordData());
		deleteCommand.setElement(checkInOutRecord);
		invoke.setCommand(deleteCommand);
		invoke.execute();

		ICommandFactory factory = StoreCommandFactory.getInstance();
		factory.createCommand(ObjectState.DELETED);
	}

	@Override
	public List<CheckInOutRecord> getAllCheckInOutRecords() {
		CheckInOutRecordData dataAccess = new CheckInOutRecordData();
		List<CheckInOutRecord> retval = new ArrayList<>();
		retval.addAll(dataAccess.getElements().values());
		return retval;
	}

	// CheckInOutRecordData checkInOutRecordData = new CheckInOutRecordData();
	@Override
	public List<CheckInOutRecord> getCheckInOutRecordBy(Member member, Item item) {
		CheckInOutRecordData dataAccess = new CheckInOutRecordData();
		List<CheckInOutRecord> returnValue = dataAccess.getElements().values().stream()
				.filter(entry -> entry.getMember().equals(member) && entry.getItem().equals(item))
				.collect(Collectors.toList());
		// List<CheckInOutRecord> returnValue = new
		// ArrayList<CheckInOutRecord>();
		return returnValue;
	}

}
