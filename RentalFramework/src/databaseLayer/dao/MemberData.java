package databaseLayer.dao;

import java.util.HashMap;

import business.Address;
import business.Member;
import config.Config;

public class MemberData implements IDataFacade<Member> {

	private String STORAGE = Config.getInstance().getMemberStorage();
	@SuppressWarnings("unchecked")
	@Override
	public HashMap<String, Member> getElements() {
		return (HashMap<String, Member>) readFromStorage(STORAGE);
	}

	@Override
	public boolean add(Member member, String Type) {
		HashMap<String, Member> memberMap = null;
		if (getElements() == null)
			memberMap = new HashMap<String, Member>();
		else
			memberMap = getElements();

		String keyItem = member.getMemberId();
		if (memberMap.keySet().contains(keyItem))
			return false;
		else
			memberMap.put(keyItem, member);
		saveToStorage(STORAGE, memberMap);
		return true;
	}

	@Override
	public boolean update(Member member, String Type) {
		HashMap<String, Member> hashMapMember = getElements();
		String keyMember = member.getMemberId();
		if (hashMapMember.keySet().contains(keyMember)) {
			hashMapMember.replace(keyMember, member);
			saveToStorage(STORAGE, hashMapMember);
			return true;
		} else
			return false;
	}

	@Override
	public boolean delete(Member member, String Type) {
		HashMap<String, Member> hashMapMember = getElements();
		String keyMember = member.getMemberId();
		if (hashMapMember.keySet().contains(keyMember)) {
			hashMapMember.remove(keyMember);
			saveToStorage(STORAGE, hashMapMember);
			return true;
		} else
			return false;
	}

	@Override
	public Member search(String id) {
		HashMap<String, Member> membersMap = getElements();
		Member m = membersMap.get(id);
		return m;
	}

}
