package com.bw.addressbook.dao;

import java.util.ArrayList;

import com.bw.addressbook.model.AddressBookEntry;

public interface AddressBookDao {
	
	public ArrayList<AddressBookEntry> getAddressBook();
	
	public AddressBookEntry getEntryByID(int id);
	
	public boolean deleteEntry(int id);
	
	public boolean editEntryByID(int id, AddressBookEntry addressBookEntry);
	
	public boolean createEntry(AddressBookEntry addressBookEntry);
	
	public void sortByZip();
	
	public void sortByName();
	
	public void printAddressBook();

}
