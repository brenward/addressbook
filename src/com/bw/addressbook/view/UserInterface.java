package com.bw.addressbook.view;

import java.util.Scanner;

import com.bw.addressbook.model.Address;
import com.bw.addressbook.model.AddressBookEntry;

public class UserInterface {
	private Scanner sc;
	public UserInterface(Scanner sc){
		this.sc = sc;
	}
	
	
	public String start(){
		System.out.println("Type q to quit, p to print, s to sort, a to add an entry, e to edit an entry, d to delete an entry");
		return sc.nextLine();
	}


	public String sort() {
		System.out.println("Type n to sort by name, z to sort by zip");
		return sc.nextLine();
	}


	public AddressBookEntry addEditDeleteEntry() {
		System.out.print("Type First Name: ");
		String firstName = sc.nextLine();
		System.out.print("Type Last Name: ");
		String lastName = sc.nextLine();
		System.out.print("Type Address1: ");
		String address1 = sc.nextLine();
		System.out.print("Type Address2: ");
		String address2 = sc.nextLine();
		System.out.print("Type Town: ");
		String town = sc.nextLine();
		System.out.print("Type City: ");
		String city = sc.nextLine();
		System.out.print("Type Phone: ");
		String phone = sc.nextLine();
		System.out.print("Type Email: ");
		String email = sc.nextLine();
		System.out.print("Type Zip: ");
		String zip = sc.nextLine();
		return new AddressBookEntry(0,firstName,lastName,new Address(address1,address2,town,city),phone,email,zip);
	}


	public void added(boolean added) {
		if(added){
			System.out.println("Entry added successfully");
		}else{
			System.out.println("Entry not added, possibly a duplicate");
		}		
	}


	public void edited(boolean edited) {
		if(edited){
			System.out.println("Entry edited successfully");
		}else{
			System.out.println("Entry not edited, possibly does not exist");
		}		
	}


	public void deleted(boolean deleted) {
		if(deleted){
			System.out.println("Entry deleted successfully");
		}else{
			System.out.println("Entry not deleted, possibly does not exist");
		}		
	}


	public boolean save() {
		System.out.println("Do you want to save your updates to the file system? y or n");
		String save = sc.nextLine();
		if(save.equalsIgnoreCase("y")){
			return true;
		}
		return false;
	}
	
	public int addressId(){
		System.out.print("Enter ID of address: ");
		int id = sc.nextInt();
		sc.nextLine();
		return id;
	}
}
