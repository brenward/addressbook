package com.bw.addressbook.dao;

import java.util.Comparator;
import java.util.TreeSet;

import com.bw.addressbook.model.AddressBookEntry;

public class AddressBookDao {
	private Database database;
	private TreeSet<AddressBookEntry> addressBook;
	private boolean saved;
	
	public AddressBookDao(){
		database = Database.getDatabase();
		addressBook = database.readInData();
		saved = true;
	}
	
	public TreeSet<AddressBookEntry> getAddressBook(){
		return addressBook;
	}
	
	public boolean deleteEntry(AddressBookEntry addressBookEntry){
		boolean deleted = addressBook.remove(addressBookEntry);
		if(deleted){
			saved = false;
		}
		return deleted;
	}
	
	public boolean editEntry(AddressBookEntry addressBookEntry){
		boolean updated = false;
		if(addressBook.contains(addressBookEntry)){
			addressBook.remove(addressBookEntry);
			updated = addressBook.add(addressBookEntry);
			saved = false;
		}else{
			System.out.println(addressBookEntry.toString());
		}
		return updated;
	}
	
	public boolean createEntry(AddressBookEntry addressBookEntry){
		boolean added = addressBook.add(addressBookEntry);
		if(added){
			saved = false;
		}
		return added;		
	}
	
	public boolean saveAddressBook(){
		return database.writeOutData(addressBook);
	}
	
	public void sortByZip(){
		TreeSet<AddressBookEntry> addressBookByZip = new TreeSet<AddressBookEntry>();
		addressBookByZip.addAll(addressBook);
		addressBook = addressBookByZip;
	}
	
	public void sortByName(){
		TreeSet<AddressBookEntry> addressBookByName = new TreeSet<AddressBookEntry>(new Comparator<AddressBookEntry>(){

			@Override
			public int compare(AddressBookEntry o1, AddressBookEntry o2) {
				if(!o1.getLastName().equals(o2.getLastName())){
					return o1.getLastName().compareToIgnoreCase(o2.getLastName());
				}else if(!o1.getFirstName().equals(o2.getFirstName())) {
					return o1.getFirstName().compareToIgnoreCase(o2.getFirstName());
				}else if(!o1.getZip().equals(o2.getZip())){
					return o1.getZip().compareToIgnoreCase(o2.getZip());
				}else{
					return o1.getPhoneNumber().compareToIgnoreCase(o2.getPhoneNumber());
				}
			}
		
		});
		addressBookByName.addAll(addressBook);
		addressBook = addressBookByName;
	}
	
	public void printAddressBook(){
		for(AddressBookEntry entry:addressBook){
			System.out.println(entry.toString());
		}
	}
	
	public boolean getSaved(){
		return saved;
	}

	
}
