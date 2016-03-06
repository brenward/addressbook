package com.bw.addressbook.controller;

import java.util.Scanner;
import java.util.TreeSet;

import com.bw.addressbook.dao.AddressBookDao;
import com.bw.addressbook.model.Address;
import com.bw.addressbook.model.AddressBookEntry;
import com.bw.addressbook.view.UserInterface;

public class AddressBookController {
	private AddressBookDao addressBookDao;
	private UserInterface userInterface;
	
	public AddressBookController(){
		addressBookDao = new AddressBookDao();		
		userInterface = new UserInterface(new Scanner(System.in));
	}
	
	public static void main(String[] args) {
		AddressBookController  addressBookController = new AddressBookController();		
		boolean exit = false;
		String input = addressBookController.userInterface.start();
		while(!exit){
			if(input.equalsIgnoreCase("q")){
				exit = true;
				if(!addressBookController.addressBookDao.getSaved()){
					if(addressBookController.userInterface.save()){
						addressBookController.addressBookDao.saveAddressBook();
					}					
				}
			}else if(input.equalsIgnoreCase("p")){
				addressBookController.addressBookDao.printAddressBook();
			}else if(input.equalsIgnoreCase("s")){
				input = addressBookController.userInterface.sort();
				if(input.equalsIgnoreCase("n")){
					addressBookController.addressBookDao.sortByName();
				}else if(input.equalsIgnoreCase("z")){
					addressBookController.addressBookDao.sortByZip();
				}
			}else if(input.equalsIgnoreCase("a")){
				boolean added = addressBookController.addressBookDao.createEntry(addressBookController.userInterface.addEditDeleteEntry());
				addressBookController.userInterface.added(added);
			}else if(input.equalsIgnoreCase("e")){
				boolean edited = addressBookController.addressBookDao.editEntry(addressBookController.userInterface.addEditDeleteEntry());
				addressBookController.userInterface.edited(edited);
			}else if(input.equalsIgnoreCase("d")){
				boolean deleted = addressBookController.addressBookDao.deleteEntry(addressBookController.userInterface.addEditDeleteEntry());
				addressBookController.userInterface.deleted(deleted);
			}
			
			if(!exit){
				input = addressBookController.userInterface.start();
			}else{
				System.out.println("Exiting");
			}
		}

	}
}
