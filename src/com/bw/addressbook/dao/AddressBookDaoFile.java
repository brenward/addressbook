package com.bw.addressbook.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

import com.bw.addressbook.model.AddressBookEntry;

public class AddressBookDaoFile implements AddressBookDao{
	private Database database;
	private ArrayList<AddressBookEntry> addressBook;
	private boolean saved;
	private Comparator<AddressBookEntry> comparator;
	
	public AddressBookDaoFile(){
		database = Database.getDatabase();
		addressBook = database.readInData();
		saved = true;
		sortByZip();
	}
	
	public ArrayList<AddressBookEntry> getAddressBook(){
		Collections.sort(addressBook,comparator);
		return addressBook;
	}
	
	public boolean deleteEntry(int id){
		AddressBookEntry entryToRemove = getEntryByID(id);
		boolean deleted = false;
		if(entryToRemove!=null){
			deleted = addressBook.remove(entryToRemove);
		}
		
		if(deleted){
			saved = false;
		}
		return deleted;
	}
	
	public boolean editEntryByID(int id, AddressBookEntry addressBookEntry){
		boolean updated = false;
		AddressBookEntry entryToEdit = getEntryByID(id);
		if(entryToEdit!=null){
			if(addressBook.contains(entryToEdit)){
				addressBook.remove(entryToEdit);
				AddressBookEntry updatedEntry = new AddressBookEntry(id,addressBookEntry.getFirstName(), addressBookEntry.getLastName(),
						addressBookEntry.getAddress(),addressBookEntry.getPhoneNumber(),addressBookEntry.getEmail(), addressBookEntry.getZip());  
				updated = addressBook.add(updatedEntry);
				saved = false;
			}else{
				System.out.println(addressBookEntry.toString());
			}
		}
		return updated;
	}
	
	public boolean createEntry(AddressBookEntry addressBookEntry){
		int id = getMaxId();
		AddressBookEntry addedEntry = new AddressBookEntry(id,addressBookEntry.getFirstName(), addressBookEntry.getLastName(),
				addressBookEntry.getAddress(),addressBookEntry.getPhoneNumber(),addressBookEntry.getEmail(), addressBookEntry.getZip());
		boolean added = addressBook.add(addedEntry);
		if(added){
			saved = false;
		}
		return added;		
	}
	
	public boolean saveAddressBook(){
		return database.writeOutData(addressBook);
	}
	
	public void sortByZip() {
		comparator = new Comparator<AddressBookEntry>(){

			@Override
			public int compare(AddressBookEntry o1, AddressBookEntry o2) {
				return o1.compareTo(o2);
			}			
		};
		
	}


	public void sortByName() {
		comparator = new Comparator<AddressBookEntry>(){

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
		};		
	}
	
	public void printAddressBook(){
		for(AddressBookEntry entry:addressBook){
			System.out.println(entry.toString());
		}
	}
	
	public boolean getSaved(){
		return saved;
	}

	@Override
	public AddressBookEntry getEntryByID(int id) {
		for(AddressBookEntry entry: addressBook){
			if(entry.getIdAddress() == id){
				return entry;
			}
		}
		return null;
	}	
	
	private int getMaxId(){
		int maxId = 0;
		for(AddressBookEntry entry:addressBook){
			if(maxId < entry.getIdAddress()){
				maxId = entry.getIdAddress() +1 ;
			}			
		}
		return maxId;
	}
}
