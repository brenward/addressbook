package com.bw.addressbook.dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Map;

import com.bw.addressbook.model.Address;
import com.bw.addressbook.model.AddressBookEntry;

public class Database {
	private static Database DATABASE_INSTANCE = new Database();
	
	private Path database;
	
	private Database(){
		database = Paths.get("database.txt");
	}

	public static Database getDatabase(){
		return DATABASE_INSTANCE;
	}
	
	public ArrayList<AddressBookEntry> readInData(){
		ArrayList<AddressBookEntry> addressBook = new ArrayList<AddressBookEntry>();
		
		try(InputStream in = Files.newInputStream(database)){
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			String entry = reader.readLine();
			System.out.println("Reading in Database");
			while(entry != null){
				String[] rawAddressBookEntry = entry.split(",");
				AddressBookEntry addressBookEntry = new AddressBookEntry(Integer.parseInt(rawAddressBookEntry[0]),
						rawAddressBookEntry[1],
						rawAddressBookEntry[2],
						new Address(rawAddressBookEntry[3],rawAddressBookEntry[4],rawAddressBookEntry[5],rawAddressBookEntry[6]),
						rawAddressBookEntry[7],
						rawAddressBookEntry[8],
						rawAddressBookEntry[9]);
				addressBook.add(addressBookEntry);
				entry = reader.readLine();
			}
			
		}catch(IOException e){
			System.out.println("Error opening file!");
		}
		return addressBook;
	}
	
	public boolean writeOutData(ArrayList<AddressBookEntry> addressBook){
		try(OutputStream out = Files.newOutputStream(database)){
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out));
			
			for(AddressBookEntry entry:addressBook){
				StringBuilder sb = new StringBuilder();
				sb.append(entry.getIdAddress() + ",");
				sb.append(entry.getFirstName() + ",");
				sb.append(entry.getLastName() + ",");
				sb.append(entry.getAddress().getAddress1() + ",");
				sb.append(entry.getAddress().getAddress2() + ",");
				sb.append(entry.getAddress().getTown() + ",");
				sb.append(entry.getAddress().getCity() + ",");
				sb.append(entry.getPhoneNumber() + ",");
				sb.append(entry.getEmail() + ",");
				sb.append(entry.getZip());
				writer.write(sb.toString());
				writer.newLine();
				writer.flush();
			}
		}catch(IOException e){
			System.out.println("Error saving file!");
			return false;
		}
		System.out.println("Wrote out Data");
		return true;
	}
}
