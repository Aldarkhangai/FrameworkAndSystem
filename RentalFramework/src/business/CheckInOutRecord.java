package business;

import java.io.Serializable;
import java.time.LocalDate;

public class CheckInOutRecord implements Serializable {
	private static final long serialVersionUID = 3449195945707891169L;
	private String id;
	private Member member;
	private Item item;
	private LocalDate fromDate;
	private LocalDate toDate;
	private RECORDSTATUS status;
	private double price;
	private double amount;

	public CheckInOutRecord(String id, Member member, Item item, LocalDate fromDate, LocalDate toDate,
			RECORDSTATUS status, double price, double amount) {
		this.id = id;
		this.member = member;
		this.item = item;
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.status = status;
		this.price = price;
		this.amount = amount;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public LocalDate getFromDate() {
		return fromDate;
	}

	public void setFromDate(LocalDate fromDate) {
		this.fromDate = fromDate;
	}

	public LocalDate getToDate() {
		return toDate;
	}

	public void setToDate(LocalDate toDate) {
		this.toDate = toDate;
	}

	public RECORDSTATUS getStatus() {
		return status;
	}

	public void setStatus(RECORDSTATUS status) {
		this.status = status;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "CheckInOutRecord [id=" + id + ", member=" + member + ", item=" + item + ", fromDate=" + fromDate
				+ ", toDate=" + toDate + ", status=" + status + ", price=" + price + ", amount=" + amount + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fromDate == null) ? 0 : fromDate.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((item == null) ? 0 : item.hashCode());
		result = prime * result + ((member == null) ? 0 : member.hashCode());
		result = prime * result + ((toDate == null) ? 0 : toDate.hashCode());
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
		CheckInOutRecord other = (CheckInOutRecord) obj;
		if (fromDate == null) {
			if (other.fromDate != null)
				return false;
		} else if (!fromDate.equals(other.fromDate))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (item == null) {
			if (other.item != null)
				return false;
		} else if (!item.equals(other.item))
			return false;
		if (member == null) {
			if (other.member != null)
				return false;
		} else if (!member.equals(other.member))
			return false;
		if (toDate == null) {
			if (other.toDate != null)
				return false;
		} else if (!toDate.equals(other.toDate))
			return false;
		return true;
	}

	public enum RECORDSTATUS {
		checkin, checkout, checkincancelled, checkoutcancelled, reserved, reservationcancelled
	}

}
