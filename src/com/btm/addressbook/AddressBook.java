package com.btm.addressbook;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class AddressBook {

	static Scanner sc = new Scanner(System.in);

	static List<UserDetails> lst = new ArrayList<UserDetails>();

	public static void addContact() {
		System.out.println("Enter your firstName : ");
		String firstName = sc.nextLine();
		// Restricted to duplicate entry
		for (int i = 0; i < lst.size(); i++) {
			if (lst.get(i).getFirstName().equalsIgnoreCase(firstName)) {
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

		UserDetails obj = new UserDetails(firstName, lastName, address, city, state, zip, phoneNo, email);
		lst.add(obj);
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
		lst.stream().filter(n -> n.getCity().equals(city)).forEach(i -> System.out.println(i.getFirstName()));
	}

	// UC9
	public void viewByCity() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter city name : ");
		String city = sc.nextLine();
		sc.close();
		lst.stream().filter(n -> n.getCity().equals(city)).forEach(i -> System.out.println(i));
	}

	// UC10
	public void countBasedOnCity() {
		int count = 0;
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter city name : ");
		String city = sc.nextLine();
		count = (int) lst.stream().filter(n -> n.getCity().equals(city)).count();// count Stream;
		System.out.println(count);
	}

	// UC11
	public void sortingByName() {
		lst = lst.stream().sorted(Comparator.comparing(UserDetails::getFirstName)).collect(Collectors.toList());
		lst.forEach(i -> System.out.println(i));
	}

	// UC12
	public void sortingByCity() {
		lst = lst.stream().sorted(Comparator.comparing(UserDetails::getCity)).collect(Collectors.toList());
		lst.forEach(i -> System.out.println(i));
	}

	public static void main(String[] args) {
		AddressBook oj = new AddressBook();
		System.out.println("Welcome to Address Book Program...");
		addPerson();
		oj.sortingByCity();
	}
}
