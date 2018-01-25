package testCheckInOutRecord;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

import business.CheckInOutRecord;
import business.CheckInOutRecord.RECORDSTATUS;
import controller.ControllerInterface;
import controller.SystemController;
import business.Item;
import business.Member;
import config.Config;
import databaseLayer.dao.CheckInOutRecordData;
import databaseLayer.dao.IDataFacade;

public class Driver {
	private final static String STORAGE = "CHECKINOUTRECORDS";

	// CheckInOutRecord(Item item, LocalDate checkInDate, LocalDate
	// checkOutDate, RECORDSTATUS status, double price, double amount)
	public static void main(String[] args) {
		ControllerInterface controller = new SystemController();

		// print all check in, check out records
		controller.getAllCheckInOutRecords().forEach(item -> System.out.println(item));

		// delete all check in, out records
		controller.getAllCheckInOutRecords().forEach(item -> controller.deleteCheckInOutRecord(item));

		// print all members
		controller.allMembers().forEach(item -> System.out.println(item));

		// configuration print
		System.out.println(Config.getInstance().toString());

		Optional<Member> member = controller.allMembers().stream().findAny();
		Item item1 = new Item("100", "Car#1", "Smart car");
		CheckInOutRecord record1 = new CheckInOutRecord("111", member.get(), item1, LocalDate.now(),
				LocalDate.now().plusWeeks(2), RECORDSTATUS.checkin, 0, 0);
		controller.addCheckInOutRecordStatus(record1);

		// print all check in, check out records
		controller.getAllCheckInOutRecords().forEach(item -> System.out.println("After add new record: " + item));

		// cancel check in record
		controller.updateCheckInOutRecordStatus(record1, RECORDSTATUS.checkincancelled);

		// print all check in, check out records
		controller.getAllCheckInOutRecords()
				.forEach(item -> System.out.println("After item check in cancel : " + item));

		controller.updateCheckInOutRecordStatus(record1, RECORDSTATUS.checkout);
		// print all check in, check out records
		controller.getAllCheckInOutRecords()
				.forEach(item -> System.out.println("After item check out : " + item));

		// find any record by member and item
		controller.getCheckInOutRecordBy(member.get(), item1)
				.forEach(item -> System.out.println("Found item: " + item));

	}
}
