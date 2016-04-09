package com.bw.addressbook.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import com.bw.addressbook.model.Address;
import com.bw.addressbook.model.AddressBookEntry;

public class AddressBookDaoDB {
	
	private String url;
	private String username;
	private String password;
	private Comparator<AddressBookEntry> comparator;
	
	private String insertStatement = "INSERT INTO address (firstName,lastName,address1,address2,town,city,phoneNumber,email,zip) VALUES (?,?,?,?,?,?,?,?,?)";
	private String updateStatement = "UPDATE address SET firstName = ?, lastName = ?, address1 = ?, address2 = ?,town=?,city=?,phoneNumber=?,email=?,zip=? WHERE idAddress = ?";
	private String getEntryByIDStatement = "SELECT * FROM address WHERE idAddress = ?";
	private String deleteStatement = "DELETE FROM address WHERE idAddress = ?";
	private String getAllEntries = "SELECT * FROM address";
	
	public AddressBookDaoDB(){
		url="jdbc:mysql://localhost:3306/addressbook";
		username="root";
		password="password";
		sortByZip();
	}
	
	public ArrayList<AddressBookEntry> getAddressBook(){
		ArrayList<AddressBookEntry> addressBook = new ArrayList<>();
		try(Connection conn = DriverManager.getConnection(url, username, password);
				PreparedStatement stmt = conn.prepareStatement(getAllEntries)){
			ResultSet rs = stmt.executeQuery();
			while(rs.next()){
				addressBook.add(createAddressBookEntry(rs));
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		Collections.sort(addressBook, comparator);
		return addressBook;
	}
	
	public AddressBookEntry getEntryByID(int id){
		try(Connection conn = DriverManager.getConnection(url, username, password);
				PreparedStatement stmt = conn.prepareStatement(getEntryByIDStatement)){
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()){
				return createAddressBookEntry(rs);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return null;
	}
	
	private AddressBookEntry createAddressBookEntry(ResultSet rs) throws SQLException {
		Address address = new Address(rs.getString("address1"),rs.getString("address2"),rs.getString("town"),rs.getString("city"));
		AddressBookEntry addressBookEntry = new AddressBookEntry(rs.getString("firstName"), rs.getString("lastName"), address, rs.getString("phoneNumber"),rs.getString("email"), rs.getString("zip"));
		return addressBookEntry;
	}

	public boolean deleteEntry(int id) {
		try(Connection conn = DriverManager.getConnection(url, username, password);
				PreparedStatement stmt = conn.prepareStatement(deleteStatement)){
			stmt.setInt(1, id);
			stmt.executeUpdate();
			return true;
		}catch(SQLException e){
			e.printStackTrace();
		}
		return false;
	}


	public boolean editEntryByID(int id, AddressBookEntry addressBookEntry) {
		if(getEntryByID(id) != null){
			try(Connection conn = DriverManager.getConnection(url, username, password);
					PreparedStatement stmt = conn.prepareStatement(updateStatement)){
				setStatementAddressParams(stmt, addressBookEntry);
				stmt.setInt(10, id);
				stmt.executeUpdate();
				return true;
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		return false;
	}


	public boolean createEntry(AddressBookEntry addressBookEntry) {
		try(Connection conn = DriverManager.getConnection(url, username, password);
				PreparedStatement stmt = conn.prepareStatement(insertStatement)){
			setStatementAddressParams(stmt, addressBookEntry);
			stmt.executeUpdate();
			return true;
		}catch(SQLException e){
			return false;
		}
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


	public void printAddressBook() {
		ArrayList<AddressBookEntry> addressBook = getAddressBook();
		for(AddressBookEntry address: addressBook){
			System.out.println(address.toString());
		}
		
	}
	
	private void setStatementAddressParams(PreparedStatement stmt, AddressBookEntry addressBookEntry) throws SQLException{
		stmt.setString(1, addressBookEntry.getFirstName());
		stmt.setString(2, addressBookEntry.getLastName());
		stmt.setString(3, addressBookEntry.getAddress().getAddress1());
		stmt.setString(4, addressBookEntry.getAddress().getAddress2());
		stmt.setString(5, addressBookEntry.getAddress().getTown());
		stmt.setString(6, addressBookEntry.getAddress().getCity());
		stmt.setString(7, addressBookEntry.getPhoneNumber());
		stmt.setString(8, addressBookEntry.getEmail());
		stmt.setString(9, addressBookEntry.getZip());
	}


}
