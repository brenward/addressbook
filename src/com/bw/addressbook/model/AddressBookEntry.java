package com.bw.addressbook.model;

public class AddressBookEntry implements Comparable{
	private int idAddress;
	private String firstName;
	private String lastName;
	private Address address;
	private String phoneNumber;
	private String email;
	private String zip;
	
	public AddressBookEntry(int idAddress, String firstName, String lastName, Address address, String phoneNumber, String email,
			String zip) {
		this.idAddress = idAddress;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.zip = zip;
	}
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Address getAddress() {
		return address;
	}
	
	public void setAddress(Address address) {
		this.address = address;
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getZip() {
		return zip;
	}
	
	public void setZip(String zip) {
		this.zip = zip;
	}
	
	public int getIdAddress(){
		return idAddress;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((phoneNumber == null) ? 0 : phoneNumber.hashCode());
		result = prime * result + ((zip == null) ? 0 : zip.hashCode());
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
		AddressBookEntry other = (AddressBookEntry) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (phoneNumber == null) {
			if (other.phoneNumber != null)
				return false;
		} else if (!phoneNumber.equals(other.phoneNumber))
			return false;
		if (zip == null) {
			if (other.zip != null)
				return false;
		} else if (!zip.equals(other.zip))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "AddressBookEntry [idAddress=" + idAddress +", firstName=" + firstName + ", lastName=" + lastName + ", address=" + address
				+ ", phoneNumber=" + phoneNumber + ", email=" + email + ", zip=" + zip + "]";
	}

	@Override
	public int compareTo(Object o) {
		if(!this.zip.equalsIgnoreCase(((AddressBookEntry)o).getZip())){
			return this.zip.compareTo(((AddressBookEntry)o).getZip());
		}else if(!this.lastName.equalsIgnoreCase(((AddressBookEntry)o).getLastName())){
			return this.lastName.compareTo(((AddressBookEntry)o).getLastName());
		}else if(!this.firstName.equalsIgnoreCase(((AddressBookEntry)o).getFirstName())){
			return this.firstName.compareTo(((AddressBookEntry)o).getFirstName());
		}else{
			return this.phoneNumber.compareTo(((AddressBookEntry)o).getPhoneNumber());
		}
		
	}	
}
