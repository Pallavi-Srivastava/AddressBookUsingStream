package com.blz.addressbook;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class AddressBook {

	static Scanner sc = new Scanner(System.in);

	public enum IOService {
		CONSOLE_IO, FILE_IO, DB_IO, REST_IO
	}

	private static List<UserDetails> lst;

	public AddressBook(List<UserDetails> lst) {
		super();
		this.lst = lst;
	}

	public static void addContact() {
		System.out.println("Enter your firstName : ");
		String firstName = sc.nextLine();
		// Restricted to duplicate entry
		for (int i = 0; i < lst.size(); i++) {
			if (lst.get(i).getFirstName().equals(firstName)) {
				System.out.println("This name is already exists try with another name");
				addPerson();
				break;
			}
		}
		System.out.println("Enter your lastName : ");
		String lastName = sc.nextLine();
		System.out.println("Enter your address : ");
		String address = sc.nextLine();
		System.out.println("Enter your city : ");
		String city = sc.nextLine();
		System.out.println("Enter your state : ");
		String state = sc.nextLine();
		System.out.println("Enter your zipCode : ");
		String zip = sc.nextLine();
		System.out.println("Enter your phoneNo : ");
		String phoneNo = sc.nextLine();
		System.out.println("Enter your emailId : ");
		String email = sc.nextLine();
		lst.add(new UserDetails(firstName, lastName, address, city, state, zip, phoneNo, email));
	}

	// UC3
	public static void editContact() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter first name : ");
		String firstName = sc.nextLine();
		for (int i = 0; i < lst.size(); i++) {
			if (lst.get(i).getFirstName().equalsIgnoreCase(firstName)) {
				lst.remove(i);
				addContact();
			} else {
				System.out.println("No data found");
			}
		}
		sc.close();
	}

	// UC4
	public static void deleteContact() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter first name : ");
		String firstName = sc.nextLine();
		for (int i = 0; i < lst.size(); i++) {
			if (lst.get(i).getFirstName().equalsIgnoreCase(firstName)) {
				lst.remove(i);
			} else {
				System.out.println("No data found");
			}
		}
		sc.close();
	}

	// UC5
	public static void addPerson() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter how many AddressBook you want to add in system : ");
		int noOfAddressBook = sc.nextInt();
		int flag = 1;
		while (flag <= noOfAddressBook) {
			addContact();
			flag++;
		}
	}

	// UC8
	public void searchByCity() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter city name : ");
		String city = sc.nextLine();
		lst.stream().filter(n -> n.getCity().equals(city))
				.forEach(i -> System.out.println("Data Found:" + i.getFirstName()));
	}

	// UC13
	public static void writeAddressBookData(IOService ioService) {
		if (ioService.equals(com.blz.addressbook.AddressBook.IOService.CONSOLE_IO))
			System.out.println("Employee Payroll to Details : " + lst);
		if (ioService.equals(com.blz.addressbook.AddressBook.IOService.FILE_IO))
			new AddressBookFileIOService().writeData(lst);

	}

	// UC13
	public void readDataFromFile() {
		System.out.println("Enter address book name: ");
		String addressBookFile = sc.nextLine();
		Path filePath = Paths.get("E:\\Fellowship\\STS\\ReadOrWriteAddressBook\\" + addressBookFile + ".txt");
		try {
			Files.lines(filePath).map(line -> line.trim()).forEach(line -> System.out.println(line));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Method for count entries
	public long countEntries(IOService ioService) {
		if (ioService.equals(IOService.FILE_IO))
			return new AddressBookFileIOService().countEntries(ioService);
		return 0;
	}
}
